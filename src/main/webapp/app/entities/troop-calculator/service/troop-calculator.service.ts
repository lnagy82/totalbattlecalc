import { PreferredUnitFactoryService } from './preferred-unit-factory.service';
import { Injectable } from '@angular/core';

import { BattleUnit } from 'app/entities/battle-unit/battle-unit.model';
import { HealthOptimizerService } from './health-optimizer.service';
import { TroopCalculation } from '../troop-calculator.model';

@Injectable({
  providedIn: 'root',
})
export class TroopCalculatorService {
  constructor(
    protected healthOptimizerService: HealthOptimizerService,
    protected preferredUnitFactoryService: PreferredUnitFactoryService
  ) {}

  getTroopsCalculation(troops: Array<BattleUnit>, leadership: number): TroopCalculation {
    const preferredUnits = this.preferredUnitFactoryService.initializePreferredUnits(troops);
    this.healthOptimizerService.optimizeTroopsHealth(troops, preferredUnits, leadership);
    return new TroopCalculation(this.healthOptimizerService.sumLeadership(troops), troops);
  }
}
