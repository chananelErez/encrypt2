import java.io.File;

public class draft {

	public static void main(String[] args) {
		final File folder = new File("C:\\Users\\Public\\Documents\\openingexperiment");
		listFilesForFolder(folder);
		

	}
	
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	    	if(fileEntry.getName().toLowerCase().endsWith(".txt"))
	         System.out.println(fileEntry.getName());
	        }
	    }
	


}
