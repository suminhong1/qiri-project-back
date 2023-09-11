package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PostThema {

    @Id
    @Column(name = "postThemaSeq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "postThemaSeq")
    @SequenceGenerator(name="postThemaSeq", sequenceName = "POST_THEMA_SEQ", allocationSize = 1)
    private int postThemaSeq;

    @Column(name="ptName")
    private String ptName;

}
