package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.SCT;
import duan.server.entity.Sc;
import duan.server.service.impl.ScServiceImpl;
import duan.server.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-10
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/sc")
public class ScController {

    @Autowired
    private ScServiceImpl scService;
    @Autowired
    private StudentServiceImpl studentService;

    //todo:学生选课学期id变String传给前端
    @PostMapping("/add")
    public Result add(@RequestBody SCT sct) {
        try {
            Integer ttermid =studentService.findBySno(sct.getSno()).getTermid();
            Integer s=sct.getTermid();
            //  一定要用equals，不能用==
            if (Objects.equals(sct.getTermid(), studentService.findBySno(sct.getSno()).getTermid()))  //校验前端数据，判断学期是否一致
            {
                if (scService.add(sct)) {
                    return Result.succ("添加成功");
                } else {
                    return Result.fail("添加失败,请勿重复选课");
                }
            }
            else {
                return Result.fail("添加失败,学期不符");
            }
        }
        catch (DataAccessException e) {
            return Result.fail("请勿重复选课");
        }
    }

    @PostMapping("/delete")
    public Result delById(@RequestBody Sc sc) {
        try {
//            boolean flag= sc.getGrade()!=null;
            if(sc.getGrade()==null)
            {
                if (scService.delete(sc)) {
                    return Result.succ("删除成功");
                }
                else {
                    return Result.fail("删除失败，没有该选课信息");
                }
            }

            else
            {
                return Result.fail("删除失败，已有成绩，无法删除");
            }
        }
        catch (DataAccessException e) {
            return Result.fail("删除失败,数据库异常");
        }


    }

    /**
     * 根据学号和ctid修改成绩
     *
     *
     */
    @PostMapping("/updateGrade")
    public Result updateGrade(@RequestBody Sc sc) {
        try {
            if (sc.getGrade() >= 0 && sc.getGrade() <= 200) {
                if (scService.updateGrade(sc)) {
                    return Result.succ("修改成功");
                } else {
                    return Result.fail("修改失败，没有该选课信息");
                }
            }
            else {
                return Result.fail("修改失败，成绩超出范围");
            }
        }
        catch (DataAccessException e) {
            return Result.fail("修改失败,没有该选课信息");
        }
    }


    /**
     * 模糊查询
     *
     *
     */
    @PostMapping("/findBySearch")
    public Result findBySearch(@RequestBody SCT sct) {
        try {
            Integer fuzzyInt = (Objects.equals(sct.getFuzzy(), "true")) ? 1 : 0;

            return Result.succ(scService.findBySearch(sct, fuzzyInt));
        }
        catch (DataAccessException e) {
            return Result.fail("查询失败,没有该选课信息");
        }
    }

}

