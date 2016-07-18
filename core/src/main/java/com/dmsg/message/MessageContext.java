package com.dmsg.message;

import com.dmsg.message.vo.MessageBase;
import com.dmsg.message.vo.MessageType;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by cjl on 2016/7/12.
 */
public class MessageContext {
    private ChannelHandlerContext channelHandlerContext;

    private MessageBase source;

    private MessageBase message;
    private MessageType messageType;

    public MessageContext(ChannelHandlerContext channelHandlerContext, MessageBase message) {
        this.channelHandlerContext = channelHandlerContext;
        this.source = message;
    }

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

    public MessageBase getMessage() {

        return message;
    }

    public void setMessage(MessageBase message) {
        this.message = message;
    }

    public MessageBase getSource() {
        return source;
    }

    public void setSource(MessageBase source) {
        this.source = source;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}