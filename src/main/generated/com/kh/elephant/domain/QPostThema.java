package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostThema is a Querydsl query type for PostThema
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostThema extends EntityPathBase<PostThema> {

    private static final long serialVersionUID = -1264450188L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostThema postThema = new QPostThema("postThema");

    public final QBoard board;

    public final NumberPath<Integer> postThemaSeq = createNumber("postThemaSeq", Integer.class);

    public final StringPath ptName = createString("ptName");

    public QPostThema(String variable) {
        this(PostThema.class, forVariable(variable), INITS);
    }

    public QPostThema(Path<? extends PostThema> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostThema(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostThema(PathMetadata metadata, PathInits inits) {
        this(PostThema.class, metadata, inits);
    }

    public QPostThema(Class<? extends PostThema> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board")) : null;
    }

}

