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

    @DataProvider(name = "arrayTopics")
    public Object[][] arrayTopics() {
        return new Object[][] {
                {"Arrays in Python"},
                {"Arrays Using List"},
                {"Basic Operations in Lists"},
                {"Applications of Array"}
        };
    }

    @DataProvider(name = "arrayPracticeQuestions")
    public Object[][] arrayPracticeQuestions() {
        return new Object[][] {
                {"Arrays in Python"              ,   "Search the array"  },
                {"Arrays in Python"              ,   "Max Consecutive Ones"  },
                {"Arrays in Python"              ,   "Find Numbers with Even Number of Digits"  },
                {"Arrays in Python"              ,   "Squares of  a Sorted Array"  },
                {"Arrays Using List"             ,   "Search the array"  },
                {"Arrays Using List"             ,   "Max Consecutive Ones"  },
                {"Arrays Using List"             ,   "Find Numbers with Even Number of Digits"  },
                {"Arrays Using List"             ,   "Squares of  a Sorted Array"  },
                {"Basic Operations in Lists"     ,   "Search the array"  },
                {"Basic Operations in Lists"     ,   "Max Consecutive Ones"  },
                {"Basic Operations in Lists"     ,   "Find Numbers with Even Number of Digits"  },
                {"Basic Operations in Lists"     ,   "Squares of  a Sorted Array"  },
                {"Applications of Array"         ,   "Search the array"  },
                {"Applications of Array"         ,   "Max Consecutive Ones"  },
                {"Applications of Array"         ,   "Find Numbers with Even Number of Digits"  },
                {"Applications of Array"         ,   "Squares of  a Sorted Array"  },
        };
    }
    
    
}


