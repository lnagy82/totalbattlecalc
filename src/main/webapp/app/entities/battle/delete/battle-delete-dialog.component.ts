import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBattle } from '../battle.model';
import { BattleService } from '../service/battle.service';

@Component({
  templateUrl: './battle-delete-dialog.component.html',
})
export class BattleDeleteDialogComponent {
  battle?: IBattle;

  constructor(protected battleService: BattleService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.battleService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
