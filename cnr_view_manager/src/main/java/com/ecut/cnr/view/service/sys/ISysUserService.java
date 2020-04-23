package com.ecut.cnr.view.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.dto.sys.UserSearchDto;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.dto.sys.SysUserDto;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import org.csource.common.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Classname ISysUserServcie
 * @Description
 * @Date 2019/12/15 13:23
 * @Create by fangming_chen
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过用户名查询管理员信息
     * @param username
     * @return
     */
    UserInfoBO selectUserByUsername(String username);

    Long insertUsersAndRole(SysUser sysUser, List<String> roleIds);

    /**
     * 根据id删除管理员及他的权限
     * @param id
     * @return
     */
    Integer deleteById(String id);

    /**
     * 根据用户id查询管理员信息
     * @param id
     * @return
     */
    UserInfoBO findByUserId(String id);

    /**
     * 通过id修改管理员信息
     * @param sysUser
     * @return
     */
    Integer updateManagerById(SysUser sysUser,List<String> ids);

    /**
     * 修改账号状态
     * @param sysUser
     * @return
     */
    Integer updateStatusBuId(SysUser sysUser);

    /**
     * 分页查询管理者
     * @param userSearchDto
     * @return
     */
    IPage<SysUserDto> selectAllUsers(UserSearchDto userSearchDto);

    /**
     * 修改密码
     * @param sysUser
     */
    void updatePassword(SysUser sysUser);

    /**
     * 修改用户头像
     * @param file
     * @param userInfoBO
     */
    String updatePic(MultipartFile file, UserInfoBO userInfoBO) throws IOException, MyException;
}

