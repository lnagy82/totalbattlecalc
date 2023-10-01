package hu.totalbattlecalc.service.mapper;

import hu.totalbattlecalc.domain.*;
import hu.totalbattlecalc.service.dto.UnitDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Unit} and its DTO {@link UnitDTO}.
 */
@Mapper(componentModel = "spring", uses = { FeatureMapper.class })
public interface UnitMapper extends EntityMapper<UnitDTO, Unit> {
    @Mapping(target = "features", source = "features", qualifiedByName = "idSet")
    UnitDTO toDto(Unit s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UnitDTO toDtoId(Unit unit);

    @Mapping(target = "removeFeature", ignore = true)
    Unit toEntity(UnitDTO unitDTO);
}
