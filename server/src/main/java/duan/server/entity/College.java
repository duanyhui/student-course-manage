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
 * @since 2022-10-11
 */
@Getter
@Setter
@ApiModel(value = "College对象", description = "")
public class College implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer collegeid;

    private String collegename;


}


