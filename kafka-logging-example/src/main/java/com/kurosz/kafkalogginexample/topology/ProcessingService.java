package com.kurosz.kafkalogginexample.topology;

import com.kurosz.kafkalogginexample.processor.UsersProcessorSupplier;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessingService {

    @Autowired
    public void buildTopology(StreamsBuilder streamsBuilder){
        var topology = streamsBuilder.build();

        topology.addSource("users-source",
                Serdes.String().deserializer(),
                Serdes.String().deserializer(),
                "users");

        topology.addProcessor("users-processor", new UsersProcessorSupplier(), "users-source");

        topology.addSink("users-sink",
                "users-output",
                Serdes.String().serializer(),
                Serdes.String().serializer(),
                "users-processor");
    }
}
