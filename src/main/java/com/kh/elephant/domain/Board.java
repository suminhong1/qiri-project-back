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
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "boardSequence")//generator name이랑 맞추기
    @SequenceGenerator(name = "boardSequence", sequenceName = "SEQ_BOARD", allocationSize = 1)
    private int boardSEQ;

    @Column(name = "board_name")
    private String boardName;

    @Column(name = "board_admin_only")
    private String boardAdminOnly;

}
