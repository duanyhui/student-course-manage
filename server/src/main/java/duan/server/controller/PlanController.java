package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.PlanIndex;
import duan.server.service.impl.PlanIndexServiceImpl;
import duan.server.service.impl.PlanTableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
        return planIndexService.createPlanIndex(planIndex);
    }
    @PostMapping("/delete_plan_index")
    public Result deletePlanIndex(@RequestBody PlanIndex planIndex) throws Exception {
        log.info("正在删除开设培养计划的索引");
        return planIndexService.deletePlanIndex(planIndex);
    }


}

