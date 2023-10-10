package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLike is a Querydsl query type for UserLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLike extends EntityPathBase<UserLike> {

    private static final long serialVersionUID = -1203063885L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLike userLike = new QUserLike("userLike");

    public final DateTimePath<java.util.Date> likeUpDate = createDateTime("likeUpDate", java.util.Date.class);

    public final NumberPath<Integer> likeUpSeq = createNumber("likeUpSeq", Integer.class);

    public final QUserInfo likeUpTarget;

    public final QUserInfo likeUpUser;

    public QUserLike(String variable) {
        this(UserLike.class, forVariable(variable), INITS);
    }

    public QUserLike(Path<? extends UserLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLike(PathMetadata metadata, PathInits inits) {
        this(UserLike.class, metadata, inits);
    }

    public QUserLike(Class<? extends UserLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.likeUpTarget = inits.isInitialized("likeUpTarget") ? new QUserInfo(forProperty("likeUpTarget"), inits.get("likeUpTarget")) : null;
        this.likeUpUser = inits.isInitialized("likeUpUser") ? new QUserInfo(forProperty("likeUpUser"), inits.get("likeUpUser")) : null;
    }

}

