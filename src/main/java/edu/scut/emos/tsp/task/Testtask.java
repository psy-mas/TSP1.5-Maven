package edu.scut.emos.tsp.task;

import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.system.OutgoingMessageEnvelope;
import org.apache.samza.system.SystemStream;
import org.apache.samza.task.MessageCollector;
import org.apache.samza.task.StreamTask;
import org.apache.samza.task.TaskCoordinator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Qinzheng
 */
public class Testtask implements StreamTask {
	  private static final SystemStream OUTPUT_STREAM = new SystemStream("kafka", "tsp_test_1");
	  private static final Logger LOG = LoggerFactory.getLogger(Testtask.class);
	

	  
	  @Override
	  public void process(IncomingMessageEnvelope envelope, MessageCollector collector, TaskCoordinator coordinator) {
		    LOG.info("START PROCESSING!");
		    LOG.info(envelope.getKey().toString() + envelope.getMessage().toString());

		    LOG.info("KEY:" + envelope.getKey() + ", MSG:" + envelope.getMessage());
		    collector.send(new OutgoingMessageEnvelope(OUTPUT_STREAM, envelope.getKey(), envelope.getMessage()));
		  }
	  
	  
}
