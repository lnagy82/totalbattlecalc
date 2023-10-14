import { Injectable } from '@angular/core';
import { BattleUnit } from 'app/entities/battle-unit/battle-unit.model';

@Injectable({
  providedIn: 'root',
})
export class PreferredUnitFactoryService {
  constructor() {
    //
  }

  initializePreferredUnits(troops: Array<BattleUnit>): Array<BattleUnit> {
    const preferredUnits = [...troops];
    return preferredUnits.sort((o1, o2) => o2.unit!.strength! - o1.unit!.strength!);
  }
}
