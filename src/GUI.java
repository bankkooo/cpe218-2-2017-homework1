/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package treegui;

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
import treegui.Treegui.Node;

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
    public static Node root= Treegui.inor;
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
