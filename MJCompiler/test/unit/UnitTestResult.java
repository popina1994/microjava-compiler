package unit;

public class UnitTestResult extends Throwable{
	private final boolean successful;
	private  int typeError;
	// Only in case typetypeError = 1;
	//
	private int exitCode;
	static final int OK = 0;
	static final int ERROR_CODE_PROGRAM = 1;
	static final int ERROR_CODE_UNIT_TEST = 2;
	static final int ERROR_CODE_BUILD_SOURCE_CODE = 3;
	static final int ERROR_CODE_BUILD_UNIT_TEST_SOURCE_CODE = 4;
	static final int ERROR_CODE_SYSTEM = 4;
	
	private static final String ERROR_MESSAGE[] = new String[] {
				  "Result of unit test is correct", 
				  "Program failed to finish, please check if paths are correct",
				  "Unit test executed, but result is not correct",
				  "Build of source code failed",
				  "Build of unit test source code failed",
				  "System error"};
	
	
	
	public UnitTestResult(boolean successful, int typeError) {
		this.successful = successful;
		this.typeError = typeError;
	}

	public boolean isSuccessful() {
		return successful;
	}
	
	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}
	
	

	public int getTypeError() {
		return typeError;
	}


	String getError()
	{
		return ERROR_MESSAGE[typeError];
	}
	
	
}
