import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextArea;

public class App {

	private JFrame frmCommofferer;
	private JTextField textFieldManagerName;
	private JTextField textFieldManagerMail;
	private JTextField textFieldManagerPhone;
	private JTextField textFieldDeltaAddress;
	private JTextField textFieldDeltaPhones;
	private JTextField textFieldDeltaSite;
	private JTextField textFieldClientSite;
	private JTextField textFieldClientAppeal;
	private JTextField textFieldCSV;
	private JTextField textFieldPrice;
	private JTextField textFieldTechText;
	private JTextField textFieldCommText;
	
	private static String csvPath = null;
	private static String saveName = null;
	private JTextField textFieldSave;

	/**
	 * Launch the application.
	 */
	
	private static String examplePath = "." + File.separator + "config" + File.separator + "DELTA KP.docx";
	private static String tempXMLpath = "." + File.separator + "config" + File.separator + "document.xml";
	private static String defaultsPath = "." + File.separator + "config" + File.separator + "defaults.txt";
	private JTextField textFieldFirstResult;
	private JTextField textFieldMaxResult;
	private JTextField textFieldTextTime;
	private JTextField textFieldSiteMod;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmCommofferer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCommofferer = new JFrame();
		frmCommofferer.setResizable(false);
		frmCommofferer.setTitle("CommOfferer");
		frmCommofferer.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmCommofferer.setBounds(100, 100, 675, 700);
		frmCommofferer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HashMap<String, Region> regions = null;
		try {
			regions = Region.loadRegions("./config/regions");
		} catch (IOException e1) {
			System.out.println("ПРОБЛЕМЫ С ЧТЕНИЕМ КОНФИГ-ФАЙЛОВ С РЕГИОНАМИ");
			e1.printStackTrace();
		}
		Set<Entry<String, Region>> regionSet = regions.entrySet();
		
		JTextArea textAreaRegions = new JTextArea();
		textAreaRegions.setBounds(10, 308, 649, 137);
		textAreaRegions.setLineWrap(true);
		
		JLabel label = new JLabel("\u0420\u0435\u0433\u0438\u043E\u043D:");
		label.setBounds(10, 14, 71, 14);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setForeground(new Color(245, 245, 245));
		
		JComboBox<Region> comboBoxRegion = new JComboBox<>();
		comboBoxRegion.setBounds(91, 11, 266, 20);
		comboBoxRegion.setBackground(new Color(255, 255, 255));
		JComboBox<Manager> comboBoxManager = new JComboBox<>();
		comboBoxManager.setBounds(453, 13, 194, 20);
		comboBoxManager.setBackground(new Color(255, 255, 255));
		
		//combo1
		for (Entry<String, Region> entry : regionSet) {
			comboBoxRegion.addItem(entry.getValue());
		}
		comboBoxRegion.setSelectedIndex(-1);
		comboBoxRegion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Region rgn = (Region) comboBoxRegion.getSelectedItem();
				textFieldDeltaAddress.setText(rgn.getAddress());
				textFieldDeltaPhones.setText(rgn.getPhones());
				textFieldDeltaSite.setText(rgn.getSite());
				textAreaRegions.setText(rgn.getName() + "\nПродвижение сайта предполагается осуществлять по предложенным запросам с добавлением"
						+ " городов Ростов-на-Дону, Краснодар и их производных, например: «пластиковые окна ростов», «ремонт квартир в краснодаре»");
				
				comboBoxManager.removeAllItems();

