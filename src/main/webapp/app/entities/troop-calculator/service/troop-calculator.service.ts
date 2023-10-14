import { PreferredUnitFactoryService } from './preferred-unit-factory.service';
import { Injectable } from '@angular/core';

import { BattleUnit } from 'app/entities/battle-unit/battle-unit.model';
import { HealthOptimizerService } from './health-optimizer.service';

@Injectable({
  providedIn: 'root',
})
export class TroopCalculatorService {
  constructor(
    protected healthOptimizerService: HealthOptimizerService,
    protected preferredUnitFactoryService: PreferredUnitFactoryService
  ) {}

  getTroops(troops: Array<BattleUnit>, leadership: number): Array<BattleUnit> {
    const preferredUnits = this.preferredUnitFactoryService.initializePreferredUnits(troops);
    this.healthOptimizerService.optimizeTroopsHealth(troops, preferredUnits, leadership);
    return troops;
  }
}
