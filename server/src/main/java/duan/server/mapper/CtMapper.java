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



    List<SCT> getByTno(@Param("tno") String tno);

    List<SCT> getAll();

    boolean deleteCt(@Param("ct") Ct ct);

    List<SCT> findBySearch(@Param("sct") SCT sct, @Param("fuzzyInt") Integer fuzzyInt);

    List<SCT> findByTno(@Param("tno") String tno);

    List<SCT> findByTerm(@Param("term") String term);
}
