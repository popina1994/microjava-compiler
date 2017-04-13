package unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

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
	
	private static String [] TEST_MICRO_JAVA_LEXER  = new String[]{	
		"MJLexerTest1.mj"
	};
	
	private static final String[] TEST_MICRO_JAVA_CUP = new String[]{};
	private static void addPath(String path, String[] tests)
	{
		for (int idx = 0; idx < tests.length; idx ++)
		{
			tests[idx] = path + tests[idx];
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
	
	public static void main(String[] args)
	{

		
		
		
	}

	@Override
	protected List<String> argumentsListUnitTestRun(int unitTest) {
		return null;
	}

	@Override
	protected List<String> argumentsListUnitTestBuild() {
		LinkedList listArgs = new LinkedList();
		listArgs.addLast(CMD_JAVA_RUN_MAIN);
		listArgs.addLast(PATH_JFLEX_JAR);
		listArgs.addLast(CMD_JFLEX_OUTPUT_DIRECTORY);
		listArgs.addLast(PATH_SRC);
		listArgs.addLast(PATH_MICRO_JAVA_FLEX);
		return listArgs;
	}

  
	
}
