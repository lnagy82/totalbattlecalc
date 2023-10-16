import { Injectable } from '@angular/core';
import { BattleUnit } from 'app/entities/battle-unit/battle-unit.model';
import { OptimizerService } from './optimizer.service';

@Injectable({
  providedIn: 'root',
})
export class HealthOptimizerService extends OptimizerService {

  constructor() {
    super();
  }

  public optimizeTroopsHealth(troops: Array<BattleUnit>, preferredUnits: Array<BattleUnit>, maxLeaderShip: number): void {
    do {
      this.incrementTroops(troops);
      troops.sort((o1, o2) => this.getAllHealth(o1) - this.getAllHealth(o2));
    } while (maxLeaderShip > this.sumLeadership(troops));

    do {
      this.decrementTroops(troops, preferredUnits);
      troops.sort((o1, o2) => this.getAllHealth(o1) - this.getAllHealth(o2));
    } while (!this.eqTroops(troops, preferredUnits));
  }

  getAllHealth(unit: BattleUnit): number {
    return unit.unit!.health! * unit.number * ((unit.bHealth + 100.0) / 100.0);
  }
}
