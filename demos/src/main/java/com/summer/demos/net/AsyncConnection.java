package com.summer.demos.net;//package com.test.net;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.util.concurrent.Future;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//
///**
// * An async style mysql connection
// *
// *
// * @author lishuangtao
// *
// */
//public class AsyncConnection {
//
//	private static final Logger logger = LoggerFactory.getLogger(AsyncConnection.class);
//	private InetSocketAddress address;
//	private String username;
//	private String password;
//
//	private byte charsetNumber = 33;
//	private String defaultSchema = "retl";
//	private int soTimeout = 30 * 1000;
//	private int receiveBufferSize = 16 * 1024;
//	private int sendBufferSize = 16 * 1024;
//	private AsynchronousSocketChannel channel;
//	private volatile boolean dumping = false;
//	// mysql connectinnId
//	private long connectionId = -1;
//	private AtomicBoolean connected = new AtomicBoolean(false);
//
//	public AsyncConnection(InetSocketAddress address, String username, String password){
//		this.address = address;
//		this.username = username;
//		this.password = password;
//	}
//
//	public void connect() throws IOException{
//		channel = AsynchronousSocketChannel.open();
//		Future fn = channel.connect(address);
//
//		// send the authenticate package
//
//
//	}
//
//
//}
