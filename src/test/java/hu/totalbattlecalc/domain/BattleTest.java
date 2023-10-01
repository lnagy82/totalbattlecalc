package hu.totalbattlecalc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import hu.totalbattlecalc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BattleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Battle.class);
        Battle battle1 = new Battle();
        battle1.setId(1L);
        Battle battle2 = new Battle();
        battle2.setId(battle1.getId());
        assertThat(battle1).isEqualTo(battle2);
        battle2.setId(2L);
        assertThat(battle1).isNotEqualTo(battle2);
        battle1.setId(null);
        assertThat(battle1).isNotEqualTo(battle2);
    }
}
