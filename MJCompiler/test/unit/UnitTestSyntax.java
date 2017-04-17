package unit;

import java.util.LinkedList;

public class UnitTestSyntax extends UnitTestParser {
	
	protected static  String [] TEST_MICRO_JAVA_PARSER_SYNTAX = new String[]{	
			"MJSyntaxTest1.mj",
			"MJSyntaxTest2.mj"
	};
	private static final String PATH_TEST_LEXER_RUN = PATH_TEST + "/parser/syntax";
	
	private static void addPath(String path, String[] tests)
	{
		for (int idx = 0; idx < tests.length; idx ++)
		{
			tests[idx] = path + "/" + tests[idx];
		}
	}
	
	public static void initTest()
	{
		addPath(PATH_TEST_LEXER_RUN, TEST_MICRO_JAVA_PARSER_SYNTAX);
	}
	
	public UnitTestSyntax(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LinkedList<String> argumentsListUnitTestRun(int unitTestNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
