package com.bkash.rnd.pgwwebview.model;

import java.io.Serializable;

/**
 * Created by syed.ahmad on 5/15/2018.
 * Updated by Wordh Ul Hasan on 3/24/2019.
 */

public class Checkout implements Serializable {

    private String amount;
    private String intent;
    private String version;

    @Override
    public String toString() {
        return "Checkout{" +
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
