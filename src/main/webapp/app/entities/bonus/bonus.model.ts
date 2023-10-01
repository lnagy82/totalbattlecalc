import { IBattleUnit } from 'app/entities/battle-unit/battle-unit.model';
import { BonusName } from 'app/entities/enumerations/bonus-name.model';
import { MeasurementUnit } from 'app/entities/enumerations/measurement-unit.model';

export interface IBonus {
  id?: number;
  name?: BonusName | null;
  value?: number | null;
  unit?: MeasurementUnit | null;
  battleUnits?: IBattleUnit[] | null;
}

export class Bonus implements IBonus {
  constructor(
    public id?: number,
    public name?: BonusName | null,
    public value?: number | null,
    public unit?: MeasurementUnit | null,
    public battleUnits?: IBattleUnit[] | null
  ) {}
}

export function getBonusIdentifier(bonus: IBonus): number | undefined {
  return bonus.id;
}
