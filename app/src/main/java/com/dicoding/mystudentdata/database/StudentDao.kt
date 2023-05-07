package com.dicoding.mystudentdata.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student: List<Student>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUniversity(university: List<University>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourse(course: List<Course>)

    // insert new Many-to-Many bridge database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourseStudentCrossRef(courseStudentCrossRef: List<CourseStudentCrossRef>)

    // use RawQuery so it supports ORDER
    @RawQuery(observedEntities = [Student::class])
    fun getAllStudent(query: SupportSQLiteQuery): LiveData<List<Student>>

    // transaction to get StudentAndUniversity data class, from student
    @Transaction
    @Query("SELECT * from student")
    fun getAllStudentAndUniversity(): LiveData<List<StudentAndUniversity>>

    // transaction to get UniversityAndStudent data class, from university
    @Transaction
    @Query("SELECT * from university")
    fun getAllUniversityAndStudent(): LiveData<List<UniversityAndStudent>>

    // transaction to get StudentWithCourse data class, from student
    @Transaction
    @Query("SELECT * from student")
    fun getAllStudentWithCourse(): LiveData<List<StudentWithCourse>>
}