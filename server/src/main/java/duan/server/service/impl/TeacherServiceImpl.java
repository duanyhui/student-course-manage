package duan.server.service.impl;

import duan.server.commom.lang.Result;
import duan.server.entity.Student;
import duan.server.entity.Teacher;
import duan.server.mapper.TeacherMapper;
import duan.server.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import duan.server.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public Teacher findByTid(Integer tid) {
        return teacherMapper.findByTid(tid);
    }

    public Teacher findByTno(String tno) {
        return teacherMapper.findByTno(tno);
    }

    public boolean insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }

    public boolean deleteByTno(String tno) {
        return teacherMapper.deleteByTno(tno);
    }

    public boolean updateByTno(Teacher teacher) {
        if (teacher.getPassword() != null) {
            teacher.setPassword(HashUtils.getBC(teacher.getPassword()));
        }
        return teacherMapper.updateByTno(teacher);
    }

    public boolean getTno(String tno) {
        return teacherMapper.getTno(tno);
    }

    /**
     * 查询老师分页信息
     * @return List<Teacher>
     */
    public List<Teacher> findByPage(Integer num, Integer size) {
        // num：第几页，size：一页多大
        // num：从零开始
        List<Teacher> teacherList = teacherMapper.findAll();
        ArrayList<Teacher> list = new ArrayList<>();

        int start = size * num;
        int end = size * (num + 1);
        int sz = teacherList.size();

        for (int i = start; i < end && i < sz; i++) {
            list.add(teacherList.get(i));
        }

        return list;
    }


    /**
     * 条件查询老师信息，fuzzy为模糊查询标志位,为0时精确查询，为1时模糊查询
     */
    public List<Teacher> findBySearch(String tno, String tname, Integer fuzzy) {
        Teacher teacher = new Teacher();
        teacher.setTno(tno);
        teacher.setTname(tname);
        fuzzy = (fuzzy == 1) ? 1 : 0;

        System.out.println("模糊查询标志位：" + fuzzy);
        return teacherMapper.findBySearch(teacher, fuzzy);
    }
}
