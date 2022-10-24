package duan.server.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student_vo  {
    private Integer sid;

    @ApiModelProperty("学号")
    private String sno;

    @ApiModelProperty("姓名")
    private String sname;

    @ApiModelProperty("性别")
    private String ssex;

    @ApiModelProperty("专业")
    private String majorname;

    @ApiModelProperty("就读学期")
    private String termname;

    @ApiModelProperty("学院")
    private String collegename;

    private String role;

    @ApiModelProperty("培养计划id")
    private Integer planid;

    @ApiModelProperty("学年")
    private Integer schoolyear;

    @ApiModelProperty("班级id")
    private Integer classid;

    @ApiModelProperty("在班编号")
    private Integer onclassid;
}
