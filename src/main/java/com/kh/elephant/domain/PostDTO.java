package com.kh.elephant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private int Seq;

    private String Title;

    private String Content;

    private Date Date;

    private int View;

    private String Url;

    private UserInfo userInfo;

    private Place placeSeq;

    private PostThema postThemaSeq;

    private Board board;

    private String postNotice;

    private String Delete;

    public Post ToPost(){
        Post post = new Post();

        post.setPostSEQ(this.Seq);

        post.setPostTitle(this.Title);

        post.setPostContent(this.Content);

        post.setPostDate(this.Date);

        post.setPostView(this.View);

        post.setPostUrl(this.Url);

        post.setUserInfo(this.userInfo);

        post.setPlaceSeq(this.placeSeq);

        post.setPostThemaSeq(this.postThemaSeq);

        post.setBoard(this.board);

        post.setPostNotice(this.postNotice);

        post.setPostDelete(this.Delete);

        return post;
    }
}
