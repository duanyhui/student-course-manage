package duan.server.mapper;

import duan.server.entity.PlanIndex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.server.entity.PlanIndex_vo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-12
 */
@Mapper
public interface PlanIndexMapper extends BaseMapper<PlanIndex> {

    List<PlanIndex_vo> getPlanIndexList();
}
