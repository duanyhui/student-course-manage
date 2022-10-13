package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Ct;
import duan.server.entity.Teacher;
import duan.server.service.impl.CtServiceImpl;
import duan.server.service.impl.LoginServiceImpl;
import duan.server.service.impl.TeacherServiceImpl;
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
 * @since 2022-05-05
 */
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public Result Login(@RequestBody Teacher teacher) throws Exception {
        System.out.println("正在验证老师登陆 " + teacher);
        Teacher tea = teacherService.findByTno(teacher.getTno());
        System.out.println("老师信息：" + tea );

        if(tea == null){
            return Result.fail("操作失败,账号或密码不正确");
        }
        else{
            return loginService.login(teacher);
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

    @GetMapping("/getByTno/{tno}")
    public Result getByTno(@PathVariable("tno") String tno) {
        Teacher teacher = teacherService.findByTno(tno);
       try {
           if (teacher == null) {
               return Result.fail("操作失败,老师不存在");
           }
           else {
               teacher.setPassword("");
               return Result.succ(teacher);
           }
       }
       catch (DataAccessException e) {
           return Result.fail("操作失败");
       }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher) {
        if (teacher.getPassword()==null || teacher.getPassword().equals("")) {
            teacher.setPassword("123456");
        }
        try {
            System.out.println("正在添加老师 " + teacher);
            if (teacherService.getTno(teacher.getTno())) {
                return Result.fail("操作失败,老师已存在");
            }
            int flag = teacherService.insertTeacher(teacher);
            return flag==1 ? Result.succ("操作成功,初始密码为123456") : Result.fail("操作失败,存在编号相同的老师");
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
            log.info("正在更新为 " + teacher.getTno()+"的老师信息");
            int flag = teacherService.updateByTno(teacher);
            return flag==1 ? Result.succ("操作成功") : Result.fail("操作失败,老师不存在");
        } catch (DataAccessException e) {
            return Result.fail("操作失败,数据库异常");
        }
    }


    /**
     * 获取老师表的分页信息
     * */
    @GetMapping("/findByPage/{page}/{size}")
    public Result findByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        try {
            System.out.println("查询老师列表分页 " + page + " " + size);
            List<Teacher> list=teacherService.findByPage(page, size);
            return Result.succ(list);
        }
        catch (Exception e) {
            return Result.fail("查询老师列表分页失败");
        }
    }

    /**
     * 查询老师信息，fuzzy为模糊查询标志位,为0时精确查询，为1时模糊查询
     *(这里唯一用了teaacher的password当做模糊查询条件，不想改了，屎山预定)
     */
    @PostMapping("/findBySearch")
    public Result findBySearch(@RequestBody Teacher teacher) {

        try {
            System.out.println("查看password " + teacher.getPassword());
            Integer fuzzy = (Objects.equals(teacher.getPassword(), "fuzzy")) ? 1 : 0;
            /**
             * fuzzy为模糊查询标志位，当传入的teacher的pwd为fuzzy时，模糊查询
             */
            List<Teacher> list = teacherService.findBySearch(teacher.getTno(), teacher.getTname(), fuzzy);
            System.out.println("查看list " + list);
            list.forEach(tea -> tea.setPassword(""));  //密码设置为空
            return Result.succ(list);
        }
        catch (Exception e) {
            return Result.fail("查询老师信息失败,请检查参数");
        }
    }

    @Autowired
    private CtServiceImpl ctService;
    @PostMapping("/open_course")
    public Result openCourse(@RequestBody Ct ct) throws Exception{
        return ctService.openCourse(ct);
    }

    @GetMapping("/get_classteacherlist/{tno}")
    public Result getClassTeacherList(@PathVariable("tno") String tno) throws Exception{
        return ctService.getClassTeacherListByTno(tno);
    }

}

