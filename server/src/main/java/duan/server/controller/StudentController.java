package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Student;
import duan.server.service.impl.StudentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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


            return Result.fail("操作失败,账号或密码不正确");
        }
        else {
            return Result.succ(stu);
        }
    }

    @GetMapping("/getbysno")
    @ApiOperation(value = "通过sno获取学生信息", notes = "通过sno获取学生信息")
    @ApiParam(name = "sno",value = "学号",required = true)
    public Result getBySno(@RequestParam("sno") String sno){
        Student stu = studentService.findBySno(sno);
        return Result.succ(stu);

    }

    /**
     * 获取学生表的分页信息
     * */
    @GetMapping("/findbypage/{page}/{size}")
    public Result findByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        try {
            System.out.println("查询学生列表分页 " + page + " " + size);
            List<Student> list=studentService.findByPage(page, size);
            return Result.succ(list);
        }
        catch (Exception e) {
            return Result.fail("查询学生列表分页失败");
        }
    }


    /**
     * 查询学生信息
     *
     */
    @GetMapping("/findBySearch")
    public Result findBySearch(@RequestBody Student student) {

        try {
            Integer fuzzy = (Objects.equals(student.getPassword(), "fuzzy")) ? 1 : 0;

            List<Student> list = studentService.findBySearch(student.getSno(), student.getSname(), fuzzy);
            return Result.succ(list);
        }
        catch (Exception e) {
            return Result.fail("查询学生信息失败,请检查参数");
        }
    }



    @GetMapping("/getLength")
    public Result getLength() {
        try {
        return Result.succ(studentService.getLength());
        }
        catch (Exception e) {
            return Result.fail("获取学生表长度失败");
        }
    }

}

