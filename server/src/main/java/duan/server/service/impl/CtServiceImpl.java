package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.Ct;
import duan.server.entity.Ct_vo;
import duan.server.entity.SCT_old;
import duan.server.entity.Sct;
import duan.server.mapper.CtMapper;
import duan.server.mapper.SctMapper;
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
    public List<SCT_old> getByTno(String tno) {
        return ctMapper.getByTno(tno);
    }

    @Override
    public List<SCT_old> getAll() {
        return ctMapper.getAll();
    }

    @Override
    public List<SCT_old> findBySearch(SCT_old sct, Integer fuzzyInt) {
        fuzzyInt = (fuzzyInt == 1) ? 1 : 0;
        System.out.println("模糊查询标志位：" + fuzzyInt);
        return ctMapper.findBySearch(sct, fuzzyInt);
    }

    @Override
    public List<SCT_old> findByTno(String tno) {
        return ctMapper.findByTno(tno);
    }

    @Override
    public List<SCT_old> findByTerm(String term) {
        return ctMapper.findByTerm(term);
    }

    @Override
    public List<SCT_old> findByStudent(SCT_old sct, Integer fuzzyInt) {
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



    @Autowired
    private SctMapper sctMapper;
    @Override
    public Result updateScore(Sct sct) {
        try {
            LambdaQueryWrapper<Sct> queryWrapper = new LambdaQueryWrapper<Sct>();
            queryWrapper.eq(Sct::getSno,sct.getSno());
            queryWrapper.eq(Sct::getCtid,sct.getCtid());
            if (sctMapper.update(sct,queryWrapper)==1){
                return Result.succ("修改成功");
            }
            else {
                return Result.fail("修改失败");
            }
        }
        catch (Exception e){
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public List<Ct_vo> getStudentClassList(String tno, String cname, String sname) {
        if (cname == null) cname = "";
        if (sname == null) sname = "";
        return ctMapper.getStudentClassList(tno,cname,sname);
    }

    @Override
    public List<Ct_vo> getStudentClassList_Admin(String sname, String cname, String tname) {
        if (sname == null) sname = "";
        if (cname == null) cname = "";
        if (tname == null) tname = "";
        return ctMapper.getStudentClassList_Admin(sname,cname,tname);
    }


}
