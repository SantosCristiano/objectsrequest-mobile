package br.com.diebold.partsrequest.ui.status;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.data.api.response.PedidoItemResponse;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.PedidoProdutosView;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.ui.solicitacao.DevolucaoActivity;
import br.com.diebold.partsrequest.ui.solicitacao.EdicaoActivity;
import br.com.diebold.partsrequest.ui.solicitacao.SolicitacaoActivity;
import br.com.diebold.partsrequest.ui.status.adapters.AdapterDetalhaPedido;
import br.com.diebold.partsrequest.ui.status.adapters.AdapterItensDoCarrinho;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class DetalhesActivity extends AppCompatActivity {
    private ListView lsvPedido;
    private AdapterDetalhaPedido adapterDetalhaPedido;
    private AppCompatActivity context;
    public Integer idPedido;
    private PedidoView pedido;
    UsuarioResponse DadosUsuarioLogado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        this.setTitle(getString(R.string.activity_detalhes));

        context = this;

        DadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this, UsuarioResponse.class,  PreferencesUserUtil.PREFS_USER_DATA_LOGGED);

        Intent intent = getIntent();

        if(intent != null){
            Bundle params = intent.getExtras();

            if(params != null){
                idPedido = Integer.parseInt(params.getString("idPedido"));

                Executors.newSingleThreadExecutor().submit (new Runnable() {
                    //runOnUiThread(new Thread(new Runnable() {
                    @Override
                    public void run() {

//                        pedido = PedidoController.getInstance(context)
//                                .obterPedidosPorCodigo(idPedido, "65386");

                        pedido = PedidoController.getInstance(context)
                                .obterPedidosPorCodigoDB(idPedido);

                        if (pedido.isSuccess()) {

                            runOnUiThread( new Runnable() {
                                @Override
                                public void run() {
                                    lsvPedido = (ListView) findViewById(R.id.lsvPedido);

                                    adapterDetalhaPedido = new AdapterDetalhaPedido(context, pedido.getPedidos());

                                    lsvPedido.setAdapter(adapterDetalhaPedido);
                                }

                            });

                        }
                    }
                });

            }
        }

    }

    public void editarPedido(View view) {

        AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(context);

        janelaEscolha.setTitle("Edição de Pedido:");
        janelaEscolha.setMessage("Deseja realmente editar este pedido?");

        janelaEscolha.setNeutralButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });

        janelaEscolha.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();

                //Toast.makeText(context, "Tela de Edição!", Toast.LENGTH_LONG).show();

                Bundle params = new Bundle();

                params.putString("idPedido", pedido.getPedidos().get(0).getId().toString());

                params.putString("localizacao", pedido.getPedidos().get(0).getNomeLocalizacao());
                params.putString("tipo", pedido.getPedidos().get(0).getTipoPedido());

                params.putString("tarefa", pedido.getPedidos().get(0).getIdTarefa().toString());
                params.putString("site", pedido.getPedidos().get(0).getIdSite());

                params.putString("dataEntrega", pedido.getPedidos().get(0).getDtEntrega());
                params.putString("transporte", pedido.getPedidos().get(0).getTransporte().toString());

                params.putString("endereco",  pedido.getPedidos().get(0).getEaLogradouro());
                params.putString("bairro", pedido.getPedidos().get(0).getEaBairro());
                params.putString("cep", pedido.getPedidos().get(0).getEaCep());
                params.putString("cidade", pedido.getPedidos().get(0).getEaCidade());
                params.putString("numero", pedido.getPedidos().get(0).getEaNum());

                params.putString("equipamento", pedido.getPedidos().get(0).getDescricaoDoEquipamento());

                Intent intent = new Intent(context, EdicaoActivity.class);
                intent.putExtras(params);

                startActivity(intent);
            }
        });

        janelaEscolha.create().show();
    }

    public void cancelarPedido(View view) {

        AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(context);

        janelaEscolha.setTitle("Cancelamento de Pedido:");
        janelaEscolha.setMessage("Deseja realmente cancelar este pedido?");

        janelaEscolha.setNeutralButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });

        janelaEscolha.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();

                if(pedido.getPedidos() != null && pedido.getPedidos().size() > 0 ) {

                    Executors.newSingleThreadExecutor().submit (new Runnable() {
                        @Override
                        public void run() {

                            PedidoView pedidoAtualizadoDB;
                            PedidoView pedidoAtualizadoWS;

                            List<PedidoItemView> piv = pedido.getPedidos();
                            PedidoItemView pedidoItemView = null;

                            for (int i = 0; i < pedido.getPedidos().size(); i++) {
                                piv.get(i).setStPstatusStatus("Cancelado");
                                pedido.setPedidos(piv);
                                pedidoItemView = pedido.getPedidos().get(i);
                            }

                            pedidoAtualizadoWS = PedidoController.getInstance(context)
                                    .alterarPedidoStatusWS(pedidoItemView);

                            if(pedidoAtualizadoWS.isSuccess()){

                                pedidoAtualizadoDB = PedidoController.getInstance(context)
                                        .alterarPedidoStatusDB(pedidoItemView);

                                if(pedidoAtualizadoDB.isSuccess()){
                                    runOnUiThread( new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "Pedido " + idPedido + " Cancelado com Sucesso!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(context, CanceladosActivity.class);
                                            startActivity(intent);
                                        }

                                    });
                                } else {
                                    runOnUiThread( new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "Erro ao Cancelar Pedido " + idPedido + "!", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                            } else {
                                runOnUiThread( new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, "Erro ao Cancelar Pedido " + idPedido + "!", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        janelaEscolha.create().show();
    }

    public void finalizarPedido(View view) {

        AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(context);

        janelaEscolha.setTitle("Finalização de Pedido:");
        janelaEscolha.setMessage("Deseja realmente finalizar este pedido?");

        janelaEscolha.setNeutralButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });

        janelaEscolha.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();

                if(pedido.getPedidos() != null && pedido.getPedidos().size() > 0 ) {

                    Executors.newSingleThreadExecutor().submit (new Runnable() {
                        @Override
                        public void run() {

                            PedidoView pedidoAtualizadoDB;
                            PedidoView pedidoAtualizadoWS;

                            List<PedidoItemView> piv = pedido.getPedidos();
                            PedidoItemView pedidoItemView = null;

                            for (int i = 0; i < pedido.getPedidos().size(); i++) {
                                piv.get(i).setStPstatusStatus("Finalizado");
                                pedido.setPedidos(piv);
                                pedidoItemView = pedido.getPedidos().get(i);
                            }

                            pedidoAtualizadoWS = PedidoController.getInstance(context)
                                    .alterarPedidoStatusWS(pedidoItemView);

                            if(pedidoAtualizadoWS.isSuccess()){

                                pedidoAtualizadoDB = PedidoController.getInstance(context)
                                        .alterarPedidoStatusDB(pedidoItemView);

                                if(pedidoAtualizadoDB.isSuccess()){
                                    runOnUiThread( new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "Pedido " + idPedido + " Finalizado com Sucesso!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(context, FinalizadosActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                } else {
                                    runOnUiThread( new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "Erro ao Finalizar Pedido " + idPedido + "!", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                            } else {
                                runOnUiThread( new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, "Erro ao Finalizar Pedido " + idPedido + "!", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        janelaEscolha.create().show();
    }
}