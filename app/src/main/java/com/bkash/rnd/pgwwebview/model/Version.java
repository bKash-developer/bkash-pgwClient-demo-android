package com.bkash.rnd.pgwwebview.model;

import java.io.Serializable;

/**
 * Created by Wordh Ul Hasan on 3/24/2019.
 */


public class Version implements Serializable {

    private String version;

    @Override
    public String toString() {
        return "Version = "+ version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
