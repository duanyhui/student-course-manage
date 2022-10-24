package duan.server.service;

import duan.server.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import duan.server.entity.Class_vo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-23
 */
public interface IClassService extends IService<Class> {

    void addClass(Integer collegeid, Integer majorid, Integer classid,Integer schoolyear );

    List<Class_vo> getClassList(Integer collegeid, Integer majorid, Integer schoolyear);

    void deleteClass(Integer collegeid, Integer majorid, Integer classid,Integer schoolyear);

    List<Class_vo> getClassList();

    List<Class_vo> getClassList(Integer schoolyear);

    Integer createOnclassid(Integer schoolyear, Integer collegeid, Integer majorid, Integer classid);
}
