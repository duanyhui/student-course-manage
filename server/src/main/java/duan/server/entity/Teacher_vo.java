package duan.server.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher_vo {
    @ApiModelProperty("老师编号")
    private String tno;

    @ApiModelProperty("姓名")
    private String tname;

    @ApiModelProperty("性别")
    private String tsex;

    @ApiModelProperty("学院")
    private String collegename;
}
