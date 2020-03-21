import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Page2 {
	private JFrame mainFrame;
	private JPanel mainPanel,weekPanel,panel1,panel2,panel3,panel4,panel5,panel6,panel7;
	private JLabel week;
	private JButton leftBtn,rightBtn;
	private Calendar calendar;
	private ArrayList<JButton> buttonList1,buttonList2,buttonList3,buttonList4,buttonList5,buttonList6,buttonList7;
	private String l,r;
	private int year,month,day,iT,eT;
	
	//P2 frame
	public Page2()
	{
		getdate();
		createMainPanel();
		mainFrame = new JFrame("Calender");
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainFrame.setSize(600, 500);
	}
	
	//P2 mainPanel
	public void createMainPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(createWeek(), BorderLayout.NORTH);
		mainPanel.add(createItem(), BorderLayout.CENTER);
		try {FillInBtn();} catch (SQLException | ParseException e) {e.printStackTrace();}
	}

	//P2 Panel(上)
	public JPanel createWeek() 
	{ 
		weekPanel = new JPanel();
		weekPanel.setBackground(new java.awt.Color(249,208,211));
		Calendar calendarL = new GregorianCalendar(year,month-1,day);
		Calendar calendarR = new GregorianCalendar(year,month-1,day);
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		try {if(date2Day(year+"-"+month+"-"+day)!=7){calendarL.add(Calendar.DAY_OF_WEEK, -(date2Day(year+"-"+month+"-"+day)));}}
		catch (ParseException e) {e.printStackTrace();}
		try {if(date2Day(year+"-"+month+"-"+day)!=7){calendarR.add(Calendar.DAY_OF_WEEK, -(date2Day(year+"-"+month+"-"+day)));}} 
		catch (ParseException e) {e.printStackTrace();}
		l = format.format(calendarL.getTime());
		calendarR.add(Calendar.DAY_OF_WEEK,+6);
		r = format.format(calendarR.getTime());
		week = new JLabel(l +   " ~ " + r);
	
		leftBtn = new JButton(" < ");
		class leftListener implements ActionListener
		{
			public void actionPerformed (ActionEvent event)
			{	
				calendarL.add(Calendar.DAY_OF_WEEK,-7);
				calendarR.add(Calendar.DAY_OF_WEEK,-7);
				l = format.format(calendarL.getTime());
				r = format.format(calendarR.getTime());
				week.setText(l +   " ~ " + r);
				repaint();
			}
		}
		rightBtn = new JButton(">");
		class rightListener implements ActionListener
		{
			public void actionPerformed (ActionEvent event)
			{	
				calendarL.add(Calendar.DAY_OF_WEEK,+7);
				calendarR.add(Calendar.DAY_OF_WEEK,+7);
				l = format.format(calendarL.getTime());
				r = format.format(calendarR.getTime());
				week.setText(l +   " ~ " + r);
				repaint();
			}
		}
		ActionListener leftListener = new leftListener();leftBtn.addActionListener(leftListener);
		ActionListener rightListener = new rightListener();rightBtn.addActionListener(rightListener);
		weekPanel.add(leftBtn);
		weekPanel.add(week);
		weekPanel.add(rightBtn);
		return weekPanel;
	}	
	
	
	public JPanel weekPanel()
	{
		weekPanel = new JPanel();
		Calendar calendarL = new GregorianCalendar(Integer.parseInt(dateConvert(l).get(0)),Integer.parseInt(dateConvert(l).get(1))-1,Integer.parseInt(dateConvert(l).get(2)));
		Calendar calendarR = new GregorianCalendar(Integer.parseInt(dateConvert(r).get(0)),Integer.parseInt(dateConvert(r).get(1))-1,Integer.parseInt(dateConvert(r).get(2)));
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		weekPanel.setBackground(new java.awt.Color(249,208,211));
		l = format.format(calendarL.getTime());
		r = format.format(calendarR.getTime());
		week = new JLabel(l +   " ~ " + r);
		leftBtn = new JButton(" < ");
		
		class leftListener implements ActionListener
		{
			public void actionPerformed (ActionEvent event)
			{	
				calendarL.add(Calendar.DAY_OF_WEEK,-7);
				calendarR.add(Calendar.DAY_OF_WEEK,-7);
				l = format.format(calendarL.getTime());
				r = format.format(calendarR.getTime());
				week.setText(l +   " ~ " + r);
				repaint();
			}
		}
		
		rightBtn = new JButton(">");
		
		class rightListener implements ActionListener
		{
			public void actionPerformed (ActionEvent event)
			{	
				calendarL.add(Calendar.DAY_OF_WEEK,+7);
				calendarR.add(Calendar.DAY_OF_WEEK,+7);
				l = format.format(calendarL.getTime());
				r = format.format(calendarR.getTime());
				week.setText(l +   " ~ " + r);
				repaint();
			}
		}
		
		ActionListener leftListener = new leftListener();leftBtn.addActionListener(leftListener);
		ActionListener rightListener = new rightListener();rightBtn.addActionListener(rightListener);
		weekPanel.add(leftBtn);
		weekPanel.add(week);
		weekPanel.add(rightBtn);
		return weekPanel;
	}		



	public JPanel createItem()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		JPanel panel_label = new JPanel(); panel_label.setLayout(new GridLayout(12,1));
		panel_label.setBackground(new java.awt.Color(249,208,211)); 
		panel_label.add(new JLabel("   "));panel_label.add(new JLabel("　　8:00"));panel_label.add(new JLabel("　　9:00"));panel_label.add(new JLabel("　   10:00"));panel_label.add(new JLabel("   　11:00"));panel_label.add(new JLabel("　   12:00"));
		panel_label.add(new JLabel("   　13:00"));panel_label.add(new JLabel("   　14:00"));panel_label.add(new JLabel("   　15:00"));panel_label.add(new JLabel("   　16:00"));panel_label.add(new JLabel("   　17:00"));panel_label.add(new JLabel("　   18:00"));
		buttonList1 = new ArrayList<JButton>();panel1 = new JPanel();panel1.setLayout(new GridLayout(12,1));panel1.add(new JLabel("          日"));
		buttonList2 = new ArrayList<JButton>();panel2 = new JPanel();panel2.setLayout(new GridLayout(12,1));panel2.add(new JLabel("          一"));
		buttonList3 = new ArrayList<JButton>();panel3 = new JPanel();panel3.setLayout(new GridLayout(12,1));panel3.add(new JLabel("          二"));
		buttonList4 = new ArrayList<JButton>();panel4 = new JPanel();panel4.setLayout(new GridLayout(12,1));panel4.add(new JLabel("          三"));
		buttonList5 = new ArrayList<JButton>();panel5 = new JPanel();panel5.setLayout(new GridLayout(12,1));panel5.add(new JLabel("          四"));
		buttonList6 = new ArrayList<JButton>();panel6 = new JPanel();panel6.setLayout(new GridLayout(12,1));panel6.add(new JLabel("          五"));
		buttonList7 = new ArrayList<JButton>();panel7 = new JPanel();panel7.setLayout(new GridLayout(12,1));panel7.add(new JLabel("          六"));

		
		
		for(int i=0;i<11;i++)//周日
		{	
			calendar = new GregorianCalendar(Integer.parseInt(dateConvert(l).get(0)),Integer.parseInt(dateConvert(l).get(1))-1,Integer.parseInt(dateConvert(l).get(2)));
			JButton btn = new JButton();
			panel1.add(btn);buttonList1.add(btn);
			panel1.setBackground(new java.awt.Color(249,208,211)); 
			int x=i;
			class tester implements ActionListener
			{
				public void actionPerformed(ActionEvent event) 
				{	
					boolean end_1 = false ,end_2 = false;
					if(btn.getText()!="") 
					{
						int x_1=x; int x_2=x;
						String text = btn.getText();
						while(end_1!=true) {
								if (x_1==0) {x_1=0;end_1=true;}
								else if(buttonList1.get(x_1-1).getText().contentEquals(((buttonList1.get(x_1).getText())))&&x_1>0){x_1--;}
								else{end_1=true;}}
						while(end_2!= true) {
							if(x_2==10) {x_2=10;end_2=true;}
							else if(buttonList1.get(x_2).getText().equals(buttonList1.get(x_2+1).getText())){x_2++;}
							else {end_2=true;}}
						try {fillIn(text,(x_1+8),(x_2+9));} catch (SQLException e) {e.printStackTrace();}
					}
					else 
					{
						Page3 pg3 = new Page3(format.format(calendar.getTime()));
						mainFrame.setVisible(false);
					}
				}				
			}
			ActionListener test = new tester();btn.addActionListener(test);
		}
		for(int i=0;i<11;i++)
		{	
			JButton btn = new JButton();
			panel2.add(btn);buttonList2.add(btn);
			panel2.setBackground(new java.awt.Color(249,208,211)); 
			int x=i;
			class tester implements ActionListener
			{
				public void actionPerformed(ActionEvent event) 
				{	
					boolean end_1 = false ,end_2 = false;
					if(btn.getText()!="") 
					{
						int x_1=x; int x_2=x;
						String text = btn.getText();
						while(end_1!=true) {
								if (x_1==0) {x_1=0;end_1=true;}
								else if(buttonList2.get(x_1-1).getText().contentEquals(((buttonList2.get(x_1).getText())))&&x_1>0){x_1--;}
								else{end_1=true;}}
						while(end_2!= true) {
							if(x_2==10) {x_2=10;end_2=true;}
							else if(buttonList2.get(x_2).getText().equals(buttonList2.get(x_2+1).getText())){x_2++;}
							else {end_2=true;}}
							try {fillIn(text,(x_1+8),(x_2+9));} catch (SQLException e) {e.printStackTrace();}
					}
					else 
					{
						calendar.add(Calendar.DAY_OF_WEEK, +1);
						Page3 pg3 = new Page3(format.format(calendar.getTime()));
						mainFrame.setVisible(false);
					}
				}				
			}
			ActionListener test = new tester();btn.addActionListener(test);
		}
		for(int i=0;i<11;i++)
		{	
			JButton btn = new JButton();
			panel3.add(btn);buttonList3.add(btn);
			panel3.setBackground(new java.awt.Color(249,208,211));
			int x=i;
			class tester implements ActionListener
			{
				public void actionPerformed(ActionEvent event) 
				{	
					boolean end_1 = false ,end_2 = false;
					if(btn.getText()!="") 
					{
						int x_1=x; int x_2=x;
						String text = btn.getText();
						while(end_1!=true) {
								if (x_1==0) {x_1=0;end_1=true;}
								else if(buttonList3.get(x_1-1).getText().contentEquals(buttonList3.get(x_1).getText())&&x_1>0){x_1--;}
								else{end_1=true;}}
						while(end_2!= true) {
							if(x_2==10) {x_2=10;end_2=true;}
							else if(buttonList3.get(x_2).getText().equals(buttonList3.get(x_2+1).getText())&&x_2<12){x_2++;}
							else {end_2=true;}}
						try {fillIn(text,(x_1+8),(x_2+9));} catch (SQLException e) {e.printStackTrace();}
					}
					else 
					{
						calendar.add(Calendar.DAY_OF_WEEK, +2);
						Page3 pg3 = new Page3(format.format(calendar.getTime()));
						mainFrame.setVisible(false);
					}
				}				
			}
			ActionListener test = new tester();btn.addActionListener(test);
		}
		for(int i=0;i<11;i++)
		{	
			JButton btn = new JButton();
			panel4.add(btn);buttonList4.add(btn);
			panel4.setBackground(new java.awt.Color(249,208,211));
			int x=i;
			class tester implements ActionListener
			{
				public void actionPerformed(ActionEvent event) 
				{	
					boolean end_1 = false ,end_2 = false;
					if(btn.getText()!="") 
					{
						int x_1=x; int x_2=x;
						String text = btn.getText();
						while(end_1!=true) {
								if (x_1==0) {x_1=0;end_1=true;}
								else if(buttonList4.get(x_1-1).getText().contentEquals(((buttonList4.get(x_1).getText())))&&x_1>0){x_1--;}
								else{end_1=true;}}
						while(end_2!= true) {
							if(x_2==10) {x_2=10;end_2=true;}
							else if(buttonList4.get(x_2).getText().equals(buttonList4.get(x_2+1).getText())){x_2++;}
							else {end_2=true;}}
						try {fillIn(text,(x_1+8),(x_2+9));} catch (SQLException e) {e.printStackTrace();}
					}
					else 
					{
						calendar.add(Calendar.DAY_OF_WEEK, +3);
						Page3 pg3 = new Page3(format.format(calendar.getTime()));
						mainFrame.setVisible(false);
					}
				}				
			}
			ActionListener test = new tester();btn.addActionListener(test);
		}
		for(int i=0;i<11;i++)
		{	
			JButton btn = new JButton();
			panel5.add(btn);buttonList5.add(btn);
			panel5.setBackground(new java.awt.Color(249,208,211));
			int x=i;
			class tester implements ActionListener
			{
				public void actionPerformed(ActionEvent event) 
				{	
					boolean end_1 = false ,end_2 = false;
					if(btn.getText()!="") 
					{
						int x_1=x; int x_2=x;
						String text = btn.getText();
						while(end_1!=true) {
								if (x_1==0) {x_1=0;end_1=true;}
								else if(buttonList5.get(x_1-1).getText().contentEquals(((buttonList5.get(x_1).getText())))&&x_1>0){x_1--;}
								else{end_1=true;}}
						while(end_2!= true) {
							if(x_2==10) {x_2=10;end_2=true;}
							else if(buttonList5.get(x_2).getText().equals(buttonList5.get(x_2+1).getText())){x_2++;}
							else {end_2=true;}}
						try {fillIn(text,(x_1+8),(x_2+9));} catch (SQLException e) {e.printStackTrace();}
					}
					else 
					{
						calendar.add(Calendar.DAY_OF_WEEK, +4);
						Page3 pg3 = new Page3(format.format(calendar.getTime()));
						mainFrame.setVisible(false);
					}
				}				
			}
			ActionListener test = new tester();btn.addActionListener(test);
		}
		for(int i=0;i<11;i++)
		{	
			JButton btn = new JButton();
			panel6.add(btn);buttonList6.add(btn);
			panel6.setBackground(new java.awt.Color(249,208,211)); 
			int x=i;
			class tester implements ActionListener
			{
				public void actionPerformed(ActionEvent event) 
				{	
					boolean end_1 = false ,end_2 = false;
					if(btn.getText()!="") 
					{
						int x_1=x; int x_2=x;
						String text = btn.getText();
						while(end_1!=true) {
								if (x_1==0) {x_1=0;end_1=true;}
								else if(buttonList6.get(x_1-1).getText().contentEquals(((buttonList6.get(x_1).getText())))&&x_1>0){x_1--;}
								else{end_1=true;}}
						while(end_2!= true) {
							if(x_2==10) {x_2=10;end_2=true;}
							else if(buttonList6.get(x_2).getText().equals(buttonList6.get(x_2+1).getText())){x_2++;}
							else {end_2=true;}}
						try {fillIn(text,(x_1+8),(x_2+9));} catch (SQLException e) {e.printStackTrace();}
					}
					else 
					{
						calendar.add(Calendar.DAY_OF_WEEK, +5);
						Page3 pg3 = new Page3(format.format(calendar.getTime()));
						mainFrame.setVisible(false);
					}
				}				
			}
			ActionListener test = new tester();btn.addActionListener(test);
		}
		for(int i=0;i<11;i++)
		{	
			JButton btn = new JButton();
			panel7.add(btn);buttonList7.add(btn);
			panel7.setBackground(new java.awt.Color(249,208,211)); 
			int x=i;
			class tester implements ActionListener
			{
				public void actionPerformed(ActionEvent event) 
				{	
					boolean end_1 = false ,end_2 = false;
					if(btn.getText()!="") 
					{
						int x_1=x; int x_2=x;
						String text = btn.getText();
						while(end_1!=true) {
								if (x_1==0) {x_1=0;end_1=true;}
								else if(buttonList7.get(x_1-1).getText().contentEquals(((buttonList7.get(x_1).getText())))&&x_1>0){x_1--;}
								else{end_1=true;}}
						while(end_2!= true) {
							if(x_2==10) {x_2=10;end_2=true;}
							else if(buttonList7.get(x_2).getText().equals(buttonList7.get(x_2+1).getText())){x_2++;}
							else {end_2=true;}}
						try {fillIn(text,(x_1+8),(x_2+9));} catch (SQLException e) {e.printStackTrace();}
					}
					else 
					{
						calendar.add(Calendar.DAY_OF_WEEK, +6);
						Page3 pg3 = new Page3(format.format(calendar.getTime()));
						mainFrame.setVisible(false);
					}
				}				
			}
			ActionListener test = new tester();btn.addActionListener(test);
		}
		for(JButton btn: buttonList1) { btn.setBackground(Color.WHITE);}
		for(JButton btn: buttonList2) { btn.setBackground(Color.WHITE); }
		for(JButton btn: buttonList3) { btn.setBackground(Color.WHITE); }
		for(JButton btn: buttonList4) { btn.setBackground(Color.WHITE); }
		for(JButton btn: buttonList5) { btn.setBackground(Color.WHITE); }
		for(JButton btn: buttonList6) { btn.setBackground(Color.WHITE); }
		for(JButton btn: buttonList7) { btn.setBackground(Color.WHITE); }
		JPanel panel = new JPanel();panel.setLayout(new GridLayout(1,8));
		panel.add(panel_label);panel.add(panel1);panel.add(panel2);panel.add(panel3);panel.add(panel4);panel.add(panel5);panel.add(panel6);panel.add(panel7);
		return panel;
	}
	
	
	
	
	public void setVisible(){mainFrame.setVisible(true);}
	public void setInvisible() {mainFrame.setVisible(false);}
	
