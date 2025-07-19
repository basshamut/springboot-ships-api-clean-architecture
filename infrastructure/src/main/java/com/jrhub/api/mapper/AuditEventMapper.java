package com.jrhub.api.mapper;

import com.jrhub.api.model.AuditEvent;
import com.jrhub.api.dto.AuditEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuditEventMapper {
    
    AuditEventMapper INSTANCE = Mappers.getMapper(AuditEventMapper.class);

    @Mapping(target = "operation", source = "operation")
    @Mapping(target = "shipId", source = "shipId")
    @Mapping(target = "shipName", source = "shipName")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "timestamp", expression = "java(event.getTimestamp().toInstant(java.time.ZoneOffset.UTC).getEpochSecond())")
    AuditEventDto toDto(AuditEvent event);

}