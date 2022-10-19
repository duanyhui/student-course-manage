package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.Course;
import duan.server.entity.PlanTable;
import duan.server.mapper.CourseMapper;
import duan.server.mapper.PlanTableMapper;
import duan.server.service.IPlanTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-12
 */
@Service
public class PlanTableServiceImpl extends ServiceImpl<PlanTableMapper, PlanTable> implements IPlanTableService {

    @Autowired
    private PlanTableMapper planTableMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Result createPlanTable(PlanTable planTable) throws Exception {
        LambdaQueryWrapper<PlanTable> queryWrapper = new LambdaQueryWrapper<>();
            Course course = courseMapper.selectOne(new LambdaQueryWrapper<Course>().eq(Course::getCid, planTable.getCid()));
            if (courseMapper.selectOne(new LambdaQueryWrapper<Course>().eq(Course::getCid, planTable.getCid())) == null) {
                throw new Exception("课程不存在");
            }
            if (planTableMapper.insert(planTable) == 1) {
                return Result.succ("创建成功");
            }
            else {
                throw new Exception("创建失败,缺少必要参数");
            }

    }

    @Override
    public Result deletePlanTable(Integer id) {
        LambdaQueryWrapper<PlanTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlanTable::getId,id);
        if (planTableMapper.delete(queryWrapper) == 1) {
            return Result.succ("删除成功");
        }
        else {
            return Result.fail("删除失败，不存在该计划");
        }
    }

    @Override
    public Result deletePlanTableByPlanId(Integer planid) {
        LambdaQueryWrapper<PlanTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlanTable::getPlanid,planid);
        if (planTableMapper.delete(queryWrapper) != 0) {
            return Result.succ("删除成功");
        }
        else {
            return Result.fail("删除失败，不存在该计划");
        }
    }

    @Override
    public Result updatePlanTableById(PlanTable planTable) {
        LambdaQueryWrapper<PlanTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlanTable::getId,planTable.getId());
        if (planTableMapper.update(planTable,queryWrapper) == 1) {
            return Result.succ("更新成功");
        }
        else {
            return Result.fail("更新失败，不存在该计划");
        }
    }

    @Override
    public Result getPlanTableListByPlanId(Integer planid) {
        LambdaQueryWrapper<PlanTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlanTable::getPlanid,planid);
        return Result.succ(planTableMapper.selectList(queryWrapper));
    }

    @Override
    public List<Course> getCourseListByPlanid(Integer planid) {
        return planTableMapper.getCourseListByPlanid(planid);
    }

    @Override
    public List<Course> getCourseListBySno(String sno) {
        return planTableMapper.getCourseListBySno(sno);
    }

    @Override
    public List<Course> getPlanTableBySno(String sno) {
        return planTableMapper.getPlanTableBySno(sno);
    }

    @Override
    public List<Course> getCourseList(Integer collegeid, Integer majorid, Integer termid) {
        return planTableMapper.getCourseList(collegeid,majorid,termid);
    }

    @Override
    public Result getPlanTableList() {
        return Result.succ(planTableMapper.selectList(null));
    }




}
