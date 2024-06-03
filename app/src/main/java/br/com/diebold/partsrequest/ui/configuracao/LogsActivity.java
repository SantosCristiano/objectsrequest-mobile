package br.com.diebold.partsrequest.ui.configuracao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.diebold.partsrequest.R;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        this.setTitle(getString(R.string.menu_logs));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.configuracao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_informacoes:
                Intent intent = new Intent(LogsActivity.this, ConfiguracaoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_ambiente:
                Intent intent2 = new Intent(LogsActivity.this, AmbienteActivity.class);
                startActivity(intent2);
                return true;
//            case R.id.action_logs:
//                return true;
            case R.id.action_senha:
                Intent intent4 = new Intent(LogsActivity.this, AtualizacaoSenhaActivity.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}