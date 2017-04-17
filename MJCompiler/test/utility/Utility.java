package utility;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;

public class Utility {
	private static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";
	
	public static void println(PrintStream printStream, String color, String outputStr)
	{
		printStream.println(color + outputStr + RESET);
	}
	
	public static String[] listFiles(String path)
	{
		File f = new File(path);
		String[] listFiles = f.list(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				String nameFull = dir.getAbsolutePath() + name; 
				File f = new File(nameFull);
				return f.isFile();
			}
			
		});
		return listFiles;
	}
}
