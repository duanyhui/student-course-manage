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
 * @since 2022-10-23
 */
@Getter
@Setter
  @ApiModel(value = "Class对象", description = "")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer collegeid;

    private Integer majorid;

      @ApiModelProperty("班级编号")
      private Integer classid;
    @ApiModelProperty("学年")
      private Integer schoolyear;


}
