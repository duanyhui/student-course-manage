package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Student;
import duan.server.entity.Teacher;
import duan.server.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher) {
        try {
            System.out.println("正在添加老师 " + teacher);
            boolean flag = teacherService.insertTeacher(teacher);
            return flag ? Result.succ("操作成功") : Result.fail("操作失败,存在编号相同的老师");
        } catch (DataAccessException e) {
            return Result.fail("操作失败,请检查老师编号是否重复");
        }
    }
    @PostMapping("/deleteByTno/{tno}")
    public Result deleteByTno(@PathVariable("tno") String tno) {
        try {
            System.out.println("正在删除老师 " + tno);
            boolean flag = teacherService.deleteByTno(tno);
            return flag ? Result.succ("操作成功") : Result.fail("操作失败,老师不存在");
        }
        catch (DataAccessException e) {
            return Result.fail("操作失败,存在外键依赖，请检查老师是否被使用");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Teacher teacher) {
        try {
            System.out.println("正在更新老师 " + teacher);
            boolean flag = teacherService.updateByTno(teacher);
            return flag ? Result.succ("操作成功") : Result.fail("操作失败,老师不存在");
        } catch (DataAccessException e) {
            return Result.fail("操作失败,数据库异常");
        }
    }

}

