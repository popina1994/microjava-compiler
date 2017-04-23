package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import org.apache.log4j.Logger;

import java_cup.runtime.Symbol;

public class MJCodeGeneratorTest {


	public static void main(String[] args) throws Exception {
		String inputFileName = null;
		String testFileName = null;
		String outputFileName = null;
		PrintWriter printWriter = null;
		File sourceCode = null;
		if (3 == args.length)
		{
			inputFileName = args[0];
			testFileName = args[1];
		}
		else
		{
			System.err.println("Pogresni arugmenti");
			return; 
		}
		Reader br = null;
		
		Logger log = Logger.getLogger(MJCodeGeneratorTest.class);
		
		try {
			
			sourceCode = new File(inputFileName);	
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			Symbol currToken = null;
			
			
			@SuppressWarnings("deprecation")
			MJParser p = new MJParser(lexer);
			p.setFileName(outputFileName);
			
			Symbol s  = p.parse();
		
			if (p.errorDetected) 
			{
				System.exit(-1);
			}
			
			
			
		}
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { 
				log.error(e1.getMessage(), e1); }
			if (null != printWriter) {
				printWriter.close();
			}
		}
		
		
	}

}
