package com.daoren.trans.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.trans.exception.CustomTrans1Exception;
import com.daoren.trans.exception.CustomTransException;
import com.daoren.trans.mapper.PersonMapper;
import com.daoren.trans.model.entity.Person;
import com.daoren.trans.service.PersonService;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author peng_da
 * @date 2022/12/26 14:39
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public Person queryById(String id) {
        return personMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Person saveAndQuery(Person entity) {
        personMapper.insert(entity);
        final Person newPerson = personMapper.selectById(entity.getId());
        int i = 1/0;
        return newPerson;
    }

    @Transactional(rollbackFor = {CustomTrans1Exception.class})
    @Override
    public boolean saveByTran(Person person) {
        personMapper.insert(person);

        if ("ex".equals(person.getName())){
            throw new CustomTransException("save fail, custom exception");
        }
        // 事务传播
        throwEx();

        return true;
    }
    @Transactional(rollbackFor = ArithmeticException.class)
    public void throwEx(){
        int i = 1/0;
    }
    /**
     * 测试 transaction propagation
     * @author peng_da
     * @since 2022/12/27 15:56
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void methodA() {
        System.out.println("PersonServiceImpl.methodA");
        final PersonServiceImpl personService = (PersonServiceImpl) AopContext.currentProxy();
        final Person entity = Person.builder().name("methodA").build();
        personMapper.insert(entity);
        try {
            personService.methodB();
        }catch (Exception e){
            // 将异常进行捕获
            e.printStackTrace();
        }
        int i = 1/0;
    }

    /**
     * 测试 transaction propagation
     * @author peng_da
     * @since 2022/12/27 15:56
     */
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.NESTED)
    @Override
    public void methodB() {
        System.out.println("PersonServiceImpl.methodB");
        final Person entity = Person.builder().name("methodB").build();
        personMapper.insert(entity);
        // 由于 TransactionAspectSupport.completeTransactionAfterThrowing() 之后执行throw ex，如果当前ex符合外层事务的rollback，外层事务也会进行回滚
//        int i = 1/0;
    }
}
