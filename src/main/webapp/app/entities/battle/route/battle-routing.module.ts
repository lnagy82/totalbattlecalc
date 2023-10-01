import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BattleComponent } from '../list/battle.component';
import { BattleDetailComponent } from '../detail/battle-detail.component';
import { BattleUpdateComponent } from '../update/battle-update.component';
import { BattleRoutingResolveService } from './battle-routing-resolve.service';

const battleRoute: Routes = [
  {
    path: '',
    component: BattleComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BattleDetailComponent,
    resolve: {
      battle: BattleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BattleUpdateComponent,
    resolve: {
      battle: BattleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BattleUpdateComponent,
    resolve: {
      battle: BattleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(battleRoute)],
  exports: [RouterModule],
})
export class BattleRoutingModule {}
