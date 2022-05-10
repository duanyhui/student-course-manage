package duan.server.entity;

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
 * @since 2022-05-10
 */
@Getter
@Setter
  @ApiModel(value = "Sc对象", description = "")
public class Sc implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
      private Integer scid;

      @ApiModelProperty("学生编号")
        private String sno;

      @ApiModelProperty("课程编号")
        private String cno;

      @ApiModelProperty("开课编号")
        private Integer ctid;

      @ApiModelProperty("学生成绩")
      private Float grade;


}
