package duan.server.service;

import duan.server.entity.SCT;
import duan.server.entity.Sc;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-10
 */
public interface IScService extends IService<Sc> {
    public boolean add(SCT sct);
    public boolean delete(Sc sc);
    public boolean updateGrade(Sc sc);
    public List<SCT> findBySearch(SCT sct, Integer fuzzyInt);

}
