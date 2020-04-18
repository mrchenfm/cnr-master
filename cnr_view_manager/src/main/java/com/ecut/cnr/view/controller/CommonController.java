package com.ecut.cnr.view.controller;

import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.enums.ErrorEnum;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommonController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/12 20:02
 */
@Controller
public class CommonController {
    @ResponseBody
    @RequestMapping("/no/permission")
    public Result nonPrivileged(){
        return Result.error(ErrorEnum.NO_AUTH);
    }
}
