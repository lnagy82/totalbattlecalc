package hu.totalbattlecalc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BattleUnit.
 */
@Entity
@Table(name = "battle_unit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BattleUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JsonIgnoreProperties(value = { "features" }, allowSetters = true)
    private Unit unit;

    @ManyToMany
    @JoinTable(
        name = "rel_battle_unit__bonus",
        joinColumns = @JoinColumn(name = "battle_unit_id"),
        inverseJoinColumns = @JoinColumn(name = "bonus_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "battleUnits" }, allowSetters = true)
    private Set<Bonus> bonuses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BattleUnit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return this.number;
    }

    public BattleUnit number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public BattleUnit unit(Unit unit) {
        this.setUnit(unit);
        return this;
    }

    public Set<Bonus> getBonuses() {
        return this.bonuses;
    }

    public void setBonuses(Set<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    public BattleUnit bonuses(Set<Bonus> bonuses) {
        this.setBonuses(bonuses);
        return this;
    }

    public BattleUnit addBonus(Bonus bonus) {
        this.bonuses.add(bonus);
        bonus.getBattleUnits().add(this);
        return this;
    }

    public BattleUnit removeBonus(Bonus bonus) {
        this.bonuses.remove(bonus);
        bonus.getBattleUnits().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BattleUnit)) {
            return false;
        }
        return id != null && id.equals(((BattleUnit) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BattleUnit{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            "}";
    }
}
