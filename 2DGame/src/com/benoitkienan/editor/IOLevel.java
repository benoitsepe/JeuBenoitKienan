package com.benoitkienan.editor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


public class IOLevel {
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Niveau niv = new Niveau();
	String path;
	
	public IOLevel(){
	}
	
	public void saveLvl(Niveau saveNiv){
		
//		JFileChooser fc = new JFileChooser(path);
//	    FileNameExtensionFilter filter = new FileNameExtensionFilter(".potato", "potato");
//	    fc.setFileFilter(filter);
//		fc.showSaveDialog(new JFrame());
//		path=(fc.getSelectedFile().toString());
		
		if (!path.endsWith(".potato"))
		    path += ".potato";
		System.out.println("Enregistrement de: "+path);
		
		try{
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(
							new File(path))));
			
			oos.writeObject(saveNiv);
			oos.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveLvlUnder(Niveau saveNiv){
		JFileChooser fc = new JFileChooser(path);
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(".potato", "potato");
	    fc.setFileFilter(filter);
		fc.showSaveDialog(new JFrame());
		path=(fc.getSelectedFile().toString());
		
		if (!path.endsWith(".potato"))
		    path += ".potato";
		System.out.println("Enregistrement de: "+path);
		
		try{
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(
							new File(path))));
			
			oos.writeObject(saveNiv);
			oos.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	
	public Niveau openLvl(String name){
		JFileChooser fc = new JFileChooser(path);
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(".potato", "potato");
	    fc.setFileFilter(filter);
		fc.showOpenDialog(new JFrame());
		path=(fc.getSelectedFile().toString());
		System.out.println("Ouverture de: "+path);
		
		try {
			ois=new ObjectInputStream(
					new BufferedInputStream(
						new FileInputStream(
							new File(fc.getSelectedFile().toString()))));
			
			niv=(Niveau)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return niv;
	}
	
	
	
	public boolean doPathExist(){
		if(path!=null)
			return true;
		else
			return false;
	}
}
