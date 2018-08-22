package br.com.ractecnologia.converter;

import javax.persistence.AttributeConverter;

import br.com.ractecnologia.entity.LicensingModel;

public class LicensingModelConverter implements AttributeConverter<LicensingModel, Integer> {

	@Override
	public Integer convertToDatabaseColumn(LicensingModel attribute) {
		switch (attribute) {
		case TWO_DAYS:
			return 1;
		case LIFE_LONG:
			return 2;

		default:
			throw new IllegalArgumentException("Unknown" + attribute);
		}
	}

	@Override
	public LicensingModel convertToEntityAttribute(Integer dbData) {
		switch (dbData) {
		case 1:
			return LicensingModel.TWO_DAYS;
		case 2:
			return LicensingModel.LIFE_LONG;

		default:
			throw new IllegalArgumentException("Unknown" + dbData);
		}
	}

}
