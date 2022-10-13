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
  @ApiModel(value = "Ct对象", description = "")
public class Ct implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ctid;

      @ApiModelProperty("课程编号")
        private String cno;

      @ApiModelProperty("教师编号")
        private String tno;

      @ApiModelProperty("开课学期")
        private Integer termid;

        @ApiModelProperty("开课时间")
        private Integer classtimeid;

      @ApiModelProperty("开课容量")
      private Integer capacity;


}
