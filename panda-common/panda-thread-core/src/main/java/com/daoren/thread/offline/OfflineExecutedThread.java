package com.daoren.thread.offline;

import com.daoren.thread.worker.PandaWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 离线任务执行器
 *
 * @author peng_da
 * @date 2022/8/31 14:24
 */
public class OfflineExecutedThread extends PandaWorker {
    private final static Logger log = LoggerFactory.getLogger(OfflineExecutedThread.class);

    @Override
    public void run() {
        super.run();
    }

    /**
     * 自定义运行
     *
     * @author peng_da
     * @since 2022/8/30 15:43
     */
    @Override
    public void customRun() {

    }
}
