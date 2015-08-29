package hr.primefaces.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.lowagie.text.Document;

@ManagedBean(name = "homeMB")
@ViewScoped
public class HomeView implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
	}

	 public TreeNode createDocuments1() {
        TreeNode root = new DefaultTreeNode(new Document("Files", "-", "Folder"), null);
         
        TreeNode documents = new DefaultTreeNode(new Document("Documents", "-", "Folder"), root);
        TreeNode pictures = new DefaultTreeNode(new Document("Pictures", "-", "Folder"), root);
        TreeNode movies = new DefaultTreeNode(new Document("Movies", "-", "Folder"), root);
         
        TreeNode work = new DefaultTreeNode(new Document("Work", "-", "Folder"), documents);
        TreeNode primefaces = new DefaultTreeNode(new Document("PrimeFaces", "-", "Folder"), documents);
         
        //Documents
        TreeNode expenses = new DefaultTreeNode("document", new Document("Expenses.doc", "30 KB", "Word Document"), work);
        TreeNode resume = new DefaultTreeNode("document", new Document("Resume.doc", "10 KB", "Word Document"), work);
        TreeNode refdoc = new DefaultTreeNode("document", new Document("RefDoc.pages", "40 KB", "Pages Document"), primefaces);
         
        //Pictures
        TreeNode barca = new DefaultTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
        TreeNode primelogo = new DefaultTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);
        TreeNode optimus = new DefaultTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);
         
        //Movies
        TreeNode pacino = new DefaultTreeNode(new Document("Al Pacino", "-", "Folder"), movies);
        TreeNode deniro = new DefaultTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);
         
        TreeNode scarface = new DefaultTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File"), pacino);
        TreeNode carlitosWay = new DefaultTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File"), pacino);
         
        TreeNode goodfellas = new DefaultTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File"), deniro);
        TreeNode untouchables = new DefaultTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File"), deniro);
         
        return root;
    }
	 
	 public TreeNode createDocuments2() {
        TreeNode root = new DefaultTreeNode(new Document("Files", "-", "Folder"), null);
         
        TreeNode documents = new DefaultTreeNode(new Document("Documents", "-", "Folder"), root);
        TreeNode pictures = new DefaultTreeNode(new Document("Pictures", "-", "Folder"), root);
        TreeNode movies = new DefaultTreeNode(new Document("Movies", "-", "Folder"), root);
         
        TreeNode work = new DefaultTreeNode(new Document("Work", "-", "Folder"), documents);
        TreeNode primefaces = new DefaultTreeNode(new Document("PrimeFaces", "-", "Folder"), documents);
         
        //Documents
        TreeNode expenses = new DefaultTreeNode("document", new Document("Expenses.doc", "30 KB", "Word Document"), work);
        TreeNode resume = new DefaultTreeNode("document", new Document("Resume.doc", "10 KB", "Word Document"), work);
        TreeNode refdoc = new DefaultTreeNode("document", new Document("RefDoc.pages", "40 KB", "Pages Document"), primefaces);
         
        //Pictures
        TreeNode barca = new DefaultTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
        TreeNode primelogo = new DefaultTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);
        TreeNode optimus = new DefaultTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);
         
        //Movies
        TreeNode pacino = new DefaultTreeNode(new Document("Al Pacino", "-", "Folder"), movies);
        TreeNode deniro = new DefaultTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);
         
        TreeNode scarface = new DefaultTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File"), pacino);
        TreeNode carlitosWay = new DefaultTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File"), pacino);
         
        TreeNode goodfellas = new DefaultTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File"), deniro);
        TreeNode untouchables = new DefaultTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File"), deniro);
         
        return root;
    }
	 
	 public class Document implements Serializable {
	 
	    private String name;
	     
	    private String size;
	     
	    private String type;
	     
	    public Document(String name, String size, String type) {
	        this.name = name;
	        this.size = size;
	        this.type = type;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	        this.name = name;
	    }
	 
	    public String getSize() {
	        return size;
	    }
	 
	    public void setSize(String size) {
	        this.size = size;
	    }
	 
	    public String getType() {
	        return type;
	    }
	 
	    public void setType(String type) {
	        this.type = type;
	    }
	 }
}