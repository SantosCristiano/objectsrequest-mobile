package br.com.diebold.partsrequest.ui.configuracao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.databinding.ActivityConfiguracaoBinding;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class ConfiguracaoActivity extends AppCompatActivity {

    private ActivityConfiguracaoBinding binding;

    TextView tvTecnico, tvNumeroDoTecnico, /*numero_ems, telefone,*/ tvFilial;
    UsuarioResponse DadosUsuarioLogado = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        binding = ActivityConfiguracaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvTecnico = binding.tvTecnico;
        tvNumeroDoTecnico = binding.tvNumeroDoTecnico;
//        numero_ems = binding.numeroEms;
//        telefone = binding.telefone;
        tvFilial = binding.tvFilialModel;

        this.setTitle(getString(R.string.menu_informacoes));

        DadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this, UsuarioResponse.class,  PreferencesUserUtil.PREFS_USER_DATA_LOGGED);

        tvTecnico.setText(DadosUsuarioLogado.getNome());
        tvNumeroDoTecnico.setText(DadosUsuarioLogado.getTecnico().getIdTecnico().toString());
//        numero_ems.setText("API NÃO ESTA RETORNANDO AINDA");
//        telefone.setText("API NÃO ESTA RETORNANDO AINDA");
        if(!DadosUsuarioLogado.getFiliais().isEmpty()) {
            tvFilial.setText(DadosUsuarioLogado.getFiliais().get(0).getId() + " - " + DadosUsuarioLogado.getFiliais().get(0).getNome());
        }

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
                return true;
            case R.id.action_ambiente:
                Intent intent2 = new Intent(ConfiguracaoActivity.this, AmbienteActivity.class);
                startActivity(intent2);
                return true;
//            case R.id.action_logs:
//                Intent intent3 = new Intent(ConfiguracaoActivity.this, LogsActivity.class);
//                startActivity(intent3);
//                return true;
            case R.id.action_senha:
                Intent intent4 = new Intent(ConfiguracaoActivity.this, AtualizacaoSenhaActivity.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}