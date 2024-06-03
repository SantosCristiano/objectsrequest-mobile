package br.com.diebold.partsrequest.data.dao;

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
import br.com.diebold.partsrequest.data.dao.model.FilialModel;


public class FilialRepository implements ICrud {

    private final static String TABLE = "Filial";
    private static FilialRepository instance;
    private ConexaoSQLite cnn = null;


    public static FilialRepository getInstance(Context context) {
        if (instance == null) {
            instance = new FilialRepository(context);
        }
        return instance;
    }

    public FilialRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }



    public long inserir(FilialModel filial){

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", filial.getId());
        values.put("idFilial", filial.getIdFilial());
        values.put("nome", filial.getNome());

        alterou = cnn.inserir( values);

         return alterou;
    }


    public long inserirOuAtualizar(FilialModel filial){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(filial.getId() != null && filial.getId() > 0) {
            values.put("id", filial.getId());
        }

        values.put("idFilial", filial.getIdFilial());
        values.put("nome", filial.getNome());

        alterou = cnn.inserirOuAtualizar( values,  "idFilial = ?", new String[] {filial.getIdFilial().toString()});

        return alterou;
    }



    public List<FilialModel> obterLista() {

        ArrayList<FilialModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE ;
        String[] selectionArgs = new String[]{};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }


    public FilialModel obter(int idFilial) {
        FilialModel filialModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE idFilial = ?";
        String[] selectionArgs = new String[]{"" + idFilial};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            filialModel = build(cursor);
        }

        return filialModel;

    }

    public boolean excluir(long idFilial) {

        long alterou = 0;

        alterou = cnn.deletar("idFilial = ?", new String[]{String.valueOf(idFilial)});

        return alterou > 0;

    }

    public boolean excluir() {

        long alterou = 0;

        alterou = cnn.deletar("", new String[]{});

        return alterou > 0;

    }

    public boolean atualizar(FilialModel filial) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", filial.getId());
        values.put("idFilial", filial.getIdFilial());
        values.put("nome", filial.getNome());


        alterou = cnn.atualizar(values, "idFilial = ?", new String[]{filial.getIdFilial().toString()});

        return alterou > 0;

    }



    public FilialModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id;
        Integer idFilial;
        String nome;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idFilial = cursor.getInt(cursor.getColumnIndex("idFilial"));
        nome = cursor.getString(cursor.getColumnIndex("nome"));

        return new FilialModel(id, idFilial, nome);

    }


}
