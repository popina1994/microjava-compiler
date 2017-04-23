package unit;

import java.util.LinkedList;

public class UnitTestSemantic extends UnitTestParser {
	protected static  String [] TEST_MICRO_JAVA_PARSER_SEMANTIC = new String[]{	
			"MJSemanticTest1.mj",
			"MJSemanticTest2.mj"
	};
	private static final String PATH_TEST_SEMANTIC_RUN = PATH_TEST + "/parser/semantic";
	

	static 
	{
		addPath(PATH_TEST_SEMANTIC_RUN, TEST_MICRO_JAVA_PARSER_SEMANTIC);
	}
	
	
	public UnitTestSemantic(String name) {
		super(name);
	}

	@Override
	protected LinkedList<String> argumentsListUnitTestRun(int unitTestNum) {
		LinkedList<String> listArgs = new LinkedList<>();
		listArgs.addLast(PATH_JAVA_EXE);
		listArgs.addLast(FLAG_JAVA_RUN_NOT_MAIN);
		listArgs.addLast(CLASS_PATH);
		listArgs.addLast(PATH_TEST_UNIT_PROGRAM);
		listArgs.addLast(TEST_MICRO_JAVA_PARSER_SEMANTIC[unitTestNum]);
		listArgs.add("false"/*isSyntax*/);
		return listArgs;
	}

}
