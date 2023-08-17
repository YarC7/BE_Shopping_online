// package com.example.salesmanagement.entity.utilities;

// public class NullPointerExceptionTest {
// 	public static void main(String[] args) {
// 		try {
			
// 			// VD1: Lỗi NPE xảy ra nếu bạn cố truy nhập một null Object
// 			NPE1();
// 		} catch (NullPointerException e) {
// 			System.out.println("Exception in NPE1()");
// 			e.printStackTrace();
// 		}
 
// 		try {
			
// 			// VD2: Lỗi NPE xảy ra khi bạn có đổi một null String
// 		    NPE2();
// 		} catch (NullPointerException e) {
// 			System.out.println("\nException in NPE2()");
// 			e.printStackTrace();
// 		}
 
// 		try {
// 			//VD 3: Lỗi NPE xảy ra khi bạn cố truy nhập
// 			//null Object trong quá trình khởi tạo class
			
// 			NullTest npe = new NullTest();
// 			npe.getName();
// 		} catch (NullPointerException e) {
// 			System.out.println("\n Exception in NullNPETest()");
// 			e.printStackTrace();
// 		}
 
// 	}
 
// 	private static void NPE1() {
// 		Object object = null;
// 		object.hashCode();
// 	} 
 
// 	private static void NPE2() {
// 		String str;

// 		//Chúng ta gọi một biến techmasterString có kiểu giá trị String
// 		//Biên chưa trỏ tới giá trị cụ thể, nên Java mặc định giá trị nó là null

// 		str = "Test text";

//                 //Sau dòng lệnh trên, biến được gọi giá trị cụ thể 
//                 //với giá trị cụ thể trên bộ nhớ
// 		// nên sẽ không bị lỗi NullPointerException


// 		System.out.println("\nvalue: " + str.toString() + ", lenght: " + str.length());
// 		System.out.println("No NPE exception");
 
// 		// Giờ tạo ra lỗi NullPointerException nào
// 		String str2 = null;
// 		System.out.println(str2.toString());
 
// 	}	
// }
 
// class NullTest {
// 	private String str;
 
// 	public void setName(String name) {
// 		this.str = name;
// 	}
 
// 	public void getName() {
// 		printName(str);
// 	}
 
// 	private void printName(String s) {
// 		System.out.println(s + " (" + s.length() + ")");
// 	}
// }
