import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BattleUnitComponent } from '../list/battle-unit.component';
import { BattleUnitDetailComponent } from '../detail/battle-unit-detail.component';
import { BattleUnitUpdateComponent } from '../update/battle-unit-update.component';
import { BattleUnitRoutingResolveService } from './battle-unit-routing-resolve.service';

const battleUnitRoute: Routes = [
  {
    path: '',
    component: BattleUnitComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BattleUnitDetailComponent,
    resolve: {
      battleUnit: BattleUnitRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BattleUnitUpdateComponent,
    resolve: {
      battleUnit: BattleUnitRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BattleUnitUpdateComponent,
    resolve: {
      battleUnit: BattleUnitRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(battleUnitRoute)],
  exports: [RouterModule],
})
export class BattleUnitRoutingModule {}
