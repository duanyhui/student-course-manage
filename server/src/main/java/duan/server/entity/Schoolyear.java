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
 * @since 2022-10-24
 */
@Getter
@Setter
  @ApiModel(value = "Schoolyear对象", description = "")
public class Schoolyear implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("学年")
        private Integer schoolyear;


}
