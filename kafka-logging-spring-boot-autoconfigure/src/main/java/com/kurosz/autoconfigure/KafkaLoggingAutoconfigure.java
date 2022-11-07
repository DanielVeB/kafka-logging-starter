package com.kurosz.autoconfigure;

import com.kurosz.logging.aspects.ProcessedEventAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(value = ProcessedEventAspect.class, name = "org.apache.kafka.streams.processor.Processor")
@EnableConfigurationProperties(KafkaLoggingProperties.class)
public class KafkaLoggingAutoconfigure {

    @Bean
    public ProcessedEventAspect processedEventAspect() {
        return new ProcessedEventAspect();
    }
}
