package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Admin;
import duan.server.entity.Student;
import duan.server.service.impl.StudentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @ApiParam(name = "sno",value = "学号",required = true)
    public Result Login(@RequestBody Student student){
        System.out.println("正在验证学生登陆 " + student);
        Student stu = studentService.findBySno(student.getSno());
        System.out.println("学生sno：" + stu);

        if (stu == null || !stu.getPassword().equals(student.getPassword())) {


            Result res=Result.fail("操作失败,账号或密码不正确");
            return res;
        }
        else {
            Result res=Result.succ(stu);
            return res;
        }
    }

    @GetMapping("/getbysno")
    @ApiOperation(value = "通过sno获取学生信息", notes = "通过sno获取学生信息")
    @ApiParam(name = "sno",value = "学号",required = true)
    public Result getBySno(@RequestParam("sno") String sno){
        Student stu = studentService.findBySno(sno);
        return Result.succ(stu);

    }

}

