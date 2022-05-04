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
  @ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "tid", type = IdType.AUTO)
      private Integer tid;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("姓名")
      private String tname;

      @ApiModelProperty("性别")
      private String tsex;

      @ApiModelProperty("学院")
      private String college;


}
