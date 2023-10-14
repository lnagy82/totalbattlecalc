import { Injectable } from '@angular/core';
import { BattleUnit } from 'app/entities/battle-unit/battle-unit.model';

@Injectable({
  providedIn: 'root',
})
export class OptimizerService {
  constructor() {
    //
  }

  incrementTroops(troops: Array<BattleUnit>): void {
    troops[0].number = troops[0].number + 1;
  }

  decrementTroops(troops: Array<BattleUnit>, preferredUnits: Array<BattleUnit>): void {
    for (let i = 0; i < troops.length; i++) {
      if (troops[i] !== preferredUnits[i]) {
        preferredUnits[i].number = preferredUnits[i].number - 1;
        break;
      }
    }
  }

  eqTroops(t1: Array<BattleUnit>, t2: Array<BattleUnit>): boolean {
    for (let i = 0; i < t1.length; i++) {
      if (t1[i] !== t2[i]) {
        return false;
      }
    }
    return true;
  }

  sumLeadership(troops: Array<BattleUnit>): number {
    let leadership = 0;

    troops.forEach(unit => {
      leadership += unit.unit!.leadership! * unit.number;
    });

    return leadership;
  }

  sumCost(troops: Array<BattleUnit>): number {
    let cost = 0;
    troops.forEach(unit => {
      cost += unit.getAllCost();
    });

    return cost;
  }

  sumStrength(troops: Array<BattleUnit>): number {
    let strength = 0;

    troops.forEach(unit => {
      strength += this.getAllStrength(unit);
    });
    return strength;
  }

  getAllStrength(unit: BattleUnit): number {
    return unit.unit!.strength! * unit.number * ((unit.bStrength + 100.0) / 100.0);
  }
}
