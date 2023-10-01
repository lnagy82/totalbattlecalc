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
 * Criteria class for the {@link hu.totalbattlecalc.domain.Unit} entity. This class is used
 * in {@link hu.totalbattlecalc.web.rest.UnitResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /units?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UnitCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private IntegerFilter strength;

    private IntegerFilter health;

    private IntegerFilter leadership;

    private IntegerFilter speed;

    private IntegerFilter initiative;

    private IntegerFilter foodConsumption;

    private IntegerFilter carryingCapacity;

    private IntegerFilter revivalCostAfterAnAttackGold;

    private IntegerFilter revivalCostAfterDefendingSilver;

    private LongFilter featureId;

    private Boolean distinct;

    public UnitCriteria() {}

    public UnitCriteria(UnitCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.strength = other.strength == null ? null : other.strength.copy();
        this.health = other.health == null ? null : other.health.copy();
        this.leadership = other.leadership == null ? null : other.leadership.copy();
        this.speed = other.speed == null ? null : other.speed.copy();
        this.initiative = other.initiative == null ? null : other.initiative.copy();
        this.foodConsumption = other.foodConsumption == null ? null : other.foodConsumption.copy();
        this.carryingCapacity = other.carryingCapacity == null ? null : other.carryingCapacity.copy();
        this.revivalCostAfterAnAttackGold = other.revivalCostAfterAnAttackGold == null ? null : other.revivalCostAfterAnAttackGold.copy();
        this.revivalCostAfterDefendingSilver =
            other.revivalCostAfterDefendingSilver == null ? null : other.revivalCostAfterDefendingSilver.copy();
        this.featureId = other.featureId == null ? null : other.featureId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public UnitCriteria copy() {
        return new UnitCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public IntegerFilter getStrength() {
        return strength;
    }

    public IntegerFilter strength() {
        if (strength == null) {
            strength = new IntegerFilter();
        }
        return strength;
    }

    public void setStrength(IntegerFilter strength) {
        this.strength = strength;
    }

    public IntegerFilter getHealth() {
        return health;
    }

    public IntegerFilter health() {
        if (health == null) {
            health = new IntegerFilter();
        }
        return health;
    }

    public void setHealth(IntegerFilter health) {
        this.health = health;
    }

    public IntegerFilter getLeadership() {
        return leadership;
    }

    public IntegerFilter leadership() {
        if (leadership == null) {
            leadership = new IntegerFilter();
        }
        return leadership;
    }

    public void setLeadership(IntegerFilter leadership) {
        this.leadership = leadership;
    }

    public IntegerFilter getSpeed() {
        return speed;
    }

    public IntegerFilter speed() {
        if (speed == null) {
            speed = new IntegerFilter();
        }
        return speed;
    }

    public void setSpeed(IntegerFilter speed) {
        this.speed = speed;
    }

    public IntegerFilter getInitiative() {
        return initiative;
    }

    public IntegerFilter initiative() {
        if (initiative == null) {
            initiative = new IntegerFilter();
        }
        return initiative;
    }

    public void setInitiative(IntegerFilter initiative) {
        this.initiative = initiative;
    }

    public IntegerFilter getFoodConsumption() {
        return foodConsumption;
    }

    public IntegerFilter foodConsumption() {
        if (foodConsumption == null) {
            foodConsumption = new IntegerFilter();
        }
        return foodConsumption;
    }

    public void setFoodConsumption(IntegerFilter foodConsumption) {
        this.foodConsumption = foodConsumption;
    }

    public IntegerFilter getCarryingCapacity() {
        return carryingCapacity;
    }

    public IntegerFilter carryingCapacity() {
        if (carryingCapacity == null) {
            carryingCapacity = new IntegerFilter();
        }
        return carryingCapacity;
    }

    public void setCarryingCapacity(IntegerFilter carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public IntegerFilter getRevivalCostAfterAnAttackGold() {
        return revivalCostAfterAnAttackGold;
    }

    public IntegerFilter revivalCostAfterAnAttackGold() {
        if (revivalCostAfterAnAttackGold == null) {
            revivalCostAfterAnAttackGold = new IntegerFilter();
        }
        return revivalCostAfterAnAttackGold;
    }

    public void setRevivalCostAfterAnAttackGold(IntegerFilter revivalCostAfterAnAttackGold) {
        this.revivalCostAfterAnAttackGold = revivalCostAfterAnAttackGold;
    }

    public IntegerFilter getRevivalCostAfterDefendingSilver() {
        return revivalCostAfterDefendingSilver;
    }

    public IntegerFilter revivalCostAfterDefendingSilver() {
        if (revivalCostAfterDefendingSilver == null) {
            revivalCostAfterDefendingSilver = new IntegerFilter();
        }
        return revivalCostAfterDefendingSilver;
    }

    public void setRevivalCostAfterDefendingSilver(IntegerFilter revivalCostAfterDefendingSilver) {
        this.revivalCostAfterDefendingSilver = revivalCostAfterDefendingSilver;
    }

    public LongFilter getFeatureId() {
        return featureId;
    }

    public LongFilter featureId() {
        if (featureId == null) {
            featureId = new LongFilter();
        }
        return featureId;
    }

    public void setFeatureId(LongFilter featureId) {
        this.featureId = featureId;
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
        final UnitCriteria that = (UnitCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(strength, that.strength) &&
            Objects.equals(health, that.health) &&
            Objects.equals(leadership, that.leadership) &&
            Objects.equals(speed, that.speed) &&
            Objects.equals(initiative, that.initiative) &&
            Objects.equals(foodConsumption, that.foodConsumption) &&
            Objects.equals(carryingCapacity, that.carryingCapacity) &&
            Objects.equals(revivalCostAfterAnAttackGold, that.revivalCostAfterAnAttackGold) &&
            Objects.equals(revivalCostAfterDefendingSilver, that.revivalCostAfterDefendingSilver) &&
            Objects.equals(featureId, that.featureId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            strength,
            health,
            leadership,
            speed,
            initiative,
            foodConsumption,
            carryingCapacity,
            revivalCostAfterAnAttackGold,
            revivalCostAfterDefendingSilver,
            featureId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnitCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (strength != null ? "strength=" + strength + ", " : "") +
            (health != null ? "health=" + health + ", " : "") +
            (leadership != null ? "leadership=" + leadership + ", " : "") +
            (speed != null ? "speed=" + speed + ", " : "") +
            (initiative != null ? "initiative=" + initiative + ", " : "") +
            (foodConsumption != null ? "foodConsumption=" + foodConsumption + ", " : "") +
            (carryingCapacity != null ? "carryingCapacity=" + carryingCapacity + ", " : "") +
            (revivalCostAfterAnAttackGold != null ? "revivalCostAfterAnAttackGold=" + revivalCostAfterAnAttackGold + ", " : "") +
            (revivalCostAfterDefendingSilver != null ? "revivalCostAfterDefendingSilver=" + revivalCostAfterDefendingSilver + ", " : "") +
            (featureId != null ? "featureId=" + featureId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
