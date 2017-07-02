package serviceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

//UnicastRemoteObject用于导出的远程对象和获得与该远程对象通信的存根。 
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane; 


public class UserServiceImpl  extends UnicastRemoteObject implements UserService{

	public UserServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	public File NameFile=new File("E:\\学习\\大作业\\BFServer\\user_password.txt");
	public String Client=null;
	public String Language=null;
	public boolean wannaloginAgain=false;
	@Override
	public boolean login(String username, String password) throws RemoteException {
		String NameAndWord=username+"_"+password;
		boolean result=false;
		try{
			FileReader fileReader=new FileReader(NameFile);
			BufferedReader reader=new BufferedReader(fileReader);
			String line=null;
			while((line=reader.readLine())!=null){
			   if(NameAndWord.equals(line)){
				   result=true;
				   break;
			   }
			}
		    reader.close();
			}catch(IOException e){
				e.printStackTrace();
			 }
		return result;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		this.Client=null;
		return true;
	}
	
	
	@Override
	public boolean register(String username,String password) throws RemoteException{
		boolean Result=true;
		try {
			FileReader fileReader=new FileReader(NameFile);
			BufferedReader reader=new BufferedReader(fileReader);
			String Line=null;
			while((Line=reader.readLine())!=null&&Line.contains("_")){
				String[] NameAndPass=Line.split("_");
				if(NameAndPass[0].equals(username)){//用户名重合
					Result=false;
					break;
				}
			}
			if(Result==true){//无重复用户名
				FileWriter writer=new FileWriter(NameFile,true);
				writer.write(System.lineSeparator());
				writer.write(username+"_"+password);
				writer.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return Result;
	}

	@Override
	public void setClient(String username) throws RemoteException {
		// TODO Auto-generated method stub
		this.Client=username;
	}

	@Override
	public String getClient() throws RemoteException {
		// TODO Auto-generated method stub
		return this.Client;
	}


}
