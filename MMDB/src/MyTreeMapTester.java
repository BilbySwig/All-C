

public class MyTreeMapTester {

	public static void main(String[] args) {
		MyTreeMap mappy = new MyTreeMap();
		mappy.put("keyA","A1");
		mappy.put("keyA","A2");
		mappy.put("keyA","A3");
		mappy.put("keyA","A4");
		mappy.put("keyB","B1");
		mappy.put("keyB","B2");
		mappy.put("keyC","C1");
		mappy.put("keyC","C2");
		mappy.put("keyC","C3");
		mappy.put("keyA","A5");
		
		if(mappy.isEmpty())
			System.out.println("bad stuff");
		
		System.out.println(mappy);
		
		for(Comparable word: mappy.keyList())
			System.out.println(word+"-->"+mappy.get(word));
		

		

	}

}
