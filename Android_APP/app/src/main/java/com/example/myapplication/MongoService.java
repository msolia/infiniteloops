package com.example.myapplication;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


/**
 * Created by madsoliy on 1/22/2015.
 */
public class MongoService {

    public boolean softLogin(String us,String pass) {

        try {
            MongoClient mclient = new MongoClient("10.132.162.76", 27017);

            DB db = mclient.getDB("tempdb");

            DBCollection dbcollection = db.getCollection("users");
            DBObject obj = null;
            obj = dbcollection.findOne(new BasicDBObject("username",us).append("password",pass));

            //If user available
            if(!obj.equals(null))
            {
                return true;
            }

        }catch (Exception e)
        {
                return false;
        }
            return false;
    }
}
