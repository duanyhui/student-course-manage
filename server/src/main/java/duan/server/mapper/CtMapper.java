package duan.server.mapper;

import duan.server.entity.Ct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.server.entity.Ct_vo;
import duan.server.entity.SCT_old;
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

    int add(@Param("ct") Ct ct);



    List<SCT_old> getByTno(@Param("tno") String tno);

    List<SCT_old> getAll();

    boolean deleteCt(@Param("ct") Ct ct);

    List<SCT_old> findBySearch(@Param("sct") SCT_old sct, @Param("fuzzyInt") Integer fuzzyInt);

    List<SCT_old> findByTno(@Param("tno") String tno);

    List<SCT_old> findByTerm(@Param("term") String term);

    List<SCT_old> findByStudent(@Param("sct") SCT_old sct, @Param("fuzzyInt") Integer fuzzyInt);

    List<Ct_vo> getClassTeacherListByTno(String tno);

    void update_capacity(Integer capacity, Integer ctid);
}
