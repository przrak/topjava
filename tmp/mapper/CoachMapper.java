package space.pirs.modules.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import space.pirs.BaseMapper;
import space.pirs.modules.user.model.User;
import space.pirs.modules.user.to.CoachTo;

@Mapper(componentModel = "spring")
public abstract class CoachMapper implements BaseMapper<User, CoachTo> {
    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "name", source = "user.name"),
            @Mapping(target = "teams", ignore = true),
            @Mapping(target = "registered",  expression = "java(user.getStartpoint().toLocalDate())") // maybe we need another date here?
    })

    @Override
    public abstract CoachTo toTo(User user);
}