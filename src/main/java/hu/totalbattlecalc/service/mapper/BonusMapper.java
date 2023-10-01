package hu.totalbattlecalc.service.mapper;

import hu.totalbattlecalc.domain.*;
import hu.totalbattlecalc.service.dto.BonusDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bonus} and its DTO {@link BonusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BonusMapper extends EntityMapper<BonusDTO, Bonus> {
    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<BonusDTO> toDtoIdSet(Set<Bonus> bonus);
}
