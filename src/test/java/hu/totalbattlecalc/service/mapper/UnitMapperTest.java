package hu.totalbattlecalc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitMapperTest {

    private UnitMapper unitMapper;

    @BeforeEach
    public void setUp() {
        unitMapper = new UnitMapperImpl();
    }
}
