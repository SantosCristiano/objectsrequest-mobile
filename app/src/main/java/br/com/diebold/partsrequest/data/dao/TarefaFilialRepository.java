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
import br.com.diebold.partsrequest.data.dao.model.TarefaFilialModel;


public class TarefaFilialRepository implements ICrud {

    private final static String TABLE = "Tarefa_Filial";
    private static TarefaFilialRepository instance;
    private ConexaoSQLite cnn = null;


    public static TarefaFilialRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TarefaFilialRepository(context);
        }
        return instance;
    }

    public TarefaFilialRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }



    public long inserir(TarefaFilialModel tarefa){

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", tarefa.getId());
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("idSite", tarefa.getIdSite());
        values.put("idFilial", tarefa.getIdFilial());

        alterou = cnn.inserir(values);

        return alterou;
    }


    public long inserirOuAtualizar(TarefaFilialModel tarefa){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(tarefa.getId() != null && tarefa.getId() > 0) {
            values.put("id", tarefa.getId());
        }
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("idSite", tarefa.getIdSite());
        values.put("idFilial", tarefa.getIdFilial());

        alterou = cnn.inserirOuAtualizar( values,  "idFilial = ? and idTarefa = ?", new String[] {tarefa.getIdFilial().toString(), tarefa.getIdTarefa().toString()});

        return alterou;
    }



    public List<TarefaFilialModel> obterLista() {

        ArrayList<TarefaFilialModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE;
        String[] selectionArgs = new String[]{};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }


    public TarefaFilialModel obter(int idTarefa) {
        TarefaFilialModel tarefaFilialModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE idTarefa = ?";
        String[] selectionArgs = new String[]{"" + idTarefa};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            tarefaFilialModel = build(cursor);
        }

        return tarefaFilialModel;

    }

    public boolean excluir(Integer idFilial, Integer idTarefa) {

        long alterou = 0;

        alterou = cnn.deletar("idFilial = ? and idTarefa = ?", new String[] {idFilial.toString(), idTarefa.toString()});

        return alterou > 0;

    }

    public boolean excluir() {

        long alterou = 0;

        alterou = cnn.deletar("", new String[] {});

        return alterou > 0;

    }

    public boolean atualizar(TarefaFilialModel tarefa) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", tarefa.getId());
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("idSite", tarefa.getIdSite());
        values.put("idFilial", tarefa.getIdFilial());

        alterou = cnn.atualizar(values, "idFilial = ? and idTarefa = ?", new String[] {tarefa.getIdFilial().toString(), tarefa.getIdTarefa().toString()});

        return alterou > 0;

    }



    public TarefaFilialModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id;
        Integer idTarefa;
        String idSite;
        Integer idFilial;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idTarefa = cursor.getInt(cursor.getColumnIndex("idTarefa"));
        idSite = cursor.getString(cursor.getColumnIndex("idSite"));
        idFilial = cursor.getInt(cursor.getColumnIndex("idFilial"));

        return new TarefaFilialModel(id,idTarefa, idSite, idFilial);

    }


}
