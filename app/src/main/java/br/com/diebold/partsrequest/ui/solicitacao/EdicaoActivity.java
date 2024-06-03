package br.com.diebold.partsrequest.ui.solicitacao;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.adapter.AutoCompleteBomAdapter;
import br.com.diebold.partsrequest.controller.EquipamentoController;
import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.controller.TarefaController;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.modelView.EquipamentoBomItemView;
import br.com.diebold.partsrequest.modelView.EquipamentoBomView;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.PedidoProdutosView;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.modelView.TarefaView;
import br.com.diebold.partsrequest.modelView.TransporteView;
import br.com.diebold.partsrequest.modelView.VerificaTarefaView;
import br.com.diebold.partsrequest.ui.atualizacao.AtualizacaoActivity;
import br.com.diebold.partsrequest.ui.status.PendentesActivity;
import br.com.diebold.partsrequest.ui.status.adapters.AdapterDetalhaPedido;
import br.com.diebold.partsrequest.ui.status.adapters.AdapterItensDoCarrinho;
import br.com.diebold.partsrequest.utils.DateTime;
import br.com.diebold.partsrequest.utils.DateTimeFormat;
import br.com.diebold.partsrequest.utils.MaskEditUtil;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;


public class EdicaoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView tvLocalizacao;
    private AutoCompleteTextView autoCompleteTarefa;
    private AutoCompleteTextView autoCompleteEquipamento;
    private AutoCompleteTextView autoCompleteEquipamentoBom;
    private AutoCompleteTextView edtDescricaoPeca;
    private Spinner spinnerTransportes;
    private String transporteSelecionado;

    private String itemCode;
    private String productName;
    private String productCode;
    private Integer productId;
    private Integer destroyFlag;
    private String attribute1;
    private Integer qtd;

    private EditText quantidadeItem;

    private TextView edtSite;
    private TextView tvTipo;
    private EditText edtEndereco;
    private EditText edtNumero;
    private EditText edtCEP;
    private EditText edtBairro;
    private EditText edtCidade;
    private boolean buscaPedido;

    private ListView lsvCarrinho;
    private List<PedidoProdutosView> listaItensDoCarrinho;
    private AdapterItensDoCarrinho adpItemDoCarrinho;

    private String dataAbertura;
    private String dataAgendadaParaAtendimento;
    private String dataLimiteAtendimento;
    private String dataLimiteSolucao;

    private String idSite;
    private String nmSite;
    private Integer nSerie;
    private Integer regiaoTecnica;
    private String descricaoDoEquipamento;
    private String numeroDeSerieDoEquipamento;
    private String catEquipamento;
    private String enderecoSite;
    private String cliente;

    private Button btAgendaEntrega;
    private TextView tvDataAgendaEntrega;
    private Integer dia = 0, mes = 0, ano = 0, hora = 0, minuto = 0;
    private String agendamentoEntrega;

    private Button btnSalvarPedido;

    private AppCompatActivity context;

    private ListView lsvPedido;
    private AdapterDetalhaPedido adapterDetalhaPedido;

    UsuarioResponse DadosUsuarioLogado;

    public Integer idPedido;
    private PedidoView pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);

        this.setTitle(getString(R.string.activity_edicao));

        context = this;

        tvLocalizacao = (TextView) findViewById(R.id.localizacao);
        tvTipo = (TextView) findViewById(R.id.tipo);
        autoCompleteTarefa = findViewById(R.id.autoCompleteTarefa);
        autoCompleteEquipamento = findViewById(R.id.autoCompleteEquipamento);
        autoCompleteEquipamentoBom = findViewById(R.id.autoCompleteItemCode);
        spinnerTransportes = (Spinner) findViewById(R.id.spinnerTransportes);
        autoCompleteEquipamentoBom.addTextChangedListener(MaskEditUtil.mask(autoCompleteEquipamentoBom, "##.###.#####-##"));
        edtDescricaoPeca = findViewById(R.id.edtDescricaoPeca);

        edtSite = (TextView) findViewById(R.id.site);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtCEP = (EditText) findViewById(R.id.edtCEP);
        edtBairro = (EditText) findViewById(R.id.edtBairro);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtNumero = (EditText) findViewById(R.id.edtNumero);
        buscaPedido = false;

        btnSalvarPedido = (Button) findViewById(R.id.btnSalvarPedido);

        autoCompleteTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autoCompleteTarefa.showDropDown();
            }
        });

        autoCompleteEquipamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autoCompleteEquipamento.showDropDown();
            }
        });

        autoCompleteEquipamentoBom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autoCompleteEquipamentoBom.showDropDown();
            }
        });

        this.quantidadeItem = (EditText) findViewById(R.id.edtQuantidade);

        btAgendaEntrega = (Button) findViewById(R.id.btAgendaEntrega);
        tvDataAgendaEntrega = (TextView) findViewById(R.id.tvDataAgendaEntrega);

        final Calendar date = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora = hourOfDay;
                minuto = minute;

                String horario1;
                String minutos1;
                String dia1 = "", mes1 = "", ano1  = ano.toString() ;

                if(hora < 10 && minuto < 10) {
                    horario1 = "0" + hora;
                    minutos1 = "0" + minuto;
                } else if (hora > 9 && minuto < 10) {
                    horario1 = "" + hora;
                    minutos1 = "0" + minuto;
                } else if(hora < 10 && minuto > 9) {
                    horario1 = "0" + hora;
                    minutos1= "" + minuto;
                } else {
                    horario1 = "" + hora;
                    minutos1= "" + minuto;
                }

                if(mes < 10 && dia < 10) {
                    dia1 = "0" + dia.toString();
                    mes1 = "0" + mes.toString();
                } else if(mes > 9 && dia < 10 ) {

                    dia1 = "0" + dia.toString();
                    mes1 = "" + mes.toString();
                } else if(mes < 10 && dia > 9) {
                    mes1 = "0" + mes.toString();
                    dia1 = "" + dia.toString();
                } else {
                    dia1 =  dia.toString();
                    mes1 = mes.toString();
                }

                String dataAgendada = ano1 + "-" + mes1 + "-" + dia1 + "T" + horario1 + ":" + minutos1 + ":00.000+0000";

                Date dataAgendamentoEntrega = DateTime.StringToDate(dataAgendada, DateTimeFormat.BRAZILDATEANDTIME.getValue());

                agendamentoEntrega = dataAgendada;
                tvDataAgendaEntrega.setText(DateTime.DateToString(dataAgendamentoEntrega, DateTimeFormat.BRAZILDATEANDTIME.getValue()));

                Date dataAtualValidacao = new Date();
