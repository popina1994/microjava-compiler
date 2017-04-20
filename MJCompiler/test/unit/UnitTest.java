package unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import utility.Utility;

import java.nio.file.*;


public abstract class UnitTest {
	
	// CHANGE!!!
	//
	protected static final String PATH_JAVA_BIN = "C:/Program Files/Java/jdk1.8.0_111/bin";
	protected static final String PATH_JAVA_COMPILE = PATH_JAVA_BIN + "/javac.exe";
	protected static final String PATH_JAVA_EXE = PATH_JAVA_BIN + "/java.exe";
	
	protected static final String FLAG_JAVA_RUN_MAIN = "-jar";
	protected static final String FLAG_JAVA_RUN_NOT_MAIN = "-cp";
	protected static final String FLAG_JAVA_COMPILE_CLASS_PATH= "-cp";
	protected static final String FLAG_JAVA_COMPILE_OUTPUT_DIRECTORY = "-d";
	
	// CHANGE!!!
	//
	protected static final String PATH_ECLIPSE_ROOT  = "C:/pp1 domaci/workspace/MJCompiler";
	protected static final String PATH_PROJECT_PACKAGE= "rs/ac/bg/etf/pp1";
	
	protected static final String PATH_TEST = PATH_ECLIPSE_ROOT + "/test";
	protected static final String PATH_SPEC = PATH_ECLIPSE_ROOT + "/spec";
	protected static final String PATH_LIB = PATH_ECLIPSE_ROOT + "/lib";
	protected static final String PATH_BIN = PATH_ECLIPSE_ROOT + "/bin";
	protected static final String PATH_SRC = PATH_ECLIPSE_ROOT + "/src";
	protected static final String PATH_CONFIG = PATH_ECLIPSE_ROOT + "/config";
	protected static final String PATH_LIB_CLASS_PATH = PATH_LIB + "/*";
	
	protected static final String CLASS_PATH = PATH_BIN + ";" + PATH_LIB_CLASS_PATH;
	protected static final String PATH_SRC_PACKAGE = PATH_SRC + "/" + PATH_PROJECT_PACKAGE;
	protected static final String PATH_BIN_PACKAGE = PATH_BIN + "/" + PATH_PROJECT_PACKAGE;
	protected static final String PATH_LOG4J_SRC = PATH_SRC_PACKAGE + "/util/Log4JUtils.java";
	protected static final String LOG4J_CONFIG_NAME = "log4j.xml";
	protected static final String PATH_LOG4J_CONFIG = PATH_CONFIG + "/" + LOG4J_CONFIG_NAME;
	private static final int EXIT_CODE_UNIT_TEST_FAILED = -1;

	public static final int RUN_ALL_TESTS = -1;
	
	private String name;
	
	public UnitTest(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	static {
		LinkedList <String> listArgsBuild = listArgPreInitializationBuild();
		executeCommand(listArgsBuild);
		try {
			Files.copy(Paths.get(PATH_LOG4J_CONFIG), 
					Paths.get(PATH_BIN + "/" + LOG4J_CONFIG_NAME), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected static void addPath(String path, String[] tests)
	{
		for (int idx = 0; idx < tests.length; idx ++)
		{
			tests[idx] = path + "/" + tests[idx];
		}
	}
	
	
	private static LinkedList<String> listArgPreInitializationBuild()
	{
		LinkedList<String> listArgs = new LinkedList<>();
		
		listArgs.addLast(PATH_JAVA_COMPILE);
		listArgs.addLast(FLAG_JAVA_COMPILE_CLASS_PATH);
		listArgs.addLast(PATH_LIB_CLASS_PATH);
		
		listArgs.addLast(PATH_LOG4J_SRC);
		// Where to store built java files.
		//
		listArgs.addLast(FLAG_JAVA_COMPILE_OUTPUT_DIRECTORY);
		listArgs.addLast(PATH_BIN);
		return listArgs;
	}
	
	private static UnitTestResult executeCommand(LinkedList<String> cmdAndArgs)
	{
		ProcessBuilder processBuilder = new ProcessBuilder(cmdAndArgs).inheritIO();
		Process process;
		
		try {
			processBuilder.redirectErrorStream(true);

			process = processBuilder.start();
			int errCode = process.waitFor();
			
			//System.out.println(output(process.getInputStream()));
			
			if (errCode != 0)
			{
				if (errCode != EXIT_CODE_UNIT_TEST_FAILED)
				{
					UnitTestResult unitTestResult = new UnitTestResult(false, UnitTestResult.ERROR_CODE_PROGRAM);
					unitTestResult.setExitCode(errCode);
					return unitTestResult;
				}
				else 
				{
					UnitTestResult unitTestResult = new UnitTestResult(false, UnitTestResult.ERROR_CODE_UNIT_TEST);
					return unitTestResult;
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
			return new UnitTestResult(false, UnitTestResult.ERROR_CODE_SYSTEM);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			return new UnitTestResult(false, UnitTestResult.ERROR_CODE_SYSTEM);
		}
		return new UnitTestResult(true, UnitTestResult.OK);
	}
	
	
	public UnitTestResult executeTest(int unitTestNum)
	{
		LinkedList<String> listArgs = null;
		listArgs = argumentsListUnitTestRun(unitTestNum);
		
		Utility.println(System.out, Utility.BLUE, "EXECUTING UNIT TEST NUMBER " + unitTestNum); 
		return executeCommand(listArgs);
		
	}
	
	public UnitTestResult generateSouorceCodeFromSpec()
	{
		LinkedList<String> listArgs = null;
		listArgs = argumentsListSourceCodeGenerate();
		Utility.println(System.out, Utility.BLUE, "GENERATING SOURCE CODE " + name);
		return executeCommand(listArgs);
	}
	


	public UnitTestResult buildSourceCodeImplementation()
	{
		LinkedList<String> listArgs = null;
		listArgs = argumentsListSourceCodeBuild();
		Utility.println(System.out, Utility.BLUE, "COMPILING SOURCE CODE " + name);
		return executeCommand(listArgs);
	}
	
	public UnitTestResult buildUnitTestEnviroment()
	{
		LinkedList<String> listArgs = null;
		listArgs = argumentsListUnitTestBuild();
		Utility.println(System.out, Utility.BLUE, "COMPILING SOURCE CODE OF UNIT TEST ENVIROMENT "  + name);
		return executeCommand(listArgs);
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
	protected abstract LinkedList<String> argumentsListSourceCodeBuild();
	
	
	protected abstract LinkedList<String> argumentsListSourceCodeGenerate();

	
	
}
