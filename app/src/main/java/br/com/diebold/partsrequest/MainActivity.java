package br.com.diebold.partsrequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

//import com.astuetz.PagerSlidingTabStrip;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.data.dao.PedidoRepository;
import br.com.diebold.partsrequest.data.dao.model.PedidoModel;
import br.com.diebold.partsrequest.databinding.ActivityMainBinding;
import br.com.diebold.partsrequest.data.dao.dbHelper.ConexaoSQLite;
import br.com.diebold.partsrequest.interfaces.IFragmentsPedidos;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.tasks.EnviarPedidosTask;
import br.com.diebold.partsrequest.ui.home.HomeFragment;
import br.com.diebold.partsrequest.ui.solicitacao.DevolucaoActivity;
import br.com.diebold.partsrequest.ui.solicitacao.SolicitacaoActivity;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class MainActivity extends AppCompatActivity implements IFragmentsPedidos {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
//    private AppCompatActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        context = this;

        configurarComponentes();

        carregarTelaInicial();

    }

    private void carregarTelaInicial(){

        HomeFragment homeFragment = new HomeFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout, homeFragment).commit();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                carregarTelaInicial();
                Toast.makeText(this, "Atualizado com sucesso!", Toast.LENGTH_LONG).show();
//                new EnviarPedidosTask(this).execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configurarComponentes(){

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.appBarMain.solicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SolicitacaoActivity.class);
                startActivity(intent);
            }
        });

        binding.appBarMain.devolucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DevolucaoActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_solicitacao, R.id.nav_atualizacao,
                R.id.nav_configuracao, R.id.nav_sobre, R.id.nav_privacidade, R.id.nav_sobre, R.id.login)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}