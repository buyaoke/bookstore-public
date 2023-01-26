package com.lz.bookstore.controller;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.common.Result;
import com.lz.bookstore.entity.Files;
import com.lz.bookstore.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.lz.bookstore.service.IFileService;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-24
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private IFileService fileService;


    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        File uploadFileParent = new File(fileUploadPath);
        if (!uploadFileParent.exists()){
            uploadFileParent.mkdirs();
        }
        //唯一文件标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT+ type;
        File uploadFile = new File(fileUploadPath + fileUuid);
        //存到磁盘
        file.transferTo(uploadFile);
        Files files = new Files();
        files.setName(originalFilename);
        files.setType(type);
        double sizeM = size/1024;
        files.setSize(sizeM);
        String url = "http://localhost:9090/file/"+fileUuid;
        files.setUrl(url);
        fileService.save(files);

        return url;

    }


    @GetMapping("/{fileUuid}")
    public void  download(@PathVariable String fileUuid, HttpServletResponse response) throws IOException {
        //根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUuid);
        //设置输出流格式
        ServletOutputStream os  = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileUuid,"utf-8"));
        response.setContentType("application/octet-stream");
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();


    }

}

