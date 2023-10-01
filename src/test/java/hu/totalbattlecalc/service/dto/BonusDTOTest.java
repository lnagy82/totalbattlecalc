package hu.totalbattlecalc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hu.totalbattlecalc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BonusDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BonusDTO.class);
        BonusDTO bonusDTO1 = new BonusDTO();
        bonusDTO1.setId(1L);
        BonusDTO bonusDTO2 = new BonusDTO();
        assertThat(bonusDTO1).isNotEqualTo(bonusDTO2);
        bonusDTO2.setId(bonusDTO1.getId());
        assertThat(bonusDTO1).isEqualTo(bonusDTO2);
        bonusDTO2.setId(2L);
        assertThat(bonusDTO1).isNotEqualTo(bonusDTO2);
        bonusDTO1.setId(null);
        assertThat(bonusDTO1).isNotEqualTo(bonusDTO2);
    }
}
