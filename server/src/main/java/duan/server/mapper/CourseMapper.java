package duan.server.mapper;

import duan.server.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.server.entity.SCT_old;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-05
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 按照课程名称查询课程
     * 模糊查询
     * @author duanyhui
     * @since 2022-05-05
     * */
    public Course findCourseByCid(@Param("cid") Integer cid);

    public Course findCourseByCno(@Param("cno") String cno);

    public List<Course> findCourseBySearch(@Param("cid") Integer cid,
                                     @Param("cname") String cname,@Param("fuzzy") Integer fuzzy);
                                                                         /**
                                                                          * fuzzy为模糊查询标志符
                                                                          * */
    //    insert
    public boolean insertCourse(@Param("course") Course course);

    //    update
    public boolean updateCourse(@Param("course") Course course);

    //    delete
    public boolean deleteByCno(@Param("cno") String cno);




    List<Course> findBySearch(@Param("sct") SCT_old sct);

    Course findByCno(@Param("cno") String cno);

    List<Course> findBySearch_mohu(@Param("sct") SCT_old sct);
}

