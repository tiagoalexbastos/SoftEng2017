package feature.connection;

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

public class ConnectionSteps {
        ConnectionFactory factory;

	@Given("^that we want to connect to the broker\\.$")
	public void connectFactory() {
        factory = new ConnectionFactory();	
        }

	@When("^we try to connect to the (.+) IP.")
	public void setIP(String ip) {
                    factory.setHost(ip); // RabbitMQ IP
	}

	@Then("^we should get a successfull connection.$")
	public void finalizeConnect() throws IOException, TimeoutException {
              Connection connection = factory.newConnection();
	}

}
