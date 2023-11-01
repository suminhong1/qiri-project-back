package com.kh.elephant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostAttachmentsDTO {
    private int postAttachmentSEQ;
    private int post;
    private String attachmentURL;
}
