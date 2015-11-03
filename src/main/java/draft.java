





public class draft {

	public static void main(String[] args) {
		System.setProperty("file.encoding","UTF-8");
		
		for(int i=0;i<256;i++){
			System.out.print((char)i);
			System.out.print((char)((i*15)%256));
			System.out.println(i);
		}

	}

}
