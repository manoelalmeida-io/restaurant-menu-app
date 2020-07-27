package com.example.backend.converter

import com.example.backend.model.CheckoutMethod
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class CheckoutMethodConverter : AttributeConverter<CheckoutMethod, Int?> {

  override fun convertToDatabaseColumn(attribute: CheckoutMethod?): Int? {
    return attribute?.value
  }

  override fun convertToEntityAttribute(dbData: Int?): CheckoutMethod? {
    if (dbData == null) {
      return null
    }

    return CheckoutMethod.values().first { it.value == dbData }
  }
}