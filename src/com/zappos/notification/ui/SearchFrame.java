/**
 * 
 */
package com.zappos.notification.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.zappos.domain.ui.UIProduct;
import com.zappos.notification.NotificationSearchService;
import com.zappos.notification.NotifierThread;

/**
 * @author satyaswaroop
 * 
 */
public class SearchFrame extends JFrame {
	JTextField prodValue;
	JTextField brandValue;
	JTextField priceValue;
	JTextField origPriceValue;
	JTextField percentValue;
	JTextField productURLValue;
	String[][] data = new String[10][6];
	int count = 0;
	private JTabbedPane tabbedPane;
	JTable table;
	private JPanel topPane;
	private JPanel searchPane;
	private JTextField searchField;
	private JButton searchButton;
	private String query;
	private int totalPages;
	NotificationSearchService service;
	private List<UIProduct> results;
	ImageIcon image;
	private JTextField styleIDValue;
	private JLabel imageLabel;
	private JTextField emailId;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SearchFrame frame = new SearchFrame();
		final Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {

			public void run() {

				NotifierThread thread = new NotifierThread();
				Thread t = new Thread(thread);
				t.start();

			}
		}, (long) 600000, (long) 600000 
		);
		frame.pack();
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Application");
		frame.setVisible(true);
		frame.setSize(750, 500);
	}

	public SearchFrame() {

		setTitle(" Application");
		setSize(750, 300);
		setBackground(Color.gray);
		topPane = new JPanel();
		topPane.setLayout(new FlowLayout());
		this.add(topPane);
		JPanel emailPane = new JPanel(new FlowLayout());
		emailPane.add(new JLabel("EmailID:"));

		emailId = new JTextField(20);
		emailPane.add(emailId);
		topPane.add(emailPane);
		searchField = new JTextField();
		topPane.add(searchField);
		searchField.setEnabled(true);
		searchField.setColumns(40);
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchField.getText() != null
						&& !searchField.getText().equals("")) {
					service = new NotificationSearchService(searchField
							.getText());
					results = service.search();
					totalPages = service.getTotalPages();
					query = searchField.getText();
					updateData();
				}

			}
		});
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (query != null && !query.equals("")) {
					count++;
					if (count >= 10) {
						results = service.getNextPage();
						if(results!=null)
						count = 0;
					}
					if(results!=null)
					updateData();
				}

			}
		});

		JButton prevButton = new JButton("Previous");
		prevButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (query != null && !query.equals("")) {
					if (count >= 0) {
						count--;
						updateData();
					}
				}

			}
		});
		topPane.add(searchButton);
		topPane.add(prevButton);
		topPane.add(nextButton);

		JButton save = (new JButton("save"));
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();

			}
		});
		topPane.add(save);
		// Create the tab pages
		createSearchPanel();

	}

	protected void save() {
		// TODO Auto-generated method stub
		service.save(styleIDValue.getText(), emailId.getText());
	}

	private void createSearchPanel() {

		searchPane = new JPanel(new GridLayout(9, 2, 1, 1));
		JLabel prodlabel = new JLabel("Product Name");
		prodValue = new JTextField(30);
		prodValue.setEditable(false);
		JLabel brandLabel = new JLabel("Brand Name");
		brandValue = new JTextField(30);
		brandValue.setEditable(false);
		JLabel priceLabel = new JLabel("Price");
		priceValue = new JTextField(30);
		priceValue.setEditable(false);
		JLabel origPriceLabel = new JLabel("Original Price");
		origPriceValue = new JTextField(30);
		origPriceValue.setEditable(false);
		JLabel percentLabel = new JLabel("Percentage Off");
		percentValue = new JTextField(30);
		percentValue.setEditable(false);
		JLabel productURL = new JLabel("Product URL");
		productURLValue = new JTextField(30);
		productURLValue.setEditable(false);
		JLabel styleIDLabel = new JLabel("Style ID");
		styleIDValue = new JTextField(30);
		styleIDValue.setEditable(false);
		image = new ImageIcon();
		imageLabel = new JLabel(image);
		searchPane.add(prodlabel);
		searchPane.add(prodValue);
		searchPane.add(brandLabel);
		searchPane.add(brandValue);
		searchPane.add(priceLabel);
		searchPane.add(priceValue);
		searchPane.add(origPriceLabel);
		searchPane.add(origPriceValue);
		searchPane.add(percentLabel);
		searchPane.add(percentValue);
		searchPane.add(productURL);
		searchPane.add(productURLValue);
		searchPane.add(styleIDLabel);
		searchPane.add(styleIDValue);

		topPane.add(searchPane);
		topPane.add(new JPanel(new FlowLayout()).add(imageLabel));

	}

	private void updateData() {
		if(!results.isEmpty()){
		UIProduct p = results.get(count);
		prodValue.setText(p.getProductName());
		brandValue.setText(p.getBrandName());
		priceValue.setText(p.getPrice());
		origPriceValue.setText(p.getOriginalPrice());
		percentValue.setText(p.getPercentOff());
		productURLValue.setText(p.getProductUrl());
		styleIDValue.setText(p.getStyleId());
		try {
			image.setImage(ImageIO.read(new File(p.getImageFile())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
		}
	}

}
