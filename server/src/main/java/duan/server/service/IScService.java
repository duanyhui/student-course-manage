package duan.server.service;

import duan.server.entity.SCT_old;
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
    public boolean add(SCT_old sct);
    public boolean delete(Sc sc);
    public boolean updateGrade(Sc sc);
    public List<SCT_old> findBySearch(SCT_old sct, Integer fuzzyInt);

}
