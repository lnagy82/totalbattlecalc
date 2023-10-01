package hu.totalbattlecalc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BonusMapperTest {

    private BonusMapper bonusMapper;

    @BeforeEach
    public void setUp() {
        bonusMapper = new BonusMapperImpl();
    }
}
