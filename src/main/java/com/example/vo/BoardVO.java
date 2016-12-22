package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by kimyongyeon on 2016-12-23.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_board")
public class BoardVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long bno;
    @Column(name ="title", length = 256)
    private String title;
    @Column(name ="content", length = 256)
    private String content;
}
