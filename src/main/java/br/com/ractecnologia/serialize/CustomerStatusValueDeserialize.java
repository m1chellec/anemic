package br.com.ractecnologia.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.ractecnologia.entity.CustomerStatus;

public class CustomerStatusValueDeserialize extends JsonDeserializer<CustomerStatus> {

	@Override
	public CustomerStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		CustomerStatus type = null;
		try {
			if (node != null) {
				// Integer.parseInt(node.get("attr").asText())
				type = CustomerStatus.get( node.asInt()    ); //CustomerStatus.get();
				if (type != null) {
					return type;
				}
			}
		} catch (Exception e) {
			type = null;
		}
		return type;
	}
}