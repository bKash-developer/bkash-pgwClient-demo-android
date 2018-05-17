package com.bkash.rnd.pgwwebview.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.bkash.rnd.pgwwebview.R;
import com.bkash.rnd.pgwwebview.model.Checkout;
import com.bkash.rnd.pgwwebview.model.PaymentRequest;
import com.bkash.rnd.pgwwebview.utility.JavaScriptInterface;
import com.google.gson.Gson;

public class WebViewCheckoutActivity extends AppCompatActivity {

    private WebView mWebView;
    private static final String TAG = "WebViewActivity";
    private ProgressBar progressBar;
    private String request = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_checkout);

        Checkout checkout = (Checkout) getIntent().getSerializableExtra("values");

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(checkout.getAmount());
        paymentRequest.setIntent(checkout.getIntent());

        Gson gson = new Gson();
        request = gson.toJson(paymentRequest);

        Log.i(TAG, request);

        mWebView = (WebView) findViewById(R.id.activity_checkout_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        /*
        * Below part is for enabling webview settings for using javascript and accessing html files and other assets
           */
        mWebView.setClickable(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.clearCache(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        /*
        * To control any kind of interaction from html file
           */
        mWebView.addJavascriptInterface(new JavaScriptInterface(this), "AndroidNative");

        mWebView.loadUrl("file:///android_asset/www/checkout.html");

        mWebView.setWebViewClient(new WebViewCheckoutActivity.CheckoutWebViewClient());
    }

    private class CheckoutWebViewClient extends WebViewClient {

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("External URL: ", url);
            if (url.equals("https://www.bkash.com/terms-and-conditions")) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(myIntent);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(view.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            String paymentRequest = "{paymentRequest:" + request + "}";
            mWebView.loadUrl("javascript:callReconfigure(" + paymentRequest + " )");
            mWebView.loadUrl("javascript:clickPayButton()");
            progressBar.setVisibility(view.GONE);

        }

    }
}
