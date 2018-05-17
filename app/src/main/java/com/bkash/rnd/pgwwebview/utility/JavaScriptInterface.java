package com.bkash.rnd.pgwwebview.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.bkash.rnd.pgwwebview.activity.MainActivity;

/**
 * Created by syed.ahmad on 5/15/2018.
 */

public class JavaScriptInterface {
    Context mContext;

    /**
     * Instantiate the interface and set the context
     */
    public JavaScriptInterface(Context c) {
        mContext = c;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void switchActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

}