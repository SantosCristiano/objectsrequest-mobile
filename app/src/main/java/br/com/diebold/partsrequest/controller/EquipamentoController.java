package br.com.diebold.partsrequest.controller;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.api.ApiServices;
import br.com.diebold.partsrequest.data.api.response.EquipamentoBomResponse;
import br.com.diebold.partsrequest.data.api.response.EquipamentoResponse;
import br.com.diebold.partsrequest.data.api.response.TarefaResponse;
import br.com.diebold.partsrequest.data.dao.EquipamentoBomRepository;
import br.com.diebold.partsrequest.data.dao.FilialRepository;
import br.com.diebold.partsrequest.data.dao.SiteRepository;
import br.com.diebold.partsrequest.data.dao.TarefaFilialRepository;
import br.com.diebold.partsrequest.data.dao.TarefaRepository;
import br.com.diebold.partsrequest.data.dao.TarefaSiteRepository;
import br.com.diebold.partsrequest.data.dao.model.EquipamentoBomModel;
import br.com.diebold.partsrequest.data.dao.model.EquipamentoModel;
import br.com.diebold.partsrequest.data.dao.model.FilialModel;
import br.com.diebold.partsrequest.data.dao.model.SiteModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaFilialModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaSiteModel;
import br.com.diebold.partsrequest.modelView.EquipamentoBomItemView;
import br.com.diebold.partsrequest.modelView.EquipamentoBomView;
import br.com.diebold.partsrequest.modelView.EquipamentoItemView;
import br.com.diebold.partsrequest.modelView.EquipamentoView;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.TarefaFilialView;
import br.com.diebold.partsrequest.modelView.TarefaItemView;
import br.com.diebold.partsrequest.modelView.TarefaSiteView;
import br.com.diebold.partsrequest.modelView.TarefaView;

public class EquipamentoController {
    private static EquipamentoController instance;
    private Context context;
    private EquipamentoBomRepository equipamentoBomRepository;


    public EquipamentoController(Context context) {
        this.context = context;
        equipamentoBomRepository = EquipamentoBomRepository.getInstance(context);
    }

    public static EquipamentoController getInstance(AppCompatActivity context) {
        if (instance == null) {
            instance = new EquipamentoController(context);
        }
        return instance;
    }

    public EquipamentoView obterEquipamentosWS() {
        EquipamentoView equipamentoView = new EquipamentoView();

            EquipamentoResponse response = null;

            try {
                response = ApiServices.getInstance(context).obterEquipamentos();

                if(response.isSuccess()) {
                    equipamentoView.setSuccess(true);
                    equipamentoView.setMensagem("Equipamentos retornados com sucesso!");

                    List<EquipamentoItemView> equipamentoItemView = new ArrayList<>();

                    if(response.getEquipamentos() != null) {
                        for(int i =0; i < response.getEquipamentos().size() ; i++) {
                            equipamentoItemView.add(new EquipamentoItemView(response.getEquipamentos().get(i)));
                            //this.inserirEquipamentosDB(new EquipamentoItemView(response.getEquipamentos().get(i)));
                        }
                    }

                    equipamentoView.setEquipamentos(equipamentoItemView);

                }else{
                    equipamentoView.setSuccess(false);
                    equipamentoView.setMensagem(response.getMessage());
                }
            } catch (IOException e) {
                equipamentoView.setSuccess(false);
                equipamentoView.setMensagem("EquipamentoControler.obterEquipamentosWS - " +  "Erro ao obter equipamento: " + e.getMessage());
            }

        return equipamentoView;

    }

    public List<String> obterEquipamentosDB() {

        List<String> listaEquipamentos = this.equipamentoBomRepository.obterLista();

        return listaEquipamentos;
    }

//    public long inserirEquipamentosDB(EquipamentoItemView equipamentoItemView) {
//
//        EquipamentoModel equipamento = new EquipamentoModel(equipamentoItemView);
//        long atualizarEquipamento = this.equipamentoRepository.inserirOuAtualizar(equipamento);
//
//        return atualizarEquipamento;
//
//    }

    public EquipamentoBomView buscarEIncluirEstruturaBomMFS(String productCode) {

        // Ir no Serviço buscar os dados
        EquipamentoBomView equipamentoBomView = new EquipamentoBomView();

        EquipamentoBomResponse response = null;

        long atualizarEquipamento = 0;

        try {
            response = ApiServices.getInstance(context).obterEquipamentosBomMFS(productCode);

            if(response.isSuccess()) {
                equipamentoBomView.setSuccess(true);
                equipamentoBomView.setMensagem("EquipamentosBom retornados com sucesso!");

                List<EquipamentoBomItemView> equipamentoBomItemView = new ArrayList<>();

                // Excluir e Incluir no EquipamentoBomRepository a estrutura do produto
                if(response.getEquipamentosBom() != null) {
                    this.equipamentoBomRepository.excluir("productCode", productCode);

                    for(int i =0; i < response.getEquipamentosBom().size() ; i++) {
                        equipamentoBomItemView.add(new EquipamentoBomItemView(response.getEquipamentosBom().get(i)));
                        atualizarEquipamento = this.inserirEquipamentosBomDB(new EquipamentoBomItemView(response.getEquipamentosBom().get(i)));
                    }
                }
                equipamentoBomView.setEquipamentosBom(equipamentoBomItemView);
            }else{
                equipamentoBomView.setSuccess(false);
                equipamentoBomView.setMensagem(response.getMessage());
            }
        } catch (IOException e) {
            equipamentoBomView.setSuccess(false);
            equipamentoBomView.setMensagem( "EquipamentoControler.buscarEIncluirEstruturaBomMFS - " +  "Erro ao obter equipamento '" + productCode + "': " + e.getMessage());
        }

        return equipamentoBomView;
    }

