package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Ct;
import duan.server.entity.SCT;
import duan.server.service.impl.CtServiceImpl;
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
@RequestMapping("/ct")
public class CtController {

    @Autowired private CtServiceImpl ctService;

    @PostMapping("/add")
    public Result add(@RequestBody Ct ct) {
        try {
            if (ctService.add(ct)) {
            return Result.succ("添加成功");}
            else {
                return Result.fail("添加失败");
            }
        }
        catch (DataAccessException e) {
            return Result.fail("添加失败,请检查数据是否合法，或者是否已经存在");
        }
    }

    @PostMapping("/delete")
    public Result delByCt(@RequestBody Ct ct) {
        try {
            if (ctService.delByCt(ct)) {
                return Result.succ("删除成功");
            } else {
                return Result.fail("删除失败,不存在该开课信息");
            }
        } catch (DataAccessException e) {
            return Result.fail("删除失败,存在外键依赖，请检查学生是否已经选择课程");
        }
    }

    /**
     * 根据老师tno查询开课信息
     * @param ct
     * @return List<SCT>
     */
    @GetMapping("/getByTno")
    public Result getByTid(@RequestBody Ct ct) {
        try {
            System.out.println(ct.getTno());
            return Result.succ(ctService.getByTno(ct.getTno()));

        } catch (DataAccessException e) {
            return Result.fail("查询失败");
        }
    }

    /**
     * 查询所有开课信息
     */
    @GetMapping("/getAll")
    public Result getAll() {
        try {
            return Result.succ(ctService.getAll());
        } catch (DataAccessException e) {
            return Result.fail("查询失败");
        }
    }


    /**
     * 模糊查询开课信息
     */
    @PostMapping("/findBySearch")
    public Result findBySearch(@RequestBody SCT sct) {
        try {
            Integer fuzzyInt = (Objects.equals(sct.getFuzzy(), "true")) ? 1 : 0;
            /**
             * fuzzy为模糊查询标志位，当传入的fuzzy为'fuzzy'时，模糊查询
             */
            System.out.println("传入数据"+sct);
            System.out.println();
            return Result.succ(ctService.findBySearch(sct,fuzzyInt));
        } catch (DataAccessException e) {
            return Result.fail("查询失败");
        }
    }


}

