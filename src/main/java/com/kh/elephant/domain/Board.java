package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @Column(name="board_seq")
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "boardSeq")//generator name이랑 맞추기
    @SequenceGenerator(name = "boardSeq", sequenceName = "BOARD_SEQ", allocationSize = 1)
    private int boardSeq;

    @Column(name = "board_name")
    private String boardName;

    @Column(name = "board_manager")
    private String boardManager;

}
