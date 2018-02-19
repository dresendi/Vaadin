package my.vaadintest.appABC;

import java.util.Iterator;

import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;

public class UIComponents {

	Component findComponentWithId(HasComponents root, String id) {
	    for(Component child : root) {
	        if(id.equals(child.getId())) {
	            // found it!
	            return child;
	        } else if(child instanceof HasComponents) {
	            // recursively go through all children that themselves have children
	            //return findComponentWithId((HasComponents) child, id); <--bug here(cause short cut)
	            Component ret= findComponentWithId((HasComponents) child, id);                if(ret!=null)return ret;
	            
	        }
	    }
	    // none was found
	    return null;
	}
}
