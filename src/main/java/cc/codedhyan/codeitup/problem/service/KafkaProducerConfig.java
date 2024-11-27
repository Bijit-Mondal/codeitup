// package cc.codedhyan.codeitup.problem.service;

// import java.io.IOException;
// import java.util.HashMap;
// import java.util.Map;

// import org.apache.kafka.clients.CommonClientConfigs;
// import org.apache.kafka.clients.producer.ProducerConfig;
// import org.apache.kafka.common.config.SslConfigs;
// import org.apache.kafka.common.serialization.StringSerializer;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.Resource;
// import org.springframework.kafka.annotation.EnableKafka;
// import org.springframework.kafka.core.DefaultKafkaProducerFactory;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.core.ProducerFactory;
// import org.springframework.kafka.support.serializer.JsonSerializer;


// import cc.codedhyan.codeitup.problem.Judge0Response;

// @Configuration
// @EnableKafka
// public class KafkaProducerConfig {

//     @Value("${spring.kafka.bootstrap-servers}")
//     private String bootstrapServers;
//     @Value("${spring.kafka.ssl.trust-store-location}")
//     private Resource trustStoreLocation;
//     @Value("${spring.kafka.ssl.trust-store-password}")
//     private String trustStorePassword;
//     @Value("${spring.kafka.ssl.key-store-location}")
//     private Resource keyStoreLocation;
//     @Value("${spring.kafka.ssl.key-store-password}")
//     private String keyStorePassword;

//     @Bean
//     public Map<String, Object> producerConfig() throws IOException {
//         Map<String, Object> configProps = new HashMap<>();
//         configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//         configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
//         configProps.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreLocation.getFile().getAbsolutePath());
//         configProps.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, trustStorePassword);
//         configProps.put("ssl.keystore.type", "PKCS12");
//         configProps.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keyStoreLocation.getFile().getAbsolutePath());
//         configProps.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, keyStorePassword);
//         configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//         configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//         return configProps;
//     }

//     @Bean
//     public ProducerFactory<String, Judge0Response> producerFactory() {
//         return new DefaultKafkaProducerFactory<>(producerConfig());
//     }

//     @Bean
//     public KafkaTemplate<String, Judge0Response> kafkaTemplate(
//         ProducerFactory<String, Judge0Response> producerFactory
//     ) {
//         return new KafkaTemplate<>(producerFactory);
//     }
// }
