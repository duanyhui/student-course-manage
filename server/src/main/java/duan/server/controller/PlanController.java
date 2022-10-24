package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Course;
import duan.server.entity.PlanIndex;
import duan.server.entity.PlanIndex_vo;
import duan.server.entity.PlanTable;
import duan.server.service.impl.PlanIndexServiceImpl;
import duan.server.service.impl.PlanTableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  开设培养计划的相关接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-12
 */
@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanIndexServiceImpl planIndexService;
    @Autowired
    private PlanTableServiceImpl planTableService;

    /**
     * 添加开设培养计划（每个专业一个）管理员专用
     * @param planIndex
     * @return
     */
    @PostMapping("/create_plan_index")
    public Result createPlanIndex(@RequestBody PlanIndex planIndex) throws Exception {
        log.info("正在创建开设培养计划的索引");
        log.info(planIndex.toString());
        // 直接创建8个学期的培养计划索引表
        for (int i = 1; i <= 20; i++) {
            planIndex.setTermid(i);
            planIndexService.createPlanIndex(planIndex);
        }
        return Result.succ("创建成功");
    }



    @PostMapping("/delete_plan_index")
    public Result deletePlanIndex(@RequestBody PlanIndex planIndex) throws Exception {
        log.info("正在删除开设培养计划的索引id为"+planIndex.getPlanid());
        return planIndexService.deletePlanIndex(planIndex.getPlanid());
    }

    /**
     * 根据培养计划的索引id创建培养计划表
     * @param planTable
     * @return msg
     * @throws Exception
     */
    @PostMapping("/create_plan_table")
    public Result createPlanTable(@RequestBody PlanTable planTable) throws Exception {
        log.info("正在创建开设培养计划的表");
        log.info(planTable.toString());

        return planTableService.createPlanTable(planTable);

    }


    @PostMapping("/delete_plan_table_by_planid")
    public Result deletePlanTableByPlanId(@RequestBody PlanTable planTable) throws Exception {
        log.info("正在删除开设培养计划的表planid为"+planTable.getPlanid());
        return planTableService.deletePlanTableByPlanId(planTable.getPlanid());
    }

    @PostMapping("delete_plan_table_by_id")
    public Result deletePlanTableById(@RequestBody PlanTable planTable) throws Exception {
        log.info("正在删除开设培养计划的表id为"+planTable.getId());
        return planTableService.deletePlanTable(planTable.getId());
    }

    @PostMapping("delete_plan_table_by_planid_and_cid")
    public Result deletePlanTableByPlanIdAndCid(@RequestBody PlanTable planTable) throws Exception {
        log.info("正在删除开设培养计划的表planid为"+planTable.getPlanid()+"cid为"+planTable.getCid());

        return planTableService.deletePlanTableByPlanIdAndCid(planTable.getPlanid(),planTable.getCid());
    }

    @PostMapping("/update_plan_table_by_id")
    public Result updatePlanTableById(@RequestBody PlanTable planTable) throws Exception {
        log.info("正在更新开设培养计划的表id为"+planTable.getId());
        return planTableService.updatePlanTableById(planTable);
    }

    @GetMapping("/get_plan_tablelist")
    public Result getPlanTableList() throws Exception {
        log.info("正在获取所有开设培养计划的表");
        return planTableService.getPlanTableList();
    }

    @GetMapping("/get_plan_tablelist_by_planid")
    public Result getPlanTableListByPlanId(@RequestParam("planid") Integer planid) throws Exception {
        log.info("正在获取所有开设培养计划的表");
        log.info("planid为"+planid);
        return planTableService.getPlanTableListByPlanId(planid);
    }

    @GetMapping("/get_plan_table_list")
    public Result getPlanTable(Integer planid ,String sno) throws Exception {
        log.info("正在获取培养计划");
        log.info("planid为"+planid+"sno为"+sno);
        if (planid==null&&sno==null){
            throw new Exception("参数错误");
        }
        if(planid!=null){
            return Result.succ(planTableService.getCourseListByPlanid(planid));
        }
        else {
            log.info(planTableService.getCourseListBySno(sno).toString());
            return Result.succ(planTableService.getCourseListBySno(sno));

        }
    }

    @GetMapping("/get_plan_index_list")
    public Result getPlanIndexList() throws Exception {
        log.info("正在获取所有开设培养计划的索引");
        List<PlanIndex_vo> planIndex_vo = planIndexService.getPlanIndexList();

        return Result.succ(planIndex_vo);
    }

    @GetMapping("/get_plan_list_by_sno")
    public Result getPlanTableBySno(@RequestParam("sno") String sno) throws Exception {
        log.info("正在获取学生的培养计划");
        log.info("sno为"+sno);
        List<Course> course = planTableService.getPlanTableBySno(sno);
        return Result.succ(course);
    }

    @GetMapping("/get_course_list")
    public Result getCourseList(Integer collegeid,Integer majorid,Integer termid) throws Exception {
        log.info("正在获取培养计划对应的课程列表");
        log.info("collegeid为"+collegeid+"majorid为"+majorid+"termid为"+termid);
        List<Course> course = planTableService.getCourseList(collegeid,majorid,termid);
        return Result.succ(course);
    }

//    @GetMapping("/get_plan_table_list")
//    public Result getPlanTableList() throws Exception {
//        log.info("正在获取所有开设培养计划的表");
//        List<PlanTable_vo> planTable_vo = planTableService.getPlanTableList();
//        return Result.succ(planTable_vo);
//    }


}

