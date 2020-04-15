package com.summer.demos.net;//package com.test.net;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.StandardSocketOptions;
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeoutException;
//import java.util.function.Consumer;
//
///**
// * AsyncCounterClient is used to connect to remote the AsyncCounterServer.
// * Multiple AsyncCounterClients can connect to AsyncCounterServer at the same time.
// * There a command line program which will run AsyncCounterClient instances.
// * See: {@link CounterClientCLI}
// */
//public class AsyncEchoClient {
//
//	private Logger logger = LoggerFactory.getLogger(AsyncEchoClient.class.getName());
//
//
//	private String serverName= null;
//	private int port;
//	private String clientName;
//
//    public final static int MESSAGE_INPUT_SIZE= 128;
//
//    private final static int WAIT_TIME = 3;
//
// 	public AsyncEchoClient(String clientName, String serverName, int port) throws IOException {
//		logger.info(">>AsynCounterClient(clientName=" + clientName + ",serverName=" +
//							serverName + ",port=" + port + ")");
//		this.clientName = clientName;
//		this.serverName = serverName;
//		this.port = port;
//	}
//
//
//	private void connectToServer(int waitTime,  Consumer<AsynchronousSocketChannel> fn)
//						throws IOException, InterruptedException, ExecutionException, TimeoutException {
//		AsynchronousSocketChannel asyncSocketChannel = AsynchronousSocketChannel.open();
//		Future<Void> connectFuture = null;
//
//		// Connecting to server
//		System.out.println("Connecting to server... " + serverName + ",port=" + port);
//		logger.info("Connecting to server... " + serverName + ",port=" + port);
//		asyncSocketChannel.connect(new InetSocketAddress("localhost", this.port), "localhost",
//			new CompletionHandler<Void, String>(){
//				@Override
//				public void completed(Void result, String attachment) {
//					try {
//						asyncSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * MESSAGE_INPUT_SIZE);
//						asyncSocketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * MESSAGE_INPUT_SIZE);
//						asyncSocketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
//						fn.accept(asyncSocketChannel);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//
//				@Override
//				public void failed(Throwable exc, String attachment) {
//					logger.error("connection error",exc);
//				}
//			});
//
//		// You have two seconds to connect. This will throw exception if server is not there.
////		connectFuture.get(waitTime, TimeUnit.SECONDS);
//
////		asyncSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * MESSAGE_INPUT_SIZE);
////		asyncSocketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * MESSAGE_INPUT_SIZE);
////		asyncSocketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
//
//		//return asyncSocketChannel;
//	}
//
//	public void sendMessage(String request) {
//		logger.info(">> sendMessage(request=" + request + ")");
//
//		try {
//			connectToServer(WAIT_TIME, asyncSocketChannel -> {
//					String response = null;
//					ByteBuffer messageByteBuffer = ByteBuffer.wrap(request.getBytes());
//					Future<Integer> futureWriteResult = asyncSocketChannel.write(messageByteBuffer);
//					try {
//						futureWriteResult.get();
//					} catch (InterruptedException | ExecutionException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					//Now wait for return message.
//					ByteBuffer returnMessage = ByteBuffer.allocate(MESSAGE_INPUT_SIZE);
//					Future<Integer> futureReadResult = asyncSocketChannel.read(returnMessage);
//		     		try {
//						futureReadResult.get();
//						response = new String(returnMessage.array());
//					} catch (InterruptedException | ExecutionException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					logger.info("received response=" + response);
//
//					messageByteBuffer.clear();
//			});
//		} catch (InterruptedException | ExecutionException | TimeoutException | IOException e) {
//			handleException(e);
//		} finally {
////			if (asyncSocketChannel.isOpen()) {
////				try {
////					asyncSocketChannel.close();
////				} catch (IOException e) {
////					// Not really anything we can do here.
////					e.printStackTrace();
////				}
////			}
//		}
////		return response;
//	}
//
//	private void handleException(Exception e) {
//		e.printStackTrace();
//		throw new RuntimeException(e);
//	}
//
//	public static void main(String[] argv) throws IOException{
//		AsyncEchoClient client  = new AsyncEchoClient("127.0.0.1", "127.0.0.1", 8099 );
//		client.sendMessage("request from mysql client");
//	}
//
//
//}