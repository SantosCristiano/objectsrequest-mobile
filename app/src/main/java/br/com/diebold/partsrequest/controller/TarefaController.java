package br.com.diebold.partsrequest.controller;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.api.ApiServices;
import br.com.diebold.partsrequest.data.api.response.PedidoResponse;
import br.com.diebold.partsrequest.data.api.response.TarefaItemResponse;
import br.com.diebold.partsrequest.data.api.response.TarefaResponse;
import br.com.diebold.partsrequest.data.dao.FilialRepository;
import br.com.diebold.partsrequest.data.dao.PedidoRepository;
import br.com.diebold.partsrequest.data.dao.SiteRepository;
import br.com.diebold.partsrequest.data.dao.TarefaFilialRepository;
import br.com.diebold.partsrequest.data.dao.TarefaRepository;
import br.com.diebold.partsrequest.data.dao.TarefaSiteRepository;
import br.com.diebold.partsrequest.data.dao.dbHelper.PartsRequestDataBase;
import br.com.diebold.partsrequest.data.dao.model.FilialModel;
import br.com.diebold.partsrequest.data.dao.model.PedidoModel;
import br.com.diebold.partsrequest.data.dao.model.SiteModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaFilialModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaSiteModel;
import br.com.diebold.partsrequest.modelView.FilialView;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.SiteView;
import br.com.diebold.partsrequest.modelView.TarefaFilialView;
import br.com.diebold.partsrequest.modelView.TarefaItemView;
import br.com.diebold.partsrequest.modelView.TarefaSiteView;
import br.com.diebold.partsrequest.modelView.TarefaView;

public class TarefaController {
    private static TarefaController instance;
    private AppCompatActivity context;
    private TarefaRepository tarefaRepository;
    private SiteRepository siteRepository;
    private FilialRepository filialRepository;
    private TarefaFilialRepository tarefaFilialRepository;
    private TarefaSiteRepository tarefaSiteRepository;


    public TarefaController(AppCompatActivity context) {
        this.context = context;
        tarefaRepository = TarefaRepository.getInstance(context);
        siteRepository = SiteRepository.getInstance(context);
        filialRepository = FilialRepository.getInstance(context);
        tarefaFilialRepository = TarefaFilialRepository.getInstance(context);
        tarefaSiteRepository = TarefaSiteRepository.getInstance(context);
    }

    public static TarefaController getInstance(AppCompatActivity context) {
        if (instance == null) {
            instance = new TarefaController(context);
        }
        return instance;
    }

    public TarefaView obterTarefasWS(Integer tecnico) {
        TarefaView tarefaView = new TarefaView();

        if(tecnico > 0){

            TarefaResponse response = null;

            try {
                response = ApiServices.getInstance(context).obterTarefasPeloTecnico(tecnico);

                if(response.isSuccess()) {
                    tarefaView.setSuccess(true);
                    tarefaView.setMensagem("Tarefas pelo Tecnico retornadas com sucesso!");

                    List<TarefaItemView> tarefaItemViews = new ArrayList<>();

                    if(response.getTarefas() != null) {
                        this.excluirTarefasDB();

                        for(int i =0; i < response.getTarefas().size() ; i++) {
                            tarefaItemViews.add(new TarefaItemView(response.getTarefas().get(i)));
                            this.inserirTarefasDB(new TarefaItemView(response.getTarefas().get(i)));
                        }
                    }

                    tarefaView.setTarefa(tarefaItemViews);

                }else{
                    tarefaView.setSuccess(false);
                    tarefaView.setMensagem(response.getMessage());
                }

            } catch (IOException e) {

                tarefaView.setSuccess(false);
                tarefaView.setMensagem(e.getMessage());

            }
        } else {
            tarefaView.setSuccess(false);
            tarefaView.setMensagem("Campos nulos ou vázios");
        }

        return tarefaView;

    }

    public TarefaView obterTarefasDB() {
        TarefaView tarefaView = new TarefaView();

        List<TarefaModel> listaTarefas = this.tarefaRepository.obterLista();

        if(listaTarefas.size() > 0 ) {
            tarefaView.setSuccess(true);
            tarefaView.setMensagem("Tarefas retornadas com sucesso!");
            List<TarefaItemView> tiv = new ArrayList<TarefaItemView>();

            TarefaSiteModel tarefaSite;
            TarefaFilialModel tarefaFilial;
            SiteModel site;
            FilialModel filial;

            for (int i = 0; i < listaTarefas.size(); i++) {
                tarefaSite = this.tarefaSiteRepository.obter(listaTarefas.get(i).getIdTarefa());
                tarefaFilial = this.tarefaFilialRepository.obter(listaTarefas.get(i).getIdTarefa());

                site = this.siteRepository.obter(tarefaSite.getIdSite());
                filial = this.filialRepository.obter(tarefaFilial.getIdFilial());

                listaTarefas.get(i).setSite(site);
                listaTarefas.get(i).getSite().setFilial(filial);

                tiv.add(new TarefaItemView(listaTarefas.get(i)));
            }
            tarefaView.setTarefa(tiv);
        } else {
            tarefaView.setSuccess(false);
            tarefaView.setMensagem("Erro ao retornar Tarefas!");
            tarefaView.setTarefa(new ArrayList<>());
        }

        return tarefaView;
    }

    public long inserirTarefasDB(TarefaItemView tarefaItemView) {

        TarefaModel tarefa = new TarefaModel(tarefaItemView);
        long atualizarTarefa = this.tarefaRepository.inserirOuAtualizar(tarefa);

        SiteModel site = new SiteModel(tarefaItemView.getSite());
        long atualizarSite = this.siteRepository.inserirOuAtualizar(site);

        FilialModel filial = new FilialModel(tarefaItemView.getSite().getFilial());
        long atualizarFilial = this.filialRepository.inserirOuAtualizar(filial);

        TarefaSiteModel tarefaSite = new TarefaSiteModel(new TarefaSiteView(tarefa.getIdTarefa(), tarefaItemView.getSite()));
        long atualizarTarefaSite = this.tarefaSiteRepository.inserirOuAtualizar(tarefaSite);

        TarefaFilialModel tarefaFilial = new TarefaFilialModel(new TarefaFilialView(tarefa.getIdTarefa(), site.getIdSite(), tarefaItemView.getSite().getFilial()));
        long atualizarTarefaFilial = this.tarefaFilialRepository.inserirOuAtualizar(tarefaFilial);

        return atualizarTarefaFilial;

    }

    public boolean excluirTarefasDB() {

        boolean tarefa = this.tarefaRepository.excluir();

        boolean site = this.siteRepository.excluir();

        boolean filial = this.filialRepository.excluir();

        boolean tarefaSite = this.tarefaSiteRepository.excluir();

        boolean tarefaFilial =  this.tarefaFilialRepository.excluir();

        return tarefaFilial;

    }

    public void deleteDB() {
//        context.deleteDatabase("PartsRequestdb.db");

        File data = Environment.getDataDirectory();
        String currentDBPath = "/data/br.com.diebold.partsrequest/databases/" + "PartsRequestdb.db";
        File currentDB = new File(data, currentDBPath);
        boolean deleted = SQLiteDatabase.deleteDatabase(currentDB);
    }
}
