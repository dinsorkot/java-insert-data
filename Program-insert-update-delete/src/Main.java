import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
	/*
		students s = new students();
		//insert 
		s.setFirst_name("sirichai");
		s.setLast_name("kasemsuk");
		s.setDate_of_birth("2002-07-25");
		s.setGender("male");
		s.setEmail("dinsorkot");
		s.insertData(s);
		/*
		//update
		s.setStudent_id("6604100002");
		s.setFirst_name("sirichai");
		s.setLast_name("kasemsuk");
		s.setDate_of_birth("2002-08-25");
		s.setGender("male");
		s.setEmail("dinsorkot");
		s.updateData(s);
		//delete
		s.setStudent_id("6604100003");
		s.deleteData(s);
*/
    	
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui();
            }
        });
        
    }
}
