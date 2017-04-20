package unit;

import java.util.LinkedList;

public class UnitTestSyntax extends UnitTestParser {
	
	protected static  String [] TEST_MICRO_JAVA_PARSER_SYNTAX = new String[]{	
			"MJSyntaxTest1.mj",
			"MJSyntaxTest2.mj"
	};
	private static final String PATH_TEST_SYNTAX_RUN = PATH_TEST + "/parser/syntax";
	

	static 
	{
		addPath(PATH_TEST_SYNTAX_RUN, TEST_MICRO_JAVA_PARSER_SYNTAX);
	}
	
	public UnitTestSyntax(String name) {
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
		listArgs.addLast(TEST_MICRO_JAVA_PARSER_SYNTAX[unitTestNum]);
		listArgs.add("true"/*isSyntax*/);
		return listArgs;

	}

}