//                String dataAtual = DateTime.DateToString(dataAtualValidacao, DateTimeFormat.BRAZILDATEANDTIME.getValue());

                if(dataAgendamentoEntrega.getTime() < dataAtualValidacao.getTime()) {
                    Toast.makeText(context, "Data de entrega inválida!", Toast.LENGTH_LONG).show();
                    agendamentoEntrega = "";
                    tvDataAgendaEntrega.setText("");
                }
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, onTimeSetListener, hora, minuto, true);

        timePickerDialog.setTitle("Selecione a Hora");

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar updateDate = Calendar.getInstance();
                updateDate.set(year, month, dayOfMonth);
                dia = dayOfMonth;
                mes = month + 1;
                ano = year;

                timePickerDialog.show();
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));

        btAgendaEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        Intent intent = getIntent();

        if(intent != null) {
            Bundle params = intent.getExtras();

            if (params != null) {

                idPedido = Integer.parseInt(params.getString("idPedido"));

                tvLocalizacao.setText(params.getString("localizacao"));
                tvTipo.setText(params.getString("tipo"));

                autoCompleteTarefa.setText(params.getString("tarefa"));
                edtSite.setText(params.getString("site"));

                String transporte = params.getString("transporte").trim();
                spinnerTransportes.setSelection(getIndex(spinnerTransportes, transporte));

                edtEndereco.setText(params.getString("endereco"));
                edtBairro.setText(params.getString("bairro"));
                edtCEP.setText(params.getString("cep"));
                edtCidade.setText(params.getString("cidade"));
                edtNumero.setText(params.getString("numero"));

                autoCompleteEquipamento.setText(params.getString("equipamento"));

                agendamentoEntrega = params.getString("dataEntrega");

                Date dataEntrega = DateTime.StringToDate(params.getString("dataEntrega"), DateTimeFormat.BRAZILDATEANDTIME.getValue());

                String dataAgendamento = DateTime.DateToString(dataEntrega, DateTimeFormat.BRAZILDATEANDTIME.getValue());

                tvDataAgendaEntrega.setText(dataAgendamento);

                setarEquipamentoBom(params.getString("equipamento"));
                verificaTarefa(params.getString("tarefa"));

                lsvCarrinho = (ListView) this.findViewById(R.id.lsvProdutos);

                Executors.newSingleThreadExecutor().submit (new Runnable() {
                    @Override
                    public void run() {
                        pedido = PedidoController.getInstance(context)
                                .obterPedidosPorCodigoDB(idPedido);
                        if (pedido.isSuccess()) {
                            runOnUiThread( new Runnable() {
                                @Override
                                public void run() {
                                    // variaveis e objetos do carrinho
                                    listaItensDoCarrinho = new ArrayList<>(pedido.getPedidos().get(0).getProduto());
                                    adpItemDoCarrinho = new AdapterItensDoCarrinho(context, listaItensDoCarrinho);
                                    lsvCarrinho.setAdapter(adpItemDoCarrinho);
                                }
                            });

                        }
                    }
                });
            }
        }

        lsvCarrinho.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        DadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this, UsuarioResponse.class,  PreferencesUserUtil.PREFS_USER_DATA_LOGGED);
        if(DadosUsuarioLogado.getTecnico().getLocalResidente().equals("0") || DadosUsuarioLogado.getTecnico().getLocalResidente().equals("LOCAL")){
            tvLocalizacao.setText("LOCAL");
        } else {
            tvLocalizacao.setText("RESIDENTE");
        }

        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {

                TarefaController.getInstance(context)
                        .obterTarefasWS(DadosUsuarioLogado.getTecnico().getIdTecnico());

                TarefaView tarefaDB = TarefaController.getInstance(context)
                        .obterTarefasDB();

                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if (tarefaDB.isSuccess()) {
                            setarTarefa(tarefaDB);
                            setarEquipamento();
                            setarTransporte();
                        } else {
                            setarEquipamento();
                            setarTransporte();
                        }
                    }
                });
            }
        });

        this.setarExclusaoCarrinho();
    }

    private int getIndex(Spinner spinnerTransportes, String s) {
        //Use for loop

        for(int i = 0; i < spinnerTransportes.getCount(); i++) {
            //Check condition
            if(spinnerTransportes.getItemAtPosition(i).toString().equalsIgnoreCase(s)) {
                //When value match
                //Return value position
                return i;
            }
        }
        //When no values match
        //Return 0
        return 0;
    }

    public void setarExclusaoCarrinho() {
        this.lsvCarrinho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //PedidoProdutosView itemDoCarrinho = (PedidoProdutosView) adpItemDoCarrinho.getItem(position);

                //Toast.makeText(AbertosActivity.this, "Pedido: " + pedidoSelecionado.getNome(), Toast.LENGTH_LONG).show();

                AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(context);

                janelaEscolha.setTitle("Equipamento:");
                janelaEscolha.setMessage("Deseja excluir o Item selecionado?");

                janelaEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });

                janelaEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        // Excluir o equipamento
                        boolean excluiu = adpItemDoCarrinho.removerProdutos(position);

                        dialogInterface.cancel();

                        if (excluiu == true) {

                            Toast.makeText(context, "Item excluído com sucesso!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Ocorreu um erro ao excluir o Item!", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                janelaEscolha.create().show();
            }
        });

    }

    public void setarTarefa(TarefaView tarefa) {

        ArrayList<Integer> arrayTarefa = new ArrayList<Integer>();

        for(int i = 0; i < tarefa.getTarefa().size(); i++) {
            arrayTarefa.add(i, tarefa.getTarefa().get(i).getIdTarefa());
        }

        ArrayAdapter<Integer> arrayAdapterTarefa = new ArrayAdapter<Integer>(
                context, android.R.layout.simple_list_item_1, arrayTarefa);

        autoCompleteTarefa.setAdapter(arrayAdapterTarefa);
        autoCompleteTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SolicitacaoActivity.this,
//                         "Site" + tarefa.getTarefa().get(position).getSite().getIdSite() + " Tarefa " + arrayAdapterTarefa.getItem(position).toString(),
//                        Toast.LENGTH_SHORT).show();

                if(tarefa != null) {
                    int newPosition = -1;

                    for(int i = 0; i < tarefa.getTarefa().size(); i++) {
                        if(arrayTarefa.get(i).equals(Integer.parseInt(autoCompleteTarefa.getText().toString()))) {
                            newPosition = i;
                            break;
                        }
                    }

                    if(newPosition  == -1) {
                        newPosition = position;
                    }

                    dataAbertura = tarefa.getTarefa().get(newPosition).getDataAbertura();
                    dataAgendadaParaAtendimento = tarefa.getTarefa().get(newPosition).getDataAgendadaParaAtendimento();
                    dataLimiteAtendimento = tarefa.getTarefa().get(newPosition).getDataLimiteAtendimento();
                    dataLimiteSolucao = tarefa.getTarefa().get(newPosition).getDataLimiteSolucao();
                    edtSite.setText(tarefa.getTarefa().get(newPosition).getSite().getIdSite());
                    edtEndereco.setText(tarefa.getTarefa().get(newPosition).getSite().getEndereco());
                    edtCEP.setText(tarefa.getTarefa().get(newPosition).getSite().getCep());
                    edtBairro.setText(tarefa.getTarefa().get(newPosition).getSite().getBairro());
                    edtCidade.setText(tarefa.getTarefa().get(newPosition).getSite().getCidade());
                    autoCompleteEquipamento.setText(tarefa.getTarefa().get(newPosition).getDescricaoDoEquipamento());
                    setarEquipamentoBom(tarefa.getTarefa().get(newPosition).getDescricaoDoEquipamento());
                    verificaTarefa(autoCompleteTarefa.getText().toString());
                }
            }
        });
    }

    public void verificaTarefa(String tarefa) {
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {

                VerificaTarefaView verificaTarefa = PedidoController.getInstance(context)
                        .obterDadosPedidoPelaTask(tarefa);

                if (verificaTarefa.isSuccess()) {
                    idSite = verificaTarefa.getIdSite();
                    nmSite = verificaTarefa.getNmSite();
                    nSerie = verificaTarefa.getnSerie();
                    regiaoTecnica = verificaTarefa.getRegiaoTecnica();
                    descricaoDoEquipamento = verificaTarefa.getDescricaoDoEquipamento();
                    numeroDeSerieDoEquipamento = verificaTarefa.getNumeroDeSerieDoEquipamento();
                    catEquipamento = verificaTarefa.getCatEquipamento();
                    enderecoSite = verificaTarefa.getEnderecoSite();
                    cliente = verificaTarefa.getCliente();
                }

            }
        });
    }

    public void setarEquipamento() {

        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {

                List<String> equipamento = EquipamentoController.getInstance(context)
                        .obterEquipamentosDB();

                if (equipamento.size() > 0) {

                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<String> arrayEquipamento = new ArrayList<>();

                            for(int i = 0; i < equipamento.size(); i++) {
                                arrayEquipamento.add(i,  equipamento.get(i));
                            }

                            ArrayAdapter<String> arrayAdapterEquipamento = new ArrayAdapter<String>(
                                    context, android.R.layout.simple_list_item_1, arrayEquipamento);

                            autoCompleteEquipamento.setAdapter(arrayAdapterEquipamento);
                            autoCompleteEquipamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    Toast.makeText(DevolucaoActivity.this,
//                                            "ID: " + equipamento.getEquipamentos().get(position).getId() + " Equipamento: " + equipamento.getEquipamentos().get(position).getEquipamento(),
//                                            Toast.LENGTH_SHORT).show();
                                    autoCompleteEquipamento.setText(arrayAdapterEquipamento.getItem(position));
                                    setarEquipamentoBom(arrayAdapterEquipamento.getItem(position));
                                }
                            });
                        }

                    });

                } else {
                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder janelaAtencao = new AlertDialog.Builder(context);

                            janelaAtencao.setTitle("Atenção:");
                            janelaAtencao.setMessage("Você precisa atualizar sua estrutura de produtos!");

                            janelaAtencao.setNegativeButton("Atualizar Estrutura de Produtos", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.cancel();
                                    Intent intent = new Intent(context, AtualizacaoActivity.class);
                                    startActivity(intent);
                                }
                            });

                            janelaAtencao.create().show();
                            //Toast.makeText(SolicitacaoActivity.this, "Você precisa atualizar sua estrutura de produtos!", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

    }

    public void setarEquipamentoBom(String equipamento) {

        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {

                EquipamentoBomView equipamentoBom = EquipamentoController.getInstance(context)
                        .obterEquipamentosBomDB(equipamento);

                if (equipamentoBom.isSuccess()) {

                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            List<EquipamentoBomItemView> equipamentoBomList = new ArrayList<>();

                            for(int i = 0; i < equipamentoBom.getEquipamentosBom().size(); i++) {
                                equipamentoBomList.add(new EquipamentoBomItemView(equipamentoBom.getEquipamentosBom().get(i)));
                            }

                            AutoCompleteBomAdapter adapter = new AutoCompleteBomAdapter(context, equipamentoBomList);
                            autoCompleteEquipamentoBom.setAdapter(adapter);
                            autoCompleteEquipamentoBom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                    itemCode = equipamentoBomList.get(position).getItemCode();
                                    productName = equipamentoBomList.get(position).getProductName();
                                    productCode = equipamentoBomList.get(position).getProductCode();
                                    productId = equipamentoBomList.get(position).getIdEquipamentoBom();
                                    edtDescricaoPeca.setText(equipamentoBomList.get(position).getItemDescription());
                                }
                            });
                        }

                    });

                }

            }
        });

    }

    public void setarTransporte() {

        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {

                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {

                        String[] lsTransportes = getResources().getStringArray(R.array.transportes);

                        ArrayAdapter<String> arrayAdapterTransportes = new ArrayAdapter<String>(
                                context, android.R.layout.simple_list_item_1, lsTransportes);

                        spinnerTransportes.setAdapter(arrayAdapterTransportes);
                        spinnerTransportes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                            {
                                transporteSelecionado = parent.getSelectedItem().toString();
                                if(transporteSelecionado.equals("Buscar no Estoque")) {
                                    edtEndereco.setText("BUSCAR NO ESTOQUE");
                                    edtCEP.setText("00000000");
                                    edtBairro.setText("ESTOQUE");
                                    edtCidade.setText("ESTOQUE");
                                    edtNumero.setText("0000");
                                    buscaPedido = true;
                                }else {
                                    if(buscaPedido) {
                                        edtEndereco.setText("");
                                        edtCEP.setText("");
                                        edtBairro.setText("");
                                        edtCidade.setText("");
                                        edtNumero.setText("");
                                    }
                                    buscaPedido = false;
                                }
                                //Toast.makeText(SolicitacaoActivity.this, "" + parent.getSelectedItem(), Toast.LENGTH_LONG).show();
                            }
                            public void onNothingSelected(AdapterView<?> parent)
                            {

                            }
                        });
                    }

                });
            }
        });

    }

    public void setarOpcoes(int id, int array) {
        Spinner spinner = findViewById(id);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSalvarPedido:
                if(!listaItensDoCarrinho.isEmpty() && !edtEndereco.getText().toString().equals("") && !edtCEP.getText().toString().equals("")
                && !edtBairro.getText().toString().equals("") && !edtCidade.getText().toString().equals("") && !transporteSelecionado.equals("Selecione")) {
                    salvarPedido();
                } else {
                    Toast.makeText(context, "Você precisa preencher a edição!", Toast.LENGTH_LONG).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void botaoSalvarListener(){
        this.btnSalvarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPedido();
            }
        });
    }

    private void salvarPedido() {
        PedidoItemView pedido = getDadosPedido();

        if(pedido != null){
            PedidoController pedidoController = new PedidoController(context);

            PedidoView pedidoView = pedidoController.alterarPedidoDB(pedido);

            if(pedidoView.isSuccess()){
                Toast.makeText(context, "Pedido alterado com sucesso com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, PendentesActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(context, "Erro ao alterar Pedido!", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(context, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
        }
    }

    private PedidoItemView getDadosPedido(){

        PedidoItemView pedido = new PedidoItemView();

        Date data = new Date();

        String localizacao = this.tvLocalizacao.getText().toString();
        String tipo = this.tvTipo.getText().toString();
        Integer tarefa;
        if(!this.autoCompleteTarefa.getText().toString().equals("")) {
            tarefa = Integer.parseInt(this.autoCompleteTarefa.getText().toString().trim());
        } else {
            tarefa = null;
        }
        String dataPedido = DateTime.DateToString(data, DateTimeFormat.DATEANDTIME_UTC.getValue());
        String site = this.edtSite.getText().toString();
        String endereco = this.edtEndereco.getText().toString();
        String CEP = this.edtCEP.getText().toString();
        String bairro = this.edtBairro.getText().toString();
        String cidade = this.edtCidade.getText().toString();
        String numero = this.edtNumero.getText().toString();

        pedido.setStPstatusStatus("Pendente");

        pedido.setId(idPedido);
        pedido.setIdSite(idSite);
        pedido.setNmSite(nmSite);
        pedido.setnSerie(nSerie);
        pedido.setDescricaoDoEquipamento(descricaoDoEquipamento);
        pedido.setNumeroDeSerieDoEquipamento(numeroDeSerieDoEquipamento);
        pedido.setCatEquipamento(catEquipamento);
        pedido.setEnderecoSite(enderecoSite);
        pedido.setCliente(cliente);
        if(!DadosUsuarioLogado.getFiliais().isEmpty()){
            pedido.setNomeFilial(DadosUsuarioLogado.getFiliais().get(0).getNome());
        }

        pedido.setStPstatusUsuario(DadosUsuarioLogado.getUsuario());
        pedido.setNomeLocalizacao(localizacao);
        pedido.setTipoPedido(tipo);
        pedido.setIdTarefa(tarefa);
        pedido.setStPstatusData(dataPedido);

        pedido.setDtAbertura(dataAbertura);
        pedido.setDtAgendamento(dataAgendadaParaAtendimento);
        pedido.setPrazoAtendimento(dataLimiteAtendimento);
        pedido.setPrazoSolucao(dataLimiteSolucao);

        pedido.setIdSite(site);
        pedido.setNmTecnico(DadosUsuarioLogado.getTecnico().getNome());
        pedido.setIdTecnico(DadosUsuarioLogado.getTecnico().getIdTecnico());
        pedido.setEaLogradouro(endereco);
        pedido.setEaCep(CEP);
        pedido.setEaBairro(bairro);
        pedido.setEaCidade(cidade);
        pedido.setEaNum(numero);
        pedido.setDtEntrega(agendamentoEntrega);
        pedido.setBuscaPedido(buscaPedido);

        String nomeTransporte = transporteSelecionado;
        TransporteView transporteView = new TransporteView();

        if(nomeTransporte.equals("Transportadora")) {
            transporteView.setId(2);
            transporteView.setIdTransporte(2);
            transporteView.setNome("Transportadora");
        } else if(nomeTransporte.equals("Buscar no Estoque")) {
            transporteView.setId(3);
            transporteView.setIdTransporte(3);
            transporteView.setNome("Buscar no Estoque");
        } else {
            transporteView.setId(1);
            transporteView.setIdTransporte(1);
            transporteView.setNome("Moto");
        }

        pedido.setTransporte(transporteView);

        pedido.setProduto(listaItensDoCarrinho);

        return pedido;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void eventAddProduto(View view) {

        PedidoProdutosView itemDoCarrinho = new PedidoProdutosView();
        String peca = this.autoCompleteEquipamentoBom.getText().toString();
        String descricao = this.edtDescricaoPeca.getText().toString();
        Integer idProduto = this.productId;

        if(peca.equals("")) {
            setarJanelaAtencao("Você precisa selecionar o Item!");
        } else {
            EquipamentoBomView equipamento = EquipamentoController.getInstance(context)
                    .validarEquipamentosBomDB(peca.trim());

            if (!equipamento.isSuccess()) {
                AlertDialog.Builder janelaAtencao = new AlertDialog.Builder(context);

                janelaAtencao.setTitle(R.string.atencao);
                janelaAtencao.setMessage("Este Item não está presente na estrutura de produtos! Tem certeza que deseja adicionar mesmo assim?");

                janelaAtencao.setNeutralButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();

                        quantidadeItem.setText("");
                        edtDescricaoPeca.setText("");
                        autoCompleteEquipamentoBom.setText("");
                    }
                });

                janelaAtencao.setNegativeButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();

                        if(descricao.equals("")) {
                            AlertDialog.Builder janelaAtencao = new AlertDialog.Builder(context);

                            janelaAtencao.setTitle(R.string.atencao);
                            janelaAtencao.setMessage("Digite a Descrição do Item e tente adicionar novamente!");

                            janelaAtencao.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.cancel();
                                }
                            });

                            janelaAtencao.create().show();

                            edtDescricaoPeca.setEnabled(true);
                        } else {
                            int quantidade = 0;
                            String qtd = quantidadeItem.getText().toString();

                            if (qtd.equals("")) {
                                quantidade = 1;
                            } else {
                                quantidade = Integer.parseInt(qtd);
                            }

                            itemDoCarrinho.setItemCodigo(peca.trim());
                            itemDoCarrinho.setItemDescription(descricao);
                            itemDoCarrinho.setProdQtd(quantidade);
                            itemDoCarrinho.setIdProduto(idProduto);
                            itemDoCarrinho.setItemBom(false);

                            adpItemDoCarrinho.addItemDoCarrinho(itemDoCarrinho);

                            quantidadeItem.setText("");
                            edtDescricaoPeca.setText("");
                            autoCompleteEquipamentoBom.setText("");

                            edtDescricaoPeca.setEnabled(false);
                        }
                    }
                });

                janelaAtencao.create().show();
            } else {
                int quantidade = 0;
                String qtd = quantidadeItem.getText().toString();

                if (qtd.equals("")) {
                    quantidade = 1;
                } else {
                    quantidade = Integer.parseInt(qtd);
                }

                itemDoCarrinho.setItemCodigo(peca.trim());
                itemDoCarrinho.setItemDescription(descricao);
                itemDoCarrinho.setProdQtd(quantidade);
                itemDoCarrinho.setIdProduto(idProduto);
                itemDoCarrinho.setItemBom(true);

                adpItemDoCarrinho.addItemDoCarrinho(itemDoCarrinho);

                quantidadeItem.setText("");
                edtDescricaoPeca.setText("");
                autoCompleteEquipamentoBom.setText("");

                edtDescricaoPeca.setEnabled(false);
            }
        }
    }

    public void setarJanelaAtencao(String mensagem) {
        AlertDialog.Builder janelaAtencao = new AlertDialog.Builder(context);

        janelaAtencao.setTitle(R.string.atencao);
        janelaAtencao.setMessage(mensagem);

        janelaAtencao.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });

        janelaAtencao.create().show();
    }

    public void eventAlterarEndereco(View view) {
        edtEndereco.setText("");
        edtCEP.setText("");
        edtBairro.setText("");
        edtCidade.setText("");
        edtNumero.setText("");
        buscaPedido = false;
    }

    public void eventBuscarEstoque(View view) {
        edtEndereco.setText("BUSCAR NO ESTOQUE");
        edtCEP.setText("00000000");
        edtBairro.setText("ESTOQUE");
        edtCidade.setText("ESTOQUE");
        edtNumero.setText("0000");
        buscaPedido = true;
    }

    public void eventLimparCampos() {
        autoCompleteTarefa.setText("");
        edtSite.setText("");

        edtEndereco.setText("");
        edtCEP.setText("");
        edtBairro.setText("");
        edtCidade.setText("");
        edtNumero.setText("");

        autoCompleteEquipamento.setText("");

        listaItensDoCarrinho.clear();

        autoCompleteEquipamentoBom.setText("");
        quantidadeItem.setText("");

        this.adpItemDoCarrinho.limparCarrinho();
    }
}