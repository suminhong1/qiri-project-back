package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBanInfo is a Querydsl query type for BanInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBanInfo extends EntityPathBase<BanInfo> {

    private static final long serialVersionUID = 1571404172L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBanInfo banInfo = new QBanInfo("banInfo");

    public final DateTimePath<java.util.Date> banEnd = createDateTime("banEnd", java.util.Date.class);

    public final NumberPath<Integer> banInfoSeq = createNumber("banInfoSeq", Integer.class);

    public final StringPath banReason = createString("banReason");

    public final DateTimePath<java.util.Date> banStart = createDateTime("banStart", java.util.Date.class);

    public final QUserInfo userInfo;

    public QBanInfo(String variable) {
        this(BanInfo.class, forVariable(variable), INITS);
    }

    public QBanInfo(Path<? extends BanInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBanInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBanInfo(PathMetadata metadata, PathInits inits) {
        this(BanInfo.class, metadata, inits);
    }

    public QBanInfo(Class<? extends BanInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}

