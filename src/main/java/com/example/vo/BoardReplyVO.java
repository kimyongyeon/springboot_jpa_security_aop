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
@Table(name = "tbl_board_reply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardReplyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int bno;

    @Column(name ="title", length = 256)
    private String title;

    @Column(name ="content", length = 256)
    private String content;

    @Column(name ="viewCount")
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardVO boardVO;




}
