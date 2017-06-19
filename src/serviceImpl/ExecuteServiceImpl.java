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
		StringBuffer codd=new StringBuffer();
		StringBuffer temp=new StringBuffer();
		if(code.contains("k")){
			String[] split=code.split(" ");
			for(String st:split){
				temp.append(st);
			}
			code=temp.toString();
			for(int i=0;i<=code.length()-1-7;i=i+8){
				String str=code.substring(i, i+8);
				switch(str){
				case "Ook.Ook?":
					str=">";
					break;
				case "Ook?Ook."	:
					str="<";
					break;
				case "Ook.Ook.":
					str="+";
					break;
				case "Ook!Ook!":
					str="-";
					break;
				case "Ook!Ook.":
					str=".";
					break;
				case "Ook.Ook!":
					str=",";
					break;
				case "Ook!Ook?":
					str="[";
					break;
				case "Ook?Ook!":
					str="]";
					break;
				}
				codd.append(str);
			}
			code=codd.toString();
		}
		String result="";
		
		int paramAlreadyUse=0;  //param已用的部分
		
		int ptr=0;  // myList中的指针
		
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
				if(myList.size()<ptr+1){
					myList.add(0);
				}
				int numPlus=myList.get(ptr);
				myList.set(ptr, ++numPlus);
				break;
				
			case '-':  //指针对应的位置的值减一
				int numMinus=myList.get(ptr);
				myList.set(ptr, --numMinus);
				break;
				
			case '.':  //输出指针指向的单元内容（ASCII码）
				if(myList.size()<ptr+1){
					myList.add(0);
				}
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
							myList.set(ptr, param.charAt(0)+0);
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
					int count=0;
					for(int k=i+1;k<code.length();k++){
						if(code.charAt(k)=='['){
							count++;
						}
						else if(code.charAt(k)==']'&&count!=0){
							count--;
						}
						else if(code.charAt(k)==']'&&count==0){
							i=k;
							break;
						}
					}
				}
				//在code中 ] 在i位置 
				break;
			
			case ']':  //    如果指针指向的单元值不为零，向前跳转到对应的[指令的次一指令处
				if(myList.get(ptr)==0){
					continue;
				}
				else{ // 向前跳转
					int count=0;
					for(int k=i-1;k>=0;k--){
						if(code.charAt(k)==']'){
							count++;
						}
						else if(code.charAt(k)=='['&&count!=0){
							count--;
						}
						else if(code.charAt(k)=='['&&count==0){
							i=k;
							break;
						}
					}
				}
				break;
			}
		}
		return result;
		
	}
}
