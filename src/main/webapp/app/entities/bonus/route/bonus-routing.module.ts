import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BonusComponent } from '../list/bonus.component';
import { BonusDetailComponent } from '../detail/bonus-detail.component';
import { BonusUpdateComponent } from '../update/bonus-update.component';
import { BonusRoutingResolveService } from './bonus-routing-resolve.service';

const bonusRoute: Routes = [
  {
    path: '',
    component: BonusComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BonusDetailComponent,
    resolve: {
      bonus: BonusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BonusUpdateComponent,
    resolve: {
      bonus: BonusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BonusUpdateComponent,
    resolve: {
      bonus: BonusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(bonusRoute)],
  exports: [RouterModule],
})
export class BonusRoutingModule {}
