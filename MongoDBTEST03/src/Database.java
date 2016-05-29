import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Database {	
	private String dbName = null;
	private String textUri = null;
	
	private MongoClientURI uri = null;
	private MongoClient mongoClient = null;
	private DB db = null;

	public Database() {
		this.textUri = "mongodb://user1:123@ds011863.mlab.com:11863/?authSource=battle_rain";
		this.dbName = "battle_rain";
	}
	
	@SuppressWarnings("deprecation")
	public void connect() {
		this.uri = new MongoClientURI(textUri);
		this.mongoClient = new MongoClient(uri);
		this.db = mongoClient.getDB(dbName);
	}
	
	//��� ��� �ҷ�����
	public DBCursor find(String collection){
		DBCollection coll = db.getCollection(collection);
		DBCursor myDoc = coll.find();		
		return myDoc;
	}
	
	//�α���, �� ������ ���� �÷�ã��
	public DBObject find(String collection, String key, String value){
		BasicDBObject query = new BasicDBObject(key, value);
		DBCollection coll = db.getCollection(collection);
		DBObject result = coll.findOne(query);
		return result;
	}
	
	//collection�� obj����
	//collection�� �����Ѵٸ� �ű⼭ ���ǵ� �Ͱ� �ٸ��� �Ƹ� exception
	//���ٸ� ���� ������
	public void insert(String collection, BasicDBObject obj){
		DBCollection coll = db.getCollection(collection);
		coll.insert(obj);
	}

	//test
	public void run() {
		DBCollection coll = db.getCollection("User");
		DBObject myDoc = coll.findOne();
		//�÷� ���
		System.out.println(myDoc);
		//�÷� value ���
		System.out.println(myDoc.get("name"));
		//��� �÷� ���
		DBCursor myCursor = coll.find();
		try {
			while (myCursor.hasNext()) {
				System.out.println(myCursor.next());
			}
		} finally {
			myCursor.close();
		}
	}

}
