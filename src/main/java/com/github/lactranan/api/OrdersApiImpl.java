package com.github.lactranan.api;

import com.github.lactranan.order.service.demo.v1.api.OrdersApi;
import com.github.lactranan.order.service.demo.v1.dto.*;
import com.github.lactranan.service.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/orders")
public class OrdersApiImpl implements OrdersApi {

    @Inject
    OrderService orderService;

    @Override
    public Response createOrder(CreateOrderRequest createOrderRequest) {
        Order order = orderService.createOrder(createOrderRequest);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @Override
    public Response getOrderById(String id) {
        Order order = orderService.getOrderById(id);
        return Response.ok(order).build();
    }

    @Override
    public Response updateOrder(String id, UpdateOrderRequest updateOrderRequest) {
        Order order = orderService.updateOrder(id, updateOrderRequest);
        return Response.ok(order).build();
    }

    @Override
    public Response patchOrder(String id, PatchOrderRequest patchOrderRequest) {
        Order order = orderService.patchOrder(id, patchOrderRequest);
        return Response.ok(order).build();
    }

    @Override
    public Response deleteOrder(String id) {
        orderService.deleteOrder(id);
        return Response.noContent().build();
    }

    @Override
    public Response startOrderVerification(String id, StartOrderVerificationRequest startOrderVerificationRequest) {
        StartOrderVerification200Response response = orderService.startOrderVerification(id, startOrderVerificationRequest);
        return Response.ok(response).build();
    }

    @Override
    public Response confirmOrderVerification(String id, ConfirmOrderVerificationRequest confirmOrderVerificationRequest) {
        Order order = orderService.confirmOrderVerification(id, confirmOrderVerificationRequest);
        return Response.ok(order).build();
    }

    @Override
    public Response reviewOrder(String id, ReviewOrderRequest reviewOrderRequest) {
        Order order = orderService.reviewOrder(id, reviewOrderRequest);
        return Response.ok(order).build();
    }
}
