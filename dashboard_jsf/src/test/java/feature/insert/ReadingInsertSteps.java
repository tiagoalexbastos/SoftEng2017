package feature.insert;

import feature.getting.*;
import com.es.dashboard.Reading;
import feature.connection.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import junit.framework.Assert;




public class ReadingInsertSteps {
        List<Reading> readings = new ArrayList();
	
        @Given("^a reading with the timestamp (\\d+), with data '(.+)', with name (.+), at (.+).")
	public void connectFactory(int timestamp, String data, String name, String room) {
            Date date = new Date(timestamp);
            readings.add(new Reading(date, data, name, room));
        }
        
        @Then("^(\\d+) reading should have been found$")
        public void assertSize(int size){
            Assert.assertEquals(size, readings.size());
        }

}
