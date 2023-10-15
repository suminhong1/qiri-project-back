package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryType is a Querydsl query type for CategoryType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryType extends EntityPathBase<CategoryType> {

    private static final long serialVersionUID = 1831752329L;

    public static final QCategoryType categoryType = new QCategoryType("categoryType");

    public final StringPath ctName = createString("ctName");

    public final NumberPath<Integer> ctSEQ = createNumber("ctSEQ", Integer.class);

    public QCategoryType(String variable) {
        super(CategoryType.class, forVariable(variable));
    }

    public QCategoryType(Path<? extends CategoryType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryType(PathMetadata metadata) {
        super(CategoryType.class, metadata);
    }

}

