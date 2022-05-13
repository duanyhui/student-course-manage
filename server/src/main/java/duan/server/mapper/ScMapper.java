package duan.server.mapper;

import duan.server.entity.SCT;
import duan.server.entity.Sc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-10
 */
@Mapper
public interface ScMapper extends BaseMapper<Sc> {

    boolean add(@Param("sct") SCT sct);

    boolean delete(@Param("sc") Sc sc);

    boolean updateGrade(@Param("sc") Sc sc);

    List<SCT> findBySearch(@Param("sct") SCT sct, @Param("fuzzyInt") Integer fuzzyInt);
}
