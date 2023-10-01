import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBattle } from '../battle.model';

@Component({
  selector: 'jhi-battle-detail',
  templateUrl: './battle-detail.component.html',
})
export class BattleDetailComponent implements OnInit {
  battle: IBattle | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ battle }) => {
      this.battle = battle;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
