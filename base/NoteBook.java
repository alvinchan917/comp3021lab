package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class NoteBook implements java.io.Serializable{
	private ArrayList<Folder> folders;
	
	private static final long serialVersionUID = 1L;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public NoteBook(String file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook)in.readObject();
			this.folders = n.folders;
			in.close();
		} catch (Exception e) {
			folders = new ArrayList<Folder>();
		}
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	public boolean insertNote(String folderName, Note note) {
		Folder folderNeeded = null;
		for (Folder folder: folders) {
			if (folder.getName() == folderName) {
				folderNeeded = folder;
				break;
			}
		}
		
		if (folderNeeded == null) {
			folderNeeded = new Folder(folderName);
			folders.add(folderNeeded);
		}
		
		ArrayList<Note> notesInFolder = folderNeeded.getNotes();
		
		for (Note thisNote: notesInFolder) {
			if (thisNote.getTitle() == note.getTitle()) {
				System.out.println("Creating note " + note.getTitle() + " under folder " +
						folderName + " failed");
				return false;
			}
		}
		
		folderNeeded.addNote(note);
		return true;
		
	}
	
	public void sortFolders() {
		for (Folder thisFolder: folders) {
			thisFolder.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public ArrayList<Note> searchNotes(String keywords) {
		ArrayList<Note> matchingNotes = new ArrayList<Note>();
		for (Folder thisFolder: folders) {
			matchingNotes.addAll(thisFolder.searchNotes(keywords));
		}
		return matchingNotes;
	}
	
	public boolean save (String file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fos);
//			for (Folder f: folders)
//				out.writeObject(f);
			out.writeObject(this);
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
