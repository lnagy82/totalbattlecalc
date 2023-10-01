import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFeature, getFeatureIdentifier } from '../feature.model';

export type EntityResponseType = HttpResponse<IFeature>;
export type EntityArrayResponseType = HttpResponse<IFeature[]>;

@Injectable({ providedIn: 'root' })
export class FeatureService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/features');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(feature: IFeature): Observable<EntityResponseType> {
    return this.http.post<IFeature>(this.resourceUrl, feature, { observe: 'response' });
  }

  update(feature: IFeature): Observable<EntityResponseType> {
    return this.http.put<IFeature>(`${this.resourceUrl}/${getFeatureIdentifier(feature) as number}`, feature, { observe: 'response' });
  }

  partialUpdate(feature: IFeature): Observable<EntityResponseType> {
    return this.http.patch<IFeature>(`${this.resourceUrl}/${getFeatureIdentifier(feature) as number}`, feature, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFeature>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFeature[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addFeatureToCollectionIfMissing(featureCollection: IFeature[], ...featuresToCheck: (IFeature | null | undefined)[]): IFeature[] {
    const features: IFeature[] = featuresToCheck.filter(isPresent);
    if (features.length > 0) {
      const featureCollectionIdentifiers = featureCollection.map(featureItem => getFeatureIdentifier(featureItem)!);
      const featuresToAdd = features.filter(featureItem => {
        const featureIdentifier = getFeatureIdentifier(featureItem);
        if (featureIdentifier == null || featureCollectionIdentifiers.includes(featureIdentifier)) {
          return false;
        }
        featureCollectionIdentifiers.push(featureIdentifier);
        return true;
      });
      return [...featuresToAdd, ...featureCollection];
    }
    return featureCollection;
  }
}
