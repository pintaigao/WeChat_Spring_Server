package com.imooc.netty;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class WSServer {

    private static class SimulationWSServer {
        static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance(){
        return SimulationWSServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture future;

    public WSServer(){
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup).channel(NioServerSocketChannel.class).childHandler(new WSServerInitializer());
    }

    public void start(){
        this.future = serverBootstrap.bind(8088);
        System.err.println("netty websocket server 启动完毕");
    }

    /* 不再需要shutdownGracefully了，因为已经全盘依托给了Spring Boot容器 */

    /*public static void main(String[] args) throws InterruptedException {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(mainGroup, subGroup).channel(NioServerSocketChannel.class).childHandler(null);

            ChannelFuture future = serverBootstrap.bind(8088).sync();

            future.channel().closeFuture().sync();
        } finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }*/
}
