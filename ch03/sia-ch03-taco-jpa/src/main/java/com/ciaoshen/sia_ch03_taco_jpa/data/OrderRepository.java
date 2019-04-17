package com.ciaoshen.sia_ch03_taco_jpa.data;

import org.springframework.data.repository.CrudRepository;
import com.ciaoshen.sia_ch03_taco_jpa.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}