package hu.totalbattlecalc.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link hu.totalbattlecalc.domain.BattleUnit} entity.
 */
public class BattleUnitDTO implements Serializable {

    private Long id;

    private Integer number;

    private UnitDTO unit;

    private Set<BonusDTO> bonuses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UnitDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitDTO unit) {
        this.unit = unit;
    }

    public Set<BonusDTO> getBonuses() {
        return bonuses;
    }

    public void setBonuses(Set<BonusDTO> bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BattleUnitDTO)) {
            return false;
        }

        BattleUnitDTO battleUnitDTO = (BattleUnitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, battleUnitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BattleUnitDTO{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            ", unit=" + getUnit() +
            ", bonuses=" + getBonuses() +
            "}";
    }
}
