package com.daoren.test.demo;

import com.daoren.test.model.entity.Person;
import com.daoren.test.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class PersonDataCreateTest {
    @Autowired
    private PersonService personService;

    @Test
    public void genData() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 10, 200, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r);
                thread.setName("panda-thread-person-exec-" + count.getAndIncrement());
                return thread;
            }
        });
        List<Future<List<Person>>> result = new ArrayList<>();
        // 生成 (n-1)(n-2)/2 条数据
        for (int i = 0; i < 1000; i++) {
            final Future<List<Person>> listFuture = executor.submit(new PersonDataRun(i));
            result.add(listFuture);
        }
        List<Person> personList = new ArrayList<>();
        for (Future<List<Person>> listFuture : result) {
            List<Person> list = listFuture.get();
            personList.addAll(list);
        }
        personService.saveBatch(personList);

    }

    static class PersonDataRun implements Callable<List<Person>> {
        private final int dataSize;

        PersonDataRun(int dataSize) {
            this.dataSize = dataSize;
        }

        @Override
        public List<Person> call() throws Exception {
            List<Person> list = new ArrayList<>();
            for (int i = 0; i < dataSize; i++) {
                final Person person = Person.builder().name("panda" + i).build();
                list.add(person);
            }
            return list;
        }
    }
}
