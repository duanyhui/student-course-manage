package duan.server.service;

import duan.server.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import duan.server.entity.Student_vo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-04
 */
public interface IStudentService extends IService<Student> {

    public Student findBySno(String sno);
    public List<Student> findByPage(Integer num, Integer size);
    public List<Student_vo> findBySearch(String sno, String sname, Integer fuzzy);
    public Integer getLength();
    public int updateByCno(Student student) throws Exception;
    public boolean deleteBySno(String sno);
    public String getTerm(String sno);
    public int add(Student student) throws Exception;
    public boolean haveSno(String sno);

    Student_vo findBySno_vo(String sno);
}
