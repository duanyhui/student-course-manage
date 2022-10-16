package duan.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-10-12
 */
@Getter
@Setter
  @TableName("plan_index")
@ApiModel(value = "PlanIndex对象", description = "")
public class PlanIndex implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("培养计划id")
      private Integer planid;

      private Integer majorid;

      private Integer collegeid;

      private Integer termid;

  @Override
  public String toString() {
    return "PlanIndex{" +
        "planid=" + planid +
        ", majorid=" + majorid +
        ", collegeid=" + collegeid +
        ", termid=" + termid +
    "}";
  }
}
