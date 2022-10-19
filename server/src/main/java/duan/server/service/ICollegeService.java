package duan.server.service;

import duan.server.commom.lang.Result;
import duan.server.entity.College;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-11
 */
public interface ICollegeService extends IService<College> {

    Result getCollege(Integer collegeid) throws Exception;

    Result getCollegeList();

    Result createCollege(String college_name);
}
