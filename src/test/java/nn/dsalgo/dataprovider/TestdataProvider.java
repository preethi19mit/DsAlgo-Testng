package nn.dsalgo.dataprovider;

import org.testng.annotations.DataProvider;

public class TestdataProvider {
	
    @DataProvider(name="Loginscenario")
    public Object[][] loginscenario()
    {
    	return new Object[][]
    			{
    		{"Missing username"},
    		{"Missing password"},
    		{"Invalid username"},
    		{"Invalid password"},
    			};
    }
    
	
    @DataProvider(name = "topicsDS")
    public Object[][] topicsProviderDS() {
        return new Object[][] {
                {"Time Complexity"}
        };
    }

        @DataProvider(name = "StackTopics")
        public Object[][] StackTopicsProvider() {
        	return new Object[][] {
        		{"Operations in Stack"},{"Implementation"},{"Applications"}
        		};
       }  
        
    @DataProvider(name = "inputCodeForTopicsInDS")
    public Object[][] inputCodeForDSTopics()
    {
        return new Object[][] {
                {"Time Complexity","ValidCode"},
                {"Time Complexity","InvalidCode"}
        };

    }
    @DataProvider(name = "topicsTree")
    public Object[][] topicsProviderTree()
    {
        return new Object[][] {
                {"Overview of Trees"},
                {"Terminologies"},
                {"Types of Trees"},
                {"Tree Traversals"},
                {"Traversals-Illustration"},
                {"Binary Trees"},
                {"Types of Binary Trees"},
                {"Implementation in Python"},
                {"Binary Tree Traversals"},
                {"Implementation of Binary Trees"},
                {"Applications of Binary trees"},
                {"Binary Search Trees"},
                {"Implementation Of BST"}
        };
    }
    @DataProvider(name = "inputCodeForTopicsInTree")
    public Object[][] inputCodeForTreeTopics()
    {
        return new Object[][] {
                {"Overview of Trees"              ,   "ValidCode"  },
                {"Terminologies"                  ,   "InvalidCode"},
                {"Types of Trees"                 ,   "AlphaNumeric"},
                {"Tree Traversals"                ,   "Numeric"     },
                {"Traversals-Illustration"        ,   "ValidCode"   },
                {"Binary Trees"                   ,   "InvalidCode" },
                {"Types of Binary Trees"          ,   "AlphaNumeric"},
                {"Implementation in Python"       ,   "Numeric"     },
                {"Binary Tree Traversals"         ,   "ValidCode"   },
                {"Implementation of Binary Trees" ,   "InvalidCode" },
                {"Applications of Binary trees"   ,   "AlphaNumeric"},
                {"Binary Search Trees"            ,   "Numeric"     },
                {"Implementation Of BST"          ,   "ValidCode"   }
        };

    }

    @DataProvider(name ="Queuetopics")
    public Object[][] queuetopics()
    {
    	return new Object[][]
    			{
    		{"Implementation of Queue in python"},
    		{"Implementation using collections deque"},
    		{"Implementation using Array"},
    		{"Queue operations"}
    			};
    }
    
    @DataProvider(name="RegisterMissingFieldsValidation")
    public Object[] RegisterMissingfieldvalidate()
    {
    	return new Object[][]{
    			{"EmptyValues", "Please fill out this field."},
    	             {"UsernameValues", "Please fill out this field."},
    	             {"PasswordValues", "Please fill out this field."},
    	             {"NoPasswordConfirmation", "Please fill out this field."},                		
               };
    }
    
    @DataProvider(name="RegisterInvalidCredentials")
    public Object[] RegisterInvalidCredentials()
    {
    	return new Object[][] {
    		{"InvalidUsername", "Username is not valid"},
            {"PwdNumericValue", "Your password can’t be entirely numeric."}
    	};
    }
    
    @DataProvider(name="RegisterValidcredentials")
    public Object[] RegisterValidCredentials()
    {
    	return new Object[][] {
    		{"ValidCredentials", "New Account Created."},
    	};
    }
    
    @DataProvider(name="RegisterMismatchPassword")
    public Object[] RegisterMismatchPassword()
    {
    	return new Object[][] {
    		{"MismatchPassword", "password_mismatch:The two password fields didn’t match."},
    	};
    }
      
    @DataProvider(name = "OptionsLinkedList")
    public Object[][] OptionsProviderLinkedList() {
        return new Object[][] {
   
        	{"Introduction"},
        	{"Creating Linked LIst"},
        	{"Types of Linked List"},
        	{"Implement Linked List in Python"},
        	{"Traversal"},
        	{"Insertion"},
        	{"Deletion"}
        
        };
    }
    
    @DataProvider(name = "OptionsHomePage")
    public Object[][] OptionsProviderHome() {
        return new Object[][] {
   
        	{"Datastructures"},
        	{"Array"},
        	{"Linkedlist"},
        	{"Stack"},
        	{"Queue"},
        	{"Tree"},
        	{"Graph"}
        };
    }
    
    @DataProvider(name = "OptionsHomeScenario")
    public Object[][] OptionsHomeScenario() {
        return new Object[][] {
   
        	
        	{"Array"},
        	{"Linkedlist"},
        	{"Stack"},
        	{"Queue"},
        	{"Tree"},
        	{"Graph"}
        };
    }
    
    
}

