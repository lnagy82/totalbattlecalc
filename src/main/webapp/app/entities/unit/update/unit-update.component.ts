import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IUnit, Unit } from '../unit.model';
import { UnitService } from '../service/unit.service';
import { IFeature } from 'app/entities/feature/feature.model';
import { FeatureService } from 'app/entities/feature/service/feature.service';

@Component({
  selector: 'jhi-unit-update',
  templateUrl: './unit-update.component.html',
})
export class UnitUpdateComponent implements OnInit {
  isSaving = false;

  featuresSharedCollection: IFeature[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    strength: [],
    health: [],
    leadership: [],
    speed: [],
    initiative: [],
    foodConsumption: [],
    carryingCapacity: [],
    revivalCostAfterAnAttackGold: [],
    revivalCostAfterDefendingSilver: [],
    features: [],
  });

  constructor(
    protected unitService: UnitService,
    protected featureService: FeatureService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ unit }) => {
      this.updateForm(unit);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const unit = this.createFromForm();
    if (unit.id !== undefined) {
      this.subscribeToSaveResponse(this.unitService.update(unit));
    } else {
      this.subscribeToSaveResponse(this.unitService.create(unit));
    }
  }

  trackFeatureById(index: number, item: IFeature): number {
    return item.id!;
  }

  getSelectedFeature(option: IFeature, selectedVals?: IFeature[]): IFeature {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUnit>>): void {
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

  protected updateForm(unit: IUnit): void {
    this.editForm.patchValue({
      id: unit.id,
      name: unit.name,
      strength: unit.strength,
      health: unit.health,
      leadership: unit.leadership,
      speed: unit.speed,
      initiative: unit.initiative,
      foodConsumption: unit.foodConsumption,
      carryingCapacity: unit.carryingCapacity,
      revivalCostAfterAnAttackGold: unit.revivalCostAfterAnAttackGold,
      revivalCostAfterDefendingSilver: unit.revivalCostAfterDefendingSilver,
      features: unit.features,
    });

    this.featuresSharedCollection = this.featureService.addFeatureToCollectionIfMissing(
      this.featuresSharedCollection,
      ...(unit.features ?? [])
    );
  }

  protected loadRelationshipsOptions(): void {
    this.featureService
      .query()
      .pipe(map((res: HttpResponse<IFeature[]>) => res.body ?? []))
      .pipe(
        map((features: IFeature[]) =>
          this.featureService.addFeatureToCollectionIfMissing(features, ...(this.editForm.get('features')!.value ?? []))
        )
      )
      .subscribe((features: IFeature[]) => (this.featuresSharedCollection = features));
  }

  protected createFromForm(): IUnit {
    return {
      ...new Unit(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      strength: this.editForm.get(['strength'])!.value,
      health: this.editForm.get(['health'])!.value,
      leadership: this.editForm.get(['leadership'])!.value,
      speed: this.editForm.get(['speed'])!.value,
      initiative: this.editForm.get(['initiative'])!.value,
      foodConsumption: this.editForm.get(['foodConsumption'])!.value,
      carryingCapacity: this.editForm.get(['carryingCapacity'])!.value,
      revivalCostAfterAnAttackGold: this.editForm.get(['revivalCostAfterAnAttackGold'])!.value,
      revivalCostAfterDefendingSilver: this.editForm.get(['revivalCostAfterDefendingSilver'])!.value,
      features: this.editForm.get(['features'])!.value,
    };
  }
}
