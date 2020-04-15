package com.summer.demos.net;//package com.test.net;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//import java.util.Date;
//import java.util.List;
//
//public class MockClient {
//
//
//	public static class TimeClientHandler extends ChannelInboundHandlerAdapter{
////		@Override
////	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
////	        ByteBuf m = (ByteBuf) msg; // (1)
////	        try {
////	            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
////	            System.out.println(new Date(currentTimeMillis));
////	            ctx.close();
////	        } finally {
////	            m.release();
////	        }
////	    }
//		@Override
//		public void channelRead(ChannelHandlerContext ctx, Object msg) {
//		    UnixTime m = (UnixTime) msg;
//			System.out.println("channelRead is ");
//		    System.out.println(m);
//		    ctx.close();
//		}
//
//	    @Override
//	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//	        cause.printStackTrace();
//	        ctx.close();
//	    }
//	}
//
//	public static class TimeClientDecoder extends ByteToMessageDecoder{
//
//		@Override
//		protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//			if (in.readableBytes() < 4) {
//		        return;
//		    }
//
//		    out.add(new UnixTime(in.readUnsignedInt()));
//		}
//
//	}
//
//
//	public static class UnixTime {
//
//	    private final long value;
//
//	    public UnixTime() {
//	        this(System.currentTimeMillis() / 1000L + 2208988800L);
//	    }
//
//	    public UnixTime(long value) {
//	        this.value = value;
//	    }
//
//	    public long value() {
//	        return value;
//	    }
//
//	    @Override
//	    public String toString() {
//	        return new Date((value() - 2208988800L) * 1000L).toString();
//	    }
//	}
//
//
//
//
//	public static void main(String[] args) throws InterruptedException {
////		String host = args[0];
////        int port = Integer.parseInt(args[1]);
//		String host = "127.0.0.1";
//		int port = 8080;
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        try {
//            Bootstrap b = new Bootstrap(); // (1)
//            b.group(workerGroup); // (2)
//            b.channel(NioSocketChannel.class); // (3)
//            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeClientDecoder(), new TimeClientHandler());
//                }
//            });
//
//            // Start the client.
//            ChannelFuture f = b.connect(host, port).sync(); // (5)
//
//            // Wait until the connection is closed.
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//        }
//	}
//
//}
