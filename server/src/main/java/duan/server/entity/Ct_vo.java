package duan.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ct_vo {
    private Integer ctid;
    private String cno;
    private String tno;

    // 上课时间
    private String classtime;

    private String cname;

    private String tname;

    private Integer capacity;
}
