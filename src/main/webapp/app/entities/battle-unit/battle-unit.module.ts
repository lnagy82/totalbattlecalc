import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BattleUnitComponent } from './list/battle-unit.component';
import { BattleUnitDetailComponent } from './detail/battle-unit-detail.component';
import { BattleUnitUpdateComponent } from './update/battle-unit-update.component';
import { BattleUnitDeleteDialogComponent } from './delete/battle-unit-delete-dialog.component';
import { BattleUnitRoutingModule } from './route/battle-unit-routing.module';

@NgModule({
  imports: [SharedModule, BattleUnitRoutingModule],
  declarations: [BattleUnitComponent, BattleUnitDetailComponent, BattleUnitUpdateComponent, BattleUnitDeleteDialogComponent],
  entryComponents: [BattleUnitDeleteDialogComponent],
})
export class BattleUnitModule {}
