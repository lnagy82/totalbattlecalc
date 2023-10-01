import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IBattle, Battle } from '../battle.model';
import { BattleService } from '../service/battle.service';

@Component({
  selector: 'jhi-battle-update',
  templateUrl: './battle-update.component.html',
})
export class BattleUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    date: [],
  });

  constructor(protected battleService: BattleService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ battle }) => {
      if (battle.id === undefined) {
        const today = dayjs().startOf('day');
        battle.date = today;
      }

      this.updateForm(battle);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const battle = this.createFromForm();
    if (battle.id !== undefined) {
      this.subscribeToSaveResponse(this.battleService.update(battle));
    } else {
      this.subscribeToSaveResponse(this.battleService.create(battle));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBattle>>): void {
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

  protected updateForm(battle: IBattle): void {
    this.editForm.patchValue({
      id: battle.id,
      date: battle.date ? battle.date.format(DATE_TIME_FORMAT) : null,
    });
  }

  protected createFromForm(): IBattle {
    return {
      ...new Battle(),
      id: this.editForm.get(['id'])!.value,
      date: this.editForm.get(['date'])!.value ? dayjs(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }
}
