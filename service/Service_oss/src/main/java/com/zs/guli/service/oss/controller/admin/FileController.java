package com.zs.guli.service.oss.controller.admin;

import com.zs.guli.common.base.result.R;
import com.zs.guli.common.base.result.ResultCodeEnum;
import com.zs.guli.common.base.util.ExceptionUtils;
import com.zs.guli.service.base.exception.GuliException;
import com.zs.guli.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;


/**
 * @author optimist
 * @date 2020/9/12 17:51
 */
@Slf4j
@Api(value = "阿里云文件管理")
//  @CrossOrigin  统一在网关中配置跨域
@RestController
@RequestMapping("/admin/oss/file")
@RefreshScope
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public R fileUpload(
           @ApiParam(value = "文件",required = true) MultipartFile file,
           @ApiParam(value = "模块名", required = true)  @RequestParam("module")String module ) throws IOException {
        log.info("准备上传文件");
        InputStream inputStream = null;
        String fileOssUrl = null;
        try {
            inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            fileOssUrl = fileService.uploadFileToOss(inputStream, module, originalFilename);

        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }

        return R.ok().message("文件上传成功！").data("url",fileOssUrl);
    }

    @ApiOperation(value = "阿里云文件删除")
    @DeleteMapping("remove")
    public R removeFile(
            @ApiParam(value = "要删除的文件url路径", required = true)
            @RequestBody  String url) {
        fileService.removeFile(url);
        return R.ok().message("文件删除成功！");
    }



    @ApiOperation(value = "测试")
    @GetMapping("test")
    public R test() {
        log.info("oss test被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
}
