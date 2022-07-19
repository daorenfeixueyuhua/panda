package com.daoren.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daoren.file.model.entity.SysFileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 文件 服务类
 * </p>
 *
 * @author daoren
 * @since 2022-03-08
 */
public interface FileInfoService extends IService<SysFileInfo> {
    /**
     * 上传文件
     *
     * @param files   : 文件
     * @param request : request
     * @return java.util.List<com.daoren.file.model.entity.File>
     * @author peng_da
     * @since 2022/3/8 12:07
     */
    List<SysFileInfo> upload(MultipartFile[] files, HttpServletRequest request);
}
