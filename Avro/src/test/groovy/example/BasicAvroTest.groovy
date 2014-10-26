package example

import static org.junit.Assert.*;

import com.miguno.avro.Tweet

import groovy.util.logging.Slf4j
import ch.qos.logback.core.util.StatusPrinter

import org.junit.BeforeClass
import org.junit.Test;
import org.apache.avro.Schema
import org.apache.avro.io.DatumWriter
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificData
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.avro.specific.SpecificRecord


@Slf4j
class BasicAvroTest {
	
	/**
	 * Helper method to print runtime information to diagnose
	 * logback issues
	 * 
	 * Use @BeforeClass annotation to enable
	 */
	public static void printInfo() {
		StatusPrinter.print(log.getLoggerContext())		
		BasicAvroTest.class.classLoader.systemClassLoader.URLs.each {log.info(it.toString())}
	}
	
	
	@Test		
	void testSchemaLoad() {
		Schema twitter = new Schema.Parser().parse(getClass().getResourceAsStream('/avro/Twitter.avsc'));
		assertEquals('Tweet', twitter.getName());
		log.info('Schema {}', twitter);
	}

	@Test
	void testSpecificRecord() {
		Tweet tweet = new Tweet();
		tweet.setUsername('Malcolm X')
		tweet.setTweet('I want to be free')
		tweet.setTimestamp(new Date().time)
		
		assertTrue(tweet instanceof SpecificRecord)
		log.info("Tweet: {}", tweet)
	}
}
