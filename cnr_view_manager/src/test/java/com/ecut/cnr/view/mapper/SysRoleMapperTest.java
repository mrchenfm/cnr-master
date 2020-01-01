package com.ecut.cnr.view.mapper;

import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.dto.SysUserDto;
import com.ecut.cnr.view.CnrApplication;
import com.ecut.cnr.view.mapper.sys.SysMenuMapper;
import com.ecut.cnr.view.mapper.sys.SysRoleMapper;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import com.ecut.cnr.view.service.sys.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Classname SysRoleMapperTest
 * @Description
 * @Date 2019/12/20 21:17
 * @Create by fangming_chen
 */
@SpringBootTest(classes = {CnrApplication.class})
@RunWith(SpringRunner.class)
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Test
    public void selectByUserId() throws Exception {
       /* List<String> list = sysRoleMapper.selectByUserId("11");
        System.out.println(list.get(0));*/
        /*List<SysUserDto> sysUsers = sysUserService.selectAll();
        System.out.println(Arrays.asList(sysUsers));*/
    }

}