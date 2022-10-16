package duan.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
  @TableName("plan_table")
@ApiModel(value = "PlanTable对象", description = "")
public class PlanTable implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("计划索引id")
      private Integer planid;

      @ApiModelProperty("课程id")
      private Integer cid;

  @Override
  public String toString() {
    return "PlanTable{" +
        "id=" + id +
        ", planid=" + planid +
        ", cid=" + cid +
    "}";
  }
}
