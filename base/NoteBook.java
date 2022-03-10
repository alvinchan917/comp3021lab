package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
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
}
