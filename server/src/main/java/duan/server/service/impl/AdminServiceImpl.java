package duan.server.service.impl;

import duan.server.entity.Admin;
import duan.server.mapper.AdminMapper;
import duan.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin findByUid(Integer uid) {
        return adminMapper.findByUid(uid);
    }
}
