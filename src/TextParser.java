import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Text Parser class for <b>BU MET CS342</b><br><br>
 * To use this class do the following:<br>
 * <br>
 * 1) Copy the TextParser.jar file to your project directory<br>
 * 2) In Eclipse go to Project-&gt;Properties<br>
 * 3) Select Java Build Path from the left side<br>
 * 4) Select the Libraries Tab from the top of the Dialog Box<br>
 * 5) Click on Classpath in the library listings section<br>
 * 6) Select Add External Jars from the right side buttons<br>
 * 7) Select the path to TextParser.jar<br>
 * <br>
 * @author Vic
 *
 */
public class TextParser {

	private String lines = null;
 	private int strPos = 0;
 	

 	private void initialize() {
 		lines = null;
 		strPos = 0;
 	}

	/**
	 * Opens the filename passed in in <b>fileName</b> and prepares it for parsing.  In the event there
	 * is any type of failure, the method returns false.  <br>Possible Failures are:<br><br>
	 * <b>File not found<br>
	 * General exception<br></b>
	 * @param fileName String containing path to file to open
	 * @return true if successful, false if not
	 */
	public boolean openFile(String fileName) {
		lines = null;
		File book = new File(fileName);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(book));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			lines = sb.toString();
			br.close();
			return true;
			
		} catch (FileNotFoundException e) {
			initialize();
			return false;
		} catch (IOException e) {
			initialize();
			return false;
		}
		
	}
	
	/**
	 * Get the next word from the opened file.  If the file is not opened, or the end is reached
	 * the method returns null;  Please note... calling this method without opening a file immediately
	 * returns null.
	 * @return next available word, or null if end of text or failure
	 */
	public String getNextWord() {
		String rtn = "";
		Character n;

		try {

			n = lines.charAt(strPos);
			n = Character.toLowerCase(n);
			//System.out.println(Character.valueOf(n));

			// First trim beginning
			while (((n < 'a') || (n > 'z')) && !Character.isDigit(n)) {
				strPos++;
				n = lines.charAt(strPos);
				n = Character.toLowerCase(n);
			}

			// Now pull only letters or numbers until we hit a space
			while(!Character.isWhitespace(n)) {
				if (Character.isLetterOrDigit(n)) {
					rtn += lines.charAt(strPos);
				}
				strPos++;
				n = lines.charAt(strPos);
			}
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

		rtn = rtn.toLowerCase();
		return rtn;
	}

}
