package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -1459381551L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final QBoard board;

    public final QPlace place;

    public final StringPath postContent = createString("postContent");

    public final DateTimePath<java.util.Date> postDate = createDateTime("postDate", java.util.Date.class);

    public final StringPath postDelete = createString("postDelete");

    public final StringPath postNotice = createString("postNotice");

    public final NumberPath<Integer> postSEQ = createNumber("postSEQ", Integer.class);

    public final QPostThema postThema;

    public final StringPath postTitle = createString("postTitle");

    public final StringPath postUrl = createString("postUrl");

    public final NumberPath<Integer> postView = createNumber("postView", Integer.class);

    public final QUserInfo userInfo;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board")) : null;
        this.place = inits.isInitialized("place") ? new QPlace(forProperty("place"), inits.get("place")) : null;
        this.postThema = inits.isInitialized("postThema") ? new QPostThema(forProperty("postThema"), inits.get("postThema")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}

