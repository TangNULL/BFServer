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
			String filename=JOptionPane.showInputDialog(null, "并没有目标文件，请输入文件名以创建一个新文件也可以选择取消");
		}
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
	public String readFile(String filepath, String fileName) {
		// TODO Auto-generated method stub
		String result="";
		try {
			File myfile=new File(filepath+fileName);
			FileReader fileReader= new FileReader(myfile);
			BufferedReader buffer=new BufferedReader(fileReader);
			String Line=null;
			while((Line=buffer.readLine())!=null){
				result+=Line;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "文件不存在", "提示 ", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String readFileList(String filepath) {//不用这个
		// TODO Auto-generated method stub
		String result="";
		File myfile=new File(filepath);
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
