import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IBattleUnit, BattleUnit } from '../battle-unit.model';
import { BattleUnitService } from '../service/battle-unit.service';
import { IUnit } from 'app/entities/unit/unit.model';
import { UnitService } from 'app/entities/unit/service/unit.service';
import { IBonus } from 'app/entities/bonus/bonus.model';
import { BonusService } from 'app/entities/bonus/service/bonus.service';

@Component({
  selector: 'jhi-battle-unit-update',
  templateUrl: './battle-unit-update.component.html',
})
export class BattleUnitUpdateComponent implements OnInit {
  isSaving = false;

  unitsSharedCollection: IUnit[] = [];
  bonusesSharedCollection: IBonus[] = [];

  editForm = this.fb.group({
    id: [],
    number: [],
    unit: [],
    bonuses: [],
  });

  constructor(
    protected battleUnitService: BattleUnitService,
    protected unitService: UnitService,
    protected bonusService: BonusService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ battleUnit }) => {
      this.updateForm(battleUnit);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const battleUnit = this.createFromForm();
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-condition
    if (battleUnit.id !== undefined) {
      this.subscribeToSaveResponse(this.battleUnitService.update(battleUnit));
    } else {
      this.subscribeToSaveResponse(this.battleUnitService.create(battleUnit));
    }
  }

  trackUnitById(index: number, item: IUnit): number {
    return item.id!;
  }

  trackBonusById(index: number, item: IBonus): number {
    return item.id!;
  }

  getSelectedBonus(option: IBonus, selectedVals?: IBonus[]): IBonus {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBattleUnit>>): void {
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

  protected updateForm(battleUnit: IBattleUnit): void {
    this.editForm.patchValue({
      id: battleUnit.id,
      number: battleUnit.number,
      unit: battleUnit.unit,
      bonuses: battleUnit.bonuses,
    });

    this.unitsSharedCollection = this.unitService.addUnitToCollectionIfMissing(this.unitsSharedCollection, battleUnit.unit);
    this.bonusesSharedCollection = this.bonusService.addBonusToCollectionIfMissing(
      this.bonusesSharedCollection,
      ...(battleUnit.bonuses ?? [])
    );
  }

  protected loadRelationshipsOptions(): void {
    this.unitService
      .query()
      .pipe(map((res: HttpResponse<IUnit[]>) => res.body ?? []))
      .pipe(map((units: IUnit[]) => this.unitService.addUnitToCollectionIfMissing(units, this.editForm.get('unit')!.value)))
      .subscribe((units: IUnit[]) => (this.unitsSharedCollection = units));

    this.bonusService
      .query()
      .pipe(map((res: HttpResponse<IBonus[]>) => res.body ?? []))
      .pipe(
        map((bonuses: IBonus[]) => this.bonusService.addBonusToCollectionIfMissing(bonuses, ...(this.editForm.get('bonuses')!.value ?? [])))
      )
      .subscribe((bonuses: IBonus[]) => (this.bonusesSharedCollection = bonuses));
  }

  protected createFromForm(): IBattleUnit {
    const battleUnit = new BattleUnit(
      this.editForm.get(['id'])!.value,
      this.editForm.get(['number'])!.value,
      this.editForm.get(['unit'])!.value,
      this.editForm.get(['bonuses'])!.value
    );
    return battleUnit;
  }
}
