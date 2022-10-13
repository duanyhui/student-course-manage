package duan.server.service;

import duan.server.commom.lang.Result;
import duan.server.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-11
 */
public interface IMajorService extends IService<Major> {

    Result getMajor(Integer collegeid) throws Exception;

    Result getMajorList();

    Result getMajor(Integer collegeid, Integer majorid);
}
