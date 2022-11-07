package com.kurosz.kafkalogginexample.processor;

import com.kurosz.logging.annotations.LogProcessedEvent;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.ProcessorSupplier;
import org.apache.kafka.streams.processor.api.Record;
import org.springframework.stereotype.Component;

public class UsersProcessorSupplier implements ProcessorSupplier<String, String, String, String> {

    @Override
    public Processor<String, String, String, String> get() {
        return new UsersProcessor();
    }
}

@Component
class UsersProcessor implements Processor<String, String, String, String> {
    private ProcessorContext<String, String> context;

    @Override
    @LogProcessedEvent
    public void process(Record<String, String> record) {
        System.out.println("process record" + record.key() + " val: " + record.value());
        context.forward(record);
    }

    @Override
    public void init(ProcessorContext<String, String> context) {
        this.context = context;
    }
}
