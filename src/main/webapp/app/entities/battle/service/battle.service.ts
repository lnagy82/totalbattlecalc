import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBattle, getBattleIdentifier } from '../battle.model';

export type EntityResponseType = HttpResponse<IBattle>;
export type EntityArrayResponseType = HttpResponse<IBattle[]>;

@Injectable({ providedIn: 'root' })
export class BattleService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/battles');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(battle: IBattle): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(battle);
    return this.http
      .post<IBattle>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(battle: IBattle): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(battle);
    return this.http
      .put<IBattle>(`${this.resourceUrl}/${getBattleIdentifier(battle) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(battle: IBattle): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(battle);
    return this.http
      .patch<IBattle>(`${this.resourceUrl}/${getBattleIdentifier(battle) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBattle>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBattle[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addBattleToCollectionIfMissing(battleCollection: IBattle[], ...battlesToCheck: (IBattle | null | undefined)[]): IBattle[] {
    const battles: IBattle[] = battlesToCheck.filter(isPresent);
    if (battles.length > 0) {
      const battleCollectionIdentifiers = battleCollection.map(battleItem => getBattleIdentifier(battleItem)!);
      const battlesToAdd = battles.filter(battleItem => {
        const battleIdentifier = getBattleIdentifier(battleItem);
        if (battleIdentifier == null || battleCollectionIdentifiers.includes(battleIdentifier)) {
          return false;
        }
        battleCollectionIdentifiers.push(battleIdentifier);
        return true;
      });
      return [...battlesToAdd, ...battleCollection];
    }
    return battleCollection;
  }

  protected convertDateFromClient(battle: IBattle): IBattle {
    return Object.assign({}, battle, {
      date: battle.date?.isValid() ? battle.date.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? dayjs(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((battle: IBattle) => {
        battle.date = battle.date ? dayjs(battle.date) : undefined;
      });
    }
    return res;
  }
}
