package duan.server.mapper;

import duan.server.entity.Sct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-20
 */
@Mapper
public interface SctMapper extends BaseMapper<Sct> {

    int insert(Integer ctid, String sno);

    Float get_grade(Integer ctid, String sno);

    void delete(Integer ctid, String sno);
}
