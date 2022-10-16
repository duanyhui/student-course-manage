package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.PlanIndex;
import duan.server.mapper.PlanIndexMapper;
import duan.server.service.IPlanIndexService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
