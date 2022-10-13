package duan.server.service;

import duan.server.commom.lang.Result;
import duan.server.entity.ClassTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-13
 */
public interface IClassTimeService extends IService<ClassTime> {

    Result getClassTimeById(Integer classtimeid);

    Result getClassTimeList();
}
