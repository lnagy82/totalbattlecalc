import { IUnit } from 'app/entities/unit/unit.model';
import { IBonus } from 'app/entities/bonus/bonus.model';

export interface IBattleUnit {
  id?: number;
  number?: number | null;
  unit?: IUnit | null;
  bonuses?: IBonus[] | null;
}

export class BattleUnit implements IBattleUnit {
  constructor(public id?: number, public number?: number | null, public unit?: IUnit | null, public bonuses?: IBonus[] | null) {}
}

export function getBattleUnitIdentifier(battleUnit: IBattleUnit): number | undefined {
  return battleUnit.id;
}
