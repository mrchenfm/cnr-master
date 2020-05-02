package com.ecut.cnr.view.controller.sys;

import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.MenuUtils;
import com.ecut.cnr.framework.common.enums.ErrorEnum;
import com.ecut.cnr.framework.entity.log.LoginLog;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.dto.sys.SysMenuDto;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.request.sys.LoginFormRequest;
import com.ecut.cnr.view.service.log.ILoginLogService;
import com.ecut.cnr.view.service.sys.ISysCaptchaService;
import com.ecut.cnr.view.service.sys.ISysMenuService;
import com.ecut.cnr.view.service.sys.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Classname LoginController
 * @Description
 * @Date 2019/12/15 10:46
 * @Create by fangming_chen
 */
@Controller
@CrossOrigin
public class LoginController extends BaseController {

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysCaptchaService sysCaptchaService;

    @Autowired
    private ILoginLogService loginLogService;

    @RequestMapping(value = {"", "/login"})
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/admin/index")
    public String toIndex(Model model) {
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        SysUser sysUser = sysUserService.getById(userInfoBO.getId());
        sysUser.setLastLoginTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUserService.saveOrUpdate(sysUser);
        List<SysMenu> sysMenus = sysMenuService.findByPersRoleIds(userInfoBO.getRoleIds());
        List<SysMenuDto> menuTree = MenuUtils.getMenuTree(sysMenus);
        model.addAttribute("userInfoBO", userInfoBO);
        model.addAttribute("menuTree", menuTree);
        return "index";
    }

    @RequestMapping("/admin/mainIndex")
    public String forMain() {
        return "main";
    }

    @RequestMapping(value = "captcha.jpg", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }


    @ResponseBody
    @PostMapping(value = "/sys/login")
    public Result login(@RequestBody LoginFormRequest loginRequest) {
        /*boolean captcha=sysCaptchaService.validate(loginRequest.getUuid(),loginRequest.getCaptcha());
        if(!captcha){
            // 验证码不正确
            return Result.error(ErrorEnum.CAPTCHA_WRONG);
        }*/

        // 用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getEmail(), loginRequest.getPassword());
        //request.setAttribute("token",token);
        UserInfoBO userInfoBO = sysUserService.selectUserByUsername(loginRequest.getEmail());
        /*if(userInfoBO == null || !userInfoBO.getPassword().equals(new Sha256Hash(loginRequest.getPassword(),userInfoBO.getSalt()).toHex())){
            // 用户名或密码错误
            return Result.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
        }
        if(userInfoBO.getEnabled() ==0){
            return Result.error("账号已被锁定，请联系管理员");
        }*/
        //添加用户认证信息
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        } catch (AuthenticationException e) {
            return Result.error("验证失败！");
        }

        // 保存登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(userInfoBO.getUsername());
        loginLog.setSystemBrowserInfo();
        this.loginLogService.saveLoginLog(loginLog);

        //生成token，并保存到redis
        return new Result();
    }

    /**
     * 退出
     */

    @ResponseBody
    @PostMapping("/sys/logout")
    public Result logout() {
        return Result.ok();
    }

}