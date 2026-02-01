package com.github.lactranan.api;

import com.github.lactranan.order.service.demo.v1.api.OrdersApi;
import com.github.lactranan.order.service.demo.v1.dto.*;
import jakarta.ws.rs.core.Response;

public class OrdersApiImpl implements OrdersApi {

    @Override
    public Response confirmOrderVerification(String id, ConfirmOrderVerificationRequest confirmOrderVerificationRequest) {
        return null;
    }

    @Override
    public Response createOrder(CreateOrderRequest createOrderRequest) {
        return null;
    }

    @Override
    public Response deleteOrder(String id) {
        return null;
    }

    @Override
    public Response getOrderById(String id) {
        return null;
    }

    @Override
    public Response patchOrder(String id, PatchOrderRequest patchOrderRequest) {
        return null;
    }

    @Override
    public Response reviewOrder(String id, ReviewOrderRequest reviewOrderRequest) {
        return null;
    }

    @Override
    public Response startOrderVerification(String id, StartOrderVerificationRequest startOrderVerificationRequest) {
        return null;
    }

    @Override
    public Response updateOrder(String id, UpdateOrderRequest updateOrderRequest) {
        return null;
    }
}
