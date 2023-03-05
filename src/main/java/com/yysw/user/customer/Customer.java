package com.yysw.user.customer;

import java.io.Serializable;
import com.yysw.order.Order;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column private String username;
    @Column private String passwd;

    public Customer() {}

    public Customer(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
