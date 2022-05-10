package duan.server.mapper;

import duan.server.entity.Ct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.server.entity.SCT;
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
public interface CtMapper extends BaseMapper<Ct> {

    boolean add(@Param("ct") Ct ct);

    boolean delByCtid(@Param("ctid") Integer ctid);

    List<SCT> getByTno(@Param("tno") String tno);

    List<SCT> getAll();

}
