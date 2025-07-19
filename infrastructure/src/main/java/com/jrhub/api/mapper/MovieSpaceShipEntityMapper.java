package com.jrhub.api.mapper;

import com.jrhub.api.model.MovieSpaceShip;
import com.jrhub.api.persistance.entities.MovieSpaceShipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieSpaceShipEntityMapper {
    
    MovieSpaceShipEntityMapper INSTANCE = Mappers.getMapper(MovieSpaceShipEntityMapper.class);
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "movie", source = "movie")
    default MovieSpaceShip toDomain(MovieSpaceShipEntity entity) {
        if (entity == null) {
            return null;
        }
        return MovieSpaceShip.fromPersistence(
                entity.getId(),
                entity.getName(),
                entity.getMovie()
        );
    }
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "movie", source = "movie")
    MovieSpaceShipEntity toEntity(MovieSpaceShip domain);
}
