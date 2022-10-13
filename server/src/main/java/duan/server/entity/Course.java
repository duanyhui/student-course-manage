package duan.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-05
 */
@Getter
@Setter
  @ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "cid", type = IdType.AUTO)
      private Integer cid;

      @ApiModelProperty("课程编号")
      private String cno;

      @ApiModelProperty("课程名称")
      private String cname;

      @ApiModelProperty("课程学分")
      private Integer ccredit;

      private String type;


}
