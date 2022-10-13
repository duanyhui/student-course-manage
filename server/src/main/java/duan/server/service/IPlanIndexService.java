package duan.server.service;

import duan.server.commom.lang.Result;
import duan.server.entity.PlanIndex;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-12
 */
public interface IPlanIndexService extends IService<PlanIndex> {

    Result createPlanIndex(PlanIndex planIndex) throws Exception;

    Result deletePlanIndex(PlanIndex planIndex);
}
