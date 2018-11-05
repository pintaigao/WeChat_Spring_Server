package com.imooc.netty;

import java.time.LocalDateTime;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;


/* 处理消息的handler
 * TextWebSocketFrame: 在netty中，用于为websocket专门处理文本的对象，frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /* 记录和管理所有客户端的channels，把所有的channel都保存到所有的client，固定的写法*/
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 获取客户端传输过来的消息
        String content = textWebSocketFrame.text();
        System.out.println("接受到的数据：" + content);
        for (Channel channel : clients) {
            channel.writeAndFlush(new TextWebSocketFrame("[服务器在]" + LocalDateTime.now() + ",接收到消息，消息为：" + content));
        }
        // 下面这个方法和上面的for循环一致
        // clients.writeAndFlush(new TextWebSocketFrame("[服务器在]" + LocalDateTime.now() + ",接收到消息，消息为：" + content));

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        /* 当客户端连接服务端之后（打开链接），获取客户端的channel，并且放到ChannelGroup中去进行管理 */
        clients.add(ctx.channel());
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        clients.remove(ctx.channel());
        System.out.println("客户端断开，channel对应的长ID 和 短ID");
        System.out.println(ctx.channel().id().asLongText());
        System.out.println(ctx.channel().id().asShortText());

    }
}
