import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database db = new Database();
		db.connect();
		
		System.out.println("기본 동작 테스트");
		db.run();
		System.out.println("기본 동작 테스트 종료");

		//한 컬럼 찾기
		System.out.println("Find() with Collection name, key, value");
		System.out.println(db.find("User",  "name",  "송수연"));
		
		//모든 컬럼 찾기
		System.out.println("Find() with collection name");
		DBObject acursor = db.find("User", "id", "123");
		System.out.println(acursor);
		
		DBCursor cursor = db.find("User");
		System.out.println("모든 유저의 목록");
		DBObject tempDBObj;
		try {
			while (cursor.hasNext()) {
				tempDBObj = cursor.next();
				System.out.println("-"+tempDBObj.get("id")+tempDBObj.get("name"));
			}
		} finally {
			cursor.close();
		}
		
		//insert obj
		System.out.println("insert test");
		
		String collectionName = "User";
		//User,Room
		
		//BasicDBObject tempBasicDBObj = new BasicDBObject("id", "qaz321")
		//		.append("password", "qazxsw12")
		//		.append("name", "박현영")
		//		.append("icon", "3");		//캐릭터 아이콘 [0,7]
		//db.insert(collectionName,  tempBasicDBObj);
		
		//updated list
		cursor = db.find(collectionName);
		System.out.println("업데이트된 유저 목록");
		try {
			while (cursor.hasNext()) {
				System.out.println("-"+cursor.next().get("name"));
			}
		} finally {
			cursor.close();
		}	

	}

}
