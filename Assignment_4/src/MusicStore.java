import java.util.ArrayList;
import java.util.Iterator;


public class MusicStore {
    //ADD YOUR CODE BELOW HERE
	
	int initialCapacity = 99;
	 MyHashTable<String, Song> HashTitles = new MyHashTable<String, Song>(initialCapacity);
	 MyHashTable<Integer, ArrayList<Song>> HashYears = new MyHashTable<Integer, ArrayList<Song>>(initialCapacity);
	 MyHashTable<String, ArrayList<Song>> HashArtist = new MyHashTable<String, ArrayList<Song>>(initialCapacity);	
    //ADD YOUR CODE ABOVE HERE
    
	
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
    	
    	/***for the titles***/
    	for(Song songinput : songs) {
    		HashTitles.put(songinput.getTitle(), songinput); 
    	}
    	
    	
    	/***1. for the artist***/ 
    	for(Song songinput : songs) {
    		//clone the song, get the key then store it.
    		
    	if(HashArtist.get(songinput.getArtist()) == null || HashArtist.get(songinput.getArtist()).size() == 0) {
    		ArrayList<Song> songValues = new ArrayList<Song>();
    		songValues.add(songinput);
    		HashArtist.put(songinput.getArtist(), songValues);
    	}
    	else {
    		(HashArtist.get(songinput.getArtist())).add(songinput);
    	}
    	}
//    		
    	
    	
    	/***2. for the year***/
	for(Song songinput : songs) {
		//clone the song, get the key then store it.
	
	if(HashYears.get(songinput.getYear()) == null || HashYears.get(songinput.getYear()).size() == 0) {
		ArrayList<Song> songValues = new ArrayList<Song>();
		songValues.add(songinput);
		HashYears.put(songinput.getYear(), songValues);
	}
	else {
		(HashYears.get(songinput.getYear())).add(songinput);
	}}
	}
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
    	

        HashTitles.put(s.getTitle(), s);
    	if(HashArtist.get(s.getArtist())==null) {
    		ArrayList<Song> songs = new ArrayList<Song>();
			songs.add(s);
			HashArtist.put(s.getArtist(), songs);
    	}else {
			HashArtist.get(s.getArtist()).add(s);
    	}	
    	
		
		if(HashYears.get(s.getYear())==null || HashYears.get(s.getYear()).size()==0 ) {
			ArrayList<Song> songs2 = new ArrayList<Song>();
			songs2.add(s);
			HashYears.put(s.getYear(), songs2);
		}else {
			HashYears.get(s.getYear()).add(s);
		}
    	
    
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
    	
    	
    	return (HashTitles.get(title));
    	//ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
       
    	return (HashArtist.get(artist));
    	
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
        
    	return (HashYears.get(year));
    	
        //ADD CODE ABOVE HERE
        
    }
}
