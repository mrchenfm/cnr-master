package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.common.utils.DateUtils;
import com.ecut.cnr.framework.common.utils.JsonUtils;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.bo.UserInfoBO;
import com.ecut.cnr.framework.entity.sys.dto.SysUserDto;
import com.ecut.cnr.view.mapper.sys.SysRoleMapper;
import com.ecut.cnr.view.mapper.sys.SysUserMapper;
import com.ecut.cnr.view.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Classname SysUserServicempl
 * @Description
 * @Date 2019/12/15 13:23
 * @Create by fangming_chen
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserInfoBO selectUserByUsername(String username){
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        List<String> roleIds = sysRoleMapper.selectByUserId(sysUser.getId());
        UserInfoBO userInfoBO = new UserInfoBO();
        BeanUtils.copyProperties(sysUser,userInfoBO);
        userInfoBO.setRoleIds(roleIds);
        log.info("userInfoBO={}", JsonUtils.toJson(userInfoBO));
        return userInfoBO;
    }

    /**
     * 查询所用管理员
     * @return
     */
    @Override
    public List<SysUserDto> selectAll(){
        List<SysUser> sysUsers = this.baseMapper.selectList(null);
        log.info("sysUsers={}",JsonUtils.toJson(sysUsers));
        List<SysUserDto> sysUserDtos = new ArrayList<>(sysUsers.size());
        for(Iterator iterator = sysUsers.iterator();iterator.hasNext();){
            SysUserDto sysUserDto = new SysUserDto();
            SysUser sysUser = (SysUser) iterator.next();
            BeanUtils.copyProperties(sysUser,sysUserDto);
            StringBuilder sb = getStringBuilder(sysUserDto.getId());
            sysUserDto.setRoleNames(sb.toString());
            sysUserDto.setCreateTime(DateUtils.convert(sysUser.getCreateTime()));
            sysUserDtos.add(sysUserDto);
        }
        return sysUserDtos;
    }

    private StringBuilder getStringBuilder(String id) {
        List<String> roleNames = sysRoleMapper.findBuUserId(id);
        StringBuilder sb = new StringBuilder();
        if(!CollectionUtils.isEmpty(roleNames)){
            for(Iterator itr = roleNames.iterator(); itr.hasNext();){
                sb.append(itr.next());
                if(itr.hasNext()){
                    sb.append(",");
                }
            }
        }
        return sb;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertUsersAndRole(SysUser sysUser, List<String> roleIds) {

        long insert = sysUserMapper.insert(sysUser);
        if(insert>0 && !CollectionUtils.isEmpty(roleIds)){
            for(String id : roleIds){
                sysRoleMapper.insertUserRole(sysUser.getId(),id);
            }
        }
        return insert;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteById(String id) {
        Integer count = sysUserMapper.deleteById(id);
        if (count>0){
            sysRoleMapper.deleteUserRole(id);
        }
        return count;
    }

    @Override
    public UserInfoBO findByUserId(String id) {
        UserInfoBO userInfoBO = new UserInfoBO();
        SysUser sysUser = sysUserMapper.selectById(id);
        BeanUtils.copyProperties(sysUser,userInfoBO);
        List<String> buUserId = sysRoleMapper.selectByUserId(id);
        userInfoBO.setRoleIds(buUserId);
        return userInfoBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateManagerById(SysUser sysUser) {
        SysUser user = sysUserMapper.selectById(sysUser.getId());
        int i = 0;
        if(user !=null){
           i = sysUserMapper.updateById(sysUser);
        }
        return i;
    }

    @Override
    public Integer updateStatusBuId(SysUser sysUser) {
        return sysUserMapper.updateStatusById(sysUser);
    }

}
