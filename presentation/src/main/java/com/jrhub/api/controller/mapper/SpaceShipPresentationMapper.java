package com.jrhub.api.controller.mapper;

import com.jrhub.api.dto.MovieSpaceShipsDto;
import com.jrhub.api.mapper.SpaceShipMapper;
import com.jrhub.api.model.MovieSpaceShip;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SpaceShipPresentationMapper {

    private final SpaceShipMapper dtoMapper;

    public SpaceShipPresentationMapper(SpaceShipMapper dtoMapper) {
        this.dtoMapper = dtoMapper;
    }

    public MovieSpaceShipsDto toDto(MovieSpaceShip domain) {
        return dtoMapper.toDto(domain);
    }

    public MovieSpaceShip toDomain(MovieSpaceShipsDto dto) {
        return dtoMapper.toDomain(dto);
    }

    public List<MovieSpaceShipsDto> toDtoList(List<MovieSpaceShip> domains) {
        if (domains == null) {
            return List.of();
        }

        return domains.stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Set<MovieSpaceShipsDto> toDtoSet(Set<MovieSpaceShip> domains) {
        if (domains == null) {
            return Set.of();
        }

        return domains.stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toSet());
    }
}
