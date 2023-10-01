package hu.totalbattlecalc.service.criteria;

import hu.totalbattlecalc.domain.enumeration.FeatureName;
import hu.totalbattlecalc.domain.enumeration.MeasurementUnit;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link hu.totalbattlecalc.domain.Feature} entity. This class is used
 * in {@link hu.totalbattlecalc.web.rest.FeatureResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /features?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FeatureCriteria implements Serializable, Criteria {

    /**
     * Class for filtering FeatureName
     */
    public static class FeatureNameFilter extends Filter<FeatureName> {

        public FeatureNameFilter() {}

        public FeatureNameFilter(FeatureNameFilter filter) {
            super(filter);
        }

        @Override
        public FeatureNameFilter copy() {
            return new FeatureNameFilter(this);
        }
    }

    /**
     * Class for filtering MeasurementUnit
     */
    public static class MeasurementUnitFilter extends Filter<MeasurementUnit> {

        public MeasurementUnitFilter() {}

        public MeasurementUnitFilter(MeasurementUnitFilter filter) {
            super(filter);
        }

        @Override
        public MeasurementUnitFilter copy() {
            return new MeasurementUnitFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FeatureNameFilter name;

    private DoubleFilter value;

    private MeasurementUnitFilter unit;

    private LongFilter unitId;

    private Boolean distinct;

    public FeatureCriteria() {}

    public FeatureCriteria(FeatureCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.unit = other.unit == null ? null : other.unit.copy();
        this.unitId = other.unitId == null ? null : other.unitId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public FeatureCriteria copy() {
        return new FeatureCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FeatureNameFilter getName() {
        return name;
    }

    public FeatureNameFilter name() {
        if (name == null) {
            name = new FeatureNameFilter();
        }
        return name;
    }

    public void setName(FeatureNameFilter name) {
        this.name = name;
    }

    public DoubleFilter getValue() {
        return value;
    }

    public DoubleFilter value() {
        if (value == null) {
            value = new DoubleFilter();
        }
        return value;
    }

    public void setValue(DoubleFilter value) {
        this.value = value;
    }

    public MeasurementUnitFilter getUnit() {
        return unit;
    }

    public MeasurementUnitFilter unit() {
        if (unit == null) {
            unit = new MeasurementUnitFilter();
        }
        return unit;
    }

    public void setUnit(MeasurementUnitFilter unit) {
        this.unit = unit;
    }

    public LongFilter getUnitId() {
        return unitId;
    }

    public LongFilter unitId() {
        if (unitId == null) {
            unitId = new LongFilter();
        }
        return unitId;
    }

    public void setUnitId(LongFilter unitId) {
        this.unitId = unitId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FeatureCriteria that = (FeatureCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(value, that.value) &&
            Objects.equals(unit, that.unit) &&
            Objects.equals(unitId, that.unitId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, unit, unitId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FeatureCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (value != null ? "value=" + value + ", " : "") +
            (unit != null ? "unit=" + unit + ", " : "") +
            (unitId != null ? "unitId=" + unitId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
