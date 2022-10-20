package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.College;
import duan.server.mapper.CollegeMapper;
import duan.server.service.ICollegeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-11
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements ICollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public Result getCollege(Integer collegeid) throws Exception {
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(College::getCollegeid, collegeid);
        College college = collegeMapper.selectOne(queryWrapper);
        if(college == null){
            throw new Exception("学院不存在");
        }
        return Result.succ(college.getCollegename());
    }

    @Override
    public Result getCollegeList() {
        List<College> collegeList = collegeMapper.selectList(null);
        return Result.succ(collegeList);
    }

    @Override
    public Result createCollege(String college_name) {
        College college = new College();
        college.setCollegename(college_name);

        try {
            if(collegeMapper.insert(college) == 1){
                return Result.succ(getCollegeId(college_name));
            }
            return Result.fail("创建失败");
        }
        catch (Exception e){
            throw new RuntimeException("创建学院失败,学院名重复");
        }


    }

    private Integer getCollegeId(String college_name) {
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(College::getCollegename,college_name);
        College college = collegeMapper.selectOne(queryWrapper);
        if (college == null){
            throw new RuntimeException("学院不存在");
        }
        return college.getCollegeid();

    }
}
