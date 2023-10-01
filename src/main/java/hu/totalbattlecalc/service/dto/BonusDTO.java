package hu.totalbattlecalc.service.dto;

import hu.totalbattlecalc.domain.enumeration.BonusName;
import hu.totalbattlecalc.domain.enumeration.MeasurementUnit;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link hu.totalbattlecalc.domain.Bonus} entity.
 */
public class BonusDTO implements Serializable {

    private Long id;

    private BonusName name;

    private Double value;

    private MeasurementUnit unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BonusName getName() {
        return name;
    }

    public void setName(BonusName name) {
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
        if (!(o instanceof BonusDTO)) {
            return false;
        }

        BonusDTO bonusDTO = (BonusDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bonusDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BonusDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value=" + getValue() +
            ", unit='" + getUnit() + "'" +
            "}";
    }
}
