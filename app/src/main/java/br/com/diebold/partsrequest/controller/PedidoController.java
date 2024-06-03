package br.com.diebold.partsrequest.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.api.ApiServices;
import br.com.diebold.partsrequest.data.api.request.PedidoRequest;
import br.com.diebold.partsrequest.data.api.response.PedidoResponse;
import br.com.diebold.partsrequest.data.api.response.VerificaTarefaResponse;
import br.com.diebold.partsrequest.data.dao.PedidoProdutosRepository;
import br.com.diebold.partsrequest.data.dao.PedidoRepository;
import br.com.diebold.partsrequest.data.dao.model.PedidoModel;
import br.com.diebold.partsrequest.data.dao.model.PedidoProdutosModel;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.modelView.VerificaTarefaView;

public class PedidoController{
    private PedidoRepository pedidoRepository;
    private PedidoProdutosRepository pedidoProdutosRepository;
    private static PedidoController instance;
    private Context context;

    public PedidoController(Context appContext) {
        this.context = appContext;
        pedidoRepository = PedidoRepository.getInstance(appContext);
        pedidoProdutosRepository = PedidoProdutosRepository.getInstance(appContext);
    }


    public static PedidoController getInstance(Context context){
        if (instance == null) {
            instance = new PedidoController(context);
        }
        return instance;
    }

