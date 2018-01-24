
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    package treegui;
    import java.util.Stack;


    public class Treegui{

    /**
     * @param args the command line arguments
     */
    static String input ;
    static Stack datastack= new Stack();
    static int sum=0 ;
    static Stack calstack = new Stack();
    
    public static class Node {
        Node left;
        Node right;
        char data;
        Node parent;

        public Node(char n){
            data=n;
        }
        public Node(){

        }
    }
    
    public static Node inor=new Node();
    public static void main(String[] args) {
        input = args[0];
        //String input = "251-*32*+";
        //sum=9;
        
        int i;
        for( i=0;i<input.length();i++){
            inor=inorder(new Node(input.charAt(i)));
            //System.out.println(sum);
        }
        infix(inor);
        System.out.println("="+sum);
        GUI.main(args);
        
        
    }
    
    public static Node inorder(Node n){
        if(n.data!='+'&&n.data!='-'&&n.data!='*'&&n.data!='/'){
        //if(n.data=='+'){
            datastack.push(n);
            calstack.push(n);
           // System.out.println("infixif");
        }else/* if(n.data=='+'||n.data=='-'||n.data=='*'||n.data=='/')*/{
           // inorder( (Node) datastack.pop());
            Node c1=(Node) datastack.pop();
            Node c2=(Node) datastack.pop();
            c1.parent=n;
            c2.parent=n;
            n.left=c2;
            n.right=c1;
            datastack.push(n);
            //calculate(c1);
            //calculate(c2);
            calculate(n);
            //System.out.println("infixelse");
            
        }
        
       return n; 
    }
    
    public static void infix(Node n){
           /* System.out.print("(");
            if(n.data=='+') System.out.print("=");*/
        /*if(n.left!=null) inorder(n.left);
        if(n.right!=null) inorder(n.right);
        System.out.print("(");
        System.out.print(n.data);
        System.out.print(")");*/
        if(n==null){              
        return;
        }
        else{
                infix(n.left); 
                if(n.parent == null||n.parent.parent==null)
                {  
                }
                else if(n==n.parent.left)
                {
                    System.out.print("(");
                }
                System.out.print(n.data);
                infix(n.right);
                if(n.parent == null||n.parent.parent==null)
                {  
                }
                else if(n==n.parent.right)
                {
                    System.out.print(")");
                }
        }
        
        
        
    }
    
    public static void calculate(Node n){
        //calstack.push(n);
        if(n.data!='+'&&n.data!='-'&&n.data!='*'&&n.data!='/'/*||datastack.empty()*/){
            //calstack.push(n);
           // System.out.println("calif");
        }else{
            //Character.getNumericValue(a);
            Node ca1=(Node) calstack.pop();
            Node ca2=(Node) calstack.pop();
            int canum1 = Character.getNumericValue(ca1.data);
            int canum2 = Character.getNumericValue(ca2.data);
            if(n.data=='+')sum=canum2+canum1;
            else if(n.data=='-')sum=canum2-canum1;
            else if(n.data=='*')sum=canum2*canum1;
            else if(n.data=='/')sum=canum2/canum1;
            calstack.push(new Node(Character.forDigit(sum,10)));
            //System.out.println("calelse");
            
        }
        //return 0;
    }
    
}
    


