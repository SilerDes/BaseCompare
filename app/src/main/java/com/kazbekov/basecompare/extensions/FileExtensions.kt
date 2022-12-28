package com.kazbekov.basecompare.extensions

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.xmlbeans.SchemaType

//Возвращает тип в столбце - строка, чисто и др.
fun XSSFSheet.type(columnIndex: Long): SchemaType {
    return this.columnHelper.getColumn(columnIndex, false).schemaType()
}