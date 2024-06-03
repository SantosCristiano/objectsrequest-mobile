package br.com.diebold.partsrequest.data.api.listener;

import br.com.diebold.partsrequest.data.api.response.ApiErrorResponse;

public interface ResponseListenerApiError<T> {
    void onSuccess(T o);
    void onFailure(ApiErrorResponse apiErrorResponse);
}
