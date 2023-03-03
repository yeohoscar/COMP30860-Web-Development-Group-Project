package com.yysw.order;

import org.springframework.ui.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private int id;
    private ArrayList<Model> modelsPurchased;

}
