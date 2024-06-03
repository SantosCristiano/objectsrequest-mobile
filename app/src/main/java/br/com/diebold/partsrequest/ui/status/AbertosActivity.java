package br.com.diebold.partsrequest.ui.status;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.BaseActivity;
import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.ui.solicitacao.SolicitacaoActivity;
import br.com.diebold.partsrequest.ui.status.adapters.AdapterListaPedido;
import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class AbertosActivity extends BaseActivity {

    private ListView lsvPedidos;
    private List<PedidoView> pedidoView;
    //private List<Pedido> pedidoList;
    private AdapterListaPedido adapterListaPedido;
    private PedidoController pedidoController;
    private AppCompatActivity context;

    UsuarioResponse DadosUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abertos);

        this.setTitle(getString(R.string.activity_abertos));

        context = this;

        DadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this, UsuarioResponse.class,  PreferencesUserUtil.PREFS_USER_DATA_LOGGED);

        Executors.newSingleThreadExecutor().submit (new Runnable() {
        //runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {

//                PedidoView pedido = PedidoController.getInstance(context)
//                        .obterPedidosPorStatus("Aberto", "65386");

                PedidoView pedidos = PedidoController.getInstance(context)
                        .obterPedidosDB(DadosUsuarioLogado.getTecnico().getIdTecnico());

                PedidoView pedidosAbertos = new PedidoView();

                if(pedidos != null) {
                    List<PedidoItemView> pedidosPendentesViewList = new ArrayList<>();
                    List<PedidoItemView> pedidosAbertosViewList = new ArrayList<>();
                    List<PedidoItemView> pedidosEnviadosViewList = new ArrayList<>();
                    List<PedidoItemView> pedidosFinalizadosViewList = new ArrayList<>();
                    List<PedidoItemView> pedidosCanceladosViewList = new ArrayList<>();
                    List<PedidoItemView> pedidosErrosViewList = new ArrayList<>();

                    for(int i =0; i < pedidos.getPedidos().size() ; i++) {
                        if(pedidos.getPedidos().get(i).getStPstatusStatus() != null) {
                            switch(pedidos.getPedidos().get(i).getStPstatusStatus()) {
                                case "Pendente":
                                    pedidosPendentesViewList.add(pedidos.getPedidos().get(i));
                                    break;
                                case "Aberto":
                                    pedidosAbertosViewList.add(pedidos.getPedidos().get(i));
                                    break;
                                case "Em Transporte":
                                    pedidosEnviadosViewList.add(pedidos.getPedidos().get(i));
                                    break;
                                case "Finalizado":
                                    pedidosFinalizadosViewList.add(pedidos.getPedidos().get(i));
                                    break;
                                case "Cancelado":
                                    pedidosCanceladosViewList.add(pedidos.getPedidos().get(i));
                                    break;
                                case "Erro":
                                    pedidosErrosViewList.add(pedidos.getPedidos().get(i));
                                    break;
                                default:
                                    pedidosAbertosViewList.add(pedidos.getPedidos().get(i));
                                    break;
                            }
                        }
                    }
                    pedidosAbertos.setPedidos(pedidosAbertosViewList);
                    pedidosAbertos.setSuccess(true);
                }

                if (pedidosAbertos.isSuccess()) {

                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            lsvPedidos = (ListView) findViewById(R.id.lsvPedidos);

                            adapterListaPedido = new AdapterListaPedido(context, pedidosAbertos.getPedidos());

                            lsvPedidos.setAdapter(adapterListaPedido);
                        }

                    });

                }
            }
        });


//        this.lsvPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Pedido pedidoSelecionado = (Pedido) adapterListaPedido.getItem(position);
//
//                //Toast.makeText(AbertosActivity.this, "Pedido: " + pedidoSelecionado.getNome(), Toast.LENGTH_LONG).show();
//
//                AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(AbertosActivity.this);
//
//                janelaEscolha.setTitle("Escolha:");
//                janelaEscolha.setMessage("O que deseja fazer com o pedido " + pedidoSelecionado.getNome() + "?");
//
//                janelaEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        dialogInterface.cancel();
//                    }
//                });
//
//                janelaEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//
//                        // Excluir o pedido
//                        boolean excluiu = pedidoController.excluirPedidoController(pedidoSelecionado.getId());
//
//                        dialogInterface.cancel();
//
//                        if(excluiu == true ){
//
//                            adapterListaPedido.removerPedido(position);
//
//                            Toast.makeText(AbertosActivity.this, "Pedido excluído com sucesso!", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(AbertosActivity.this, "Ocorreu um erro ao excluir pedido!", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                });
//
//                janelaEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//
//                        //dialogInterface.cancel();
//
//                        Bundle bundleDadosPedido = new Bundle();
//
//                        bundleDadosPedido.putLong("id_pedido", pedidoSelecionado.getId());
//                        bundleDadosPedido.putString("nome_pedido", pedidoSelecionado.getNome());
//                        bundleDadosPedido.putDouble("preco_pedido", pedidoSelecionado.getPreco());
//                        bundleDadosPedido.putInt("estoque_pedido", pedidoSelecionado.getQuantidadeEmEstoque());
//
//                        //Intent intentEditarPedidos = new Intent(AbertosActivity.this, EditarPedidoActivity.class);
//                        //intentEditarPedidos.putExtras(bundleDadosPedido);
//                        //startActivity(intentEditarPedidos);
//                    }
//                });
//
//                janelaEscolha.create().show();
//            }
//        });
    }

//    // Executa o evento de click no botão atualizar
//    public void EventAtualizarPedidos(View view) {
//        //chamar o update no adapter pedidos
//        this.adapterListaPedido.atualizar(this.pedidoController.getListaPedidosController());
//
//    }
}