package com.github.lactranan.service;

import com.github.lactranan.entity.OrderEntity;
import com.github.lactranan.entity.OrderState;
import com.github.lactranan.mapper.OrderMapper;
import com.github.lactranan.order.service.demo.v1.dto.*;
import com.github.lactranan.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.security.SecureRandom;
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

    @Transactional
    public StartOrderVerification200Response startOrderVerification(String id, StartOrderVerificationRequest request) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Order not found: " + id);
        }
        if (entity.getState() != OrderState.DRAFT) {
            throw new BadRequestException("Order must be in DRAFT state to start verification");
        }

        String otpCode = generateOtpCode();
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setOtpCode(otpCode);
        entity.setUpdatedAt(OffsetDateTime.now());

        StartOrderVerification200Response response = new StartOrderVerification200Response();
        response.setState(orderMapper.toDtoState(entity.getState()));
        return response;
    }

    @Transactional
    public Order confirmOrderVerification(String id, ConfirmOrderVerificationRequest request) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Order not found: " + id);
        }
        if (entity.getState() != OrderState.DRAFT) {
            throw new BadRequestException("Order must be in DRAFT state to confirm verification");
        }
        if (entity.getOtpCode() == null) {
            throw new BadRequestException("Verification not started for this order");
        }
        if (!entity.getOtpCode().equals(request.getOtpCode())) {
            throw new BadRequestException("Invalid OTP code");
        }

        entity.setState(OrderState.UNDER_REVIEW);
        entity.setOtpCode(null);
        entity.setApprovalCode(generateApprovalCode());
        entity.setUpdatedAt(OffsetDateTime.now());

        return orderMapper.toDto(entity);
    }

    @Transactional
    public Order reviewOrder(String id, ReviewOrderRequest request) {
        OrderEntity entity = orderRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Order not found: " + id);
        }
        if (entity.getState() != OrderState.UNDER_REVIEW) {
            throw new BadRequestException("Order must be in UNDER_REVIEW state to review");
        }
        if (!entity.getApprovalCode().equals(request.getApprovalCode())) {
            throw new BadRequestException("Invalid approval code");
        }

        if (request.getDecision() == ReviewOrderRequest.DecisionEnum.APPROVE) {
            entity.setState(OrderState.APPROVED);
        } else {
            entity.setState(OrderState.REJECTED);
        }
        entity.setUpdatedAt(OffsetDateTime.now());

        return orderMapper.toDto(entity);
    }

    private String generateOtpCode() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private String generateApprovalCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
