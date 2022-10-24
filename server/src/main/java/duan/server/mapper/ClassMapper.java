package duan.server.mapper;

import duan.server.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.server.entity.Class_vo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-23
 */
@Mapper
public interface ClassMapper extends BaseMapper<Class> {

    void addClass(Integer collegeid, Integer majorid, Integer classid, Integer schoolyear);

    List<Class_vo> getClassList(Integer collegeid, Integer majorid, Integer schoolyear);

    List<Class_vo> getClassList();

    List<Class_vo> getClassList_all();

    List<Class_vo> getClassList_by_schoolyear(Integer schoolyear);

    Integer getOnclassid(Integer schoolyear, Integer collegeid, Integer majorid, Integer classid);

    void updateOnclassid(Integer schoolyear, Integer collegeid, Integer majorid, Integer classid, Integer onclassid);
}
