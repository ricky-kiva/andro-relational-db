package com.dicoding.mystudentdata

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.mystudentdata.database.*
import com.dicoding.mystudentdata.helper.InitialDataSource
import com.dicoding.mystudentdata.helper.SortType
import com.dicoding.mystudentdata.helper.SortUtils

class StudentRepository(private val studentDao: StudentDao) {

    // implement getAllStudent with Paging 2
    fun getAllStudent(sortType: SortType): LiveData<PagedList<Student>> {
        val query = SortUtils.getSortedQuery(sortType)
        val student = studentDao.getAllStudent(query)


        val config = PagedList.Config.Builder()
            // sets placeholder when data is being loaded
            .setEnablePlaceholders(true)
            // set initial number of loaded data
            .setInitialLoadSizeHint(30)
            // set number of items to load each subsequent page
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(student, config).build()
    }

    // call the DAO that get the Many-to-One data class
    fun getAllStudentAndUniversity(): LiveData<List<StudentAndUniversity>> = studentDao.getAllStudentAndUniversity()

    // call the DAO that get the One-to-Many data class
    fun getAllUniversityAndStudent(): LiveData<List<UniversityAndStudent>> = studentDao.getAllUniversityAndStudent()

    // call the DAO that get the Many-to-Many data class
    fun getAllStudentWithCourse(): LiveData<List<StudentWithCourse>> = studentDao.getAllStudentWithCourse()


}