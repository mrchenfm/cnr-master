package com.ecut.cnr.view.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.entity.sys.SysRole;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.bo.RoleInfoBO;
import com.ecut.cnr.framework.entity.sys.bo.UserInfoBO;
import com.ecut.cnr.framework.entity.sys.request.QueryRequest;
import com.ecut.cnr.view.service.sys.ISysMenuService;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import com.ecut.cnr.view.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Classname SysRoleController
 * @Description
 * @Date 2019/12/28 18:49
 * @Create by fangming_chen
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("/roleList")
    public String userList(){
        return "sys/role/roleList";
    }

    @GetMapping("/roles")
    @ResponseBody
    public Result getRoles(QueryRequest queryRequest){
        IPage<RoleInfoBO> allRoles = sysRoleService.findAllRoles(queryRequest);
        Map<String, Object> dataTable = getDataTable(allRoles);

        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);

        return result;
    }

    @RequestMapping("/addRole")
    public String  toAddPage(Model model){
        List<SysMenu> list = sysMenuService.list(null);
        model.addAttribute("allRole",list);
        return "sys/role/addRole";
    }

    @RequestMapping("/save/role")
    @ResponseBody
    public Result addManager(@RequestBody UserInfoBO userInfoBO){
       if(true){
            return new Result();
        }
        return Result.error("管理员添加失败");
    }

    @RequestMapping("/updateRole")
    public String  toUpdatePage(Model model,String id){
        try {
            return "sys/role/updateRole";
        } catch (Exception e) {
            log.info("转到修改页面出错：{}",e);
            return "common/500";
        }
    }

    /**
     * 修改角色信息
     * @param userInfoBO
     * @return
     */
    @RequestMapping("/update/role")
    @ResponseBody
    public Result updateManager(@RequestBody UserInfoBO userInfoBO){

        return Result.error("管理员修改失败");
    }

    @RequestMapping("/delete/role")
    @ResponseBody
    public Result deleteById(String id){

        Integer count = sysRoleService.deleteById(id);
        if(count == null || count<1){
            return Result.error("删除失败");
        }
        return new Result();
    }



}
