package com.daoren.test.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * @author peng_da
 * @date 2023/1/5 17:17
 */
public class CanalTest {

    @Test
    public void printTest() {
        System.out.println("mmmmmmmmm");

    }

    @Test
    public void listener() {
        final CanalConnector canal = CanalConnectors
                .newSingleConnector(new InetSocketAddress("127.0.0.1", 11111),
                        "test", "admin", "admin");
        try {
            canal.connect();
            canal.subscribe(".*\\..*");
            canal.rollback();
            while (true) {
                final Message message = canal.getWithoutAck(10);
                final long id = message.getId();
                final int batchSize = message.getEntries().size();
                if (id == -1 || batchSize == 0) {
                    Thread.sleep(2000);
                } else {
                    System.out.println(message.getEntries());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            canal.disconnect();
        }
    }
}
