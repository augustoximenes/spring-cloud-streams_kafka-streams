package dev.augustoximenes.kafka.streams;

import dev.augustoximenes.kafka.streams.models.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@Slf4j
@SpringBootApplication
public class StreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamsApplication.class, args);
	}

	@Bean
	public Function<KTable<Key, ProspectValue>,
			Function<KTable<Key, AccountValue>,
				Function<KTable<Key, EnrichmentAccountValue>,
					KStream<Key, ClientValue>>>> process() {
		return prospectTable -> (
				accountTable -> (
					enrichmentAccountTable -> (prospectTable
						.join(
							accountTable,
							(prospectValue, accountValue) -> new ClientValue(prospectValue.getId(), prospectValue.getName(), accountValue.getAccountNumber(), null)
						)
						.join(enrichmentAccountTable,
							(clientValue, enrichmentAccountValue) -> {
								clientValue.setStatus(enrichmentAccountValue.getStatus());
								return clientValue;
							}
						)
						.toStream()
						.peek((k, v) -> log.info("Key: {}, Value: {}", k, v))
				)
			)
		);
	}
}
