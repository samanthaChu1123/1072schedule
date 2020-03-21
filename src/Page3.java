import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Page3 extends Page2{
	
	private static final int FRAME_WIDTH = 310;
	private static final int FRAME_HEIGHT = 280;
	private static final int AREA_WIDTH = 15;
	private JFrame frame;
	private JLabel eventLabel,dateLabel,placeLabel;
	private JButton aBtn,bBtn;
	private JTextField eventTextField,dateTextField,placeTextField;
	private JComboBox<Integer> timeStartCombo,timeEndCombo;
	private String year,month,day;
	

	//P3 frame
	public Page3(String date) 
	{
		frame=new JFrame("新增");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		createPanel();
		dateTextField.setText(date);
		frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	//P3 frame
	public Page3(String text,String place,String date,int iT,int eT)
	{
		frame=new JFrame("新增");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		createPanel();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		eventTextField.setText(text+" ");
		placeTextField.setText(place);
		dateTextField.setText(date);
		timeStartCombo.setSelectedItem(iT);
		timeEndCombo.setSelectedItem(eT);
	}
	
	//P3 Panel
	public void createPanel() {
		frame.add(createMainPanel1(), BorderLayout.CENTER);
		frame.add(createBtn(), BorderLayout.SOUTH);
	}
	
	//P3 Panel(中)
	public JPanel createMainPanel1() 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(createEvent(), BorderLayout.NORTH);
		panel.add(createAPanel(), BorderLayout.CENTER);
		panel.add(createBPanel(), BorderLayout.SOUTH);
		return panel;
	}
	
	public JPanel createAPanel() 
	{
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		panel.add(createPlace());
		return panel;
	}

	public JPanel createBPanel() 
	{
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		panel.setLayout(new GridLayout(2,1));
		panel.add(createDate());
		panel.add(createTime());
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Time"));
		return panel;
	}

	//P3 Panel(下)
	public JPanel createBtn() 
	{
		aBtn = new JButton("清除");
		bBtn = new JButton("確定");
		class aBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				try 
				{
					delete();
					eventTextField.setText(" ");
					placeTextField.setText("");
					timeStartCombo.setSelectedIndex(0);
					timeEndCombo.setSelectedIndex(0);
					frame.setVisible(false);
					repaint();	
				} 
				catch (SQLException e) {e.printStackTrace();}
				catch(Exception e){}
				eventTextField.setText(" ");
				placeTextField.setText("");
				timeStartCombo.setSelectedItem(null);
				timeEndCombo.setSelectedItem(null);
			}
		}
		ActionListener aBtnL = new aBtnListener();
		aBtn.addActionListener(aBtnL);
		class bBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				try 
				{
					delete();
					update();
					frame.setVisible(false);
					repaint();
				} 
				catch (SQLException e) {e.printStackTrace();}
				catch(Exception e )
				{
					try {update();} 
					catch (SQLException e1) {e1.printStackTrace();}
				frame.setVisible(false);
				repaint();
				}
			}
		}
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ActionListener bBtnL = new bBtnListener();
		bBtn.addActionListener(bBtnL);
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		panel.add(aBtn);
		panel.add(bBtn);
		return panel;
	}
	
	//Components
	public JPanel createEvent() 
	{
		eventLabel = new JLabel("Event");
		eventTextField = new JTextField(AREA_WIDTH);
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		panel.add(eventLabel, BorderLayout.CENTER);
		panel.add(eventTextField, BorderLayout.SOUTH);
		return panel;
	}
	public JPanel createDate() {
		int[] now = new int[3];
	    now = getdates();
		dateLabel = new JLabel("Date");
		dateTextField = new JTextField(10);
		year = String.valueOf(now[0]);
		month = String.valueOf(now[1]);
		day = String.valueOf(now[2]);
		 if (month.length() == 1)month = "0"+month;
		 if (day.length() == 1)month = "0"+month;
		dateTextField.setText(year+"-"+month+"-"+day);
		dateTextField.setEditable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		panel.add(dateLabel);
		panel.add(dateTextField);
		return panel;
	}
	public JPanel createPlace() {
		placeLabel = new JLabel("Place");
		placeTextField = new JTextField(10);
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(249,208,211));
		panel.add(placeLabel);
		panel.add(placeTextField);
		return panel;
	}
	public JPanel createTime() {
		timeStartCombo = new JComboBox<Integer>();
		timeEndCombo = new JComboBox<Integer>();
		timeStartCombo.addItem(null);
		timeStartCombo.addItem(8);
		timeStartCombo.addItem(9);
		timeStartCombo.addItem(10);
		timeStartCombo.addItem(11);
		timeStartCombo.addItem(12);
		timeStartCombo.addItem(13);
		timeStartCombo.addItem(14);
		timeStartCombo.addItem(15);
		timeStartCombo.addItem(16);
		timeStartCombo.addItem(17);
		timeStartCombo.addItem(18);
		timeEndCombo.addItem(null);
		class btnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				if(timeStartCombo.getSelectedItem()!=null)
				{
					timeEndCombo.removeAllItems();
					int x = (int)timeStartCombo.getSelectedItem();
					for(int a = x+1 ; a<20;a++)
					{
						timeEndCombo.addItem(a);
					}
				}
			}
		}
		ActionListener listener = new btnListener();
		timeStartCombo.addActionListener(listener);
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("From");
		JLabel label2 = new JLabel("To");
		panel.setBackground(new java.awt.Color(249,208,211));
		panel.add(label1);panel.add(timeStartCombo);
		panel.add(label2);panel.add(timeEndCombo);
		return panel;
	}

	/*----------------------------------------*/
	
	//取得系統日期函數
	public  int[] getdates()
	  {
	    int[] date_array = new int[3];
	    Calendar ca = new GregorianCalendar();  
	    date_array[0] = ca.get(Calendar.YEAR);//年
	    date_array[1] = ca.get(Calendar.MONTH)+1;//月
	    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);//日
	    return date_array;
	  }
	
	//set method
		public void setEventInfo(String e,String p,String d,int iT,int eT) 
		{
			eventTextField.setText(e);
			placeTextField.setText(p);
			dateTextField.setText(d);
			timeStartCombo.setSelectedIndex(iT-8);
			timeStartCombo.setSelectedIndex(eT-8);
		}
		
		
	public void delete() throws SQLException
	 {
	  Connection conn = SimpleDataSource.getConnection();
	  if(eventTextField.getText()==null) {eventTextField.setText(" ");}
	  if(placeTextField.getText()==null) {placeTextField.setText(" ");}
	  try
	  	{
		  PreparedStatement stat = conn.prepareStatement("DELETE FROM `Event` WHERE `eventName`=? and `place`=? and `date`=? and `initialTime`=? and `endTime`=?");
		  stat.setString(1,eventTextField.getText());
		  stat.setString(2,placeTextField.getText());
		  stat.setString(3,dateConverter());
		  stat.setInt(4,(int)timeStartCombo.getSelectedItem());
		  stat.setInt(5,(int)timeEndCombo.getSelectedItem());
		  stat.executeUpdate();
	  	} 
	  finally
	  	{
		  conn.close();
	  	}
	 }
	
	public void update() throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		try {
			PreparedStatement stat =
					conn.prepareStatement("INSERT INTO `Event`( `eventName`,`place`,`date`,`initialTime`,`endTime`) VALUES (?,?,?,?,?)");
			stat.setString(1,eventTextField.getText()+" ");
			stat.setString(2,placeTextField.getText());
			stat.setString(3,dateTextField.getText());
			stat.setInt(4,(int)timeStartCombo.getSelectedItem());
			stat.setInt(5,(int)timeEndCombo.getSelectedItem());
			stat.executeUpdate();
		}catch (Exception e )
		{JOptionPane.showMessageDialog(null, "input invalid");}
		finally {conn.close();repaint();}
	}
	
	public String dateConverter()
	{
		String[] split =dateTextField.getText().split("-");
		String years=split[0];
		String months=split[1];
		String days=split[2];
		 return years+months+days;
	}
	
	
	}
	

