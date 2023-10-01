import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFeature } from '../feature.model';
import { FeatureService } from '../service/feature.service';

@Component({
  templateUrl: './feature-delete-dialog.component.html',
})
export class FeatureDeleteDialogComponent {
  feature?: IFeature;

  constructor(protected featureService: FeatureService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.featureService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
