package com.kazbekov.basecompare.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

class SelectFilesViewModel : ViewModel() {

    private val repository = SelectFilesRepository()

    //.xlsx files
    private val firstFileLiveData = MutableLiveData<File>(null)
    private val secondFileLiveData = MutableLiveData<File>(null)
    private val onPassedLiveData = MutableLiveData<Unit>()
    private val notPassedLiveData = MutableLiveData<Int>()

    val firstFile: LiveData<File>
        get() = firstFileLiveData
    val secondFile: LiveData<File>
        get() = secondFileLiveData
    val onPassed: LiveData<Unit>
        get() = onPassedLiveData
    val notPassed: LiveData<Int>
        get() = notPassedLiveData

    fun checkFileStandard() {
        repository.checkFileStandard(
            firstFileLiveData.value!!,
            secondFileLiveData.value!!,
            {
                //Прошла проверку
                onPassedLiveData.postValue(Unit)
            },
            {
                //Не прошла проверку
                notPassedLiveData.postValue(it.first())
            },
            {
                //Не обработанная ошибка
            }
        )
    }
}