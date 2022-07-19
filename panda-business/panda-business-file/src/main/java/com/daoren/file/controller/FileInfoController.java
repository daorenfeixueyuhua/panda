package com.daoren.file.controller;


import com.daoren.file.model.entity.CustomUser;
import com.daoren.file.model.entity.SysFileInfo;
import com.daoren.file.service.FileInfoService;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 文件 前端控制器
 * </p>
 *
 * @author daoren
 * @since 2022-03-08
 */
@RestController
@RequestMapping("/fileInfos")
public class FileInfoController {
    private final FileInfoService baseService;

    public FileInfoController(FileInfoService baseService) {
        this.baseService = baseService;
    }

    @PostMapping
    @ResponseResult
    public List<SysFileInfo> upload(MultipartFile[] files, HttpServletRequest request) {
        return baseService.upload(files, request);
    }

    @ResponseResult
    @GetMapping("/{id}")
    public SysFileInfo one(@PathVariable String id) {
        final SysFileInfo fileInfo = baseService.getById(id);
        final CustomUser customUser = new CustomUser();
        customUser.setName("test");
        fileInfo.setCustomUser(customUser);
        return fileInfo;
    }
}
