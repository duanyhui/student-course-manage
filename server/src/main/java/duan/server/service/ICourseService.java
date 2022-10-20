package duan.server.service;

import duan.server.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import duan.server.entity.SCT_old;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-05
 */
public interface ICourseService extends IService<Course> {

    public Course findCourseByCid(Integer id);
    public boolean insertCourse(Course course);
    public boolean delCourseByCno(String cno);
    public boolean updateCourse(Course course);
    public List<Course> findBySearch(SCT_old sct, Integer fuzzyInt);
    public Course findByCno(String cno);

    List<Course> getCourseList();
}
