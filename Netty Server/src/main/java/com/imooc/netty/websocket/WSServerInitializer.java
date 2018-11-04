package com.imooc.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {

        // 获取pipline
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        // 添加助手类 webSocket 基于http协议，所以要有http的编解码器
        channelPipeline.addLast(new HttpServerCodec());

        // 对写大数据流的支持
        channelPipeline.addLast(new ChunkedWriteHandler());

        // 对Http Message 进行聚合，聚合成FullHttpRequest 或 FullHttpResponse, 几乎在netty中的编程，都会是有到这个handler
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 64));

        // ======================= 以上是用于支持Http协议 ======================= //

        /*
         websocket 服务器处理的协议，用于指定
         本handler 会帮助处理一些繁重的重复的事
         会帮助处理握手动作: handshaking(close,ping,pong) ping + pong = 心跳
         对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
        */
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        /* 自定义的Handler */
        channelPipeline.addLast(new ChatHandler());
    }
}
