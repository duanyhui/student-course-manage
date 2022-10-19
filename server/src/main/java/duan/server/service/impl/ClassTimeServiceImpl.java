package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.ClassTime;
import duan.server.mapper.ClassTimeMapper;
import duan.server.service.IClassTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-13
 */
@Service
public class ClassTimeServiceImpl extends ServiceImpl<ClassTimeMapper, ClassTime> implements IClassTimeService {

    @Autowired
    private ClassTimeMapper classTimeMapper;
    @Override
    public Result getClassTimeById(Integer classtimeid) {
        LambdaQueryWrapper<ClassTime> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClassTime::getClasstimeid,classtimeid);
        ClassTime classTime = classTimeMapper.selectOne(queryWrapper);
        if (classTime == null){
            return Result.fail("课程时间不存在");
        }
        return Result.succ(classTime.getClasstime());
    }

    @Override
    public Result getClassTimeList() {
        return Result.succ(classTimeMapper.selectList(null));
    }
}
