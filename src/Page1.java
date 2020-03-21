import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Page1 extends JFrame 
{
	private static final int AREA_WIDTH = 8;
	private static final int AREA_HEIGHT = 54;
	private ArrayList<JButton> buttonList1,buttonList2,buttonList3,buttonList4,buttonList5,buttonList6;
	private JFrame frame;
	private JLabel ymd = new JLabel();
	private JTextArea p1Area = new JTextArea();
	private Page2 p2 = new Page2();
	
	
	//P1 frame
	public Page1()
	{
		p2.setInvisible();
		this.setSize(600, 500);
		this.setTitle("Create your schedule!");
		try {createP1();} catch (SQLException e) {e.printStackTrace();}
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		class WindowHandler extends WindowAdapter
		{
			public void windowClosing(WindowEvent e) 
			{
				int rs = JOptionPane.showConfirmDialog(null, "確定要離開?","Create your schedule!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if (rs== 0){System.exit(0);}
			}
		}
		
		this.addWindowListener(new WindowHandler() );
		this.setVisible(true);
	}
	
	// P1 mainPanel
	public void createP1() throws SQLException						
	{
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		timeSetting();
		panel.setLayout(new BorderLayout());
		panel.add(ymd, BorderLayout.NORTH);
		panel.add(createChart(), BorderLayout.CENTER);
		panel.add(createP1Area(), BorderLayout.SOUTH);
		this.add(panel);
	}
	
	//P1 Panel(上)
	public void timeSetting()
	{
		int[] now = new int[3];
	    now = getdates();
		String year = String.valueOf(now[0]);
		String month = String.valueOf(now[1]);
		ymd.setText(year+" "+month+"月");		
		ymd.setHorizontalAlignment(SwingConstants.CENTER);
		ymd.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
	}
	
	//P1 Panel(下)
	public JPanel createP1Area() throws SQLException{
		p1Area = new JTextArea(AREA_WIDTH,AREA_HEIGHT);
		p1Area.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
		appendArea();
		p1Area.setLineWrap(true);
		p1Area.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(p1Area);
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		panel.add(scrollPane);
		return panel;
	}
	
	//P1 Panel(下)(AreaText)
	public void appendArea() throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		try 
		{
		   Statement stat = conn.createStatement();
		   String query = "SELECT * FROM `Event` order by date";
		   ResultSet result = stat.executeQuery(query);
		   while(result.next()) 
		   {
			   String date = result.getString("date");
			   int iT= result.getInt("initialTime");
			   int eT= result.getInt("endTime");
			   String eventName = result.getString("eventName");
			   String place = result.getString("place");
			   String str = String.format("%1$12s",date)+String.format("%1$3s",iT)+"~"+String.format("%1$3s",eT)+String.format("%1$15s",eventName)+" @"+String.format("%1$10s",place)+"\n";
			   p1Area.append(str);
		   }
		}
		finally
		{
			conn.close();
		}
	}

	
	//P1 Panel(中)
	public JPanel createChart() {
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		
		JPanel p1 = new JPanel();
		p1.setBackground(new java.awt.Color(249,208,211));

		JLabel sun = new JLabel("  日    ");
		JLabel mon = new JLabel("  一   ");
		JLabel tue = new JLabel("    二  ");
		JLabel wed = new JLabel("    三   ");
		JLabel thu = new JLabel("   四  ");
		JLabel fri = new JLabel("   五   ");
		JLabel sat = new JLabel("  六   ");
		
		p1.add(sun);p1.add(mon);p1.add(tue);p1.add(wed);p1.add(thu);p1.add(fri);p1.add(sat);
		  
		sun.setForeground(Color.BLUE);
		sun.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));tue.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
		wed.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));thu.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
		fri.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));sat.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
		  
		JPanel p1_1 = new JPanel();		  
		p1_1.setBackground(new java.awt.Color(249,208,211));
		p1_1.setLayout(new GridLayout(6,7));
		    	  
		buttonList1 = new ArrayList<JButton>();buttonList2 = new ArrayList<JButton>();
		buttonList3 = new ArrayList<JButton>();buttonList4 = new ArrayList<JButton>();
		buttonList5 = new ArrayList<JButton>();buttonList6 = new ArrayList<JButton>();
		  
		JButton button1 = new JButton(""); JButton button2 = new JButton("");
		JButton button3 = new JButton(""); JButton button4 = new JButton("");
		JButton button5 = new JButton(""); JButton button6 = new JButton("");
		JButton button7 = new JButton("1");JButton button8 = new JButton("2");
		JButton button9 = new JButton("3");JButton button10 = new JButton("4");
		JButton button11 = new JButton("5");JButton button12 = new JButton("6");
		JButton button13 = new JButton("7");JButton button14 = new JButton("8"); 
		JButton button15 = new JButton("9");JButton button16 = new JButton("10");
		JButton button17 = new JButton("11");JButton button18 = new JButton("12");
		JButton button19 = new JButton("13");JButton button20 = new JButton("14");
		JButton button21 = new JButton("15");JButton button22 = new JButton("16");
		JButton button23 = new JButton("17");JButton button24 = new JButton("18");
		JButton button25 = new JButton("19");JButton button26 = new JButton("20");
		JButton button27 = new JButton("21");JButton button28 = new JButton("22");
		JButton button29 = new JButton("23");JButton button30 = new JButton("24");
		JButton button31 = new JButton("25");JButton button32 = new JButton("26");
		JButton button33 = new JButton("27");JButton button34 = new JButton("28");
		JButton button35 = new JButton("29");JButton button36 = new JButton("30");
		JButton button37 = new JButton("");JButton button38 = new JButton("");
		JButton button39 = new JButton("");JButton button40 = new JButton("");
		JButton button41 = new JButton("");JButton button42 = new JButton("");   

		buttonList1.add(button1); buttonList1.add(button2);
		buttonList1.add(button3);	buttonList1.add(button4);
		buttonList1.add(button5); buttonList1.add(button6);
		buttonList1.add(button7);buttonList2.add(button8);
		buttonList2.add(button9);buttonList2.add(button10);
		buttonList2.add(button11);buttonList2.add(button12);
		buttonList2.add(button13);buttonList2.add(button14);
		buttonList3.add(button15);buttonList3.add(button16);
		buttonList3.add(button17);buttonList3.add(button18);
		buttonList3.add(button19);buttonList3.add(button20);
		buttonList3.add(button21);buttonList4.add(button22);
		buttonList4.add(button23);buttonList4.add(button24);
		buttonList4.add(button25);buttonList4.add(button26);
		buttonList4.add(button27);buttonList4.add(button28);
		buttonList5.add(button29);buttonList5.add(button30);
		buttonList5.add(button31);buttonList5.add(button32);
		buttonList5.add(button33);buttonList5.add(button34);
		buttonList5.add(button35); buttonList6.add(button36);
		buttonList6.add(button37);buttonList6.add(button38);
		buttonList6.add(button39);buttonList6.add(button40);
		buttonList6.add(button41);buttonList6.add(button42);
		  
		for(JButton btn: buttonList1) { btn.setContentAreaFilled(false); }
		for(int i=0; i<(buttonList1.size() -1) ; i++) { buttonList1.get(i).setBorderPainted(false); }
		for(JButton btn: buttonList2) { btn.setContentAreaFilled(false); }	  
		for(JButton btn: buttonList3) { btn.setContentAreaFilled(false); }
		for(JButton btn: buttonList4) { btn.setContentAreaFilled(false); }
		for(JButton btn: buttonList5) { btn.setContentAreaFilled(false); }
		for(JButton btn: buttonList6) { btn.setContentAreaFilled(false); }
		for(int i=1; i<buttonList6.size() ; i++) { buttonList6.get(i).setBorderPainted(false);}
		 
		p1_1.add(button1);p1_1.add(button2);p1_1.add(button3);p1_1.add(button4);p1_1.add(button5);
		p1_1.add(button6);p1_1.add(button7);p1_1.add(button8);p1_1.add(button9);p1_1.add(button10);
		p1_1.add(button11);p1_1.add(button12);p1_1.add(button13);p1_1.add(button14);p1_1.add(button15);
		p1_1.add(button16);p1_1.add(button17);p1_1.add(button18);p1_1.add(button19);p1_1.add(button20);
		p1_1.add(button21);p1_1.add(button22);p1_1.add(button23);p1_1.add(button24);p1_1.add(button25);
		p1_1.add(button26);p1_1.add(button27);p1_1.add(button28);p1_1.add(button29);p1_1.add(button30);
		p1_1.add(button31);p1_1.add(button32);p1_1.add(button33);p1_1.add(button34);p1_1.add(button35);
		p1_1.add(button36);p1_1.add(button37);p1_1.add(button38);p1_1.add(button39);p1_1.add(button40);
		p1_1.add(button41);p1_1.add(button42);
		   
		  class listener implements ActionListener
		  {
		     public void actionPerformed (ActionEvent event)
		     {
		      Page2 p2 = new Page2(); 
		     }
		  }
		    
		   ActionListener listener = new listener();
		   button1.addActionListener(listener);button2.addActionListener(listener);button3.addActionListener(listener);
		   button4.addActionListener(listener);button5.addActionListener(listener);button6.addActionListener(listener);
		   button7.addActionListener(listener);button8.addActionListener(listener);button9.addActionListener(listener);
		   button10.addActionListener(listener);button11.addActionListener(listener);button12.addActionListener(listener);
		   button13.addActionListener(listener);button14.addActionListener(listener);button15.addActionListener(listener);
		   button16.addActionListener(listener);button17.addActionListener(listener);button18.addActionListener(listener);
		   button19.addActionListener(listener);button20.addActionListener(listener);button21.addActionListener(listener);
		   button22.addActionListener(listener);button23.addActionListener(listener);button24.addActionListener(listener);
		   button25.addActionListener(listener);button26.addActionListener(listener);button27.addActionListener(listener);
		   button28.addActionListener(listener);button29.addActionListener(listener);button30.addActionListener(listener);
		   button31.addActionListener(listener);button32.addActionListener(listener);button33.addActionListener(listener);
		   button34.addActionListener(listener);button35.addActionListener(listener);button36.addActionListener(listener);
		   button37.addActionListener(listener);button38.addActionListener(listener);button39.addActionListener(listener);
		   button40.addActionListener(listener);button41.addActionListener(listener);button42.addActionListener(listener);
		
		   panel.add(p1, BorderLayout.NORTH);
		   panel.add(p1_1, BorderLayout.CENTER);
		   return panel;
	}
	
	public  int[] getdates()//取得系統日期函數
	  {
	    int[] date_array = new int[3];
	    Calendar ca = new GregorianCalendar();  
	    date_array[0] = ca.get(Calendar.YEAR);
	    date_array[1] = ca.get(Calendar.MONTH)+1;
	    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);
	    return date_array;
	  }
	
	
	
}
