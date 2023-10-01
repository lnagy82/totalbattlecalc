package hu.totalbattlecalc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link hu.totalbattlecalc.domain.Battle} entity.
 */
public class BattleDTO implements Serializable {

    private Long id;

    private Instant date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BattleDTO)) {
            return false;
        }

        BattleDTO battleDTO = (BattleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, battleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BattleDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
