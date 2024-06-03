package br.com.diebold.partsrequest.ui.status;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.ui.status.adapters.AdapterListaPedido;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class EnviadosActivity extends AppCompatActivity {

    private ListView lsvPedidos;
    private List<PedidoView> pedidoView;
    private AdapterListaPedido adapterListaPedido;
    private PedidoController pedidoController;
    private AppCompatActivity context;
    UsuarioResponse DadosUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviados);
        this.setTitle(getString(R.string.activity_enviados));

        context = this;
        DadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this, UsuarioResponse.class,  PreferencesUserUtil.PREFS_USER_DATA_LOGGED);
        //ExecutorService exec = Executors.newSingleThreadExecutor();
        Executors.newSingleThreadExecutor().submit (new Runnable() {
            //runOnUiThread(new Runnable() {
            @Override
            public void run() {

//                PedidoView pedido = PedidoController.getInstance(context)
//                        .obterPedidosPorStatus("Em Transporte", "53101");

                PedidoView pedido = PedidoController.getInstance(context)
                        .obterPedidosPorStatusDB(new String[]{"Em Transporte"}, DadosUsuarioLogado.getTecnico().getIdTecnico());

                if (pedido.isSuccess()) {

                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            lsvPedidos = (ListView) findViewById(R.id.lsvPedidos);

                            adapterListaPedido = new AdapterListaPedido(context, pedido.getPedidos());

                            lsvPedidos.setAdapter(adapterListaPedido);
                        }

                    });

                }
            }
        });
    }
}