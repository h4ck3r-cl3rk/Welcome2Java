package jsoup.thread;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import jsoup.entities.Page;

public class ThreadRunner implements Runnable {
	private Page page;
	private Document doc;
	
	public ThreadRunner(Page page) {
		super();
		this.page = page;
	}

	@Override
	public void run() {
		try {
			doc = Jsoup.connect(page.getBaseURL()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public Page getPage() {
		return page;
	}

	public Document getDoc() {
		return doc;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	@Override
	public String toString() {
		return "ThreadRunner [page=" + page + ", doc=" + doc + "]";
	}	
	
}
