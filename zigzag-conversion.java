// Time complexity O(N)
// space complxity O(N) [list of string builders] + O(N) [result]

/*
* Thought process
We just need to traverse through the string and add it to another matrix. We are also ignoring the spaces
We can use a 2d matrix. However, we do not know the number of columns and they can change based on row and size of the string. 
Mayb we can use List<List> so we do not worry about number of columns and keep adding
We will need to indicate when we are going up and when we are going down. This can be done by two conditions
 ----- if(row > numrow-1) and if(row <= 0) ----
Going down we will keep the column same and increment the row [row++][column] 
Going up we will decrement the row and increment the column[row--][column++]

Since we ignore spaces, we can just append to the List<List> and not worry about column incrementing or going zigzag. This is a trick question. Zigzag is just to throw us off.  
List<List> is same as List<String> (since string is internally a char array) and since String is immutable in Java lets use List<StringBuilder>
*
*
*
*
**/
class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        
        // build a list of string builders as the number of rows.
        List<StringBuilder> sbList = new ArrayList<StringBuilder>();
        for(int idx = 0; idx <numRows; idx++){
            sbList.add(new StringBuilder());
        }
        
        // boolean flag to indicate if we are going up or down
        // this is the key condition that you need to hash out
        boolean goingUp = true;
        
        // row index
        int currentRow = 0;
        for(int i = 0; i < s.length(); i++) {
            
            char currentChar = s.charAt(i);
            
            // add or subtract based on the direction we are going in
            if(goingUp) {
                sbList.get(currentRow++).append(currentChar);    
            } else {
                sbList.get(currentRow--).append(currentChar);
            }
            

            goingUp = currentRow >= numRows-1 || currentRow == 0 ? !goingUp : goingUp;
        }
        
        StringBuilder result = new StringBuilder();
        for(StringBuilder sb : sbList) {
            result.append(sb.toString());
        }
        
        return result.toString();
    }
}