package jsoup.logic;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import jsoup.entities.Page;
import jsoup.thread.ThreadManager;
import jsoup.thread.ThreadRunner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		ThreadManager thrdMngr = ThreadManager.getInstance();
		
		List<Page> pageList = new ArrayList<>();
		
//		pageList.add(new Page("http://www.boerse.to", "Börse"));
		pageList.add(new Page("http://www.crynet.to", "Crynet"));
		pageList.add(new Page("http://example.com/", "Example"));
		pageList.add(new Page("http://en.wikipedia.org/", "Wikipedia"));
		pageList.add(new Page("https://serienstream.to/", "SerienStream"));
		pageList.add(new Page("https://serienjunkies.org/", "SerienJunkies"));	

		Thread.sleep(1000);
		
		for (Page page : pageList) {
			thrdMngr.addPageThread(new ThreadRunner(page));
			thrdMngr.threads.get(thrdMngr.threads.size()-1).start();
		}
		
		for (Thread thread : thrdMngr.threads) {
			thread.join();
		}
		
		ArrayList<Element> bodyList = new ArrayList<>();
		
		for (ThreadRunner thread : thrdMngr.threads) {			
			bodyList.add((thread.getDoc().body()));
		}
		
		for (Element elmnt : bodyList) {
			System.out.println(elmnt.text());
		}
		
	}

}
