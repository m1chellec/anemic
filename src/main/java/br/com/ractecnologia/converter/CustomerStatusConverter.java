package br.com.ractecnologia.converter;

import javax.persistence.AttributeConverter;

import br.com.ractecnologia.entity.CustomerStatus;

public class CustomerStatusConverter implements AttributeConverter<CustomerStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(CustomerStatus attribute) {
		switch (attribute) {
		case REGULAR:
			return 1;
		case ADVANCED:
			return 2;
		
		default:
			throw new IllegalArgumentException("Unknown" + attribute);
		}
	}

	@Override
	public CustomerStatus convertToEntityAttribute(Integer dbData) {
		switch (dbData) {
        case 1:
            return CustomerStatus.REGULAR;
        case 2:
            return CustomerStatus.ADVANCED;
        
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
    }
	}

}