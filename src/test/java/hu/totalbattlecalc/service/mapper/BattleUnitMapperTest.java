package hu.totalbattlecalc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleUnitMapperTest {

    private BattleUnitMapper battleUnitMapper;

    @BeforeEach
    public void setUp() {
        battleUnitMapper = new BattleUnitMapperImpl();
    }
}
