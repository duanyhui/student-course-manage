package duan.server.controller;

import duan.server.commom.lang.Result;
import duan.server.mapper.MajorMapper;
import duan.server.service.impl.CollegeServiceImpl;
import duan.server.service.impl.MajorServiceImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 *  多表查询的结果返回
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-11
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/utils")
@Slf4j
public class utils {

    @Autowired
    private CollegeServiceImpl collegeService;
    @Autowired
    private MajorServiceImpl majorService;

    @GetMapping("/getcollege/{collegeid}")
    public Result getCollege (@PathVariable("collegeid") Integer collegeid) throws Exception {
        return collegeService.getCollege(collegeid);
    }

    @GetMapping("/getcollegelist")
    public Result getCollegeList () throws Exception {
        return collegeService.getCollegeList();
    }

    @GetMapping("/getmajor/{collegeid}")
    public Result getMajor(@PathVariable("collegeid") Integer collegeid) throws Exception{
        return majorService.getMajor(collegeid);
    }

    @GetMapping("/getmajorlist")
    public Result getMajorList() throws Exception{
        return majorService.getMajorList();
    }
}
