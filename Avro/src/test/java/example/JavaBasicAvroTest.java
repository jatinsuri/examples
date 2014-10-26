package example;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecord;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miguno.avro.Tweet;

public class JavaBasicAvroTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testSchemaLoad() throws IOException {
		Schema twitter = new Schema.Parser().parse(getClass().getResourceAsStream("/avro/Twitter.avsc"));
		assertNotNull(twitter);
		log.info("Schema: {}", twitter);
	}
	
	@Test
	public void testSpecificRecord() {
		Tweet tweet = new Tweet();
		tweet.setUsername("Malcolm X");
		tweet.setTweet("I want to be free");
		tweet.setTimestamp(new Date().getTime());
		assertTrue(tweet instanceof SpecificRecord);
		log.info("Tweet: {}", tweet);
	}
}
