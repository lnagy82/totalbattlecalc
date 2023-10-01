package hu.totalbattlecalc.service.dto;

import hu.totalbattlecalc.domain.enumeration.FeatureName;
import hu.totalbattlecalc.domain.enumeration.MeasurementUnit;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link hu.totalbattlecalc.domain.Feature} entity.
 */
public class FeatureDTO implements Serializable {

    private Long id;

    private FeatureName name;

    private Double value;

    private MeasurementUnit unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeatureName getName() {
        return name;
    }

    public void setName(FeatureName name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeatureDTO)) {
            return false;
        }

        FeatureDTO featureDTO = (FeatureDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, featureDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FeatureDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value=" + getValue() +
            ", unit='" + getUnit() + "'" +
            "}";
    }
}
