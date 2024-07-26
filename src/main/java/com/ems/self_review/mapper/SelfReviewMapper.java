package com.ems.self_review.mapper;

import com.ems.self_review.dto.SelfReviewDto;
import com.ems.self_review.enitity.SelfReview;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SelfReviewMapper {
        SelfReviewMapper INSTANCE = Mappers.getMapper(SelfReviewMapper.class);
        SelfReviewDto mapEntityToDto(SelfReview entity);
        SelfReview mapDtoToEntity(SelfReviewDto dto);
    }
