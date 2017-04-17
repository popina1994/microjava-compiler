package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.util.Log4JUtils;

public class MJLexerTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws IOException {
		boolean successfullParsing = true;
		String outputFileName = null;
		String inputFileName;
		
		PrintWriter printWriter = null;
		File sourceCode = null;
		
		if (1 == args.length) {
			inputFileName = args[0];
		}
		else if (2 == args.length) {
			inputFileName = args[0];
			outputFileName = args[1];
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
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			Symbol currToken = null;
			
			while ((currToken = lexer.next_token()).sym != sym.EOF) {
				if (currToken != null && currToken.value != null)
				{
					if (null == outputFileName) {
						log.debug(currToken.toString() + " " + currToken.value.toString());
					}
					else {
						printWriter.write(currToken.toString() + " " 
								+ currToken.value.toString() + "\n");
					}
				}
			}
			if (lexer.noError)
			{
				System.out.println("Lexer syntax is fulfilled!!");
			}
			else 
			{
				System.exit(-1);
			}
			
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
			if (null != printWriter) {
				printWriter.close();
			}
		}
	}
	
}
