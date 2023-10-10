package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockUsers is a Querydsl query type for BlockUsers
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockUsers extends EntityPathBase<BlockUsers> {

    private static final long serialVersionUID = -902266132L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockUsers blockUsers = new QBlockUsers("blockUsers");

    public final DateTimePath<java.util.Date> blockDate = createDateTime("blockDate", java.util.Date.class);

    public final QUserInfo blockInfo;

    public final StringPath blockReason = createString("blockReason");

    public final NumberPath<Integer> blockUserSeq = createNumber("blockUserSeq", Integer.class);

    public final StringPath unblock = createString("unblock");

    public final QUserInfo userInfo;

    public QBlockUsers(String variable) {
        this(BlockUsers.class, forVariable(variable), INITS);
    }

    public QBlockUsers(Path<? extends BlockUsers> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockUsers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockUsers(PathMetadata metadata, PathInits inits) {
        this(BlockUsers.class, metadata, inits);
    }

    public QBlockUsers(Class<? extends BlockUsers> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockInfo = inits.isInitialized("blockInfo") ? new QUserInfo(forProperty("blockInfo"), inits.get("blockInfo")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}

