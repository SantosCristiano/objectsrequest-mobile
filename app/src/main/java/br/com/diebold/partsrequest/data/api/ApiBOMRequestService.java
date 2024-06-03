package br.com.diebold.partsrequest.data.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiBOMRequestService {
    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("/Intmfsorc/v2/MFSWebService.asmx")
    Call<ResponseBody> ObterEquipamentosBomMFS(@Body BOMRequestEnvelope body);

    @Headers({
            "Content-Type: application/soap+xml",
            "Accept-Charset: utf-8"
    })
    @POST("/Intmfsorc/v2/MFSWebService.asmx")
    Call<ResponseBody> ObterEquipamentosFullBomMFS(@Body FullBOMRequestEnvelope body);
}
