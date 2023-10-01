import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BonusComponent } from './list/bonus.component';
import { BonusDetailComponent } from './detail/bonus-detail.component';
import { BonusUpdateComponent } from './update/bonus-update.component';
import { BonusDeleteDialogComponent } from './delete/bonus-delete-dialog.component';
import { BonusRoutingModule } from './route/bonus-routing.module';

@NgModule({
  imports: [SharedModule, BonusRoutingModule],
  declarations: [BonusComponent, BonusDetailComponent, BonusUpdateComponent, BonusDeleteDialogComponent],
  entryComponents: [BonusDeleteDialogComponent],
})
export class BonusModule {}
