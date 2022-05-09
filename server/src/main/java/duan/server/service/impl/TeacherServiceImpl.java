package duan.server.service.impl;

import duan.server.entity.Teacher;
import duan.server.mapper.TeacherMapper;
import duan.server.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
