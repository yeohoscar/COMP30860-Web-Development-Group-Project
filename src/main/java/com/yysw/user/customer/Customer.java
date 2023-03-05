package com.yysw.user.customer;

import java.io.Serializable;
import com.yysw.user.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Customer extends User implements Serializable {
}
