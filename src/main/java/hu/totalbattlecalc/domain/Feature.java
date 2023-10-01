package hu.totalbattlecalc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.totalbattlecalc.domain.enumeration.FeatureName;
import hu.totalbattlecalc.domain.enumeration.MeasurementUnit;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Feature.
 */
@Entity
@Table(name = "feature")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Feature implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private FeatureName name;

    @Column(name = "value")
    private Double value;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private MeasurementUnit unit;

    @ManyToMany(mappedBy = "features")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "features" }, allowSetters = true)
    private Set<Unit> units = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Feature id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeatureName getName() {
        return this.name;
    }

    public Feature name(FeatureName name) {
        this.setName(name);
        return this;
    }

    public void setName(FeatureName name) {
        this.name = name;
    }

    public Double getValue() {
        return this.value;
    }

    public Feature value(Double value) {
        this.setValue(value);
        return this;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public MeasurementUnit getUnit() {
        return this.unit;
    }

    public Feature unit(MeasurementUnit unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }

    public Set<Unit> getUnits() {
        return this.units;
    }

    public void setUnits(Set<Unit> units) {
        if (this.units != null) {
            this.units.forEach(i -> i.removeFeature(this));
        }
        if (units != null) {
            units.forEach(i -> i.addFeature(this));
        }
        this.units = units;
    }

    public Feature units(Set<Unit> units) {
        this.setUnits(units);
        return this;
    }

    public Feature addUnit(Unit unit) {
        this.units.add(unit);
        unit.getFeatures().add(this);
        return this;
    }

    public Feature removeUnit(Unit unit) {
        this.units.remove(unit);
        unit.getFeatures().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Feature)) {
            return false;
        }
        return id != null && id.equals(((Feature) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Feature{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value=" + getValue() +
            ", unit='" + getUnit() + "'" +
            "}";
    }
}
