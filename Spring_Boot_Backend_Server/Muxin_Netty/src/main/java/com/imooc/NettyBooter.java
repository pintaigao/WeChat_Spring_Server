package com.imooc;

import com.imooc.netty.WSServer;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            // 启动Netty
            try {
                WSServer.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
