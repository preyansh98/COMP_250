import java.util.ArrayList;

public class MyOwnTester {
	
	public static void main(String[] args) {
		Song la = new Song("Key: title", "Value: artist", 2000);
		Song la2 = new Song("Key2: title", "Value2: artist", 2020);
		Song la3 = new Song("Key3: title", "Value3: artist", 2001);
		Song la4 = new Song("Key4: title", "Value4: artist", 2003);
		Song la5 = new Song("Key5: title", "Value5: artist", 2004);
	
        ArrayList<Song> songs = initSongList();
        MyHashTable<String,Song> songTable;
        
	        int numBuckets = 7;
	        // Initialize the hash table.   Key will be the song title.
	        
	        songTable = new MyHashTable<String,Song>(numBuckets);
	        
	        for (Song song: songs) {
	            songTable.put(song.getTitle(), song);
	        }
	        
	       
            /******************************/
            MusicStore store = new MusicStore(initSongList());
            System.out.println(store.HashYears.keys());
           System.out.println(store.HashYears.get(2011));
            
	        

	        
	        System.out.println("New MyHashtable created.....");
	        System.out.println("Number of songs in the table: " + songTable.size());
	        System.out.println("Number of buckets in the table: " + songTable.numBuckets());
	}
	
	
	
	
	
	
	   private static ArrayList<Song> initSongList() {
	        ArrayList<Song> songs = new ArrayList<Song>();
	        songs.add(new Song("Le Premier Bonheur du Jour", "Os Mutantes", 1968));
	        songs.add(new Song("Stretch Out And Wait", "The Smiths", 1987));
	        songs.add(new Song("Scream", "Black Flag", 1984));
	        songs.add(new Song("Europe, After the Rain", "Max Richter", 2002));
	        songs.add(new Song("Why Are You Looking Grave?", "Mew", 2005));
	        songs.add(new Song("Fallen Angel", "King Crimson", 1974));
	        songs.add(new Song("The Song Of Ice and Fire", "Rhaegar", 1974));
	        songs.add(new Song("The Bear and The Maiden Fair", "Tom O'Sevens", 2011));
	        songs.add(new Song("The Dornishman's Wife", "Tom O'Sevens", 2011));
	        songs.add(new Song("The Burning of the Ships", "Tom O'Sevens", 2011));
	        songs.add(new Song("Jenny of Oldstones", "Tom O'Sevens", 2011));
	        songs.add(new Song("King Without courage", "Tom O'Sevens", 2011)); 
	        songs.add(new Song("The Mother's Tears", "Tom O'Sevens", 2011));
	        songs.add(new Song("The Rains of Castermere", "Tom O'Sevens", 2011));
	        songs.add(new Song("Two hearts that beat as one", "Tom O'Sevens", 2011));
	        songs.add(new Song("Wolf in the Night", "Tom O'Sevens", 2011));
	        songs.add(new Song("Fallen Leaves", "Marillion", 2002));
	        songs.add(new Song("Flowers of springs", "Marillion", 2002));
	        songs.add(new Song("On a Misty Morn", "Marillion", 2002));
	        songs.add(new Song("Her Little Flower", "Dareon", 2001));
	        songs.add(new Song("Milady's Supper", "Dareon", 2001));
	        songs.add(new Song("Rat Cook", "Dareon", 2001));
	        songs.add(new Song("The Night that ended", "Dareon", 2001));
	        songs.add(new Song("When Willum's Wife was wet", "Dareon", 2001));
	        songs.add(new Song("A thousad eyes and one", "Dareon", 2001));
	        
	        
	        return songs;
	    }
}

