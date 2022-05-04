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
 * @since 2022-05-04
 */
@Getter
@Setter
  @ApiModel(value = "Admin对象", description = "")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("管理员")
        private Integer uid;

    private String name;

    private String password;


}
