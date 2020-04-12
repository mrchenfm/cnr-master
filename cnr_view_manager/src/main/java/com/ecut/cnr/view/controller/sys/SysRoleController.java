package com.ecut.cnr.view.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.dto.sys.RoleSearchDto;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.bo.sys.RoleInfoBO;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.entity.sys.SysRole;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.sys.ISysMenuService;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import com.ecut.cnr.view.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private ISysMenuService sysMenuService;

    @Autowired
    private IdUtils idUtils;

    @RequestMapping("/roleList")
    public String userList(){
        return "sys/role/roleList";
    }

    @PostMapping("/roles")
    @ResponseBody
    public Result getRoles(@RequestBody RoleSearchDto roleSearchDto){
        IPage<RoleInfoBO> allRoles = sysRoleService.findAllRoles(roleSearchDto);
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

    @RequestMapping("/updateRole")
    public String  toUpdatePage(Model model,String id){
        try {
            return "sys/role/updateRole";
        } catch (Exception e) {
            log.info("转到修改页面出错：{}",e);
            return "common/500";
        }
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

    @GetMapping("/roleAdd")
    public String toRoleAdd(){
        return "sys/role/roleAdd";
    }

    @GetMapping("/roleUpdate")
    public String toRoleUpdate(Model model,@RequestParam String id){
        RoleInfoBO roleInfoBO = sysRoleService.findAllById(id);
        model.addAttribute("roleInfoBO",roleInfoBO);
        return "sys/role/roleUpdate";
    }

    @RequestMapping("/save/role")
    @ResponseBody
    public Result saveRole(@RequestBody RoleInfoBO roleInfoBO){
        roleInfoBO.setId(String.valueOf(idUtils.nextId()));
        UserInfoBO currentUser = (UserInfoBO) getCurrentUser();
        roleInfoBO.setCreateUserId(currentUser.getId());
        Long count = sysRoleService.insertRoleAndPerms(roleInfoBO);
        if(count<1){
            return new Result().error("保存失败");
        }
        return new Result();
    }

    /**
     * 修改角色信息
     * @param roleInfoBO
     * @return
     */
    @RequestMapping("/update/role")
    @ResponseBody
    public Result updateRole(@RequestBody RoleInfoBO roleInfoBO){
        Long count = sysRoleService.updateRoleById(roleInfoBO);
        if(count<1){
            return new Result().error("修改失败");
        }
        return new Result();
    }



}
