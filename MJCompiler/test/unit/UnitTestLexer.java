package unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

// MJLexerTest1.mj

public class UnitTestLexer extends UnitTest {
	public UnitTestLexer() {
		super(TEST_NAME);
		// TODO Auto-generated constructor stub
	}

	private static final String TEST_NAME = "Lexer";
	private static final String PATH_JFLEX_JAR = PATH_LIB + "/jflex.jar";
	private static final String CMD_JFLEX_OUTPUT_DIRECTORY = "-d";
	
	private static final String PATH_TEST_LEXER_RUN = PATH_TEST + "/lexer";
	private static final String PATH_MICRO_JAVA_FLEX = PATH_SPEC + "/mjlexer.flex";
	
	private static final String PATH_TEST_UNIT_PROGRAM = PATH_PROJECT_PACKAGE + "/MJLexerTest";
	
	private static String [] TEST_MICRO_JAVA_LEXER  = new String[]{	
		"MJLexerTest1.mj"
	};
	
	private static void addPath(String path, String[] tests)
	{
		for (int idx = 0; idx < tests.length; idx ++)
		{
			tests[idx] = path + "/" + tests[idx];
		}
	}
	
	private static final int RETURN_VALUE_OK = 0;
	
	public static void initTest()
	{
		addPath(PATH_TEST_LEXER_RUN, TEST_MICRO_JAVA_LEXER);
	}
	static {
		initTest();
		for (String s : TEST_MICRO_JAVA_LEXER)
		{
			System.out.println(s);
		}
	}

	@Override
	protected LinkedList<String> argumentsListUnitTestRun(int unitTest) {
		
		
		LinkedList<String> listArgs = new LinkedList<>();
		listArgs.addLast(PATH_JAVA_EXE);
		listArgs.addLast(CMD_JAVA_RUN_NOT_MAIN);
		listArgs.addLast(CLASS_PATH);
		listArgs.addLast(PATH_TEST_UNIT_PROGRAM);
		listArgs.addLast(TEST_MICRO_JAVA_LEXER[unitTest]);
		return listArgs;
	}

	@Override
	protected LinkedList<String> argumentsListUnitTestBuild() {
		LinkedList<String> listArgs = new LinkedList<>();
		// Java run
		//
		listArgs.addLast(PATH_JAVA_EXE);
		listArgs.addLast(CMD_JAVA_RUN_MAIN);
		listArgs.addLast(PATH_JFLEX_JAR);
		// Where to store built java files.
		//
		listArgs.addLast(CMD_JFLEX_OUTPUT_DIRECTORY);
		listArgs.addLast(PATH_SRC);
		// JFLEX specification file of MJ compiler.
		//
		listArgs.addLast(PATH_MICRO_JAVA_FLEX);
		return listArgs;
	}

  
	
}
