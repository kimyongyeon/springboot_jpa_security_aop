package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * Created by kimyongyeon on 2016-12-23.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_board")
public class BoardVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long bno;
    @Column(name ="title", length = 256)
    private String title;
    @Column(name ="content", length = 256)
    private String content;

    @OneToMany(mappedBy = "boardVO", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<BoardReplyVO> boardReplyVOs;

    @OneToMany(mappedBy = "boardFileVO", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<BoardFileVO> boardFileVOs;
}
