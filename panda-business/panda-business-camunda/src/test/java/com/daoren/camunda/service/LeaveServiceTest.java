package com.daoren.camunda.service;

import com.daoren.camunda.model.dto.Leave;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LeaveServiceTest {
    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RepositoryService repositoryService;

    /**
     * 流程定义部署
     */
    @SneakyThrows
    @Test
    public void deploy() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("leave.bpmn")
                .deploy();
        System.out.println(deploy.getId());
    }

    /**
     * 开启一个流程实例
     */
    @Test
    public void runProcinst() {
        Map<String, Object> params = new HashMap<>();
        params.put("employee", "zhangsan");
        params.put("leave", new Leave("NO00001", "休假", new Date()));
        params.put("days", 2);
        ProcessInstance holiday = runtimeService.startProcessInstanceByKey("holiday", params);
        System.out.println(holiday.getProcessDefinitionId());
        System.out.println(holiday.getId());
        System.out.println(holiday.getProcessInstanceId());
    }

    /**
     * 流程任务查询
     */
    @Test
    public void taskQuery() {
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("Process_07i921h")
                .list();
        for (Task task : tasks) {
            System.out.println(task.getAssignee());
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getTenantId());
        }
    }

    /**
     * 流程任务执行
     */
    @Test
    public void taskComplete() {
        //目前lisi只有一个任务，业务中根据场景选择其他合适的方式
        Task task = taskService.createTaskQuery()
                .taskAssignee("lisi")
                .singleResult();
        Map<String, Object> params = new HashMap<>();
        params.put("personal", "wangwu");
        taskService.complete(task.getId(), params);
    }

    /**
     * 流程定义查询
     */
    @Test
    public void queryDefine() {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> definitions = query.processDefinitionKey("holiday")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition definition : definitions) {
            System.out.println(definition.getDeploymentId());
            System.out.println(definition.getName());
            System.out.println(definition.getVersion());
            System.out.println(definition.getId());
            System.out.println(definition.getKey());
        }
    }

    /**
     * 删除流程定义
     */
    @Test
    public void deleteDefine() {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> definitions = processDefinitionQuery.processDefinitionKey("holiday")
                .orderByProcessDefinitionVersion()
                .asc()
                .list();
        ProcessDefinition processDefinition = definitions.get(0);
        if (processDefinition != null) {
            //删除流程定义，如果存在该定义的流程实例在运行中则删除报错
            //repositoryService.deleteDeployment(processDefinition.getDeploymentId());
            //true 级联删除流程定义，即使该流程有流程实例启动也可以删除(没有审批完的流程也会被先删除)
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        }
    }

    /**
     * 获取流程定义的资源文件
     */
    @SneakyThrows
    @Test
    public void outputBPMNFile() {
        List<ProcessDefinition> res = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("holiday")
                .list();
        ProcessDefinition holiday = res.get(0);
        //获取BPMN文件的输入流                                                                   获取BPMN文件名
        InputStream BPMNIs = repositoryService.getResourceAsStream(holiday.getDeploymentId(), holiday.getResourceName());
        //如果部署了对应的图片，也可以获取到该图片的输入流                                             获取对应图片的资源名
        //InputStream PNGIs = repositoryService.getResourceAsStream(holiday.getDeploymentId(), holiday.getDiagramResourceName());
        //输入流转换为输出流
        FileOutputStream outputStream = new FileOutputStream("E:\\流程\\" + holiday.getResourceName(), true);
        IOUtils.copy(BPMNIs, outputStream);
    }

    /**
     * 查询历史信息
     */
    @Test
    public void queryHistory() {
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .finished()
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .list();
        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getProcessDefinitionKey());
            System.out.println(instance.getAssignee());
            System.out.println(instance.getStartTime());
            System.out.println(instance.getEndTime());
            System.out.println("=============================");
        }
    }

    /**
     * 启动一个流程实例，并且添加一个业务key
     * 业务key 可以在 act_ru_execution 中看到
     */
    @Test
    public void startProcInstAddBusinessKey() {
        ProcessInstance holiday = runtimeService.startProcessInstanceByKey("holiday", "0001");
        System.out.println(holiday.getBusinessKey());
    }

    /**
     * 挂起、激活流程定义的所有流程实例
     */
    @Test
    public void activateOrSuspendProcInsts() {
        //获取流程定义
        List<ProcessDefinition> holidays = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("holiday")
                .list();
        ProcessDefinition holiday = holidays.get(0);
        //得到流程定义的所有流程实例会否都是暂停状态
        boolean suspended = holiday.isSuspended();
        if (suspended) {
            //激活
            repositoryService.activateProcessDefinitionById(holiday.getId(), true, null);
            System.out.println("定义" + holiday.getId() + "激活");
        } else {
            //挂起
            repositoryService.suspendProcessDefinitionById(holiday.getId(), true, null);
            System.out.println("定义" + holiday.getId() + "挂起");
        }
    }

    /**
     * 挂起、激活单个流程实例
     * 执行挂起的流程会抛出异常
     */
    @Test
    public void activateOrSuspendProcInst() {
        ProcessInstance holiday = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey("0001")
                .singleResult();
        boolean suspended = holiday.isSuspended();
        if (suspended) {
            runtimeService.activateProcessInstanceById(holiday.getId());
            System.out.println("实例" + holiday.getId() + "激活");
        } else {
            runtimeService.suspendProcessInstanceById(holiday.getId());
            System.out.println("实例" + holiday.getId() + "挂起");
        }
    }

    /**
     * 开启一条流程，并给用户任务的 assignee 赋值
     */
    @Test
    public void startProcessInstanceWithAssignee() {
        Map<String, Object> map = new HashMap<>();
        map.put("employee", "zhangsan");
        map.put("deptment", "lisi");
        map.put("personal", "wangwu");
        ProcessInstance holiday = runtimeService.startProcessInstanceByKey("holiday", map);
        System.out.println(holiday.getProcessInstanceId());
    }

    /**
     * 设置global/loacal变量
     */
    @Test
    public void setVariables() {
        Map params = new HashMap();
        params.put("days", 3);
        params.put("type", "休假");
        //设置global变量
        runtimeService.setVariable("excutionId", "key", "value");
        runtimeService.setVariables("excutionId", params);
        //设置local变量
        runtimeService.setVariableLocal("excutionId", "key", "value");
        runtimeService.setVariablesLocal("excutionId", params);
    }
}