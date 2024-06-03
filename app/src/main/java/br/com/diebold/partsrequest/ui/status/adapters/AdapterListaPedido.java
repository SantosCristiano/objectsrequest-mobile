package br.com.diebold.partsrequest.ui.status.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.modelView.PedidoItemView;
import br.com.diebold.partsrequest.ui.status.DetalhesActivity;

public class AdapterListaPedido extends BaseAdapter  {

    private Context context;
    private List<PedidoItemView> pedidoList;

    public AdapterListaPedido(Context context, List<PedidoItemView> pedidoList) {
        this.context = context;
        this.pedidoList = pedidoList;
    }

    @Override
    public int getCount() {

        if(this.pedidoList != null) {
            return this.pedidoList.size();
        } else {
            return 0;
        }
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

        TextView tvStatusPedido;
        TextView tvTask;
//        TextView tvData;
        TextView tvSite;
        TextView tvIdPedidoWS;
        TextView tvTipoPedido;
        TextView tvAtendimento;
        TextView tvSolucao;
        TextView tvPrevisao;

        try {
            v = View.inflate(this.context, R.layout.layout_pedido,null);

            tvStatusPedido = (TextView) v.findViewById(R.id.statusPedido);
            tvTask = (TextView) v.findViewById(R.id.task1);
//            tvData = (TextView) v.findViewById(R.id.data1);
            tvSite = (TextView) v.findViewById(R.id.site1);
            tvIdPedidoWS = (TextView) v.findViewById(R.id.idPedidoWS);
            tvTipoPedido = (TextView) v.findViewById(R.id.tipoPedido1);
            tvAtendimento = (TextView) v.findViewById(R.id.atendimento1);
            tvSolucao = (TextView) v.findViewById(R.id.solucao1);
            tvPrevisao = (TextView) v.findViewById(R.id.previsao1);

            v.setOnClickListener(v1 ->
            {
                Bundle params = new Bundle();

                params.putString("idPedido", this.pedidoList.get(position).getId().toString());

                Intent intent = new Intent(context.getApplicationContext(), DetalhesActivity.class);
                intent.putExtras(params);

                context.startActivity(intent);

            });

//            Date dateAbertura = StringToDate(this.pedidoList.get(position).getDtAbertura(), DateTimeFormat.BRAZILDATEANDTIMER.getValue());
//            String dataAbertura =  DateToString(dateAbertura, DateTimeFormat.BRAZILDATEANDTIMER.getValue());

            Date dateAtendimento = StringToDate(this.pedidoList.get(position).getPrazoAtendimento(), DateTimeFormat.BRAZILDATEANDTIMER.getValue());
            String dataAtendimento =  DateToString(dateAtendimento, DateTimeFormat.BRAZILDATEANDTIMER.getValue());

            Date dateSolucao = StringToDate(this.pedidoList.get(position).getPrazoSolucao(), DateTimeFormat.BRAZILDATEANDTIMER.getValue());
            String dataSolucao =  DateToString(dateSolucao, DateTimeFormat.BRAZILDATEANDTIMER.getValue());

            Date dateAgendamento = StringToDate(this.pedidoList.get(position).getDtAgendamento(), DateTimeFormat.BRAZILDATEANDTIMER.getValue());
            String dataAgendamento =  DateToString(dateAgendamento, DateTimeFormat.BRAZILDATEANDTIMER.getValue());

            String status = "Status: " + this.pedidoList.get(position).getStPstatusStatus();
            String task;
            if(this.pedidoList.get(position).getIdTarefa() != null) {
                task = "Tarefa: " + this.pedidoList.get(position).getIdTarefa().toString();
            } else {
                task = "Tarefa: ";
            }
            String site;
            if(this.pedidoList.get(position).getIdSite() != null) {
                site = "Site: " + this.pedidoList.get(position).getIdSite();
            } else {
                site = "Site: ";
            }
            String tipoPedido = "Tipo: " + this.pedidoList.get(position).getTipoPedido();

//            String abertura = "Data de Solicitação: " + dataAbertura;
            String atendimento = "CH PZ Atendimento: " + dataAtendimento;
            String solucao = "CH PZ Solução: " + dataSolucao;
            String agendamento = "CH DT Agendamento: " + dataAgendamento;

            String idPedidoWS;

            if( this.pedidoList.get(position).getIdPedidoWS().toString() != null) {
                idPedidoWS = "Pedido: " + this.pedidoList.get(position).getIdPedidoWS().toString();
            } else {
                idPedidoWS = "Pedido: Não Sincronizou Ainda!";
            }

            tvStatusPedido.setText(status);
            tvTask.setText(task);
//            tvData.setText(abertura);
            tvSite.setText(site);
            tvIdPedidoWS.setText(idPedidoWS);
            tvTipoPedido.setText(tipoPedido);
            tvAtendimento.setText(atendimento);
            tvSolucao.setText(solucao);
            tvPrevisao.setText(agendamento);

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
