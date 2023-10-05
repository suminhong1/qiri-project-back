package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamicInsert
@Entity
public class PostAttachments {

    @Id
    @Column(name = "post_attachment_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "postAttachmentSequence")
    @SequenceGenerator(name="postAttachmentSequence", sequenceName = "SEQ_POST_ATTACHMENT",allocationSize = 1)
    private int postAttachmentSEQ;


    @ManyToOne
    @JoinColumn(name = "post_seq")
    private Post post;

    @Column(name = "attachment_url")
    private String attachmentURL;
}
