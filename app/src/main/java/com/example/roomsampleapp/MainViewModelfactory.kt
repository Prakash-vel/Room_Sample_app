package com.example.roomsampleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomsampleapp.database.SampleDatabaseDao



class MainViewModelFactory(

    private val dataSource: SampleDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel( dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
