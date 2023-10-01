package hu.totalbattlecalc.service.mapper;

import hu.totalbattlecalc.domain.*;
import hu.totalbattlecalc.service.dto.BattleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Battle} and its DTO {@link BattleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BattleMapper extends EntityMapper<BattleDTO, Battle> {}
