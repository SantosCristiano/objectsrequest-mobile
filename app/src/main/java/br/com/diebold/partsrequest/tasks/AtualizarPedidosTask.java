package br.com.diebold.partsrequest.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;

import br.com.diebold.partsrequest.controller.EquipamentoController;
import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.data.dao.EquipamentoBomRepository;
import br.com.diebold.partsrequest.data.dao.model.EquipamentoBomModel;
import br.com.diebold.partsrequest.modelView.EquipamentoBomView;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.utils.DateTime;
import br.com.diebold.partsrequest.utils.DateTimeFormat;
import br.com.diebold.partsrequest.utils.NotificationUtil;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class AtualizarPedidosTask extends
        AsyncTask<String, Object, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private int NOTIF_ID = 102;
    private PedidoController pedidoController;
    private EquipamentoController equipamentoController;
    NotificationUtil notificationUtil;
    UsuarioResponse dadosUsuarioLogado;

    public AtualizarPedidosTask(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        pedidoController = new PedidoController(this.context);
        equipamentoController = new EquipamentoController(this.context);
        notificationUtil = new NotificationUtil(this.context);
        dadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this.context, UsuarioResponse.class, PreferencesUserUtil.PREFS_USER_DATA_LOGGED);
    }


    @Override
    protected Boolean doInBackground(String... strings) {
        if(dadosUsuarioLogado != null) {
            baixarPorTecnico();
        }
        return true;
    }

//    private void baixarPorStatus(String status){
//        notificationUtil.sendNotification("AtualizarPedidosTask", "Baixar Pedidos pelo Status:" + status, "Baixando Pedidos: " + status +".", NOTIF_ID);
//        PedidoView pedidos = pedidoController.obterPedidosPorStatus(status, dadosUsuarioLogado.getTecnico().getIdTecnico());
//
//        if(pedidos != null && pedidos.getPedidos() != null) {
//            incluirENotificar(pedidos.getPedidos());
//        }
//
//    }

    private void baixarPorTecnico(){
        boolean houveErrosInclusao = false;
        PedidoView pedidos = pedidoController.obterDadosPedidoPeloTecnico(dadosUsuarioLogado.getTecnico().getIdTecnico());

        if(pedidos.isSuccess()) {
            if(pedidos.getPedidos() != null && pedidos.getPedidos().size() > 0){
                houveErrosInclusao = incluirENotificar(pedidos.getPedidos());
                incluirEstruturaBOM(pedidos.getPedidos());
                if (!houveErrosInclusao) {
                    confirmarPedidosRecebidos();
                }
            }
        }else{
            notificationUtil.sendNotification("AtualizarPedidosTask", "Baixar Pedidos pelo Tecnico:" + dadosUsuarioLogado.getTecnico().getIdTecnico().toString(), "Erro: " + dadosUsuarioLogado.getTecnico().getIdTecnico().toString() + " - " + pedidos.getMensagem(), NOTIF_ID);
        }
    }

    private void confirmarPedidosRecebidos() {
        pedidoController.confirmarPedidosRecebidosPeloTecnico(dadosUsuarioLogado.getTecnico().getIdTecnico());
    }

    private boolean incluirENotificar(List<PedidoItemView> itens) {
        PedidoView resultado;
        boolean erroInclusao = false;

        if( itens != null && itens.size() > 0) {

            for (int i = 0; i < itens.size(); i++) {
                PedidoView pedidosDB = pedidoController.obterPedidosPorIdPedidoDB(itens.get(i).getIdPedidoWS().toString());

                if(pedidosDB != null && pedidosDB.getPedidos() != null && pedidosDB.getPedidos().size() > 0) {
                    String dataWS = itens.get(i).getDataUltimaAlteracao();
                    String dataDB = pedidosDB.getPedidos().get(0).getDataUltimaAlteracao();

                    Date dateWS = DateTime.StringToDate(dataWS, DateTimeFormat.BRAZILDATEANDTIME.getValue());
                    Date dateDB = DateTime.StringToDate(dataDB, DateTimeFormat.BRAZILDATEANDTIME.getValue());

                    if((dataDB == null && dataDB.equals("")) | (dateDB != null && dateWS.getTime() > dateDB.getTime())) {
                        resultado = pedidoController.incluirPedidoDB(itens.get(i), true);
                        if(!resultado.isSuccess()) {
                            erroInclusao = true;
                        }
                        notificationUtil.sendNotification("AtualizarPedidosTask", "Atualizar Pedido", itens.get(i).getIdPedidoWS() + ": " + resultado.getMensagem(), NOTIF_ID);
                    }
                } else {
                    resultado = pedidoController.incluirPedidoDB(itens.get(i), true);
                    if(!resultado.isSuccess()) {
                        erroInclusao = true;
                    }
                    notificationUtil.sendNotification("AtualizarPedidosTask", "Atualizar Pedido", itens.get(i).getIdPedidoWS() + ": " + resultado.getMensagem(), NOTIF_ID);
                }
            }
        }
        return erroInclusao;
    }

    private void incluirEstruturaBOM(List<PedidoItemView> itens) {
        EquipamentoBomView resultado = null;

        if( itens  != null && itens.size() > 0) {

            for (int i = 0; i < itens.size(); i++) {
                for (int j = 0; j < itens.get(i).getProduto().size(); j++) {
                    // Busca pelo productCode na base SQLite
                    String codigoProduto = itens.get(i).getProduto().get(j).getProdCodigo();
                    EquipamentoBomModel produtoBomDB = EquipamentoBomRepository.getInstance(this.context).obter(codigoProduto);
                    // Se nao encontrar
                    if(produtoBomDB == null) {
                        resultado = equipamentoController.buscarEIncluirEstruturaBomMFS(codigoProduto);

                        if(!resultado.isSuccess()) {
                            notificationUtil.sendNotification("AtualizarPedidosTask", "Atualizar estrutura do produto", resultado.getMensagem(), NOTIF_ID);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
//        if(result)
//            notificationUtil.sendNotification("AtualizarPedidosTask", "Atualizar Pedido", "Pedidos atualizados com sucesso!", NOTIF_ID);
//        else
//            notificationUtil.sendNotification("AtualizarPedidosTask", "Atualizar Pedido", "Erro ao atualizar Pedidos!", NOTIF_ID);
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        //progress.setMessage(values[0].toString());
    }
}
