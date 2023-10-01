import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBattleUnit } from '../battle-unit.model';
import { BattleUnitService } from '../service/battle-unit.service';

@Component({
  templateUrl: './battle-unit-delete-dialog.component.html',
})
export class BattleUnitDeleteDialogComponent {
  battleUnit?: IBattleUnit;

  constructor(protected battleUnitService: BattleUnitService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.battleUnitService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
