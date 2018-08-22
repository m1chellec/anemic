package br.com.ractecnologia.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.ractecnologia.entity.CustomerStatus;

public class CustomerStatusSerializer extends StdSerializer<CustomerStatus> {

	private static final long serialVersionUID = 251359054280817262L;

	public CustomerStatusSerializer() {
		super(CustomerStatus.class);
	}
	
	public CustomerStatusSerializer(Class<CustomerStatus> clazz) {
		super(clazz);
	}

	@Override
	public void serialize(CustomerStatus value, JsonGenerator generator, SerializerProvider provider) 
			throws IOException {
		generator.writeStartObject();
		generator.writeFieldName("name");
        generator.writeString(value.name());
        generator.writeFieldName("value");
        generator.writeNumber(value.getValue());
        generator.writeFieldName("ordinal");
        generator.writeNumber(value.ordinal());
		generator.writeEndObject();
		

	}

}
