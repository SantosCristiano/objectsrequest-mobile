package br.com.diebold.partsrequest.controller;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import br.com.diebold.partsrequest.data.dao.TransporteRepository;

public class TransporteController {
    private static TransporteController instance;
    private Context context;
    private TransporteRepository transporteRepository;


    public TransporteController(Context context) {
        this.context = context;
        transporteRepository = TransporteRepository.getInstance(context);
    }

    public static TransporteController getInstance(AppCompatActivity context) {
        if (instance == null) {
            instance = new TransporteController(context);
        }
        return instance;
    }

//    public TransporteView obterTransporteWS() {
//        TransporteView transporteView = new TransporteView();
//
//            TransporteResponse response = null;
//
//            try {
//                response = ApiServices.getInstance(context).obterTransportes();
//
//                if(response.isSuccess()) {
//                    transporteView.setSuccess(true);
//                    transporteView.setMensagem("Transportes retornados com sucesso!");
//
//                    List<TransporteItemView> transporteItemView = new ArrayList<>();
//
//                    if(response.getTransportes() != null) {
//                        for(int i =0; i < response.getTransportes().size() ; i++) {
//                            transporteItemView.add(new TransporteItemView(response.getTransportes().get(i)));
//                            //this.inserirEquipamentosDB(new EquipamentoItemView(response.getEquipamentos().get(i)));
//                        }
//                    }
//
//                    transporteView.setTransportes(transporteItemView);
//
//                }else{
//                    transporteView.setSuccess(false);
//                    transporteView.setMensagem(response.getMessage());
//                }
//            } catch (IOException e) {
//                transporteView.setSuccess(false);
//                transporteView.setMensagem("TransporteControler.obterTransporteWS - " +  "Erro ao obter transporte: " + e.getMessage());
//            }
//
//        return transporteView;
//
//    }
//
//    public List<TransporteModel> obterTransportesDB() {
//
//        List<TransporteModel> listaTransportes = this.transporteRepository.obterLista();
//
//        return listaTransportes;
//    }
//
//    public long inserirTransporteDB(TransporteItemView transporteItemView) {
//
//        TransporteModel transporte = new TransporteModel(transporteItemView);
//        long atualizarTransporte = this.transporteRepository.inserir(transporte);
//
//        return atualizarTransporte;
//
//    }
//
//    public boolean excluirEquipamentosBomDB() {
//
//        boolean excluirTransportes = this.transporteRepository.excluir();
//
//        return excluirTransportes;
//
//    }


}
