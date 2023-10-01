import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBattleUnit, getBattleUnitIdentifier } from '../battle-unit.model';

export type EntityResponseType = HttpResponse<IBattleUnit>;
export type EntityArrayResponseType = HttpResponse<IBattleUnit[]>;

@Injectable({ providedIn: 'root' })
export class BattleUnitService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/battle-units');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(battleUnit: IBattleUnit): Observable<EntityResponseType> {
    return this.http.post<IBattleUnit>(this.resourceUrl, battleUnit, { observe: 'response' });
  }

  update(battleUnit: IBattleUnit): Observable<EntityResponseType> {
    return this.http.put<IBattleUnit>(`${this.resourceUrl}/${getBattleUnitIdentifier(battleUnit) as number}`, battleUnit, {
      observe: 'response',
    });
  }

  partialUpdate(battleUnit: IBattleUnit): Observable<EntityResponseType> {
    return this.http.patch<IBattleUnit>(`${this.resourceUrl}/${getBattleUnitIdentifier(battleUnit) as number}`, battleUnit, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBattleUnit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBattleUnit[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addBattleUnitToCollectionIfMissing(
    battleUnitCollection: IBattleUnit[],
    ...battleUnitsToCheck: (IBattleUnit | null | undefined)[]
  ): IBattleUnit[] {
    const battleUnits: IBattleUnit[] = battleUnitsToCheck.filter(isPresent);
    if (battleUnits.length > 0) {
      const battleUnitCollectionIdentifiers = battleUnitCollection.map(battleUnitItem => getBattleUnitIdentifier(battleUnitItem)!);
      const battleUnitsToAdd = battleUnits.filter(battleUnitItem => {
        const battleUnitIdentifier = getBattleUnitIdentifier(battleUnitItem);
        if (battleUnitIdentifier == null || battleUnitCollectionIdentifiers.includes(battleUnitIdentifier)) {
          return false;
        }
        battleUnitCollectionIdentifiers.push(battleUnitIdentifier);
        return true;
      });
      return [...battleUnitsToAdd, ...battleUnitCollection];
    }
    return battleUnitCollection;
  }
}
