package duan.server.mapper;

import duan.server.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-04
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    Student findBySno(@Param("sno") String sno);


    List<Student> findAll();

    List<Student> findBySearch(@Param("student") Student student,@Param("fuzzy")  Integer fuzzy);


//    boolean updateByCno(@Param("student") Student student);

    boolean deleteBySno(@Param("sno") String sno);

    String getTerm(@Param("sno") String sno);

    boolean add(@Param("student") Student student);

    boolean haveSno(@Param("sno") String sno);
}
