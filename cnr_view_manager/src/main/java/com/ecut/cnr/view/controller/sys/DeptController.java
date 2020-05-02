package com.ecut.cnr.view.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.sys.Dept;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.view.service.sys.IDeptService;
import com.ecut.cnr.view.service.sys.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/28 16:34
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IdUtils idUtils;

    @RequestMapping("/dept/manager")
    public String deptList(){

        return "sys/dept/deptList";
    }

    @RequestMapping("/dept/deptTree")
    @ResponseBody
    public Result menuTree(){
        List<Dept> list = deptService.list(null);
        //List<DeptDto> deptDtos = DeptUtils.getMenuTree(list);
        Object toJSON = JSONArray.toJSON(list);
        return new Result().put("data",toJSON);
    }

    @GetMapping("/deptAdd")
    public String toDeptAdd(Model model){
        List<SysUser> list = sysUserService.list(null);
        model.addAttribute("users",list);
        return "sys/dept/addDept";
    }

    @GetMapping("/deptAddChildren")
    public String toDeptAddChildren(Model model, String id){
        List<SysUser> list = sysUserService.list(null);
        model.addAttribute("users",list);
        Dept dept = this.deptService.getById(id);
        model.addAttribute("dept",dept);
        return "sys/dept/addChildrenDept";
    }

    @GetMapping("/deptUpdate")
    public String toDeptUpdate(Model model,String id){
        List<SysUser> list = sysUserService.list(null);
        model.addAttribute("users",list);
        Dept dept = this.deptService.getById(id);
        model.addAttribute("dept", dept);
        return "sys/dept/addDeptMaster";
    }

    @PostMapping("/save/dept")
    @ResponseBody
    public Result saveDept(@RequestBody Dept dept){
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        SysUser sysUser = sysUserService.getById(dept.getMaster());
        generateDept(dept, userInfoBO,sysUser);
        boolean b = this.deptService.saveOrUpdate(dept);
        if(!b){
            return Result.error("添加失败");
        }
        sysUserService.updateById(sysUser);
        return new Result();
    }

    private void generateDept(@RequestBody Dept dept, UserInfoBO userInfoBO,SysUser sysUser) {
        dept.setId(String.valueOf(idUtils.nextId()));
        dept.setCreateBy(userInfoBO.getId());
        dept.setCreateDate(new Date());
        dept.setDelFlag(0);
        dept.setMasterName(sysUser.getUsername());
        dept.setPhone(sysUser.getPhone());
        dept.setEmail(sysUser.getEmail());
        dept.setUpdateBy(userInfoBO.getId());
        dept.setUpdateDate(new Date());
        sysUser.setDeptId(dept.getId());
    }

    @PostMapping("/update/dept/master")
    @ResponseBody
    public Result saveDeptMaster(@RequestBody Dept dept){
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        SysUser sysUser = sysUserService.getById(dept.getMaster());
        generateDeptNotId(dept, userInfoBO,sysUser);

        Result result = deptService.saveDeptMaster(dept,sysUser);
        return result;
    }

    private void generateDeptNotId(Dept dept, UserInfoBO userInfoBO,SysUser sysUser) {

        dept.setMasterName(sysUser.getUsername());
        dept.setPhone(sysUser.getPhone());
        dept.setEmail(sysUser.getEmail());
        dept.setUpdateBy(userInfoBO.getId());
        dept.setUpdateDate(new Date());
        sysUser.setDeptId(dept.getId());
    }


    @PostMapping("/delete/dept")
    @ResponseBody
    public Result deleteDept(String id){
        Result result = deptService.deleteById(id);
        return result;
    }
}