    public EquipamentoBomView atualizarEstruturaBomMFS(int pagina) {
        // Ir no Serviço buscar os dados
        EquipamentoBomView equipamentoBomView = new EquipamentoBomView();

        EquipamentoBomResponse response = null;

        long atualizarEquipamento = 0;

        try {

            response = ApiServices.getInstance(context).obterEquipamentosFullBomMFS(pagina);

            if(response.isSuccess()) {
                equipamentoBomView.setSuccess(true);
                equipamentoBomView.setMensagem("EquipamentosBom retornados com sucesso!");

                List<EquipamentoBomItemView> equipamentoBomItemView = new ArrayList<>();

                // Incluir no EquipamentoBomRepository a estrutura do produto
                if(response.getEquipamentosBom() != null) {

                    for(int i =0; i < response.getEquipamentosBom().size() ; i++) {
                        equipamentoBomItemView.add(new EquipamentoBomItemView(response.getEquipamentosBom().get(i)));
                        atualizarEquipamento = this.inserirEquipamentosBomDB(new EquipamentoBomItemView(response.getEquipamentosBom().get(i)));
                    }
                }
                equipamentoBomView.setEquipamentosBom(equipamentoBomItemView);
            }else{
                equipamentoBomView.setSuccess(false);
                equipamentoBomView.setMensagem(response.getMessage());
            }
        } catch (IOException e) {
            equipamentoBomView.setSuccess(false);
            equipamentoBomView.setMensagem( "EquipamentoControler.buscarEIncluirEstruturaBomMFS - " +  "Erro ao obter equipamento: " + e.getMessage());
        }

        return equipamentoBomView;
    }


    public EquipamentoBomView obterEquipamentosBomWS(String equipamento) {
        EquipamentoBomView equipamentoBomView = new EquipamentoBomView();

        EquipamentoBomResponse response = null;

        try {
            response = ApiServices.getInstance(context).obterEquipamentosBom(equipamento);

            if(response.isSuccess()) {
                equipamentoBomView.setSuccess(true);
                equipamentoBomView.setMensagem("EquipamentosBom retornados com sucesso!");

                List<EquipamentoBomItemView> equipamentoBomItemView = new ArrayList<>();

                if(response.getEquipamentosBom() != null) {
                    for(int i =0; i < response.getEquipamentosBom().size() ; i++) {
                        equipamentoBomItemView.add(new EquipamentoBomItemView(response.getEquipamentosBom().get(i)));
                        //this.inserirEquipamentosBomDB(new EquipamentoBomItemView(response.getEquipamentosBom().get(i)));
                    }
                }

                equipamentoBomView.setEquipamentosBom(equipamentoBomItemView);

            }else{
                equipamentoBomView.setSuccess(false);
                equipamentoBomView.setMensagem(response.getMessage());
            }
        } catch (IOException e) {
            equipamentoBomView.setSuccess(false);
            equipamentoBomView.setMensagem( "EquipamentoControler.obterEquipamentosBomWS - " +  "Erro ao obter equipamento BOM: " + e.getMessage());
        }

        return equipamentoBomView;

    }

    public EquipamentoBomView obterEquipamentosBomDB(String equipamento) {
        EquipamentoBomView equipamentoBomView = new EquipamentoBomView();

        List<EquipamentoBomModel> listaEquipamentos = this.equipamentoBomRepository.obterLista(equipamento);

        if(listaEquipamentos.size() > 0 ) {
            equipamentoBomView.setSuccess(true);
            equipamentoBomView.setMensagem("Equipamentos retornados com sucesso!");
            List<EquipamentoBomItemView> eiv = new ArrayList<EquipamentoBomItemView>();

            for (int i = 0; i < listaEquipamentos.size(); i++) {
                eiv.add(new EquipamentoBomItemView(listaEquipamentos.get(i)));
            }
            equipamentoBomView.setEquipamentosBom(eiv);
        } else {
            equipamentoBomView.setSuccess(false);
            equipamentoBomView.setMensagem("Equipamento não encontrado!");
            equipamentoBomView.setEquipamentosBom(new ArrayList<>());
        }

        return equipamentoBomView;
    }

    public EquipamentoBomView validarEquipamentosBomDB(String equipamento) {
        EquipamentoBomView equipamentoBomView = new EquipamentoBomView();

        List<EquipamentoBomModel> listaEquipamentos = this.equipamentoBomRepository.validarEquipamento(equipamento);

        if(listaEquipamentos.size() > 0 ) {
            equipamentoBomView.setSuccess(true);
            equipamentoBomView.setMensagem("Peça retornada com sucesso!");
        } else {
            equipamentoBomView.setSuccess(false);
            equipamentoBomView.setMensagem("Peça não encontrada!");
        }

        return equipamentoBomView;
    }

    public long inserirEquipamentosBomDB(EquipamentoBomItemView equipamentoBomItemView) {

        EquipamentoBomModel equipamento = new EquipamentoBomModel(equipamentoBomItemView);
        long atualizarEquipamento = this.equipamentoBomRepository.inserir(equipamento);

        return atualizarEquipamento;

    }

    public boolean excluirEquipamentosBomDB() {

        boolean excluirEquipamentos = this.equipamentoBomRepository.excluir();

        return excluirEquipamentos;

    }


}
