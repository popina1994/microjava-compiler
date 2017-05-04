package unit;

import java.util.LinkedList;

public class UnitTestCodeGenerator extends UnitTest {
	
	private static final String PATH_TEST_UNIT_PROGRAM = PATH_PROJECT_PACKAGE + 
														"/MJCodeGeneratorTest";
	private static final String PACKAGE_CODE_GENERATOR_RUN = "rs.etf.pp1.mj.runtime.Run";
	public static final String[] TEST_MICRO_JAVA_CODE_GENERATOR_INPUT = new String[]
			{
					"A/Input/MJCodeGenerator1.mj",
					"A/Input/MJCodeGenerator2.mj",
					"A/Input/MJCodeGenerator3.mj",
					"A/Input/MJCodeGenerator4.mj",
					"A/Input/test301.mj"
			};
	public static final String[] TEST_MICRO_JAVA_CODE_GENERATOR_OUTPUT = new String[]
			{
					"A/Output/MJCodeGenerator1",
					"A/Output/MJCodeGenerator2",
					"A/Output/MJCodeGenerator3",
					"A/Output/MJCodeGenerator4",
					"A/Output/test301.mj"
			};
	
	public static final String[] TEST_MICRO_JAVA_CODE_GENERATOR_TEST = new String[]
			{
					"A/Output/MJCodeGenerator1.test",
					"A/Output/MJCodeGenerator2.test",
					"A/Output/MJCodeGenerator3.test",
					"A/Output/MJCodeGenerator4.test",
					"A/Output/test301.test"
			};
	
	private static final String PATH_TEST_CODE_GENERATOR_RUN = PATH_TEST + "/generator";
	
	static {
		addPath(PATH_TEST_CODE_GENERATOR_RUN, TEST_MICRO_JAVA_CODE_GENERATOR_INPUT);
		addPath(PATH_TEST_CODE_GENERATOR_RUN, TEST_MICRO_JAVA_CODE_GENERATOR_OUTPUT);
		addPath(PATH_TEST_CODE_GENERATOR_RUN, TEST_MICRO_JAVA_CODE_GENERATOR_TEST);
	}
	
	public static final String EXT_OBJECT = ".obj";
	public static final String EXT_OUTPUT = ".out";
	
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
		listArgs.addLast(UnitTestParser.PATH_TEST_UNIT_PROGRAM);
		listArgs.addLast(TEST_MICRO_JAVA_CODE_GENERATOR_INPUT[unitTestNum]);
		listArgs.add("3"/*syntax and semantic*/);
		listArgs.add("false"/*debug*/);
		listArgs.addLast(TEST_MICRO_JAVA_CODE_GENERATOR_OUTPUT[unitTestNum] + EXT_OBJECT);
		return listArgs;
	}
	
	@Override
	protected LinkedList<String> argumentsListAdditionalUnitTest(int unitTestNum)
	{
		LinkedList<String> listArgs = new LinkedList<String>();
		listArgs.addLast(PATH_JAVA_EXE);
		listArgs.addLast(FLAG_JAVA_RUN_NOT_MAIN);
		listArgs.addLast(CLASS_PATH);
		listArgs.addLast(PACKAGE_CODE_GENERATOR_RUN);
		listArgs.addLast(TEST_MICRO_JAVA_CODE_GENERATOR_OUTPUT[unitTestNum] + EXT_OBJECT);
		// Because redirecting to some output is not supported in Code.run.
		//
		listArgs.addLast(TEST_MICRO_JAVA_CODE_GENERATOR_OUTPUT[unitTestNum] + EXT_OUTPUT);
		
		return listArgs;
	}
	
	@Override
	protected LinkedList<String> argumentsListComapreResultUnitTest(int unitTestNum)
	{
		LinkedList<String> listArgs = new LinkedList<String>();
		listArgs.addLast(PATH_JAVA_EXE);
		listArgs.addLast(FLAG_JAVA_RUN_NOT_MAIN);
		listArgs.addLast(CLASS_PATH);
		listArgs.addLast(PATH_TEST_UNIT_PROGRAM);
		listArgs.addLast(TEST_MICRO_JAVA_CODE_GENERATOR_OUTPUT[unitTestNum] + EXT_OUTPUT);
		listArgs.addLast(TEST_MICRO_JAVA_CODE_GENERATOR_TEST[unitTestNum]);
		
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
