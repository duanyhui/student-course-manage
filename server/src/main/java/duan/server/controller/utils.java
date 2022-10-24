package duan.server.controller;

import duan.server.commom.lang.Result;
import duan.server.entity.*;
import duan.server.entity.Class;
import duan.server.mapper.MajorMapper;
import duan.server.service.impl.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create_college")
    public Result createCollege(@RequestParam("collegename") String collegename){
        log.info("正在创建"+collegename+"学院");
        return collegeService.createCollege(collegename);
    }

    /**
     * 创建专业并返回专业id
     * @param majorname
     * @param collegeid
     * @return 专业id
     */
    @Autowired
    private PlanIndexServiceImpl planIndexService;
    @PostMapping("/create_major")
    public Result createMajor(@Param("majorname") String majorname, @Param("collegeid") Integer collegeid) throws Exception {
        log.info("正在创建"+majorname+"专业");
        Integer majorid = majorService.createMajor(majorname, collegeid);
        PlanIndex planIndex = new PlanIndex();
        planIndex.setMajorid(majorid);
        planIndex.setCollegeid(collegeid);
        //22/10/24 凌晨2点34分
        // 这里写9是因为要考虑毕业的情况，我数据库设置了约束一定要有9个学期，我之后才能变更到第9个学期
        for (int i = 1; i <= 20; i++) {
            planIndex.setTermid(i);
            planIndexService.createPlanIndex(planIndex);
        }
        return Result.succ(majorid);
    }

    @GetMapping("/get_college_name")
    public Result getCollege ( Integer collegeid) throws Exception {
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

    @GetMapping("/get_student_classlist")
    public Result getStudentClassList(String sname,String cname,String tname) throws Exception{
        if (sname ==null) sname="";
        if (cname ==null) cname="";
        if (tname ==null) tname="";
        List<Ct_vo> list = ctService.getStudentClassList_Admin(sname,cname,tname);
        return Result.succ(list);

    }
    @Autowired
    private StudentServiceImpl studentService;
    @PostMapping("/update_term")
    public Result updateTerm() throws Exception {
        try {
            studentService.updateTerm();
            return Result.succ("操作成功");
        } catch (Exception e) {
            return Result.fail("操作失败");
        }

    }

    @PostMapping("/update_term_before")
    public Result updateTermBefore() throws Exception {
        try {
            studentService.updateTermBefore();
            return Result.succ("操作成功");
        } catch (Exception e) {
            return Result.fail("操作失败");
        }

    }

    @Autowired
    private ClassServiceImpl classService;

    /**
     * 创建班级
     * @param collegeid
     * @param majorid
     * @param classid
     * @return
     * @throws Exception
     */
    @PostMapping("/add_class")
    public Result addClass(@RequestParam("collegeid") Integer collegeid,
                           @RequestParam("majorid") Integer majorid,
                           @RequestParam("classid") Integer classid,
                           @RequestParam("schoolyear") Integer schoolyear)
            throws Exception {
        try {
            classService.addClass(collegeid,majorid,classid,schoolyear);
            return Result.succ("操作成功");
        } catch (Exception e) {
            return Result.fail("操作失败");
        }

    }

    @GetMapping("/get_classlist_by_college_major_schoolyear")
    public Result getClassList(@RequestParam("collegeid") Integer collegeid,
                               @RequestParam("majorid") Integer majorid,
                               @RequestParam("schoolyear") Integer schoolyear)
            throws Exception{
        List<Class_vo> list = classService.getClassList(collegeid,majorid,schoolyear);
        return Result.succ(list);
    }

    @GetMapping("/get_classlist")
    public Result getClassList() throws Exception{
        List<Class_vo> list = classService.getClassList();
        return Result.succ(list);
    }

    @GetMapping("/get_classlist_by_schoolyear")
    public Result getClassList(@RequestParam("schoolyear") Integer schoolyear) throws Exception{
        List<Class_vo> list = classService.getClassList(schoolyear);
        return Result.succ(list);
    }

    @PostMapping("/delete_class")
    public Result deleteClass(@RequestParam("collegeid") Integer collegeid,
                              @RequestParam("majorid") Integer majorid,
                              @RequestParam("classid") Integer classid,
                              @RequestParam("schoolyear") Integer schoolyear)
            throws Exception {
        try {
            classService.deleteClass(collegeid,majorid,classid,schoolyear);
            return Result.succ("操作成功");
        } catch (Exception e) {
            return Result.fail("操作失败");
        }
    }

    @Autowired
    private SchoolyearServiceImpl schoolyearService;
    @GetMapping("/get_schoolyear_list")
    public Result getSchoolyearList() throws Exception{
        List<Schoolyear> list = schoolyearService.getSchoolyearList();
        return Result.succ(list);
    }

    @PostMapping("/add_schoolyear")
    public Result addSchoolyear(@RequestParam("schoolyear") Integer schoolyear) throws Exception{
        try {
            schoolyearService.addSchoolyear(schoolyear);
            return Result.succ("操作成功");
        } catch (Exception e) {
            return Result.fail("操作失败");
        }
    }






}
