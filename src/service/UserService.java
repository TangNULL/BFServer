//需要客户端的Stub  存根
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote{
	//声明服务器端必须提供的服务 
	public boolean login(String username, String password) throws RemoteException;

	public boolean logout(String username) throws RemoteException;
	
	public boolean register(String username,String password) throws RemoteException;
	//这些方法必须被实现
}
