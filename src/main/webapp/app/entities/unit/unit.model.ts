import { IFeature } from 'app/entities/feature/feature.model';

export interface IUnit {
  id?: number;
  name?: string | null;
  tier?: number;
  strength?: number | null;
  health?: number | null;
  leadership?: number | null;
  speed?: number | null;
  initiative?: number | null;
  cost?: number | null;
  foodConsumption?: number | null;
  carryingCapacity?: number | null;
  revivalCostAfterAnAttackGold?: number | null;
  revivalCostAfterDefendingSilver?: number | null;
  features?: IFeature[] | null;
  imageUrl?: string;
}

export class Unit implements IUnit {
  constructor(
    public id?: number,
    public name?: string | null,
    public tier?: number,
    public strength?: number | null,
    public health?: number | null,
    public leadership?: number | null,
    public speed?: number | null,
    public initiative?: number | null,
    public cost?: number | null,
    public foodConsumption?: number | null,
    public carryingCapacity?: number | null,
    public revivalCostAfterAnAttackGold?: number | null,
    public revivalCostAfterDefendingSilver?: number | null,
    public features?: IFeature[] | null,
    public imageUrl?: string
  ) {}
}

export function getUnitIdentifier(unit: IUnit): number | undefined {
  return unit.id;
}
