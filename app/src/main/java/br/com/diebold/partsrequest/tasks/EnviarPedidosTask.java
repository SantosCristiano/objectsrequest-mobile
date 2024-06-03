package br.com.diebold.partsrequest.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.utils.NotificationUtil;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class EnviarPedidosTask extends
        AsyncTask<String, Object, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private int NOTIF_ID = 103;
    private PedidoController pedidoController;
    NotificationUtil notificationUtil;
    UsuarioResponse dadosUsuarioLogado;

    public EnviarPedidosTask(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        //Logar Inicio

        pedidoController = new PedidoController(this.context);
        notificationUtil = new NotificationUtil(this.context);
        dadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this.context, UsuarioResponse.class, PreferencesUserUtil.PREFS_USER_DATA_LOGGED);

    }


    @Override
    protected Boolean doInBackground(String... strings) {

        if(dadosUsuarioLogado != null) {
            enviarPorStatus();
        }

        return true;
    }



    private void enviarPorStatus(){

        //notificationUtil.sendNotification("EnviarPedidosTask", "Enviar Pedidos pelo Status!", "Enviando Pedidos! " + ".", NOTIF_ID);
        PedidoView pedidos = pedidoController.obterPedidosPorStatusDB(new String[]{"Pendente", "Erro"}, dadosUsuarioLogado.getTecnico().getIdTecnico());

        if(pedidos != null && pedidos.getPedidos() != null) {
            enviarENotificar(pedidos.getPedidos());
        }


    }

    private void enviarENotificar(List<PedidoItemView> itens) {

        PedidoView resultado = new PedidoView();

        if( itens  != null && itens.size() > 0) {

            for (int i = 0; i < itens.size(); i++) {

                //notificationUtil.sendNotification("EnviarPedidosTask", "Enviar Pedido", "Enviando Pedido " + itens.get(i).getIdPedidoWS(), NOTIF_ID);

                resultado = pedidoController.incluirPedido(itens.get(i));

                if(resultado.isSuccess()){

                    PedidoView pedidoIdAlterado = PedidoController.getInstance(context)

                            .atualizarIdPedidoDB(itens.get(i).getId(), resultado.getIdPedidoWS());

                    if(pedidoIdAlterado.isSuccess()) {

                        PedidoView pedidoStatusAlterado = PedidoController.getInstance(context)
                                .alterarPedidoStatusDB(itens.get(i).getId(), "Aberto");
                    } else {
                        PedidoView pedidoStatusAlterado = PedidoController.getInstance(context)
                                .alterarPedidoStatusDB(itens.get(i).getId(), "Erro");
                    }
                } else {
                    PedidoView pedidoStatusAlterado = PedidoController.getInstance(context)
                            .alterarPedidoStatusDB(itens.get(i).getId(), "Erro");
                }

                String mensagem = itens.get(i).getIdPedidoWS() + ": " + resultado.getMensagem();
                if(itens.get(i).getIdPedidoWS() == null || itens.get(i).getIdPedidoWS() == 0) {
                    mensagem = "" + resultado.getMensagem();
                }
                notificationUtil.sendNotification("EnviarPedidosTask", "Enviar Pedido", mensagem, NOTIF_ID);
            }

        }

    }


    @Override
    protected void onPostExecute(Boolean result) {
//        if(result)
//            notificationUtil.sendNotification("EnviarPedidosTask", "Enviar Pedido", "Pedidos enviados com sucesso!", NOTIF_ID);
//        else
//            notificationUtil.sendNotification("EnviarPedidosTask", "Enviar Pedido", "Erro ao enviar Pedidos!", NOTIF_ID);
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);

        //progress.setMessage(values[0].toString());

    }

}
