



/**
 * This application that requires the following additional files:
 *   TreeDemoHelp.html
 *    arnold.html
 *    bloch.html
 *    chan.html
 *    jls.html
 *    swingtutorial.html
 *    tutorial.html
 *    tutorialcont.html
 *    vm.html
 */

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;
//import treegui.treegui.Node;
import java.util.Stack;

public class GUI extends JPanel
        implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
    public static Node root= Homework1.inor;
    public GUI() {
        super(new GridLayout(1,0));

        //Create the nodes.
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode(root.data);
        createNodes(top,root);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);

        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();
        res = "";
        //String all;
        // all=all+

        htmlPane.setText(infixui(node));



    }
    public static String res="";
    public static String infixui(DefaultMutableTreeNode n){

        if(n.toString()=="+"||n.toString()=="-"||n.toString()=="*"||n.toString()=="/"){

        }

        if(n==null){
            return null;
        }else{
            if(n.isLeaf()){
                res=res+n.toString();
            }else{
                infixui((DefaultMutableTreeNode) n.getChildAt(0));
                //DefaultMutableTreeNode test = (DefaultMutableTreeNode) n.getChildAt(0);
                if(n.getParent() == null||n.getParent().getParent()==null)
                {
                }
                else if(n==n.getParent().getChildAt(0))
                {
                    //System.out.print("(");
                    //res[res.length+1]=("(");
                    res=res+"(";

                }
                //System.out.print(n);
                res=res+n.toString();
                //res=res+test.toString();
                infixui((DefaultMutableTreeNode) n.getChildAt(1));
                //test = (DefaultMutableTreeNode) n.getChildAt(1);
                //res=res+test.toString();
                if(n.getParent() == null||n.getParent().getParent()==null)
                {
                }
                else if(n==n.getParent().getChildAt(1))
                {
                    //System.out.print(")");
                    res=res+")";
                }
            }
        }


        return res;
    }







    private void createNodes(DefaultMutableTreeNode top,Node n) {
        DefaultMutableTreeNode left = new DefaultMutableTreeNode(n.left.data);
        DefaultMutableTreeNode right = new DefaultMutableTreeNode(n.right.data);
        //top.add(right);
        top.add(left);
        top.add(right);
        if(n.left.left!=null||n.left.right!=null) createNodes(left,n.left);
        if(n.right.left!=null||n.right.right!=null) createNodes(right,n.right);




    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new GUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }





    static int sum=0 ;
    static Stack calstack = new Stack();
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

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
