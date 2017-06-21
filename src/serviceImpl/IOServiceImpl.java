package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import rmi.RemoteHelper;
import service.IOService;

public class IOServiceImpl extends UnicastRemoteObject implements IOService{
	
	public IOServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean writeFile(String file, String filepath, String fileName) {//修改文件
		File f = new File(filepath);
		if(!f.exists()){
			//String filename=JOptionPane.showInputDialog(null, "并没有目标文件，请输入文件名以创建一个新文件也可以选择取消");
			JOptionPane.showMessageDialog(null, "文件不存在");
			return false;
		}
		else{
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
	}

	@Override
	public String readFile(String filepath, String fileName) {
		// TODO Auto-generated method stub
		String result="";
		try {
			File myfile=new File(filepath+fileName);
			if(!myfile.exists()){
				return null;
			}
			else{
				FileReader fileReader= new FileReader(myfile);
				BufferedReader buffer=new BufferedReader(fileReader);
				String Line=null;
				while((Line=buffer.readLine())!=null){
					result+=Line;
				}
				return result;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String readFileList(String filepath) {
		// TODO Auto-generated method stub
		File file=new File(filepath);
		String myStr="";
		if(file.exists()){
			String[] FileName=file.list();
			String newline = System.getProperty("line.separator");
			for(String str:FileName){
				if(str.contains("."))
					myStr+=str+newline;
			}
		}
		else{
			myStr=null;
		}
		return myStr;
	}
	
}
