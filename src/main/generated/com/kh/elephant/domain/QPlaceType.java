package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlaceType is a Querydsl query type for PlaceType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceType extends EntityPathBase<PlaceType> {

    private static final long serialVersionUID = -1464683184L;

    public static final QPlaceType placeType = new QPlaceType("placeType");

    public final StringPath placeTypeName = createString("placeTypeName");

    public final NumberPath<Integer> placeTypeSeq = createNumber("placeTypeSeq", Integer.class);

    public QPlaceType(String variable) {
        super(PlaceType.class, forVariable(variable));
    }

    public QPlaceType(Path<? extends PlaceType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlaceType(PathMetadata metadata) {
        super(PlaceType.class, metadata);
    }

}

