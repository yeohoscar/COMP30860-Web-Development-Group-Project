package com.yysw.cart;

import com.yysw.user.customer.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {
    //@Modifying
    //@Query("delete from ShoppingCartItem s where s.id=:id")
    void deleteByIdAndCustomer(Long id, Customer customer);
}
