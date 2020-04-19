package com.ecut.cnr.view.controller.fastdfs;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.bo.sys.FileSystemBO;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.common.enums.ErrorEnum;
import com.ecut.cnr.framework.dto.sys.FileSearchDto;
import com.ecut.cnr.view.service.sys.IFileSystemService;
import com.ecut.cnr.view.utils.CommonFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
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
public class FileServerController extends BaseController {

    @Autowired
    private CommonFileUtil fileUtil;

    @Autowired
    private IFileSystemService fileSystemService;

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

    //使用fastdfs，富文本编辑进行文件上传
    @PostMapping("/uploadFileToFast")
    @ResponseBody
    public String uoloadFileToFast(@RequestParam("file")MultipartFile file) throws IOException{

        if(file.isEmpty()){
            log.info("文件不存在");
        }
        //构造驳回参数
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        String path = fileUtil.uploadFile(file,userInfoBO.getId());
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

    /**
     * 普通图片上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file")MultipartFile file) throws IOException{

        if(file.isEmpty()){
            log.info("文件不存在");
        }
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        String path = fileUtil.uploadFile(file,userInfoBO.getId());
        log.info("文件路径：{}",path);
        if(ObjectUtils.isEmpty(path)){
            return Result.error("上传失败！");
        }
        Result res = new Result().put("msg", "上传成功");
        res.put("path",path);
        res.put("basicPath",CnrContants.BASE_URL_UPLOAD);
        return res;
    }

    /**
     * 跳转页面
     * @return
     */
    @RequestMapping("/imgs/list")
    public String toFileListPage(){
        return "/sys/file/fileInfoList";
    }

    /**
     * file列表
     * @return
     */
    @RequestMapping("/files/list")
    @ResponseBody
    public Result fileInfoList(@RequestBody FileSearchDto fileSearchDto){
        Map<String, Object> dataTable = null;
        try {
            IPage<FileSystemBO> allSystemLogs = fileSystemService.listAllFilePage(fileSearchDto);
            dataTable = getDataTable(allSystemLogs);
        } catch (Exception e) {
            log.error("查询文件列表报错：{}",e);
            return Result.error(ErrorEnum.SQL_ILLEGAL);
        }
        Result result = Result.addMap(dataTable);
        return result;
    }

}