//Btn顯示txt.
	 public void FillInBtn() throws SQLException, ParseException
	 {
	  Connection conn = SimpleDataSource.getConnection();
	  try 
	  {
	   PreparedStatement stat = conn.prepareStatement("SELECT * FROM `Event` WHERE date BETWEEN ? and ? order by date");
		stat.setString(1,dateConverter(l));
		stat.setString(2,dateConverter(r));
	   ResultSet result = stat.executeQuery();
	   while(result.next())
	   {
	    //處理填一天中的哪格
	    iT= result.getInt("initialTime");
	    eT= result.getInt("endTime");
	    int lastTime = eT - iT ;
	    int index = 0;
	    if(iT == 8) 
	    {index = 0; }
	    else 
	    {index = iT - 8;}
	    //填的是哪些格,哪天的格
	    String day_of_week = result.getString("date");
	    int i = 0;
	    	while(i < lastTime)
	    	{
	    		if(date2Day(day_of_week)==1)
	    			{buttonList2.get(index).setText(result.getString("eventName"));index++;i++;buttonList2.get(index-1).setBackground(new java.awt.Color(211, 171, 208));}
	    		if(date2Day(day_of_week)==2)
	    			{buttonList3.get(index).setText(result.getString("eventName"));index++;i++;buttonList3.get(index-1).setBackground(new java.awt.Color(254, 196, 104));}
	    		if(date2Day(day_of_week)==3)
	    			{buttonList4.get(index).setText(result.getString("eventName"));index++;i++;buttonList4.get(index-1).setBackground(new java.awt.Color(251, 245, 137));}
	    		if(date2Day(day_of_week)==4)
	    			{buttonList5.get(index).setText(result.getString("eventName"));index++;i++;buttonList5.get(index-1).setBackground(new java.awt.Color(107, 215, 241));}
	    		if(date2Day(day_of_week)==5)
	    			{buttonList6.get(index).setText(result.getString("eventName"));index++;i++;buttonList6.get(index-1).setBackground(new java.awt.Color(251, 187, 249));}
	    		if(date2Day(day_of_week)==6)
	    			{buttonList7.get(index).setText(result.getString("eventName"));index++;i++;buttonList7.get(index-1).setBackground(new java.awt.Color(89, 183, 243));}
	    		if(date2Day(day_of_week)==7)
	    			{buttonList1.get(index).setText(result.getString("eventName"));index++;i++;buttonList1.get(index-1).setBackground(new java.awt.Color(180, 215, 201));}
	    	}}}
	  finally{conn.close();}
	 }
	
