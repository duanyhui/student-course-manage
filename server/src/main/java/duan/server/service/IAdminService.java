package duan.server.service;

import duan.server.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-04
 */
public interface IAdminService extends IService<Admin> {
    public Admin findByUid(Integer uid);

}
