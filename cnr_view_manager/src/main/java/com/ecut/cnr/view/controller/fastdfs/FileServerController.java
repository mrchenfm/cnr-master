package com.ecut.cnr.view.controller.fastdfs;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.bo.sys.FileSystemBO;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.common.enums.ErrorEnum;
import com.ecut.cnr.framework.dto.sys.FileSearchDto;
import com.ecut.cnr.view.fastdfs.FastDFSService;
import com.ecut.cnr.view.service.sys.IFileSystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.csource.common.MyException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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
    private IFileSystemService fileSystemService;

    @Resource
    private FastDFSService fastDFSService;


    @PostMapping("/uploadFileToFast")
    @ResponseBody
    public String uoloadFileToFast1(@RequestParam("file")MultipartFile file) throws IOException, MyException {

        if(file.isEmpty()){
            log.info("文件不存在");
        }
        String name = file.getOriginalFilename();
        String path = fastDFSService.uploadFile(file.getBytes(), name.substring(name.lastIndexOf(".")+1));
        //构造驳回参数
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        fileSystemService.saveFileInfo(userInfoBO.getId(),path,file.getSize(),file.getContentType());
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
    public Result upload1(@RequestParam("file")MultipartFile file) {

        if(file.isEmpty()){
            log.info("文件不存在");
            new Result().put("msg", "上传失败");
        }
        String path = null;
        Result res = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
            String name = file.getOriginalFilename();
            path = fastDFSService.uploadFile(file.getBytes(), name.substring(name.lastIndexOf(".")+1));
            fileSystemService.saveFileInfo(userInfoBO.getId(),path,file.getSize(),file.getContentType());
            res = new Result().put("msg", "上传成功");
            res.put("path",path);
            res.put("basicPath",CnrContants.BASE_URL_UPLOAD);
        } catch (Exception e) {
            res = new Result().put("msg", "上传失败");
        }

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
