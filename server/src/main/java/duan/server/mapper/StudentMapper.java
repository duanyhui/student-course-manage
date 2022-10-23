package duan.server.mapper;

import duan.server.entity.Ct_vo;
import duan.server.entity.Sct;
import duan.server.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.server.entity.Student_vo;
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

    List<Student_vo> findBySearch(@Param("stu") Student stu);

    List<Student_vo> findBySearch_mohu(@Param("stu") Student student);

//    boolean updateByCno(@Param("student") Student student);

    boolean deleteBySno(@Param("sno") String sno);

    String getTerm(@Param("sno") String sno);

    boolean add(@Param("student") Student student);

    boolean haveSno(@Param("sno") String sno);


    Student_vo findBySno_vo(String sno);

    List<Ct_vo> get_able_course(String sno);

    List<Sct> have_select_course(Integer ctid, String sno);

    List<Ct_vo> get_selected_course(String sno);

    void updateTerm();

    void updateTermBefore();

//    void select_course(Integer ctid, String sno);
}
