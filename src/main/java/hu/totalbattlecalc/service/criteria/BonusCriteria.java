package hu.totalbattlecalc.service.criteria;

import hu.totalbattlecalc.domain.enumeration.BonusName;
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
 * Criteria class for the {@link hu.totalbattlecalc.domain.Bonus} entity. This class is used
 * in {@link hu.totalbattlecalc.web.rest.BonusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bonuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BonusCriteria implements Serializable, Criteria {

    /**
     * Class for filtering BonusName
     */
    public static class BonusNameFilter extends Filter<BonusName> {

        public BonusNameFilter() {}

        public BonusNameFilter(BonusNameFilter filter) {
            super(filter);
        }

        @Override
        public BonusNameFilter copy() {
            return new BonusNameFilter(this);
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

    private BonusNameFilter name;

    private DoubleFilter value;

    private MeasurementUnitFilter unit;

    private LongFilter battleUnitId;

    private Boolean distinct;

    public BonusCriteria() {}

    public BonusCriteria(BonusCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.unit = other.unit == null ? null : other.unit.copy();
        this.battleUnitId = other.battleUnitId == null ? null : other.battleUnitId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BonusCriteria copy() {
        return new BonusCriteria(this);
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

    public BonusNameFilter getName() {
        return name;
    }

    public BonusNameFilter name() {
        if (name == null) {
            name = new BonusNameFilter();
        }
        return name;
    }

    public void setName(BonusNameFilter name) {
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

    public LongFilter getBattleUnitId() {
        return battleUnitId;
    }

    public LongFilter battleUnitId() {
        if (battleUnitId == null) {
            battleUnitId = new LongFilter();
        }
        return battleUnitId;
    }

    public void setBattleUnitId(LongFilter battleUnitId) {
        this.battleUnitId = battleUnitId;
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
        final BonusCriteria that = (BonusCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(value, that.value) &&
            Objects.equals(unit, that.unit) &&
            Objects.equals(battleUnitId, that.battleUnitId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, unit, battleUnitId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BonusCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (value != null ? "value=" + value + ", " : "") +
            (unit != null ? "unit=" + unit + ", " : "") +
            (battleUnitId != null ? "battleUnitId=" + battleUnitId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
