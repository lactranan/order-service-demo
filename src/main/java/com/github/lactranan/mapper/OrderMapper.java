package com.github.lactranan.mapper;

import com.github.lactranan.entity.OrderEntity;
import com.github.lactranan.entity.OrderItemEntity;
import com.github.lactranan.entity.OrderState;
import com.github.lactranan.order.service.demo.v1.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface OrderMapper {

    @Mapping(target = "removeItemsItem", ignore = true)
    Order toDto(OrderEntity entity);

    OrderItem toDto(OrderItemEntity entity);

    List<OrderItem> toItemDtoList(List<OrderItemEntity> entities);

    OrderItemEntity toEntity(OrderItem dto);

    List<OrderItemEntity> toItemEntityList(List<OrderItem> dtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "otpCode", ignore = true)
    @Mapping(target = "approvalCode", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrderEntity toEntity(CreateOrderRequest request);

    default OrderState toEntityState(com.github.lactranan.order.service.demo.v1.dto.OrderState dtoState) {
        if (dtoState == null) return null;
        return OrderState.valueOf(dtoState.name());
    }

    default com.github.lactranan.order.service.demo.v1.dto.OrderState toDtoState(OrderState entityState) {
        if (entityState == null) return null;
        return com.github.lactranan.order.service.demo.v1.dto.OrderState.valueOf(entityState.name());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "otpCode", ignore = true)
    @Mapping(target = "approvalCode", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromRequest(UpdateOrderRequest request, @MappingTarget OrderEntity entity);

    @org.mapstruct.BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "otpCode", ignore = true)
    @Mapping(target = "approvalCode", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void patchFromRequest(PatchOrderRequest request, @MappingTarget OrderEntity entity);
}
