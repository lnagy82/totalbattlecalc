package hu.totalbattlecalc.service.mapper;

import hu.totalbattlecalc.domain.*;
import hu.totalbattlecalc.service.dto.BattleUnitDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BattleUnit} and its DTO {@link BattleUnitDTO}.
 */
@Mapper(componentModel = "spring", uses = { UnitMapper.class, BonusMapper.class })
public interface BattleUnitMapper extends EntityMapper<BattleUnitDTO, BattleUnit> {
    @Mapping(target = "unit", source = "unit", qualifiedByName = "id")
    @Mapping(target = "bonuses", source = "bonuses", qualifiedByName = "idSet")
    BattleUnitDTO toDto(BattleUnit s);

    @Mapping(target = "removeBonus", ignore = true)
    BattleUnit toEntity(BattleUnitDTO battleUnitDTO);
}
