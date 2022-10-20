
public class ParallelSearchCoarse {
	private static Object reader;

	public static void main(String args[]) throws InterruptedException {
		if( args.length < 2) {
			System.out.println("Usage: Java ParallelSearchCoarse FileName Pattern");
			System.exit(0);
		}
		
		String fname = args[0];         // fileName = files/wikipedia2text-extracted.txt
		String pattern = args[1];       // pattern = "(John) (.+?) ";
		long start = System.currentTimeMillis();
		
		// Create your thread reader and searcher here
		// TODO

		
		// ((Object) reader).join();
		// searcher.join();
		long end = System.currentTimeMillis();
		System.out.println("Time cost for concurrent solution is " + (end - start));
		
	}

}
