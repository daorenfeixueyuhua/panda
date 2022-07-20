package com.daoren.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.test.mapper.ResponseCodeMessageMapper;
import com.daoren.test.model.entity.ResponseCodeMessage;
import com.daoren.test.service.ResponseCodeMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author daoren
 * @since 2022-07-20
 */
@Service
public class ResponseCodeMessageServiceImpl extends ServiceImpl<ResponseCodeMessageMapper, ResponseCodeMessage> implements ResponseCodeMessageService {

}
