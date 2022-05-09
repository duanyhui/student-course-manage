package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Student;
import duan.server.service.impl.StudentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @PostMapping("/deleteBySno/{sno}")
    public Result deleteById(@PathVariable("sno") String sno) {
        System.out.println("正在删除学生 sno为：" + sno);
        try {
            if (studentService.deleteBySno(sno)) {
                return Result.succ("删除成功");
            }
            else {
                return Result.fail("删除失败");
            }

        }
        catch (DataAccessException e) {
            return Result.fail("删除学生失败,存在外键依赖");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        System.out.println("正在添加学生 " + student);
        try {
            if (studentService.haveSno(student.getSno())) {
                return Result.fail("添加失败,学号已存在");
            }
            if(student.getPassword()==null){
                student.setPassword("123456");
            }
            if (studentService.add(student)) {
                return Result.succ("添加成功,默认密码为123456");
            }
            else {
                return Result.fail("添加失败");
            }
        }
        catch (DataAccessException e) {
            return Result.fail("添加学生失败,缺少必要参数");
        }
    }

    @PostMapping("/update")
    public Result updateStudent(@RequestBody Student student) {
        try {
            System.out.println("更新 " + student);
            if(studentService.updateByCno(student)) {
                return Result.succ("更新成功");
            }
            else {
                return Result.fail("更新失败,没有这个学生");
            }
        }
        catch (Exception e) {
            return Result.fail("更新学生信息失败，存在外键依赖");
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

    @GetMapping("/getTerm/{sno}")
    public Result getTerm(@PathVariable("sno") String sno) {
        try {
            if(studentService.getTerm(sno) == null) {
                return Result.fail("没有这个学生","大一上");
            }
            else {
                return Result.succ(studentService.getTerm(sno));
            }
        }
        catch (Exception e) {
            return Result.fail("获取学期失败","大一上");
        }
    }


}

