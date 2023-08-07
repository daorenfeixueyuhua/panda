package com.daoren.http;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author peng_da
 * @date 2022/12/28 16:08
 */
public class Demo1 {

    public static void main(String[] args) throws IOException {
        String url = "http://daoren.top/hello";
        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Demo1.onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Demo1.onResponse");
            }
        });
//        final Response response = httpClient.newCall(request).execute();
//        System.out.println(response.body());
    }

    /**
     * 同步请求
     *
     * @author peng_da
     * @since 2022/12/29 11:10
     */
    @Test
    public void syncRun() throws IOException {
        final String url = "http://daoren.top/hello";
        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody body = response.body();
        assert body != null;
        System.out.println(body.string());
    }

    /**
     * 异步请求
     *
     * @author peng_da
     * @since 2022/12/29 11:09
     */
    @Test
    public void asyncRun() throws InterruptedException {
        String url = "http://daoren.top/hello";
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = httpClient.newCall(request);
        try {
            call.enqueue(
                    new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println("call failed");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println("call successful");
                            assert response.body() != null;
                            System.out.println("result is " + response.body().string());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(20000);
        System.out.println("1111111111111");
    }
}
