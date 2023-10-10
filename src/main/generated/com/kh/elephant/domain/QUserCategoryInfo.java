package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCategoryInfo is a Querydsl query type for UserCategoryInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCategoryInfo extends EntityPathBase<UserCategoryInfo> {

    private static final long serialVersionUID = -2102194456L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCategoryInfo userCategoryInfo = new QUserCategoryInfo("userCategoryInfo");

    public final QCategory category;

    public final NumberPath<Integer> userCategorySeq = createNumber("userCategorySeq", Integer.class);

    public final QUserInfo userInfo;

    public QUserCategoryInfo(String variable) {
        this(UserCategoryInfo.class, forVariable(variable), INITS);
    }

    public QUserCategoryInfo(Path<? extends UserCategoryInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCategoryInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCategoryInfo(PathMetadata metadata, PathInits inits) {
        this(UserCategoryInfo.class, metadata, inits);
    }

    public QUserCategoryInfo(Class<? extends UserCategoryInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category"), inits.get("category")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}

