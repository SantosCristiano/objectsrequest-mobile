package br.com.diebold.partsrequest.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.ui.status.adapters.AdapterListaPedido;

public class HomeActivity extends AppCompatActivity {

    private AppCompatActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}