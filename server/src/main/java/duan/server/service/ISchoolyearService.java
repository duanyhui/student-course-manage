package duan.server.service;

import duan.server.entity.Schoolyear;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-24
 */
public interface ISchoolyearService extends IService<Schoolyear> {

    List<Schoolyear> getSchoolyearList();

    void addSchoolyear(Integer schoolyear);
}
