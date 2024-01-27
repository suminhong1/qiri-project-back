package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchingUserInfo is a Querydsl query type for MatchingUserInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchingUserInfo extends EntityPathBase<MatchingUserInfo> {

    private static final long serialVersionUID = 148695783L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchingUserInfo matchingUserInfo = new QMatchingUserInfo("matchingUserInfo");

    public final DateTimePath<java.util.Date> applicationDate = createDateTime("applicationDate", java.util.Date.class);

    public final StringPath matchingAccept = createString("matchingAccept");

    public final NumberPath<Integer> matchingUserInfoSeq = createNumber("matchingUserInfoSeq", Integer.class);

    public final QPost post;

    public final StringPath postReview = createString("postReview");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final QUserInfo userInfo;

    public QMatchingUserInfo(String variable) {
        this(MatchingUserInfo.class, forVariable(variable), INITS);
    }

    public QMatchingUserInfo(Path<? extends MatchingUserInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchingUserInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchingUserInfo(PathMetadata metadata, PathInits inits) {
        this(MatchingUserInfo.class, metadata, inits);
    }

    public QMatchingUserInfo(Class<? extends MatchingUserInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}

