import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFeature, Feature } from '../feature.model';
import { FeatureService } from '../service/feature.service';

@Injectable({ providedIn: 'root' })
export class FeatureRoutingResolveService implements Resolve<IFeature> {
  constructor(protected service: FeatureService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFeature> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((feature: HttpResponse<Feature>) => {
          if (feature.body) {
            return of(feature.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Feature());
  }
}
