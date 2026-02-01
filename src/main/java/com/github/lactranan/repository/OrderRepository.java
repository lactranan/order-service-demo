package com.github.lactranan.repository;

import com.github.lactranan.entity.OrderEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<OrderEntity, String> {
}
