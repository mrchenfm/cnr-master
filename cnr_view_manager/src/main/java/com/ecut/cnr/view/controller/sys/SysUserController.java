package com.ecut.cnr.view.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.anno.ControllerEndpoint;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.sys.SysRole;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.dto.sys.SysUserDto;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import com.ecut.cnr.view.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Classname SysUserController
 * @Description
 * @Date 2019/12/28 18:43
 * @Create by fangming_chen
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class SysUserController extends BaseController{
    @Autowired
    private ISysUserService sysUserService;


    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private IdUtils idUtils;

    /**
     * 查询管理员列表
     * @return
     */
    @RequestMapping("/userList")
    public String userList(){
        return "sys/user/userList";
    }

    @GetMapping("/users")
    @ResponseBody
    public Result getUsers(QueryRequest queryRequest){
        IPage<SysUserDto> allUsers = sysUserService.selectAllUsers(queryRequest);
        Map<String, Object> dataTable = getDataTable(allUsers);

        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);

        return result;
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delete/user")
    @ResponseBody
    @ControllerEndpoint(operation = "删除管理员用户", exceptionMessage = "管理员用户删除异常")
    public Result deleteById(String id){

        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        if(userInfoBO != null && userInfoBO.getId() != null && userInfoBO.getId().equals(id)){
            return Result.error("不能删除当前登入账号");
        }
        Integer count = sysUserService.deleteById(id);
        if(count == null || count<1){
            return Result.error("删除失败");
        }
        return new Result();
    }

    @RequestMapping("/addUser")
    public String  toAddPage(Model model){
        List<SysRole> allRole = sysRoleService.list(null);
        model.addAttribute("allRole",allRole);
        return "sys/user/operateUser";
    }

    @RequestMapping("/save/user")
    @ResponseBody
    public Result addManager(@RequestBody UserInfoBO userInfoBO){
        SysUser sysUser = new SysUser();
        userInfoBO.setSalt(UUID.randomUUID().toString().replace("_",""));
        userInfoBO.setPassword(new Md5Hash(CnrContants.BASE_PASSWORD,userInfoBO.getSalt(),2).toHex());
        userInfoBO.setNickname(userInfoBO.getUsername());
        userInfoBO.setId(String.valueOf(idUtils.nextId()));
        if(userInfoBO.getUserface() != null){
            userInfoBO.setUserface(CnrContants.BASE_URL_UPLOAD+userInfoBO.getUserface());
        }
        BeanUtils.copyProperties(userInfoBO,sysUser);
        long count = sysUserService.insertUsersAndRole(sysUser,userInfoBO.getRoleIds());
        if(count>0){
            return new Result();
        }
        return Result.error("管理员添加失败");
    }

    @RequestMapping("/updateUser")
    public String  toUpdatePage(Model model,String id){
        try {
            UserInfoBO userInfoBO = sysUserService.findByUserId(id);
            List<SysRole> allRole = sysRoleService.list(null);
            model.addAttribute("allRole",allRole);
            model.addAttribute("userInfoBO",userInfoBO);
            return "sys/user/updateUser";
        } catch (Exception e) {
            log.info("转到修改页面出错：{}",e);
            return "common/500";
        }
    }

    /**
     * 修改管理员信息
     * @param userInfoBO
     * @return
     */
    @RequestMapping("/update/user")
    @ResponseBody
    public Result updateManager(@RequestBody UserInfoBO userInfoBO){
        SysUser sysUser = new SysUser();
        userInfoBO.setNickname(userInfoBO.getUsername());
        BeanUtils.copyProperties(userInfoBO,sysUser);
        Integer count = sysUserService.updateManagerById(sysUser,userInfoBO.getRoleIds());
        //long count = sysUserService.insertUsersAndRole(sysUser,userInfoBO.getRoleIds());
        if(count>0){
            return new Result();
        }
        return Result.error("管理员修改失败");
    }

    @RequestMapping("/update/status")
    @ResponseBody
    public Result updateStatus(@RequestParam("id") String id){
        SysUser sysUser = new SysUser(id,1);
        Integer count = sysUserService.updateStatusBuId(sysUser);
        if(count>0){
            return new Result().put("msg","成功激活");
        }
        return Result.error("激活失败");
    }

    /**
     * 查看用户头像
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/userface")
    public String userFace(Model model , @RequestParam("id") String id){

        UserInfoBO userInfoBO = sysUserService.findByUserId(id);
        model.addAttribute("userface",CnrContants.BASE_URL_UPLOAD+userInfoBO.getUserface());
       return "sys/user/userFace";
    }

    /**
     * 用户信息
     * @return
     */
    @RequestMapping("/personInfo")
    public String userInfo(Model model){

        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        userInfoBO = sysUserService.findByUserId(userInfoBO.getId());
        model.addAttribute("sysUser",userInfoBO);
        return "sys/user/personalInfo";
    }
}
