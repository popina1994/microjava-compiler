package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import java_cup.runtime.Symbol;

public class MJCodeGeneratorTest {


	public static void main(String[] args) throws Exception {
		String testFileName = null;
		String outputFileName = null;
		BufferedReader outputBufferedReader = null;
		BufferedReader testBufferedReader = null;  
		Boolean equal = true;
		if (2 == args.length)
		{
			outputFileName= args[0];
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
			outputBufferedReader = new BufferedReader(new FileReader(new File(outputFileName)));
			testBufferedReader = new BufferedReader(new FileReader(new File(testFileName)));
			
			int line = 0;
			String outputLine = null;
			String testLine = null;
			
			int lineNotEqual = -1;
			int cntNotEqual = 0;
			
			outputLine = outputBufferedReader.readLine();
			testLine = testBufferedReader.readLine();
			while ( ( outputLine!= null) && (testLine != null))
			{
				if (!outputLine.equals(testLine))
				{
					equal = false;
					lineNotEqual = line;
					cntNotEqual ++;
					System.out.println("Output " + outputLine);
					System.out.println("Test " + testLine);
				}
				line ++;
				outputLine = outputBufferedReader.readLine();
				testLine = testBufferedReader.readLine();
			}
			
			
			if ( (lineNotEqual == line - 1) && (cntNotEqual == 1) && (outputLine == null)
					&& (testLine == null))
			{
				equal = true;
			}
			
			
		}
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { 
				log.error(e1.getMessage(), e1); }
			if (null != outputBufferedReader) {
				outputBufferedReader.close();
			}
			if (null != testBufferedReader)
			{
				testBufferedReader.close();
			}
		}
		
		if (!equal)
		{
			System.exit(-1);
		}
		
		
	}

}
