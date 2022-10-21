package duan.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Ct_vo implements Serializable {
    private Integer ctid;
    private String cno;
    private String tno;
    private String sno;

    // 上课时间
    private String classtime;

    private String cname;
    private String sname;

    private String tname;
    private Integer ccredit;

    private Integer capacity;
    private Integer capacityable;
    private String type;

    private float grade;


}
