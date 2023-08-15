import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

public class CoronaDataConsumer {
    private static final String BROKER_URL = "tcp://localhost:61616"; // ActiveMQ broker URL
    private static final String CORONA_QUEUE = "corona.queue"; // Queue name

    public void startListening() {
        try {
            // Initialize JMS connection
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a JMS session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Lookup the Corona queue
            Queue queue = session.createQueue(CORONA_QUEUE);

            // Create a message consumer
            MessageConsumer consumer = session.createConsumer(queue);

            // Start listening for messages
            consumer.setMessageListener(message -> {
                if (message instanceof ObjectMessage) {
                    try {
                        // Process the Corona tracking data
                        CoronaTrackingData data = (CoronaTrackingData) ((ObjectMessage) message).getObject();
                        processCoronaData(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processCoronaData(CoronaTrackingData data) {
        // Implement your Corona tracking data processing logic here
        System.out.println("Received Corona tracking data: " + data.toString());
    }
}