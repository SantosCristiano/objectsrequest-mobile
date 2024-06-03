package br.com.diebold.partsrequest.ui.politica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.diebold.partsrequest.R;

public class PrivacidadeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacidade);

        TextView tv = (TextView) findViewById(R.id.textViewPrivacidade);


        tv.setMovementMethod(new ScrollingMovementMethod());

        /*WebView wv = (WebView) findViewById(R.id.WebViewPrivacidade);

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        wv.loadUrl("https://www.google.com");*/

        /*WebView wv = new WebView(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        wv.setLayoutParams(lp);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);
        wv.loadUrl("https://www.dieboldnixdorf.com.br/politica-de-privacidade");*/
    }
}