package br.com.ractecnologia.serialize;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.ractecnologia.entity.LicensingModel;
import br.com.ractecnologia.exception.LicensingModelException;

public class LicensingModelNameDeserialize extends JsonDeserializer<LicensingModel> {

	@Override
	public LicensingModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		
		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		final LicensingModel type;

		if (Objects.nonNull(node)) {
			type = LicensingModel.get(node.asText());
			if (Objects.isNull(type)) {
				throw new LicensingModelException("Licensing Model Inválido");
			}
		}else {
			throw new LicensingModelException("Licensing Model Inválido");
		}

		return type;
	}
}