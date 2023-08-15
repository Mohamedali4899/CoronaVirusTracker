public class TestCoronaTrackingApp {
    public static void main(String[] args) {
        // Simulate new Corona tracking data
        CoronaTrackingData data = new CoronaTrackingData();
        data.setName("Mohamed Ali");
        data.setLocation("Chennai");
        data.setDate("2023-07-25");

        // Send Corona tracking data to JMS queue
        CoronaDataProducer producer = new CoronaDataProducer();
        producer.sendCoronaData(data);

        // Start the message consumer
        CoronaDataConsumer consumer = new CoronaDataConsumer();
        consumer.startListening();
    }
}