package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.entity.Class;
import duan.server.entity.Class_vo;
import duan.server.mapper.ClassMapper;
import duan.server.service.IClassService;
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
 * @since 2022-10-23
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

    @Autowired
    private ClassMapper classMapper;
    @Override
    public void addClass(Integer collegeid, Integer majorid, Integer classid, Integer schoolyear) {
        classMapper.addClass(collegeid,majorid,classid,schoolyear);
    }

    @Override
    public List<Class_vo> getClassList(Integer collegeid, Integer majorid, Integer schoolyear) {
        return classMapper.getClassList(collegeid,majorid,schoolyear);
    }

    @Override
    public void deleteClass(Integer collegeid, Integer majorid, Integer classid,Integer schoolyear) {
        LambdaQueryWrapper<Class> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Class::getCollegeid,collegeid)
                .eq(Class::getMajorid,majorid)
                .eq(Class::getClassid,classid)
                 .eq(Class::getSchoolyear,schoolyear);
        classMapper.delete(wrapper);
    }

    @Override
    public List<Class_vo> getClassList() {
        return classMapper.getClassList_all();
    }

    @Override
    public List<Class_vo> getClassList(Integer schoolyear) {
        return classMapper.getClassList_by_schoolyear(schoolyear);
    }

    @Override
    public Integer createOnclassid(Integer schoolyear, Integer collegeid, Integer majorid, Integer classid) {
        Integer onclassid = classMapper.getOnclassid(schoolyear,collegeid,majorid,classid);
        onclassid++;
        classMapper.updateOnclassid(schoolyear,collegeid,majorid,classid,onclassid);
        return onclassid;
    }
}
