package dev.augustoximenes.kafka.streams;

import dev.augustoximenes.kafka.streams.models.keys.ClientKey;
import dev.augustoximenes.kafka.streams.models.keys.Key;
import dev.augustoximenes.kafka.streams.models.values.AccountValue;
import dev.augustoximenes.kafka.streams.models.values.ClientValue;
import dev.augustoximenes.kafka.streams.models.values.EnrichmentAccountValue;
import dev.augustoximenes.kafka.streams.models.values.ProspectValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
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
					KStream<ClientKey, ClientValue>>>> process() {
		return prospectTable -> (
				accountTable -> (
					enrichmentAccountTable -> (
						prospectTable
						.join(
							accountTable,
							(prospectValue, accountValue) -> new ClientValue(prospectValue.getId(), prospectValue.getName(), accountValue.getAccountNumber(), null)
						)
						.join(
							enrichmentAccountTable,
							(clientValue, enrichmentAccountValue) -> {
								clientValue.setStatus(enrichmentAccountValue.getStatus());
								return clientValue;
							}
						)
						.toStream()
						.map((clientKey, clientValue) -> KeyValue.pair(new ClientKey(clientValue.getAccountNumber()), clientValue))
						.filter((clientKey, clientValue) -> clientValue.getStatus().equals("REGULAR"))
						.peek((k, v) -> log.info("Key: {}, Value: {}", k, v))
					)
				)
		);
	}
}
