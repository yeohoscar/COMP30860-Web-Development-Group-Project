package com.yysw.user;

import com.yysw.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPasswd(String username, String passwd);

    User findUserById(Long id);
}