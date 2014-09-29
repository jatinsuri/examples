import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Some basic tests for trying out the Apache Curator API. 
 * 
 * <p>This class consists of a {@link #main main} driver function to 
 * launch various tests.
 * 
 * Currently Implemented:
 * <ul>
 *	<li>{@link #connectAndWait()}, A simple test to obtain and start a client, 
 *  	wait for a configurable period and give up if not connected till then.
 *  	Once a connection is established, one can observe zookeeper connection
 *  	management events 
 * </ul>
 *
 */

public class FrameworkExample {
	// Helper objects
	private final static Logger		logger	= LoggerFactory.getLogger("FrameworkExample");
	private final static Scanner 	scanner	= new Scanner(System.in);
	
	// Some constants to guide the execution
	private final static int SLEEP_TIME 	= Integer.getInteger("SLEEP_TIME", 1000);
	private final static int MAX_RETRIES	= Integer.getInteger("MAX_RETRIES", 3);
	private final static int TIMEOUT		= Integer.getInteger("TIMEOUT", 30);
	
	private final static String CONNECT_STRING = System.getProperty("CONNECT_STRING", "localhost:2181");
	
	/**
	 * Obtains an instance of a CuratorFramework client and starts it and then
	 * waits for a configurable time interval for the connection to be 
	 * established.
	 * <p>If an initial connection is successfully established, the test then
	 * waits till ENTER is pressed.
	 * 
	 */
	public void connectAndWait() {
		logger.info("Starting up");
		
		logger.info("Settings - CONNECT_STRING: {}, TIMEOUT: {}, SLEEP_TIME: {}, MAX_RETRIES: {}", 
				CONNECT_STRING,
				TIMEOUT,
				SLEEP_TIME,
				MAX_RETRIES);
		
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(SLEEP_TIME, MAX_RETRIES);
		CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_STRING, retryPolicy);
		
		logger.info("Establishing initial connection...");
		client.start();
		try {
			// Wait until connected and allow an extra second for things to stabilize
			client.blockUntilConnected(TIMEOUT, TimeUnit.SECONDS);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("Interrupted before getting connected", e);
		}
		
		if(client.getZookeeperClient().isConnected()) {
			logger.info("Connection established");
			waitForever();
		} else {
			logger.info("Unable to establish connection after waiting {} seconds", TIMEOUT);
		}

		logger.info("Cleaning up");
		client.close();
		
		logger.info("Done!");
	}
	
	/**
	 * The main routine is simply the driver for the various tests.
	 * 
	 * @param args Commandline arguments
	 */
	public static void main(String[] args) {
		FrameworkExample example = new FrameworkExample();
		example.connectAndWait();
		System.exit(0);
	}
	
	/**
	 * Helper function to wait till ENTER key is pressed
	 *   
	 */
	private void waitForever() {
		logger.info("Press ENTER to continue...");
		scanner.nextLine();
		logger.info("Continuing");
	}
}
