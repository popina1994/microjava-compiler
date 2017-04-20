package unit;

import java.util.LinkedList;

public abstract class UnitTestParser extends UnitTest{
	
	protected static final String PATH_CUP_JAR = PATH_LIB + "/java-cup.jar";
	protected static final String FLAG_CUP_OUTPUT_DIRECTORY = "-destdir";
	protected static final String FLAG_CUP_PARSER = "-parser";
	protected static final String PATH_MICRO_JAVA_CUP = PATH_SPEC + "/mjparser.cup";
	protected static final String PARSER_OUTPUT_NAME = "MJParser";
	protected static final String[] CUP_GENERATED = new String[]
													{
														PARSER_OUTPUT_NAME + ".java",
														"sym.java"
													};
	

	protected static final String PATH_TEST_UNIT_PROGRAM = PATH_PROJECT_PACKAGE + "/MJParserTest";
	
	public UnitTestParser(String name) {
		super(name);
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
		
		for (String it : CUP_GENERATED)
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
		listArgs.addLast(PATH_CUP_JAR);
		// Where to store built java files.
		//
		listArgs.addLast(FLAG_CUP_OUTPUT_DIRECTORY);
		listArgs.addLast(PATH_SRC_PACKAGE);
		// JFLEX specification file of MJ compiler.
		//
		listArgs.addLast(FLAG_CUP_PARSER);
		listArgs.addLast(PARSER_OUTPUT_NAME);
		listArgs.addLast(PATH_MICRO_JAVA_CUP);
		return listArgs;
	}
	
}
