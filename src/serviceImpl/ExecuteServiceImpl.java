//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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
		String result="";
		int paramAlreadyUse=0;  //param已用的部分
		int ptr=0;  // myList中的指针
		//int codeAlreadyUse=0;
		ArrayList<Integer> myList=new ArrayList<Integer>();
		for(int i=0;i<code.length();i++){
			char order=code.charAt(i);
			switch(order){
			case '>':  //右移
				if(myList.size()<ptr+1){
					ptr++;
					myList.add(0);
				}
				else{
					ptr++;
					try{
						if(myList.get(ptr)==null){
							myList.set(ptr, 0);
						}
					}catch(IndexOutOfBoundsException e){
						myList.add(0);
					}
				}
				break;
				
			case '<':  //左移
				ptr--;
				break;
				
			case '+':  //指针对应的位置的值加一
				int numPlus=myList.get(ptr);
				myList.set(ptr, ++numPlus);
				break;
				
			case '-':  //指针对应的位置的值减一
				int numMinus=myList.get(ptr);
				myList.set(ptr, --numMinus);
				break;
				
			case '.':  //输出指针指向的单元内容（ASCII码）
				int m=myList.get(ptr);
				char x=(char)m;
				result=result+x;
				break;
			
			case ',':  //把param内容到指针指向的单元（ASCII码）
				if(myList.size()<ptr+1){
					myList.add(param.charAt(0)+0);
				}
				else{
					try{
						if(myList.get(ptr)==null){
							myList.set(ptr, 0);
						}
					}catch(IndexOutOfBoundsException e){
						myList.add(0);
					}
					myList.set(ptr,param.charAt(0)+0);
				}
				paramAlreadyUse++;
				param=param.substring(1);
				break;
				
			case '[':  //   如果指针指向的单元值为零，向后跳转到对应的]指令的次一指令处
				       //   在code里面  从这个前括号向后找对应的后括号
				if(myList.get(ptr)!=0){
					continue;
				}
				else{ // 向后跳转
					while(code.charAt(i)!=']'){
						i++;
					}
				}
				//在code中 ] 在i位置 
				break;
			
			case ']':  //    如果指针指向的单元值不为零，向前跳转到对应的[指令的次一指令处
				if(myList.get(ptr)==0){
					continue;
				}
				else{ // 向前跳转
					while(code.charAt(i)!='['){
						i--;
					}
				}
				break;
			}
		}
		return result;
	}

}
