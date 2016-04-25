package com.test.netty.echo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by huojia on 2016/4/25 16:24.
 * ECHO服务（响应式协议）针对任何接收的数据都会返回一个响应
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**
         * 请注意不同于DISCARD的例子我们并没有释放接受到的消息，
         * 这是因为当写入的时候Netty已经帮我们释放了
         */
        ctx.writeAndFlush(msg);
    }
}
