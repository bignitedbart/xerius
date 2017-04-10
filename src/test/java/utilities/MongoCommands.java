package utilities;

import com.mongodb.*;

/**
 * Created by cleenma on 4/10/2017.
 */
public class MongoCommands {

    public DBCollection connectToMongoDB(String URL, String databaseName, String collectionName){
        MongoClient mongo = new MongoClient(URL);
        DB db = (DB) mongo.getDatabase(databaseName);

        DBCollection table = db.getCollection(collectionName);
        return table;
    }

    public void findFirstRecord(DBObject value, DBCollection table){
        DBObject dbObject = table.findOne(value);
        System.out.println(dbObject);
    }

    public void findAllValue(DBObject value, DBCollection table){
        DBCursor cursor = table.find(value);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    public void findSpecificField(DBObject fieldName, DBObject value, DBCollection table){
        DBCursor cursor = table.find(value, fieldName);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

}
