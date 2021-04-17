package com.example.roomsampleapp

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomsampleapp.database.SampleData
import com.example.roomsampleapp.database.SampleDatabaseDao
import kotlinx.coroutines.*

class MainViewModel(

    private val database: SampleDatabaseDao
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val state=MutableLiveData<Boolean>()
    init{
        state.value=false
    }
    var data: List<SampleData>?=null
    fun insert(str: String){
        uiScope.launch{
            withContext(Dispatchers.IO){
                var sampleData=SampleData()
                sampleData.sampleName=str
                database.insert(sampleData)
            }
        }
    }
    fun getData(){
        uiScope.launch{
            withContext(Dispatchers.IO){

                data=database.getAllTasks()
            }
            state.value=true
        }
    }

}


