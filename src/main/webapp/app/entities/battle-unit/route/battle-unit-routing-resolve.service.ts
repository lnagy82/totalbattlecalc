import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBattleUnit, BattleUnit } from '../battle-unit.model';
import { BattleUnitService } from '../service/battle-unit.service';

@Injectable({ providedIn: 'root' })
export class BattleUnitRoutingResolveService implements Resolve<IBattleUnit> {
  constructor(protected service: BattleUnitService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBattleUnit> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((battleUnit: HttpResponse<BattleUnit>) => {
          if (battleUnit.body) {
            return of(battleUnit.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BattleUnit(0, 0, 0.0, 0.0));
  }
}
