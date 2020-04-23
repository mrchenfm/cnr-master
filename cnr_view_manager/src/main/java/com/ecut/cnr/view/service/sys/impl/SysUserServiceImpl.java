package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.common.utils.DateUtils;
import com.ecut.cnr.framework.common.utils.JsonUtils;
import com.ecut.cnr.framework.dto.sys.UserSearchDto;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.dto.sys.SysUserDto;
import com.ecut.cnr.framework.fastdfs.FileSystem;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.fastdfs.FastDFSService;
import com.ecut.cnr.view.mapper.sys.SysRoleMapper;
import com.ecut.cnr.view.mapper.sys.SysUserMapper;
import com.ecut.cnr.view.service.sys.IFileSystemService;
import com.ecut.cnr.view.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.csource.common.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
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

    @Resource
    FastDFSService fastDFSService;

    @Resource
    IFileSystemService fileSystemService;

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
    public Integer updateManagerById(SysUser sysUser,List<String> ids) {
        SysUser user = sysUserMapper.selectById(sysUser.getId());
        int i = 0;
        if(user !=null){
           i = sysUserMapper.updateById(sysUser);
        }
        sysUserMapper.deleteAllRole(sysUser.getId());
        for (String id: ids) {
            sysUserMapper.saveRole(sysUser.getId(),id);
        }
        return i;
    }

    @Override
    public Integer updateStatusBuId(SysUser sysUser) {
        return sysUserMapper.updateStatusById(sysUser);
    }

    @Override
    public IPage<SysUserDto> selectAllUsers(UserSearchDto userSearchDto) {
        if(!ObjectUtils.isEmpty(userSearchDto.getRegisterTimeRange())){
            userSearchDto.setStart(DateUtils.convert(userSearchDto.getRegisterTimeRange().split(" - ")[0],DateUtils.DATE_FORMAT));
            userSearchDto.setEnd(DateUtils.convert(userSearchDto.getRegisterTimeRange().split(" - ")[1],DateUtils.DATE_FORMAT));
        }
        Page<UserInfoBO> page = new Page<>(userSearchDto.getPage(), userSearchDto.getLimit());
        //this.baseMapper.findAllUsersPage(page);
        IPage<SysUserDto> allUsers = this.baseMapper.findAllUsers(page,userSearchDto);
        List<SysUserDto> records = allUsers.getRecords();
        for (Iterator iterator = records.iterator(); iterator.hasNext(); ) {
            SysUserDto sysUserDto = (SysUserDto) iterator.next();
            StringBuilder sb = getStringBuilder(sysUserDto.getId());
            sysUserDto.setRoleNames(sb.toString());
        }
        allUsers.setRecords(records);
        return allUsers;
    }

    @Override
    public void updatePassword(SysUser sysUser) {
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updatePasswordOrPic(sysUser);
    }

    @Override
    public String updatePic(MultipartFile file, UserInfoBO userInfoBO) throws IOException, MyException {
        String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String path = fastDFSService.uploadFile(file.getBytes(), extName);
        int count = fileSystemService.saveFileInfo(userInfoBO.getId(), path, file.getSize(), file.getContentType());
        if(count>0){
            SysUser sysUser = new SysUser();
            sysUser.setId(userInfoBO.getId());
            sysUser.setUserface(CnrContants.BASE_URL_UPLOAD+path);
            SysUser sysUser1 = sysUserMapper.selectByUsername(userInfoBO.getUsername());
            FileSystem fileSystem = fileSystemService.findByUrl(sysUser1.getUserface());
            int i = sysUserMapper.updatePasswordOrPic(sysUser);
            if(i>0){
                if(fileSystem != null && fileSystem.getSrc() != null){
                    fastDFSService.deleteFile(fileSystem.getSrc());
                    fileSystemService.removeById(fileSystem.getId());
                }
            }
        }
        return path;
    }

}
