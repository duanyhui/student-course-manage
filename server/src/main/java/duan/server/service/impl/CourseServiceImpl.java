package duan.server.service.impl;

import duan.server.entity.Course;
import duan.server.entity.SCT;
import duan.server.mapper.CourseMapper;
import duan.server.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-05
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course findCourseByCid(Integer id) {
        return courseMapper.findCourseByCid(id);
    }

    @Override
    public boolean insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    @Override
    public boolean delCourseByCno(String cno) {
        return courseMapper.deleteByCno(cno);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

//    public Map<String,String> findAllCourse() {
//        return courseMapper.findAllCourse();
//    }

    @Override
    public List<Course> findBySearch(SCT sct, Integer fuzzyInt) {
        fuzzyInt = (fuzzyInt == 1) ? 1 : 0;
        return courseMapper.findBySearch(sct,fuzzyInt);
    }

    @Override
    public Course findByCno(String cno) {
        return courseMapper.findByCno(cno);
    }
}
