package com.example.practicewebview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private final String USERNAME = "admin";
    private final String PASSWORD = "12345";
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* webViewインスタンスの取得と設定 */
        final WebView mWebView = (WebView)findViewById(R.id.webview);
        /* webViewの設定インスタンスを取得 */
        WebSettings settings = mWebView.getSettings();
        /* webViewの設定 */
        settings.setJavaScriptEnabled(true);    //JavaScriptの実行：許可（true）
        settings.setSaveFormData(false);        //Formデータの保存：なし（false）
        settings.setSupportZoom(false);         // ズーム機能：無効(false)

        /* WebViewに表示するWebViewClientオブジェクトを設定 */
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(USERNAME,PASSWORD);
            }
        });

        /* webViewに引数で指定したURLのページをロード（読込）させる */
        mWebView.loadUrl("http://10.26.142.123/cgi-bin/camera?resolution=640&quality=5&Language=1%20height=640%20width=480");

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mWebView.reload();
                            }
                        });
                    } catch (InterruptedException e) {
                    }
                }
            }
        });

        th.start();

    }
}