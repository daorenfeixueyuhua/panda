package com.daoren.trans.service.impl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.daoren.trans.exception.CustomTransException;
import com.daoren.trans.model.entity.Person;
import com.daoren.trans.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.junit.Assert.*;
/**
 * 事务测试
 * @author peng_da
 * @date  2022/12/26 16:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TranPersonServiceImplTest {

    @Autowired
    private PersonService personService;
    @Resource
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Test
    public void successfulSave(){
        final Person entity = Person.builder().name("ex").build();
        personService.save(entity);

    }
    @Test
    public void failedSave(){
        final Person entity = Person.builder().name("ex").build();
        personService.save(entity);
        throw new RuntimeException("save failed");
    }
    /**
     * 自动回滚
     * @author peng_da
     * @since 2022/12/26 17:07
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Test
    public void failedSaveAndTran(){
        final Person entity = Person.builder().name("ex").build();
        personService.save(entity);
        throw new RuntimeException("save failed");
    }

    /**
     * 手动回滚
     * @author peng_da
     * @since 2022/12/26 17:06
     */
    @Transactional
    @Test
    public void failedSaveAndRollTran(){
        try{
            final Person entity = Person.builder().name("ex").build();
            personService.save(entity);
            throw new RuntimeException("save failed");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();;
        }
    }


    /**
     * 使用transactionTemplate 进行事务编程
     * @author peng_da
     * @since 2022/12/27 9:59
     */
    @Test
    public void transactionTemplateTest(){
        final Person entity = Person.builder().name("ex").build();
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        final Person person = transactionTemplate.execute(status -> {
            try {
                personService.save(entity);
                if ("ex".equals(entity.getName())){
                    throw new CustomTransException("save fail");
                }
                return entity;
            }catch (CustomTransException e){
                // rollback
                status.setRollbackOnly();
                return null;
            }
        });
        System.out.println("entity's uuid is:" + entity.getId());
    }

    /**
     * 编程式事务
     * todo 编程式事务回滚失效：
     *
     * @author peng_da
     * @since 2022/12/27 9:22
     */
    @Test
    public void transactionManagerTest(){
        final DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        final TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try{
            final Person entity = Person.builder().name("ex").build();
            personService.save(entity);
            if ("ex".equals(entity.getName())){
                throw new RuntimeException("save failed");
            }
            transactionManager.commit(status);
        }catch (RuntimeException e){
            System.out.println("rollback trans");
            transactionManager.rollback(status);
        }
    }
}