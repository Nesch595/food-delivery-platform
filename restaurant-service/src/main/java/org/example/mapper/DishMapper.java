package org.example.mapper;

import org.example.dto.DishDto;
import org.example.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DishMapper {
    DishMapper INSTANCE = Mappers.getMapper(DishMapper.class);

    DishDto toDto(Dish dish);
    Dish toEntity(DishDto dishDto);
}
