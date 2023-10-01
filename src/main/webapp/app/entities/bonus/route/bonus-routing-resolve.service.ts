import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBonus, Bonus } from '../bonus.model';
import { BonusService } from '../service/bonus.service';

@Injectable({ providedIn: 'root' })
export class BonusRoutingResolveService implements Resolve<IBonus> {
  constructor(protected service: BonusService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBonus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((bonus: HttpResponse<Bonus>) => {
          if (bonus.body) {
            return of(bonus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bonus());
  }
}
