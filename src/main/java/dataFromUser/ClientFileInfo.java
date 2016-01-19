package dataFromUser;

import java.util.Scanner;

import encryptionInfo.FileInfo;

public class ClientFileInfo implements ClientData{
	
	public static FileInfo getData(){
		FileInfo fi=new FileInfo();
		Scanner user_input=new Scanner(System.in);
		System.out.println(showFileORDirec());
		String s=user_input.next();
		if(s.equals("1")){
			fi.setFord("File");
			System.out.println(showFileName());
			String fileName=user_input.next();
			fi.setFileN(fileName.substring(fileName.
					lastIndexOf('\\'),fileName.length()));
			fi.setDireN(fileName.substring(0, fileName.
					lastIndexOf('\\')));
		}else if(s.equals("2")){
			fi.setFord("Directory");
			fi.setFileN(null);
			System.out.println(showSourceDirectory());
			String direcName=user_input.next();
			fi.setDireN(direcName);
		}
		
		System.out.println(showKeyPath());
		String s1=user_input.next();
		fi.setKeyPath(s1);
		user_input.close();
		
		return fi;
	}
	
	public static String showFileORDirec() {
		String FileORDirec="This is an encryption algorithm please type 1 for "
				+ "operation one single file and 2 for operation on whole folder: ";
		return FileORDirec;
	}

	public static String showSourceDirectory() {
		String SourceDirectory="Please insert the directory path: ";
		return SourceDirectory;
	}
	
	public static String showKeyPath() {
		String KeyPath="Please insert the key path: ";
		return KeyPath;
	}

	public static String showFileName() {
		String FileName="Please insert the file path: ";
		return FileName;
	}
}
