package br.com.diebold.partsrequest.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.BaseActivity;
import br.com.diebold.partsrequest.MainActivity;
import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.controller.LoginController;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.databinding.ActivityLoginBinding;
import br.com.diebold.partsrequest.modelView.LoginView;
import br.com.diebold.partsrequest.ui.configuracao.AtualizacaoSenhaActivity;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;


public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;
    TextView tvVersao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.login;
        loadingProgressBar = binding.loading;
        tvVersao = binding.versao;

        tvVersao.setText("Versão: " + br.com.diebold.partsrequest.BuildConfig.VERSION_NAME);

        loginButton.setOnClickListener(v -> login());

        if(br.com.diebold.partsrequest.BuildConfig.DEBUG) {
            usernameEditText.setText("");
            passwordEditText.setText("");
        }


        //limpar dados de login
        if(PreferencesUserUtil.isLogged(this)) {
            PreferencesUserUtil.logout(this);
            PreferencesUserUtil.saveToPrefs(this, PreferencesUserUtil.PREFS_USER_LOGGED, false);
            PreferencesUserUtil.saveObjectToPref(this, "", PreferencesUserUtil.PREFS_USER_TOKEN_LOGGED);

        }

    }

    private void login(){

        // Fecha o teclado
        BaseActivity.hideKeyboard(LoginActivity.this);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Verifica se usuario e senha estão vázios
        if(username.matches("") || password.matches("")) {
            Toast.makeText(LoginActivity.this, "Usuário e senha não podem ser vázios.", Toast.LENGTH_LONG).show();
            return;
        }

        loadingProgressBar.setVisibility(View.VISIBLE);

        Executors.newSingleThreadExecutor().submit (new Runnable() {
            @Override
            public void run() {

                LoginView lw = LoginController.getInstance(LoginActivity.this)
                        .login(usernameEditText.getText().toString(), passwordEditText.getText().toString());

                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if (!lw.isLogado()) {
                            usernameEditText.setText("");
                            passwordEditText.setText("");

                            AlertDialog.Builder janelaMensagem = new AlertDialog.Builder(LoginActivity.this);

                            janelaMensagem.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.cancel();
                                }
                            });

                            janelaMensagem.setTitle(R.string.atencao);
                            janelaMensagem.setMessage(lw.getMensagem());

                            if (!LoginActivity.this.isFinishing()){
                                janelaMensagem.create().show();
                            }
                        }else{
                            UsuarioResponse DadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(LoginActivity.this, UsuarioResponse.class,  PreferencesUserUtil.PREFS_USER_DATA_LOGGED);

                            if(DadosUsuarioLogado.isAlteraSenha()) {
                                OpenScreen(AtualizacaoSenhaActivity.class, null);
                            } else {
                                OpenScreen(MainActivity.class, null);
                            }
                        }

                        loadingProgressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, lw.getMensagem(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}