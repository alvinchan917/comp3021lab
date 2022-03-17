package base;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;



public class TextNote extends Note {
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		try {
			FileReader f = new FileReader(absolutePath);
			BufferedReader buffer = new BufferedReader(f);
			String line;
			while ((line = buffer.readLine()) != null) {
				result += line + "\n";
			}
			buffer.close();
		}  catch (FileNotFoundException e) {
			// do nothing
		} catch (IOException e) {
			// do nothing
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		try {
//			String fullName = pathFolder + File.separator + validTitle + ".txt";
			File file = new File(pathFolder + File.separator + getTitle().replaceAll(" ", "_") + ".txt");
//			file.createNewFile();
			FileWriter f = new FileWriter (file.getName());
			f.write(content + "\n");
//			buffer.write(content + "\n");
			f.close();
//			buffer.close();
			
		} catch (IOException e) {
			// do nothing
			e.printStackTrace();
		}
		
	}
}
