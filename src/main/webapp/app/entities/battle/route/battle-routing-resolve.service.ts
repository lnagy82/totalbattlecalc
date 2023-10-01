import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBattle, Battle } from '../battle.model';
import { BattleService } from '../service/battle.service';

@Injectable({ providedIn: 'root' })
export class BattleRoutingResolveService implements Resolve<IBattle> {
  constructor(protected service: BattleService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBattle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((battle: HttpResponse<Battle>) => {
          if (battle.body) {
            return of(battle.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Battle());
  }
}
