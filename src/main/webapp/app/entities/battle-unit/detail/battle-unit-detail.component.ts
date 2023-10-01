import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBattleUnit } from '../battle-unit.model';

@Component({
  selector: 'jhi-battle-unit-detail',
  templateUrl: './battle-unit-detail.component.html',
})
export class BattleUnitDetailComponent implements OnInit {
  battleUnit: IBattleUnit | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ battleUnit }) => {
      this.battleUnit = battleUnit;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
