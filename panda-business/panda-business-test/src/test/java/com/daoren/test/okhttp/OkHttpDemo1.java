package com.daoren.test.okhttp;


import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

public class OkHttpDemo1 {
    @SneakyThrows
    @Test
    public void run1() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder()
                .get()
                .url("https://www.baidu.com")
                .build();
        final Call call = okHttpClient.newCall(request);
        final Response response = call.execute();
        System.out.println(response);

    }
}
