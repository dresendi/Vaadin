package my.vaadintest.appABC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import org.vaadin.haijian.ExcelExporter;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.EditorErrorHandler;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import my.vaadintest.appABC.UIComponents;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	int Id = 0;
	//HashMap<Integer,Map> list = new HashMap<Integer,Map>();
	HashMap<Integer,String> listVals = new HashMap<Integer,String>();
	HashMap<Integer,Person> listValso = new HashMap<Integer,Person>();
	
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    	
    		
    	GridLayout gridLayout = new GridLayout(2,2);
    	gridLayout.setMargin(true);
    	
    	FormLayout layout = new FormLayout();
    	layout.setMargin(true);
    	layout.setSizeFull();
    	
    	
    	
    	VerticalLayout vlayout = new VerticalLayout();
    	Grid grid = new Grid("Persons");
    	
    	
    	// Define some columns
    	grid.addColumn("Title", String.class);
    	grid.addColumn("First Name", String.class);
    	grid.addColumn("Last Name", String.class);
    	grid.setColumnOrder("First Name");
    	grid.setEditorEnabled(true);
    	grid.setId("abc");
    	
    	
        TextField titleField = new TextField("Title");	
        titleField.setCaption("Title");
        titleField.addValidator(new RegexpValidator("Sr|Sra|Srita", "Use Sr, Sra, Srita"));
        TextField firstNameField = new TextField("First name");
        
        firstNameField.setCaption("First Name");
        TextField lastNameField = new TextField("Last name");
        lastNameField.setCaption("Last Name");
        
        
        
        //VerticalLayout vlayout = new VerticalLayout();
        Button submitBtn = new Button("Submit");
        submitBtn.addClickListener((Button.ClickListener) clickEvent -> {
        	if(titleField.isEmpty()) {
        		Notification.show("Title is required");
        		titleField.focus();
        	}
        	else if(firstNameField.isEmpty()) {
        		Notification.show("First Name is required");
        		firstNameField.focus();
        	}
        	else if(lastNameField.isEmpty()) {
        		Notification.show("Last Name is required");
        		lastNameField.focus();
        	}
        	else {
        		Id++;
        		Person p = new Person();
        		p.setFirstName(firstNameField.getValue());
        		p.setLastName(lastNameField.getValue());
        		p.setTitle(titleField.getValue());
        		//listVals.put(Id,titleField.getValue()+","+firstNameField.getValue()+","+lastNameField.getValue());
        		listValso.put(Id,p);
        		clearFields(titleField, firstNameField, lastNameField);
        		grid.addRow(p.getTitle(),p.getFirstName(),p.getLastName());
        		//listVals.put(Id,titleField.getValue()+","+firstNameField.getValue()+","+lastNameField.getValue());
        		
        		
                //listValso.forEach((k,v) -> grid.addRow(v.getTitle(),v.getFirstName(),v.getLastName()));
                //grid.setSizeFull();
                
                
        	}
        	
        });
        
        Button removeSelected = new Button("Remove Selected",e->  {
        	
            grid.getContainerDataSource().removeItem(grid.getSelectedRow());

            // Otherwise out of sync with container
            grid.getSelectionModel().reset();

            // Disable after deleting
            //e.getButton().setEnabled(false);
        });
        //removeSelected.setEnabled(grid.getSelectedRows().size() > 0);
        /*Button btnView = new Button("View Saved");
        btnView.addClickListener((Button.ClickListener) clickEvent -> {
        	
        	// Get a set of the entries
            Set set = list.entrySet();
            
            // Get an iterator
            Iterator i = set.iterator();
        	while(i.hasNext()) {
        		Map.Entry me = (Map.Entry)i.next();
        		System.out.println(me.getKey()+":"+me.getValue());
        	}
        	
        	listVals.forEach((k,v) -> System.out.println("Key: " + k +" Value: "+v));
        	
        });*/
        Button btnViewGVals = new Button("View Grid Vals");
        btnViewGVals.addClickListener((Button.ClickListener) clickEvent -> {
        	
        			
        	UIComponents fc = new UIComponents();
        	Component myComponent = fc.findComponentWithId(layout, "abc");
        	
        	System.out.println("Value="+myComponent.getId());
        	
        });
        
        
        
        Button exportExcel = new Button("Exportar CSV");
        exportExcel.addClickListener((Button.ClickListener) clickEvent ->{
        	
        	PrintWriter pw;
			try {
				pw = new PrintWriter(new File("test.csv"));
				StringBuilder sb = new StringBuilder();
	            sb.append("Title");
	            sb.append(',');
	            sb.append("First Name");
	            sb.append(',');
	            sb.append("Last Name");
	            sb.append('\n');
	            	
	            sb.append("1");
	            sb.append(',');
	            sb.append("Prashant Ghimire");
	            sb.append('\n');

	            pw.write(sb.toString());
	            pw.close();
	            System.out.println("done!");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        });
        
        
        
    	
    	//listVals.forEach((k,v) -> System.out.println("Key: " + k +" Value: "+v));
        grid.setSelectionMode(SelectionMode.SINGLE);
        layout.addComponents(titleField,firstNameField,lastNameField,submitBtn,removeSelected,btnViewGVals);
        gridLayout.addComponent(layout,0,0);
        gridLayout.addComponent(grid);
        
        
        
        setContent(gridLayout);
    }

	private void clearFields(TextField titleField, TextField firstNameField, TextField lastNameField) {
		titleField.clear();
		firstNameField.clear();
		lastNameField.clear();
	}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    
}
