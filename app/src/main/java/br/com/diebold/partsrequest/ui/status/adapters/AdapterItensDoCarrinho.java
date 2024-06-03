package br.com.diebold.partsrequest.ui.status.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.modelView.PedidoProdutosView;

public class AdapterItensDoCarrinho extends BaseAdapter  {

    private Context context;
    private List<PedidoProdutosView> itensDoCarrinho;
    private boolean detalhe;

    public AdapterItensDoCarrinho(Context context, List<PedidoProdutosView> itensDoCarrinho) {
        this.context = context;
        this.itensDoCarrinho = itensDoCarrinho;
    }

    @Override
    public int getCount() {

        if(this.itensDoCarrinho != null) {
            return this.itensDoCarrinho.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return this.itensDoCarrinho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;

        TextView tvCodigo;
        TextView tvQuantidade;
        TextView tvDescricao;
        TextView tvX;

        try {
            v = View.inflate(this.context, R.layout.layout_carrinho,null);

            tvCodigo = (TextView) v.findViewById(R.id.tvCodigo);
            tvDescricao = (TextView) v.findViewById(R.id.tvDescricao);
            tvQuantidade = (TextView) v.findViewById(R.id.tvQuantidade);
            tvX = (TextView) v.findViewById(R.id.tvX);

            String codigo = "Código da Peça: " + this.itensDoCarrinho.get(position).getItemCodigo();
            String descricao = "Descrição: " + this.itensDoCarrinho.get(position).getItemDescription();
            String quantidade = "Quantidade: " + this.itensDoCarrinho.get(position).getProdQtd().toString();

            tvCodigo.setText(codigo);
            tvQuantidade.setText(quantidade);
            tvDescricao.setText(descricao);

            if(detalhe) {
                tvX.setVisibility(View.GONE);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    public void addItemDoCarrinho(PedidoProdutosView pItemDoCarrinho) {
        this.itensDoCarrinho.add(pItemDoCarrinho);
        this.notifyDataSetChanged();
    }

    public void atualizar(List<PedidoProdutosView> pItensDoCarrinho) {
        //this.itensDoCarrinho.clear();
        this.itensDoCarrinho = pItensDoCarrinho;
        this.notifyDataSetChanged();
    }

    public void limparCarrinho() {
        this.itensDoCarrinho.clear();
        this.notifyDataSetChanged();
    }

    public boolean removerProdutos(int position) {
        this.itensDoCarrinho.remove(position);
        this.notifyDataSetChanged();
        return true;
    }
}
