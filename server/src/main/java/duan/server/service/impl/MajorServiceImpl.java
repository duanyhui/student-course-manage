package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.Major;
import duan.server.mapper.MajorMapper;
import duan.server.service.IMajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-11
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public Integer createMajor(String majorname, Integer collegeid) {

        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Major::getMajorname, majorname);
        queryWrapper.eq(Major::getCollegeid, collegeid);
        if (majorMapper.selectOne(queryWrapper) != null) {
            throw new RuntimeException("专业已存在");
        }
        Major major = new Major();
        major.setMajorname(majorname);
        major.setCollegeid(collegeid);

        try {
            if (majorMapper.insert(major) == 1) {
                return majorMapper.selectOne(queryWrapper).getMajorid();
            }
            return null;
        }
        catch (Exception e){
            throw new RuntimeException("创建专业失败,专业名重复");
        }
    }

    @Override
    public Result getMajor(Integer collegeid) throws Exception {
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Major::getCollegeid, collegeid);
        if(majorMapper.selectList(queryWrapper).size() == 0){
            throw new Exception("学院不存在");
        }
        return Result.succ(majorMapper.selectList(queryWrapper));
    }

    @Override
    public Result getMajorList() {
        return Result.succ(majorMapper.selectList(null));
    }

    @Override
    public Result getMajor(Integer collegeid, Integer majorid) {
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Major::getCollegeid, collegeid);
        queryWrapper.eq(Major::getMajorid, majorid);
        queryWrapper.select(Major::getMajorname);
        if (majorMapper.selectOne(queryWrapper) == null) {
            throw new RuntimeException("专业不存在");
        }
        return Result.succ(majorMapper.selectOne(queryWrapper).getMajorname());
    }


}
