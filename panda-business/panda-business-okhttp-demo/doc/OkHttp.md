## OkHttp使用教程

### 快速上手

#### 同步请求

```java
public class Demo1 {
    public static void main(String[] args) throws IOException {
        String url = "http://daoren.top/hello";
        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();
        final Response response = httpClient.newCall(request).execute();
        System.out.println(response.body());
    }
}

```

#### 异步请求

```java
public class Demo1 {

    public static void main(String[] args) throws IOException {
        String url = "http://daoren.top/hello";
        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();
        // 主线程结束之后，等待一分钟才会停止程序
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
    }
}
```

#### Q&A

##### 线程名称的来源

```text
okhttp3.Dispatcher#executorService
```

##### 为什么等待一分钟

OkHttp Dispatcher设置的非守护线程，清理频率为60S，并且corePoolSize为0；

```java
public final class Dispatcher {
  public synchronized ExecutorService executorService() {
    if (executorService == null) {
      executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
          new SynchronousQueue<>(), Util.threadFactory("OkHttp Dispatcher", false));
    }
    return executorService;
  }
}
```

##### 回调机制

okhttp3.RealCall.AsyncCall#execute