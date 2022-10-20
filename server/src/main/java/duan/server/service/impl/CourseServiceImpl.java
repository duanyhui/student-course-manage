package duan.server.service.impl;

import duan.server.entity.Course;
import duan.server.entity.SCT_old;
import duan.server.mapper.CourseMapper;
import duan.server.service.ICourseService;
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
    public List<Course> findBySearch(SCT_old sct, Integer fuzzyInt) {
        fuzzyInt = (fuzzyInt == 1) ? 1 : 0;
        if (fuzzyInt==1){
            return courseMapper.findBySearch_mohu(sct);
        }
        return courseMapper.findBySearch(sct);
    }

    @Override
    public Course findByCno(String cno) {
        return courseMapper.findByCno(cno);
    }

    @Override
    public List<Course> getCourseList() {

        return courseMapper.selectList(null);
    }
}
