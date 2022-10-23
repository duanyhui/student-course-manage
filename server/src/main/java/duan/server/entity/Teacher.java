package duan.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author duanyhui
 * @since 2022-05-05
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
  @ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "tid", type = IdType.AUTO)
      private Integer tid;

      @ApiModelProperty("老师编号")
      private String tno;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("姓名")
      private String tname;

      @ApiModelProperty("性别")
      private String tsex;

      @ApiModelProperty("学院id")
      private Integer collegeid;

      private String role;

      private String education;

      private Integer age;


  public Teacher(Teacher teacher) {
    this.tno = teacher.getTno();
    this.password = teacher.getPassword();
    this.tname = teacher.getTname();
    this.tsex = teacher.getTsex();
    this.collegeid = teacher.getCollegeid();
    this.education = teacher.getEducation();
    this.role = "ROLE_TEACHER";
    this.age = teacher.getAge();
  }
}
