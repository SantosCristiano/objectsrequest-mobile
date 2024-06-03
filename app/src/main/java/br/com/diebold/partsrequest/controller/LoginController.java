
package br.com.diebold.partsrequest.controller;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Date;

import br.com.diebold.partsrequest.data.api.ApiServices;
import br.com.diebold.partsrequest.data.api.response.LoginResponse;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.modelView.LoginView;
import br.com.diebold.partsrequest.modelView.UsuarioView;
import br.com.diebold.partsrequest.ui.solicitacao.SolicitacaoActivity;
import br.com.diebold.partsrequest.utils.DateTime;
import br.com.diebold.partsrequest.utils.DateTimeFormat;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class LoginController {
    private static LoginController instance;
    private AppCompatActivity context;


    public LoginController(AppCompatActivity context) {
        this.context = context;
    }

    public static LoginController getInstance(AppCompatActivity context) {
        if (instance == null) {
            instance = new LoginController(context);
        }
        return instance;
    }

    public LoginView login(String usuario, String senha) {

        LoginView lw = new LoginView();
        UsuarioView uv = new UsuarioView();

        if(!usuario.equals(null) || !usuario.equals("") || !senha.equals(null) || !senha.equals("")){
            usuario = usuario.trim();
            senha = senha.trim();
        } else {
            return lw;
        }

        try {

//            PreferencesUserUtil.logout(context);

            LoginResponse response = ApiServices.getInstance(context).userLogin(usuario, senha);

            if (response.isSucesso()) {
                lw.setLogado(true);
                lw.setToken(response.getToken());
                lw.setMensagem("Logado com Sucesso!");

                PreferencesUserUtil.saveToPrefs(context, PreferencesUserUtil.PREFS_USER_LOGGED, true);
                PreferencesUserUtil.saveObjectToPref(context, lw.getToken(), PreferencesUserUtil.PREFS_USER_TOKEN_LOGGED);

                UsuarioResponse responseDadosUsuario = null;
                try {
                    responseDadosUsuario = ApiServices.getInstance(context).userInfo(usuario);

                    if (responseDadosUsuario.isSuccess()) {
                        Date DataAtual = new Date();
                        Date dataFimVigenciaTecnico = responseDadosUsuario.getTecnico().getFimVigencia();
                        Date dataFimUsuario = responseDadosUsuario.getDataFim();

                        if(dataFimUsuario.getTime() < DataAtual.getTime()) {
                            lw.setLogado(false);
                            lw.setToken("");
                            lw.setMensagem("Usuario nao tem permissao de acesso ao sistema!");
                            uv.setSuccess(false);
                            uv.setMensagem("Usuario nao tem permissao de acesso ao sistema!");
                            //PreferencesUserUtil.logout(context);
                        } else {
                            PreferencesUserUtil.saveObjectToPref(context, responseDadosUsuario, PreferencesUserUtil.PREFS_USER_DATA_LOGGED);
                            uv.setSuccess(true);
                            uv.setMensagem("Dados salvos com sucesso no preferences!");
                        }

                    } else {

                        PreferencesUserUtil.saveToPrefs(context, PreferencesUserUtil.PREFS_USER_LOGGED, false);
                        PreferencesUserUtil.saveObjectToPref(context, "", PreferencesUserUtil.PREFS_USER_TOKEN_LOGGED);

                        lw.setLogado(false);
                        lw.setMensagem(responseDadosUsuario.getMessage());
                    }

                } catch (IOException e) {

                    PreferencesUserUtil.saveToPrefs(context, PreferencesUserUtil.PREFS_USER_LOGGED, false);
                    PreferencesUserUtil.saveObjectToPref(context, "", PreferencesUserUtil.PREFS_USER_TOKEN_LOGGED);

                    lw.setLogado(false);
                    lw.setMensagem(e.getMessage());
                }

            } else {

                PreferencesUserUtil.saveToPrefs(context, PreferencesUserUtil.PREFS_USER_LOGGED, false);
                PreferencesUserUtil.saveObjectToPref(context, "", PreferencesUserUtil.PREFS_USER_TOKEN_LOGGED);

                lw.setLogado(false);
                lw.setMensagem(response.getMessage());

            }

        } catch (IOException e) {
            lw.setLogado(false);
            lw.setMensagem("Erro ao tentar logar.");

            e.printStackTrace();
        }

        return lw;

    }

    public UsuarioView alterarSenha(String usuario, String senha) {

        UsuarioView uv = new UsuarioView();

        if(!usuario.equals(null) || !usuario.equals("") || !senha.equals(null) || !senha.equals("")){
            usuario = usuario.trim();
            senha = senha.trim();
        } else {
            return uv;
        }

        try {
            UsuarioResponse response = ApiServices.getInstance(context).alterarSenha(usuario, senha);

            if (response.isSuccess()) {
                uv.setSuccess(true);
                uv.setMensagem("A Senha foi Atualizada com Sucesso!");
            }  else {
                uv.setSuccess(false);
                uv.setMensagem("Erro ao atualizar a senha!");
            }
        } catch (IOException e) {
            uv.setSuccess(false);
            uv.setMensagem("Erro ao atualizar a senha!");
        }

        return uv;

    }

}
