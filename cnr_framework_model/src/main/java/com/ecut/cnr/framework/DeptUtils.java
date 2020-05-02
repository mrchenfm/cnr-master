package com.ecut.cnr.framework;

import com.ecut.cnr.framework.dto.sys.DeptDto;
import com.ecut.cnr.framework.entity.sys.Dept;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/28 16:54
 * @Description:
 */
public class DeptUtils {

    public static List<DeptDto> getMenuTree(List<Dept> depts){
        List<DeptDto> deptDtos = new ArrayList<>();
        for(Iterator iterator = depts.iterator(); iterator.hasNext();){
            Dept dept= (Dept) iterator.next();
            if(dept.getParentId().equals("0")){
                DeptDto deptDtoCopy = new DeptDto();
                BeanUtils.copyProperties(dept,deptDtoCopy);
                deptDtoCopy.setName(dept.getDeptName());
                deptDtos.add(deptDtoCopy);
            }
        }
        deptDtos = getMoreChirden(deptDtos,depts);
        return deptDtos;
    }

    private static List<DeptDto> getMoreChirden(List<DeptDto> deptDtos, List<Dept> depts) {
        if (!CollectionUtils.isEmpty(deptDtos)) {
            for(Iterator iterator = deptDtos.iterator();iterator.hasNext();){
                DeptDto deptDto= (DeptDto) iterator.next();
                for(Iterator itr = depts.iterator();itr.hasNext();){
                    Dept dept= (Dept) itr.next();
                    if(dept.getParentId().equals(deptDto.getId())){
                        DeptDto deptDtoCopy = new DeptDto();
                        BeanUtils.copyProperties(dept,deptDtoCopy);
                        deptDtoCopy.setName(dept.getDeptName());
                        deptDto.getChildren().add(deptDtoCopy);
                    }
                }
                List<DeptDto> moreChirden = getMoreChirden(deptDto.getChildren(), depts);
                deptDto.setChildren(moreChirden);
            }
        }
        return deptDtos;
    }
}
