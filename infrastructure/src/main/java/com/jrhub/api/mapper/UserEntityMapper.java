package com.jrhub.api.mapper;

import com.jrhub.api.model.User;
import com.jrhub.api.persistance.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);
    
    @Mapping(target = "id", source = "id", qualifiedByName = "integerToLong")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    default User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
                entity.getId().longValue(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
        );
    }
    
    @Mapping(target = "id", source = "id", qualifiedByName = "longToInteger")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    UserEntity toEntity(User domain);
    
    @org.mapstruct.Named("integerToLong")
    default Long integerToLong(Integer value) {
        return value != null ? value.longValue() : null;
    }
    
    @org.mapstruct.Named("longToInteger")
    default Integer longToInteger(Long value) {
        return value != null ? value.intValue() : null;
    }
}
