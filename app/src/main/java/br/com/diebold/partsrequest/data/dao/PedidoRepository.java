package br.com.diebold.partsrequest.data.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.data.dao.dbHelper.DataBaseName;
import br.com.diebold.partsrequest.data.dao.dbHelper.DataBaseVersion;
import br.com.diebold.partsrequest.data.dao.dbHelper.ICrud;
import br.com.diebold.partsrequest.data.dao.dbHelper.PartsRequestDataBase;
import br.com.diebold.partsrequest.data.dao.model.PedidoModel;
import br.com.diebold.partsrequest.data.dao.dbHelper.ConexaoSQLite;
import br.com.diebold.partsrequest.data.dao.model.PedidoProdutosModel;
import br.com.diebold.partsrequest.data.dao.model.TransporteModel;
import br.com.diebold.partsrequest.utils.DateTime;
import br.com.diebold.partsrequest.utils.DateTimeFormat;


public class PedidoRepository  implements ICrud {

    private final static String TABLE = "Pedido";
    private static PedidoRepository instance;
    private ConexaoSQLite cnn = null;


    public static PedidoRepository getInstance(Context context) {
        if (instance == null) {
            instance = new PedidoRepository(context);
        }
        return instance;
    }

    public PedidoRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }



    public long inserir(PedidoModel pedido){

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", pedido.getId());
        values.put("idPedidoWS", pedido.getIdPedidoWS());
        values.put("stPstatusData", pedido.getStPstatusData());
        values.put("stPstatusUsuario", pedido.getStPstatusUsuario());
        values.put("stPstatusStatus", pedido.getStPstatusStatus());
        values.put("nomeLocalizacao", pedido.getNomeLocalizacao());
        values.put("tipoPedido", pedido.getTipoPedido());
        values.put("idTarefa", pedido.getIdTarefa());
        values.put("prodTarefa", pedido.getProdTarefa());
        values.put("nSerie", pedido.getnSerie());
        values.put("dtAbertura", pedido.getDtAbertura());
        values.put("dtAgendamento", pedido.getDtAgendamento());
        values.put("prazoAtendimento", pedido.getPrazoAtendimento());
        values.put("prazoSolucao", pedido.getPrazoSolucao());
        values.put("descricaoDoEquipamento", pedido.getDescricaoDoEquipamento());
        values.put("numeroDeSerieDoEquipamento", pedido.getNumeroDeSerieDoEquipamento());
        values.put("catEquipamento", pedido.getCatEquipamento());
        values.put("idSite", pedido.getIdSite());
        values.put("nmSite", pedido.getNmSite());
        values.put("enderecoSite", pedido.getEnderecoSite());
        values.put("regiaoTecnica", pedido.getRegiaoTecnica());
        values.put("nomeFilial", pedido.getNomeFilial());
        values.put("cliente", pedido.getCliente());
        values.put("idTecnico", pedido.getIdTecnico());
        values.put("nmTecnico", pedido.getNmTecnico());
        values.put("eaLogradouro", pedido.getEaLogradouro());
        values.put("eaCep", pedido.getEaCep());
        values.put("eaBairro", pedido.getEaBairro());
        values.put("eaCidade", pedido.getEaCidade());
        values.put("eaNum", pedido.getEaNum());
        values.put("dtEntrega", pedido.getDtEntrega());
        values.put("nomeEntregador", pedido.getNomeEntregador());
        values.put("telefoneEntregador", pedido.getTelefoneEntregador());
        values.put("observacao", pedido.getObservacao());
        if(pedido.isBuscaPedido()) {
            values.put("buscaPedido", "1");
        } else {
            values.put("buscaPedido", "0");
        }
        values.put("dataUltimaAlteracao", pedido.getDataUltimaAlteracao());
        values.put("idTransporte", pedido.getTransporte().getId());
        values.put("nomeTransporte", pedido.getTransporte().getNome());

        alterou = cnn.inserir( values);

         return alterou;
    }


    public long inserirOuAtualizar(PedidoModel pedido){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(pedido.getId() != null && pedido.getId() > 0) {
            values.put("id", pedido.getId());
        }
        values.put("idPedidoWS", pedido.getIdPedidoWS());
        values.put("stPstatusData", pedido.getStPstatusData());
        values.put("stPstatusUsuario", pedido.getStPstatusUsuario());
        values.put("stPstatusStatus", pedido.getStPstatusStatus());
        values.put("nomeLocalizacao", pedido.getNomeLocalizacao());
        values.put("tipoPedido", pedido.getTipoPedido());
        values.put("idTarefa", pedido.getIdTarefa());
        values.put("prodTarefa", pedido.getProdTarefa());
        values.put("nSerie", pedido.getnSerie());
        values.put("dtAbertura", pedido.getDtAbertura());
        values.put("dtAgendamento", pedido.getDtAgendamento());
        values.put("prazoAtendimento", pedido.getPrazoAtendimento());
        values.put("prazoSolucao", pedido.getPrazoSolucao());
        values.put("descricaoDoEquipamento", pedido.getDescricaoDoEquipamento());
        values.put("numeroDeSerieDoEquipamento", pedido.getNumeroDeSerieDoEquipamento());
        values.put("catEquipamento", pedido.getCatEquipamento());
        values.put("idSite", pedido.getIdSite());
        values.put("nmSite", pedido.getNmSite());
        values.put("enderecoSite", pedido.getEnderecoSite());
        values.put("regiaoTecnica", pedido.getRegiaoTecnica());
        values.put("nomeFilial", pedido.getNomeFilial());
        values.put("cliente", pedido.getCliente());
        values.put("idTecnico", pedido.getIdTecnico());
        values.put("nmTecnico", pedido.getNmTecnico());
        values.put("eaLogradouro", pedido.getEaLogradouro());
        values.put("eaCep", pedido.getEaCep());
        values.put("eaBairro", pedido.getEaBairro());
        values.put("eaCidade", pedido.getEaCidade());
        values.put("eaNum", pedido.getEaNum());
        values.put("dtEntrega", pedido.getDtEntrega());
        values.put("nomeEntregador", pedido.getNomeEntregador());
        values.put("telefoneEntregador", pedido.getTelefoneEntregador());
        values.put("observacao", pedido.getObservacao());
        if(pedido.isBuscaPedido()) {
            values.put("buscaPedido", "1");
        } else {
            values.put("buscaPedido", "0");
        }
        values.put("dataUltimaAlteracao", pedido.getDataUltimaAlteracao());
        values.put("idTransporte", pedido.getTransporte().getId());
        values.put("nomeTransporte", pedido.getTransporte().getNome());

        alterou = cnn.inserirOuAtualizar( values,  "idPedidoWS = ?", new String[] {pedido.getIdPedidoWS().toString()});

        return alterou;
    }



    public List<PedidoModel> obterListaPorStatus(String[] status, Integer tecnico) {

        ArrayList<PedidoModel> lista = new ArrayList<>();
        String statusWhere = " stPstatusStatus = ? ";
        for (int i = 0; i < status.length; i++) {
            if(i > 0) {
                statusWhere += " OR stPstatusStatus = ? ";
            }
        }

        String sql = "SELECT * FROM " + TABLE + " WHERE  ("+ statusWhere + ") AND idTecnico = ? ORDER BY id DESC";
        String[] selectionArgs = new String[status.length + 1]; //total da variavel status + 1 para o id tecnico.


        for (int i = 0; i < status.length; i++) {
                selectionArgs[i] = status[i];
        }

        selectionArgs[selectionArgs.length -1] = tecnico.toString();

        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }

    public List<PedidoModel> obterLista(Integer tecnico) {

        ArrayList<PedidoModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE + " WHERE id > ? and idTecnico = ? ORDER BY id DESC";
        String[] selectionArgs = new String[]{"0", tecnico.toString()};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }


    public PedidoModel obter(int id) {
        PedidoModel pedidoModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        String[] selectionArgs = new String[]{"" + id};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            pedidoModel = build(cursor);
        }

        return pedidoModel;

    }

    public PedidoModel obter(String idPedidoWS) {
        PedidoModel pedidoModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE idPedidoWS = ?";
        String[] selectionArgs = new String[]{"" + idPedidoWS};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            pedidoModel = build(cursor);
        }

        return pedidoModel;

    }

    public PedidoModel obter(String nomeDaColuna, String valor) {
        PedidoModel pedidoModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE " + nomeDaColuna + " = ?";
        String[] selectionArgs = new String[]{"" + valor};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            pedidoModel = build(cursor);
        }

        return pedidoModel;

    }

    public PedidoModel obter(String nomeDaColuna1, String valor1, String nomeDaColuna2, String valor2) {
        PedidoModel pedidoModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE " + nomeDaColuna1 + " = ? AND " + nomeDaColuna2 + " = ?";
        String[] selectionArgs = new String[]{valor1, valor2};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            pedidoModel = build(cursor);
        }

        return pedidoModel;

    }

    public boolean excluir(long idPedidoWS) {

        long alterou = 0;

        alterou = cnn.deletar("idPedidoWS = ?", new String[]{String.valueOf(idPedidoWS)});

        return alterou > 0;

    }

    public boolean atualizar(PedidoModel pedido) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", pedido.getId());
        values.put("idPedidoWS", pedido.getIdPedidoWS());
        values.put("stPstatusData", pedido.getStPstatusData());
        values.put("stPstatusUsuario", pedido.getStPstatusUsuario());
        values.put("stPstatusStatus", pedido.getStPstatusStatus());
        values.put("nomeLocalizacao", pedido.getNomeLocalizacao());
        values.put("tipoPedido", pedido.getTipoPedido());
        values.put("idTarefa", pedido.getIdTarefa());
        values.put("prodTarefa", pedido.getProdTarefa());
        values.put("nSerie", pedido.getnSerie());
        values.put("dtAbertura", pedido.getDtAbertura());
        values.put("dtAgendamento", pedido.getDtAgendamento());
        values.put("prazoAtendimento", pedido.getPrazoAtendimento());
        values.put("prazoSolucao", pedido.getPrazoSolucao());
        values.put("descricaoDoEquipamento", pedido.getDescricaoDoEquipamento());
        values.put("numeroDeSerieDoEquipamento", pedido.getNumeroDeSerieDoEquipamento());
        values.put("catEquipamento", pedido.getCatEquipamento());
        values.put("idSite", pedido.getIdSite());
        values.put("nmSite", pedido.getNmSite());
        values.put("enderecoSite", pedido.getEnderecoSite());
        values.put("regiaoTecnica", pedido.getRegiaoTecnica());
        values.put("nomeFilial", pedido.getNomeFilial());
        values.put("cliente", pedido.getCliente());
        values.put("idTecnico", pedido.getIdTecnico());
        values.put("nmTecnico", pedido.getNmTecnico());
        values.put("eaLogradouro", pedido.getEaLogradouro());
        values.put("eaCep", pedido.getEaCep());
        values.put("eaBairro", pedido.getEaBairro());
        values.put("eaCidade", pedido.getEaCidade());
        values.put("eaNum", pedido.getEaNum());
        values.put("dtEntrega", pedido.getDtEntrega());
        values.put("nomeEntregador", pedido.getNomeEntregador());
        values.put("telefoneEntregador", pedido.getTelefoneEntregador());
        values.put("observacao", pedido.getObservacao());
        if(pedido.isBuscaPedido()) {
            values.put("buscaPedido", "1");
        } else {
            values.put("buscaPedido", "0");
        }
        values.put("dataUltimaAlteracao", pedido.getDataUltimaAlteracao());
        values.put("idTransporte", pedido.getTransporte().getId());
        values.put("nomeTransporte", pedido.getTransporte().getNome());

        alterou = cnn.atualizar(values, "id = ?", new String[]{pedido.getId().toString()});

        return alterou > 0;

    }

    public boolean atualizar(String nomeDaColunaWhere, String valorWhere, String nomeDaColunaUpdate, String valorUpdate) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put(nomeDaColunaUpdate, valorUpdate);
        values.put("dataUltimaAlteracao", DateTime.DateToString(DateTime.ActuallyDate(), DateTimeFormat.DATEANDTIME_UTC.getValue()));

        alterou = cnn.atualizar(values, nomeDaColunaWhere + " = ?", new String[]{valorWhere});

        return alterou > 0;

    }



    @SuppressLint("Range")
    public PedidoModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id, idPedido;
        String stPstatusData;
        String stPstatusUsuario;
        String stPstatusStatus;
        String nomeLocalizacao;
        String tipoPedido;
        Integer idTarefa;
        String prodTarefa;
        Integer nSerie;
        String dtAbertura;
        String dtAgendamento;
        String prazoAtendimento;
        String prazoSolucao;
        String descricaoDoEquipamento;
        String numeroDeSerieDoEquipamento;
        String catEquipamento;
        String idSite;
        String nmSite;
        String enderecoSite;
        Integer regiaoTecnica;
        String nomeFilial;
        String cliente;
        Integer idTecnico;
        String nmTecnico;
        String eaLogradouro;
        String eaCep;
        String eaBairro;
        String eaCidade;
        String eaNum;
        String dtEntrega;
        String nomeEntregador;
        String telefoneEntregador;
        String observacao;
        boolean buscaPedido;
        String dataUltimaAlteracao;
        Integer idTransporte;
        String nomeTransporte;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idPedido = cursor.getInt(cursor.getColumnIndex("idPedidoWS"));
        stPstatusData = cursor.getString(cursor.getColumnIndex("stPstatusData"));
        stPstatusUsuario = cursor.getString(cursor.getColumnIndex("stPstatusUsuario"));
        stPstatusStatus = cursor.getString(cursor.getColumnIndex("stPstatusStatus"));
        nomeLocalizacao = cursor.getString(cursor.getColumnIndex("nomeLocalizacao"));
        tipoPedido = cursor.getString(cursor.getColumnIndex("tipoPedido"));

        idTarefa = cursor.getInt(cursor.getColumnIndex("idTarefa"));
        if(idTarefa == 0) {
            idTarefa = null;
        }
        prodTarefa = cursor.getString(cursor.getColumnIndex("prodTarefa"));
        nSerie = cursor.getInt(cursor.getColumnIndex("nSerie"));
        dtAbertura = cursor.getString(cursor.getColumnIndex("dtAbertura"));
        dtAgendamento = cursor.getString(cursor.getColumnIndex("dtAgendamento"));
        prazoAtendimento = cursor.getString(cursor.getColumnIndex("prazoAtendimento"));
        prazoSolucao = cursor.getString(cursor.getColumnIndex("prazoSolucao"));
        descricaoDoEquipamento = cursor.getString(cursor.getColumnIndex("descricaoDoEquipamento"));
        numeroDeSerieDoEquipamento = cursor.getString(cursor.getColumnIndex("numeroDeSerieDoEquipamento"));
        catEquipamento = cursor.getString(cursor.getColumnIndex("catEquipamento"));
        idSite = cursor.getString(cursor.getColumnIndex("idSite"));
        nmSite = cursor.getString(cursor.getColumnIndex("nmSite"));
        enderecoSite = cursor.getString(cursor.getColumnIndex("enderecoSite"));
        regiaoTecnica = cursor.getInt(cursor.getColumnIndex("regiaoTecnica"));
        nomeFilial = cursor.getString(cursor.getColumnIndex("nomeFilial"));
        cliente = cursor.getString(cursor.getColumnIndex("cliente"));
        idTecnico = cursor.getInt(cursor.getColumnIndex("idTecnico"));
        nmTecnico = cursor.getString(cursor.getColumnIndex("nmTecnico"));
        eaLogradouro = cursor.getString(cursor.getColumnIndex("eaLogradouro"));
        eaCep = cursor.getString(cursor.getColumnIndex("eaCep"));
        eaBairro = cursor.getString(cursor.getColumnIndex("eaBairro"));
        eaCidade = cursor.getString(cursor.getColumnIndex("eaCidade"));
        eaNum = cursor.getString(cursor.getColumnIndex("eaNum"));
        dtEntrega = cursor.getString(cursor.getColumnIndex("dtEntrega"));
        nomeEntregador = cursor.getString(cursor.getColumnIndex("nomeEntregador"));
        telefoneEntregador = cursor.getString(cursor.getColumnIndex("telefoneEntregador"));
        observacao = cursor.getString(cursor.getColumnIndex("observacao"));
        if (cursor.getString(cursor.getColumnIndex("buscaPedido")).equals("0")) {
            buscaPedido = false;
        } else {
            buscaPedido = true;
        }
        dataUltimaAlteracao = cursor.getString(cursor.getColumnIndex("dataUltimaAlteracao"));
        idTransporte = cursor.getInt(cursor.getColumnIndex("idTransporte"));
        nomeTransporte = cursor.getString(cursor.getColumnIndex("nomeTransporte"));

        return new PedidoModel(id,
                idPedido,
                stPstatusData,
                stPstatusUsuario,
                stPstatusStatus,
                nomeLocalizacao,
                tipoPedido,
                idTarefa,
                prodTarefa,
                nSerie,
                dtAbertura,
                dtAgendamento,
                prazoAtendimento,
                prazoSolucao,
                descricaoDoEquipamento,
                numeroDeSerieDoEquipamento,
                catEquipamento,
                idSite,
                nmSite,
                enderecoSite,
                regiaoTecnica,
                nomeFilial,
                cliente,
                idTecnico,
                nmTecnico,
                new ArrayList<PedidoProdutosModel>(),
                eaLogradouro,
                eaCep,
                eaBairro,
                eaCidade,
                eaNum,
                dtEntrega,
                nomeEntregador,
                telefoneEntregador,
                observacao,
                buscaPedido,
                dataUltimaAlteracao,
                new TransporteModel(idTransporte, nomeTransporte)
                );

    }


}
