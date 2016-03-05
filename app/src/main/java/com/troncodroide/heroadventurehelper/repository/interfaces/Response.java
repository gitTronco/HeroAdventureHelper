package com.troncodroide.heroadventurehelper.repository.interfaces;

import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName("status")
    int status;

    @SerializedName("error")
    String error;

    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public Response(T data) {
        this.data = data;
    }

    public static class Error<T> {
        int errorCode;
        T data;

        public Error(int errorCode, T data) {
            this.errorCode = errorCode;
            this.data = data;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            if (data != null) {
                return data.toString();
            }
            return super.toString();
        }
    }

    public interface Listener<T> {

        void onSuccess(T data, boolean hideLoading);

        void onError(Error error);
    }

}
