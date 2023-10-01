import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBonus } from '../bonus.model';

@Component({
  selector: 'jhi-bonus-detail',
  templateUrl: './bonus-detail.component.html',
})
export class BonusDetailComponent implements OnInit {
  bonus: IBonus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bonus }) => {
      this.bonus = bonus;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
