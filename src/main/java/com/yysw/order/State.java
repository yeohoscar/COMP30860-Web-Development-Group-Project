package com.yysw.order;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum State {
    @Enumerated(EnumType.STRING)
    NEW,
    @Enumerated(EnumType.STRING)
    DELIVERED,
    @Enumerated(EnumType.STRING)
    CANCELLED
}


