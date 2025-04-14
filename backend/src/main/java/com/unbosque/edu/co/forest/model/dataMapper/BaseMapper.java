package com.unbosque.edu.co.forest.model.dataMapper;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<Entity, DTO> {
    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto);

    default List<DTO> toDTOList(List<Entity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<Entity> toEntityList(List<DTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}