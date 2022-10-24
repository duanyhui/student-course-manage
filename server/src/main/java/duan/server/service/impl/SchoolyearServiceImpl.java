package duan.server.service.impl;

import duan.server.entity.Schoolyear;
import duan.server.mapper.SchoolyearMapper;
import duan.server.service.ISchoolyearService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-24
 */
@Service
public class SchoolyearServiceImpl extends ServiceImpl<SchoolyearMapper, Schoolyear> implements ISchoolyearService {

    @Autowired
    private SchoolyearMapper schoolyearMapper;
    @Override
    public List<Schoolyear> getSchoolyearList() {
        return schoolyearMapper.selectList(null);
    }

    @Override
    public void addSchoolyear(Integer schoolyear) {
        Schoolyear schoolyear1 = new Schoolyear();
        schoolyear1.setSchoolyear(schoolyear);
        schoolyearMapper.insert(schoolyear1);
    }
}
