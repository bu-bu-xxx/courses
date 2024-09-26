// Name       : ZHONG Qiaoyang
// Student ID : 24112456g

// Complete your code for Question 4 in this file
// Hints: no more than 40 lines


// Change <yourStudentID> for both class and file

import net.datastructures.ArrayStack;

public class A1_Q4_24112456g {
    public static void main(String[] args) {
        // 2
        ArrayStack stack_old = new ArrayStack(5);
        stack_old.push(20);
        stack_old.push(19);
        stack_old.push(37);
        stack_old.push(48);
        stack_old.push(10);
        System.out.println("old ArrayStack:" + stack_old.toString());

        // 3
        ArrayStack stack_new = new ArrayStack(5);
        while (!stack_old.isEmpty()) {
            stack_new.push(stack_old.pop());
        }
        System.out.println("old ArrayStack:" + stack_old.toString());
        System.out.println("new ArrayStack:" + stack_new.toString());
        
    }
}




// 15:16