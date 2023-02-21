package arcorg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final ImageIcon bgIcon = new ImageIcon("img/bf5.jpg");
	private final JLabel bg = new JLabel(bgIcon);
	private final ImageIcon img = new ImageIcon("img/icon.png");
	private final JLabel header = new JLabel(new ImageIcon("img/input.gif"));
	private final JLabel smol = new JLabel(new ImageIcon("img/h1.gif"));
	private JButton clearBtn;
	
	private JTextField input;
	
	private JTextField packed;
	private JLabel packedText;
	
	private JTextField unpacked;
	private JLabel unpackedText;
	
	private JTextField decimal;
	private JLabel decimalText;
	
	private String userInput;
	
    public Main() {
		super("Densely Packed BCD Decoder");
		setSize(1280, 720);
		setContentPane(bg);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		
		userInput = "";
		smol.setBounds(1194,0,80,15);
		header.setBounds(150, 0, 940, 106);
		add(header);
		add(smol);
		
		clearBtn = new JButton();
		clearBtn.setIcon(new ImageIcon("img/clear.png"));
		clearBtn.addActionListener(this);
		clearBtn.setOpaque(false);
		clearBtn.setContentAreaFilled(false);
		clearBtn.setBorderPainted(false);
		clearBtn.setFocusPainted(false);
		clearBtn.setBounds(1175, 720-295, 88, 252);
		clearBtn.addMouseListener(new java.awt.event.MouseAdapter() { //hover "animation"
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	clearBtn.setIcon(new ImageIcon("img/big.png"));
		    	clearBtn.setBounds(1175, 720-295, 92, 265);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	clearBtn.setIcon(new ImageIcon("img/clear.png"));
		    	clearBtn.setBounds(1175, 720-295, 88, 252);
		    }
		});
		
		
		input = new JTextField();
		Font fieldFont = new Font("Consolas", Font.PLAIN, 20);
		input.setFont(fieldFont);
        input.setBackground(Color.white);
        input.setForeground(Color.black);
        input.setBounds(350, 50+50, 615, 40);
        input.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
            	userInput = input.getText();            	
            	char[] temp = userInput.toCharArray();
            	
            	//first case
            	
            		if(temp[6] == '0') { //DENSELY PACKED BCD TO PACKED BCD
            		
            		char a = '0';
            		char b = temp[0];
            		char c = temp[1];
            		char d = temp[2];
            		char E = '0';
            		char f = temp[3];
            		char g = temp[4];
            		char h = temp[5];
            		char i = '0';
            		char j = temp[7];
            		char k = temp[8];
            		char m = temp[9];
            	
            		StringBuilder sb = new StringBuilder();
            		sb.append(a);
            		sb.append(b);
            		sb.append(c);
            		sb.append(d);
            		
            		sb.append(E);
            		sb.append(f);
            		sb.append(g);
            		sb.append(h);
            		
            		sb.append(i);
            		sb.append(j);
            		sb.append(k);
            		sb.append(m);
            		
            		String packedBCD = sb.toString();
            		StringBuilder reverse = new StringBuilder(); //decimal
            		String string = packedBCD; //copy to reverse
            		sb.delete(0, sb.length()); //reuse String Builder
            		
            		int counter = 0;
            		StringBuilder nicePrint  = new StringBuilder();
            		
            		for(int p=0;p<string.length();p++) {
            			if(counter == 4) {
            				counter = 0;
            				nicePrint.append(" ");
            			}
            				
            			nicePrint.append(string.charAt(p));
            			counter++;
            		}
            		packed.setText(nicePrint.toString());
            		
            		
            		for(int kk= string.length()-1;kk>=0;kk--)
            			reverse.append(string.charAt(kk));
            		string = reverse.toString();
            		char[] bcd = string.toCharArray();
            		int total = 0;
            		for(int x = 0; x < 3; x++){ 
            		    int n;
            			for(int y = 0; y < 4; y++){ 
            				n = (y+x) + (3*(x)); 
            				System.out.println("j:" + y + ", i:" + x + ", n:" + n);
            				if(bcd[n] == '1') {
            					total += (Math.pow(2,(y)))*Math.pow(10, (x));
                        		System.out.println("total:" + total);
            				}
            		    }
            		}
            		
            		decimal.setText(String.valueOf(total));
            		
            		
            		char temp2[] = packedBCD.toCharArray(); //PACKED TO UNPACKED
            		StringBuilder sb2 = new StringBuilder();
            		
            		sb.append('0'); //first term
            		sb.append('0');
            		sb.append('0');
            		sb.append('0');
            		sb.append(' ');
            		sb.append(temp2[0]);
            		sb.append(temp2[1]);
            		sb.append(temp2[2]);
            		sb.append(temp2[3]);
            		sb.append(' ');
            		sb2.append(sb.toString());
            		sb.delete(0, sb.length()); //reuse String Builder
            		
            		sb.append('0'); //second term
            		sb.append('0');
            		sb.append('0');
            		sb.append('0');
            		sb.append(' ');
            		sb.append(temp2[4]);
            		sb.append(temp2[5]);
            		sb.append(temp2[6]);
            		sb.append(temp2[7]);
            		sb.append(' ');
            		sb2.append(sb.toString());
            		sb.delete(0, sb.length()); //reuse String Builder
            		
            		sb.append('0'); //third term
            		sb.append('0');
            		sb.append('0');
            		sb.append('0');
            		sb.append(' ');
            		sb.append(temp2[8]);
            		sb.append(temp2[9]);
            		sb.append(temp2[10]);
            		sb.append(temp2[11]);
            		sb.append(' ');
            		sb2.append(sb.toString());
            		sb.delete(0, sb.length()); 
            		
            		unpacked.setText(sb2.toString());
            		
            	}
            		//second case
            		else if(temp[6] == '1' && temp[7] == '0' && temp[8] == '0') {
            			char a = '0';
                		char b = temp[0];
                		char c = temp[1];
                		char d = temp[2];
                		char E = '0';
                		char f = temp[3];
                		char g = temp[4];
                		char h = temp[5];
                		char i = '1';
                		char j = '0';
                		char k = '0';
                		char m = temp[9];
                	
                		StringBuilder sb = new StringBuilder();
                		sb.append(a);
                		sb.append(b);
                		sb.append(c);
                		sb.append(d);
                		sb.append(E);
                		sb.append(f);
                		sb.append(g);
                		sb.append(h);
                		sb.append(i);
                		sb.append(j);
                		sb.append(k);
                		sb.append(m);
                		
                		String packedBCD = sb.toString();
                		StringBuilder reverse = new StringBuilder(); //decimal
                		String string = packedBCD; //copy to reverse
                		sb.delete(0, sb.length()); //reuse String Builder
                		int counter = 0;
                		StringBuilder nicePrint  = new StringBuilder();
                		
                		for(int p=0;p<string.length();p++) {
                			if(counter == 4) {
                				counter = 0;
                				nicePrint.append(" ");
                			}
                				
                			nicePrint.append(string.charAt(p));
                			counter++;
                		}
                		packed.setText(nicePrint.toString());
                		
                		for(int kk= string.length()-1;kk>=0;kk--)
                			reverse.append(string.charAt(kk));
                		string = reverse.toString();
                		char[] bcd = string.toCharArray();
                		int total = 0;
                		for(int x = 0; x < 3; x++){ 
                		    int n;
                			for(int y = 0; y < 4; y++){ 
                				n = (y+x) + (3*(x)); 
                				System.out.println("j:" + y + ", i:" + x + ", n:" + n);
                				if(bcd[n] == '1') {
                					total += (Math.pow(2,(y)))*Math.pow(10, (x));
                            		System.out.println("total:" + total);
                				}
                		    }
                		}
                		
                		decimal.setText(String.valueOf(total));
                		
                		char temp2[] = packedBCD.toCharArray(); //PACKED TO UNPACKED
                		StringBuilder sb2 = new StringBuilder();
                		
                		sb.append('0'); //first term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[0]);
                		sb.append(temp2[1]);
                		sb.append(temp2[2]);
                		sb.append(temp2[3]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //second term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[4]);
                		sb.append(temp2[5]);
                		sb.append(temp2[6]);
                		sb.append(temp2[7]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //third term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[8]);
                		sb.append(temp2[9]);
                		sb.append(temp2[10]);
                		sb.append(temp2[11]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); 
                		
                		unpacked.setText(sb2.toString());
                		
                	
            		}
            			
            		//third case
            		else if(temp[6] == '1' && temp[7] == '0' && temp[8] == '1') {
            			char a = '0';
                		char b = temp[0];
                		char c = temp[1];
                		char d = temp[2];
                		char E = '1';
                		char f = '0';
                		char g = '0';
                		char h = temp[5];
                		char i = '0';
                		char j = temp[3];
                		char k = temp[4];
                		char m = temp[9];
                	
                		StringBuilder sb = new StringBuilder();
                		sb.append(a);
                		sb.append(b);
                		sb.append(c);
                		sb.append(d);
                		sb.append(E);
                		sb.append(f);
                		sb.append(g);
                		sb.append(h);
                		sb.append(i);
                		sb.append(j);
                		sb.append(k);
                		sb.append(m);
                		
                		String packedBCD = sb.toString();
                		StringBuilder reverse = new StringBuilder(); //decimal
                		String string = packedBCD; //copy to reverse
                		sb.delete(0, sb.length()); //reuse String Builder
                		int counter = 0;
                		StringBuilder nicePrint  = new StringBuilder();
                		
                		for(int p=0;p<string.length();p++) {
                			if(counter == 4) {
                				counter = 0;
                				nicePrint.append(" ");
                			}
                				
                			nicePrint.append(string.charAt(p));
                			counter++;
                		}
                		packed.setText(nicePrint.toString());
                		
                		
                		for(int kk= string.length()-1;kk>=0;kk--)
                			reverse.append(string.charAt(kk));
                		string = reverse.toString();
                		char[] bcd = string.toCharArray();
                		int total = 0;
                		for(int x = 0; x < 3; x++){ 
                		    int n;
                			for(int y = 0; y < 4; y++){ 
                				n = (y+x) + (3*(x)); 
                				System.out.println("j:" + y + ", i:" + x + ", n:" + n);
                				if(bcd[n] == '1') {
                					total += (Math.pow(2,(y)))*Math.pow(10, (x));
                            		System.out.println("total:" + total);
                				}
                		    }
                		}
                		
                		decimal.setText(String.valueOf(total));
                		
                		char temp2[] = packedBCD.toCharArray(); //PACKED TO UNPACKED
                		StringBuilder sb2 = new StringBuilder();
                		
                		sb.append('0'); //first term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[0]);
                		sb.append(temp2[1]);
                		sb.append(temp2[2]);
                		sb.append(temp2[3]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //second term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[4]);
                		sb.append(temp2[5]);
                		sb.append(temp2[6]);
                		sb.append(temp2[7]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //third term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[8]);
                		sb.append(temp2[9]);
                		sb.append(temp2[10]);
                		sb.append(temp2[11]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); 
                		
                		unpacked.setText(sb2.toString());
            		}
            		// fourth case
            		else if(temp[3] == '1' && temp[4] == '0' && temp[6] == '1' && temp[7] == '1' && temp[8] == '1') {
            			char a = '0';
                		char b = temp[0];
                		char c = temp[1];
                		char d = temp[2];
                		char E = '1';
                		char f = '0';
                		char g = '0';
                		char h = temp[5];
                		char i = '1';
                		char j = '0';
                		char k = '0';
                		char m = temp[9];
                	
                		StringBuilder sb = new StringBuilder();
                		sb.append(a);
                		sb.append(b);
                		sb.append(c);
                		sb.append(d);
                		sb.append(E);
                		sb.append(f);
                		sb.append(g);
                		sb.append(h);
                		sb.append(i);
                		sb.append(j);
                		sb.append(k);
                		sb.append(m);
                		
                		String packedBCD = sb.toString();
                		StringBuilder reverse = new StringBuilder(); //decimal
                		String string = packedBCD; //copy to reverse
                		sb.delete(0, sb.length()); //reuse String Builder
                		int counter = 0;
                		StringBuilder nicePrint  = new StringBuilder();
                		
                		for(int p=0;p<string.length();p++) {
                			if(counter == 4) {
                				counter = 0;
                				nicePrint.append(" ");
                			}
                				
                			nicePrint.append(string.charAt(p));
                			counter++;
                		}
                		packed.setText(nicePrint.toString());
                		
                		for(int kk= string.length()-1;kk>=0;kk--)
                			reverse.append(string.charAt(kk));
                		string = reverse.toString();
                		char[] bcd = string.toCharArray();
                		int total = 0;
                		for(int x = 0; x < 3; x++){ 
                		    int n;
                			for(int y = 0; y < 4; y++){ 
                				n = (y+x) + (3*(x)); 
                				System.out.println("j:" + y + ", i:" + x + ", n:" + n);
                				if(bcd[n] == '1') {
                					total += (Math.pow(2,(y)))*Math.pow(10, (x));
                            		System.out.println("total:" + total);
                				}
                		    }
                		}
                		
                		decimal.setText(String.valueOf(total));
                		
                		char temp2[] = packedBCD.toCharArray(); //PACKED TO UNPACKED
                		StringBuilder sb2 = new StringBuilder();
                		
                		sb.append('0'); //first term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[0]);
                		sb.append(temp2[1]);
                		sb.append(temp2[2]);
                		sb.append(temp2[3]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //second term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[4]);
                		sb.append(temp2[5]);
                		sb.append(temp2[6]);
                		sb.append(temp2[7]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //third term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[8]);
                		sb.append(temp2[9]);
                		sb.append(temp2[10]);
                		sb.append(temp2[11]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); 
                		
                		unpacked.setText(sb2.toString());
            		}
            		// fifth case
            		else if(temp[6] == '1' && temp[7] == '1' && temp[8] == '0') {
            			char a = '1';
                		char b = '0';
                		char c = '0';
                		char d = temp[2];
                		char E = '0';
                		char f = temp[3];
                		char g = temp[4];
                		char h = temp[5];
                		char i = '0';
                		char j = temp[0];
                		char k = temp[1];
                		char m = temp[9];
                	
                		StringBuilder sb = new StringBuilder();
                		sb.append(a);
                		sb.append(b);
                		sb.append(c);
                		sb.append(d);
                		sb.append(E);
                		sb.append(f);
                		sb.append(g);
                		sb.append(h);
                		sb.append(i);
                		sb.append(j);
                		sb.append(k);
                		sb.append(m);
                		
                		String packedBCD = sb.toString();
                		StringBuilder reverse = new StringBuilder(); //decimal
                		String string = packedBCD; //copy to reverse
                		sb.delete(0, sb.length()); //reuse String Builder
                		int counter = 0;
                		StringBuilder nicePrint  = new StringBuilder();
                		
                		for(int p=0;p<string.length();p++) {
                			if(counter == 4) {
                				counter = 0;
                				nicePrint.append(" ");
                			}
                				
                			nicePrint.append(string.charAt(p));
                			counter++;
                		}
                		packed.setText(nicePrint.toString());
                		
                		
                		for(int kk= string.length()-1;kk>=0;kk--)
                			reverse.append(string.charAt(kk));
                		string = reverse.toString();
                		char[] bcd = string.toCharArray();
                		int total = 0;
                		for(int x = 0; x < 3; x++){ 
                		    int n;
                			for(int y = 0; y < 4; y++){ 
                				n = (y+x) + (3*(x)); 
                				System.out.println("j:" + y + ", i:" + x + ", n:" + n);
                				if(bcd[n] == '1') {
                					total += (Math.pow(2,(y)))*Math.pow(10, (x));
                            		System.out.println("total:" + total);
                				}
                		    }
                		}
                		
                		decimal.setText(String.valueOf(total));
                		
                		char temp2[] = packedBCD.toCharArray(); //PACKED TO UNPACKED
                		StringBuilder sb2 = new StringBuilder();
                		
                		sb.append('0'); //first term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[0]);
                		sb.append(temp2[1]);
                		sb.append(temp2[2]);
                		sb.append(temp2[3]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //second term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[4]);
                		sb.append(temp2[5]);
                		sb.append(temp2[6]);
                		sb.append(temp2[7]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //third term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[8]);
                		sb.append(temp2[9]);
                		sb.append(temp2[10]);
                		sb.append(temp2[11]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); 
                		
                		unpacked.setText(sb2.toString());
            		}
            		// sixth case
            		else if(temp[3] == '0' && temp[4] == '1' && temp[6] == '1' && temp[7] == '1' && temp[8] == '1') {
            			char a = '1';
                		char b = '0';
                		char c = '0';
                		char d = temp[2];
                		char E = '0';
                		char f = temp[0];
                		char g = temp[1];
                		char h = temp[5];
                		char i = '1';
                		char j = '0';
                		char k = '0';
                		char m = temp[9];
                	
                		StringBuilder sb = new StringBuilder();
                		sb.append(a);
                		sb.append(b);
                		sb.append(c);
                		sb.append(d);
                		sb.append(E);
                		sb.append(f);
                		sb.append(g);
                		sb.append(h);
                		sb.append(i);
                		sb.append(j);
                		sb.append(k);
                		sb.append(m);
                		
                		String packedBCD = sb.toString();
                		StringBuilder reverse = new StringBuilder(); //decimal
                		String string = packedBCD; //copy to reverse
                		sb.delete(0, sb.length()); //reuse String Builder
                		int counter = 0;
                		StringBuilder nicePrint  = new StringBuilder();
                		
                		for(int p=0;p<string.length();p++) {
                			if(counter == 4) {
                				counter = 0;
                				nicePrint.append(" ");
                			}
                				
                			nicePrint.append(string.charAt(p));
                			counter++;
                		}
                		packed.setText(nicePrint.toString());
                		
                		for(int kk= string.length()-1;kk>=0;kk--)
                			reverse.append(string.charAt(kk));
                		string = reverse.toString();
                		char[] bcd = string.toCharArray();
                		int total = 0;
                		for(int x = 0; x < 3; x++){ 
                		    int n;
                			for(int y = 0; y < 4; y++){ 
                				n = (y+x) + (3*(x)); 
                				System.out.println("j:" + y + ", i:" + x + ", n:" + n);
                				if(bcd[n] == '1') {
                					total += (Math.pow(2,(y)))*Math.pow(10, (x));
                            		System.out.println("total:" + total);
                				}
                		    }
                		}
                		
                		decimal.setText(String.valueOf(total));
                		
                		char temp2[] = packedBCD.toCharArray(); //PACKED TO UNPACKED
                		StringBuilder sb2 = new StringBuilder();
                		
                		sb.append('0'); //first term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[0]);
                		sb.append(temp2[1]);
                		sb.append(temp2[2]);
                		sb.append(temp2[3]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //second term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[4]);
                		sb.append(temp2[5]);
                		sb.append(temp2[6]);
                		sb.append(temp2[7]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //third term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[8]);
                		sb.append(temp2[9]);
                		sb.append(temp2[10]);
                		sb.append(temp2[11]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); 
                		
                		unpacked.setText(sb2.toString());
            		}
            		// seventh case
            		else if(temp[3] == '0' && temp[4] == '0' && temp[6] == '1' && temp[7] == '1' && temp[8] == '1') {
            			char a = '1';
                		char b = '0';
                		char c = '0';
                		char d = temp[2];
                		char E = '1';
                		char f = '0';
                		char g = '0';
                		char h = temp[5];
                		char i = '0';
                		char j = temp[0];
                		char k = temp[1];
                		char m = temp[9];
                	
                		StringBuilder sb = new StringBuilder();
                		sb.append(a);
                		sb.append(b);
                		sb.append(c);
                		sb.append(d);
                		sb.append(E);
                		sb.append(f);
                		sb.append(g);
                		sb.append(h);
                		sb.append(i);
                		sb.append(j);
                		sb.append(k);
                		sb.append(m);
                		
                		String packedBCD = sb.toString();
                		StringBuilder reverse = new StringBuilder(); //decimal
                		String string = packedBCD; //copy to reverse
                		sb.delete(0, sb.length()); //reuse String Builder
                		int counter = 0;
                		StringBuilder nicePrint  = new StringBuilder();
                		
                		for(int p=0;p<string.length();p++) {
                			if(counter == 4) {
                				counter = 0;
                				nicePrint.append(" ");
                			}
                				
                			nicePrint.append(string.charAt(p));
                			counter++;
                		}
                		packed.setText(nicePrint.toString());
                		
                		for(int kk= string.length()-1;kk>=0;kk--)
                			reverse.append(string.charAt(kk));
                		string = reverse.toString();
                		char[] bcd = string.toCharArray();
                		int total = 0;
                		for(int x = 0; x < 3; x++){ 
                		    int n;
                			for(int y = 0; y < 4; y++){ 
                				n = (y+x) + (3*(x)); 
                				System.out.println("j:" + y + ", i:" + x + ", n:" + n);
                				if(bcd[n] == '1') {
                					total += (Math.pow(2,(y)))*Math.pow(10, (x));
                            		System.out.println("total:" + total);
                				}
                		    }
                		}
                		
                		decimal.setText(String.valueOf(total));
                		
                		char temp2[] = packedBCD.toCharArray(); //PACKED TO UNPACKED
                		StringBuilder sb2 = new StringBuilder();
                		
                		sb.append('0'); //first term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[0]);
                		sb.append(temp2[1]);
                		sb.append(temp2[2]);
                		sb.append(temp2[3]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //second term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[4]);
                		sb.append(temp2[5]);
                		sb.append(temp2[6]);
                		sb.append(temp2[7]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //third term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[8]);
                		sb.append(temp2[9]);
                		sb.append(temp2[10]);
                		sb.append(temp2[11]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); 
                		
                		unpacked.setText(sb2.toString());
            		}
            		// eighth case
            		else if(temp[0] == '0' && temp[1] == '0' && temp[3] == '1' && temp[4] == '1' && temp[6] == '1' && temp[7] == '1' && temp[8] == '1') {
            			char a = '1';
                		char b = '0';
                		char c = '0';
                		char d = temp[2];
                		char E = '1';
                		char f = '0';
                		char g = '0';
                		char h = temp[5];
                		char i = '1';
                		char j = '0';
                		char k = '0';
                		char m = temp[9];
                	
                		StringBuilder sb = new StringBuilder();
                		sb.append(a);
                		sb.append(b);
                		sb.append(c);
                		sb.append(d);
                		sb.append(E);
                		sb.append(f);
                		sb.append(g);
                		sb.append(h);
                		sb.append(i);
                		sb.append(j);
                		sb.append(k);
                		sb.append(m);
                		
                		String packedBCD = sb.toString();
                		StringBuilder reverse = new StringBuilder(); //decimal
                		String string = packedBCD; //copy to reverse
                		sb.delete(0, sb.length()); //reuse String Builder
                		int counter = 0;
                		StringBuilder nicePrint  = new StringBuilder();
                		
                		for(int p=0;p<string.length();p++) {
                			if(counter == 4) {
                				counter = 0;
                				nicePrint.append(" ");
                			}
                				
                			nicePrint.append(string.charAt(p));
                			counter++;
                		}
                		packed.setText(nicePrint.toString());
                		
                		
                		for(int kk= string.length()-1;kk>=0;kk--)
                			reverse.append(string.charAt(kk));
                		string = reverse.toString();
                		char[] bcd = string.toCharArray();
                		int total = 0;
                		for(int x = 0; x < 3; x++){ 
                		    int n;
                			for(int y = 0; y < 4; y++){ 
                				n = (y+x) + (3*(x)); 
                				System.out.println("j:" + y + ", i:" + x + ", n:" + n);
                				if(bcd[n] == '1') {
                					total += (Math.pow(2,(y)))*Math.pow(10, (x));
                            		System.out.println("total:" + total);
                				}
                		    }
                		}
                		
                		decimal.setText(String.valueOf(total));
                		
                		char temp2[] = packedBCD.toCharArray(); //PACKED TO UNPACKED
                		StringBuilder sb2 = new StringBuilder();
                		
                		sb.append('0'); //first term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[0]);
                		sb.append(temp2[1]);
                		sb.append(temp2[2]);
                		sb.append(temp2[3]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //second term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[4]);
                		sb.append(temp2[5]);
                		sb.append(temp2[6]);
                		sb.append(temp2[7]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); //reuse String Builder
                		
                		sb.append('0'); //third term
                		sb.append('0');
                		sb.append('0');
                		sb.append('0');
                		sb.append(' ');
                		sb.append(temp2[8]);
                		sb.append(temp2[9]);
                		sb.append(temp2[10]);
                		sb.append(temp2[11]);
                		sb.append(' ');
                		sb2.append(sb.toString());
                		sb.delete(0, sb.length()); 
                		
                		unpacked.setText(sb2.toString());
            		}
            }});
       
		
		packed = new JTextField();
		packed.setFont(fieldFont);
        packed.setBackground(Color.white);
        packed.setForeground(Color.black);
        packed.setBounds(350, 50+80+80+80, 615, 40);
        
        packedText = new JLabel();
        packedText.setFont(new Font("Consolas", Font.PLAIN, 20));
		packedText.setForeground(Color.black);
		packedText.setBounds(184 , -100+80+80, 500, 500);
		packedText.setText("Packed BCD:");
		
		
		unpacked = new JTextField();
		unpacked.setFont(fieldFont);
		unpacked.setBackground(Color.white);
        unpacked.setForeground(Color.black);
        unpacked.setBounds(350, 50+80+80+80+80, 615, 40);
        
        unpackedText = new JLabel();
        unpackedText.setFont(new Font("Consolas", Font.PLAIN, 20));
		unpackedText.setForeground(Color.black);
		unpackedText.setBounds(184 , 60+80, 500, 500);
		unpackedText.setText("Unpacked BCD:");
		
		decimal = new JTextField();
		decimal.setFont(fieldFont);
		decimal.setBackground(Color.white);
        decimal.setForeground(Color.black);
        decimal.setBounds(350, 50+80+80+80+80+80, 615, 40);
        
        decimalText = new JLabel();
        decimalText.setFont(new Font("Consolas", Font.PLAIN, 20));
		decimalText.setForeground(Color.black);
		decimalText.setBounds(184 , 60+80+80, 500, 500);
		decimalText.setText("Decimal:");
		
		add(input);
		add(packed);
		add(packedText);
		add(unpacked);
		add(unpackedText);
		add(decimal);
		add(decimalText);
		add(clearBtn);
		
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
    
    
	public String getUserInput() {
		return userInput;
	}



	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == clearBtn) {		
			input.setText("");
			packed.setText("");
			unpacked.setText("");
			decimal.setText("");
			this.userInput = "";
		}
	}
	
	
	public static void main(String[] args) {
		Main main = new Main();
		
		
	}

}