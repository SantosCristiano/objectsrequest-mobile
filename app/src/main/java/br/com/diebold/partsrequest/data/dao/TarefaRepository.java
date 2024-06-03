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
import br.com.diebold.partsrequest.data.dao.model.PedidoModel;
import br.com.diebold.partsrequest.data.dao.model.PedidoProdutosModel;
import br.com.diebold.partsrequest.data.dao.model.SiteModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaFilialModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaModel;
import br.com.diebold.partsrequest.data.dao.model.TarefaSiteModel;


public class TarefaRepository implements ICrud {

    private final static String TABLE = "Tarefa";
    private static TarefaRepository instance;
    private ConexaoSQLite cnn = null;


    public static TarefaRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TarefaRepository(context);
        }
        return instance;
    }

    public TarefaRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }

    public long inserir(TarefaModel tarefa){

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", tarefa.getId());
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("dataAbertura", tarefa.getDataAbertura());
        values.put("dataLimiteAtendimento", tarefa.getDataLimiteAtendimento());
        values.put("dataLimiteSolucao", tarefa.getDataLimiteSolucao());
        values.put("dataAgendadaParaAtendimento", tarefa.getDataAgendadaParaAtendimento());
        values.put("descricaoDoEquipamento", tarefa.getDescricaoDoEquipamento());
        values.put("numeroDeSerieDoEquipamento", tarefa.getNumeroDeSerieDoEquipamento());
        values.put("attribute8", tarefa.getAttribute8());

        alterou = cnn.inserir( values);

         return alterou;
    }

    public long inserirOuAtualizar(TarefaModel tarefa){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(tarefa.getId() != null && tarefa.getId() > 0) {
            values.put("id", tarefa.getId());
        }
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("dataAbertura", tarefa.getDataAbertura());
        values.put("dataLimiteAtendimento", tarefa.getDataLimiteAtendimento());
        values.put("dataLimiteSolucao", tarefa.getDataLimiteSolucao());
        values.put("dataAgendadaParaAtendimento", tarefa.getDataAgendadaParaAtendimento());
        values.put("descricaoDoEquipamento", tarefa.getDescricaoDoEquipamento());
        values.put("numeroDeSerieDoEquipamento", tarefa.getNumeroDeSerieDoEquipamento());
        values.put("attribute8", tarefa.getAttribute8());

        alterou = cnn.inserirOuAtualizar( values,  "idTarefa = ?", new String[] {tarefa.getIdTarefa().toString()});

        return alterou;
    }



    public List<TarefaModel> obterLista() {

        ArrayList<TarefaModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE;
        String[] selectionArgs = new String[]{};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }


    public TarefaModel obter(int idTarefa) {
        TarefaModel tarefaModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE idTarefa = ?";
        String[] selectionArgs = new String[]{"" + idTarefa};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            tarefaModel = build(cursor);
        }

        return tarefaModel;

    }

    public boolean excluir(long idTarefa) {

        long alterou = 0;

        alterou = cnn.deletar("idTarefa = ?", new String[]{String.valueOf(idTarefa)});

        return alterou > 0;

    }

    public boolean excluir() {

        long alterou = 0;

        alterou = cnn.deletar("", new String[]{});

        return alterou > 0;

    }

    public boolean atualizar(TarefaModel tarefa) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", tarefa.getId());
        values.put("idTarefa", tarefa.getIdTarefa());
        values.put("dataAbertura", tarefa.getDataAbertura());
        values.put("dataLimiteAtendimento", tarefa.getDataLimiteAtendimento());
        values.put("dataLimiteSolucao", tarefa.getDataLimiteSolucao());
        values.put("dataAgendadaParaAtendimento", tarefa.getDataAgendadaParaAtendimento());
        values.put("descricaoDoEquipamento", tarefa.getDescricaoDoEquipamento());
        values.put("numeroDeSerieDoEquipamento", tarefa.getNumeroDeSerieDoEquipamento());
        values.put("attribute8", tarefa.getAttribute8());

        alterou = cnn.atualizar(values, "idTarefa = ?", new String[]{tarefa.getIdTarefa().toString()});

        return alterou > 0;

    }



    public TarefaModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id;
        Integer idTarefa;
        String dataAbertura;
        String dataLimiteAtendimento;
        String dataLimiteSolucao;
        String dataAgendadaParaAtendimento;
        String descricaoDoEquipamento;
        String numeroDeSerieDoEquipamento;
        String attribute8;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idTarefa = cursor.getInt(cursor.getColumnIndex("idTarefa"));
        dataAbertura = cursor.getString(cursor.getColumnIndex("dataAbertura"));
        dataLimiteAtendimento = cursor.getString(cursor.getColumnIndex("dataLimiteAtendimento"));
        dataLimiteSolucao = cursor.getString(cursor.getColumnIndex("dataLimiteSolucao"));
        dataAgendadaParaAtendimento = cursor.getString(cursor.getColumnIndex("dataAgendadaParaAtendimento"));
        descricaoDoEquipamento = cursor.getString(cursor.getColumnIndex("descricaoDoEquipamento"));
        numeroDeSerieDoEquipamento = cursor.getString(cursor.getColumnIndex("numeroDeSerieDoEquipamento"));
        attribute8 = cursor.getString(cursor.getColumnIndex("attribute8"));

        return new TarefaModel(id,
                idTarefa,
                dataAbertura,
                dataLimiteAtendimento,
                dataLimiteSolucao,
                dataAgendadaParaAtendimento,
                descricaoDoEquipamento,
                numeroDeSerieDoEquipamento,
                attribute8,
                new SiteModel()
                );

    }


}
