package com.test.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;


/**
 * Created by huojia on 2016/4/21 10:13.
 * DISCARD协议的实现，丢弃所有接收到的数据协议
 */
public class DiscardServerHandler extends ChannelHandlerAdapter{
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            //为了能显式的知道接收到的信息，来做一些操作
            //两个同等
//            while (in.isReadable()){
//                System.out.println((char)in.readByte());
//                System.out.flush();
//            }

            System.out.println(in.toString(CharsetUtil.US_ASCII));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
