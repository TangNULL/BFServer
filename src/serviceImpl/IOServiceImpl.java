package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.IOService;

public class IOServiceImpl extends UnicastRemoteObject implements IOService{
	
	public IOServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	String prefix1="E:\\学习\\大作业\\BFServer\\";
	String prefix2=".txt";
	@Override
	public boolean writeFile(String file, String userId, String fileName) {//修改文件
		File f = new File(prefix1+userId + "_" + fileName+prefix2);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		String result="";
		try {
			File myfile=new File(prefix1+userId + "_" + fileName+prefix2);
			FileReader fileReader= new FileReader(myfile);
			BufferedReader buffer=new BufferedReader(fileReader);
			String Line=null;
			while((Line=buffer.readLine())!=null){
				result+=Line;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String readFileList(String userId) {
		// TODO Auto-generated method stub
		String result="";
		File myfile=new File(prefix1+userId+prefix2);
		try {
			FileReader fileReader=new FileReader(myfile);
			BufferedReader buffer=new BufferedReader(fileReader);
			String Line=null;
			try {
				while((Line=buffer.readLine())!=null){
					result+=Line+"\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
