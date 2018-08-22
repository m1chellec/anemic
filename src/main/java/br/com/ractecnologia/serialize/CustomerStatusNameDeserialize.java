package br.com.ractecnologia.serialize;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.ractecnologia.entity.CustomerStatus;
import br.com.ractecnologia.exception.CustomerSatusException;

public class CustomerStatusNameDeserialize extends JsonDeserializer<CustomerStatus> {

	@Override
	public CustomerStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		
		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		final CustomerStatus type;

		if (Objects.nonNull(node)) {
			type = CustomerStatus.get(node.asText());
			if (Objects.isNull(type)) {
				throw new CustomerSatusException("Tipo de status inválido");
			}
		}else {
			throw new CustomerSatusException("Tipo de status inválido");
		}

		return type;
	}
}