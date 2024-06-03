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
import br.com.diebold.partsrequest.data.dao.model.TarefaFilialModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaSiteModel;


public class TarefaSiteRepository implements ICrud {

    private final static String TABLE = "Tarefa_Site";
    private static TarefaSiteRepository instance;
    private ConexaoSQLite cnn = null;


    public static TarefaSiteRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TarefaSiteRepository(context);
        }
        return instance;
    }

    public TarefaSiteRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }

    public long inserir(TarefaSiteModel tarefa){

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", tarefa.getId());
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("idSite", tarefa.getIdSite());

        alterou = cnn.inserir(values);

        return alterou;
    }

    public long inserirOuAtualizar(TarefaSiteModel tarefa){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(tarefa.getId() != null && tarefa.getId() > 0) {
            values.put("id", tarefa.getId());
        }
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("idSite", tarefa.getIdSite());

        alterou = cnn.inserirOuAtualizar( values,  "idSite = ? and idTarefa = ?", new String[] {tarefa.getIdSite().toString(), tarefa.getIdTarefa().toString()});

        return alterou;
    }

    public List<TarefaSiteModel> obterLista() {

        ArrayList<TarefaSiteModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE ;
        String[] selectionArgs = new String[]{};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }

    public TarefaSiteModel obter(int idTarefa) {
        TarefaSiteModel tarefaSiteModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE idTarefa = ?";
        String[] selectionArgs = new String[]{"" + idTarefa};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            tarefaSiteModel = build(cursor);
        }

        return tarefaSiteModel;

    }

    public boolean excluir(String idSite, Integer idTarefa) {

        long alterou = 0;

        alterou = cnn.deletar("idSite = ? and idTarefa = ?", new String[] {idSite, idTarefa.toString()});

        return alterou > 0;

    }

    public boolean excluir() {

        long alterou = 0;

        alterou = cnn.deletar("", new String[] {});

        return alterou > 0;

    }

    public boolean atualizar(TarefaSiteModel tarefa) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", tarefa.getId());
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("idSite", tarefa.getIdSite());


        alterou = cnn.atualizar(values, "idSite = ? and idTarefa = ?", new String[] {tarefa.getIdSite(), tarefa.getIdTarefa().toString()});

        return alterou > 0;

    }

    public TarefaSiteModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id;
        Integer idTarefa;
        String idSite;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idTarefa = cursor.getInt(cursor.getColumnIndex("idTarefa"));
        idSite = cursor.getString(cursor.getColumnIndex("idSite"));

        return new TarefaSiteModel(id,idTarefa, idSite);

    }


}
