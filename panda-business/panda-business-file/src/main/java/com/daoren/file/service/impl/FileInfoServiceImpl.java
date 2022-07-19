package com.daoren.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.file.mapper.FileInfoMapper;
import com.daoren.file.model.entity.SysFileInfo;
import com.daoren.file.service.FileInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 文件 服务实现类
 * </p>
 *
 * @author daoren
 * @since 2022-03-08
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, SysFileInfo> implements FileInfoService {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("/yyyy/MM/dd/");
    @Value("${spring.servlet.multipart.location}")
    private String realPath;

    /**
     * 上传文件
     *
     * @param files   : 文件
     * @param request : request
     * @return java.util.List<com.daoren.file.model.entity.File>
     * @author peng_da
     * @since 2022/3/8 12:07
     */
    @Override
    public List<SysFileInfo> upload(MultipartFile[] files, HttpServletRequest request) {
        List<SysFileInfo> paths = new ArrayList<>();
        for (MultipartFile file : files) {
            String format = dateTimeFormatter.format(LocalDateTime.now());
            java.io.File folder = new java.io.File(realPath + format);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }

            String oldName = file.getOriginalFilename();
            assert oldName != null;
            String newName =
                    UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
            try {
                file.transferTo(new java.io.File(folder, newName));
                String filePath = format + newName;
                SysFileInfo fileObject = SysFileInfo
                        .builder()
                        .name(oldName.substring(0, oldName.lastIndexOf(".")))
                        .size((int) file.getSize())
                        .format(oldName.substring(oldName.lastIndexOf(".")))
                        .path(filePath)
                        .storageType(1)
                        .build();
                baseMapper.insert(fileObject);
                paths.add(fileObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paths;
    }
}
