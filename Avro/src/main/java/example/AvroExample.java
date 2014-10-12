package example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroExample {
	// Helper objects
	private final static Logger	logger	= LoggerFactory.getLogger("AvroExample");
	
	private void runTest() {
		try {
			InputStream in = getClass().getResourceAsStream("/avro/Twitter.avsc");
			Schema schema = new Schema.Parser().parse(in);
			
			GenericRecord tweet = new GenericData.Record(schema);
			tweet.put("username", "jhondoe");
			tweet.put("tweet", "Tweet Tweet");
			tweet.put("timestamp", new Date().getTime());
			
			logger.info("Created Tweet: {}", tweet);
			DatumWriter<GenericRecord> dw = new GenericDatumWriter<GenericRecord>(schema);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BinaryEncoder be = EncoderFactory.get().binaryEncoder(baos, null);
			dw.write(tweet, be);
			be.flush();
			
			byte[] bytes = baos.toByteArray();
			logger.info("Serialized size: {}", bytes.length);
			logger.info("Binary: {}", new String(bytes));
			
			baos.reset();
			JsonEncoder je = EncoderFactory.get().jsonEncoder(schema, baos);
			dw.write(tweet, je);
			je.flush();
			
			bytes = baos.toByteArray();
			logger.info("Serialized size: {}", bytes.length);
			logger.info("JSON: {}", baos.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AvroExample e = new AvroExample();
		e.runTest();
	}

}
