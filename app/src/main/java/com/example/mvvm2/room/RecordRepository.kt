package com.example.mvvm2.room

import com.example.mvvm2.entity.RecordEntity

class RecordRepository {

    private val appDbInstance = GlobalApplication.recordDatabaseInstance.recordDAO()

    /** record 저장 - record */
    suspend fun saveData(record: RecordEntity) = appDbInstance.saveData(record)

    /** date에 대한 record - today */
    suspend fun getDateData(date: String) = appDbInstance.getDateData(date)

    /** no에 대한 record - view */
    suspend fun getNoData(no: Long) = appDbInstance.getNoData(no)

    /** 전체 record - total */
    suspend fun getAll() = appDbInstance.getAll()

    /** record 삭제 - detail, total */
    suspend fun deleteData(record: RecordEntity) = appDbInstance.deleteData(record)

    /** record 수정 - detail, total */
    suspend fun updateData(record: RecordEntity) = appDbInstance.updateData(record)


}