package com.yysw.site;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class PaymentInformation implements Serializable {
    @NotBlank
    private String name;

    @Size(min = 16, max = 16)
    @NotBlank
    private String cardNum;

    @DateTimeFormat(pattern = "mm-yy")
    @NotBlank
    private Date expiry;

    @Size(min = 3, max = 3)
    @NotBlank
    private int cvv;


    public String getName() {
        return name;
    }

    public String getCardNum() {
        return cardNum;
    }

    public Date getExpiry() {
        return expiry;
    }

    public int getCvv() {
        return cvv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
