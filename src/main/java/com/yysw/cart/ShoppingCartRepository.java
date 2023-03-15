package com.yysw.cart;

import com.yysw.user.customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartItem, Long> {
    long deleteByIdAndCustomer(Long id, Customer customer);
}
