package dev.augustoximenes.kafka.streams;

import dev.augustoximenes.kafka.streams.models.Prospect;
import dev.augustoximenes.kafka.streams.models.Client;
import dev.augustoximenes.kafka.streams.models.Account;
import dev.augustoximenes.kafka.streams.models.EnrichmentAccount;
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
	public Function<KTable<String, Prospect>,
			Function<KTable<String, Account>,
					Function<KTable<String, EnrichmentAccount>, KStream<String, Client>>>> process() {
		return prospectTable -> (accountTable -> (enrichmentAccountTable -> (
				prospectTable.join(accountTable, (prospect, account) ->
							new Client(prospect.getId(), prospect.getName(), account.getAccountNumber(), null))
							.join(enrichmentAccountTable, (client, enrichmentAccount) -> {
								client.setStatus(enrichmentAccount.getStatus());
								return client;
							})
							.toStream()
							.peek((k, v) -> log.info("Key: {}, Value: {}", k, v))
				)
			)
		);
	}
}
