package com.nick.Dao;

import com.mongodb.*;
import com.nick.Entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("MongoData")
public class MongoStudentDaoImp implements IStudentDao {

    private static Map<Integer, Student> students = new HashMap<Integer, Student>();

    public MongoStudentDaoImp() throws UnknownHostException {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("School");

        DBCollection coll = db.getCollection("Student");
        DBCursor cur = coll.find();
        while (cur.hasNext()){
             BasicDBObject obj = (BasicDBObject) cur.next();
             students.put(obj.getInt("id"), new Student(obj.getInt("id"),
                     obj.getString("name"),
                     obj.getString("course")));
        }
    }

    @Override
    public Collection<Student> getAllStudents(){
        return this.students.values();
    }

    @Override
    public Student getStudentById(int id){
        return this.students.get(id);
    }

    @Override
    public void removeStudentById(int id) {
        this.students.remove(id);
    }

    @Override
    public void updateStudent(Student student){
        Student s = students.get(student.getId());
        s.setCourse(student.getCourse());
        s.setName(student.getName());
        this.students.put(s.getId(),student);
    }

    @Override
    public void insertStudent(Student student) {
        this.students.put(student.getId(),student);
    }
}
