import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pojo.Manager;
import pojo.Region;
import promo.PromoCommBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import creation.CreationCommBuilder;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Font;

public class App {

	private JFrame frmCommofferer;
	

	/**
	 * Launch the application.
	 */
	
	private static String examplePromoPath = "." + File.separator + "config" + File.separator + "DELTA KP.docx";
	private static String exampleCreationPath = "." + File.separator + "config" + File.separator + "KP_CREATION.docx";
	private static String regionText = "Продвижение сайта предполагается осуществлять по предложенным запросам с добавлением"
			+ " городов !!!ЗАМЕНИТЬ!!! и их производных, например: «ПРИМЕР 1», «ПРИМЕР 2»";
	
	private static String tempXMLpath = "." + File.separator + "config" + File.separator + "document.xml";
	private JTextField textField_deltaAddress;
	private JTextField textField_deltaPhones;
	private JTextField textField_deltaSite;
	private JTextField textField_mngName;
	private JTextField textField_mngPlace;
	private JTextField textField_mngEmail;
	private JTextField textField_mngPhone;
	private JTextField txtWww;
	private JTextField textField_appeal;
	private JTextField textField_price;
	private JTextField textField_firstResult;
	private JTextField textField_maxResult;
	private JTextField textField_techText;
	private JTextField textField_commText;
	private JTextField textField_textTime;
	private JTextField textField_mod;
	private JTextField textField_csv;
	private JTextField textField_deltaAddress2;
	private JTextField textField_deltaPhones2;
	private JTextField textField_deltaSite2;
	private JTextField textField_mngName2;
	private JTextField textField_mngPlace2;
	private JTextField textField_mngEmail2;
	private JTextField textField_mngPhone2;
	private JTextField textField_companyName;
	private JTextField textField_discuss;
	private JTextField textField_prototype;
	private JTextField textField_design;
	private JTextField textField_collect;
	private JTextField textField_template;
	private JTextField textField_templateconf;
	private JTextField textField_hosting;
	private JTextField textField_cms;
	private JTextField textField_templateinst;
	private JTextField textField_filling;
	private JTextField textField_test;
	private JTextField textField_enter;
	
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
		frmCommofferer.setBounds(100, 100, 851, 670);
		frmCommofferer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmCommofferer.getContentPane().setLayout(null);
		
