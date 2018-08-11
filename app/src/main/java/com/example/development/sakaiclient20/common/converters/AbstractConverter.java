package com.example.development.sakaiclient20.common.converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Development on 8/11/18.
 */

public abstract class AbstractConverter<TEntity, TDto> {
    public abstract TEntity fromDTO(TDto DTO);
    public abstract TDto fromEntity(TEntity entity);

    public List<TEntity> fromDTOs(List<TDto> DTOs) {
        List<TEntity> entities = new ArrayList<>(DTOs.size());
        for(TDto DTO : DTOs) {
            entities.add(fromDTO(DTO));
        }
        return entities;
    }

    public List<TDto> fromEntities(List<TEntity> entities) {
        List<TDto> DTOs = new ArrayList<>(entities.size());
        for(TEntity entity : entities) {
            DTOs.add(fromEntity(entity));
        }
        return DTOs;
    }
}
