package unit;

import java.util.LinkedList;

public class UnitTestCodeGenerator extends UnitTest {
	
	private static final String PATH_TEST_UNIT_PROGRAM = PATH_PROJECT_PACKAGE + 
														"/MJCodeGeneratorTest";
	public static final String[] TEST_MICRO_JAVA_CODE_GENERATOR_INPUT = new String[]
			{
					"MJCodeGenerator1.mj",
					"MJCodeGenerator2.mj"
			};
	public static final String[] TEST_MICRO_JAVA_CODE_GENERATOR_OUTPUT = new String[]
			{
					"MJCodeGenerator1.mj",
					"MJCodeGenerator2.mj"
			};
	
	public static final String[] TEST_MICRO_JAVA_CODE_GENERATOR_TEST = new String[]
			{
					"MJCodeGenerator1.mj",
					"MJCodeGenerator2.mj"
			};
	
	public UnitTestCodeGenerator(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LinkedList<String> argumentsListUnitTestRun(int unitTestNum) {
		LinkedList<String> listArgs = new LinkedList<>();
		listArgs.addLast(PATH_JAVA_EXE);
		listArgs.addLast(FLAG_JAVA_RUN_NOT_MAIN);
		listArgs.addLast(CLASS_PATH);
		listArgs.addLast(PATH_TEST_UNIT_PROGRAM);
		listArgs.addLast(TEST_MICRO_JAVA_CODE_GENERATOR_INPUT[unitTestNum]);
		listArgs.add("true"/*isSyntax*/);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected LinkedList<String> argumentsListSourceCodeGenerate() {
		// TODO Auto-generated method stub
		return null;
	}

}
