package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String username, String password) throws RemoteException {
		String NameAndWord=username+password;
		boolean result=false;
		try{
			File NameFile=new File("E:/学习/大作业/BFServer/admin_code","admin_code.File");
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
		return true;
	}

}
