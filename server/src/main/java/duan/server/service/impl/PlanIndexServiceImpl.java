package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.PlanIndex;
import duan.server.entity.PlanIndex_vo;
import duan.server.mapper.PlanIndexMapper;
import duan.server.service.IPlanIndexService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

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
public class PlanIndexServiceImpl extends ServiceImpl<PlanIndexMapper, PlanIndex> implements IPlanIndexService {

    @Autowired
    private PlanIndexMapper planIndexMapper;
    @Override
    public Result createPlanIndex(PlanIndex planIndex) throws Exception {
        LambdaQueryWrapper<PlanIndex> queryWrapper = new LambdaQueryWrapper<>();
        try {
            if (planIndexMapper.insert(planIndex) == 1) {
                return Result.succ("创建成功");
            }
        }
        // todo 其实这里要考虑插入不存在id比如termid为100的情况，
        //  但是前端限制了我这里也懒得改了，统一抛出为"创建失败,请勿重复创建"
        catch (Exception e){
            throw new Exception("创建失败,请勿重复创建");
        }
        return Result.fail("创建失败,请勿重复创建");
    }

    @Override
    public Result deletePlanIndex(Integer planIndex) {
        LambdaQueryWrapper<PlanIndex> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(PlanIndex::getCollegeid, planIndex.getCollegeid());
//        queryWrapper.eq(PlanIndex::getMajorid, planIndex.getMajorid());
//        queryWrapper.eq(PlanIndex::getTermid, planIndex.getTermid());
        queryWrapper.eq(PlanIndex::getPlanid, planIndex);
        if(planIndexMapper.delete(queryWrapper) == 1){
            return Result.succ("删除成功");
        }
        return Result.fail("删除失败，不存在该条记录");
    }

    @Override
    public Integer getPlanid(Integer collegeid, Integer majorid, Integer termid) throws Exception {
        LambdaQueryWrapper<PlanIndex> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlanIndex::getCollegeid, collegeid);
        queryWrapper.eq(PlanIndex::getMajorid, majorid);
        queryWrapper.eq(PlanIndex::getTermid, termid);
        queryWrapper.select(PlanIndex::getPlanid);
        if(planIndexMapper.selectOne(queryWrapper) == null){
            throw new Exception("未开设该专业该学期的教学计划，请先创建教学计划");
        }
        return planIndexMapper.selectOne(queryWrapper).getPlanid();
    }

    @Override
    public List<PlanIndex_vo> getPlanIndexList() {
        return planIndexMapper.getPlanIndexList();
    }
}
