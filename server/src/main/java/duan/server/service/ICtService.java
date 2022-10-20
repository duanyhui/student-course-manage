package duan.server.service;

import duan.server.commom.lang.Result;
import duan.server.entity.Ct;
import com.baomidou.mybatisplus.extension.service.IService;
import duan.server.entity.Ct_vo;
import duan.server.entity.SCT_old;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-10
 */
public interface ICtService extends IService<Ct> {
    public int add(Ct ct);
    public int delByCt(Ct ct);
    public List<SCT_old> getByTno(String tno);
    public List<SCT_old> getAll();
    public List<SCT_old> findBySearch(SCT_old sct, Integer fuzzyInt);
    public List<SCT_old> findByTno(String tno);
    public List<SCT_old> findByTerm(String term);
    public List<SCT_old> findByStudent(SCT_old sct, Integer fuzzyInt);

    Result openCourse(Ct ct);

    Result getClassTeacherList();

    List<Ct_vo> getClassTeacherListByTno(String tno);


}
