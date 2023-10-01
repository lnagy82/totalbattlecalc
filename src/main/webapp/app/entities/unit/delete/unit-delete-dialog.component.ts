import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IUnit } from '../unit.model';
import { UnitService } from '../service/unit.service';

@Component({
  templateUrl: './unit-delete-dialog.component.html',
})
export class UnitDeleteDialogComponent {
  unit?: IUnit;

  constructor(protected unitService: UnitService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.unitService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
