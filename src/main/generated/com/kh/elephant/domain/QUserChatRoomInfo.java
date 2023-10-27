package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserChatRoomInfo is a Querydsl query type for UserChatRoomInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserChatRoomInfo extends EntityPathBase<UserChatRoomInfo> {

    private static final long serialVersionUID = -1740457027L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserChatRoomInfo userChatRoomInfo = new QUserChatRoomInfo("userChatRoomInfo");

    public final QChatRoom chatRoom;

    public final DateTimePath<java.util.Date> joinDate = createDateTime("joinDate", java.util.Date.class);

    public final StringPath leave = createString("leave");

    public final NumberPath<Integer> userChatRoomInfoSeq = createNumber("userChatRoomInfoSeq", Integer.class);

    public final QUserInfo userInfo;

    public QUserChatRoomInfo(String variable) {
        this(UserChatRoomInfo.class, forVariable(variable), INITS);
    }

    public QUserChatRoomInfo(Path<? extends UserChatRoomInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserChatRoomInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserChatRoomInfo(PathMetadata metadata, PathInits inits) {
        this(UserChatRoomInfo.class, metadata, inits);
    }

    public QUserChatRoomInfo(Class<? extends UserChatRoomInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoom(forProperty("chatRoom"), inits.get("chatRoom")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}