		ArrayList<JTextField> moduleNames = new ArrayList<>();
		ArrayList<JTextField> moduleDescs = new ArrayList<>();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 845, 641);
		tabbedPane.addChangeListener((event) -> {
			if(tabbedPane.getSelectedIndex() == 0){
				frmCommofferer.setBounds(frmCommofferer.getBounds().x, frmCommofferer.getBounds().y, 700, 670);
				tabbedPane.setBounds(0, 0, 695, 641);
			}else{
				frmCommofferer.setBounds(frmCommofferer.getBounds().x, frmCommofferer.getBounds().y, 850, 670);
				tabbedPane.setBounds(0, 0, 845, 641);
			}
		});
		frmCommofferer.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u041F\u043E \u043F\u0440\u043E\u0434\u0432\u0438\u0436\u0435\u043D\u0438\u044E", null, panel, null);
		panel.setLayout(null);
		
		HashMap<String, Region> regions = null;
		try {
			regions = Region.loadRegions("./config/regions");
		} catch (IOException e1) {
			System.out.println("ПРОБЛЕМЫ С ЧТЕНИЕМ КОНФИГ-ФАЙЛОВ С РЕГИОНАМИ");
			e1.printStackTrace();
		}
		Set<Entry<String, Region>> regionSet = regions.entrySet();
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 42, 486, 2);
		panel.add(separator);
		
		JLabel lblDelta = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u044B Delta:");
		lblDelta.setBounds(10, 50, 235, 14);
		panel.add(lblDelta);
		
		textField_deltaAddress = new JTextField();
		textField_deltaAddress.setBounds(10, 70, 235, 20);
		panel.add(textField_deltaAddress);
		textField_deltaAddress.setColumns(10);
		
		textField_deltaPhones = new JTextField();
		textField_deltaPhones.setBounds(10, 98, 235, 20);
		panel.add(textField_deltaPhones);
		textField_deltaPhones.setColumns(10);
		
		textField_deltaSite = new JTextField();
		textField_deltaSite.setBounds(10, 125, 235, 20);
		panel.add(textField_deltaSite);
		textField_deltaSite.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u044B \u043C\u0435\u043D\u0435\u0434\u0436\u0435\u0440\u0430:");
		lblNewLabel.setBounds(261, 50, 235, 14);
		panel.add(lblNewLabel);
		
		textField_mngName = new JTextField();
		textField_mngName.setBounds(261, 70, 235, 20);
		panel.add(textField_mngName);
		textField_mngName.setColumns(10);
		
		textField_mngPlace = new JTextField();
		textField_mngPlace.setBounds(261, 98, 235, 20);
		panel.add(textField_mngPlace);
		textField_mngPlace.setColumns(10);
		
		textField_mngEmail = new JTextField();
		textField_mngEmail.setBounds(261, 125, 235, 20);
		panel.add(textField_mngEmail);
		textField_mngEmail.setColumns(10);
		
		textField_mngPhone = new JTextField();
		textField_mngPhone.setBounds(261, 151, 235, 20);
		panel.add(textField_mngPhone);
		textField_mngPhone.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("URL \u0441\u0430\u0439\u0442\u0430 (\u0441 WWW.)");
		lblNewLabel_1.setBounds(10, 189, 235, 14);
		panel.add(lblNewLabel_1);
		
		txtWww = new JTextField();
		txtWww.setText("WWW.");
		txtWww.setBounds(10, 210, 235, 20);
		panel.add(txtWww);
		txtWww.setColumns(10);
		
		JLabel label = new JLabel("\u041E\u0431\u0440\u0430\u0449\u0435\u043D\u0438\u0435");
		label.setBounds(261, 189, 235, 14);
		panel.add(label);
		
		textField_appeal = new JTextField();
		textField_appeal.setText("\u0423\u0432\u0430\u0436\u0430\u0435\u043C\u044B\u0435 \u0413\u043E\u0441\u043F\u043E\u0434\u0430");
		textField_appeal.setBounds(261, 210, 235, 20);
		panel.add(textField_appeal);
		textField_appeal.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0422\u0435\u043A\u0441\u0442 \u0441 \u043E\u0431\u0440\u0430\u0449\u0435\u043D\u0438\u0435\u043C:");
		label_1.setBounds(10, 239, 235, 14);
		panel.add(label_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u0420\u0435\u0433\u0438\u043E\u043D\u044B \u043F\u0440\u043E\u0434\u0432\u0438\u0436\u0435\u043D\u0438\u044F (\u0441 \u043A\u0440\u0430\u0441\u043D\u043E\u0439 \u0441\u0442\u0440\u043E\u043A\u0438 \u043A\u0430\u0436\u0434\u044B\u0439)");
		lblNewLabel_2.setBounds(10, 372, 300, 14);
		panel.add(lblNewLabel_2);
		
		JTextArea textArea_regionText = new JTextArea();
		textArea_regionText.setFont(new Font("Monospaced", Font.PLAIN, 10));
		textArea_regionText.setBounds(10, 397, 486, 109);
		panel.add(textArea_regionText);
		textArea_regionText.setLineWrap(true);
		
		
		JLabel lblNewLabel_3 = new JLabel("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C:");
		lblNewLabel_3.setBounds(526, 11, 154, 14);
		panel.add(lblNewLabel_3);
		
		textField_price = new JTextField();
		textField_price.setText("10 000");
		textField_price.setBounds(526, 28, 154, 20);
		panel.add(textField_price);
		textField_price.setColumns(10);
		
		JTextArea textArea_audittext = new JTextArea();
		textArea_audittext.setFont(new Font("Monospaced", Font.PLAIN, 10));
		textArea_audittext.setBounds(10, 260, 486, 101);
		panel.add(textArea_audittext);
		textArea_audittext.setLineWrap(true);
		textArea_audittext.setText("\u041C\u044B \u043F\u0440\u043E\u0432\u0435\u043B\u0438 \u0430\u0443\u0434\u0438\u0442 \u0432\u0430\u0448\u0435\u0433\u043E \u0440\u044B\u043D\u043A\u0430 \u0438 \u043A\u043E\u043D\u043A\u0443\u0440\u0435\u043D\u0442\u043D\u043E\u0439 \u0441\u0440\u0435\u0434\u044B, \u0432 \u0441\u0432\u044F\u0437\u0438 \u0441 \u0447\u0435\u043C \u043F\u0440\u0435\u0434\u043B\u0430\u0433\u0430\u0435\u043C \u043A\u043E\u043C\u043F\u043B\u0435\u043A\u0441\u043D\u043E\u0435 \u043F\u0440\u043E\u0434\u0432\u0438\u0436\u0435\u043D\u0438\u0435 \u0441\u0430\u0439\u0442\u0430 \u0432 \u043F\u043E\u0438\u0441\u043A\u043E\u0432\u044B\u0445 \u0441\u0438\u0441\u0442\u0435\u043C\u0430\u0445 \u042F\u043D\u0434\u0435\u043A\u0441, Google, \u043D\u0430 \u043F\u0435\u0440\u0432\u0443\u044E \u0441\u0442\u0440\u0430\u043D\u0438\u0446\u0443 (\u0422\u041E\u0420-10) \u043F\u043E \u0431\u043E\u043B\u0435\u0435 \u0447\u0435\u043C 70% \u043A\u043B\u044E\u0447\u0435\u0432\u044B\u0445 \u0444\u0440\u0430\u0437 \u0438 \u043F\u043E\u0434\u0434\u0435\u0440\u0436\u043A\u0443 \u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u043E\u0432 \u043F\u0440\u043E\u0434\u0432\u0438\u0436\u0435\u043D\u0438\u044F.");
		
		JLabel label_2 = new JLabel("\u041F\u0435\u0440\u0432\u044B\u0435 \u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u044B:");
		label_2.setBounds(526, 53, 154, 14);
		panel.add(label_2);
		
		textField_firstResult = new JTextField();
		textField_firstResult.setText("\u0434\u043E 3 \u043C\u0435\u0441\u044F\u0446\u0435\u0432");
		textField_firstResult.setBounds(526, 78, 154, 20);
		panel.add(textField_firstResult);
		textField_firstResult.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u041C\u0430\u043A\u0441. \u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u044B:");
		lblNewLabel_4.setBounds(526, 109, 154, 14);
		panel.add(lblNewLabel_4);
		
		textField_maxResult = new JTextField();
		textField_maxResult.setText("\u043E\u0442 4 \u0434\u043E 6 \u043C\u0435\u0441\u044F\u0446\u0435\u0432");
		textField_maxResult.setBounds(526, 134, 154, 20);
		panel.add(textField_maxResult);
		textField_maxResult.setColumns(10);
		
		JLabel label_3 = new JLabel("\u0422\u0435\u0445. \u0442\u0435\u043A\u0441\u0442\u044B:");
		label_3.setBounds(526, 177, 154, 14);
		panel.add(label_3);
		
		textField_techText = new JTextField();
		textField_techText.setText("4 000");
		textField_techText.setBounds(526, 202, 154, 20);
		panel.add(textField_techText);
		textField_techText.setColumns(10);
		
		JLabel label_4 = new JLabel("\u041A\u043E\u043C\u043C. \u0442\u0435\u043A\u0441\u0442\u044B:");
		label_4.setBounds(526, 233, 154, 14);
		panel.add(label_4);
		
		textField_commText = new JTextField();
		textField_commText.setText("6 000");
		textField_commText.setBounds(526, 258, 154, 20);
		panel.add(textField_commText);
		textField_commText.setColumns(10);
		
		JLabel label_5 = new JLabel("\u0421\u0440\u043E\u043A\u0438:");
		label_5.setBounds(526, 289, 154, 14);
		panel.add(label_5);
		
		textField_textTime = new JTextField();
		textField_textTime.setText("1 \u043C\u0435\u0441\u044F\u0446");
		textField_textTime.setBounds(526, 314, 154, 20);
		panel.add(textField_textTime);
		textField_textTime.setColumns(10);
		
		JLabel label_6 = new JLabel("\u041C\u043E\u0434. \u0438 \u0430\u0434\u0430\u043F\u0442. \u0441\u0430\u0439\u0442\u0430:");
		label_6.setBounds(526, 355, 154, 14);
		panel.add(label_6);
		
		textField_mod = new JTextField();
		textField_mod.setText("\u0417\u0410 \u041D\u0410\u0428 \u0421\u0427\u0415\u0422");
		textField_mod.setBounds(526, 380, 154, 20);
		panel.add(textField_mod);
		textField_mod.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(509, 11, 2, 495);
		panel.add(separator_1);
		
		JLabel lblCsv = new JLabel("CSV \u0441 \u0444\u0440\u0430\u0437\u0430\u043C\u0438:");
		lblCsv.setBounds(10, 517, 100, 14);
		panel.add(lblCsv);
		
		textField_csv = new JTextField();
		textField_csv.setEditable(false);
		textField_csv.setBounds(10, 542, 590, 20);
		panel.add(textField_csv);
		textField_csv.setColumns(10);
		
		JButton button_CSV = new JButton("...");
		button_CSV.addActionListener((event) -> {
			JFileChooser fileopen = new JFileChooser();
			
			fileopen.setFileFilter(new FileNameExtensionFilter("Документы CSV", "csv"));
			fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			int ret = fileopen.showDialog(null, "Открыть файл csv с фразами");
			if(ret == JFileChooser.APPROVE_OPTION){
				File file = fileopen.getSelectedFile();
				textField_csv.setText(file.getAbsolutePath());
			}
		});
		button_CSV.setBounds(610, 541, 70, 23);
		panel.add(button_CSV);
		
		
		JComboBox<Manager> comboBox_Manager = new JComboBox<>();
		comboBox_Manager.setBounds(260, 11, 235, 20);
		panel.add(comboBox_Manager);
		
		comboBox_Manager.addItemListener((event) ->{
			Manager m = (Manager)comboBox_Manager.getSelectedItem();
			if(m != null){
				textField_mngName.setText(m.getName());
				textField_mngPlace.setText(m.getPosition());
				textField_mngEmail.setText(m.getEmail());
				textField_mngPhone.setText(m.getPhonenumber());
			}
			
		});
		
		JComboBox<Region> comboBox_Region = new JComboBox<>();
		comboBox_Region.setBounds(10, 11, 235, 20);
		
		comboBox_Region.addItemListener((event) ->{
			Region r = (Region)comboBox_Region.getSelectedItem();
			textField_deltaAddress.setText(r.getAddress());
			textField_deltaPhones.setText(r.getPhones());
			textField_deltaSite.setText(r.getSite());
			textArea_regionText.setText(r.getName() + "\n" + regionText);
			
			comboBox_Manager.removeAllItems();
			for(Manager m : r.getManagers()){
				comboBox_Manager.addItem(m);
			}
			comboBox_Manager.setSelectedIndex(0);
		});
		
		for(Entry<String, Region> e : regionSet){
			comboBox_Region.addItem(e.getValue());
		}

		panel.add(comboBox_Region);
		
		JButton btnNewButton = new JButton("\u0414\u0435\u043B\u0430\u0442\u044C!");
		btnNewButton.addActionListener( (e) -> {
			HashMap<String, String> values = new HashMap<>();
			
			String regiontext = textArea_regionText.getText();
			String audittext = textArea_audittext.getText();
			
			values.put("placeholdermanagernick", ((Manager)comboBox_Manager.getSelectedItem()).getNick());
			values.put("placeholdermanagername", textField_mngName.getText());
			values.put("placeholdermanagerposition", textField_mngPlace.getText());
			values.put("placeholderemail", textField_mngEmail.getText());
			values.put("placeholderphonenumber", textField_mngPhone.getText());
			
			values.put("placeholdersitename", txtWww.getText());
			
			values.put("placeholderaddress", textField_deltaAddress.getText());
			values.put("placeholderphonenumbers", textField_deltaPhones.getText());
			values.put("placeholderdeltasite", textField_deltaSite.getText());
			
			values.put("placeholderappeal", textField_appeal.getText());
			
			values.put("placeholderprice", textField_price.getText());
			values.put("placeholderfirstresult", textField_firstResult.getText());
			values.put("placeholdermaxresult", textField_maxResult.getText());
			
			values.put("placeholdertechtext", textField_techText.getText());
			values.put("placeholdercommtext", textField_commText.getText());
			values.put("placeholdertexttime", textField_textTime.getText());
			
			values.put("placeholdersitemod", textField_mod.getText());
			
			PromoCommBuilder cb = new PromoCommBuilder(examplePromoPath, tempXMLpath, ".");
			String csvPath = textField_csv.getText();
			cb.assemble(csvPath, csvPath.replace(".csv", ".docx"), values, regiontext, audittext);
			
			showMessage("Готово!");
		});
		btnNewButton.setBounds(526, 579, 154, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("\u041F\u043E \u0441\u043E\u0437\u0434\u0430\u043D\u0438\u044E", null, panel_1, null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 42, 486, 2);
		panel_1.add(separator_2);
		
		JLabel label_7 = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u044B Delta:");
		label_7.setBounds(10, 50, 235, 14);
		panel_1.add(label_7);
		
		textField_deltaAddress2 = new JTextField();
		textField_deltaAddress2.setColumns(10);
		textField_deltaAddress2.setBounds(10, 70, 235, 20);
		panel_1.add(textField_deltaAddress2);
		
		textField_deltaPhones2 = new JTextField();
		textField_deltaPhones2.setColumns(10);
		textField_deltaPhones2.setBounds(10, 98, 235, 20);
		panel_1.add(textField_deltaPhones2);
		
		textField_deltaSite2 = new JTextField();
		textField_deltaSite2.setColumns(10);
		textField_deltaSite2.setBounds(10, 125, 235, 20);
		panel_1.add(textField_deltaSite2);
		
		JLabel label_8 = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u044B \u043C\u0435\u043D\u0435\u0434\u0436\u0435\u0440\u0430:");
		label_8.setBounds(261, 50, 235, 14);
		panel_1.add(label_8);
		
		textField_mngName2 = new JTextField();
		textField_mngName2.setColumns(10);
		textField_mngName2.setBounds(261, 70, 235, 20);
		panel_1.add(textField_mngName2);
		
		textField_mngPlace2 = new JTextField();
		textField_mngPlace2.setColumns(10);
		textField_mngPlace2.setBounds(261, 98, 235, 20);
		panel_1.add(textField_mngPlace2);
		
		textField_mngEmail2 = new JTextField();
		textField_mngEmail2.setColumns(10);
		textField_mngEmail2.setBounds(261, 125, 235, 20);
		panel_1.add(textField_mngEmail2);
		
		textField_mngPhone2 = new JTextField();
		textField_mngPhone2.setColumns(10);
		textField_mngPhone2.setBounds(261, 151, 235, 20);
		panel_1.add(textField_mngPhone2);
		
		JLabel label_9 = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u043A\u043E\u043C\u043F\u0430\u043D\u0438\u0438 \u043A\u043B\u0438\u0435\u043D\u0442\u0430");
		label_9.setBounds(10, 189, 235, 14);
		panel_1.add(label_9);
		
		textField_companyName = new JTextField();
		textField_companyName.setColumns(10);
		textField_companyName.setBounds(10, 210, 235, 20);
		panel_1.add(textField_companyName);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(509, 11, 2, 593);
		panel_1.add(separator_3);
		
		JComboBox<Manager> comboBox_Manager2 = new JComboBox<>();
		comboBox_Manager2.setBounds(260, 11, 235, 20);
		panel_1.add(comboBox_Manager2);
		
		comboBox_Manager2.addItemListener((event) ->{
			Manager m = (Manager)comboBox_Manager2.getSelectedItem();
			if(m != null){
				textField_mngName2.setText(m.getName());
				textField_mngPlace2.setText(m.getPosition());
				textField_mngEmail2.setText(m.getEmail());
				textField_mngPhone2.setText(m.getPhonenumber());
			}
			
		});
		
		JComboBox<Region> comboBox_Region2 = new JComboBox<>();
		comboBox_Region2.setBounds(10, 11, 235, 20);
		
		comboBox_Region2.addItemListener((event) ->{
			Region r = (Region)comboBox_Region2.getSelectedItem();
			textField_deltaAddress2.setText(r.getAddress());
			textField_deltaPhones2.setText(r.getPhones());
			textField_deltaSite2.setText(r.getSite());
			
			comboBox_Manager2.removeAllItems();
			for(Manager m : r.getManagers()){
				comboBox_Manager2.addItem(m);
			}
			comboBox_Manager2.setSelectedIndex(0);
		});
		
		for(Entry<String, Region> e : regionSet){
			comboBox_Region2.addItem(e.getValue());
		}

		panel_1.add(comboBox_Region2);
		
		JButton button_1 = new JButton("\u0414\u0435\u043B\u0430\u0442\u044C!");
		button_1.setBounds(521, 581, 309, 23);
		panel_1.add(button_1);
		
		JLabel lblNewLabel_5 = new JLabel("\u0420\u0430\u0441\u0446\u0435\u043D\u043A\u0438:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 254, 486, 14);
		panel_1.add(lblNewLabel_5);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 241, 486, 2);
		panel_1.add(separator_4);
		
		JLabel label_10 = new JLabel("\u041E\u0431\u0441\u0443\u0436\u0434\u0435\u043D\u0438\u0435 \u0438 \u0441\u043E\u0433\u043B\u0430\u0441\u043E\u0432\u0430\u043D\u0438\u0435 \u0434\u0435\u0442\u0430\u043B\u0435\u0439 \u043F\u0440\u043E\u0435\u043A\u0442\u0430");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_10.setBounds(10, 279, 235, 14);
		panel_1.add(label_10);
		
		textField_discuss = new JTextField();
		textField_discuss.setText(" ");
		textField_discuss.setBounds(10, 304, 235, 20);
		panel_1.add(textField_discuss);
		textField_discuss.setColumns(10);
		
		JLabel label_11 = new JLabel("\u041F\u0440\u043E\u0442\u043E\u0442\u0438\u043F\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_11.setBounds(10, 335, 235, 14);
		panel_1.add(label_11);
		
		textField_prototype = new JTextField();
		textField_prototype.setText("1 500");
		textField_prototype.setBounds(10, 360, 235, 20);
		panel_1.add(textField_prototype);
		textField_prototype.setColumns(10);
		
		JLabel label_12 = new JLabel("\u0420\u0430\u0437\u0440\u0430\u0431\u043E\u0442\u043A\u0430 \u0438 \u0441\u043E\u0433\u043B\u0430\u0441\u043E\u0432\u0430\u043D\u0438\u0435 \u0434\u0438\u0437\u0430\u0439\u043D-\u043C\u0430\u043A\u0435\u0442\u0430");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_12.setBounds(10, 391, 235, 14);
		panel_1.add(label_12);
		
		textField_design = new JTextField();
		textField_design.setText("7 000");
		textField_design.setBounds(10, 416, 235, 20);
		panel_1.add(textField_design);
		textField_design.setColumns(10);
		
		JLabel label_13 = new JLabel("\u0421\u0431\u043E\u0440 \u0433\u0440\u0430\u0444-\u0438\u0445 \u0438 \u0441\u0442\u0438\u043B-\u0438\u0445 \u0440\u0435\u0441. \u0434\u043B\u044F \u0448\u0430\u0431\u043B\u043E\u043D\u043E\u0432 \u0441\u0442\u0440\u0430\u043D\u0438\u0446");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_13.setBounds(10, 447, 235, 14);
		panel_1.add(label_13);
		
		textField_collect = new JTextField();
		textField_collect.setText("1 000");
		textField_collect.setBounds(10, 472, 235, 20);
		panel_1.add(textField_collect);
		textField_collect.setColumns(10);
		
		JLabel label_14 = new JLabel("\u0412\u0435\u0440\u0441\u0442\u043A\u0430 \u0448\u0430\u0431\u043B\u043E\u043D\u043E\u0432 \u0441\u0442\u0440\u0430\u043D\u0438\u0446");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_14.setBounds(10, 503, 235, 14);
		panel_1.add(label_14);
		
		textField_template = new JTextField();
		textField_template.setText("3 500");
		textField_template.setBounds(10, 528, 235, 20);
		panel_1.add(textField_template);
		textField_template.setColumns(10);
		
		JLabel label_15 = new JLabel("\u041D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0430 \u0448\u0430\u0431\u043B\u043E\u043D\u043E\u0432 \u043F\u043E\u0434 CMS MODx Evolution");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_15.setBounds(10, 559, 235, 14);
		panel_1.add(label_15);
		
		textField_templateconf = new JTextField();
		textField_templateconf.setText("1 200");
		textField_templateconf.setBounds(10, 584, 235, 20);
		panel_1.add(textField_templateconf);
		textField_templateconf.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\u041D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0430 \u0445\u043E\u0441\u0442\u0438\u043D\u0433\u0430 \u0434\u043B\u044F \u043D\u043E\u0432\u043E\u0433\u043E \u0441\u0430\u0439\u0442\u0430");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_6.setBounds(261, 279, 235, 14);
		panel_1.add(lblNewLabel_6);
		
		textField_hosting = new JTextField();
		textField_hosting.setText("700");
		textField_hosting.setBounds(261, 304, 235, 20);
		panel_1.add(textField_hosting);
		textField_hosting.setColumns(10);
		
		JLabel label_16 = new JLabel("\u0423\u0441\u0442\u0430\u043D\u043E\u0432\u043A\u0430 \u0438 \u043D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0430 CMS MODx Evolution");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_16.setBounds(261, 335, 235, 14);
		panel_1.add(label_16);
		
		textField_cms = new JTextField();
		textField_cms.setText("2 500");
		textField_cms.setBounds(261, 360, 235, 20);
		panel_1.add(textField_cms);
		textField_cms.setColumns(10);
		
		JLabel label_17 = new JLabel("\u0423\u0441\u0442\u0430\u043D\u043E\u0432\u043A\u0430 \u043F\u043E\u0434-\u043D\u044B\u0445 \u0448\u0430\u0431\u043B\u043E\u043D\u043E\u0432 \u043D\u0430 MODx Evolution");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_17.setBounds(261, 391, 235, 14);
		panel_1.add(label_17);
		
		textField_templateinst = new JTextField();
		textField_templateinst.setText("500");
		textField_templateinst.setBounds(261, 416, 235, 20);
		panel_1.add(textField_templateinst);
		textField_templateinst.setColumns(10);
		
		JLabel label_18 = new JLabel("\u041D\u0430\u043F\u043E\u043B\u043D. \u0431\u0430\u0437. \u0438\u043D\u0444-\u0435\u0439 \u043E\u0441\u043D. \u0440\u0430\u0437\u0434\u0435\u043B\u043E\u0432 \u0441\u0430\u0439\u0442\u0430");
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_18.setBounds(261, 447, 235, 14);
		panel_1.add(label_18);
		
		textField_filling = new JTextField();
		textField_filling.setText("300");
		textField_filling.setBounds(261, 472, 235, 20);
		panel_1.add(textField_filling);
		textField_filling.setColumns(10);
		
		JLabel label_19 = new JLabel("\u0422\u0435\u0441\u0442\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435 \u0440\u0435\u0441\u0443\u0440\u0441\u0430");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_19.setBounds(261, 503, 235, 14);
		panel_1.add(label_19);
		
		textField_test = new JTextField();
		textField_test.setText("1 500");
		textField_test.setBounds(261, 528, 235, 20);
		panel_1.add(textField_test);
		textField_test.setColumns(10);
		
		JLabel label_20 = new JLabel("\u0412\u0432\u043E\u0434 \u043F\u0440\u043E\u0435\u043A\u0442\u0430 \u0432 \u044D\u043A\u0441\u043F\u043B\u0443\u0430\u0442\u0430\u0446\u0438\u044E");
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_20.setBounds(261, 559, 235, 14);
		panel_1.add(label_20);
		
		textField_enter = new JTextField();
		textField_enter.setText("800");
		textField_enter.setBounds(261, 584, 235, 20);
		panel_1.add(textField_enter);
		textField_enter.setColumns(10);
		
		JLabel label_21 = new JLabel("\u0420\u0430\u0437\u0440\u0430\u0431\u043E\u0442\u043A\u0430 \u043C\u043E\u0434\u0443\u043B\u0435\u0439 \u0441\u0430\u0439\u0442\u0430");
		label_21.setBounds(521, 14, 175, 14);
		panel_1.add(label_21);
		
		int startX = 521;
		int startY = 42;
		int step1 = 22;
		int step2 = 53;
		int moduleCount[] = {0};
		
		
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener((event)->{
			if(moduleNames.size() == 10){
				return;
			}
			JTextField name = new JTextField();
			name.setBounds(startX, startY + step2*moduleCount[0], 309, 20);
			panel_1.add(name);
			moduleNames.add(name);
			
			JTextField desc = new JTextField();
			desc.setBounds(startX, startY + step2*moduleCount[0] + step1, 309, 20);
			panel_1.add(desc);
			moduleDescs.add(desc);
			
			moduleCount[0]++;
			
			panel_1.repaint();
		});
		btnNewButton_1.setBounds(720, 10, 50, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.addActionListener((event)->{
			if(moduleNames.size() < 1){
				return;
			}
			JTextField name = moduleNames.get(moduleCount[0] - 1);
			panel_1.remove(name);
			moduleNames.remove(moduleCount[0] - 1);
			
			JTextField desc = moduleDescs.get(moduleCount[0] - 1);
			panel_1.remove(desc);
			moduleDescs.remove(moduleCount[0] - 1);
			
			moduleCount[0]--;
			panel_1.repaint();
		});
		btnNewButton_2.setBounds(780, 10, 50, 23);
		panel_1.add(btnNewButton_2);
		
		button_1.addActionListener((e) ->{
			HashMap<String, String> values = new HashMap<>();
			
			values.put("pholdermanagernick", ((Manager)comboBox_Manager2.getSelectedItem()).getNick());
			values.put("pholdermanagername", textField_mngName2.getText());
			values.put("pholdermanagerposition", textField_mngPlace2.getText());
			values.put("pholdermanageremail", textField_mngEmail2.getText());
			values.put("pholdermanagerphone", textField_mngPhone2.getText());
			
			values.put("pholderdeltaaddress", textField_deltaAddress2.getText());
			values.put("pholderdeltaphonenumbers", textField_deltaPhones2.getText());
			values.put("pholderdeltasite", textField_deltaSite2.getText());
			
			values.put("pholdercompanyname", textField_companyName.getText());
			
			values.put("pholderdiscuss", textField_discuss.getText());
			values.put("pholderprototype", textField_prototype.getText());
			values.put("pholderdesign", textField_design.getText());
			
			values.put("pholdercollect", textField_collect.getText());
			values.put("pholdertemplate", textField_template.getText());
			values.put("pholdertemplateconf", textField_templateconf.getText());
			
			values.put("pholderhosting", textField_hosting.getText());
			values.put("pholdercms", textField_cms.getText());
			values.put("pholdertemplateinst", textField_templateinst.getText());

			values.put("pholderfilling", textField_filling.getText());
			values.put("pholdertest", textField_test.getText());
			values.put("pholderenter", textField_enter.getText());

			CreationCommBuilder cb = new CreationCommBuilder(exampleCreationPath, tempXMLpath, ".");
			
			ArrayList<String> modules = new ArrayList<>();
			ArrayList<String> modulePrices= new ArrayList<>();
			for(JTextField t : moduleNames){
				modules.add(t.getText());
			}
			for(JTextField t : moduleDescs){
				modulePrices.add(t.getText());
			}
			
			cb.assemble("." + File.separator + textField_companyName.getText() + " коммерческое.docx", values, modules, modulePrices);
		});
	}
	
	private void showMessage(String message){
		JOptionPane.showMessageDialog(frmCommofferer, message);
	}
}
