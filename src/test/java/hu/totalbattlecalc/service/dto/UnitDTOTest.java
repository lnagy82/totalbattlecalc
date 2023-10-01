package hu.totalbattlecalc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import hu.totalbattlecalc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UnitDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnitDTO.class);
        UnitDTO unitDTO1 = new UnitDTO();
        unitDTO1.setId(1L);
        UnitDTO unitDTO2 = new UnitDTO();
        assertThat(unitDTO1).isNotEqualTo(unitDTO2);
        unitDTO2.setId(unitDTO1.getId());
        assertThat(unitDTO1).isEqualTo(unitDTO2);
        unitDTO2.setId(2L);
        assertThat(unitDTO1).isNotEqualTo(unitDTO2);
        unitDTO1.setId(null);
        assertThat(unitDTO1).isNotEqualTo(unitDTO2);
    }
}
