import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IBonus, Bonus } from '../bonus.model';
import { BonusService } from '../service/bonus.service';
import { BonusName } from 'app/entities/enumerations/bonus-name.model';
import { MeasurementUnit } from 'app/entities/enumerations/measurement-unit.model';

@Component({
  selector: 'jhi-bonus-update',
  templateUrl: './bonus-update.component.html',
})
export class BonusUpdateComponent implements OnInit {
  isSaving = false;
  bonusNameValues = Object.keys(BonusName);
  measurementUnitValues = Object.keys(MeasurementUnit);

  editForm = this.fb.group({
    id: [],
    name: [],
    value: [],
    unit: [],
  });

  constructor(protected bonusService: BonusService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bonus }) => {
      this.updateForm(bonus);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bonus = this.createFromForm();
    if (bonus.id !== undefined) {
      this.subscribeToSaveResponse(this.bonusService.update(bonus));
    } else {
      this.subscribeToSaveResponse(this.bonusService.create(bonus));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBonus>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(bonus: IBonus): void {
    this.editForm.patchValue({
      id: bonus.id,
      name: bonus.name,
      value: bonus.value,
      unit: bonus.unit,
    });
  }

  protected createFromForm(): IBonus {
    return {
      ...new Bonus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      value: this.editForm.get(['value'])!.value,
      unit: this.editForm.get(['unit'])!.value,
    };
  }
}
