package com.ciaoshen.sia_ch03_taco.data;

import com.ciaoshen.sia_ch03_taco.Order;

public interface OrderRepository {
    Order save(Order order);
}