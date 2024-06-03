package br.com.diebold.partsrequest.data.dao.dbHelper;

import android.database.sqlite.SQLiteDatabase;

import br.com.diebold.partsrequest.data.dao.model.FilialModel;
import br.com.diebold.partsrequest.data.dao.model.PedidoProdutosModel;

public class PartsRequestDataBase  implements DataBaseCreator {

    private String CREATE;

    @Override
    public void create(SQLiteDatabase db) {

        CREATE =
                "CREATE TABLE IF NOT EXISTS pedido"
                        + "("
                        +     "id Integer PRIMARY KEY AUTOINCREMENT,"
                        +     "idPedidoWS Integer,"
                        +     "stPstatusData varchar(100),"
                        +     "stPstatusUsuario  varchar(100),"
                        +     "stPstatusStatus varchar(100),"
                        +     "nomeLocalizacao varchar(255),"
                        +     "tipoPedido varchar(255),"
                        +     "idTarefa Integer,"
                        +     "prodTarefa  varchar(100),"
                        +     "nSerie Integer,"
                        +     "dtAbertura   varchar(100),"
                        +     "dtAgendamento  varchar(100),"
                        +     "prazoAtendimento  varchar(100),"
                        +     "prazoSolucao  varchar(100),"
                        +     "descricaoDoEquipamento  varchar(100),"
                        +     "numeroDeSerieDoEquipamento  varchar(100),"
                        +     "catEquipamento  varchar(100),"
                        +     "idSite  varchar(15),"
                        +     "nmSite  varchar(100),"
                        +     "enderecoSite  varchar(480),"
                        +     "regiaoTecnica Integer,"
                        +     "nomeFilial  varchar(255),"
                        +     "cliente  varchar(100),"
                        +     "idTecnico Integer,"
                        +     "nmTecnico  varchar(100),"
                        +     "eaLogradouro  varchar(255),"
                        +     "eaCep  varchar(255),"
                        +     "eaBairro  varchar(255),"
                        +     "eaCidade  varchar(255),"
                        +     "eaNum  varchar(255),"
                        +     "dtEntrega  varchar(100),"
                        +     "nomeEntregador  varchar(100),"
                        +     "telefoneEntregador  varchar(45),"
                        +     "observacao  varchar(255),"
                        +     "buscaPedido  char(1),"
                        +     "dataUltimaAlteracao varchar(100),"
                        +     "idTransporte Integer,"
                        +     "nomeTransporte varchar(45)"
                        + ")";

        db.execSQL(CREATE);

        CREATE =
                "CREATE TABLE IF NOT EXISTS tarefa"
                        + "("
                        +     "id Integer PRIMARY KEY AUTOINCREMENT,"
                        +     "idTarefa Integer,"
                        +     "dataAbertura varchar(50),"
                        +     "dataLimiteAtendimento  varchar(50),"
                        +     "dataLimiteSolucao varchar(50),"
                        +     "dataAgendadaParaAtendimento varchar(50),"
                        +     "descricaoDoEquipamento varchar(40),"
                        +     "numeroDeSerieDoEquipamento varchar(50),"
                        +     "attribute8  varchar(255)"
                        + ")";

        db.execSQL(CREATE);

        CREATE =
                "CREATE TABLE IF NOT EXISTS site"
                        + "("
                        +     "id Integer PRIMARY KEY AUTOINCREMENT,"
                        +     "idSite varchar(15),"
                        +     "latitude double,"
                        +     "longitude double,"
                        +     "bairro varchar(100),"
                        +     "cep varchar(9),"
                        +     "cidade varchar(100),"
                        +     "endereco varchar(480),"
                        +     "estado varchar(2),"
                        +     "pais varchar(50),"
                        +     "site varchar(100),"
                        +     "status varchar(1),"
                        +     "telefone varchar(30),"
                        +     "cliente varchar(255),"
                        +     "regiaoTecnica Intenger"
                        + ")";

        db.execSQL(CREATE);

        CREATE =
                "CREATE TABLE IF NOT EXISTS filial"
                        + "("
                        +     "id Integer PRIMARY KEY AUTOINCREMENT,"
                        +     "idFilial Integer,"
                        +     "nome varchar(255)"
                        + ")";

        db.execSQL(CREATE);

        CREATE =
                "CREATE TABLE IF NOT EXISTS tarefa_site"
                        + "("
                        +     "id Integer PRIMARY KEY AUTOINCREMENT,"
                        +     "idTarefa Integer,"
                        +     "idSite varchar(30)"
                        + ")";

        db.execSQL(CREATE);

        CREATE =
                "CREATE TABLE IF NOT EXISTS tarefa_filial"
                        + "("
                        +     "id Integer PRIMARY KEY AUTOINCREMENT,"
                        +     "idTarefa Integer,"
                        +     "idSite varchar(30),"
                        +     "idFilial Integer"
                        + ")";

        db.execSQL(CREATE);

        CREATE =
                "CREATE TABLE IF NOT EXISTS pedido_produtos"
                        + "("
                        +     "id Integer PRIMARY KEY AUTOINCREMENT,"
                        +     "idPedidoDB Integer,"
                        +     "idProduto Integer,"
                        +     "prodQtd Integer,"
                        +     "prodCodigo varchar(50),"
                        +     "prodName varchar(50),"
                        +     "itemCodigo varchar(50),"
                        +     "itemDescription varchar(255),"
                        +     "itemBom char(1),"
                        +     "dataUltimaAlteracao varchar(100)"
                        + ")";

        db.execSQL(CREATE);

        CREATE =
                "CREATE TABLE IF NOT EXISTS equipamentoBom"
                        + "("
                        +     "id Integer PRIMARY KEY AUTOINCREMENT,"
                        +     "idEquipamentoBom Integer,"
                        +     "productCode varchar(255),"
                        +     "productName varchar(255),"
                        +     "itemFamily varchar(255),"
                        +     "itemCode varchar(255),"
                        +     "itemDescription varchar(255)"
                        + ")";

        db.execSQL(CREATE);

    }
}
