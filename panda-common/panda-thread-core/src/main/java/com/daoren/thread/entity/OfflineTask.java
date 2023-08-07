package com.daoren.thread.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 离线任务
 *
 * @author peng_da
 * @date 2022/8/31 10:25
 */
@TableName("sys_offline_task")
public class OfflineTask {
    @TableId
    private String id;
}
