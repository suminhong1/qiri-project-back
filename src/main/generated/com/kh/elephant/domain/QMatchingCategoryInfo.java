package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchingCategoryInfo is a Querydsl query type for MatchingCategoryInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchingCategoryInfo extends EntityPathBase<MatchingCategoryInfo> {

    private static final long serialVersionUID = 1707365274L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchingCategoryInfo matchingCategoryInfo = new QMatchingCategoryInfo("matchingCategoryInfo");

    public final QCategory Category;

    public final NumberPath<Integer> matchingCategorySEQ = createNumber("matchingCategorySEQ", Integer.class);

    public final QPost post;

    public QMatchingCategoryInfo(String variable) {
        this(MatchingCategoryInfo.class, forVariable(variable), INITS);
    }

    public QMatchingCategoryInfo(Path<? extends MatchingCategoryInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchingCategoryInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchingCategoryInfo(PathMetadata metadata, PathInits inits) {
        this(MatchingCategoryInfo.class, metadata, inits);
    }

    public QMatchingCategoryInfo(Class<? extends MatchingCategoryInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.Category = inits.isInitialized("Category") ? new QCategory(forProperty("Category"), inits.get("Category")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

