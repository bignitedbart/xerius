package utils;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;

/**
 * Created by cleenma on 4/10/2017.
 */

public class MongoCommands extends Mongo {

    public void connectToDB(String url, String databaseName, String collectionName){
        MongoClient mongo = new MongoClient(url);
        DB db = (DB) mongo.getDatabase(databaseName);

        DBCollection table = db.getCollection(collectionName);
    }

    public void findEverythingInCollection(MongoCollection selectedCollection){
        DBCursor cursor = (DBCursor) selectedCollection.find();
        System.out.println(cursor.next());
    }

    public void findValueInCollection(MongoCollection selectedCollection, BasicDBObject query){
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("number", 5);
        DBCursor cursor = (DBCursor) selectedCollection.find(whereQuery);
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }



}