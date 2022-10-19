package duan.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-05-04
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
  @ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "sid", type = IdType.AUTO)
      private Integer sid;

      @ApiModelProperty("学号")
        private String sno;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("姓名")
      private String sname;

      @ApiModelProperty("性别")
      private String ssex;

      @ApiModelProperty("专业")
      private Integer majorid;

      @ApiModelProperty("就读学期")
      private Integer termid;

      @ApiModelProperty("学院id")
      private Integer collegeid;

      private String role;

      @ApiModelProperty("培养计划id")
      private Integer planid;


  public Student(Student student) {
    this.sno = student.getSno();
    this.password = student.getPassword();
    this.sname = student.getSname();
    this.ssex = student.getSsex();
    this.majorid = student.getMajorid();
    this.termid = student.getTermid();
    this.collegeid = student.getCollegeid();
    this.role = "ROLE_STUDENT";
    this.planid = student.getPlanid();
  }
}
