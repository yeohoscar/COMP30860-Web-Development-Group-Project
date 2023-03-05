package com.yysw.user.owner;

import com.yysw.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("1")
public class Owner extends User implements Serializable {

}