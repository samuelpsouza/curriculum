package space.ssouza.mslog.config

import org.apache.kafka.clients.producer.ProducerConfig.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfig

@Bean
fun producerFactory(): ProducerFactory<String, String> = DefaultKafkaProducerFactory(mapOf(
    Pair(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"),
    Pair(RETRIES_CONFIG, 0),
    Pair(BUFFER_MEMORY_CONFIG, 33554432),
    Pair(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer"),
    Pair(VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer")
))

@Bean
fun kafkaTemplate(): KafkaTemplate<String, String> {
    return KafkaTemplate(producerFactory())
}
