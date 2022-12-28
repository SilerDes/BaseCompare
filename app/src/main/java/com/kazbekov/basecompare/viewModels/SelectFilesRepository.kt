package com.kazbekov.basecompare.viewModels

import android.content.Context
import com.kazbekov.basecompare.extensions.type
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File

class SelectFilesRepository() {

    /**
     *
     * Первая проверка: одинаковое количество столбцов
     * Вторая проверка: соответствующие столбцы имеют одинаковый формат данных
     *
     * В случае нарушения одного из двух правил пользователь получает предупреждение
     *
     **/
    fun checkFileStandard(
        firstFile: File,
        secondFile: File,
        passedByStandard: () -> Unit,
        notPassedByStandard: (List<Int>) -> Unit,
        onError: (String?) -> Unit
    ) {
        try {
            Thread {
                val warningList = mutableListOf<Int>() //Всегда один элемент = одно предупреждение

                val fWorkbook: XSSFWorkbook
                val sWorkbook: XSSFWorkbook

                val fSheet: XSSFSheet
                val sSheet: XSSFSheet

                firstFile.inputStream().use {
                    fWorkbook = XSSFWorkbook(it)
                    fSheet = fWorkbook.getSheetAt(0)
                }
                secondFile.inputStream().use {
                    sWorkbook = XSSFWorkbook(it)
                    sSheet = sWorkbook.getSheetAt(0)
                }

                if (fSheet.getRow(0).lastCellNum != sSheet.getRow(0).lastCellNum) {
                    warningList.add(COLUMN_NUMBER)
                } else {
                    for (i in 0..fSheet.getRow(0).lastCellNum) {
                        if (fSheet.type(i.toLong()) != sSheet.type(i.toLong())) {
                            warningList.add(COLUMN_TYPE)
                            break
                        }
                    }
                }

                if (warningList.isEmpty()) {
                    passedByStandard()
                } else {
                    notPassedByStandard(warningList)
                }

            }.start()
        } catch (t: Throwable) {
            onError(t.message)
        }
    }

    companion object {
        const val COLUMN_NUMBER = 100
        const val COLUMN_TYPE = 200
    }
}