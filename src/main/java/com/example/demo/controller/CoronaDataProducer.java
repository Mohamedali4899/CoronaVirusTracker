import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

public class CoronaDataProducer {
    private static final String BROKER_URL = "tcp://localhost:61616"; // ActiveMQ broker URL
    private static final String CORONA_QUEUE = "corona.queue"; // Queue name

    public void sendCoronaData(CoronaTrackingData data) {
        try {
            // Initialize JMS connection
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a JMS session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Lookup the Corona queue
            Queue queue = session.createQueue(CORONA_QUEUE);

            // Create a message producer
            MessageProducer producer = session.createProducer(queue);

            // Create an object message containing the Corona tracking data
            ObjectMessage message = session.createObjectMessage(data);

            // Send the message
            producer.send(message);

            // Clean up resources
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}