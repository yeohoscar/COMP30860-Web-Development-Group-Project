package com.yysw.user.customer;

import com.yysw.general.AIModel;
import com.yysw.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("from Customer ")
    User findByUsernameAndPasswd(String username, String passwd);

    @Query("from Customer")
    List<AIModel> findCartById(Integer id);

    @Modifying
    @Query("update Customer c set c.cart= :newCart where c.user_id = :id")
    int updateCart(@Param("newCart") List<AIModel> newCart, @Param("id") Integer customer);


}