				for(Manager mng : rgn.getManagers()){
					comboBoxManager.addItem(new Manager(mng));
				}
				
			}
		});
		//combo2
		JLabel label_1 = new JLabel("\u041C\u0435\u043D\u0435\u0434\u0436\u0435\u0440:");
		label_1.setBounds(375, 16, 68, 14);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setForeground(new Color(245, 245, 245));
		comboBoxManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxManager.getSelectedIndex() == -1) return;
				
				Manager mng = (Manager) comboBoxManager.getSelectedItem();
				textFieldManagerName.setText(mng.getName());
				textFieldManagerPhone.setText(mng.getPhonenumber());
				textFieldManagerMail.setText(mng.getEmail());
			}
		});
		
		///////////
		JLabel label_2 = new JLabel("\u0418\u043C\u044F:");
		label_2.setBounds(375, 42, 68, 14);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setForeground(new Color(245, 245, 245));
		
		textFieldManagerName = new JTextField();
		textFieldManagerName.setBounds(453, 39, 194, 20);
		textFieldManagerName.setBackground(new Color(255, 255, 255));
		textFieldManagerName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u041F\u043E\u0447\u0442\u0430:");
		lblNewLabel.setBounds(375, 68, 39, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(245, 245, 245));
		
		textFieldManagerMail = new JTextField();
		textFieldManagerMail.setBounds(453, 65, 194, 20);
		textFieldManagerMail.setBackground(new Color(255, 255, 255));
		textFieldManagerMail.setColumns(10);
		
		JLabel label_3 = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D:");
		label_3.setBounds(375, 94, 54, 14);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setForeground(new Color(245, 245, 245));
		
		textFieldManagerPhone = new JTextField();
		textFieldManagerPhone.setBounds(453, 91, 194, 20);
		textFieldManagerPhone.setBackground(new Color(255, 255, 255));
		textFieldManagerPhone.setColumns(10);
		
		JLabel label_4 = new JLabel("\u0410\u0434\u0440\u0435\u0441:");
		label_4.setBounds(10, 40, 40, 14);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setForeground(new Color(245, 245, 245));
		
		textFieldDeltaAddress = new JTextField();
		textFieldDeltaAddress.setBounds(91, 37, 266, 20);
		textFieldDeltaAddress.setBackground(new Color(255, 255, 255));
		textFieldDeltaAddress.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D\u044B:");
		lblNewLabel_1.setBounds(10, 66, 71, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(245, 245, 245));
		
		textFieldDeltaPhones = new JTextField();
		textFieldDeltaPhones.setBounds(91, 63, 266, 20);
		textFieldDeltaPhones.setBackground(new Color(255, 255, 255));
		textFieldDeltaPhones.setColumns(10);
		
		JLabel label_5 = new JLabel("\u0421\u0430\u0439\u0442:");
		label_5.setBounds(10, 92, 71, 14);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setForeground(new Color(245, 245, 245));
		
		textFieldDeltaSite = new JTextField();
		textFieldDeltaSite.setBounds(91, 89, 266, 20);
		textFieldDeltaSite.setBackground(new Color(255, 255, 255));
		textFieldDeltaSite.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 129, 649, 2);
		
		JLabel lblNewLabel_2 = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u0438\u043B\u0438 \u0430\u0434\u0440\u0435\u0441 \u0441\u0430\u0439\u0442\u0430 \u043A\u043B\u0438\u0435\u043D\u0442\u0430:");
		lblNewLabel_2.setBounds(10, 142, 206, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setForeground(new Color(245, 245, 245));
		
		textFieldClientSite = new JTextField();
		textFieldClientSite.setBounds(10, 162, 439, 20);
		textFieldClientSite.setBackground(new Color(255, 255, 255));
		textFieldClientSite.setText("www.");
		textFieldClientSite.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u041E\u0431\u0440\u0430\u0449\u0435\u043D\u0438\u0435:");
		lblNewLabel_3.setBounds(10, 188, 69, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(new Color(245, 245, 245));
		
		textFieldClientAppeal = new JTextField();
		textFieldClientAppeal.setBounds(10, 205, 439, 20);
		textFieldClientAppeal.setBackground(new Color(255, 255, 255));
		textFieldClientAppeal.setText("\u0423\u0432\u0430\u0436\u0430\u0435\u043C\u044B\u0435 \u0413\u043E\u0441\u043F\u043E\u0434\u0430");
		textFieldClientAppeal.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u0424\u0430\u0439\u043B \u0441 \u0444\u0440\u0430\u0437\u0430\u043C\u0438:");
		lblNewLabel_4.setBounds(10, 251, 94, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(245, 245, 245));
		
		textFieldCSV = new JTextField();
		textFieldCSV.setForeground(new Color(211, 211, 211));
		textFieldCSV.setBounds(118, 248, 331, 20);
		textFieldCSV.setBackground(new Color(105, 105, 105));
		textFieldCSV.setEditable(false);
		textFieldCSV.setColumns(10);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.setBounds(466, 247, 43, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(csvPath == null) csvPath = "." + File.separator;
				JFileChooser jfc = new JFileChooser(csvPath);
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.setFileFilter(new FileFilter() {
					@Override public String getDescription() {return "Файлы .csv";}
					@Override public boolean accept(File f) {return f.getPath().endsWith(".csv") | f.isDirectory();}
				});
				int ret = jfc.showDialog(null, "Выбрать csv");
				if(ret == JFileChooser.APPROVE_OPTION){
					csvPath = jfc.getSelectedFile().getPath();
					textFieldCSV.setText(csvPath);
				}
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("\u0420\u0435\u0433\u0438\u043E\u043D\u044B \u043F\u0440\u043E\u0434\u0432\u0438\u0436\u0435\u043D\u0438\u044F(\u041F\u0420\u041E\u0412\u0415\u0420\u042F\u0422\u042C \u0412\u0421\u0415\u0413\u0414\u0410):");
		lblNewLabel_5.setBounds(10, 288, 259, 14);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setForeground(new Color(245, 245, 245));
		
		
		JLabel lblNewLabel_6 = new JLabel("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C:");
		lblNewLabel_6.setBounds(10, 463, 65, 14);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setForeground(new Color(245, 245, 245));
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(10, 483, 217, 20);
		textFieldPrice.setText("10 000");
		textFieldPrice.setBackground(new Color(255, 255, 255));
		textFieldPrice.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\u0422\u0435\u0445\u043D\u0438\u0447. \u0442\u0435\u043A\u0441\u0442\u044B:");
		lblNewLabel_7.setBounds(245, 463, 94, 14);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setForeground(new Color(245, 245, 245));
		
		textFieldTechText = new JTextField();
		textFieldTechText.setBounds(245, 483, 203, 20);
		textFieldTechText.setText("3 500 \u2013 5 500");
		textFieldTechText.setBackground(new Color(255, 255, 255));
		textFieldTechText.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("\u041A\u043E\u043C\u043C\u0435\u0440\u0447. \u0442\u0435\u043A\u0441\u0442\u044B:");
		lblNewLabel_8.setBounds(245, 509, 104, 14);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setForeground(new Color(245, 245, 245));
		
		textFieldCommText = new JTextField();
		textFieldCommText.setBounds(245, 529, 203, 20);
		textFieldCommText.setText("5 000 \u2013 9 500");
		textFieldCommText.setBackground(new Color(255, 255, 255));
		textFieldCommText.setColumns(10);
				
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 236, 649, 5);
		
		JLabel label_6 = new JLabel("\u041A\u0443\u0434\u0430 \u0441\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C:");
		label_6.setBounds(10, 631, 97, 14);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setForeground(new Color(245, 245, 245));
		
		textFieldSave = new JTextField();
		textFieldSave.setForeground(new Color(211, 211, 211));
		textFieldSave.setBounds(121, 628, 331, 20);
		textFieldSave.setBackground(new Color(105, 105, 105));
		textFieldSave.setEditable(false);
		textFieldSave.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.setBounds(465, 627, 44, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc = new JFileChooser();
				
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.addChoosableFileFilter(new FileFilter() {
					@Override public String getDescription() {return "Файлы Word .docx";}
					@Override public boolean accept(File f) {return f.getName().endsWith(".docx") | f.isDirectory();}
				});
				
				
				if(saveName == null){
					saveName = "." + File.separator + "Коммерческое.docx";
				}
				
				File f = new File(saveName);
				jfc.setAcceptAllFileFilterUsed(false);

				jfc.setSelectedFile(f);
				jfc.setCurrentDirectory(new File(f.getParent()));
				
				int ret = jfc.showDialog(null, "Выбрать");
				if(ret == JFileChooser.APPROVE_OPTION){
					saveName = jfc.getSelectedFile().getPath();
					if(!saveName.endsWith(".docx"))
						saveName += ".docx";
					textFieldSave.setText(saveName);
				}
			}
		});
		
		JButton button = new JButton("\u0421\u0434\u0435\u043B\u0430\u0442\u044C");
		button.setFont(new Font("Times New Roman", Font.BOLD, 16));
		button.setBounds(535, 627, 124, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxRegion.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(frmCommofferer, "Не выбран регион");
					return;
				}
				if(csvPath == null) {
					JOptionPane.showMessageDialog(frmCommofferer, "Не выбран файл csv");
					return;
				}
				frmCommofferer.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				CommBuilder cb = new CommBuilder(examplePath, tempXMLpath);
				HashMap<String, String> values = new HashMap<>();
				//Менеджер
				values.put("placeholdermanagername", textFieldManagerName.getText());
				values.put("placeholderphonenumber", textFieldManagerPhone.getText());
				values.put("placeholderemail", textFieldManagerMail.getText());
				values.put("placeholderemail1", textFieldManagerMail.getText());
				//Дельта
				values.put("placeholderaddress", textFieldDeltaAddress.getText());
				values.put("placeholderphonenumbers", textFieldDeltaPhones.getText());
				values.put("placeholderdeltasite", textFieldDeltaSite.getText());
				//Клиент
				values.put("placeholdersitename", (textFieldClientSite.getText().startsWith("www.") ? textFieldClientSite.getText() : "www." + textFieldClientSite.getText()));
				values.put("placeholderappeal", textFieldClientAppeal.getText());
				//Цены и сроки
				values.put("placeholderprice", textFieldPrice.getText());
				values.put("placeholderfirstresult", textFieldFirstResult.getText());
				values.put("placeholdermaxresult", textFieldMaxResult.getText());
				values.put("placeholdertechtext", textFieldTechText.getText());
				values.put("placeholdercommtext", textFieldCommText.getText());
				values.put("placeholdertexttime", textFieldTextTime.getText());
				values.put("placeholdersitemod", textFieldSiteMod.getText());
    
				int code = cb.assemble(csvPath, saveName, values, textAreaRegions.getText());
				

				frmCommofferer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if(code == 0){
					showMessage("Готово!");
				}else{
					showMessage("Что-то пошло не так");
				}
			}
		});
		
		JLabel label_7 = new JLabel("\u041F\u0435\u0440\u0432\u044B\u0435 \u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u044B:");
		label_7.setBounds(10, 509, 124, 14);
		label_7.setForeground(new Color(245, 245, 245));
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldFirstResult = new JTextField();
		textFieldFirstResult.setBounds(10, 529, 217, 20);
		textFieldFirstResult.setText("\u0434\u043E 2 \u043C\u0435\u0441\u044F\u0446\u0435\u0432");
		textFieldFirstResult.setColumns(10);
		
		JLabel label_8 = new JLabel("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u044B\u0435 \u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u044B:");
		label_8.setBounds(10, 555, 167, 14);
		label_8.setForeground(new Color(245, 245, 245));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldMaxResult = new JTextField();
		textFieldMaxResult.setBounds(10, 575, 217, 20);
		textFieldMaxResult.setText("\u043E\u0442 2 \u0434\u043E 6 \u043C\u0435\u0441\u044F\u0446\u0435\u0432");
		textFieldMaxResult.setColumns(10);
		
		JLabel label_9 = new JLabel("\u0421\u0440\u043E\u043A\u0438:");
		label_9.setBounds(245, 555, 37, 14);
		label_9.setForeground(new Color(245, 245, 245));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldTextTime = new JTextField();
		textFieldTextTime.setBounds(245, 575, 203, 20);
		textFieldTextTime.setText("1 \u043C\u0435\u0441\u044F\u0446");
		textFieldTextTime.setColumns(10);
		
		JLabel label_10 = new JLabel("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C:");
		label_10.setBounds(465, 463, 65, 14);
		label_10.setForeground(new Color(245, 245, 245));
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldSiteMod = new JTextField();
		textFieldSiteMod.setBounds(466, 483, 193, 20);
		textFieldSiteMod.setText("\u0417\u0410 \u041D\u0410\u0428 \u0421\u0427\u0415\u0422");
		textFieldSiteMod.setColumns(10);
		
		
		File defaultsFile = new File(defaultsPath);
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(defaultsFile);
			isr = new InputStreamReader(fis, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		BufferedReader bReader = new BufferedReader(isr);
		
		try {
			textFieldClientSite.setText(bReader.readLine());
			textFieldClientAppeal.setText(bReader.readLine());
			textFieldPrice.setText(bReader.readLine());
			textFieldFirstResult.setText(bReader.readLine());
			textFieldMaxResult.setText(bReader.readLine());
			textFieldTechText.setText(bReader.readLine());
			textFieldCommText.setText(bReader.readLine());
			textFieldTextTime.setText(bReader.readLine());
			textFieldSiteMod.setText(bReader.readLine());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			
		try {
			bReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		frmCommofferer.getContentPane().setLayout(null);
		frmCommofferer.getContentPane().add(textAreaRegions);
		frmCommofferer.getContentPane().add(separator_1);
		frmCommofferer.getContentPane().add(separator);
		frmCommofferer.getContentPane().add(label_5);
		frmCommofferer.getContentPane().add(label);
		frmCommofferer.getContentPane().add(lblNewLabel_1);
		frmCommofferer.getContentPane().add(label_4);
		frmCommofferer.getContentPane().add(comboBoxRegion);
		frmCommofferer.getContentPane().add(textFieldDeltaAddress);
		frmCommofferer.getContentPane().add(textFieldDeltaPhones);
		frmCommofferer.getContentPane().add(textFieldDeltaSite);
		frmCommofferer.getContentPane().add(label_2);
		frmCommofferer.getContentPane().add(label_1);
		frmCommofferer.getContentPane().add(lblNewLabel);
		frmCommofferer.getContentPane().add(label_3);
		frmCommofferer.getContentPane().add(textFieldManagerPhone);
		frmCommofferer.getContentPane().add(textFieldManagerMail);
		frmCommofferer.getContentPane().add(textFieldManagerName);
		frmCommofferer.getContentPane().add(comboBoxManager);
		frmCommofferer.getContentPane().add(lblNewLabel_5);
		frmCommofferer.getContentPane().add(lblNewLabel_2);
		frmCommofferer.getContentPane().add(lblNewLabel_3);
		frmCommofferer.getContentPane().add(textFieldClientAppeal);
		frmCommofferer.getContentPane().add(textFieldClientSite);
		frmCommofferer.getContentPane().add(lblNewLabel_4);
		frmCommofferer.getContentPane().add(textFieldCSV);
		frmCommofferer.getContentPane().add(btnNewButton);
		frmCommofferer.getContentPane().add(label_10);
		frmCommofferer.getContentPane().add(lblNewLabel_6);
		frmCommofferer.getContentPane().add(textFieldFirstResult);
		frmCommofferer.getContentPane().add(textFieldMaxResult);
		frmCommofferer.getContentPane().add(textFieldPrice);
		frmCommofferer.getContentPane().add(label_7);
		frmCommofferer.getContentPane().add(label_8);
		frmCommofferer.getContentPane().add(label_9);
		frmCommofferer.getContentPane().add(lblNewLabel_7);
		frmCommofferer.getContentPane().add(lblNewLabel_8);
		frmCommofferer.getContentPane().add(textFieldTechText);
		frmCommofferer.getContentPane().add(textFieldCommText);
		frmCommofferer.getContentPane().add(textFieldTextTime);
		frmCommofferer.getContentPane().add(textFieldSiteMod);
		frmCommofferer.getContentPane().add(label_6);
		frmCommofferer.getContentPane().add(textFieldSave);
		frmCommofferer.getContentPane().add(btnNewButton_1);
		frmCommofferer.getContentPane().add(button);
	}
	
	private void showMessage(String message){
		JOptionPane.showMessageDialog(frmCommofferer, message);
	}
}
