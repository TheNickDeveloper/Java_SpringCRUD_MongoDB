package com.nick.Service;

import com.nick.Dao.IStudentDao;
import com.nick.Dao.StudentDaoImp;
import com.nick.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

//Business logic
@Service
public class StudentService {

    @Autowired
    @Qualifier("MongoData")
    private IStudentDao studentDaoImp;

    public Collection<Student> getAllStudents(){
        return this.studentDaoImp.getAllStudents();
    }

    // todo, add authentication
    public Student getStudentById(int id){
        return studentDaoImp.getStudentById(id);
    }

    // todo, add authentication
    public void removeStudentById(int id) {
        this.studentDaoImp.removeStudentById(id);
    }

    // todo, add authentication
    public void updateStudent(Student student){
        this.studentDaoImp.updateStudent(student);
    }

    // todo, add authentication
    public void insertStudent(Student student) {
        this.studentDaoImp.insertStudent(student);
    }
}
