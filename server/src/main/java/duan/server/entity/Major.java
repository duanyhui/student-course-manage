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
 * @since 2022-10-11
 */
@Getter
@Setter
  @ApiModel(value = "Major对象", description = "")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer collegeid;

      @TableId(value = "majorid", type = IdType.AUTO)
      private Integer majorid;

      @ApiModelProperty("专业名称")
      private String majorname;


}
