package com.test.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by huojia on 2016/4/25 16:56.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    /**
     * 在连接被建立并且准备进行通信时被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**
         * 为了发送一个新的消息，我们需要分配一个包含这个消息的新的缓冲。
         * 因为我们需要写入一个32位的整数，因此我们需要一个至少有4个字节
         * 的ByteBuf。通过ChannelHandlerContext.alloc()得到一个当前的
         * ByteBufAllocator，然后分配一个新的缓冲。
         */
        ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));
        ChannelFuture f = ctx.writeAndFlush(time);
//        f.addListener(ChannelFutureListener.CLOSE);
        f.addListener(future->{
            assert f == future;
            ctx.close();
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
