package nn.dsalgo.dataprovider;

import org.testng.annotations.DataProvider;

public class TestdataProvider {
    @DataProvider(name = "topicsDS")
    public Object[][] topicsProviderDS() {
        return new Object[][] {
                {"Time Complexity"}
        };
    }
<<<<<<< HEAD
        @DataProvider(name = "StackTopics")
        public Object[][] StackTopicsProvider() {
        	return new Object[][] {{"Operations in Stack"},{"Implementation"},{"Applications"}};
  
       }  
}
=======

    @DataProvider(name = "inputCodeForTopicsInDS")
    public Object[][] inputCodeForDSTopics()
    {
        return new Object[][] {
                {"Time Complexity","ValidCode"},
                {"Time Complexity","InvalidCode"}
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
    
    
    
}


>>>>>>> eb6895ccf752b390805167c5b5c5a54313e92eda
