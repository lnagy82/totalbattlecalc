package hu.totalbattlecalc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hu.totalbattlecalc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BattleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BattleDTO.class);
        BattleDTO battleDTO1 = new BattleDTO();
        battleDTO1.setId(1L);
        BattleDTO battleDTO2 = new BattleDTO();
        assertThat(battleDTO1).isNotEqualTo(battleDTO2);
        battleDTO2.setId(battleDTO1.getId());
        assertThat(battleDTO1).isEqualTo(battleDTO2);
        battleDTO2.setId(2L);
        assertThat(battleDTO1).isNotEqualTo(battleDTO2);
        battleDTO1.setId(null);
        assertThat(battleDTO1).isNotEqualTo(battleDTO2);
    }
}