    public PedidoView obterPedidosPorStatus(String status, Integer tecnico) {

        PedidoView pedidoView = new PedidoView();

        if(status != null && !status.equals("") && tecnico != null && tecnico > 0){
            status = status.trim();

            PedidoResponse response = null;

            try {
                response = ApiServices.getInstance(context).obterPedidosPorStatus(status, tecnico);

                if(response.isSuccess()) {
                    pedidoView.setSuccess(true);
                    pedidoView.setMensagem("Pedidos retornados com sucesso!");

                    if(response.getPedidos() != null && response.getPedidos().size() > 0 ) {
                        List<PedidoItemView> piv = new ArrayList<PedidoItemView>();

                        for (int i = 0; i < response.getPedidos().size(); i++) {
                            piv.add(new PedidoItemView(response.getPedidos().get(i)));

                        }
                        pedidoView.setPedidos(piv);
                    }

                }else{
                    pedidoView.setSuccess(false);
                    pedidoView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                pedidoView.setSuccess(false);
                pedidoView.setMensagem(e.getMessage());
            }
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;

    }

    public PedidoView obterPedidosPorStatusDB(String[] status, Integer tecnico) {
        PedidoView pedidoView = new PedidoView();

        List<PedidoModel> listaPedidos = this.pedidoRepository.obterListaPorStatus(status, tecnico);

        if(listaPedidos.size() > 0 ) {
            pedidoView.setSuccess(true);
            pedidoView.setMensagem("Pedidos retornados com sucesso!");
            List<PedidoItemView> piv = new ArrayList<PedidoItemView>();

            for (int i = 0; i < listaPedidos.size(); i++) {
                List<PedidoProdutosModel> pedidoProdutos = this.pedidoProdutosRepository.obterLista(listaPedidos.get(i).getId());

                listaPedidos.get(i).setProduto(pedidoProdutos);

                piv.add(new PedidoItemView(listaPedidos.get(i)));
            }
            pedidoView.setPedidos(piv);
        } else {
            pedidoView = null;
        }

        return pedidoView;
    }

    public PedidoView obterPedidosDB(Integer tecnico) {
        PedidoView pedidoView = new PedidoView();

        List<PedidoModel> listaPedidos = this.pedidoRepository.obterLista(tecnico);

        if(listaPedidos.size() > 0 ) {
            pedidoView.setSuccess(true);
            pedidoView.setMensagem("Pedidos retornados com sucesso!");
            List<PedidoItemView> piv = new ArrayList<PedidoItemView>();

            for (int i = 0; i < listaPedidos.size(); i++) {
                List<PedidoProdutosModel> pedidoProdutos = this.pedidoProdutosRepository.obterLista(listaPedidos.get(i).getId());

                listaPedidos.get(i).setProduto(pedidoProdutos);

                piv.add(new PedidoItemView(listaPedidos.get(i)));
            }
            pedidoView.setPedidos(piv);
        } else {
            pedidoView = null;
        }

        return pedidoView;
    }

    public PedidoView obterPedidoPorTipo(String tipo, String tecnico) {
        PedidoView pedidoView = new PedidoView();

        if(tipo != null && !tipo.equals("") && tecnico != null && !tecnico.equals("")){
            tipo = tipo.trim();
            tecnico = tecnico.trim();

            PedidoResponse response = null;

            try {
                response = ApiServices.getInstance(context).obterPedidosPorTipo(tipo, tecnico);

                if(response.isSuccess()) {
                    pedidoView.setSuccess(true);
                    pedidoView.setMensagem("Pedidos retornados com sucesso!");
                    //.setPedidos(response);
                }else{
                    pedidoView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                pedidoView.setSuccess(false);
                pedidoView.setMensagem(e.getMessage());

            }
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;

    }

    public PedidoView obterPedidosPorCodigo(String codigo, String tecnico) {
        PedidoView pedidoView = new PedidoView();

        if(codigo != null && !codigo.equals("") && tecnico != null && !tecnico.equals("")){
            codigo = codigo.trim();
            tecnico = tecnico.trim();

            PedidoResponse response = null;

            try {
                response = ApiServices.getInstance(context).obterPedidosPorCodigo(codigo, tecnico);

                if(response.isSuccess()) {
                    pedidoView.setSuccess(true);
                    pedidoView.setMensagem("Pedidos retornados com sucesso!");

                    if(response.getPedidos() != null && response.getPedidos().size() > 0 ) {
                        List<PedidoItemView> piv = new ArrayList<PedidoItemView>();

                        for (int i = 0; i < response.getPedidos().size(); i++) {
                            piv.add(new PedidoItemView(response.getPedidos().get(i)));
                        }
                        pedidoView.setPedidos(piv);
                    }
                }else{
                    pedidoView.setSuccess(false);
                    pedidoView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                pedidoView.setSuccess(false);
                pedidoView.setMensagem(e.getMessage());

            }
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;

    }

    public PedidoView obterPedidosPorCodigoDB(int id) {
        PedidoView pedidoView = new PedidoView();

        PedidoModel pedido = this.pedidoRepository.obter(id);

        if(pedido != null && pedido.getId() != 0) {
            pedidoView.setSuccess(true);
            pedidoView.setMensagem("Pedidos retornados com sucesso!");
            List<PedidoItemView> piv = new ArrayList<PedidoItemView>();

            List<PedidoProdutosModel> pedidoProdutos = this.pedidoProdutosRepository.obterLista(pedido.getId());

            pedido.setProduto(pedidoProdutos);

            piv.add(new PedidoItemView(pedido));

            pedidoView.setPedidos(piv);
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;
    }

    public PedidoView obterPedidosPorIdPedidoDB(String idPedidoWS) {
        PedidoView pedidoView = new PedidoView();

        PedidoModel pedido = this.pedidoRepository.obter(idPedidoWS);

        if(pedido != null && pedido.getId() != 0) {
            pedidoView.setSuccess(true);
            pedidoView.setMensagem("Pedidos retornados com sucesso!");
            List<PedidoItemView> piv = new ArrayList<PedidoItemView>();

            List<PedidoProdutosModel> pedidoProdutos = this.pedidoProdutosRepository.obterLista(pedido.getId());

            pedido.setProduto(pedidoProdutos);

            piv.add(new PedidoItemView(pedido));

            pedidoView.setPedidos(piv);
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;
    }

    public VerificaTarefaView obterDadosPedidoPelaTask(String tarefa) {
        VerificaTarefaView verificaTarefa = new VerificaTarefaView();

        if(tarefa != null && !tarefa.equals("")){

            VerificaTarefaResponse response = null;

            try {
                response = ApiServices.getInstance(context).obterDadosPedidoPelaTask(tarefa);

                if(response.isSuccess()) {
                    verificaTarefa.setSuccess(true);
                    verificaTarefa = new VerificaTarefaView(response);
                    verificaTarefa.setMensagem("Tarefa verificada com sucesso!");
                }else{
                    verificaTarefa.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                verificaTarefa.setSuccess(false);
                verificaTarefa.setMensagem(e.getMessage());

            }
        } else {
            verificaTarefa.setSuccess(false);
            verificaTarefa.setMensagem("Campos nulos ou vázios");
        }

        return verificaTarefa;

    }

    public PedidoView obterDadosPedidoPeloSite(String site) {
        PedidoView pedidoView = new PedidoView();

        if(site != null && !site.equals("")){

            PedidoResponse response = null;

            try {
                response = ApiServices.getInstance(context).obterDadosPedidoPeloSite(site);

                if(response.isSuccess()) {
                    pedidoView.setSuccess(true);
                    pedidoView.setMensagem("Pedidos retornados com sucesso!");

                    if(response.getPedidos() != null && response.getPedidos().size() > 0 ) {
                        List<PedidoItemView> piv = new ArrayList<PedidoItemView>();

                        for (int i = 0; i < response.getPedidos().size(); i++) {
                            piv.add(new PedidoItemView(response.getPedidos().get(i)));
                        }
                        pedidoView.setPedidos(piv);
                    }
                }else{
                    pedidoView.setSuccess(false);
                    pedidoView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                pedidoView.setSuccess(false);
                pedidoView.setMensagem(e.getMessage());

            }
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;

    }

    public PedidoView obterDadosPedidoPeloTecnico(Integer tecnico) {
        boolean todos;
        PedidoView pedidoView = new PedidoView();

        if(tecnico != null && !tecnico.equals("")){

            PedidoResponse response = null;

            try {
                todos = PedidoRepository.getInstance(context).obterLista(tecnico).size() == 0;

                response = ApiServices.getInstance(context).obterDadosPedidoPeloTecnico(tecnico, todos);

                if(response.isSuccess()) {
                    pedidoView.setSuccess(true);
                    pedidoView.setMensagem("Pedido obtido com sucesso!");
                    if(response.getPedidos() != null && response.getPedidos().size() > 0 ) {
                        List<PedidoItemView> piv = new ArrayList<PedidoItemView>();

                        for (int i = 0; i < response.getPedidos().size(); i++) {
                            piv.add(new PedidoItemView(response.getPedidos().get(i)));
                        }
                        pedidoView.setPedidos(piv);
                    }
                }else{
                    pedidoView.setSuccess(false);
                    pedidoView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                pedidoView.setSuccess(false);
                pedidoView.setMensagem(e.getMessage());

            }
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;

    }

    public PedidoView incluirPedido(PedidoItemView pedido) {
        PedidoView pedidoView = new PedidoView();

        if(pedido != null){

            PedidoResponse response = null;

            PedidoRequest pedidoRequest = new PedidoRequest(pedido);

            try {
                pedidoRequest.setStPstatusStatus("Aberto");
                response = ApiServices.getInstance(context).incluirPedido(pedidoRequest);

                if(response.isSuccess()) {
                    pedidoView.setSuccess(true);
                    pedidoView.setMensagem("Pedido incluído com sucesso!");
                    pedidoView.setIdPedido(response.getIdPedidoWS());
                    //pedidoView.setPedido(response);
                }else{
                    pedidoView.setSuccess(false);
                    pedidoView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                pedidoView.setSuccess(false);
                pedidoView.setMensagem(e.getMessage());

            }
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;

    }

    public PedidoView alterarPedidoDB(PedidoItemView pedidoItemView) {
        PedidoView pv = new PedidoView();
        PedidoModel pedido = new PedidoModel(pedidoItemView);

        if(pedidoItemView != null && pedidoItemView.getId() != 0) {

            boolean pedidoAtualizado = this.pedidoRepository.atualizar(pedido);

            if(pedidoAtualizado == true) {
                pedidoProdutosRepository.excluirProdutosDB(pedido.getId());
                for(int i =0; i < pedidoItemView.getProduto().size(); i++) {

                    PedidoProdutosModel produto = new PedidoProdutosModel(pedidoItemView.getProduto().get(i));
                    produto.setIdPedidoDB(pedidoItemView.getId());

                    long inserirProduto =  this.pedidoProdutosRepository.inserir(produto);
                }
                pv.setSuccess(true);
                pv.setMensagem("Pedido atualizado com sucesso!");
            }else{
                pv.setSuccess(false);
                pv.setMensagem("Erro ao atualizar Pedido!");
            }
        } else {
            pv.setSuccess(false);
            pv.setMensagem("Campos nulos ou vázios");
        }

        return pv;

    }

    public PedidoView alterarPedidoStatusWS(PedidoItemView pedido) {
        PedidoView pedidoView = new PedidoView();

        if(pedido != null && pedido.getId() != 0){

            PedidoResponse response = null;

            PedidoRequest pedidoRequest = new PedidoRequest(pedido);

            try {
                response = ApiServices.getInstance(context).alterarPedidoStatus(pedidoRequest);

                if(response.isSuccess()) {
                    pedidoView.setSuccess(true);
                    pedidoView.setMensagem("Status do Pedido alterado com sucesso!");
                }else{
                    pedidoView.setSuccess(false);
                    pedidoView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                pedidoView.setSuccess(false);
                pedidoView.setMensagem(e.getMessage());

            }
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;

    }

    public PedidoView alterarPedidoWS(PedidoItemView pedido) {
        PedidoView pedidoView = new PedidoView();

        if(pedido != null){

            PedidoResponse response = null;

            PedidoRequest pedidoRequest = new PedidoRequest(pedido);

            try {
                response = ApiServices.getInstance(context).alterarPedido(pedidoRequest);

                if(response.isSuccess()) {
                    pedidoView.setSuccess(true);
                    pedidoView.setMensagem("Pedido alterado com sucesso!");

                    List<PedidoItemView> pedidosViewList = new ArrayList<>();

                    if(response.getPedidos() != null) {
                        for(int i =0; i < response.getPedidos().size() ; i++) {
                            pedidosViewList.add(new PedidoItemView(response.getPedidos().get(i)));
                        }
                    }

                    pedidoView.setPedidos(pedidosViewList);

                }else{
                    pedidoView.setSuccess(false);
                    pedidoView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                pedidoView.setSuccess(false);
                pedidoView.setMensagem(e.getMessage());

            }
        } else {
            pedidoView.setSuccess(false);
            pedidoView.setMensagem("Campos nulos ou vázios");
        }

        return pedidoView;

    }

    public PedidoView incluirPedidoDB(PedidoItemView pedidoView,  boolean atualizarSeNecessario) {

        PedidoView pv = new PedidoView();

        if(pedidoView != null){

                long pedidoInserido = inserirPedidoDB(pedidoView,  atualizarSeNecessario);

                if(pedidoInserido > 0) {
                    pv.setSuccess(true);
                    pv.setMensagem("Pedido inserido com sucesso!");
                }else{
                    pv.setSuccess(false);
                    pv.setMensagem("Erro ao inserir Pedido!");
                }
        } else {
            pv.setSuccess(false);
            pv.setMensagem("Campos nulos ou vázios");
        }

        return pv;

    }

    public PedidoView atualizarIdPedidoDB(Integer idPedidoDB, Integer idPedidoWS) {
        PedidoView pv = new PedidoView();

        boolean pedidoAtualizado = this.pedidoRepository.atualizar("id", idPedidoDB.toString(), "idPedidoWS", idPedidoWS.toString());

        if(pedidoAtualizado == true) {
            pv.setSuccess(true);
            pv.setMensagem("Pedido atualizado com sucesso!");
        }else{
            pv.setSuccess(false);
            pv.setMensagem("Erro ao atualizar Pedido!");
        }

        return pv;

    }

    public PedidoView alterarPedidoStatusDB(Integer idPedidoDB, String statusPedido) {
        PedidoView pv = new PedidoView();

        boolean pedidoAtualizado = this.pedidoRepository.atualizar("id", idPedidoDB.toString(), "stPstatusStatus", statusPedido);

        if(pedidoAtualizado == true) {
            pv.setSuccess(true);
            pv.setMensagem("Pedido atualizado com sucesso!");
        }else{
            pv.setSuccess(false);
            pv.setMensagem("Erro ao atualizar Pedido!");
        }

        return pv;

    }

    public PedidoView alterarPedidoStatusDB(PedidoItemView pedidoView) {
        PedidoView pv = new PedidoView();
        PedidoModel pedido = new PedidoModel(pedidoView);

        if(pedidoView != null && pedidoView.getId() != 0){

            boolean pedidoAtualizado = this.pedidoRepository.atualizar(pedido);

            if(pedidoAtualizado == true) {
                pv.setSuccess(true);
                pv.setMensagem("Pedido atualizado com sucesso!");
            }else{
                pv.setSuccess(false);
                pv.setMensagem("Erro ao atualizar Pedido!");
            }
        } else {
            pv.setSuccess(false);
            pv.setMensagem("Campos nulos ou vázios");
        }

        return pv;

    }

    public boolean excluirPedido(long idPedidoWS) {

        return this.pedidoRepository.excluir(idPedidoWS);

    }

    private long inserirPedidoDB(PedidoItemView pedidoItemView, boolean atualizarSeNecessario) {

        PedidoModel pedido = new PedidoModel(pedidoItemView);
        long inserirPedido =  atualizarSeNecessario ? this.pedidoRepository.inserirOuAtualizar(pedido) : this.pedidoRepository.inserir(pedido);

        if(pedidoItemView.getProduto() != null && pedidoItemView.getProduto().size() > 0) {

            for(int i =0; i < pedidoItemView.getProduto().size(); i++) {
                Integer idPedidoDB = null;

                if(pedido.getIdPedidoWS() != null) {

                    PedidoModel pedidoModel = this.pedidoRepository.obter("idPedidoWS",pedido.getIdPedidoWS().toString());

                    if(pedidoModel != null) {
                        idPedidoDB = pedidoModel.getId();
                    }
                } else {
                    idPedidoDB = Integer.parseInt(String.valueOf(inserirPedido));
                }

                PedidoProdutosModel produto = new PedidoProdutosModel(pedidoItemView.getProduto().get(i));

                produto.setIdPedidoDB(idPedidoDB);

                PedidoProdutosModel pedidoProdutosModel = this.pedidoProdutosRepository.obter("idPedidoDB", produto.getIdPedidoDB().toString(), "itemCodigo", produto.getItemCodigo());

                if(pedidoProdutosModel != null) {
                    produto.setId(pedidoProdutosModel.getId());
                } else {
                    atualizarSeNecessario = false;
                }

                long inserirProduto =  atualizarSeNecessario ?  this.pedidoProdutosRepository.inserirOuAtualizar(produto) :  this.pedidoProdutosRepository.inserir(produto);
            }
        }

        return inserirPedido;

    }

    public void deleteDB() {
//        context.deleteDatabase("PartsRequestdb.db");

        File data = Environment.getDataDirectory();
        String currentDBPath = "/data/br.com.diebold.partsrequest/databases/" + "PartsRequestdb.db";
        File currentDB = new File(data, currentDBPath);
        boolean deleted = SQLiteDatabase.deleteDatabase(currentDB);
    }

    public void confirmarPedidosRecebidosPeloTecnico(Integer tecnico) {
        try {
            ApiServices.getInstance(context).confirmarPedidosRecebidos(tecnico);
        } catch (Exception e) {

        }

    }
}



