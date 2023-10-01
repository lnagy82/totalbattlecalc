import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FeatureComponent } from './list/feature.component';
import { FeatureDetailComponent } from './detail/feature-detail.component';
import { FeatureUpdateComponent } from './update/feature-update.component';
import { FeatureDeleteDialogComponent } from './delete/feature-delete-dialog.component';
import { FeatureRoutingModule } from './route/feature-routing.module';

@NgModule({
  imports: [SharedModule, FeatureRoutingModule],
  declarations: [FeatureComponent, FeatureDetailComponent, FeatureUpdateComponent, FeatureDeleteDialogComponent],
  entryComponents: [FeatureDeleteDialogComponent],
})
export class FeatureModule {}
