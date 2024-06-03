package br.com.diebold.partsrequest.data.api;

import android.content.Context;
import android.util.Log;

//import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.data.api.request.PedidoRequest;
import br.com.diebold.partsrequest.data.api.response.ApiErrorResponse;
import br.com.diebold.partsrequest.data.api.response.EquipamentoBomItemResponse;
import br.com.diebold.partsrequest.data.api.response.EquipamentoBomResponse;
import br.com.diebold.partsrequest.data.api.response.EquipamentoItemResponse;
import br.com.diebold.partsrequest.data.api.response.EquipamentoResponse;
import br.com.diebold.partsrequest.data.api.response.LoginResponse;
import br.com.diebold.partsrequest.data.api.response.PedidoItemResponse;
import br.com.diebold.partsrequest.data.api.response.PedidoResponse;
import br.com.diebold.partsrequest.data.api.response.TarefaItemResponse;
import br.com.diebold.partsrequest.data.api.response.TarefaResponse;
import br.com.diebold.partsrequest.data.api.response.TransporteResponse;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.data.api.response.VerificaTarefaResponse;
import br.com.diebold.partsrequest.utils.DateTime;
import br.com.diebold.partsrequest.utils.GeneralUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
//import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
//import br.com.diebold.partsrequest.data.api.listener.ResponseListener;

public class ApiServices {

    private static Context mContext;
    private static ApiServices instance;
    private Retrofit retrofit;
    private static Call<?> currentCall;

    private ApiServices() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (br.com.diebold.partsrequest.BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        try {

            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder builder = getBuilder(original);

                            if (PreferencesUserUtil.isLogged(mContext)) {
                                builder.get().addHeader("Authorization", "Bearer " + PreferencesUserUtil.getToken(mContext));
                            }

                            Request request = builder.method(original.method(), original.body()).build();
                            return chain.proceed(request);
                        }
                    })
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .callTimeout(60, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(GeneralUtils.getStringResourceByName(mContext, "EndPointPRWS"))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Request.Builder getBuilder(Request request) {
        Request.Builder builder = request.newBuilder();

        builder.addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json");

        return builder;
    }

    public static ApiServices getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new ApiServices();
        }
        return instance;
    }

    public static ApiServices getInstance(Context context, boolean renewInstance) {
        if (!renewInstance) {
            if (instance == null) {
                instance = new ApiServices();
            }
            return instance;
        } else {
            mContext = context;
            return new ApiServices();
        }
    }

    public static void cancelCurrentCall() {
        if (currentCall != null && !currentCall.isExecuted() && !currentCall.isCanceled())
            currentCall.cancel();
    }

    private void setCurrentCall(Call<?> currentCall) {
        this.currentCall = currentCall;
    }

    public static Call<?> getCurrentCall() {
        return currentCall;
    }

    private String getError(ResponseBody errorBody, String responseCode) {
        String errorMessage = null;
        try {
            Gson gson = new GsonBuilder().create();

            ApiErrorResponse apiErrorResponse = gson.fromJson(errorBody.charStream(), ApiErrorResponse.class);

            errorMessage = apiErrorResponse.getMessage();

        } catch (Exception e) {
            Log.e("**ERR", e.getMessage());
        }

        return errorMessage != null ? errorMessage : responseCode;
    }

    private ApiErrorResponse getErrorApi(Response response) {

        ApiErrorResponse apiErrorPersonResponse = null;

        try {

            if(response.errorBody() != null) {
                Gson gson = new GsonBuilder().create();

                apiErrorPersonResponse = gson.fromJson(response.errorBody().charStream(), ApiErrorResponse.class);

                if (apiErrorPersonResponse != null && apiErrorPersonResponse.getMessage() != null
                        && apiErrorPersonResponse.getMessage().contains("failed to connect to")) {
                    apiErrorPersonResponse.setMessage("Não foi possível conectar ao servidor!");
                }
            }else{
                apiErrorPersonResponse.setMessage("Erro ao executar operação: Código " + response.code() + " -> " + response.message());
            }
        } catch (Exception e) {
            apiErrorPersonResponse.setMessage("**ERR - Não foi possível conectar ao servidor!");
            Log.e("**ERR", e.getMessage());
        }
        return apiErrorPersonResponse;
    }

    private String treatFailureMessage(Throwable t) {
        String message;
        if (t instanceof SocketTimeoutException) {
            message = mContext.getString(R.string.timeout_exception);
        } else if (t instanceof UnknownHostException) {
            message = mContext.getString(R.string.unknown_host_exception);
        } else {
            message = mContext.getString(R.string.unknown_exception);
            Log.e("API", t.getMessage());
        }

        return message;
    }

    public <T> T getDynamicService(Class<T> serviceInstance) {
        return retrofit.create(serviceInstance);
    }

