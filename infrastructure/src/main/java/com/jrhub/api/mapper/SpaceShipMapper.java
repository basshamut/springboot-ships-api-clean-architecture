package com.jrhub.api.mapper;

import com.jrhub.api.dto.MovieSpaceShipsDto;
import com.jrhub.api.model.MovieSpaceShip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = {MovieSpaceShip.class, MovieSpaceShipsDto.class})
public interface SpaceShipMapper {
    SpaceShipMapper INSTANCE = Mappers.getMapper(SpaceShipMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "movie", source = "movie")
    MovieSpaceShipsDto toDto(MovieSpaceShip domain);

    default MovieSpaceShip toDomain(MovieSpaceShipsDto dto) {
        if (dto == null) {
            return null;
        }

        if (dto.id() != null) {
            return MovieSpaceShip.fromPersistence(
                    dto.id(),
                    dto.name(),
                    dto.movie()
            );
        }

        return MovieSpaceShip.create(
                dto.name(),
                dto.movie()
        );
    }
}
