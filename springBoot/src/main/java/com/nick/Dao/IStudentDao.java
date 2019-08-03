package com.nick.Dao;

import com.nick.Entity.Student;

import java.util.Collection;

public interface IStudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentById(int id);

    void updateStudent(Student student);

    void insertStudent(Student student);
}