//    public <T> void getGenericCall(final AppCompatActivity context, Call<T> call, final ResponseListener<T> listener) {
//        setCurrentCall(call);
//        call.enqueue(new Callback<T>() {
//            @Override
//            public void onResponse(Call<T> call, Response<T> response) {
//                if (null == listener) return;
//                if (response.isSuccessful()) {
//                    listener.onSuccess(response.body());
//                } else {
//                        listener.onFailure(getError(response.errorBody(), String.valueOf(response.code())));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<T> call, Throwable t) {
//                listener.onFailure(treatFailureMessage(t));
//            }
//        });
//    }

    private void forceLogout() {
        PreferencesUserUtil.logout(mContext);
    }


    public LoginResponse userLogin(String usuario, String senha) throws IOException {

        LoginResponse response = new LoginResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("usuario", usuario);
        jsonObject.addProperty("senha", senha);

        //------------------------------------------------------------------------------------------
        ApiUsuarioPartsRequestService service = retrofit.create(ApiUsuarioPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.login(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            response.setSucesso(true);
            response.setToken(responseService.headers().get("Authorization").replace("Bearer ", ""));

        }else {
            response.setSucesso(false);
            response.setMessage(responseService.message());
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public UsuarioResponse userInfo(String usuario) throws IOException {

        UsuarioResponse response = new UsuarioResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("usuario", usuario);

        ApiUsuarioPartsRequestService service = retrofit.create(ApiUsuarioPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.usuario(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            response = gson.fromJson(reader, UsuarioResponse.class);
            response.setSuccess(true);
        }else {
            response = new UsuarioResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public UsuarioResponse alterarSenha(String usuario, String senha) throws IOException {

        UsuarioResponse response = new UsuarioResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("usuario", usuario);
        jsonObject.addProperty("senha", senha);

        ApiUsuarioPartsRequestService service = retrofit.create(ApiUsuarioPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.alterarSenha(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            response.setSuccess(true);
        }else {
            response = new UsuarioResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public PedidoResponse obterPedidosPorStatus(String status, Integer tecnico) throws IOException {

        PedidoResponse response = new PedidoResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", status);
        jsonObject.addProperty("tecnico", tecnico);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ObterPedidoPorStatus(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            Type listaType = new TypeToken<ArrayList<PedidoItemResponse>>(){}.getType();
            response.setPedidos (gson.fromJson(reader,listaType ));
            response.setSuccess(true);
        }else {
            response = new PedidoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public PedidoResponse obterPedidosPorTipo(String tipo, String tecnico) throws IOException {

        PedidoResponse response = new PedidoResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tipo", tipo);
        jsonObject.addProperty("tecnico", tecnico);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ObterPedidoPorTipo(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            Type listaType = new TypeToken<ArrayList<PedidoItemResponse>>(){}.getType();
            response.setPedidos (gson.fromJson(reader,listaType ));
            response.setSuccess(true);
        }else {
            response = new PedidoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public PedidoResponse obterPedidosPorCodigo(String codigo, String tecnico) throws IOException {

        PedidoResponse response = new PedidoResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("codigo", codigo);
        jsonObject.addProperty("tecnico", tecnico);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ObterPedidoPorCodigo(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            Type listaType = new TypeToken<ArrayList<PedidoItemResponse>>(){}.getType();
            response.setPedidos (gson.fromJson(reader,listaType ));
            response.setSuccess(true);
        }else {
            response = new PedidoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public VerificaTarefaResponse obterDadosPedidoPelaTask(String tarefa) throws IOException {
        VerificaTarefaResponse response = new VerificaTarefaResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("idTarefa", tarefa);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ObterDadosPedidoPelaTask(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            response = gson.fromJson(reader, VerificaTarefaResponse.class);
            response.setSuccess(true);
        }else {
            response = new VerificaTarefaResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public PedidoResponse obterDadosPedidoPeloSite(String site) throws IOException {

        PedidoResponse response = new PedidoResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("site", site);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ObterDadosPedidoPeloSite(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            response = gson.fromJson(reader, PedidoResponse.class);
            response.setSuccess(true);
        }else {
            response = new PedidoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public PedidoResponse obterDadosPedidoPeloTecnico(Integer tecnico, boolean todos) throws IOException {

        PedidoResponse response = new PedidoResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tecnico", tecnico);
        jsonObject.addProperty("todos", todos);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ObterPedidoPorTecnico(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            Type listaType = new TypeToken<ArrayList<PedidoItemResponse>>(){}.getType();
            response.setPedidos(gson.fromJson(reader,listaType));
            response.setSuccess(true);

        }else {
            response = new PedidoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public TarefaResponse obterTarefasPeloTecnico(int tecnico) throws IOException {

        TarefaResponse response = new TarefaResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("idTecnico", tecnico);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ObterTarefasPeloTecnico(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            Type listaType = new TypeToken<ArrayList<TarefaItemResponse>>(){}.getType();
            response.setTarefas(gson.fromJson(reader,listaType));
            response.setSuccess(true);
        }else {
            response = new TarefaResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public EquipamentoResponse obterEquipamentos() throws IOException {

        EquipamentoResponse response = new EquipamentoResponse();
        Response responseService;

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;

        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), "");
        call = service.ObterEquipamentos(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            Type listaType = new TypeToken<ArrayList<EquipamentoItemResponse>>(){}.getType();
            response.setEquipamentos(gson.fromJson(reader,listaType));
            response.setSuccess(true);
        }else {
            response = new EquipamentoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public EquipamentoBomResponse obterEquipamentosBom(String equipamento) throws IOException {

        EquipamentoBomResponse response = new EquipamentoBomResponse();
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("equipamento", equipamento);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ObterEquipamentosBom(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            Type listaType = new TypeToken<ArrayList<EquipamentoBomItemResponse>>(){}.getType();
            response.setEquipamentosBom(gson.fromJson(reader,listaType));
            response.setSuccess(true);
        }else {
            response = new EquipamentoBomResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public EquipamentoBomResponse obterEquipamentosFullBomMFS(int pagina) throws IOException {

        EquipamentoBomResponse response = new EquipamentoBomResponse();
        Response responseService;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (br.com.diebold.partsrequest.BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Strategy strategy = new AnnotationStrategy();

        Serializer serializer = new Persister(strategy);

        try {

            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();

            Retrofit retrofitService = new Retrofit.Builder()
                    .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                    .baseUrl(GeneralUtils.getStringResourceByName(mContext, "EndPointMFS"))
                    .client(okHttpClient)
                    .build();

            ApiBOMRequestService service = retrofitService.create(ApiBOMRequestService.class);

            Call<ResponseBody> call = null;
            FullBOMRequestData data = new FullBOMRequestData(pagina, "");
            FullBOMRequestBody body = new FullBOMRequestBody(data);
            FullBOMRequestEnvelope envelope = new FullBOMRequestEnvelope(body);

            call = service.ObterEquipamentosFullBomMFS(envelope);

            responseService = call.execute();

            if (responseService.isSuccessful()) {
                Gson gson = new GsonBuilder().create();
                InputStream is = ((ResponseBody) responseService.body()).byteStream();
                String equipamentoBOM = obterEstruturaFullBOMResponse(is);

                JSONObject jsonObj = new JSONObject(equipamentoBOM);
                JSONArray array = jsonObj.getJSONArray("BOM");

                List<EquipamentoBomItemResponse> equipamentoBomItemResponse = new ArrayList<EquipamentoBomItemResponse>();

                Type listaType = new TypeToken<ArrayList<EquipamentoBomItemResponse>>(){}.getType();


                equipamentoBomItemResponse.addAll(gson.fromJson(array.toString(),listaType));

                response.setEquipamentosBom(equipamentoBomItemResponse);
                response.setSuccess(true);
            } else {
                response = new EquipamentoBomResponse();
                response.setSuccess(false);
                response.setError(getErrorApi(responseService));
            }

        } catch (Exception e) {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.setTimestamp(DateTime.ActuallyDate().toString());
            apiErrorResponse.setMessage(mContext.getString(R.string.erro_obter_estutura_produto));
            apiErrorResponse.setStatus(204);
            apiErrorResponse.setPath("ApiServices.obterEquipamentosFullBomMFS");
            response.setError(apiErrorResponse);

        }

        return response;

    }

    public String obterEstruturaFullBOMResponse(InputStream xmlInputStream)
            throws XmlPullParserException, IOException {
        String equipamentoFullBomItemResponse = null;
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(xmlInputStream, "UTF-8");
        int eventType = xpp.getEventType();
        String tag = "";
        String lineValue = "";
        int i = 0;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                tag = "<" + xpp.getName() + ">";
                // Log.i(LogCat.TAG, tag);
            } else if (eventType == XmlPullParser.TEXT) {
                if (!xpp.getText().equals("\n")) {
                    lineValue = xpp.getText().trim();
                    // Log.i(LogCat.TAG, lineValue);
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                tag = "</" + xpp.getName() + ">";
                // Log.i(LogCat.TAG, tag);

                if (tag.equals("</GetBomFullPaginationJsonResult>")) {
                    equipamentoFullBomItemResponse = lineValue;
                    // Log.i(LogCat.TAG, Integer.toString(i));
                    i++;
                }
            }
            eventType = xpp.next();
        }

        return equipamentoFullBomItemResponse;
    }


    public EquipamentoBomResponse obterEquipamentosBomMFS(String equipamento) throws IOException {

        EquipamentoBomResponse response = new EquipamentoBomResponse();
        Response responseService;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (br.com.diebold.partsrequest.BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Strategy strategy = new AnnotationStrategy();

        Serializer serializer = new Persister(strategy);

        try {

            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();

            Retrofit retrofitService = new Retrofit.Builder()
                    .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                    .baseUrl(GeneralUtils.getStringResourceByName(mContext, "EndPointMFS"))
                    .client(okHttpClient)
                    .build();

            ApiBOMRequestService service = retrofitService.create(ApiBOMRequestService.class);

            Call<ResponseBody> call = null;
            BOMRequestData data = new BOMRequestData(equipamento);
            BOMRequestBody body = new BOMRequestBody(data);
            BOMRequestEnvelope envelope = new BOMRequestEnvelope(body);

            call = service.ObterEquipamentosBomMFS(envelope);

            responseService = call.execute();

            if (responseService.isSuccessful()) {
                InputStream is = ((ResponseBody) responseService.body()).byteStream();
                List<EquipamentoBomItemResponse> equipamentoBOM = obterEstruturaBOMResponse(is);
                response.setEquipamentosBom(equipamentoBOM);
                response.setSuccess(true);
            } else {
                response = new EquipamentoBomResponse();
                response.setSuccess(false);
                response.setError(getErrorApi(responseService));
            }

        } catch (Exception e) {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.setTimestamp(DateTime.ActuallyDate().toString());
            apiErrorResponse.setMessage(mContext.getString(R.string.erro_obter_estutura_produto) + equipamento);
            apiErrorResponse.setStatus(204);
            apiErrorResponse.setPath("ApiServices.obterEquipamentosBomMFS");
            response.setError(apiErrorResponse);

        }

        return response;

    }

    public List<EquipamentoBomItemResponse> obterEstruturaBOMResponse(InputStream xmlInputStream)
            throws XmlPullParserException, IOException {
        ArrayList<EquipamentoBomItemResponse> equipamentoBomItemResponses = new ArrayList<EquipamentoBomItemResponse>();
        EquipamentoBomItemResponse bom = new EquipamentoBomItemResponse();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(xmlInputStream, "UTF-8");
        int eventType = xpp.getEventType();
        String tag = "";
        String lineValue = "";
        int i = 0;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                tag = "<" + xpp.getName() + ">";
                // Log.i(LogCat.TAG, tag);
            } else if (eventType == XmlPullParser.TEXT) {
                if (!xpp.getText().equals("\n")) {
                    lineValue = xpp.getText().trim();
                    // Log.i(LogCat.TAG, lineValue);
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                tag = "</" + xpp.getName() + ">";

                if (bom == null) {
                    bom = new EquipamentoBomItemResponse();
                }

                if (tag.equals("</ProductCode>") | tag.equals("</PRODUCT_CODE>")) {
                    bom.setProductCode(lineValue.trim());
                } else if (tag.equals("</ProductName>")| tag.equals("</PRODUCT_NAME>")) {
                    bom.setProductName(lineValue.trim());
                } else if (tag.equals("</ItemFamily>")| tag.equals("</ITEM_FAMILY>")) {
                    bom.setItemFamily(lineValue.trim());
                } else if (tag.equals("</ItemCode>")| tag.equals("</ITEM_CODE>")) {
                    bom.setItemCode(lineValue.trim());
                } else if (tag.equals("</ItemDescription>")| tag.equals("</ITEM_DESCRIPTION>")) {
                    bom.setItemDescription(lineValue.trim());
                } else if (tag.equals("</Bom>")) {
                    equipamentoBomItemResponses.add(bom);
                    // Log.i(LogCat.TAG, Integer.toString(i));
                    bom = null;
                    i++;
                }
            }
            eventType = xpp.next();
        }

        return equipamentoBomItemResponses;
    }

    public TransporteResponse obterTransportes() throws IOException {

        TransporteResponse response = new TransporteResponse();
        Response responseService;

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;

        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), "");
        call = service.ObterTransportes(body);

        responseService = call.execute();

//        if(responseService.isSuccessful()) {
//            Gson gson = new GsonBuilder().create();
//            Reader reader =  ((ResponseBody) responseService.body()).charStream();
//            Type listaType = new TypeToken<ArrayList<TransporteItemResponse>>(){}.getType();
//            response.setTransportes(gson.fromJson(reader,listaType));
//            response.setSuccess(true);
//        }else {
//            response = new TransporteResponse();
//            response.setSuccess(false);
//            response.setError(getErrorApi(responseService));
//        }

        return response;

    }

    public PedidoResponse incluirPedido(PedidoRequest pedido) throws IOException {

        PedidoResponse response = new PedidoResponse();
        Response responseService;

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(pedido);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        call = service.IncluirPedido(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            Gson gson2 = new GsonBuilder().create();
            Reader reader =  ((ResponseBody) responseService.body()).charStream();
            response = gson2.fromJson(reader, PedidoResponse.class);
            response.setSuccess(true);
        }else {
            response = new PedidoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public PedidoResponse alterarPedidoStatus(PedidoRequest pedido) throws IOException {

        PedidoResponse response = new PedidoResponse();
        Response responseService;

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(pedido);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        call = service.AlterarPedidoStatus(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            response.setSuccess(true);
        }else {
            response = new PedidoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public PedidoResponse alterarPedido(PedidoRequest pedido) throws IOException {

        PedidoResponse response = new PedidoResponse();
        Response responseService;

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(pedido);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        call = service.AlterarPedido(body);

        responseService = call.execute();

        if(responseService.isSuccessful()) {
            response.setSuccess(true);
        }else {
            response = new PedidoResponse();
            response.setSuccess(false);
            response.setError(getErrorApi(responseService));
        }

        return response;

    }

    public boolean confirmarPedidosRecebidos(Integer tecnico) throws IOException {
        Response responseService;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tecnico", tecnico);

        ApiPedidosPartsRequestService service = retrofit.create(ApiPedidosPartsRequestService.class);

        Call<ResponseBody> call = null;
        RequestBody body = null;
        String request = "";

        request = jsonObject.toString();
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        call = service.ConfirmarPedidosRecebidos(body);

        responseService = call.execute();

        return responseService.isSuccessful();
    }
}
