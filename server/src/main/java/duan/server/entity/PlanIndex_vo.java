package duan.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanIndex_vo {
    private Integer planid;

    private Integer majorid;

    private Integer collegeid;

    private Integer termid;

    private String majorname;

    private String collegename;

    private String termname;

}
