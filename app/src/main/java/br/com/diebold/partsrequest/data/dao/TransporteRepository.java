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
import br.com.diebold.partsrequest.data.dao.model.TransporteModel;

public class TransporteRepository implements ICrud {

    private final static String TABLE = "Transporte";
    private static TransporteRepository instance;
    private ConexaoSQLite cnn = null;

    public static TransporteRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TransporteRepository(context);
        }
        return instance;
    }

    public TransporteRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }

    public long inserir(TransporteModel transporte){

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", transporte.getId());
        values.put("idTransporte", transporte.getIdTransporte());
        values.put("nome", transporte.getNome());

        alterou = cnn.inserir( values);

         return alterou;
    }

    public long inserirOuAtualizar(TransporteModel transporte){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(transporte.getId() != null && transporte.getId() > 0) {
            values.put("id", transporte.getId());
        }

        values.put("idTransporte", transporte.getIdTransporte());
        values.put("nome", transporte.getNome());

        alterou = cnn.inserirOuAtualizar( values,  "idTransporte = ?", new String[] {transporte.getIdTransporte().toString()});

        return alterou;
    }

    public List<TransporteModel> obterLista() {

        ArrayList<TransporteModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE ;
        String[] selectionArgs = new String[]{};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }

    public TransporteModel obter(int idTransporte) {
        TransporteModel transporteModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE idTransporte = ?";
        String[] selectionArgs = new String[]{"" + idTransporte};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            transporteModel = build(cursor);
        }

        return transporteModel;

    }

    public boolean excluir(long idTransporte) {

        long alterou = 0;

        alterou = cnn.deletar("idTransporte = ?", new String[]{String.valueOf(idTransporte)});

        return alterou > 0;

    }

    public boolean excluir() {

        long alterou = 0;

        alterou = cnn.deletar("", new String[]{});

        return alterou > 0;

    }

    public boolean atualizar(TransporteModel transporte) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", transporte.getId());
        values.put("idTransporte", transporte.getIdTransporte());
        values.put("nome", transporte.getNome());

        alterou = cnn.atualizar(values, "idTransporte = ?", new String[]{transporte.getIdTransporte().toString()});

        return alterou > 0;

    }

    @SuppressLint("Range")
    public TransporteModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id;
        Integer idTransporte;
        String nome;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idTransporte = cursor.getInt(cursor.getColumnIndex("idTransporte"));
        nome = cursor.getString(cursor.getColumnIndex("nome"));

        return new TransporteModel(id, idTransporte, nome);

    }


}
