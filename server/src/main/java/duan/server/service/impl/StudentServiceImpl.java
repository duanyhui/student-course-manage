package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.entity.Student;
import duan.server.entity.Student_vo;
import duan.server.mapper.StudentMapper;
import duan.server.service.IPlanIndexService;
import duan.server.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import duan.server.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-04
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public Student findBySno(String sno) {
        return studentMapper.findBySno(sno);
    }


    /**
     * 查询学生分页信息
     * @return List<Student>
     */
    @Override
    public List<Student> findByPage(Integer num, Integer size) {
        // num：第几页，size：一页多大
        // num：从零开始
        List<Student> studentList = studentMapper.findAll();
        ArrayList<Student> list = new ArrayList<Student>();

        int start = size * num;
        int end = size * (num + 1);
        int sz = studentList.size();

        for (int i = start; i < end && i < sz; i++) {
            list.add(studentList.get(i));
        }

        return list;
    }

    /**
     * 条件查询学生总数，fuzzy为模糊查询标志位,为0时精确查询，为1时模糊查询
     * @return
     */
    @Override
    public List<Student_vo> findBySearch(String sno, String sname, Integer fuzzy) {
        Student student = new Student();
        student.setSno(sno);
        student.setSname(sname);
        fuzzy = (fuzzy == 1) ? 1 : 0;

        System.out.println("模糊查询标志位：" + fuzzy);
        if(fuzzy == 1){
           return studentMapper.findBySearch_mohu(student);
        }

        return studentMapper.findBySearch(student);

    }

    @Override
    public Integer getLength() {
        return studentMapper.findAll().size();
    }

    @Override
    public int updateByCno(Student student) throws Exception {

        //todo 专业和学院不能修改
        student.setCollegeid(studentMapper.findBySno(student.getSno()).getCollegeid());
        student.setMajorid(studentMapper.findBySno(student.getSno()).getMajorid());
        if(student.getTermid()!=null){
            Integer planid = planIndexService.getPlanid(student.getCollegeid(), student.getMajorid(), student.getTermid());
            student.setPlanid(planid);
        }
        if (student.getPassword() != null) {
            student.setPassword(HashUtils.getBC(student.getPassword()));
        }
        Student stu = new Student(student);
        return studentMapper.update(stu, new LambdaQueryWrapper<Student>().eq(Student::getSno, student.getSno()));
    }

    @Override
    public boolean deleteBySno(String sno) {
        return studentMapper.deleteBySno(sno);
    }

    @Override
    public String getTerm(String sno) {
        return studentMapper.getTerm(sno);
    }

    @Autowired
    private IPlanIndexService planIndexService;
    @Override
    public int add(Student student) throws Exception {
        Integer planid = planIndexService.getPlanid(student.getCollegeid(), student.getMajorid(), student.getTermid());
        student.setPlanid(planid);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        Student stu = new Student(student);
        stu.setPassword(HashUtils.getBC(student.getPassword()));
        return studentMapper.insert(stu);
//        return studentMapper.add(student);
    }

    @Override
    public boolean haveSno(String sno) {
                return studentMapper.haveSno(sno);
    }

    @Override
    public Student_vo findBySno_vo(String sno) {
        return studentMapper.findBySno_vo(sno);
    }
}
