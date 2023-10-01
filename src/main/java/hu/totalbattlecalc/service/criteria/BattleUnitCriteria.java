package hu.totalbattlecalc.service.criteria;

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
 * Criteria class for the {@link hu.totalbattlecalc.domain.BattleUnit} entity. This class is used
 * in {@link hu.totalbattlecalc.web.rest.BattleUnitResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /battle-units?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BattleUnitCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter number;

    private LongFilter unitId;

    private LongFilter bonusId;

    private Boolean distinct;

    public BattleUnitCriteria() {}

    public BattleUnitCriteria(BattleUnitCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.number = other.number == null ? null : other.number.copy();
        this.unitId = other.unitId == null ? null : other.unitId.copy();
        this.bonusId = other.bonusId == null ? null : other.bonusId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BattleUnitCriteria copy() {
        return new BattleUnitCriteria(this);
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

    public IntegerFilter getNumber() {
        return number;
    }

    public IntegerFilter number() {
        if (number == null) {
            number = new IntegerFilter();
        }
        return number;
    }

    public void setNumber(IntegerFilter number) {
        this.number = number;
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

    public LongFilter getBonusId() {
        return bonusId;
    }

    public LongFilter bonusId() {
        if (bonusId == null) {
            bonusId = new LongFilter();
        }
        return bonusId;
    }

    public void setBonusId(LongFilter bonusId) {
        this.bonusId = bonusId;
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
        final BattleUnitCriteria that = (BattleUnitCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(number, that.number) &&
            Objects.equals(unitId, that.unitId) &&
            Objects.equals(bonusId, that.bonusId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, unitId, bonusId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BattleUnitCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (number != null ? "number=" + number + ", " : "") +
            (unitId != null ? "unitId=" + unitId + ", " : "") +
            (bonusId != null ? "bonusId=" + bonusId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
