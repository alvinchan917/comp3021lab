package base;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.List;

public class Folder implements Comparable<Folder> {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder (String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note n) {
		notes.add(n);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes () {
		return notes;
	}
	
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note n: notes) {
			if (n instanceof TextNote)
				++nText;
			else
				++nImage;
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	public int compareTo(Folder o) {
		return name.compareTo(o.name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public ArrayList<Note> searchNotes(String keywords) {
		ArrayList<Note> matchingNotes = new ArrayList<Note>();
		
		String[] arrayOfKeywords = keywords.split(" ");
		ArrayList<String> listOfKeywords = new ArrayList<String>();
		for (int i = 0; i < arrayOfKeywords.length; ++i) {
			if ((i == arrayOfKeywords.length - 1) || !arrayOfKeywords[i + 1].toLowerCase().equals("or")) {
				listOfKeywords.add(arrayOfKeywords[i].toLowerCase());
			}
			else {
				listOfKeywords.add(arrayOfKeywords[i].toLowerCase() + " " + arrayOfKeywords[i + 2].toLowerCase());
				i += 2;
			}
		}
		for (Note thisNote: notes) {
			boolean match = true;
			for (String thisKeyword: listOfKeywords) {
				if (thisKeyword.contains(" ")) {
					String[] arrayOfThisKeyword = thisKeyword.split(" ");

					if (thisNote instanceof TextNote) {
						TextNote thisTextNote = (TextNote)thisNote;
						if (! thisTextNote.getTitle().toLowerCase().contains(arrayOfThisKeyword[0]) &&
								! thisTextNote.getTitle().toLowerCase().contains(arrayOfThisKeyword[1]) && 
								! thisTextNote.getContent().toLowerCase().contains(arrayOfThisKeyword[0]) &&
								! thisTextNote.getContent().toLowerCase().contains(arrayOfThisKeyword[1])) {
							match = false;
							break;
						}
					}
					else if (! thisNote.getTitle().toLowerCase().contains(arrayOfThisKeyword[0]) &&
							! thisNote.getTitle().toLowerCase().contains(arrayOfThisKeyword[1])) {
						
						match = false;
						break;
					}
				}
				else {
					if (thisNote instanceof TextNote) {
						TextNote thisTextNote = (TextNote)thisNote;
						if (! thisNote.getTitle().toLowerCase().contains(thisKeyword) &&
								! thisTextNote.getContent().toLowerCase().contains(thisKeyword)) {
							match = false;
							break;
						}
					}
					else if (! thisNote.getTitle().toLowerCase().contains(thisKeyword)) {
						match = false;
						break;
					}
				}
				
			}
			if (match) matchingNotes.add(thisNote);
		}
		return matchingNotes;
	}
}
