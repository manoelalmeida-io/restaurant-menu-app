package com.example.backend.converter

import com.example.backend.model.PaymentMethod
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class PaymentMethodConverter : AttributeConverter<PaymentMethod, Int?> {

  override fun convertToDatabaseColumn(attribute: PaymentMethod?): Int? {
    return attribute?.value
  }

  override fun convertToEntityAttribute(dbData: Int?): PaymentMethod? {
    if (dbData == null) {
      return null
    }

    return PaymentMethod.values().first { it.value == dbData }
  }
}