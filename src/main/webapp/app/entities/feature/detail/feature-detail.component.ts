import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFeature } from '../feature.model';

@Component({
  selector: 'jhi-feature-detail',
  templateUrl: './feature-detail.component.html',
})
export class FeatureDetailComponent implements OnInit {
  feature: IFeature | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ feature }) => {
      this.feature = feature;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
