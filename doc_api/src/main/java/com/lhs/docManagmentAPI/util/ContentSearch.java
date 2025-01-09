/**
 * 
 */
package com.lhs.docManagmentAPI.util;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author lavanya.badham
 *
 */
public class ContentSearch {

	
	public  List<String> Searcher(String sstring,String path) throws IOException, ParseException, Exception {
		
	     List<String> list = new ArrayList<>();
	    	//final String INDEX_DIR = "J:\\Anmol\\index";
	     final String INDEX_DIR = path;
	        //Create lucene searcher. It search over a single IndexReader.
	        IndexSearcher searcher = createSearcher(INDEX_DIR);
	        //Search indexed contents using search term
	        TopDocs foundDocs = searchInContent(sstring, searcher);
	        ScoreDoc[] document = foundDocs.scoreDocs;
	        
	        for(int i = 0;i <document.length;i++)
			  {                 
			     Document doc = searcher.doc(document[i].doc);      
			     String docCode = doc.get("DOC_CODE");
			     String docSlno = doc.get("DOC_SLNO");
			    String filename= doc.get("FIELD_NAME");
			     String result=docCode+"#"+docSlno+"#"+filename;
			     list.add(result);
//			     System.out.println(result);
			   
			  }
	   return list;
	   
	    }
	
	 private  TopDocs searchInContent(String textToFind, IndexSearcher searcher) throws Exception
	    {
	        //Create search query
	        QueryParser qp = new QueryParser("contents", new SimpleAnalyzer());
	        Query query = qp.parse(textToFind);
	         
	        //search the index
	        TopDocs hits = searcher.search(query, 10000);
	        return hits;
	    }
	    
	    private  TopDocs searchInName(String textToFind, IndexSearcher searcher) throws Exception
	    {
	        //Create search query
	        QueryParser qp = new QueryParser("FIELD_NAME", new StandardAnalyzer());
	        Query query = qp.parse(textToFind);
	         
	        //search the index
	        TopDocs hits = searcher.search(query, 100);
	        return hits;
	    }
	 
	    private  IndexSearcher createSearcher(String INDEX_DIR) throws IOException 
	    {
	        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
	         
	        //It is an interface for accessing a point-in-time view of a lucene index
	        IndexReader reader = DirectoryReader.open(dir);
	         
	        //Index searcher
	        IndexSearcher searcher = new IndexSearcher(reader);
	        return searcher;
	    }
}
