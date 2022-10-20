package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.Ct;
import duan.server.entity.Ct_vo;
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

    @Override
    public int add(Ct ct)  {
        LambdaQueryWrapper<Ct> queryWrapper = new LambdaQueryWrapper<Ct>();
        queryWrapper.eq(Ct::getTno,ct.getTno());
        return ctMapper.add(ct);

    }

    @Override
    public int delByCt(Ct ct) {
        LambdaQueryWrapper<Ct> queryWrapper = new LambdaQueryWrapper<Ct>();
        queryWrapper.eq(Ct::getTno,ct.getTno());
        queryWrapper.eq(Ct::getCno,ct.getCno());
        return ctMapper.delete(queryWrapper);

    }

    @Override
    public List<SCT> getByTno(String tno) {
        return ctMapper.getByTno(tno);
    }

    @Override
    public List<SCT> getAll() {
        return ctMapper.getAll();
    }

    @Override
    public List<SCT> findBySearch(SCT sct, Integer fuzzyInt) {
        fuzzyInt = (fuzzyInt == 1) ? 1 : 0;
        System.out.println("模糊查询标志位：" + fuzzyInt);
        return ctMapper.findBySearch(sct, fuzzyInt);
    }

    @Override
    public List<SCT> findByTno(String tno) {
        return ctMapper.findByTno(tno);
    }

    @Override
    public List<SCT> findByTerm(String term) {
        return ctMapper.findByTerm(term);
    }

    @Override
    public List<SCT> findByStudent(SCT sct, Integer fuzzyInt) {
        return ctMapper.findByStudent(sct,fuzzyInt);
    }

    /**
     * 老师开课
     * @param ct
     * @return msg
     */
    @Override
    public Result openCourse(Ct ct) {
        LambdaQueryWrapper<Ct> queryWrapper = new LambdaQueryWrapper<Ct>();
        if (ctMapper.insert(ct)==1){
            return Result.succ("开课成功");
        }
        return Result.fail("开课失败,请检查是否重复开课（同一时间只能开一门课）");
    }

    /**
     * -----------------------------------------------------------------------------------------------------------
     * 2022-10-13
     * @return
     */
    @Override
    public Result getClassTeacherList() {
        return Result.succ(ctMapper.selectList(null));
    }

    @Override
    public List<Ct_vo> getClassTeacherListByTno(String tno) {
        return ctMapper.getClassTeacherListByTno(tno);
    }


}
