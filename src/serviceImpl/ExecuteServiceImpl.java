//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl extends UnicastRemoteObject implements ExecuteService {

	public ExecuteServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
