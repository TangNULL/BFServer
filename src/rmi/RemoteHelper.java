package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


/**
  * ����RMIע�������RMI���񣬲���Զ�̶���ע�ᵽRMIע����С�
  */

public class RemoteHelper {
	public RemoteHelper(){
		initServer();
	}
	public void initServer(){
		DataRemoteObject dataRemoteObject;
		try {
			dataRemoteObject = new DataRemoteObject();
			// ע�����
			LocateRegistry.createRegistry(8887);
			// ��Զ�˶�������
			Naming.rebind("rmi://127.0.0.1:8887/DataRemoteObject",dataRemoteObject);
			System.out.println("Bind Successfully");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
