package duan.server.mapper;

import duan.server.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.server.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-04
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    public Admin findByUid(@Param("uid") Integer uid);

    public Student findStu(@Param("name") String name);
}
