package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.IOService;
import service.UserService;
import service.ExecuteService;
import serviceImpl.IOServiceImpl;
import serviceImpl.UserServiceImpl;
import serviceImpl.ExecuteServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject implements IOService, UserService,ExecuteService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;//–Ú¡–ªØ
	private IOService iOService;
	private UserService userService;
	private ExecuteService executeService;
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
		executeService=new ExecuteServiceImpl();
	}

	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName);
	}

	@Override
	public String readFile(String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFile(userId, fileName);
	}

	@Override
	public String readFileList(String userId) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFileList(userId);
	}

	@Override
	public boolean login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.login(username, password);
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.logout(username);
	}

	@Override
	public boolean register(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.register(username, password);
	}
	
	@Override
	public void setClient(String username) throws RemoteException{
		// TODO Auto-generated method stub
	    userService.setClient(username);
	}

	@Override
	public String getClient() throws RemoteException {
		// TODO Auto-generated method stub
		return userService.getClient();
	}
	
	@Override
	public void setLanguage(String language) throws RemoteException{
		// TODO Auto-generated method stub
	    userService.setLanguage(language);
	}

	@Override
	public String getLanguage() throws RemoteException {
		// TODO Auto-generated method stub
		return userService.getLanguage();
	}

	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		return executeService.execute(code, param);
	}
	
	@Override
	public boolean loginAgain() throws RemoteException {
		// TODO Auto-generated method stub
		return userService.loginAgain();
	}

	@Override
	public void setloginAgain(boolean wannaloginAgain) throws RemoteException {
		// TODO Auto-generated method stub
		userService.setloginAgain(wannaloginAgain);
	}

}
