package com.kh.elephant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotificationMessage is a Querydsl query type for NotificationMessage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotificationMessage extends EntityPathBase<NotificationMessage> {

    private static final long serialVersionUID = -2031323573L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotificationMessage notificationMessage = new QNotificationMessage("notificationMessage");

    public final QChatRoom chatRoom;

    public final StringPath isRead = createString("isRead");

    public final StringPath message = createString("message");

    public final NumberPath<Integer> notificationMessageSEQ = createNumber("notificationMessageSEQ", Integer.class);

    public final QPost post;

    public final DateTimePath<java.util.Date> sentTime = createDateTime("sentTime", java.util.Date.class);

    public final QUserInfo userInfo;

    public QNotificationMessage(String variable) {
        this(NotificationMessage.class, forVariable(variable), INITS);
    }

    public QNotificationMessage(Path<? extends NotificationMessage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotificationMessage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotificationMessage(PathMetadata metadata, PathInits inits) {
        this(NotificationMessage.class, metadata, inits);
    }

    public QNotificationMessage(Class<? extends NotificationMessage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoom(forProperty("chatRoom"), inits.get("chatRoom")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}

