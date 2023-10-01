import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BattleComponent } from './list/battle.component';
import { BattleDetailComponent } from './detail/battle-detail.component';
import { BattleUpdateComponent } from './update/battle-update.component';
import { BattleDeleteDialogComponent } from './delete/battle-delete-dialog.component';
import { BattleRoutingModule } from './route/battle-routing.module';

@NgModule({
  imports: [SharedModule, BattleRoutingModule],
  declarations: [BattleComponent, BattleDetailComponent, BattleUpdateComponent, BattleDeleteDialogComponent],
  entryComponents: [BattleDeleteDialogComponent],
})
export class BattleModule {}
