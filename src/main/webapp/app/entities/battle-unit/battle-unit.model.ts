import { IUnit } from 'app/entities/unit/unit.model';
import { IBonus } from 'app/entities/bonus/bonus.model';

export interface IBattleUnit {
  id: number;
  number: number;
  bStrength: number;
  bHealth: number;
  unit?: IUnit | null;
  bonuses?: IBonus[] | null;

  getAllCost: () => number;
}

export class BattleUnit implements IBattleUnit {
  constructor(public id: number, public number: number, public bStrength: number, public bHealth: number, public unit?: IUnit | null, public bonuses?: IBonus[] | null) {}

  getAllCost(): number {
    return this.unit!.cost! * this.number;
  }
}

export function getBattleUnitIdentifier(battleUnit: IBattleUnit): number | undefined {
  return battleUnit.id;
}
