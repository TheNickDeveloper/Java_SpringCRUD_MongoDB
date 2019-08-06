package com.nick.Dao;

import com.mongodb.*;
import com.nick.Entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Repository
@Qualifier("MongoData")
public class MongoStudentDaoImp implements IStudentDao {

    private static Map sudents;

    public MongoStudentDaoImp() throws UnknownHostException {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("School");

        DBCollection coll = db.getCollection("Student");
        DBCursor cur = coll.find();
        int count = 1;
        while (cur.hasNext()){
             BasicDBObject obj = (BasicDBObject) cur.next();

             //todo, cannot move to next for some reason, may need to check
             sudents.put(count, new Student(obj.getInt("id")
                     ,obj.getString("name")
                     ,obj.getString("course")));
             count++;
        }
    }

    private DBObject createDBObject(Student student) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", student.getId());
        docBuilder.append("name", student.getName());
        docBuilder.append("course", student.getCourse());
        return docBuilder.get();
    }


    @Override
    public Collection<Student> getAllStudents() {
        return new ArrayList<Student>(){
            {
                add(new Student(1,"Mario","nothing"));
            }
        };
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public void removeStudentById(int id) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void insertStudent(Student student) {

    }
}
