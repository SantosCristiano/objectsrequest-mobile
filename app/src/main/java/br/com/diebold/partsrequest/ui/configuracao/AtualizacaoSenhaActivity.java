package br.com.diebold.partsrequest.ui.configuracao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.BaseActivity;
import br.com.diebold.partsrequest.MainActivity;
import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.controller.EquipamentoController;
import br.com.diebold.partsrequest.controller.LoginController;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.modelView.EquipamentoBomView;
import br.com.diebold.partsrequest.modelView.UsuarioView;
import br.com.diebold.partsrequest.ui.login.LoginActivity;
import br.com.diebold.partsrequest.utils.NotificationUtil;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class AtualizacaoSenhaActivity extends AppCompatActivity {

    Button btnAtualizarSenha;
    EditText edtNovaSenha;
    EditText edtConfirmarNovaSenha;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizacao_senha);

        this.setTitle(getString(R.string.menu_atualizacao_senha));

        this.context = this;

        btnAtualizarSenha = (Button) findViewById(R.id.btnAtualizarSenha);
        edtNovaSenha = (EditText) findViewById(R.id.edtNovaSenha);
        edtConfirmarNovaSenha = (EditText) findViewById(R.id.edtConfirmarNovaSenha);

        this.alertaAtualizarSenha();
    }

    public void alertaAtualizarSenha() {

        AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(AtualizacaoSenhaActivity.this);

        janelaEscolha.setTitle(R.string.atencao);
        janelaEscolha.setMessage(R.string.atualizacao_senha_atencao_msg);


        janelaEscolha.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });

        janelaEscolha.create().show();

    }

    public void eventAtualizarSenha(View view) {

        // Fecha o teclado
        BaseActivity.hideKeyboard(AtualizacaoSenhaActivity.this);

        String senha = edtNovaSenha.getText().toString();
        String confirmarSenha = edtConfirmarNovaSenha.getText().toString();

        if(senha.matches("") || confirmarSenha.matches("")) {
            Toast.makeText(AtualizacaoSenhaActivity.this, "Nova Senha não pode ser vázia.", Toast.LENGTH_LONG).show();
            return;
        } else if(senha.length() < 5 || confirmarSenha.length() < 5) {
            Toast.makeText(AtualizacaoSenhaActivity.this, "Nova Senha não pode ter menos de 5 caracteres.", Toast.LENGTH_LONG).show();
            return;
        } else if(!senha.equals(confirmarSenha)) {
            Toast.makeText(AtualizacaoSenhaActivity.this, "Senhas digitadas não são iguais.", Toast.LENGTH_LONG).show();
            return;
        }

        AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(AtualizacaoSenhaActivity.this);

        janelaEscolha.setTitle(R.string.atencao);
        janelaEscolha.setMessage(R.string.atualizacao_senha_msg);

        janelaEscolha.setNeutralButton(R.string.nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });

        janelaEscolha.setNegativeButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();

                btnAtualizarSenha.setEnabled(false);

                Executors.newSingleThreadExecutor().submit (new Runnable() {
                    @Override
                    public void run() {

                        UsuarioResponse DadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(AtualizacaoSenhaActivity.this, UsuarioResponse.class,  PreferencesUserUtil.PREFS_USER_DATA_LOGGED);

                        String usuario = DadosUsuarioLogado.getUsuario();

                        UsuarioView usuarioView = LoginController.getInstance(AtualizacaoSenhaActivity.this)
                                .alterarSenha(usuario, senha);

                        runOnUiThread( new Runnable() {
                            @Override
                            public void run() {

                                AlertDialog.Builder janelaMensagem = new AlertDialog.Builder(AtualizacaoSenhaActivity.this);

                                janelaMensagem.setNegativeButton("Logar Novamente", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        Intent intent = new Intent(AtualizacaoSenhaActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });

                                if (usuarioView.isSuccess()) {
                                    janelaMensagem.setTitle("Atualização de Senha");
                                    janelaMensagem.setMessage("A Senha foi Atualizada com Sucesso!");
                                }else{
                                    janelaMensagem.setTitle("Atualização de Senha");
                                    janelaMensagem.setMessage("Erro ao Atualizar a Senha!");
                                }

                                btnAtualizarSenha.setEnabled(true);

                                janelaMensagem.create().show();

                            }
                        });
                    }
                });
            }
        });

        janelaEscolha.create().show();


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
                Intent intent = new Intent(AtualizacaoSenhaActivity.this, ConfiguracaoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_ambiente:
                Intent intent2 = new Intent(AtualizacaoSenhaActivity.this, AmbienteActivity.class);
                startActivity(intent2);
                return true;
//            case R.id.action_logs:
//                Intent intent3 = new Intent(AtualizacaoSenhaActivity.this, LogsActivity.class);
//                startActivity(intent3);
//                return true;
            case R.id.action_senha:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}