package com.daoren.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("发送一个消息");
    }
}
