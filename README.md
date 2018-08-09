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
        //request = { amount: '1000', intent: 'sale' }
        //"intent" parameter has two options 'sale' or 'authorization'
        //'sale' is used for immediate payment and 'authorization' is for deffered payment(capture interface call needed from backend to confirm transaction)
        
            String paymentRequest = "{paymentRequest:" + request + "}";
            mWebView.loadUrl("javascript:callReconfigure(" + paymentRequest + " )");
            mWebView.loadUrl("javascript:clickPayButton()");
            progressBar.setVisibility(view.GONE);

        }
```

# Integrate bKash Checkout In your webview

Add below lines inside HTML head Tag,

```
    <meta name="viewport" content="width=device-width" ,="" initial-scale="1.0/">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1">

    <script src="PATH/jquery-1.8.3.min.js"></script>
    <script src="http://scripts.sandbox.bka.sh/bKash-checkout-v2.js"></script>
```    
Add the bKash button/image(must have id=bKash_button) inside the body Tag,

``` 
<button id="bKash_button" style="display:none;">Pay With bKash</button>
```

# Use bKash Checkout functions 

```
Check the code for reference
```

Call the bKash.reconfigure(paymentRequest) function for passing the 'paymentRequest' parameter from your WebviewActivity as shown in above Webview Integration section.

i.e. mWebView.loadUrl("javascript:callReconfigure(" + paymentRequest + " )");
 
 ```
               function callReconfigure(val){
                     bKash.reconfigure(val);
                     }
```


Trigger the "bKash_button" click function,

```

$("#bKash_button").trigger('click');

```
