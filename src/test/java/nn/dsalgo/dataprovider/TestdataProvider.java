package nn.dsalgo.dataprovider;

import org.testng.annotations.DataProvider;

public class TestdataProvider {
    @DataProvider(name = "topicsDS")
    public Object[][] topicsProviderDS() {
        return new Object[][] {
                {"Time Complexity"}
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