//Pg3顯示內容
	 public void fillIn(String text,int iT,int eT) throws SQLException 
	 {
		 Connection conn = SimpleDataSource.getConnection();
		  try 
		  {
			PreparedStatement stat = conn.prepareStatement("SELECT `place`, `date` FROM `Event` WHERE `eventName`=? and `initialTime`=? And`endTime`=?");
			stat.setString(1,text);
			stat.setInt(2,iT);
			stat.setInt(3,eT);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
			String place = result.getString("place");
			String date = result.getString("date");
			Page3 pg3 = new Page3(text,place,date,iT,eT);
			mainFrame.setVisible(false);
			}
		  }
		  finally
		  {conn.close(); }
	}
	 
	 //refresh Panel
	 public void repaint()
	 {
			mainFrame.setVisible(false);
			mainFrame.getContentPane().removeAll();
			 mainFrame.repaint();
			 JPanel Panel = new JPanel();
			 Panel.setLayout(new BorderLayout());
			Panel.add( weekPanel(), BorderLayout.NORTH);
			Panel.add(createItem(), BorderLayout.CENTER);
			try {FillInBtn();} catch (SQLException | ParseException e) {e.printStackTrace();}
			mainFrame.add(Panel);
			mainFrame.setVisible(true);
			mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			mainFrame.setSize(600, 500);
	 }
	 

	 
	/*----------------------------------------*/
	
	//取得系統日期函數
	public  void getdate()
	  {
	    int[] date_array = new int[3];
	    Calendar ca = new GregorianCalendar();  
	    date_array[0] = ca.get(Calendar.YEAR);
	    date_array[1] = ca.get(Calendar.MONTH)+1;
	    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);
	    year = date_array[0];
	    month = date_array[1];
	    day = date_array[2];
	  }

	//取得該日之星期
	public static int date2Day( String dateString ) throws ParseException
	{
		SimpleDateFormat dateStringFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = dateStringFormat.parse( dateString );
		SimpleDateFormat date2DayFormat = new SimpleDateFormat( "u" );
		String a = date2DayFormat.format( date );
		int b = Integer.parseInt(a);
		return b;
	}
	
	
	public String dateConverter(String date)
	{
		String[] split =date.split("\\.");
		String years=split[0];
		String months=split[1];
		String days=split[2];
		return years+months+days;
	}
	
 public ArrayList<String> dateConvert(String date)
	{
	 	ArrayList<String > text = new ArrayList<String>();
		String[] split =date.split("\\.");
		text.add(split[0]);
		text.add(split[1]);
		text.add(split[2]);
		 return text;		
	}

	
}
