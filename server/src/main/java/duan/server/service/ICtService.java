package duan.server.service;

import duan.server.entity.Ct;
import com.baomidou.mybatisplus.extension.service.IService;
import duan.server.entity.SCT;

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
    public boolean add(Ct ct);
    public boolean delByCt(Ct ct);
    public List<SCT> getByTno(String tno);
    public List<SCT> getAll();
    public List<SCT> findBySearch(SCT sct, Integer fuzzyInt);
    public List<SCT> findByTno(String tno);
    public List<SCT> findByTerm(String term);
    public List<SCT> findByStudent(SCT sct, Integer fuzzyInt);

}
