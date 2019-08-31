package photoviewer;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class PhotoViewer extends JFrame {
    JFrame jf,jfFullScreen;
    JPanel panel,panelLeft,panelImage,panelControl;
    JSplitPane splitPaneV,splitPaneH;
    
    JLabel j1,j1Full;
    ImageIcon img;
    
    JMenuBar mb;
    JMenu file,edit,settings,help;
    JMenuItem open, openFolder,cut,copy,paste,exit;
    JFileChooser chooser;
    JFileChooser Folderchooser;
    JButton btnNext,btnPrev,btnStart,btnStop,btnFullScreen;
    
    Timer timer;
    
    int listCount;
    JList list;
    DefaultListModel model;
    
    public PhotoViewer(){
        setFrameWindow();
        createMenu();
        initComp();
    }
    final void setFrameWindow(){
        jf = this;
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Photo Viewer");
        //setLayout(null);
           
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);
        
        panelLeft = new JPanel();
        panelLeft.setLayout(new BorderLayout());
        
        panelImage = new JPanel();
        panelImage.setLayout(new BorderLayout());
        panelImage.setBackground(Color.black);
        
        
        panelControl = new JPanel();
        panelControl.setLayout(new FlowLayout());
        
        splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneV.setDividerLocation(600);
        splitPaneV.setLeftComponent(panelImage);
        splitPaneV.setRightComponent(panelControl);
        
        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneH.setDividerLocation(200);
        splitPaneH.setLeftComponent(panelLeft);
        splitPaneH.setRightComponent(splitPaneV);
        panel.add(splitPaneH,BorderLayout.CENTER);
         
    }
    final void initComp()
    {
        model = new DefaultListModel();
        list = new JList(model);
        panelLeft.add(list,BorderLayout.CENTER);
        
        j1 = new JLabel();
        j1.setHorizontalAlignment(JLabel.CENTER);
        j1.setVerticalAlignment(JLabel.CENTER);
        panelImage.add(j1,BorderLayout.CENTER);
        
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
         //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                img = new ImageIcon(new ImageIcon(list.getSelectedValue().toString()).getImage().getScaledInstance(500, 350, Image.SCALE_DEFAULT));
                j1.setIcon(img);
            }
            
        });
        btnPrev=new JButton("Previous");
        panelControl.add(btnPrev);
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(list.getSelectedIndex()>0 )
                    list.setSelectedIndex(list.getSelectedIndex()-1);
                else
                    list.setSelectedIndex(list.getSelectedIndex()-1);
             
            }
        });
        
        btnNext=new JButton("Next");
        panelControl.add(btnNext);
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(list.getSelectedIndex()<listCount-1)
                    list.setSelectedIndex(list.getSelectedIndex()+1);
                else
                    list.setSelectedIndex(0);
             
            }
        });
        
        btnStart=new JButton("Start");
        panelControl.add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                timer.start();
             
            }
        });
        
        btnStop=new JButton("Stop");
        panelControl.add(btnStop);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                timer.stop();
             
            }
        });
        
        timer = new Timer(3000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               if(list.getSelectedIndex()<listCount-1)
                    list.setSelectedIndex(list.getSelectedIndex()+1);
                else
                    list.setSelectedIndex(0);// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        btnFullScreen = new JButton("Full Screen");
        panelControl.add(btnFullScreen);
        btnFullScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                jfFullScreen = new JFrame();
                jfFullScreen.setLayout(new BorderLayout());
                jfFullScreen.setUndecorated(true);
                jfFullScreen.setVisible(true);
                jfFullScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
                JLabel j1Full = new JLabel();
                jfFullScreen.add(j1Full,BorderLayout.CENTER);
                img = new ImageIcon(new ImageIcon(list.getSelectedValue().toString()).getImage().getScaledInstance(jfFullScreen.getWidth(),jfFullScreen.getHeight(),Image.SCALE_DEFAULT));
                j1Full.setIcon(img);
            }
        });
        
        
    }        
    final void createMenu()
    {
        mb = new JMenuBar();
        setJMenuBar(mb);
        
        file = new JMenu("File");
        mb.add(file);
        
        edit = new JMenu("Edit");
        mb.add(edit);

        settings = new JMenu("Settings");
        mb.add(settings);
        
        help = new JMenu("Help");
        mb.add(help);
        
        open = new JMenuItem("Open");
        file.add(open);
        
        openFolder = new JMenuItem("OpenFolder");
        file.add(openFolder);
        
        file.addSeparator();
        
        exit = new JMenuItem("Exit");
        file.add(exit);
        
        cut = new JMenuItem("Cut");
        edit.add(cut);
        
        copy = new JMenuItem("Copy");
        edit.add(copy);
        
        paste = new JMenuItem("Paste");
        edit.add(paste);
        
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               chooser = new JFileChooser();
               chooser.setCurrentDirectory((new java.io.File(".")));
               chooser.setDialogTitle("Select Image File");
               
               if(chooser.showOpenDialog(jf) == JFileChooser.APPROVE_OPTION)
               {
                   String path = chooser.getSelectedFile().toString();
                   //System.out.println(chooser.getSelectedFile().toS);
                   img = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(500, 350, Image.SCALE_DEFAULT));
                   j1.setIcon(img);
                   
               }
        
            }
        });
        openFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               Folderchooser = new JFileChooser();
               Folderchooser.setCurrentDirectory((new java.io.File(".")));
               Folderchooser.setDialogTitle("Select Folder");
               Folderchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               if(Folderchooser.showOpenDialog(jf) == JFileChooser.APPROVE_OPTION)
               {
                   File folder= new File(Folderchooser.getSelectedFile().toString());
                   File[] listofFiles = folder.listFiles();
                   model.removeAllElements();
                   for(File file: listofFiles)
                   {
                       if(file.isFile())
                       {
                           model.addElement(file.getPath());
                       }
                   }
                   listCount = listofFiles.length;
                   list.setSelectedIndex(0);
               }
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new PhotoViewer();
            }
        });
    }
    
}

