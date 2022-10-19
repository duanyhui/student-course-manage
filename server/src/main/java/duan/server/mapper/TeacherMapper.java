package duan.server.mapper;

import duan.server.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.server.entity.Teacher_vo;
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
public interface TeacherMapper extends BaseMapper<Teacher> {

    List<Teacher> findAll();

    Teacher findByTid(@Param("tid") Integer tid);

    Teacher findByTno(@Param("tno") String tno);

    boolean insertTeacher(@Param("teacher") Teacher teacher);

    boolean deleteByTno(@Param("tno") String tno);

    boolean updateByTno(@Param("teacher") Teacher teacher);

    boolean getTno(@Param("tno") String tno);


    List<Teacher_vo> findBySearch(@Param("tea") Teacher teacher);


    List<Teacher_vo> findBySearch_mohu(@Param("tea")Teacher teacher);
}
