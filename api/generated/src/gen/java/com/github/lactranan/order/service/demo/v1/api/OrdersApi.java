package com.github.lactranan.order.service.demo.v1.api;

import com.github.lactranan.order.service.demo.v1.dto.ConfirmOrderVerificationRequest;
import com.github.lactranan.order.service.demo.v1.dto.CreateOrderRequest;
import com.github.lactranan.order.service.demo.v1.dto.Order;
import com.github.lactranan.order.service.demo.v1.dto.ReviewOrderRequest;
import com.github.lactranan.order.service.demo.v1.dto.StartOrderVerification200Response;
import com.github.lactranan.order.service.demo.v1.dto.StartOrderVerificationRequest;
import com.github.lactranan.order.service.demo.v1.dto.UpdateOrderRequest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;




import java.io.InputStream;
import java.util.Map;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;


@Path("/orders")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen")
public interface OrdersApi {

    @PATCH
    @Path("/{id}/verify")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response confirmOrderVerification(@PathParam("id") String id,@Valid @NotNull ConfirmOrderVerificationRequest confirmOrderVerificationRequest);

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response createOrder(@Valid @NotNull CreateOrderRequest createOrderRequest);

    @DELETE
    @Path("/{id}")
    Response deleteOrder(@PathParam("id") String id);

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    Response getOrderById(@PathParam("id") String id);

    @PATCH
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response patchOrder(@PathParam("id") String id,@Valid @NotNull Object body);

    @POST
    @Path("/{id}/review")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response reviewOrder(@PathParam("id") String id,@Valid @NotNull ReviewOrderRequest reviewOrderRequest);

    @POST
    @Path("/{id}/verify")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response startOrderVerification(@PathParam("id") String id,@Valid @NotNull StartOrderVerificationRequest startOrderVerificationRequest);

    @PUT
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response updateOrder(@PathParam("id") String id,@Valid @NotNull UpdateOrderRequest updateOrderRequest);
}
