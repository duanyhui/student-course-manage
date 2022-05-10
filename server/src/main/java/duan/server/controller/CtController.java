package duan.server.controller;


import duan.server.commom.lang.Result;
import duan.server.entity.Ct;
import duan.server.service.impl.CtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

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
}

