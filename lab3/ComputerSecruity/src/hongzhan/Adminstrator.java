package hongzhan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Adminstrator {
    
    Map<String, ArrayList<String>> usermap = new HashMap<>();
    Map<String, String> adminmap = new HashMap<>();
    ArrayList<String> nowuser = new ArrayList<>();
    
    JFrame JF1,JF2;
    JPanel JP1,JP2;
    JButton JB1;
    JButton JB2,JB3,JB4,JB5;//存款、查询、取款、返回
    JLabel JL1,JL2,JL3,JL4,JL5;
    JTextField JTF1,JTF2;
    
    @SuppressWarnings("resource")
    public void read() throws IOException{
        File file1 = new File("user.txt");
        File file2 = new File("admin.txt");
        BufferedReader reader1 = null;
        BufferedReader reader2 = null;
        reader1 = new BufferedReader(new FileReader(file1));
        reader2 = new BufferedReader(new FileReader(file2));
        String temp1,temp2;
        while((temp1 = reader1.readLine()) != null){
            String[] ss = temp1.split(" ");
            ArrayList<String> list = new ArrayList<>();
            list.add(ss[1]);
            list.add(ss[2]);
            usermap.put(ss[0], list);
        }
        while((temp2 = reader2.readLine()) != null){
            String[] ss = temp2.split(" ");
            adminmap.put(ss[0], ss[1]);
        }
        
        reader1.close();
        reader2.close();
        
    }
    
    public void init() {
        // TODO Auto-generated constructor stub
        JF1 = new JFrame("login");
        JP1 = new JPanel();
        JB1 = new JButton("确定");
//        JB2 = new JButton("密码");
        JTF1 = new JTextField();
        JTF2 = new JTextField();
        
        JL1 = new JLabel("账号");
        JL2 = new JLabel("密码");
        JL1.setBounds(10,20,80,25);
        JL2.setBounds(10,50,80,25);
        JP1.add(JL1);
        JP1.add(JL2);
        
        //JL1.setBounds(10,20,80,25);
        //JP1.add(JL1);
        
        JTF1.setBounds(100, 20, 165, 25);
        JP1.add(JTF1);
        JTF2.setBounds(100, 50, 165, 25);
        JP1.add(JTF2);
        
        JB1.setBounds(10,80,80,25);
        JP1.add(JB1);
        JP1.setLayout(null);
        
        /*设置JButton监听*/
        JB1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
               // System.out.println("账号："+JTF1.getText());
              //  System.out.println("密码:"+JTF2.getText());
                if(usermap.containsKey(JTF1.getText())){
                    if(usermap.get(JTF1.getText()).get(0).equals(JTF2.getText())){
                        nowuser.add(JTF1.getText());
                        nowuser.add(JTF2.getText());
                        nowuser.add(usermap.get(JTF1.getText()).get(1));
                        JF1.dispose();
                        operate();
                    }else{
                        //密码错误
                        JF1.dispose();
                        Error("密码错误", 0);
                    }
                }else{
                    //用户名错误
                    JF1.dispose();
                    Error("用户名错误", 0);
                }
            }
        });
        JF1.add(JP1);
        
        JF1.setVisible(true);
        JF1.setSize(350, 200);
        JF1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public void approve(String name, String money, int flag){//管理员认证界面
        JF2 = new JFrame("管理员认证");              //flag=0存款              flag=1取款
        JP1 = new JPanel();
        JB1 = new JButton("确定");
//        JB2 = new JButton("密码");
        JTF1 = new JTextField();
        JTF2 = new JTextField();
        
        String s = flag==0 ? "存款 " : "取款";
        
        JL1 = new JLabel("账号");
        JL2 = new JLabel("密码");
        JL3 = new JLabel(name);
        JL4 = new JLabel(s);
        JL5 = new JLabel(money);
        
        JL1.setBounds(10,20,80,25);
        JL2.setBounds(10,50,80,25);
        JL3.setBounds(10,80,80,25);
        JL4.setBounds(100,80,80,25);
        JL5.setBounds(190, 80, 80, 25);
        JP1.add(JL1);
        JP1.add(JL2);
        JP1.add(JL3);
        JP1.add(JL4);
        JP1.add(JL5);

        JTF1.setBounds(100, 20, 165, 25);
        JP1.add(JTF1);
        JTF2.setBounds(100, 50, 165, 25);
        JP1.add(JTF2);
        
        JB1.setBounds(10,110,80,25);
        JP1.add(JB1);
        JP1.setLayout(null);
        
        /*设置JButton监听*/
        JB1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
               // System.out.println("账号："+JTF1.getText());
              //  System.out.println("密码:"+JTF2.getText());
                if(adminmap.containsKey(JTF1.getText())){
                    if(adminmap.get(JTF1.getText()).equals(JTF2.getText())){
                        JF1.dispose();
                        JF2.dispose();
                        double d;
                        if(flag==1){
                            d = Double.valueOf(nowuser.get(2).toString()) - Double.valueOf(money.toString());                                
                        }else{
                            d = Double.valueOf(nowuser.get(2).toString()) + Double.valueOf(money.toString());
                        }
                        nowuser.set(2, Double.toString(d));
                        usermap.get(nowuser.get(0)).set(1, Double.toString(d));
                        change(Double.toString(d));
                        //新的界面
                        operate();
                    }else{
                        JF1.dispose();
                        JF2.dispose();
                        Error("密码错误", 1);
                    }
                }else{
                    JF1.dispose();
                    JF2.dispose();
                    Error("用户名错误", 1);
                }
            }
        });
        JF2.add(JP1);
        
        JF2.setVisible(true);
        JF2.setBounds(400,400,350,200);
        JF2.setSize(350, 200);
        JF2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public void Error(String error, int flag) {//错误界面，flag=0返回init   flag=1返回operate
        JF1 = new JFrame("错误");
        JP1 = new JPanel();
        JB1 = new JButton("确定");
        JP1.setLayout(null);
        JL1 = new JLabel(error);
        
        JL1.setBounds(130,20,100,60);
        JP1.add(JL1);
        
        JB1.setBounds(135,100,80,50);
        JP1.add(JB1);
        JB1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JF1.dispose();
                if(flag==0){
                    init();
                }else{
                    operate();
                }
            }
        });
        
        JF1.add(JP1);
        
        JF1.setVisible(true);
        JF1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JF1.setBounds(400,400,350,200);
    }
    
    public void change(String value) {
        try {
            FileWriter writer = new FileWriter("user.txt");
            for(Map.Entry<String, ArrayList<String>> entry : usermap.entrySet()){
                ArrayList<String> list1 = new ArrayList<>();
                if(entry.getKey().equals(nowuser.get(0))){
                    writer.write(nowuser.get(0) + " ");
                    writer.write(nowuser.get(1) + " ");
                    writer.write(nowuser.get(2) + " \n");
                }else{
                    writer.write(entry.getKey() + " ");
                    writer.write(entry.getValue().get(0) + " ");
                    writer.write(entry.getValue().get(1) + " \n");
                }
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void withdraw() {//取款
        JF1 = new JFrame("取款");
        JP1 = new JPanel();
        JP1.setLayout(null);
        
        JL1 = new JLabel("金额");
        JL1.setBounds(10, 20, 80, 25);
        JP1.add(JL1);
        
        JB1 = new JButton("确认");
        JB1.setBounds(10,50,80,25);
        JB1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(Double.valueOf(nowuser.get(2).toString()) >= Double.valueOf(JTF1.getText().toString())){
                    approve(nowuser.get(0), JTF1.getText(), 1);
                }  else{
                    //钱不够
                    JF1.dispose();
                    Error("钱不够", 1);
                }
            }
        });
        JP1.add(JB1);
        
        JB2 = new JButton("返回");
        JB2.setBounds(100,50,80,25);
        JB2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JF1.dispose();
                operate();
            }
        });
        JP1.add(JB2);
        
        JTF1 = new JTextField();
        JTF1.setBounds(100, 20, 165, 25);
        JP1.add(JTF1);

        JF1.add(JP1);
        JF1.setVisible(true);
        JF1.setSize(350, 200);
        JF1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public void operate() {
        JF1 = new JFrame("操作");
        JP2 = new JPanel();
        JF1.add(JP2);
        
        JP2.setLayout(null);
        JB2 = new JButton("取款");
        JB2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JF1.dispose();
                withdraw();//取款界面
            }
        });
        JB2.setBounds(10,20,80,25);
        
        JB3 = new JButton("存款");
        JB3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JF1.dispose();
                deposit();
            }
        });
        JB3.setBounds(10,50,80,25);
        
        JB4 = new JButton("查询");
        JB4.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JF1.dispose();
                find();
            }
        });
        JB4.setBounds(10,80,80,25);
        JB5 = new JButton("返回");
        JB5.setBounds(10,110,80,25);
        JB5.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JF1.dispose();
                init();
            }
        });
        
        JP2.add(JB2);
        JP2.add(JB3);
        JP2.add(JB4);
        JP2.add(JB5);
        
        JF1.setSize(350, 200);
        JF1.setVisible(true);
        JF1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public void find() {
        JF2 = new JFrame("查询");
        JP1 = new JPanel();
        JL1 = new JLabel(nowuser.get(0));
        JL2 = new JLabel(nowuser.get(2));
        JB1 = new JButton("确认");
        
        JL1.setBounds(10,20,80,25);
        JL2.setBounds(10,100,80,25);
        JB1.setBounds(10,50,80,25);
        JB1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JF2.dispose();
                operate();
            }
        });
        
        JP1.add(JL1);
        JP1.add(JL2);
        JP1.add(JB1);
        
        JF2.add(JP1);
        JF2.setVisible(true);
        JF2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JF2.setSize(350, 200);
    }
    
    public void deposit() {//存款
        JF1 = new JFrame("存款");
        JP1 = new JPanel();
        JP1.setLayout(null);
        
        JL1 = new JLabel("金额");
        JL1.setBounds(10, 20, 80, 25);
        JP1.add(JL1);
        
        JB1 = new JButton("确认");
        JB1.setBounds(10,50,80,25);
        JB1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub          
                approve(nowuser.get(0), JTF1.getText(), 0);
            }
        });
        JP1.add(JB1);
        
        JB2 = new JButton("返回");
        JB2.setBounds(100,50,80,25);
        JB2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JF1.dispose();
                operate();
            }
        });
        JP1.add(JB2);
        
        JTF1 = new JTextField();
        JTF1.setBounds(100, 20, 165, 25);
        JP1.add(JTF1);

        JF1.add(JP1);
        JF1.setVisible(true);
        JF1.setSize(350, 200);
        JF1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        Adminstrator adminstrator = new Adminstrator();
        try {
            adminstrator.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
//        System.out.println(adminstrator.usermap.size());
//        System.out.println(adminstrator.adminmap.size());
        adminstrator.init();
    }

}
