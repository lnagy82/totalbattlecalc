package hu.totalbattlecalc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleMapperTest {

    private BattleMapper battleMapper;

    @BeforeEach
    public void setUp() {
        battleMapper = new BattleMapperImpl();
    }
}
