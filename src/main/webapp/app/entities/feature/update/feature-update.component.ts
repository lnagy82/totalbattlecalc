import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IFeature, Feature } from '../feature.model';
import { FeatureService } from '../service/feature.service';
import { FeatureName } from 'app/entities/enumerations/feature-name.model';
import { MeasurementUnit } from 'app/entities/enumerations/measurement-unit.model';

@Component({
  selector: 'jhi-feature-update',
  templateUrl: './feature-update.component.html',
})
export class FeatureUpdateComponent implements OnInit {
  isSaving = false;
  featureNameValues = Object.keys(FeatureName);
  measurementUnitValues = Object.keys(MeasurementUnit);

  editForm = this.fb.group({
    id: [],
    name: [],
    value: [],
    unit: [],
  });

  constructor(protected featureService: FeatureService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ feature }) => {
      this.updateForm(feature);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const feature = this.createFromForm();
    if (feature.id !== undefined) {
      this.subscribeToSaveResponse(this.featureService.update(feature));
    } else {
      this.subscribeToSaveResponse(this.featureService.create(feature));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFeature>>): void {
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

  protected updateForm(feature: IFeature): void {
    this.editForm.patchValue({
      id: feature.id,
      name: feature.name,
      value: feature.value,
      unit: feature.unit,
    });
  }

  protected createFromForm(): IFeature {
    return {
      ...new Feature(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      value: this.editForm.get(['value'])!.value,
      unit: this.editForm.get(['unit'])!.value,
    };
  }
}
