package jdbc_person;

import java.util.Scanner;

public class PersonMain {
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome!!! \n1.SignUp \n2.LogIn \n3.Exit");
		int choise = scanner.nextInt();

		switch (choise) {
		case 1: {
			System.out.println("Enter the Id : ");
			int id = scanner.nextInt();
			System.out.println("Enter the Name : ");
			String name = scanner.next();
			System.out.println("Enter the Phone Number : ");
			long phone = scanner.nextLong();
			System.out.println("Enter the Email : ");
			String email = scanner.next();
			System.out.println("Enter the Password : ");
			String password = scanner.next();
			
			Person person = new Person();
			person.setId(id);
			person.setEmail(email);
			person.setName(name);
			person.setPhone(phone);
			person.setPassword(password);
			
			PersonCRUD crud = new PersonCRUD();
			int result = crud.signUpPerson(person);
			if (result!=0) {
				System.out.println("SignUp SuccessFull");
			} else {
                 System.out.println("SignUp not SuccessFull");
			}
			break;
		}
		case 2:{
			System.out.println("Enter the email : ");
			String email = scanner.next();
			System.out.println("Enter the password : ");
			String password = scanner.next();
			
			PersonCRUD crud = new PersonCRUD();
			String dbpassword = crud.loginPerson(email);
			if(dbpassword!=null) {
			if(dbpassword.equals(password)) {
				System.out.println("Login success! \n");
				System.out.println("Enter the choise : ");
				System.out.println("1. Display Info");
				System.out.println("2. Update Info");
				System.out.println("3. Delete Info");
				System.out.println("4. Logout");
			    int ch = scanner.nextInt();
			    switch(ch) {
			    case 1 : {
			    	PersonCRUD crudd = new PersonCRUD();
			    	Person person = crudd.displayInfo(email);
			    	System.out.println("Id : "+person.getId());
			    	System.out.println("Name : "+person.getName());
			    	System.out.println("Phone : "+person.getPhone());
			    	System.out.println("Email : "+person.getEmail());
			    	System.out.println("Password : "+person.getPassword());
			    	break;
			    }
			    case 2 : {
			    	System.out.println("Enter new password : ");
			    	String newPassword = scanner.next();
			    	PersonCRUD crudU = new PersonCRUD();
			    	int result = crudU.updateInfo(email, newPassword);
			    	if (result!=0) {
						System.out.println("Password Updated Successfully");
					} else {
                        System.out.println("Password not updated");
					}
			    	break;
			    }
			    case 3 : {
			    	PersonCRUD crudd1 = new PersonCRUD();
			        int result = crudd1.deleteInfo(email);
			    	if(result!=0) {
			            System.out.println("Info Deleted");
			    	}
			    	else {
			    		System.out.println("Info not deleted");
			    	}
			    	break;
			    }
			    	
			    }
			    
			}else {
				System.out.println("Invalid password!");
			}
			}
			else {
				System.out.println("Person not registered with the given mail : "+email);
			}
			
			break;
		}
		}
	}
}
