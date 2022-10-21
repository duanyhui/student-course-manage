package duan.server.entity;

import lombok.Data;

import java.io.Serializable;

@Data

public class Sct implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer scid;

        private String sno;

        private Integer ctid;

        private float grade;

}
