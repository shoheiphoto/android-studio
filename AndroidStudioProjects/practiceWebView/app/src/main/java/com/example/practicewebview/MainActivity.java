package com.example.practicewebview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.provider.Telephony.Carriers.PASSWORD;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private final String USERNAME = "admin";
    private final String PASSWORD = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        /* WebViewのインスタンスを取得 */
        mWebView = (WebView)findViewById(R.id.webview);

        /* webViewの設定インスタンスを取得 */
        WebSettings settings = mWebView.getSettings();

        /* webViewの設定 */
        settings.setJavaScriptEnabled(true);    //JavaScriptの実行：許可（true）
        settings.setSaveFormData(false);        //Formデータの保存：なし（false）
        settings.setSupportZoom(true);         // ズーム機能：無効(false)

        /* WebViewに表示するWebViewClientオブジェクトを設定 */
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(USERNAME,PASSWORD);
            }
        });

        /* webViewに引数で指定したURLのページをロード（読込）させる */
        mWebView.loadUrl("http://10.26.142.123");

    }
}