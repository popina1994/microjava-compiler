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
	
	public static void main(String[] args) throws Exception {
		
		String outputFileName = null;
		String inputFileName = null;
		boolean debug = false;
		PrintWriter printWriter = null;
		File sourceCode = null;
		
		if (1 == args.length) {
			inputFileName = args[0];
		}
		else if (2 == args.length) {
			inputFileName = args[0];
			debug = Boolean.parseBoolean(args[1]);
		}
		else if (3 == args.length){
			inputFileName = args[0];
			debug = Boolean.parseBoolean(args[1]);
			outputFileName = args[2];
			
		}
		else {
			System.err.println("Pogresni argumenti");
			return;
		}
		
		Logger log = Logger.getLogger(MJLexerTest.class);
		Reader br = null;
		try {
			
			sourceCode = new File(inputFileName);	
			
			if (null != outputFileName) {
				printWriter = new PrintWriter( new File(outputFileName));
			}
			//log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			Symbol currToken = null;
			
			
			@SuppressWarnings("deprecation")
			MJParser p = new MJParser(lexer);
			// Prints out to System.error.
			//
			if (debug) {
				Symbol s = p.debug_parse();
			}
			else {
				Symbol s  = p.parse();
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
