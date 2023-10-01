package hu.totalbattlecalc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.totalbattlecalc.domain.enumeration.BonusName;
import hu.totalbattlecalc.domain.enumeration.MeasurementUnit;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Bonus.
 */
@Entity
@Table(name = "bonus")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bonus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private BonusName name;

    @Column(name = "value")
    private Double value;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private MeasurementUnit unit;

    @ManyToMany(mappedBy = "bonuses")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "unit", "bonuses" }, allowSetters = true)
    private Set<BattleUnit> battleUnits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Bonus id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BonusName getName() {
        return this.name;
    }

    public Bonus name(BonusName name) {
        this.setName(name);
        return this;
    }

    public void setName(BonusName name) {
        this.name = name;
    }

    public Double getValue() {
        return this.value;
    }

    public Bonus value(Double value) {
        this.setValue(value);
        return this;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public MeasurementUnit getUnit() {
        return this.unit;
    }

    public Bonus unit(MeasurementUnit unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }

    public Set<BattleUnit> getBattleUnits() {
        return this.battleUnits;
    }

    public void setBattleUnits(Set<BattleUnit> battleUnits) {
        if (this.battleUnits != null) {
            this.battleUnits.forEach(i -> i.removeBonus(this));
        }
        if (battleUnits != null) {
            battleUnits.forEach(i -> i.addBonus(this));
        }
        this.battleUnits = battleUnits;
    }

    public Bonus battleUnits(Set<BattleUnit> battleUnits) {
        this.setBattleUnits(battleUnits);
        return this;
    }

    public Bonus addBattleUnit(BattleUnit battleUnit) {
        this.battleUnits.add(battleUnit);
        battleUnit.getBonuses().add(this);
        return this;
    }

    public Bonus removeBattleUnit(BattleUnit battleUnit) {
        this.battleUnits.remove(battleUnit);
        battleUnit.getBonuses().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bonus)) {
            return false;
        }
        return id != null && id.equals(((Bonus) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bonus{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value=" + getValue() +
            ", unit='" + getUnit() + "'" +
            "}";
    }
}
