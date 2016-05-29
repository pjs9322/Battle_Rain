import com.mongodb.DBCursor;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database db = new Database();
		db.connect();
		
		DBCursor word = null;
		for (int i = 0; i < (int) Math.random()*5887; i++) {
			word = db.find("word");
		}
		System.out.println(word);
	}

}
