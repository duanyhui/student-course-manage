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
 * @since 2022-10-13
 */
@Getter
@Setter
  @TableName("class_time")
@ApiModel(value = "ClassTime对象", description = "")
public class ClassTime implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer classtimeid;

      private String classtime;


}
