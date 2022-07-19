package com.daoren.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.file.model.entity.SysFileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文件 Mapper 接口
 * </p>
 *
 * @author daoren
 * @since 2022-03-08
 */
@Mapper
public interface FileInfoMapper extends BaseMapper<SysFileInfo> {

}
