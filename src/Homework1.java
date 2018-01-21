/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package homework1;

import java.util.Stack;
/**
 *
 * @author User
 */
public class Homework1 {

    /**
     * @param args the command line arguments
     */
    
        //static tree newtree = new tree();
        static Node root;
        static Stack test = new Stack();
        static String[] infixx;
    //public static check
    
    public static int calculate(Node n){
        int result=0;
        int leftdata=Integer.parseInt(n.left.data);
        int rightdata=Integer.parseInt(n.right.data);
        if((checkoperate(n.right))&&checkoperate(n.left)){
            result=result+calculate(n.right);
            result=result+calculate(n.left);
            
        }
        else if( checkoperate(n.right)){
            //result=result+calculate(n.right)+calculate(n.left);
            result=result+calculate(n.right);
            if(n.data=="+")result=leftdata+result;
            else if(n.data=="-")result=leftdata-result;
            else if(n.data=="*")result=leftdata*result;
            else if(n.data=="/")result=leftdata/result;
            
        }else if(checkoperate(n.left)){
            result=result+calculate(n.left);
            if(n.data=="+")result=rightdata+result;
            else if(n.data=="-")result=rightdata-result;
            else if(n.data=="*")result=rightdata*result;
            else if(n.data=="/")result=rightdata/result;
            
        }else{
            if(n.data=="+")result=leftdata+rightdata;
            else if(n.data=="-")result=leftdata-rightdata;
            else if(n.data=="*")result=leftdata*rightdata;
            else if(n.data=="/")result=leftdata/rightdata;
        }
        return result; 
    }
    
    public static boolean checkoperate(Node n){
        if(n.data=="+"||n.data=="+"||n.data=="*"||n.data=="/"){
            return true;
        }else{
            return false;
        }
    }

    
    public static Node infix(Node n){
        int i=0;
        while(i<infixx.length){
            Node num = new Node(infixx[i]);
            if(test.empty()||(n.data!="+"&&n.data!="-"&&n.data!="*"&&n.data!="/")){
                test.push(n);
            }else{
                Node child1 = (Node) test.pop();
                Node child2 = (Node) test.pop();
                n.left = child1;
                n.right = child2;
                child1.parent= n;
                child2.parent= n;
                test.push(n);
         }
        }
    return (Node) test.pop();
        
    }
    
    public static void inorder(Node n){
        if(n==null)return;
        inorder(n.left);
        if(n==n.parent.left){
            System.out.print("(");
        }
        System.out.print(n.data);
        inorder(n.right);
        if(n==n.parent.right){
            System.out.print(")");
        }
    }
  /*  public static Node makestack(String[] args){
        int i=0;
        while(args.length > i){
                String input = args[i];
               // int inputi = Integer.parseInt(input);
                Node n2 = new Node(input);
                infix(n2);
            i++;
        } 
    }*/
    
    public static void main(String[] args) {
        infixx = args;
        //Node n = new Node(0);
       
      /*  int i=0;
        while(args.length > i){
                String input = args[i];
               // int inputi = Integer.parseInt(input);
                Node n2 = new Node(input);
                infix(n2);
            i++;
        }   */
        //newtree = (Node) test.pop();
        //return newtree;
        
    }
    
}
