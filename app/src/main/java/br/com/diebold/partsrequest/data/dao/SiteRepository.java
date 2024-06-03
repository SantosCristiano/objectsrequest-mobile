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
import br.com.diebold.partsrequest.data.dao.model.PedidoModel;
import br.com.diebold.partsrequest.data.dao.model.PedidoProdutosModel;
import br.com.diebold.partsrequest.data.dao.model.SiteModel;


public class SiteRepository implements ICrud {

    private final static String TABLE = "Site";
    private static SiteRepository instance;
    private ConexaoSQLite cnn = null;


    public static SiteRepository getInstance(Context context) {
        if (instance == null) {
            instance = new SiteRepository(context);
        }
        return instance;
    }

    public SiteRepository(Context context) {
        cnn = new ConexaoSQLite(context, DataBaseName.PARTSREQUESTDB.getValue(), null, DataBaseVersion.PARTSREQUESTDB
                .getValue(), null, new PartsRequestDataBase(), TABLE);
    }

    public long inserir(SiteModel site){

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", site.getId());
        values.put("idSite", site.getIdSite());
        values.put("latitude", site.getLatitude());
        values.put("longitude", site.getLongitude());
        values.put("bairro", site.getBairro());
        values.put("cep", site.getCep());
        values.put("cidade", site.getCidade());
        values.put("endereco", site.getEndereco());
        values.put("estado", site.getEstado());
        values.put("pais", site.getPais());
        values.put("site", site.getSite());
        values.put("status", site.getStatus());
        values.put("telefone", site.getTelefone());
        values.put("cliente", site.getCliente());
        values.put("regiaoTecnica", site.getRegiaoTecnica());

        alterou = cnn.inserir( values);

         return alterou;
    }


    public long inserirOuAtualizar(SiteModel site){

        long alterou = 0;

        ContentValues values = new ContentValues();
        if(site.getId() != null && site.getId() > 0) {
            values.put("id", site.getId());
        }
        values.put("idSite", site.getIdSite());
        values.put("latitude", site.getLatitude());
        values.put("longitude", site.getLongitude());
        values.put("bairro", site.getBairro());
        values.put("cep", site.getCep());
        values.put("cidade", site.getCidade());
        values.put("endereco", site.getEndereco());
        values.put("estado", site.getEstado());
        values.put("pais", site.getPais());
        values.put("site", site.getSite());
        values.put("status", site.getStatus());
        values.put("telefone", site.getTelefone());
        values.put("cliente", site.getCliente());
        values.put("regiaoTecnica", site.getRegiaoTecnica());

        alterou = cnn.inserirOuAtualizar( values,  "idSite = ?", new String[] {site.getIdSite().toString()});

        return alterou;
    }



    public List<SiteModel> obterLista() {

        ArrayList<SiteModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE;
        String[] selectionArgs = new String[]{};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        while (cursor.moveToNext()) {
            lista.add(build(cursor));
        }

        return lista;

    }


    public SiteModel obter(String idSite) {
        SiteModel siteModel = null;

        String sql = "SELECT * FROM " + TABLE + " WHERE idSite = ?";
        String[] selectionArgs = new String[]{"" + idSite};
        Cursor cursor = cnn.executarQuerySql(sql, selectionArgs);

        if (cursor.moveToNext()) {
            siteModel = build(cursor);
        }

        return siteModel;

    }

    public boolean excluir(String idSite) {

        long alterou = 0;

        alterou = cnn.deletar("idSite = ?", new String[]{idSite});

        return alterou > 0;

    }

    public boolean excluir() {

        long alterou = 0;

        alterou = cnn.deletar("", new String[]{});

        return alterou > 0;

    }

    public boolean atualizar(SiteModel site) {

        long alterou = 0;

        ContentValues values = new ContentValues();
        values.put("id", site.getId());
        values.put("idSite", site.getIdSite());
        values.put("latitude", site.getLatitude());
        values.put("longitude", site.getLongitude());
        values.put("bairro", site.getBairro());
        values.put("cep", site.getCep());
        values.put("cidade", site.getCidade());
        values.put("endereco", site.getEndereco());
        values.put("estado", site.getEstado());
        values.put("pais", site.getPais());
        values.put("site", site.getSite());
        values.put("status", site.getStatus());
        values.put("telefone", site.getTelefone());
        values.put("cliente", site.getCliente());
        values.put("regiaoTecnica", site.getRegiaoTecnica());


        alterou = cnn.atualizar(values, "idSite = ?", new String[]{site.getIdSite().toString()});

        return alterou > 0;

    }



    public SiteModel build(Cursor cursor) {

        if (cursor.getCount() == 0) {
            return null;
        }

        Integer id;
        String idSite;
        double latitude;
        double longitude;
        String bairro;
        String cep;
        String cidade;
        String endereco;
        String estado;
        String pais;
        String site;
        String status;
        String telefone;
        String cliente;
        Integer regiaoTecnica;

        id = cursor.getInt(cursor.getColumnIndex("id"));
        idSite = cursor.getString(cursor.getColumnIndex("idSite"));
        latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
        longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
        bairro = cursor.getString(cursor.getColumnIndex("bairro"));
        cep = cursor.getString(cursor.getColumnIndex("cep"));
        cidade = cursor.getString(cursor.getColumnIndex("cidade"));
        endereco = cursor.getString(cursor.getColumnIndex("endereco"));
        estado = cursor.getString(cursor.getColumnIndex("estado"));
        pais = cursor.getString(cursor.getColumnIndex("pais"));
        site = cursor.getString(cursor.getColumnIndex("site"));
        status = cursor.getString(cursor.getColumnIndex("status"));
        telefone = cursor.getString(cursor.getColumnIndex("telefone"));
        cliente = cursor.getString(cursor.getColumnIndex("cliente"));
        regiaoTecnica = cursor.getInt(cursor.getColumnIndex("regiaoTecnica"));

        return new SiteModel(id,
                idSite,
                latitude,
                longitude,
                bairro,
                cep,
                cidade,
                endereco,
                estado,
                pais,
                site,
                status,
                telefone,
                cliente,
                regiaoTecnica,
                new FilialModel()
                );

    }


}
