package unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import utility.*;;

public class UnitTestLexer extends UnitTest {
	protected UnitTestLexer(String name) {
		super(name);
	}
	
	private static final String PATH_JFLEX_JAR = PATH_LIB + "/jflex.jar";
	private static final String FLAG_JFLEX_OUTPUT_DIRECTORY = "-d";
	
	private static final String PATH_TEST_LEXER_RUN = PATH_TEST + "/lexer";
	private static final String PATH_MICRO_JAVA_FLEX = PATH_SPEC + "/mjlexer.flex";
	
	private static final String PATH_TEST_UNIT_PROGRAM = PATH_PROJECT_PACKAGE + "/MJLexerTest";
	
	protected static final String[] JFLEX_GENERATED = new String[]{	
			"Yylex.java",
			"sym.java"
			};
	
	
	protected static final String [] TEST_MICRO_JAVA_LEXER  = new String[]{	
		"MJLexerTest1.mj",
		"MJLexerTest2.mj"
	};
	
	private static void addPath(String path, String[] tests)
	{
		for (int idx = 0; idx < tests.length; idx ++)
		{
			tests[idx] = path + "/" + tests[idx];
		}
	}
	
	public static void initTest()
	{
		addPath(PATH_TEST_LEXER_RUN, TEST_MICRO_JAVA_LEXER);
	}
	static {
		initTest();
	}

	@Override
	protected LinkedList<String> argumentsListUnitTestRun(int unitTest) {
		
		
		LinkedList<String> listArgs = new LinkedList<>();
		listArgs.addLast(PATH_JAVA_EXE);
		listArgs.addLast(FLAG_JAVA_RUN_NOT_MAIN);
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
		listArgs.addLast(PATH_JAVA_COMPILE);
		listArgs.addLast(FLAG_JAVA_COMPILE_CLASS_PATH);
		listArgs.addLast(CLASS_PATH);
		
		listArgs.addLast(PATH_TEST + "/" + PATH_TEST_UNIT_PROGRAM + ".java");
		
		// Where to store built java files.
		//
		listArgs.addLast(FLAG_JAVA_COMPILE_OUTPUT_DIRECTORY);
		listArgs.addLast(PATH_BIN);
		return listArgs;
	}

	@Override
	protected LinkedList<String> argumentsListSourceCodeBuild() {
		LinkedList<String> listArgs = new LinkedList<>();
		// Java run
		//
		listArgs.addLast(PATH_JAVA_COMPILE);
		listArgs.addLast(FLAG_JAVA_COMPILE_CLASS_PATH);
		listArgs.addLast(PATH_LIB_CLASS_PATH);
		
		for (String it : JFLEX_GENERATED)
		{
			listArgs.add(PATH_SRC_PACKAGE + "/" + it);
		}
		
		// Where to store built java files.
		//
		listArgs.addLast(FLAG_JAVA_COMPILE_OUTPUT_DIRECTORY);
		listArgs.addLast(PATH_BIN);
		return listArgs;
	}

	@Override
	protected LinkedList<String> argumentsListSourceCodeGenerate() {
		LinkedList<String> listArgs = new LinkedList<>();
		// Java run
		//
		listArgs.addLast(PATH_JAVA_EXE);
		listArgs.addLast(FLAG_JAVA_RUN_MAIN);
		listArgs.addLast(PATH_JFLEX_JAR);
		// Where to store built java files.
		//
		listArgs.addLast(FLAG_JFLEX_OUTPUT_DIRECTORY);
		listArgs.addLast(PATH_SRC_PACKAGE);
		// JFLEX specification file of MJ compiler.
		//
		listArgs.addLast(PATH_MICRO_JAVA_FLEX);
		return listArgs;
	}
	

  
	
}
