package com.jrhub.api.application.mapper;

import com.jrhub.api.application.dto.MovieSpaceShipsDto;
import com.jrhub.api.domain.model.MovieSpaceShip;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = {MovieSpaceShip.class, MovieSpaceShipsDto.class})
public interface SpaceShipMapper {
    SpaceShipMapper MAPPER = Mappers.getMapper(SpaceShipMapper.class);

    MovieSpaceShipsDto mapToDto(MovieSpaceShip entity);

    MovieSpaceShip mapToEntity(MovieSpaceShipsDto mallDto);

}
