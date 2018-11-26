package com.salinda.androidboilerplate.model;

import java.net.UnknownHostException;

import retrofit2.Response;

/**
 * Created by Salinda
 */
//TODO Evaluate whether this class need to be kept as Retrofit2 does not
// have RetrofitError class anymore
public class RetrofitErrorWrapper extends BaseModel {
    private Response response;
    private Integer requestCode;
    private Throwable throwable;
    private RETROFIT_ERROR errorType = RETROFIT_ERROR.UNEXPECTED;

    public enum RETROFIT_ERROR {
        NETWORK,
        UNEXPECTED
    }

    public Response getResponse() {
        return response;
    }

    public RETROFIT_ERROR getErrorType() {
        return errorType;
    }

    public void setErrorType(RETROFIT_ERROR errorType) {
        this.errorType = errorType;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public RetrofitErrorWrapper(Throwable throwable) {
        super();
        if (throwable != null) {
            if (throwable instanceof UnknownHostException) {
                this.setErrorType(RETROFIT_ERROR.NETWORK);
            }//TODO add other error types
        }
    }

    public Integer getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(Integer requestCode) {
        this.requestCode = requestCode;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
