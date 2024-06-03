 package br.com.diebold.partsrequest.ui.configuracao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.controller.TarefaController;
import br.com.diebold.partsrequest.modelView.TarefaView;
import br.com.diebold.partsrequest.ui.solicitacao.SolicitacaoActivity;
import br.com.diebold.partsrequest.utils.GeneralUtils;

 public class AmbienteActivity extends AppCompatActivity {// implements AdapterView.OnItemSelectedListener {
    private TextView urlAmbiente;
    private Button btnTestarConexao;

    private TextView statusServidor;
    private TextView statusRede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambiente);

        this.setTitle(getString(R.string.menu_ambiente));

        urlAmbiente = (TextView) findViewById(R.id.urlAmbiente);
        btnTestarConexao = (Button) findViewById(R.id.btnTestarConexao);

        statusServidor = (TextView) findViewById(R.id.statusServidor);
        statusRede = (TextView) findViewById(R.id.statusRede);

        urlAmbiente.setText(GeneralUtils.getStringResourceByName(this, "Ambiente"));

//        Spinner spinnerAmbiente = findViewById(R.id.spinnerAmbiente);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ambientes, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerAmbiente.setAdapter(adapter);
//        spinnerAmbiente.setOnItemSelectedListener(this);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public void eventTestarConexao(View view) {


        Context context = this;
        if (isOnline(AmbienteActivity.this)) {
            statusRede.setText("Rede conectada!");

            Executors.newSingleThreadExecutor().submit(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection conn = null;
                    try {

                        URL url = new URL(GeneralUtils.getStringResourceByName(context, "EndPointPRWS") + "partsrequest/");

//                        conn.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaWJlaXJmMiIsImV4cCI6MTY0MjA3ODg3OH0.DD07HkWR0KDrooGXgukhQhb4qu7eHJfoXczcNjHE_HExvtDh-a1yuUOVSmfMSKvRaNEfahZDyy1lvNAj_v0yWg");

                        conn = (HttpURLConnection) url.openConnection();

                        //conn.HTTP_OK
                        if (conn.getResponseCode() == 403) {
//                            Toast.makeText(getApplicationContext(), "Conexao Servidor Funcionando", Toast.LENGTH_LONG).show();
                        runOnUiThread( new Runnable() {
                            @Override
                            public void run() {
                                statusServidor.setText("Servidor Conectado!");
                            }
                        });
                        } else {
//                            Toast.makeText(getApplicationContext(), "Conexao Servidor com Problema", Toast.LENGTH_LONG).show();
                            runOnUiThread( new Runnable() {
                                @Override
                                public void run() {
                                    statusServidor.setText("Servidor não conectado!");
                                }
                            });

                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Falha:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    } //finally {
    //                if (conn != null) {
    //                    Toast.makeText(getApplicationContext(), "Disconnect", Toast.LENGTH_LONG).show();
    //                    conn.disconnect();
    //                }
    //            }
                }
            });
        } else {
            statusRede.setText("Rede não conectada!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.configuracao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_informacoes:
                Intent intent = new Intent(AmbienteActivity.this, ConfiguracaoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_ambiente:
                return true;
//            case R.id.action_logs:
//                Intent intent3 = new Intent(AmbienteActivity.this, LogsActivity.class);
//                startActivity(intent3);
//                return true;
            case R.id.action_senha:
                Intent intent4 = new Intent(AmbienteActivity.this, AtualizacaoSenhaActivity.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text + " id: " + id, Toast.LENGTH_LONG).show();
//        if(id == 0) {
//            urlAmbiente.setText("http://smartdispatchdev03.dieboldnixdorf.com.br/partsrequest/");
//        } else if(id == 1) {
//            urlAmbiente.setText("http://smartdispatchdev02.dieboldnixdorf.com.br/partsrequest/");
//        } else if(id == 2) {
//            urlAmbiente.setText("http://smartdispatch.dieboldnixdorf.com.br/partsrequest/");
//        }
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}