package jsoup.thread;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import jsoup.entities.Page;

public class ThreadRunner extends Thread {
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
			
			System.err.println("Jsoup Connection Page: " + page);
			e.printStackTrace();
		}
		

		try {
			saveToFile(doc);
		} catch (IOException e) {
			System.err.println("Page: " + page);
			e.printStackTrace();
		}
	}

	public boolean saveToFile(Document doc) throws IOException {
        final File f = new File("filename.html");
        FileUtils.writeStringToFile(f, doc.outerHtml(), "UTF-8");
        
		return false;
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
