package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Student;
import duan.server.entity.Teacher;
import duan.server.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-05
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @PostMapping("/login")
    public Result Login(@RequestBody Teacher teacher){
        System.out.println("正在验证老师登陆 " + teacher);
        Teacher tea = teacherService.findByTno(teacher.getTno());
        System.out.println("老师信息：" + tea );

        if (tea == null || !tea.getPassword().equals(teacher.getPassword())) {
            return Result.fail("操作失败,账号或密码不正确");
        }
        else {
            return Result.succ("登陆成功");
        }
    }

    @GetMapping("/getbytid")
    public Result getByTid(@RequestParam("tid")Integer tid){
        Teacher tea = teacherService.findByTid(tid);
        if (tea == null) {
            return Result.fail("操作失败,账号不存在");
        }
        else {
            return Result.succ("操作成功");
        }
    }
}

