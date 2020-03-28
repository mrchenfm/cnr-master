package com.ecut.cnr.view.controller.fastdfs;

import com.alibaba.druid.support.json.JSONUtils;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.view.utils.CommonFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: FileServerController
 * @Description: TODO(文件上传服务器)
 * @Author: fangming_chen
 * @Date: 2020/03/01 19:29
 */
@Controller
@Slf4j
@RequestMapping("/fastdfs")
public class FileServerController {

    @Autowired
    private CommonFileUtil fileUtil;

    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file){

        String targetFilePath = "E:/opt/uploads/";

        if(file.isEmpty()){
            log.info("this file is empty");
        }

        String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        //获取原来文件名称
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        if(!fileSuffix.equals(".jpg") || !fileSuffix.equals(".png")){
            log.info("文件格式不正确");
        }
        //拼装新的文件名
        String targetFileName = targetFilePath + newFileName + fileSuffix;
        //上传文件
        try {
            FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(targetFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/success";
    }

    //使用fastdfs进行文件上传
    @PostMapping("/uploadFileToFast")
    @ResponseBody
    public String uoloadFileToFast(@RequestParam("file")MultipartFile file) throws IOException{

        if(file.isEmpty()){
            log.info("文件不存在");
        }
        String path = fileUtil.uploadFile(file);
        log.info("文件路径：{}",path);
        Result result = new Result();
        Map<String,Object> map = new HashMap<String,Object>();
        result.put("code",0);
        map.put("src", CnrContants.BASE_URL_UPLOAD +path);
        map.put("title",path.split("/")[path.split("/").length-1]);
        JSONObject data = new JSONObject(result.put("data", map));
        String s = JSONUtils.toJSONString(result);
        String replace = data.toString().replace("//", "/");
        return s;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file")MultipartFile file) throws IOException{

        if(file.isEmpty()){
            log.info("文件不存在");
        }
        String path = fileUtil.uploadFile(file);
        log.info("文件路径：{}",path);
        if(ObjectUtils.isEmpty(path)){
            return Result.error("上传失败！");
        }
        Result res = new Result().put("msg", "上传成功");
        res.put("path",path);
        res.put("basicPath",CnrContants.BASE_URL_UPLOAD);
        return res;
    }

}
