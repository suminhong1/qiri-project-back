package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostThema is a Querydsl query type for PostThema
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostThema extends EntityPathBase<PostThema> {

    private static final long serialVersionUID = -1264450188L;

    public static final QPostThema postThema = new QPostThema("postThema");

    public final NumberPath<Integer> postThemaSeq = createNumber("postThemaSeq", Integer.class);

    public final StringPath ptName = createString("ptName");

    public QPostThema(String variable) {
        super(PostThema.class, forVariable(variable));
    }

    public QPostThema(Path<? extends PostThema> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostThema(PathMetadata metadata) {
        super(PostThema.class, metadata);
    }

}

