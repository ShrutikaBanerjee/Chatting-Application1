package icons;
import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
import java.net.*;
public class Server  implements ActionListener {
    static JTextField textField;
    GradientPanel p1;
   static  Box vertical = Box.createVerticalBox();
   static DataOutputStream dout;
   static JFrame j= new JFrame();
   
    Server() {
        j.setSize(450, 700);
        j.setLocation(200, 25);
        j.setLayout(null);

        p1 = new GradientPanel();
        p1.setSize(480, 670);
        p1.setLocation(0, 60);
        p1.setLayout(null); // Set layout to null for absolute positioning
        j.add(p1);

        JPanel p2 = new JPanel();
        p2.setSize(450, 60);
        p2.setLocation(0, 0);
        p2.setBackground(new Color(239, 230, 240));
        p2.setLayout(null); // Set layout to null for absolute positioning
        j.add(p2);

        // Add the first image (arrowleft.jpg)
        URL imgURL = getClass().getResource("arrowleft.jpg");
        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust size as needed
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel back = new JLabel(scaledIcon);
            back.setBounds(5, 5, 50, 50); // Adjust bounds to position the image at the top left
            p2.add(back);

            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    j.setVisible(false);
                }
            });
        } else {
            System.err.println("Image not found.");
        }

        // Add the second image (phone.png)
        URL imgURL2 = getClass().getResource("phone.png");
        if (imgURL2 != null) {
            ImageIcon originalIcon2 = new ImageIcon(imgURL2);
            Image scaledImage2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust size as needed
            ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
            JLabel phone = new JLabel(scaledIcon2);
            phone.setBounds(340, 5, 50, 50); // Adjust bounds to position the image next to the first one
            p2.add(phone);
        } else {
            System.err.println("Image not found.");
        }

        // Add the third image (video.png)
        URL imgURL3 = getClass().getResource("video.png");
        if (imgURL3 != null) {
            ImageIcon originalIcon3 = new ImageIcon(imgURL3);
            Image scaledImage3 = originalIcon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust size as needed
            ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
            JLabel video = new JLabel(scaledIcon3);
            video.setBounds(280, 5, 50, 50); // Adjust bounds to position the image next to the second one
            p2.add(video);
        } else {
            System.err.println("Image not found.");
        }

        // Add the fourth image (mouse.png)
        URL imgURL4 = getClass().getResource("Mouse.png");
        if (imgURL4 != null) {
            ImageIcon originalIcon4 = new ImageIcon(imgURL4);
            Image scaledImage4 = originalIcon4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust size as needed
            ImageIcon scaledIcon4 = new ImageIcon(scaledImage4);
            JLabel mouse = new JLabel(scaledIcon4);
            mouse.setBounds(60, 5, 50, 50); // Adjust bounds to position the image next to the first one
            p2.add(mouse);
        } else {
            System.err.println("Image not found.");
        }

        // Add the fifth image (3icon.png)
        URL imgURL5 = getClass().getResource("3icon.png");
        if (imgURL5 != null) {
            ImageIcon originalIcon5 = new ImageIcon(imgURL5);
            Image scaledImage5 = originalIcon5.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust size as needed
            ImageIcon scaledIcon5 = new ImageIcon(scaledImage5);
            JLabel threeIcons = new JLabel(scaledIcon5);
            threeIcons.setBounds(390, 5, 50, 50); // Adjust bounds to position the image next to the other ones
            p2.add(threeIcons);
        } else {
            System.err.println("Image not found.");
        }

        // Add the username label in the middle
        JLabel usernameLabel = new JLabel("MICKEY");
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 16));
        usernameLabel.setBounds(140, 5, 200, 50); // Adjust bounds to position the label in the middle
        p2.add(usernameLabel);

        // Add text field and send button at the bottom of p1
        textField = new JTextField();
        textField.setBounds(15, 570, 300, 30); // Adjust size and position as needed
        textField.setBackground(new Color(255, 182, 193));
        p1.add(textField);

        JButton sendButton = new JButton("SEND");
        sendButton.setBounds(350, 570, 80, 30); // Adjust size and position as needed
        sendButton.setBackground(new Color(255, 182, 193));
        sendButton.addActionListener(this);
        p1.add(sendButton);

//        JScrollPane scrollPane = new JScrollPane(vertical);
//        scrollPane.setBounds(0, 60, 450, 500); // Adjust size and position as needed
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        p1.add(scrollPane);

        j.setVisible(true);
    }

    public static void main(String[] args) {
        new Server();
        try {
            ServerSocket skt = new ServerSocket(5004);
            while(true) {
                Socket s = skt.accept();
                
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                
                while(true) {
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    j.validate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	 try {
           
			String out = textField.getText();

             JPanel p2 = formatLabel(out);

             p1.setLayout(new BorderLayout());

             JPanel right = new JPanel(new BorderLayout());
             right.add(p2, BorderLayout.LINE_END);
             vertical.add(right);
             vertical.add(Box.createVerticalStrut(15));

             p1.add(vertical, BorderLayout.PAGE_START);

             dout.writeUTF(out);

             textField.setText("");

            j.repaint();
            j.invalidate();
            j.validate();   
         } catch (Exception ee) {
             ee.printStackTrace();
         }
     }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 100px\">" + out + "</p></html>");
        output.setFont(new Font("SHERIF", Font.PLAIN, 14));
        output.setBackground(new Color(255,204,220));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Define colors
            Color babyPink = new Color(255, 182, 193);
            Color babyBlue = new Color(173, 216, 230);
            Color white = Color.WHITE;

            int width = getWidth();
            int height = getHeight();

            // Create a linear gradient from baby pink to baby blue to white
            GradientPaint gradient = new GradientPaint(0, 0, babyPink, width, height, white);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
        }
    }

