package duan.server.service;

import duan.server.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

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
    public List<Student> findBySearch(String sno, String sname, Integer fuzzy);
    public Integer getLength();
    public boolean updateByCno(Student student);
    public boolean deleteBySno(String sno);
    public String getTerm(String sno);
    public boolean add(Student student);
    public boolean haveSno(String sno);
}
