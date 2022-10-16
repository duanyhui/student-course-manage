package duan.server.controller;

import duan.server.commom.lang.Result;
import duan.server.entity.Ct;
import duan.server.mapper.MajorMapper;
import duan.server.service.impl.ClassTimeServiceImpl;
import duan.server.service.impl.CollegeServiceImpl;
import duan.server.service.impl.CtServiceImpl;
import duan.server.service.impl.MajorServiceImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 *  多表查询的结果返回
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-11
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/utils")
@Slf4j
public class utils {

    @Autowired
    private CollegeServiceImpl collegeService;
    @Autowired
    private MajorServiceImpl majorService;
    @Autowired
    private ClassTimeServiceImpl classTimeService;
    @Autowired
    private CtServiceImpl ctService;

    @GetMapping("/get_college/{collegeid}")
    public Result getCollege (@PathVariable("collegeid") Integer collegeid) throws Exception {
        log.info("正在查询学院id为"+collegeid+"的信息");
        return collegeService.getCollege(collegeid);
    }

    @GetMapping("/get_collegelist")
    public Result getCollegeList () throws Exception {
        log.info("正在获取学院列表");
        return collegeService.getCollegeList();
    }

//    @GetMapping("/get_major")
//    public Result getMajor(Integer collegeid) throws Exception{
//        log.info("学院id"+collegeid+"正在获取专业列表");
//        return majorService.getMajor(collegeid);
//    }
    @GetMapping("/get_major")
    public Result getMajor( Integer collegeid, Integer majorid) throws Exception{
        log.info("学院id"+collegeid+"正在获取专业列表");
        if(collegeid!=null&& majorid!=null){
            return majorService.getMajor(collegeid,majorid);
        }
        return majorService.getMajor(collegeid);

    }

    @GetMapping("/get_majorlist")
    public Result getMajorList() throws Exception{
        return majorService.getMajorList();
    }
    @GetMapping("/get_classtime_byid")
    public Result getClassTimeById(@RequestParam("classtimeid") Integer classtimeid) throws Exception{
        return classTimeService.getClassTimeById(classtimeid);
    }
    @GetMapping("/get_classtime_list")
    public Result getClassTimeList() throws Exception{
        return classTimeService.getClassTimeList();
    }

    @GetMapping("/get_classteacherlist")
    public Result getClassTeacherList() throws Exception{
        return ctService.getClassTeacherList();
    }



}
