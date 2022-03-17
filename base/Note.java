package base;
import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>, java.io.Serializable{
	private Date date;
	private String title;
	
	private static final long serialVersionUID = 1L;
	
	public Note (String title) {
		this.title = title;
		date = new Date();
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	public int compareTo(Note o) {
		return o.date.compareTo(date);
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}

}
