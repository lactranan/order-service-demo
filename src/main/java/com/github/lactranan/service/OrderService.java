package com.github.lactranan.service;

import com.github.lactranan.entity.OrderEntity;
import com.github.lactranan.entity.OrderState;
import com.github.lactranan.mapper.OrderMapper;
import com.github.lactranan.order.service.demo.v1.dto.CreateOrderRequest;
import com.github.lactranan.order.service.demo.v1.dto.Order;
import com.github.lactranan.order.service.demo.v1.dto.PatchOrderRequest;
import com.github.lactranan.order.service.demo.v1.dto.UpdateOrderRequest;
import com.github.lactranan.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.OffsetDateTime;
import java.util.UUID;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderMapper orderMapper;

    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        OrderEntity entity = orderMapper.toEntity(request);
        entity.setId(UUID.randomUUID().toString());
        entity.setState(OrderState.DRAFT);
        entity.setCreatedAt(OffsetDateTime.now());
        entity.setUpdatedAt(OffsetDateTime.now());
        orderRepository.persist(entity);
        return orderMapper.toDto(entity);
    }

    public Order getOrderById(String id) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Order not found: " + id);
        }
        return orderMapper.toDto(entity);
    }

    @Transactional
    public Order updateOrder(String id, UpdateOrderRequest request) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Order not found: " + id);
        }
        orderMapper.updateFromRequest(request, entity);
        entity.setUpdatedAt(OffsetDateTime.now());
        return orderMapper.toDto(entity);
    }

    @Transactional
    public Order patchOrder(String id, PatchOrderRequest request) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Order not found: " + id);
        }
        orderMapper.patchFromRequest(request, entity);
        entity.setUpdatedAt(OffsetDateTime.now());
        return orderMapper.toDto(entity);
    }

    @Transactional
    public void deleteOrder(String id) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Order not found: " + id);
        }
        orderRepository.delete(entity);
    }
}
