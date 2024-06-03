package br.com.diebold.partsrequest.ui.status.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.modelView.PedidoProdutosView;

public class AdapterDetalhaPedido extends BaseAdapter  {

    private Context context;
    private List<PedidoItemView> pedidoList;

    private ListView lsvCarrinho;
    private List<PedidoProdutosView> listaItensDoCarrinho;
    private AdapterItensDoCarrinho adpItemDoCarrinho;

    public AdapterDetalhaPedido(Context context, List<PedidoItemView> pedidoList) {
        this.context = context;
        this.pedidoList = pedidoList;
    }

    @Override
    public int getCount() {
        return this.pedidoList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.pedidoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removerPedido(int position) {
        this.pedidoList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;

        TextView tvTask;
        TextView tvDataSolicitacao;
        TextView tvDataAgendamento;
        TextView tvSite;
        TextView tvIdPedidoWS;
        TextView tvTipoPedido;
        TextView tvStatus;
        TextView tvTransporte;
        TextView tvLocalizacao;
        TextView tvEndereco1;
        TextView tvEndereco2;

        TextView tvNomeEntregador;
        TextView tvTelefoneEntregador;
        TextView tvObservacao;

        Button btnCancelarPedido;
        Button btnFinalizarPedido;
        Button btnEditarPedido;

        try {
            v = View.inflate(this.context, R.layout.layout_detalhe,null);

            tvTask = (TextView) v.findViewById(R.id.task);
            tvDataSolicitacao = (TextView) v.findViewById(R.id.dataSolicitacao);
            tvDataAgendamento = (TextView) v.findViewById(R.id.dataAgendamento);
            tvSite = (TextView) v.findViewById(R.id.site);
            tvIdPedidoWS = (TextView) v.findViewById(R.id.idPedidoWS);
            tvTipoPedido = (TextView) v.findViewById(R.id.tipoPedido);
            tvStatus = (TextView) v.findViewById(R.id.status);
            tvTransporte = (TextView) v.findViewById(R.id.transporte);
            tvLocalizacao = (TextView) v.findViewById(R.id.localizacao);
            tvEndereco1 = (TextView) v.findViewById(R.id.endereco1);
            tvEndereco2 = (TextView) v.findViewById(R.id.endereco2);

            tvNomeEntregador = (TextView) v.findViewById(R.id.tvNomeEntregador);
            tvTelefoneEntregador = (TextView) v.findViewById(R.id.tvTelefoneEntregador);
            tvObservacao = (TextView) v.findViewById(R.id.tvObservacao);

            btnCancelarPedido = (Button) v.findViewById(R.id.btnCancelarPedido);
            btnFinalizarPedido = (Button) v.findViewById(R.id.btnFinalizarPedido);
            btnEditarPedido = (Button) v.findViewById(R.id.btnEditarPedido);

            Date datePedido = StringToDate(this.pedidoList.get(position).getStPstatusData(), DateTimeFormat.BRAZILDATEANDTIMER.getValue());
            String dataPedido =  DateToString(datePedido, DateTimeFormat.BRAZILDATEANDTIMER.getValue());

            Date dateEntrega = StringToDate(this.pedidoList.get(position).getDtEntrega(), DateTimeFormat.BRAZILDATEANDTIMER.getValue());
            String dataEntrega =  DateToString(dateEntrega, DateTimeFormat.BRAZILDATEANDTIMER.getValue());
            String task;
            if(this.pedidoList.get(position).getIdTarefa() != null) {
                task = "Tarefa: " + this.pedidoList.get(position).getIdTarefa().toString();
            } else {
                task = "Tarefa: ";
            }
            String abertura = "Data do Pedido: " + dataPedido;
            String agendamento = "Data de Entrega: " + dataEntrega;
            String site;
            if(this.pedidoList.get(position).getIdSite() != null) {
                site = "Site: " + this.pedidoList.get(position).getIdSite();
            } else {
                site = "Site: ";
            }
            String transporte;
            if(this.pedidoList.get(position).getTransporte().getNome() != null) {
                transporte = "Transporte: " + this.pedidoList.get(position).getTransporte().getNome();
            } else {
                transporte = "Transporte: ";
            }
            String tipoPedido = "Tipo: " + this.pedidoList.get(position).getTipoPedido();

            String idPedidoWS;
            String nomeEntregador;
            String telefoneEntregador;
            String observacao;

            if( this.pedidoList.get(position).getIdPedidoWS().toString() != null) {
                idPedidoWS = "Pedido: " + this.pedidoList.get(position).getIdPedidoWS().toString();
            } else {
                idPedidoWS = "Pedido: Não Sincronizou Ainda!";
            }
            if(this.pedidoList.get(position).getNomeEntregador() != null) {
                nomeEntregador = "Nome: " + this.pedidoList.get(position).getNomeEntregador();
            } else {
                nomeEntregador = "Nome: ";
            }
            if(this.pedidoList.get(position).getTelefoneEntregador() != null) {
                telefoneEntregador = "Telefone: " + this.pedidoList.get(position).getTelefoneEntregador();
            } else {
                telefoneEntregador = "Telefone: ";
            }
            if(this.pedidoList.get(position).getObservacao() != null) {
                observacao = "Observações: " + this.pedidoList.get(position).getObservacao();
            } else {
                observacao = "Observações: ";
            }

            tvTask.setText(task);
            tvTipoPedido.setText(tipoPedido);
            tvDataSolicitacao.setText(abertura);
            tvDataAgendamento.setText(agendamento);
            tvSite.setText(site);
            tvIdPedidoWS.setText(idPedidoWS);

            String localizacao = "Localização: " + this.pedidoList.get(position).getNomeLocalizacao();
            String status = "Status: " + this.pedidoList.get(position).getStPstatusStatus();
            String endereco1 = "End: " + this.pedidoList.get(position).getEaLogradouro();
            String endereco2 = this.pedidoList.get(position).getEaNum() + " "
                                + this.pedidoList.get(position).getEaBairro() + " "
                                + this.pedidoList.get(position).getEaCep() + " "
                                + this.pedidoList.get(position).getEaCidade();

            tvLocalizacao.setText(localizacao);
            tvStatus.setText(status);
            tvTransporte.setText(transporte);
            tvEndereco1.setText(endereco1);
            tvEndereco2.setText(endereco2);

            tvNomeEntregador.setText(nomeEntregador);
            tvTelefoneEntregador.setText(telefoneEntregador);
            tvObservacao.setText(observacao);

            if(this.pedidoList.get(position).getStPstatusStatus().equals("Finalizado") || this.pedidoList.get(position).getStPstatusStatus().equals("Cancelado")) {

                btnCancelarPedido.setVisibility(View.GONE);
                btnFinalizarPedido.setVisibility(View.GONE);
                btnEditarPedido.setVisibility(View.GONE);
            }

            if(this.pedidoList.get(position).getStPstatusStatus().equals("Aberto") || this.pedidoList.get(position).getStPstatusStatus().equals("Em Transporte")
                    || this.pedidoList.get(position).getStPstatusStatus().equals("Erro")) {
                btnEditarPedido.setVisibility(View.GONE);
            }

            // variaveis e objetos do carrinho
            this.lsvCarrinho = (ListView) v.findViewById(R.id.lsvProdutos);
            this.listaItensDoCarrinho = new ArrayList<>();
            this.adpItemDoCarrinho = new AdapterItensDoCarrinho(context, this.listaItensDoCarrinho);
            this.lsvCarrinho.setAdapter(this.adpItemDoCarrinho);

            PedidoProdutosView pedidoProdutosView = null;

            for (int i = 0; i < this.pedidoList.get(position).getProduto().size(); i++) {
                pedidoProdutosView = this.pedidoList.get(position).getProduto().get(i);
                adpItemDoCarrinho.addItemDoCarrinho(pedidoProdutosView);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    public static Date StringToDate(String value, String format) {

        try {
            if (value.contains("T")) {
                value = value.replace("-", "/").replace("T", " ").trim();
                String ano = value.substring(0, 4);
                String mes = value.substring(5, 7);
                String dia = value.substring(8, 10);
                String anoF = dia + "/" + mes + "/" + ano;
                value = value.replace(value.substring(0, 10), anoF);
            }
            SimpleDateFormat curFormater = new SimpleDateFormat(format);
            Date dateReturn = curFormater.parse(value);
            return dateReturn;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }

    public static String DateToString(Date date, String format) {

        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return "";
        }

    }

    public enum DateTimeFormat {

        USADATEANDTIME("EEE MMM dd yyyy hh:mm aaa"), BRAZILDATEANDTIME("dd/MM/yyyy HH:mm:ss"), BRAZILDATEANDTIMER("dd/MM/yyyy HH:mm"), BRAZILDATE("dd/MM/yyyy"), BRAZILDATER("dd/MM/yy"), BRAZILTIME("HH:mm:ss"), BRAZILTIMER("HH:mm"), DATEANDTIME_UTC("yyyy-MM-dd'T'HH:mm:ss");
        private String value;
        private DateTimeFormat(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    /**
     * Atualiza a lista de pedidos do adapter
     * @param pedidos
     */
    public void atualizar(List<PedidoItemView> pedidos) {
        this.pedidoList.clear();
        this.pedidoList = pedidos;
        this.notifyDataSetChanged();
    }
}
