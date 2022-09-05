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
 * @since 2022-05-04
 */
@Getter
@Setter
  @ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "sid", type = IdType.AUTO)
      private Integer sid;

      @ApiModelProperty("学号")
        private String sno;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("姓名")
      private String sname;

      @ApiModelProperty("性别")
      private String ssex;

      @ApiModelProperty("专业")
      private String major;

      @ApiModelProperty("就读学期")
      private String term;

      @ApiModelProperty("学院")
      private String college;

      private String role;


}
