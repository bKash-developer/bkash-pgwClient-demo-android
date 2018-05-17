package com.bkash.rnd.pgwwebview.model;

import java.io.Serializable;

/**
 * Created by syed.ahmad on 5/15/2018.
 */

public class Checkout implements Serializable {

    private String amount;
    private String wallet;
    private String intent;

    @Override
    public String toString() {
        return "Checkout{" +
                "amount='" + amount + '\'' +
                ", wallet='" + wallet + '\'' +
                ", intent='" + intent + '\'' +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
}
