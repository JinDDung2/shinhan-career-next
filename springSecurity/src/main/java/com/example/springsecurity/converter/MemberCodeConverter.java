package com.example.springsecurity.converter;

import com.example.springsecurity.memeber.entity.MemberCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MemberCodeConverter implements AttributeConverter<MemberCode, String> {

    @Override
    public String convertToDatabaseColumn(MemberCode attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public MemberCode convertToEntityAttribute(String dbData) {
        return dbData == null ? null : MemberCode.from(dbData);
    }
}