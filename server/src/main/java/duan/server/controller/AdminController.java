package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Admin;
import duan.server.service.impl.AdminServiceImpl;
import duan.server.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-04
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public Result Login (@RequestBody Admin admin) throws Exception {
        log.info("正在验证管理员登陆 " + admin);
        Admin ad = adminService.findByUid(admin.getUid());
        System.out.println("管理员信息" + ad);
        if (ad == null) {
            return Result.fail("操作失败,账号或密码不正确");
        }
        else {
            return loginService.login(admin);
        }
    //    return loginService.login(admin);
        //TODO 这里改了返回结果前端记得改
    }
    @GetMapping("/getbyuid")
    public Result getByUid (@RequestParam("uid") Integer uid) {
        System.out.println("正在查询管理员信息");
        Admin admin = adminService.findByUid(uid);
        System.out.println("管理员信息" + admin);
        Result res=Result.succ(admin);
        return res;
    }
}

