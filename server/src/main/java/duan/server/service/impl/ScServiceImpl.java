package duan.server.service.impl;

import duan.server.entity.SCT;
import duan.server.entity.Sc;
import duan.server.mapper.ScMapper;
import duan.server.service.IScService;
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
 * @since 2022-05-10
 */
@Service
public class ScServiceImpl extends ServiceImpl<ScMapper, Sc> implements IScService {

    @Autowired
    private ScMapper scMapper;

    public boolean add(SCT sct) {
        return scMapper.add(sct);
    }

    public boolean delete(Sc sc) {
        return scMapper.delete(sc);
    }

    public boolean updateGrade(Sc sc) {
        return scMapper.updateGrade(sc);
    }

    public List<SCT> findBySearch(SCT sct, Integer fuzzyInt) {
        fuzzyInt = (fuzzyInt == 1) ? 1 : 0;
        System.out.println("模糊查询标志位：" + fuzzyInt);
        return scMapper.findBySearch(sct, fuzzyInt);
    }
}
