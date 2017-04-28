package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static final int SYNTAX_BIT_TEST = 0x1;
	public static final int SEMATNIC_BIT_TEST = 0x1 << 1;
	
	private static boolean isSyntaxTest(int testType)
	{
		if ( (testType & SYNTAX_BIT_TEST) != 0)
		{
			return true;
		}
		return false;
	}
	
	private static boolean isSemanticTest(int testType)
	{
		if ( (testType & SEMATNIC_BIT_TEST) != 0)
		{
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		
		String objFileName = null;
		String inputFileName = null;
		boolean debug = false;
		boolean isSyntaxTest = false;
		boolean isSemanticTest = false;
		int testType = 3;
		PrintWriter printWriter = null;
		File sourceCode = null;
		
		switch (args.length)
		{
			case 4:
				objFileName = args[3];
			case 3:
				debug = Boolean.parseBoolean(args[2]);
			case 2:
				testType = Integer.parseInt(args[1]);
			case 1:
				inputFileName = args[0];
				System.out.print(inputFileName);
				break;
				default:
					System.err.println("Pogresni argumenti");
					System.exit(-1);
		}
		
		isSemanticTest = isSemanticTest(testType);
		isSyntaxTest = isSyntaxTest(testType);
		
		Logger log = Logger.getLogger(MJParserTest.class);
		Reader br = null;
		try {
			
			sourceCode = new File(inputFileName);	
			
			/*
			if (null != outputFileName) {
				printWriter = new PrintWriter( new File(outputFileName));
			}
			*/
			//log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			Symbol currToken = null;
			
			
			@SuppressWarnings("deprecation")
			MJParser p = new MJParser(lexer);
			// Prints out to System.error.
			//
			if (null != objFileName)
			{
				p.setFileName(objFileName);
			}
			
		
			if (debug) {
				Symbol s = p.debug_parse();
			}
			else {
				Symbol s  = p.parse();
			}
			
			if (isSyntaxTest && p.syntaxError)
			{
				System.err.println("Sintaksa greska!!!");
				System.exit(-1);
			}
			else if (!isSemanticTest && p.semanticError)
			{
				System.err.println("Semanticka greska!!!");
				System.exit(-1);
			}
			
			//log.info("Print calls" + p.printCallCount);
			
			//Tab.dump();
			
			/*
			if (!p.errorDetected) {
				File objFile = new File(outputFileName);
				if (objFile.exists()) {
					objFile.delete();
				}
				
				//Code.write(new FileOutputStream(objFile));
				log.info("Parsiranje uspesno");
			}
			else {
				log.info("Parsiranje nije uspesno");
			}
			*/
			/*
			while ((currToken = lexer.next_token()).sym != sym.EOF) {
				if (currToken != null && currToken.value != null)
				{
					if (null == outputFileName) {
						//System.out.println(currToken.toString() + " " + currToken.value.toString());
						log.info(currToken.toString() + " " + currToken.value.toString());
					}
					else {
						printWriter.write(currToken.toString() + " " 
								+ currToken.value.toString() + "\n");
					}
				}
			}
			*/
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
			if (null != printWriter) {
				printWriter.close();
			}
		}
		
	}
		
			

			
	
}
