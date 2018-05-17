# Android Configurations
Add the INTERNET permission to your AndroidManifest.xml. 

AndroidManifest.xml

<uses¬permission android:name="android.permission.INTERNET"/>

# Webview Integration

In order to generate the iframe create a webview,
 
 ```private WebView mWebView;
 mWebView = (WebView) findViewById(YOUR.WEBVIEW.LAYOUT);
 
 WebSettings webSettings = mWebView.getSettings();
  webSettings.setJavaScriptEnabled(true);
        mWebView.setClickable(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.clearCache(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
 ```    
        

For interaction from html file add the code below,
         
``` 
        mWebView.addJavascriptInterface(new JavaScriptInterface(this), "AndroidNative");

        mWebView.loadUrl(YOUR.HTML.FILE);
        private class CheckoutWebViewClient extends WebViewClient {

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals(ANY.EXTERNAL.URL)) {
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
