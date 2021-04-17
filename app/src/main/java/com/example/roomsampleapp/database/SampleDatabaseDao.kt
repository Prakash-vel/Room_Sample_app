package com.example.roomsampleapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SampleDatabaseDao {
    @Insert
    fun insert(sample: SampleData)

    @Query("DELETE  FROM sample_data ")
    suspend fun clear()

    @Query("SELECT * FROM sample_data ORDER BY sampleId DESC")
    suspend fun getAllTasks(): List<SampleData>
}