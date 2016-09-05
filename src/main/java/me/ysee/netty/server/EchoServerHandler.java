package me.ysee.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.apache.commons.codec.binary.Hex;

/**
 * Created by herosea on 16/8/20.
 */
@ChannelHandler.Sharable                                        //1
public class EchoServerHandler extends
        ChannelInboundHandlerAdapter {

    int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
//        byte[] init = new byte[]{0x40, 0x30, 0x31, 0x31, 0x37, 0x30, 0x0d};
//        ctx.write(Unpooled.wrappedBuffer(init));
//        ctx.flush();
        System.out.println("channelActive.通道开启" + ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
        ByteBuf in = (ByteBuf) msg;

        byte[] bytes = new byte[in.readableBytes()];
        int readerIndex = in.readerIndex();
        in.getBytes(readerIndex, bytes);
        System.out.println(Hex.encodeHexString(bytes));
        DataExtractor.show(bytes);        //2

        if(count == 0){
            byte[] init = new byte[]{0x40, 0x30, 0x31, 0x31, 0x37, 0x30, 0x0d};
            ctx.write(Unpooled.wrappedBuffer(init));
            ctx.flush();
            System.out.println("发送心跳");
            count ++;
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);//4
//                .addListener(ChannelFutureListener.CLOSE);
        System.out.println();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();                //5
        ctx.close();                            //6
    }
}
