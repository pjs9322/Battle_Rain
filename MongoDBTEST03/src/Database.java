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
	
	//모든 목록 불러오기
	public DBCursor find(String collection){
		DBCollection coll = db.getCollection(collection);
		DBCursor myDoc = coll.find();		
		return myDoc;
	}
	
	//로그인, 방 접속을 위한 컬럼찾기
	public DBObject find(String collection, String key, String value){
		BasicDBObject query = new BasicDBObject(key, value);
		DBCollection coll = db.getCollection(collection);
		DBObject result = coll.findOne(query);
		return result;
	}
	
	//collection에 obj삽입
	//collection이 존재한다면 거기서 정의된 것과 다르면 아마 exception
	//없다면 새로 생성됨
	public void insert(String collection, BasicDBObject obj){
		DBCollection coll = db.getCollection(collection);
		coll.insert(obj);
	}

	//test
	public void run() {
		DBCollection coll = db.getCollection("User");
		DBObject myDoc = coll.findOne();
		//컬럼 출력
		System.out.println(myDoc);
		//컬럼 value 출력
		System.out.println(myDoc.get("name"));
		//모든 컬럼 출력
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
