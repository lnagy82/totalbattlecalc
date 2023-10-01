package hu.totalbattlecalc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import hu.totalbattlecalc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BattleUnitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BattleUnit.class);
        BattleUnit battleUnit1 = new BattleUnit();
        battleUnit1.setId(1L);
        BattleUnit battleUnit2 = new BattleUnit();
        battleUnit2.setId(battleUnit1.getId());
        assertThat(battleUnit1).isEqualTo(battleUnit2);
        battleUnit2.setId(2L);
        assertThat(battleUnit1).isNotEqualTo(battleUnit2);
        battleUnit1.setId(null);
        assertThat(battleUnit1).isNotEqualTo(battleUnit2);
    }
}
