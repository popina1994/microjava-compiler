package unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public abstract class UnitTest {
	protected static final String PATH_JAVA_BIN = "C:/Program Files/Java/jdk1.8.0_111/bin";
	protected static final String PATH_JAVA_COMPILE = PATH_JAVA_BIN + "/javac.exe";
	protected static final String PATH_JAVA_EXE = PATH_JAVA_BIN + "/java.exe";
	protected static final String CMD_JAVA_RUN_MAIN = "-jar";
	protected static final String CMD_JAVA_RUN_NOT_MAIN = "-cp";
	
	protected static final String PATH_ECLIPSE_ROOT  = "C:/pp1 domaci/workspace/MJCompiler";
	protected static final String PATH_PROJECT_PACKAGE= "rs/ac/bg/etf/pp1";
	
	protected static final String PATH_TEST = PATH_ECLIPSE_ROOT + "/test";
	protected static final String PATH_SPEC = PATH_ECLIPSE_ROOT + "/spec";
	protected static final String PATH_LIB = PATH_ECLIPSE_ROOT + "/lib";
	protected static final String PATH_BIN = PATH_ECLIPSE_ROOT + "/bin";
	protected static final String CLASS_PATH = PATH_BIN + ";" + PATH_LIB + "/*";
	protected static final String PATH_SRC = PATH_ECLIPSE_ROOT + "/src/" + PATH_PROJECT_PACKAGE;
	
	
	
	private String name;
	
	public UnitTest(String name)
	{
		this.name = name;
	}
	
	// TODO : Refactor executeTest and executeBuild into one method.
	//
	
	
	public void executeTest(int unitTestNum)
	{
		LinkedList<String> listArgs = null;
		listArgs = argumentsListUnitTestRun(unitTestNum);
		ProcessBuilder processBuilder = new ProcessBuilder(listArgs);
		Process process;
		try {
			processBuilder.redirectErrorStream(true);
			process = processBuilder.start();
			
			int errCode = process.waitFor();
			
			if (errCode != 0)
			{
				System.out.println("Greska pri pokretanju testa " + unitTestNum + " za " + name);
			}
			System.out.println("JAVA C" + output(process.getInputStream()));
		
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

	
	public void buildCode()
	{
		LinkedList<String> listArg = null;
		listArg = argumentsListUnitTestBuild();
		
		ProcessBuilder processBuilder = new ProcessBuilder(listArg);
		Process process;
		try {
			processBuilder.redirectErrorStream(true);
			process = processBuilder.start();
			
			int errCode = process.waitFor();
			
			if (errCode != 0)
			{
				System.out.println("Greska pri kompajliranju koda za " + name);
			} 
			System.out.println("Kompajliranje "+ name + output(process.getInputStream()));
		
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static String output(InputStream inputStream)
	{
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while  ((line = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(line + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stringBuilder.toString();
		
	}
	
	protected abstract LinkedList<String> argumentsListUnitTestRun(int unitTestNum);
	protected abstract LinkedList<String> argumentsListUnitTestBuild();
	
}
