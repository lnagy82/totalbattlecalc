package hu.totalbattlecalc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Unit.
 */
@Entity
@Table(name = "unit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "strength")
    private Integer strength;

    @Column(name = "health")
    private Integer health;

    @Column(name = "leadership")
    private Integer leadership;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "initiative")
    private Integer initiative;

    @Column(name = "food_consumption")
    private Integer foodConsumption;

    @Column(name = "carrying_capacity")
    private Integer carryingCapacity;

    @Column(name = "revival_cost_after_an_attack_gold")
    private Integer revivalCostAfterAnAttackGold;

    @Column(name = "revival_cost_after_defending_silver")
    private Integer revivalCostAfterDefendingSilver;

    @ManyToMany
    @JoinTable(
        name = "rel_unit__feature",
        joinColumns = @JoinColumn(name = "unit_id"),
        inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "units" }, allowSetters = true)
    private Set<Feature> features = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Unit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Unit name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return this.strength;
    }

    public Unit strength(Integer strength) {
        this.setStrength(strength);
        return this;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getHealth() {
        return this.health;
    }

    public Unit health(Integer health) {
        this.setHealth(health);
        return this;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getLeadership() {
        return this.leadership;
    }

    public Unit leadership(Integer leadership) {
        this.setLeadership(leadership);
        return this;
    }

    public void setLeadership(Integer leadership) {
        this.leadership = leadership;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public Unit speed(Integer speed) {
        this.setSpeed(speed);
        return this;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getInitiative() {
        return this.initiative;
    }

    public Unit initiative(Integer initiative) {
        this.setInitiative(initiative);
        return this;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Integer getFoodConsumption() {
        return this.foodConsumption;
    }

    public Unit foodConsumption(Integer foodConsumption) {
        this.setFoodConsumption(foodConsumption);
        return this;
    }

    public void setFoodConsumption(Integer foodConsumption) {
        this.foodConsumption = foodConsumption;
    }

    public Integer getCarryingCapacity() {
        return this.carryingCapacity;
    }

    public Unit carryingCapacity(Integer carryingCapacity) {
        this.setCarryingCapacity(carryingCapacity);
        return this;
    }

    public void setCarryingCapacity(Integer carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public Integer getRevivalCostAfterAnAttackGold() {
        return this.revivalCostAfterAnAttackGold;
    }

    public Unit revivalCostAfterAnAttackGold(Integer revivalCostAfterAnAttackGold) {
        this.setRevivalCostAfterAnAttackGold(revivalCostAfterAnAttackGold);
        return this;
    }

    public void setRevivalCostAfterAnAttackGold(Integer revivalCostAfterAnAttackGold) {
        this.revivalCostAfterAnAttackGold = revivalCostAfterAnAttackGold;
    }

    public Integer getRevivalCostAfterDefendingSilver() {
        return this.revivalCostAfterDefendingSilver;
    }

    public Unit revivalCostAfterDefendingSilver(Integer revivalCostAfterDefendingSilver) {
        this.setRevivalCostAfterDefendingSilver(revivalCostAfterDefendingSilver);
        return this;
    }

    public void setRevivalCostAfterDefendingSilver(Integer revivalCostAfterDefendingSilver) {
        this.revivalCostAfterDefendingSilver = revivalCostAfterDefendingSilver;
    }

    public Set<Feature> getFeatures() {
        return this.features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Unit features(Set<Feature> features) {
        this.setFeatures(features);
        return this;
    }

    public Unit addFeature(Feature feature) {
        this.features.add(feature);
        feature.getUnits().add(this);
        return this;
    }

    public Unit removeFeature(Feature feature) {
        this.features.remove(feature);
        feature.getUnits().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Unit)) {
            return false;
        }
        return id != null && id.equals(((Unit) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Unit{" +
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
            "}";
    }
}
