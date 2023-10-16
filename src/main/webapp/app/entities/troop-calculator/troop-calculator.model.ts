import { IBattleUnit } from '../battle-unit/battle-unit.model';

export class TroopCalculation {
  constructor(
    //
    public leadership?: number, //
    public battleUnits?: IBattleUnit[]
  ) {}
}
