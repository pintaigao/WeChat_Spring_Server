package com.imooc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/* 创建自定义的助手类 */
// SimpleChannelInboundHandler: 对于请求来讲，相当于【入站，入境】
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    // 读数据
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        // 获取channel
        Channel channel = channelHandlerContext.channel();
        // 刷到客户端
        channel.remoteAddress();
        // 定于发送的数据消息，存入缓冲区
        ByteBuf content = Unpooled.copiedBuffer("Hello netty ~", CharsetUtil.UTF_8);
        // 构建一个HttpResponse
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
        // 设置数据类型和长度
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

        // Response刷到客户端
        channelHandlerContext.writeAndFlush(response);




    }
}
