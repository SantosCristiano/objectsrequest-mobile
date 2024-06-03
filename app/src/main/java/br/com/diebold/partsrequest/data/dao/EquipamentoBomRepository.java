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
import br.com.diebold.partsrequest.data.dao.model.EquipamentoBomModel;
import br.com.diebold.partsrequest.data.dao.model.EquipamentoModel;


public class EquipamentoBomRepository implements ICrud {

    private final static String TABLE = "EquipamentoBom";
    private static EquipamentoBomRepository instance;
    private ConexaoSQLite cnn = null;


    public static EquipamentoBomRepository getInstance(Context context) {
        if (instance == null) {
            instance = new EquipamentoBomRepository(context);
        }
        return instance;
    }

    public EquipamentoBomRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }

    public long inserir(EquipamentoBomModel equipamento) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", equipamento.getId());
        values.put("idEquipamentoBom", equipamento.getIdEquipamentoBom());
        values.put("productCode", equipamento.getProductCode());
        values.put("productName", equipamento.getProductName());
        values.put("itemFamily", equipamento.getItemFamily());
        values.put("itemCode", equipamento.getItemCode());
        values.put("itemDescription", equipamento.getItemDescription());

        alterou = cnn.inserir( values);

         return alterou;
    }

    public long inserirOuAtualizar(EquipamentoBomModel equipamento){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(equipamento.getId() != null && equipamento.getId() > 0) {
            values.put("id", equipamento.getId());
        }

        values.put("idEquipamentoBom", equipamento.getIdEquipamentoBom());
        values.put("productCode", equipamento.getProductCode());
        values.put("productName", equipamento.getProductName());
        values.put("itemFamily", equipamento.getItemFamily());
        values.put("itemCode", equipamento.getItemCode());
        values.put("itemDescription", equipamento.getItemDescription());

        alterou = cnn.inserirOuAtualizar( values,  "idEquipamentoBom = ?", new String[] {equipamento.getIdEquipamentoBom().toString()});

        return alterou;
    }

    public List<EquipamentoBomModel> obterLista(String equipamento) {

        ArrayList<EquipamentoBomModel> lista = new ArrayList<>();

        String sql = "SELECT DISTINCT 0 AS id, 0 AS idEquipamentoBom, productCode, productName, itemFamily, itemCode, itemDescription FROM " + TABLE + " WHERE productName = ? ORDER BY itemFamily";
        String[] selectionArgs = new String[]{"" + equipamento};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }

    public List<EquipamentoBomModel> validarEquipamento(String itemCode) {

        ArrayList<EquipamentoBomModel> lista = new ArrayList<>();

        String sql = "SELECT DISTINCT 0 AS id, 0 AS idEquipamentoBom, productCode, productName, itemFamily, itemCode, itemDescription FROM " + TABLE + " WHERE itemCode = ? ORDER BY itemFamily";
        String[] selectionArgs = new String[]{"" + itemCode};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }

    @SuppressLint("Range")
    public List<String> obterLista() {

        ArrayList<String> lista = new ArrayList<>();

        String sql = "SELECT DISTINCT productName FROM " + TABLE;
        String[] selectionArgs = new String[]{};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(cursor.getString(cursor.getColumnIndex("productName")));
        }

        return lista;

    }

    public EquipamentoBomModel obter(String equipamento) {
        EquipamentoBomModel equipamentoBomModel = null;

        String sql = "SELECT DISTINCT * FROM " + TABLE + " WHERE productName = ?";
        String[] selectionArgs = new String[]{"" + equipamento};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            equipamentoBomModel = build(cursor);
        }

        return equipamentoBomModel;

    }

    public boolean excluir() {

        long alterou = 0;

        alterou = cnn.deletar("", new String[]{});

        return alterou > 0;

    }

    public boolean excluir(String equipamento) {

        long alterou = 0;

        alterou = cnn.deletar("productName = ?", new String[]{equipamento});

        return alterou > 0;

    }

    public boolean excluir(String coluna, String valor) {

        long alterou = 0;

        alterou = cnn.deletar(coluna + " = ?", new String[]{valor});

        return alterou > 0;

    }

    public boolean atualizar(EquipamentoBomModel equipamento) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", equipamento.getId());
        values.put("idEquipamentoBom", equipamento.getIdEquipamentoBom());
        values.put("productCode", equipamento.getProductCode());
        values.put("productName", equipamento.getProductName());
        values.put("itemFamily", equipamento.getItemFamily());
        values.put("itemCode", equipamento.getItemCode());
        values.put("itemDescription", equipamento.getItemDescription());

        alterou = cnn.atualizar(values, "idEquipamentoBom = ?", new String[]{equipamento.getIdEquipamentoBom().toString()});

        return alterou > 0;

    }

    @SuppressLint("Range")
    public EquipamentoBomModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id;
        Integer idEquipamentoBom;
        String productCode;
        String productName;
        String itemFamily;
        String itemCode;
        String itemDescription;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idEquipamentoBom = cursor.getInt(cursor.getColumnIndex("idEquipamentoBom"));
        productCode = cursor.getString(cursor.getColumnIndex("productCode"));
        productName = cursor.getString(cursor.getColumnIndex("productName"));
        itemFamily = cursor.getString(cursor.getColumnIndex("itemFamily"));
        itemCode = cursor.getString(cursor.getColumnIndex("itemCode"));
        itemDescription = cursor.getString(cursor.getColumnIndex("itemDescription"));

        return new EquipamentoBomModel(id, idEquipamentoBom, productCode, productName, itemFamily, itemCode,
                itemDescription);

    }


}
