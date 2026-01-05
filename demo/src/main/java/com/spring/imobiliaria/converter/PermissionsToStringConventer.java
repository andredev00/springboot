package com.spring.imobiliaria.converter;

import javax.persistence.AttributeConverter;

import com.spring.imobiliaria.enums.PermissionsEnum;

public class PermissionsToStringConventer implements AttributeConverter<PermissionsEnum, String> {

	@Override
	public String convertToDatabaseColumn(PermissionsEnum attribute) {
		return attribute.equals(PermissionsEnum.USER) ? "USER" : "ADMIN";
	}

	@Override
	public PermissionsEnum convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return null;
	}

}
