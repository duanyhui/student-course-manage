package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Course;
import duan.server.entity.SCT;
import duan.server.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-08
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    public CourseServiceImpl courseService;

    @PostMapping("/add")
    public Result add(@RequestBody Course course){
        try {
            boolean a=courseService.insertCourse(course);
            System.out.println("插入课程表，插入课程表的Cno为：" + course.getCno());
            return Result.succ("增加课程成功");
        }
        // 捕获数据库异常
        catch (DataAccessException e){
            return Result.fail("课程编号已存在或者没有填选必填项，请重新输入");
        }
    }

    @PostMapping("/deleteByCno/{cno}")
    public Result delbycno(@PathVariable String cno){
        try {
            boolean a=courseService.delCourseByCno(cno);
            if (!a){
                return Result.fail("课程编号不存在，请重新输入");
            }
            System.out.println("删除课程操作boolen值：" + a);
            System.out.println("删除课程表，删除课程表的Cno为：" + cno);
            return Result.succ("删除课程成功");
        }
        catch (DataAccessException e){
            return Result.fail("存在外键依赖，可能是该课程已被学生选择，请重新输入");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Course course){
        try {
            boolean a = courseService.updateCourse(course);
            if (!a) {
                return Result.fail("课程编号不存在，请重新输入");
            }
            System.out.println("更新课程表，更新后的课程表为：" + course);
            return Result.succ(course);
        }
        catch (DataAccessException e){
            return Result.fail("存在外键依赖，可能是该课程已被学生选择，请先删除该课程的学生选课记录，再进行更新");
        }
    }
//    @GetMapping("/findall")
//    public Result findall(){
//        try {
//            return Result.succ(courseService.findAllCourse());
//        }
//        catch (DataAccessException e){
//            return Result.fail("查询失败");
//        }
//
//    }
    @GetMapping("/findByCno/{cno}")
    public Result findByCno(@PathVariable String cno){
        try {
            return Result.succ(courseService.findByCno(cno));
        }
        catch (DataAccessException e){
            return Result.fail("查询失败");
        }

    }

    /**
     * 模糊查询课程
     */
    @PostMapping("/findBySearch")
    public Result findBySearch(@RequestBody SCT sct){
        try{
            Integer fuzzyInt = (Objects.equals(sct.getFuzzy(), "true")) ? 1 : 0;
            return Result.succ(courseService.findBySearch(sct, fuzzyInt));
        }
        catch (DataAccessException e){
            return Result.fail("查询失败");
        }

    }



}






