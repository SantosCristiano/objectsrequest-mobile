package br.com.diebold.partsrequest.data.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiPedidosPartsRequestService {


    @POST("/partsrequest/pedidos/mobile/ObterPedidoPorStatus")
    Call<ResponseBody> ObterPedidoPorStatus(@Body RequestBody request);

    @POST("/partsrequest/pedidos/mobile/ObterPedidoPorTipo")
    Call<ResponseBody> ObterPedidoPorTipo(@Body RequestBody request);

    @POST("/partsrequest/pedidos/mobile/ObterPedidoPorCodigo")
    Call<ResponseBody> ObterPedidoPorCodigo(@Body RequestBody request);

    @POST("/partsrequest/pedidos/mobile/ObterPedidoPorTecnico")
    Call<ResponseBody> ObterPedidoPorTecnico(@Body RequestBody request);

    @POST("/partsrequest/pedidos/mobile/IncluirPedido")
    Call<ResponseBody> IncluirPedido(@Body RequestBody request);

    @POST("/partsrequest/pedidos/mobile/AlterarPedidoStatus")
    Call<ResponseBody> AlterarPedidoStatus(@Body RequestBody request);

    @POST("/partsrequest/pedidos/mobile/AlterarPedido")
    Call<ResponseBody> AlterarPedido(@Body RequestBody request);

    @POST("/partsrequest/verificacao/mobile/ObterDadosPedidoPelaTask")
    Call<ResponseBody> ObterDadosPedidoPelaTask(@Body RequestBody request);

    @POST("/partsrequest/verificacao/mobile/ObterDadosPedidoPeloSite")
    Call<ResponseBody> ObterDadosPedidoPeloSite(@Body RequestBody request);

    @POST("/partsrequest/verificacao/mobile/ObterDadosPedidoPeloTecnico")
    Call<ResponseBody> ObterDadosPedidoPeloTecnico(@Body RequestBody request);

    @POST("/partsrequest/verificacao/mobile/ObterTarefasPeloTecnico")
    Call<ResponseBody> ObterTarefasPeloTecnico(@Body RequestBody request);

    @POST("/partsrequest/bom/mobile/ObterEquipamentos")
    Call<ResponseBody> ObterEquipamentos(@Body RequestBody request);

    @POST("/partsrequest/bom/mobile/ObterEquipamentosBom")
    Call<ResponseBody> ObterEquipamentosBom(@Body RequestBody request);

    @POST("/partsrequest/bom/mobile/ObterTransportes")
    Call<ResponseBody> ObterTransportes(@Body RequestBody request);

    @POST("/partsrequest/pedidos/mobile/confirmarPedidosRecebidos")
    Call<ResponseBody> ConfirmarPedidosRecebidos(@Body RequestBody request);
}
