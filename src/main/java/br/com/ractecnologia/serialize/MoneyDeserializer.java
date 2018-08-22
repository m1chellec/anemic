package br.com.ractecnologia.serialize;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;

import br.com.ractecnologia.exception.MoneyException;

public class MoneyDeserializer extends JsonDeserializer<BigDecimal> {

    private NumberDeserializers.BigDecimalDeserializer delegate = NumberDeserializers.BigDecimalDeserializer.instance;

    @Override
    public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        BigDecimal bd = delegate.deserialize(jp, ctxt);
        if(bd.doubleValue() > 10 || bd.doubleValue() < 0) {
        	throw new MoneyException("moneySpent deve estar entre 0 e 10");
        }
        return bd;
    }    
}