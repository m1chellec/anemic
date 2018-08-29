package br.com.ractecnologia.serialize;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.ractecnologia.exception.DateException;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		
		Date date = null;
		if (Objects.nonNull(node)) {
			try {
				date = dateFormatter.parse(node.asText());
			} catch (ParseException e) {
				throw new DateException("Data com formato inválido.");
			}
		}
		return date;
	}

}
