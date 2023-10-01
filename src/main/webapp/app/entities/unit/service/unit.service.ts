import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUnit, getUnitIdentifier } from '../unit.model';

export type EntityResponseType = HttpResponse<IUnit>;
export type EntityArrayResponseType = HttpResponse<IUnit[]>;

@Injectable({ providedIn: 'root' })
export class UnitService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/units');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(unit: IUnit): Observable<EntityResponseType> {
    return this.http.post<IUnit>(this.resourceUrl, unit, { observe: 'response' });
  }

  update(unit: IUnit): Observable<EntityResponseType> {
    return this.http.put<IUnit>(`${this.resourceUrl}/${getUnitIdentifier(unit) as number}`, unit, { observe: 'response' });
  }

  partialUpdate(unit: IUnit): Observable<EntityResponseType> {
    return this.http.patch<IUnit>(`${this.resourceUrl}/${getUnitIdentifier(unit) as number}`, unit, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUnit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUnit[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addUnitToCollectionIfMissing(unitCollection: IUnit[], ...unitsToCheck: (IUnit | null | undefined)[]): IUnit[] {
    const units: IUnit[] = unitsToCheck.filter(isPresent);
    if (units.length > 0) {
      const unitCollectionIdentifiers = unitCollection.map(unitItem => getUnitIdentifier(unitItem)!);
      const unitsToAdd = units.filter(unitItem => {
        const unitIdentifier = getUnitIdentifier(unitItem);
        if (unitIdentifier == null || unitCollectionIdentifiers.includes(unitIdentifier)) {
          return false;
        }
        unitCollectionIdentifiers.push(unitIdentifier);
        return true;
      });
      return [...unitsToAdd, ...unitCollection];
    }
    return unitCollection;
  }
}
