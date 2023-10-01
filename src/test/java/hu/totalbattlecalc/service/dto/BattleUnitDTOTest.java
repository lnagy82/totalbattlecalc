package hu.totalbattlecalc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hu.totalbattlecalc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BattleUnitDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BattleUnitDTO.class);
        BattleUnitDTO battleUnitDTO1 = new BattleUnitDTO();
        battleUnitDTO1.setId(1L);
        BattleUnitDTO battleUnitDTO2 = new BattleUnitDTO();
        assertThat(battleUnitDTO1).isNotEqualTo(battleUnitDTO2);
        battleUnitDTO2.setId(battleUnitDTO1.getId());
        assertThat(battleUnitDTO1).isEqualTo(battleUnitDTO2);
        battleUnitDTO2.setId(2L);
        assertThat(battleUnitDTO1).isNotEqualTo(battleUnitDTO2);
        battleUnitDTO1.setId(null);
        assertThat(battleUnitDTO1).isNotEqualTo(battleUnitDTO2);
    }
}
