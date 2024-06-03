package br.com.diebold.partsrequest.data.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.dao.dbHelper.ConexaoSQLite;
import br.com.diebold.partsrequest.data.dao.dbHelper.DataBaseName;
import br.com.diebold.partsrequest.data.dao.dbHelper.DataBaseVersion;
import br.com.diebold.partsrequest.data.dao.dbHelper.ICrud;
import br.com.diebold.partsrequest.data.dao.dbHelper.PartsRequestDataBase;
import br.com.diebold.partsrequest.data.dao.model.PedidoModel;
import br.com.diebold.partsrequest.data.dao.model.PedidoProdutosModel;


public class PedidoProdutosRepository implements ICrud {

    private final static String TABLE = "Pedido_Produtos";
    private static PedidoProdutosRepository instance;
    private ConexaoSQLite cnn = null;


    public static PedidoProdutosRepository getInstance(Context context) {
        if (instance == null) {
            instance = new PedidoProdutosRepository(context);
        }
        return instance;
    }

    public PedidoProdutosRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }



    public long inserir(PedidoProdutosModel produto){

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", produto.getId());
        values.put("idPedidoDB", produto.getIdPedidoDB());
        values.put("idProduto", produto.getIdProduto());
        values.put("prodCodigo", produto.getProdCodigo());
        values.put("prodQtd", produto.getProdQtd());
        values.put("prodName", produto.getProdName());
        values.put("itemCodigo", produto.getItemCodigo());
        values.put("itemDescription", produto.getItemDescription());
        if(produto.isItemBom()) {
            values.put("itemBom", "1");
        } else {
            values.put("itemBom", "0");
        }
        values.put("dataUltimaAlteracao", produto.getDataUltimaAlteracao());

        alterou = cnn.inserir( values);

         return alterou;
    }


    public long inserirOuAtualizar(PedidoProdutosModel produto){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(produto.getId() != null && produto.getId() > 0) {
            values.put("id", produto.getId());
        }
        values.put("idPedidoDB", produto.getIdPedidoDB());
        values.put("idProduto", produto.getIdProduto());
        values.put("prodCodigo", produto.getProdCodigo());
        values.put("prodQtd", produto.getProdQtd());
        values.put("prodName", produto.getProdName());
        values.put("itemCodigo", produto.getItemCodigo());
        values.put("itemDescription", produto.getItemDescription());
        if(produto.isItemBom()) {
            values.put("itemBom", "1");
        } else {
            values.put("itemBom", "0");
        }
        values.put("dataUltimaAlteracao", produto.getDataUltimaAlteracao());

        alterou = cnn.inserirOuAtualizar( values,  "id = ?", new String[] {produto.getId().toString()});

        return alterou;
    }

    public long inserirOuAtualizarPorIdPedidoOuCodigoProduto(PedidoProdutosModel produto){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(produto.getId() != null && produto.getId() > 0) {
            values.put("id", produto.getId());
        }
        values.put("idPedidoDB", produto.getIdPedidoDB());
        values.put("idProduto", produto.getIdProduto());
        values.put("prodCodigo", produto.getProdCodigo());
        values.put("prodQtd", produto.getProdQtd());
        values.put("prodName", produto.getProdName());
        values.put("itemCodigo", produto.getItemCodigo());
        values.put("itemDescription", produto.getItemDescription());
        if(produto.isItemBom()) {
            values.put("itemBom", "1");
        } else {
            values.put("itemBom", "0");
        }
        values.put("dataUltimaAlteracao", produto.getDataUltimaAlteracao());

        alterou = cnn.inserirOuAtualizar( values,  "idPedidoDB = ? AND prodCodigo = ?", new String[] {produto.getIdPedidoDB().toString(), produto.getProdCodigo()});

        return alterou;
    }



    public List<PedidoProdutosModel> obterLista(int idPedidoDB) {

        ArrayList<PedidoProdutosModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE + " WHERE idPedidoDB = ?";
        String[] selectionArgs = new String[]{"" + idPedidoDB};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }


    public PedidoProdutosModel obter(int idProduto) {
        PedidoProdutosModel pedidoProdutosModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE idProduto = ?";
        String[] selectionArgs = new String[]{"" + idProduto};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            pedidoProdutosModel = build(cursor);
        }

        return pedidoProdutosModel;

    }

    public PedidoProdutosModel obter(String nomeDaColuna1, String valor1, String nomeDaColuna2, String valor2) {
        PedidoProdutosModel pedidoProdutosModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE " + nomeDaColuna1 + " = ? AND " + nomeDaColuna2 + " = ?";
        String[] selectionArgs = new String[]{valor1, valor2};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            pedidoProdutosModel = build(cursor);
        }

        return pedidoProdutosModel;

    }

    public boolean excluir(long idProduto) {

        long alterou = 0;

        alterou = cnn.deletar("idProduto = ?", new String[]{String.valueOf(idProduto)});

        return alterou > 0;

    }

    public boolean excluirProdutosDB(long idPedidoDB) {

        long alterou = 0;

        alterou = cnn.deletar("idPedidoDB = ?", new String[]{String.valueOf(idPedidoDB)});

        return alterou > 0;

    }

    public boolean atualizar(PedidoProdutosModel produto) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", produto.getId());
        values.put("idPedidoDB", produto.getIdPedidoDB());
        values.put("idProduto", produto.getIdProduto());
        values.put("prodCodigo", produto.getProdCodigo());
        values.put("prodQtd", produto.getProdQtd());
        values.put("prodName", produto.getProdName());
        values.put("itemCodigo", produto.getItemCodigo());
        values.put("itemDescription", produto.getItemDescription());
        if(produto.isItemBom()) {
            values.put("itemBom", "1");
        } else {
            values.put("itemBom", "0");
        }
        values.put("dataUltimaAlteracao", produto.getDataUltimaAlteracao());

        alterou = cnn.atualizar(values, "idProduto = ?", new String[]{produto.getItemCodigo().toString()});

        return alterou > 0;

    }



    @SuppressLint("Range")
    public PedidoProdutosModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id;
        Integer idPedido;
        Integer idProduto;
        Integer prodQtd;
        String prodCodigo;
        String prodName;
        String itemCodigo;
        String itemDescription;
        boolean itemBom;
        String dataUltimaAlteracao;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idPedido = cursor.getInt(cursor.getColumnIndex("idPedidoDB"));
        idProduto = cursor.getInt(cursor.getColumnIndex("idProduto"));
        prodQtd = cursor.getInt(cursor.getColumnIndex("prodQtd"));
        prodCodigo = cursor.getString(cursor.getColumnIndex("prodCodigo"));
        prodName = cursor.getString(cursor.getColumnIndex("prodName"));
        itemCodigo = cursor.getString(cursor.getColumnIndex("itemCodigo"));
        itemDescription = cursor.getString(cursor.getColumnIndex("itemDescription"));
        if (cursor.getString(cursor.getColumnIndex("itemBom")).equals("0")) {
            itemBom = false;
        } else {
            itemBom = true;
        }
        dataUltimaAlteracao = cursor.getString(cursor.getColumnIndex("dataUltimaAlteracao"));

        return new PedidoProdutosModel(id, idPedido, idProduto, prodQtd, prodCodigo, prodName, itemCodigo, itemDescription, itemBom, dataUltimaAlteracao);

    }


}
