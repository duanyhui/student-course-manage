package duan.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanTable_vo {


    private Integer planid;
    private Integer cid;
    private String cno;
    private String cname;
    private Integer ccredit;
    private String type;


}
