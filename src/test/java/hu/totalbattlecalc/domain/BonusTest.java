package hu.totalbattlecalc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import hu.totalbattlecalc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BonusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bonus.class);
        Bonus bonus1 = new Bonus();
        bonus1.setId(1L);
        Bonus bonus2 = new Bonus();
        bonus2.setId(bonus1.getId());
        assertThat(bonus1).isEqualTo(bonus2);
        bonus2.setId(2L);
        assertThat(bonus1).isNotEqualTo(bonus2);
        bonus1.setId(null);
        assertThat(bonus1).isNotEqualTo(bonus2);
    }
}
