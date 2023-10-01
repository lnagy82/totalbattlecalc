package hu.totalbattlecalc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeatureMapperTest {

    private FeatureMapper featureMapper;

    @BeforeEach
    public void setUp() {
        featureMapper = new FeatureMapperImpl();
    }
}
