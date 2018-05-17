package com.bkash.rnd.pgwwebview.model;

import java.io.Serializable;



/**
 * Created by syed.ahmad on 5/16/2018.
 */

public class PaymentRequest implements Serializable {

    private String amount;
    private String intent;

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "amount='" + amount + '\'' +
                ", intent='" + intent + '\'' +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
}
