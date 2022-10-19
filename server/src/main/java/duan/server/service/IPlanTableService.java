package duan.server.service;

import duan.server.commom.lang.Result;
import duan.server.entity.Course;
import duan.server.entity.PlanTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-12
 */
public interface IPlanTableService extends IService<PlanTable> {

    Result createPlanTable(PlanTable planTable) throws Exception;

    Result deletePlanTable(Integer id);

    Result deletePlanTableByPlanId(Integer planid);

    Result getPlanTableList();

    Result updatePlanTableById(PlanTable planTable);

    Result getPlanTableListByPlanId(Integer planid);

    List<Course> getCourseListByPlanid(Integer planid);

    List<Course> getCourseListBySno(String sno);

    List<Course> getPlanTableBySno(String sno);

    List<Course> getCourseList(Integer collegeid, Integer majorid, Integer termid);
}
