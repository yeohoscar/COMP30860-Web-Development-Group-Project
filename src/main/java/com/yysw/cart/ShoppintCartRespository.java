package com.yysw.cart;

import com.yysw.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ShoppintCartRespository extends JpaRepository<ShoppingCartItem, Long> {
}
