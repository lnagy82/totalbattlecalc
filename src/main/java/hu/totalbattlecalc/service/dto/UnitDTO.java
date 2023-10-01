package hu.totalbattlecalc.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link hu.totalbattlecalc.domain.Unit} entity.
 */
public class UnitDTO implements Serializable {

    private Long id;

    private String name;

    private Integer strength;

    private Integer health;

    private Integer leadership;

    private Integer speed;

    private Integer initiative;

    private Integer foodConsumption;

    private Integer carryingCapacity;

    private Integer revivalCostAfterAnAttackGold;

    private Integer revivalCostAfterDefendingSilver;

    private Set<FeatureDTO> features = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getLeadership() {
        return leadership;
    }

    public void setLeadership(Integer leadership) {
        this.leadership = leadership;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Integer getFoodConsumption() {
        return foodConsumption;
    }

    public void setFoodConsumption(Integer foodConsumption) {
        this.foodConsumption = foodConsumption;
    }

    public Integer getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(Integer carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public Integer getRevivalCostAfterAnAttackGold() {
        return revivalCostAfterAnAttackGold;
    }

    public void setRevivalCostAfterAnAttackGold(Integer revivalCostAfterAnAttackGold) {
        this.revivalCostAfterAnAttackGold = revivalCostAfterAnAttackGold;
    }

    public Integer getRevivalCostAfterDefendingSilver() {
        return revivalCostAfterDefendingSilver;
    }

    public void setRevivalCostAfterDefendingSilver(Integer revivalCostAfterDefendingSilver) {
        this.revivalCostAfterDefendingSilver = revivalCostAfterDefendingSilver;
    }

    public Set<FeatureDTO> getFeatures() {
        return features;
    }

    public void setFeatures(Set<FeatureDTO> features) {
        this.features = features;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitDTO)) {
            return false;
        }

        UnitDTO unitDTO = (UnitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, unitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnitDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", strength=" + getStrength() +
            ", health=" + getHealth() +
            ", leadership=" + getLeadership() +
            ", speed=" + getSpeed() +
            ", initiative=" + getInitiative() +
            ", foodConsumption=" + getFoodConsumption() +
            ", carryingCapacity=" + getCarryingCapacity() +
            ", revivalCostAfterAnAttackGold=" + getRevivalCostAfterAnAttackGold() +
            ", revivalCostAfterDefendingSilver=" + getRevivalCostAfterDefendingSilver() +
            ", features=" + getFeatures() +
            "}";
    }
}
