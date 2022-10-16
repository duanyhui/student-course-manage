package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.PlanIndex;
import duan.server.entity.PlanTable;
import duan.server.service.impl.PlanIndexServiceImpl;
import duan.server.service.impl.PlanTableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  开设培养计划的相关接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-12
 */

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
        return planIndexService.createPlanIndex(planIndex);
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


}

