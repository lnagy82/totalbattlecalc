import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { UnitComponent } from '../list/unit.component';
import { UnitDetailComponent } from '../detail/unit-detail.component';
import { UnitUpdateComponent } from '../update/unit-update.component';
import { UnitRoutingResolveService } from './unit-routing-resolve.service';

const unitRoute: Routes = [
  {
    path: '',
    component: UnitComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UnitDetailComponent,
    resolve: {
      unit: UnitRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UnitUpdateComponent,
    resolve: {
      unit: UnitRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UnitUpdateComponent,
    resolve: {
      unit: UnitRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(unitRoute)],
  exports: [RouterModule],
})
export class UnitRoutingModule {}
