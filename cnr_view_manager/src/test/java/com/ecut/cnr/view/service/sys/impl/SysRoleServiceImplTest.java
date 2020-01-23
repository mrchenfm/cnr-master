package com.ecut.cnr.view.service.sys.impl;

import com.ecut.cnr.framework.bo.sys.RoleInfoBO;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.utils.AddressUtils;
import com.ecut.cnr.view.CnrApplication;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Classname SysRoleServiceImplTest
 * @Description
 * @Date 2020/01/01 13:11
 * @Create by fangming_chen
 */
@SpringBootTest(classes = {CnrApplication.class})
@RunWith(SpringRunner.class)
public class SysRoleServiceImplTest {
    @Test
    @Transactional
    public void insertRoleAndPerms() {

        RoleInfoBO roleInfoBO = new RoleInfoBO();
        roleInfoBO.setId("1");
        roleInfoBO.setRoleName("QQQQQQ");
        roleInfoBO.setRemark("ddfd");

        sysRoleService.insertRoleAndPerms(roleInfoBO);
    }

    @Autowired
    private ISysRoleService sysRoleService;
    @Test
    public void findAllRoles() throws Exception {
       /* List<RoleInfoBO> allRoles = sysRoleService.findAllRoles();
        System.out.println(allRoles.toString());*/
        AddressUtils.getCityInfo("183.39.183.23");
    }

}