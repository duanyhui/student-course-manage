package duan.server.mapper;

import duan.server.entity.Course;
import duan.server.entity.PlanTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-12
 */
public interface PlanTableMapper extends BaseMapper<PlanTable> {

    List<Course> getCourseListByPlanid( Integer planid);

    List<Course> getCourseListBySno(String sno);
}
