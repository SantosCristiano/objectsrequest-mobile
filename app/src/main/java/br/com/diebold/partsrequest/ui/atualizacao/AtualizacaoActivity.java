package br.com.diebold.partsrequest.ui.atualizacao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.controller.EquipamentoController;
import br.com.diebold.partsrequest.modelView.EquipamentoBomView;

import br.com.diebold.partsrequest.ui.login.LoginActivity;
import br.com.diebold.partsrequest.utils.NotificationUtil;

public class AtualizacaoActivity extends AppCompatActivity {

    Button btnAtualizarEstruturaBom;
    ProgressBar loadingProgressBar;
    TextView tvMsgAtualizacao;
    NotificationUtil notificationUtil;
    private int NOTIF_ID = 104;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizacao);

        this.setTitle(getString(R.string.menu_atualizacao));

        this.context = this;

        notificationUtil = new NotificationUtil(this.context);

        btnAtualizarEstruturaBom = (Button) findViewById(R.id.btnAtualizarEstruturaBom);
        loadingProgressBar = (ProgressBar) findViewById(R.id.progressLoading);
        tvMsgAtualizacao = (TextView) findViewById(R.id.tvMsgAtualizacao);
    }

    public void eventAtualizarEstruturaBOM(View view) {

        AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(AtualizacaoActivity.this);

        janelaEscolha.setTitle(R.string.atencao);
        janelaEscolha.setMessage(R.string.atualizacao_atencao_msg);

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

                Toast.makeText(AtualizacaoActivity.this, R.string.atualizacao_atualizando_estrutura_titulo, Toast.LENGTH_LONG).show();

                btnAtualizarEstruturaBom.setEnabled(false);
                loadingProgressBar.setVisibility(View.VISIBLE);
                tvMsgAtualizacao.setVisibility(View.VISIBLE);

                Executors.newSingleThreadExecutor().submit (new Runnable() {
                    @Override
                    public void run() {

                        final boolean[] atualizado = {true};

                        EquipamentoController.getInstance(AtualizacaoActivity.this)
                                .excluirEquipamentosBomDB();

                        for(int i = 0; i < 1000; i++) {

                            int pagina = i + 1;

                            EquipamentoBomView equipamentoBomView = EquipamentoController.getInstance(AtualizacaoActivity.this)
                                    .atualizarEstruturaBomMFS(pagina);

                            if(!atualizado[0] || equipamentoBomView.getEquipamentosBom() == null)
                                break;

                            runOnUiThread( new Runnable() {
                                @Override
                                public void run() {

                                    if (!equipamentoBomView.isSuccess()) {
                                        atualizado[0] = false;
                                    } else {
                                        Toast.makeText(AtualizacaoActivity.this, "Atualizar Estrutura BOM " +  getString(R.string.pagina) + ": " + pagina, Toast.LENGTH_LONG).show();
                                        //notificationUtil.sendNotification("AtualizarEstruturaBOM", "Atualizar Estrutura BOM",  getString(R.string.pagina) + ": " + pagina, NOTIF_ID);
                                    }
                                }
                            });
                        }

                        runOnUiThread( new Runnable() {
                            @Override
                            public void run() {

                                AlertDialog.Builder janelaMensagem = new AlertDialog.Builder(AtualizacaoActivity.this);

                                janelaMensagem.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        dialogInterface.cancel();
                                    }
                                });

                                if (atualizado[0]) {
                                    //Toast.makeText(AtualizacaoActivity.this, "Estrutura de Produtos foi Atualizada com Sucesso!", Toast.LENGTH_LONG).show();
                                    janelaMensagem.setTitle("Estrutura de Produtos");
                                    janelaMensagem.setMessage("Estrutura de Produtos atualizada com sucesso!");
                                }else{
                                    //Toast.makeText(AtualizacaoActivity.this, "Erro ao Atualizar a Estrutura de Produtos!", Toast.LENGTH_LONG).show();
                                    janelaMensagem.setTitle("Estrutura de Produtos");
                                    janelaMensagem.setMessage("Erro ao Atualizar a Estrutura de Produtos!");
                                }

                                btnAtualizarEstruturaBom.setEnabled(true);
                                loadingProgressBar.setVisibility(View.GONE);
                                tvMsgAtualizacao.setVisibility(View.GONE);

                                if (!AtualizacaoActivity.this.isFinishing()) {
                                    janelaMensagem.create().show();
                                }

                            }
                        });
                    }
                });
            }
        });

        janelaEscolha.create().show();


    }
}