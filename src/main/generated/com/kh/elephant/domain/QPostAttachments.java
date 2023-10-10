package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostAttachments is a Querydsl query type for PostAttachments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostAttachments extends EntityPathBase<PostAttachments> {

    private static final long serialVersionUID = 66952223L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostAttachments postAttachments = new QPostAttachments("postAttachments");

    public final StringPath attachmentURL = createString("attachmentURL");

    public final QPost post;

    public final NumberPath<Integer> postAttachmentSEQ = createNumber("postAttachmentSEQ", Integer.class);

    public QPostAttachments(String variable) {
        this(PostAttachments.class, forVariable(variable), INITS);
    }

    public QPostAttachments(Path<? extends PostAttachments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostAttachments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostAttachments(PathMetadata metadata, PathInits inits) {
        this(PostAttachments.class, metadata, inits);
    }

    public QPostAttachments(Class<? extends PostAttachments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

