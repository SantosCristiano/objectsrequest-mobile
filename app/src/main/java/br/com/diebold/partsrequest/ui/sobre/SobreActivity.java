package br.com.diebold.partsrequest.ui.sobre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import br.com.diebold.partsrequest.R;

public class SobreActivity extends AppCompatActivity {

    TextView tvVersao, tvVersaoSO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LayoutInflater mInflater = null;
        setContentView(R.layout.activity_sobre);

        this.setTitle(getString(R.string.menu_sobre));

        tvVersao = findViewById(R.id.textVersaoApp);
        tvVersao.setText("Versão da aplicação:\n\n" +  br.com.diebold.partsrequest.BuildConfig.VERSION_NAME);


        tvVersaoSO = findViewById(R.id.textVersaoSO);
        tvVersaoSO.setText("Versão do Sistema Operacional:\n\n" +   "Android " + Build.VERSION.RELEASE + "\n(Security Path: " + Build.VERSION.SECURITY_PATCH + ")");
    }
}