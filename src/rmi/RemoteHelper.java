package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


/**
  * 创建RMI注册表，启动RMI服务，并将远程对象注册到RMI注册表中。
  */

public class RemoteHelper {
	public RemoteHelper(){
		initServer();
	}
	public void initServer(){
		DataRemoteObject dataRemoteObject;
		try {
			dataRemoteObject = new DataRemoteObject();
			// 注册表创建
			LocateRegistry.createRegistry(8887);
			// 绑定远端对象到名字
			Naming.rebind("rmi://127.0.0.1:8887/DataRemoteObject",dataRemoteObject);
			System.out.println("Bind Successfully");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
