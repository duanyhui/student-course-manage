package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Ct_vo;
import duan.server.entity.Student;
import duan.server.entity.Student_vo;
import duan.server.service.impl.LoginServiceImpl;
import duan.server.service.impl.StudentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @ApiParam(name = "sno",value = "学号",required = true)
    public Result Login(@RequestBody Student student) throws Exception {
        log.info("正在验证学号为" + student.getSno() + "的学生登陆");
        Student stu = studentService.findBySno(student.getSno());
        System.out.println("学生sno：" + stu);

        if(stu == null){
            return Result.fail("操作失败,账号或密码不正确");
        }
        else{
            return loginService.login(student);
        }
    }


    @PostMapping("/add")
    public Result add(@RequestBody Student student) throws Exception {
        log.info("正在添加学号为" + student.getSno() + "的学生");
        try {
            if (studentService.haveSno(student.getSno())) {
                return Result.fail("添加失败,学号已存在");
            }
            if(student.getPassword()==null){
                student.setPassword("123456");
            }
            if (studentService.add(student)==1) {
                return Result.succ("添加成功,默认密码为123456");
            }
            else {
                return Result.fail("添加失败");
            }
        }
        catch (DataAccessException e) {
            return Result.fail("添加学生失败,缺少必要参数或者参数错误");
        }
    }

    @PostMapping("/deleteBySno/{sno}")
    public Result deleteById(@PathVariable("sno") String sno) {
        log.info("正在删除学生 sno为：" + sno);
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

    @PostMapping("/update")
    public Result updateStudent(@RequestBody Student student) throws Exception {
        try {
            log.info("更新学号为 " + student.getSno()+"的学生");
            if(studentService.updateByCno(student)==1) {
                return Result.succ("更新成功");
            }
            else {
                return Result.fail("更新失败,没有这个学生");
            }
        }
        catch (DataAccessException e) {
            return Result.fail("更新学生信息失败，存在外键依赖");
        }
    }

    /**
     * 查询学生信息
     *
     */
    @PostMapping("/findBySearch")
    public Result findBySearch(@RequestBody Student student) {

        try {
            Integer fuzzy = (Objects.equals(student.getPassword(), "fuzzy")) ? 1 : 0;

            List<Student_vo> list = studentService.findBySearch(student.getSno(), student.getSname(), fuzzy);

            return Result.succ(list);
        }
        catch (Exception e) {
            return Result.fail("查询学生信息失败,请检查参数");
        }
    }

    @GetMapping("/getbysno")
    @ApiOperation(value = "通过sno获取学生信息", notes = "通过sno获取学生信息")
    @ApiParam(name = "sno",value = "学号",required = true)
    public Result getBySno(@RequestParam("sno") String sno){
        try {

            if (studentService.findBySno(sno)==null){
                return Result.fail("查询失败,没有这个学生");
            }
            else{
                Student stu=studentService.findBySno(sno);
                stu.setPassword("");
            return Result.succ(stu);
            }
        }
        catch (Exception e) {
            return Result.fail("查询学生信息失败,没有这个学生");
            }
    }

    @GetMapping("/getbysno_vo")
    @ApiOperation(value = "通过sno获取学生信息", notes = "通过sno获取学生信息")
    @ApiParam(name = "sno",value = "学号",required = true)
    public Result getBySno_vo(@RequestParam("sno") String sno){
        try {

            if (studentService.findBySno(sno)==null){
                return Result.fail("查询失败,没有这个学生");
            }
            else{
                Student_vo stu=studentService.findBySno_vo(sno);
                return Result.succ(stu);
            }
        }
        catch (Exception e) {
            return Result.fail("查询学生信息失败,没有这个学生");
        }
    }

    /**
     * 获取学生表的分页信息
     * */
    @GetMapping("/findByPage/{page}/{size}")
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

    @GetMapping("/get_able_course")
     public Result get_able_course(@RequestParam("sno") String sno) {
        log.info("获取学生可选课程,返回List<ct>");
        try {
            List<Ct_vo> list = studentService.get_able_course(sno);
            return Result.succ(list);
        }
        catch (Exception e) {
            return Result.fail("获取学生可选课程失败");
        }


}

}


