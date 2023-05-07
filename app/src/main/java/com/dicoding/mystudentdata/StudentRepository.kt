package com.dicoding.mystudentdata

import androidx.lifecycle.LiveData
import com.dicoding.mystudentdata.database.*
import com.dicoding.mystudentdata.helper.InitialDataSource
import com.dicoding.mystudentdata.helper.SortType
import com.dicoding.mystudentdata.helper.SortUtils

class StudentRepository(private val studentDao: StudentDao) {

    // implement RawQuery to support ORDER
    fun getAllStudent(sortType: SortType): LiveData<List<Student>> {
        val query = SortUtils.getSortedQuery(sortType)
        return studentDao.getAllStudent(query)
    }

    // call the DAO that get the Many-to-One data class
    fun getAllStudentAndUniversity(): LiveData<List<StudentAndUniversity>> = studentDao.getAllStudentAndUniversity()

    // call the DAO that get the One-to-Many data class
    fun getAllUniversityAndStudent(): LiveData<List<UniversityAndStudent>> = studentDao.getAllUniversityAndStudent()

    // call the DAO that get the Many-to-Many data class
    fun getAllStudentWithCourse(): LiveData<List<StudentWithCourse>> = studentDao.getAllStudentWithCourse()


}