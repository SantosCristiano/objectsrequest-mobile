package br.com.diebold.partsrequest.data.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiUsuarioPartsRequestService {

    @POST("/partsrequest/login")
    Call<ResponseBody> login(@Body RequestBody request);

    @POST("/partsrequest/usuarios/mobile/ObterUsuario")
    Call<ResponseBody> usuario(@Body RequestBody request);

    @POST("/partsrequest/usuarios/alterarSenha")
    Call<ResponseBody> alterarSenha(@Body RequestBody request);

}
