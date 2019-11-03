import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTaskForStringCapitalizationDriver {

	public static void main(String[] args) {
		String input = "Our db_set function actually has pretty good performance for something that is so\r\n" + 
				"				simple, because appending to a file is generally very efficient. Similarly to what\r\n" + 
				"				db_set does, many databases internally use a log, which is an append-only data file.\r\n" + 
				"				Real databases have more issues to deal with (such as concurrency control, reclaim‐\r\n" + 
				"				ing disk space so that the log doesn’t grow forever, and handling errors and partially\r\n" + 
				"				written records), but the basic principle is the same. Logs are incredibly useful, and\r\n" + 
				"				we will encounter them several times in the rest of this book.\r\n" + 
				"				The word log is often used to refer to application logs, where an\r\n" + 
				"				application outputs text that describes what’s happening. In this\r\n" + 
				"				book, log is used in the more general sense: an append-only\r\n" + 
				"				sequence of records. It doesn’t have to be human-readable; it might\r\n" + 
				"				be binary and intended only for other programs to read.\r\n" + 
				"				On the other hand, our db_get function has terrible performance if you have a large\r\n" + 
				"				number of records in your database. Every time you want to look up a key, db_get\r\n" + 
				"				has to scan the entire database file from beginning to end, looking for occurrences of\r\n" + 
				"				the key. In algorithmic terms, the cost of a lookup is O(n): if you double the number\r\n" + 
				"				of records n in your database, a lookup takes twice as long. That’s not good.\r\n" + 
				"				In order to efficiently find the value for a particular key in the database, we need a\r\n" + 
				"				different data structure: an index. In this chapter we will look at a range of indexing\r\n" + 
				"				structures and see how they compare; the general idea behind them is to keep some\r\n" + 
				"				additional metadata on the side, which acts as a signpost and helps you to locate the\r\n" + 
				"				data you want. If you want to search the same data in several different ways, you may\r\n" + 
				"				need several different indexes on different parts of the data.\r\n" + 
				"				An index is an additional structure that is derived from the primary data. Many data‐\r\n" + 
				"				bases allow you to add and remove indexes, and this doesn’t affect the contents of the\r\n" + 
				"				database; it only affects the performance of queries. Maintaining additional structures\r\n" + 
				"				incurs overhead, especially on writes. For writes, it’s hard to beat the performance of\r\n" + 
				"				simply appending to a file, because that’s the simplest possible write operation. Any\r\n" + 
				"				kind of index usually slows down writes, because the index also needs to be updated\r\n" + 
				"				every time data is written.\r\n" + 
				"				This is an important trade-off in storage systems: well-chosen indexes speed up read\r\n" + 
				"				queries, but every index slows down writes. For this reason, databases don’t usually\r\n" + 
				"				index everything by default, but require you—the application developer or database\r\n" + 
				"				administrator—to choose indexes manually, using your knowledge of the applica‐\r\n" + 
				"				tion’s typical query patterns. You can then choose the indexes that give your applica‐\r\n" + 
				"				tion the greatest benefit, without introducing more overhead than necessary.\r\n" + 
				"				Data Structures That Power Your Database | 71\r\n" + 
				"				Hash Indexes\r\n" + 
				"				Let’s start with indexes for key-value data. This is not the only kind of data you can\r\n" + 
				"				index, but it’s very common, and it’s a useful building block for more complex\r\n" + 
				"				indexes.\r\n" + 
				"				Key-value stores are quite similar to the dictionary type that you can find in most\r\n" + 
				"				programming languages, and which is usually implemented as a hash map (hash\r\n" + 
				"				table). Hash maps are described in many algorithms textbooks [1, 2], so we won’t go\r\n" + 
				"				into detail of how they work here. Since we already have hash maps for our inmemory data structures, why not use them to index our data on disk?\r\n" + 
				"				Let’s say our data storage consists only of appending to a file, as in the preceding\r\n" + 
				"				example. Then the simplest possible indexing strategy is this: keep an in-memory\r\n" + 
				"				hash map where every key is mapped to a byte offset in the data file—the location at\r\n" + 
				"				which the value can be found, as illustrated in Figure 3-1. Whenever you append a\r\n" + 
				"				new key-value pair to the file, you also update the hash map to reflect the offset of the\r\n" + 
				"				data you just wrote (this works both for inserting new keys and for updating existing\r\n" + 
				"				keys). When you want to look up a value, use the hash map to find the offset in the\r\n" + 
				"				data file, seek to that location, and read the value.\r\n" + 
				"				Figure 3-1. Storing a log of key-value pairs in a CSV-like format, indexed with an inmemory hash map.\r\n" + 
				"				This may sound simplistic, but it is a viable approach. In fact, this is essentially what\r\n" + 
				"				Bitcask (the default storage engine in Riak) does [3]. Bitcask offers high-performance\r\n" + 
				"				reads and writes, subject to the requirement that all the keys fit in the available RAM,\r\n" + 
				"				since the hash map is kept completely in memory. The values can use more space\r\n" + 
				"				than there is available memory, since they can be loaded from disk with just one disk";
		
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		ForkJoinPoolTaskForStringCapitilazation Stringtask = new ForkJoinPoolTaskForStringCapitilazation(input);
	    
				System.out.println("Input String is : "+input);
				//submitting task way 3
				System.out.println("*-*********************************************************-*");
				System.out.println("Resul of String Capitilazation : "+forkJoinPool.invoke(Stringtask));
	}
}
