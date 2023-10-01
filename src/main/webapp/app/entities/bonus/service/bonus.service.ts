import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBonus, getBonusIdentifier } from '../bonus.model';

export type EntityResponseType = HttpResponse<IBonus>;
export type EntityArrayResponseType = HttpResponse<IBonus[]>;

@Injectable({ providedIn: 'root' })
export class BonusService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/bonuses');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(bonus: IBonus): Observable<EntityResponseType> {
    return this.http.post<IBonus>(this.resourceUrl, bonus, { observe: 'response' });
  }

  update(bonus: IBonus): Observable<EntityResponseType> {
    return this.http.put<IBonus>(`${this.resourceUrl}/${getBonusIdentifier(bonus) as number}`, bonus, { observe: 'response' });
  }

  partialUpdate(bonus: IBonus): Observable<EntityResponseType> {
    return this.http.patch<IBonus>(`${this.resourceUrl}/${getBonusIdentifier(bonus) as number}`, bonus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBonus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBonus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addBonusToCollectionIfMissing(bonusCollection: IBonus[], ...bonusesToCheck: (IBonus | null | undefined)[]): IBonus[] {
    const bonuses: IBonus[] = bonusesToCheck.filter(isPresent);
    if (bonuses.length > 0) {
      const bonusCollectionIdentifiers = bonusCollection.map(bonusItem => getBonusIdentifier(bonusItem)!);
      const bonusesToAdd = bonuses.filter(bonusItem => {
        const bonusIdentifier = getBonusIdentifier(bonusItem);
        if (bonusIdentifier == null || bonusCollectionIdentifiers.includes(bonusIdentifier)) {
          return false;
        }
        bonusCollectionIdentifiers.push(bonusIdentifier);
        return true;
      });
      return [...bonusesToAdd, ...bonusCollection];
    }
    return bonusCollection;
  }
}
