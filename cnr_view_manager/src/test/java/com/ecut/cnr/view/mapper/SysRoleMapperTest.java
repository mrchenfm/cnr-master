package com.ecut.cnr.view.mapper;

import com.ecut.cnr.view.CnrApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

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
    @Test
    public void selectByUserId() throws Exception {
        List<String> list = sysRoleMapper.selectByUserId("11");
        System.out.println(list.get(0));
    }

}