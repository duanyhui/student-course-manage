package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Admin;
import duan.server.service.impl.AdminServiceImpl;
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
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/login")
    public boolean Boolean (@RequestBody Admin admin) {
        System.out.println("正在验证管理员登陆 " + admin);
        Admin ad = adminService.findByUid(admin.getUid());
        System.out.println("管理员信息" + ad);
        /*
        * 有点bug不用Result返回了*/
//        if (ad == null || !ad.getPassword().equals(admin.getPassword())) {
//            Result res=Result.fail("登陆失败");
//            return res;
//        }
        if (ad == null || !ad.getPassword().equals(admin.getPassword())) {

            return false;
        }
        else {
            return true;
        }
    }
}

