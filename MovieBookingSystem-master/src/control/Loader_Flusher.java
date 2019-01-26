package control;

import java.io.FileNotFoundException;

/**
 * Interface for loading all the data from the .txt files and flushing updated
 * data into the same .txt files.
 * 
 * @author SuyashL
 */

public interface Loader_Flusher {
	public void load() throws FileNotFoundException;

	public void flush() throws FileNotFoundException;
}
