package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kimyongyeon on 2016-12-23.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardFileVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int bno;
    @Column(name ="file_size")
    private long fileSize;
    @Column(name ="org_file_name", length = 256)
    private String orgFileName;
    @Column(name ="temp_file_name", length = 256)
    private String tempFileName;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardFileVO boardFileVO;

}
