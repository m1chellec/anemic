package br.com.ractecnologia.serialize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date> {

	private static final long serialVersionUID = 6626427991939326951L;
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	protected CustomDateSerializer() {
		super(Date.class);
	}
	
	@Override
	public void serialize(Date value, JsonGenerator generator, SerializerProvider provider) throws IOException {

		generator.writeString(dateFormatter.format(value));
	}

}
