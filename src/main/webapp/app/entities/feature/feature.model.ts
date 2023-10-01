import { IUnit } from 'app/entities/unit/unit.model';
import { FeatureName } from 'app/entities/enumerations/feature-name.model';
import { MeasurementUnit } from 'app/entities/enumerations/measurement-unit.model';

export interface IFeature {
  id?: number;
  name?: FeatureName | null;
  value?: number | null;
  unit?: MeasurementUnit | null;
  units?: IUnit[] | null;
}

export class Feature implements IFeature {
  constructor(
    public id?: number,
    public name?: FeatureName | null,
    public value?: number | null,
    public unit?: MeasurementUnit | null,
    public units?: IUnit[] | null
  ) {}
}

export function getFeatureIdentifier(feature: IFeature): number | undefined {
  return feature.id;
}
