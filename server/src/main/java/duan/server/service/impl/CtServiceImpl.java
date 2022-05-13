package duan.server.service.impl;

import duan.server.entity.Ct;
import duan.server.entity.SCT;
import duan.server.mapper.CtMapper;
import duan.server.service.ICtService;
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
public class CtServiceImpl extends ServiceImpl<CtMapper, Ct> implements ICtService {

    @Autowired
    private CtMapper ctMapper;

    public boolean add(Ct ct)  {return ctMapper.add(ct);}


    public boolean delByCt(Ct ct) {
        return ctMapper.deleteCt(ct);
    }

    public List<SCT> getByTno(String tno) {
        return ctMapper.getByTno(tno);
    }

    public List<SCT> getAll() {
        return ctMapper.getAll();
    }

    public List<SCT> findBySearch(SCT sct, Integer fuzzyInt) {
        fuzzyInt = (fuzzyInt == 1) ? 1 : 0;
        System.out.println("模糊查询标志位：" + fuzzyInt);
        return ctMapper.findBySearch(sct, fuzzyInt);
    }


    public List<SCT> findByTno(String tno) {
        return ctMapper.findByTno(tno);
    }

    public List<SCT> findByTerm(String term) {
        return ctMapper.findByTerm(term);
    }
}
