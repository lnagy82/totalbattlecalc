import { IBattleUnit } from '../battle-unit/battle-unit.model';

export class TroopCalculation {
  constructor(
    //
    public leadership?: number, //
    public strength?: number, //
    public health?: number, //
    public battleUnits?: IBattleUnit[]
  ) {}
}
