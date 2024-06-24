/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.File;
import com.itextpdf.text.Document;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Font;
import static java.sql.JDBCType.NULL;

/**
 *
 * @author CD
 */
public class payment extends javax.swing.JFrame {
    Connection con;
    /**
     * Creates new form payment
     */
    public payment() {
        setExtendedState(payment.MAXIMIZED_BOTH);
        initComponents();
        fillTable();
        txt_payId.setEditable(false);
        
        Date currentDate = new Date();
        SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss");
        txt_time.setText(timeformat.format(currentDate));
        txt_time.setEditable(false);
    }
    
    public Connection MySqlConnection(){
        Connection conn = null; // creating database connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/itp","root","");
           // JOptionPane.showMessageDialog(null, "MySql DB connection successful...");
            return conn;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "My sql connection fail..");
            return null;
        }
    }
    public ArrayList<Payment2> retrieveData(){
        ArrayList<Payment2> pp = null;
        pp = new ArrayList<Payment2>();
        try {
            Connection conn = MySqlConnection();
            String qry = "select * from payment_info";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
            Payment2 pay2;
            
            while(rs.next()){
                pay2 = new Payment2(rs.getInt(1),rs.getString(2),rs.getString("Date"),rs.getString("Time"),rs.getString("HallRID"),rs.getString("Payment_Type"),
                        Float.parseFloat(rs.getString(7)));
                
                pp.add(pay2);
            }
        } catch (Exception e) {
            System.out.println("Error in retrieveData Method" +e);
        }
        return pp;
    } 
    
    // retriveing data into jTable
    public void fillTable(){
        ArrayList<Payment2> pp = retrieveData();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        for (int i = 0; i < pp.size(); i++) {
            row[0] = pp.get(i).getPaymentid();
            row[1] = pp.get(i).getRoomid();
            row[2] = pp.get(i).getDate();
            row[3] = pp.get(i).getTime();
            row[4] = pp.get(i).getHallid();
            row[5] = pp.get(i).getTxt_type();
            row[6] = pp.get(i).getAmount();
            model.addRow(row);
        }
        
    }
    
    // show item in search
    public void showItemToFields(int index){
        txt_payId.setText(Integer.toString(retrieveData().get(index).getPaymentid()));
        txt_roomId.setText(retrieveData().get(index).getRoomid());
        //txt_date.setText(retrieveData().get(index).getDate());
        txt_time.setText(retrieveData().get(index).getTime());
        txt_hallId.setText(retrieveData().get(index).getHallid());
       // txt_payment1.setText();
        txt_final.setText(Float.toString(retrieveData().get(index).getAmount()));
        
            String gen= retrieveData().get(index).getTxt_type();
    
    if(gen.equals("cash")){
            radioCash.setSelected(true);
    }
    
      if(gen.equals("card")){
            radioCard.setSelected(true);
    }
    
        
        try {
            java.util.Date dob = null;
            dob = new SimpleDateFormat("yyyy-MM-dd").parse((String)retrieveData().get(index).getDate());            
            txt_date.setDate(dob);
        } catch (Exception e) {
            System.out.println("Error at showItemToFields method" + e);
        }
                
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_paymentType = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_roomId = new javax.swing.JTextField();
        txt_payId = new javax.swing.JTextField();
        txt_hallId = new javax.swing.JTextField();
        txt_final = new javax.swing.JTextField();
        txt_time = new javax.swing.JTextField();
        txt_date = new com.toedter.calendar.JDateChooser();
        btn_Add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        radioCash = new javax.swing.JRadioButton();
        radioCard = new javax.swing.JRadioButton();
        j2 = new javax.swing.JLabel();
        j3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btn_clock = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        ddate = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        year = new com.toedter.calendar.JYearChooser();
        month = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setAutoscrolls(true);

        b1.setBackground(new java.awt.Color(0, 102, 102));
        b1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setText("Employees");
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b1MouseClicked(evt);
            }
        });
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setBackground(new java.awt.Color(0, 102, 102));
        b2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b2.setForeground(new java.awt.Color(255, 255, 255));
        b2.setText("Hall Reservation");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setBackground(new java.awt.Color(0, 102, 102));
        b3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b3.setForeground(new java.awt.Color(255, 255, 255));
        b3.setText("Room Reservation");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b4.setBackground(new java.awt.Color(0, 102, 102));
        b4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b4.setForeground(new java.awt.Color(255, 255, 255));
        b4.setText("Menus");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b5.setBackground(new java.awt.Color(0, 102, 102));
        b5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b5.setForeground(new java.awt.Color(255, 255, 255));
        b5.setText("Payment");
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b6.setBackground(new java.awt.Color(0, 102, 102));
        b6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b6.setForeground(new java.awt.Color(255, 255, 255));
        b6.setText("Inventory");
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        b7.setBackground(new java.awt.Color(0, 102, 102));
        b7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b7.setForeground(new java.awt.Color(255, 255, 255));
        b7.setText("Orders");
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        b8.setBackground(new java.awt.Color(0, 102, 102));
        b8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b8.setForeground(new java.awt.Color(255, 255, 255));
        b8.setText("Travel Package");
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });

        b9.setBackground(new java.awt.Color(0, 102, 102));
        b9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b9.setForeground(new java.awt.Color(255, 255, 255));
        b9.setText("Expenses");
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });

        b10.setBackground(new java.awt.Color(0, 102, 102));
        b10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b10.setForeground(new java.awt.Color(255, 255, 255));
        b10.setText("Home");
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b3)
                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 0, 51));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 75)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("HOTEL ARONWAY");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Payment Info");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Payment ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Room Reservation ID:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Date:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Time:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Hall Reservation ID:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Payment Type:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("Final Amount:");

        txt_roomId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_roomIdActionPerformed(evt);
            }
        });
        txt_roomId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_roomIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_roomIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_roomIdKeyTyped(evt);
            }
        });

        txt_payId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_payIdActionPerformed(evt);
            }
        });

        txt_hallId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hallIdActionPerformed(evt);
            }
        });
        txt_hallId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_hallIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hallIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hallIdKeyTyped(evt);
            }
        });

        txt_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_finalActionPerformed(evt);
            }
        });

        txt_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timeActionPerformed(evt);
            }
        });

        txt_date.setDateFormatString("yyyy-MM-dd");

        btn_Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/add.png"))); // NOI18N
        btn_Add.setText("Add");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/update.png"))); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/delete.png"))); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        txt_paymentType.add(radioCash);
        radioCash.setText("Cash");
        radioCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCashActionPerformed(evt);
            }
        });

        txt_paymentType.add(radioCard);
        radioCard.setText("Card");
        radioCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCardActionPerformed(evt);
            }
        });

        j2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j2.setForeground(new java.awt.Color(204, 0, 0));

        j3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j3.setForeground(new java.awt.Color(204, 0, 0));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/clear.png"))); // NOI18N
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_clock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/clock.png"))); // NOI18N
        btn_clock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioCash)
                                    .addComponent(radioCard)
                                    .addComponent(txt_final, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_roomId)
                                            .addComponent(txt_payId)
                                            .addComponent(txt_date, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                            .addComponent(txt_time)
                                            .addComponent(txt_hallId)
                                            .addComponent(j2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(j3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_clock, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_update))
                                .addGap(33, 33, 33)
                                .addComponent(btn_delete)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_payId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_roomId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_clock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_hallId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(radioCash)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioCard)
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_final, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_delete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Add)
                        .addComponent(btn_update)))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payment ID", "Room ID", "Date", "Time", "Hall ID", "Payment Type", "Final Amount"
            }
        ));
        jTable1.setRowHeight(25);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/search.png"))); // NOI18N
        jLabel9.setText("Search By Date");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-business-report-32.png"))); // NOI18N
        jButton2.setText("Get Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ddate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ddateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ddateKeyReleased(evt);
            }
        });

        jButton3.setText("GO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        year.setFocusable(false);
        year.setMinimumSize(new java.awt.Dimension(49, 26));

        month.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Delete History");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(333, 333, 333)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ddate, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(96, 96, 96))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_payIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_payIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_payIdActionPerformed

    private void txt_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_finalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_finalActionPerformed

    //for updating...
    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
             txt_payId.setEditable(false);
             txt_date.setEnabled(false);
             txt_hallId.setEnabled(false);
             txt_time.setEnabled(false);
             txt_roomId.setEnabled(false);
             
         try{
                String qry = null;
                PreparedStatement ps = null;
                Connection conn = MySqlConnection();
                qry = "update payment_info set RoomRID = ? ,Date = ?,Time = ?,HallRID = ?,Payment_Type = ?,Final_Amount = ? where PaymentID = ?"; // inserting values into db
                 
                ps=conn.prepareStatement(qry);
                
                ps.setString(1,txt_roomId.getText());
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); // getting date
                 String addDate = dateformat.format(txt_date.getDate());
                ps.setString(2,addDate);
                ps.setString(3,txt_time.getText());
                ps.setString(4,txt_hallId.getText());
                //getting data from radio button 
                if(radioCard.isSelected()){
            
                    txt_payment1 = "card";
                }
                else if(radioCash.isSelected()){

                        txt_payment1 ="cash";
                }
                else{
                txt_payment1="";
                }
                ps.setString(5,txt_payment1);
                ps.setFloat(6,Float.parseFloat(txt_final.getText()));
                ps.setInt(7,Integer.parseInt(txt_payId.getText()));
                int res= ps.executeUpdate();
                
                if(res>=1){
                
                  JOptionPane.showMessageDialog(null,"Data Updated");
                }
                else{
                
                      JOptionPane.showMessageDialog(null,"Not Updated ");
                }
                
    
        }
        catch (SQLException ex) {
            
              JOptionPane.showMessageDialog(this, ex);
        }    
                fillTable();
                txt_payId.setText("");
                txt_roomId.setText("");
                txt_date.setCalendar(null);
             //   txt_time.setText("");
                txt_hallId.setText("");
                txt_paymentType.clearSelection();
                txt_final.setText("");      
    }//GEN-LAST:event_btn_updateActionPerformed

    //deleting an entry from data base...
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if(radioCard.isSelected()){
            txt_payment1 = "card";
        }
        else if(radioCash.isSelected()){
            txt_payment1 ="cash";
        }
        else{
            txt_payment1="";
        }
        txt_payId.setEditable(false);
        if(txt_payId.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ID feiled is empty");
        }
        else{  
            int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Delete" ,"Delete",JOptionPane.YES_NO_OPTION);         
            if(opt==0){
                try {
                    Connection conn = MySqlConnection();
                    String qry2 = "insert into payment_deleted"+"(PaymentID,RoomRID,Date,Time,HallRID,Payment_Type,Final_Amount) values(?,?,?,?,?,?,?)"; // inserting values into db  
                    PreparedStatement ps2=conn.prepareStatement(qry2);
                    int a = Integer.parseInt(txt_payId.getText());
                    ps2.setInt(1,a);
                    ps2.setString(2,txt_roomId.getText());
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); // getting date
                    String addDate = dateformat.format(txt_date.getDate());
                    ps2.setString(3,addDate);
                    ps2.setString(4,txt_time.getText());
                    ps2.setString(5,txt_hallId.getText());
                    //getting data from radio button 
                    ps2.setString(6,txt_payment1);
                    ps2.setFloat(7,Float.parseFloat(txt_final.getText()));
                    int res= ps2.executeUpdate();
                
                
                    String qry = "delete from payment_info where PaymentID = ? ";
                    PreparedStatement ps = conn.prepareStatement(qry);
                    ps.setInt(1, Integer.parseInt(txt_payId.getText().toString()));
                    int res2 = ps.executeUpdate();

                    if( res2 >= 1){
                        JOptionPane.showMessageDialog(null, "Entry deleted successfuly");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Entry deleted not successful"); 

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                }
            fillTable();
                txt_payId.setText("");
                txt_roomId.setText("");
                txt_date.setCalendar(null);
               // txt_time.setText("");
                txt_hallId.setText("");
                txt_paymentType.clearSelection();
                txt_final.setText("");

        }
                
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void radioCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCashActionPerformed

    private void radioCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCardActionPerformed
private String txt_payment1;
    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        
        if(radioCard.isSelected()){
            txt_payment1 = "card";
        }
        else if(radioCash.isSelected()){
            txt_payment1 ="cash";
        }
        else{
            txt_payment1="";
        }
        try {
            if(txt_roomId.getText().toString().equals("")){
             JOptionPane.showMessageDialog(null,"Enter the Room Reservation ID !!!");
        }
        if(txt_hallId.getText().toString().equals("")){
             JOptionPane.showMessageDialog(null,"Enter the Hall Reservation ID !!!");
        }
        if(txt_final.getText().toString().equals("")){
             JOptionPane.showMessageDialog(null,"Final Amount Cannot be Empty!!!");
        }
        if(txt_payment1.equals("")){
             JOptionPane.showMessageDialog(null,"Select the Payment Method !!!");
        }
        if(txt_date.equals("")){
             JOptionPane.showMessageDialog(null,"Choose the Date ");
        }
        
        else{
                try{
           Connection conn = MySqlConnection(); // creating db connection
           String qry = "insert into payment_info"+"(PaymentID,RoomRID,Date,Time,HallRID,Payment_Type,Final_Amount) values(?,?,?,?,?,?,?)"; // inserting values into db
  
                PreparedStatement ps=conn.prepareStatement(qry);
                ps.setInt(1,0);

                ps.setString(2,txt_roomId.getText());
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); // getting date
                String addDate = dateformat.format(txt_date.getDate());
                ps.setString(3,addDate);
                ps.setString(4,txt_time.getText());
                ps.setString(5,txt_hallId.getText());
                //getting data from radio button 
                ps.setString(6,txt_payment1);
                ps.setFloat(7,Float.parseFloat(txt_final.getText()));
                int res= ps.executeUpdate();
                
                if(res>=1){
                
                  JOptionPane.showMessageDialog(null,"Data inserted");
                }
                else{
                
                      JOptionPane.showMessageDialog(null,"not inserted ");
                }
                fillTable();
                txt_payId.setText("");
                txt_roomId.setText("");
                txt_date.setCalendar(null);
               // txt_time.setText("");
                txt_hallId.setText("");
                txt_paymentType.clearSelection();
                txt_final.setText("");
                
                txt_roomId.setEditable(true);
                txt_hallId.setEditable(true);
               
                         
        }
        catch (SQLException ex) {
            
              JOptionPane.showMessageDialog(this, ex);
        }
       } 
        } catch (Exception e) {
            
        }
             
    }//GEN-LAST:event_btn_AddActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //txt_date.setEnabled(false);
        txt_hallId.setEnabled(false);
       // txt_time.setEnabled(false);
        txt_roomId.setEnabled(false);
        int ind = jTable1.getSelectedRow();
        showItemToFields(ind);
        btn_Add.setEnabled(false);
      //  btn_clock.setEnabled(false);// disable adding button
        
        if(Integer.parseInt(txt_hallId.getText()) == 0){
                    txt_roomId.setEnabled(true);
        }
        if(Integer.parseInt(txt_roomId.getText()) == 0){
                    txt_hallId.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void txt_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timeActionPerformed

    private void b1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b1MouseClicked

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        NewJFrame i = new NewJFrame();
        i.setVisible(true);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        dispose();
        HallReservation h = new HallReservation();
        h.setVisible(true);
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
          dispose();
          availablerooms r = new availablerooms();
          r.setVisible(true);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        dispose();
        AllMenu a = new AllMenu();
        a.setVisible(true);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
       dispose();
       payment p = new payment();
       p.setVisible(true);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        dispose();
        ingridient i = new  ingridient();
        i.setVisible(true);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        dispose();
        order o = new order();
        o.setVisible(true);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        dispose();
        travelInterface t = new travelInterface();
        t.setVisible(true);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        new mainPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_b9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txt_date.setEnabled(true);
        txt_hallId.setEnabled(true);
        txt_time.setEnabled(true);
        txt_roomId.setEnabled(true);
        txt_payId.setText("");
        txt_roomId.setText("");
        txt_date.setCalendar(null);
        btn_clock.setEnabled(true);
        //txt_time.setText("");
        txt_hallId.setText("");
        txt_paymentType.clearSelection();
        txt_final.setText("");
        btn_Add.setEnabled(true);        
        txt_roomId.setEditable(true);
        txt_hallId.setEditable(true);
        j3.setText("");
        j2.setText("");
        fillTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_clockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clockActionPerformed
        // TODO add your handling code here:
        
        Date currentDate = new Date();
       // System.out.println(currentDate);
        SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss");
        txt_time.setText(timeformat.format(currentDate));
        txt_time.setEditable(false);
    }//GEN-LAST:event_btn_clockActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        // TODO add your handling code here:
         dispose();
         Home h = new Home();
         h.setVisible(true);
    }//GEN-LAST:event_b10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Float amountTravel, amountRoom , amountHall, total = 0f;
        int id = 0;
        Connection con = MySqlConnection();
        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File("PaymentReport.pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if(dialogResult == JFileChooser.APPROVE_OPTION){
        String filePath = dialog.getSelectedFile().getPath();
        int yearUse = year.getYear();
        String monthUse = (String) month.getSelectedItem();
        
            try {
                try {
                    String qry = "select sum(Final_Amount) from  payment_info where Date like '" + yearUse + "-" + monthUse + "%' and  RoomRID =0";
                    Statement st = con.createStatement();
                    ResultSet rs =st.executeQuery(qry);
                    rs.next() ;
                    amountHall = Float.parseFloat(rs.getString("sum(Final_Amount)"));
                    System.out.println("Hall "+amountHall);
                } catch (Exception e) {
                    amountHall = 0f;
                }
                try {
                    String qry3 = "select sum(amount) from  trav_pack_handle where startDate like '" + yearUse + "-" + monthUse + "%'";
                    Statement st3 = con.createStatement();
                    ResultSet rs3 =st3.executeQuery(qry3);
                    rs3.next();
                    amountTravel = Float.parseFloat(rs3.getString("sum(amount)"));
                    System.out.println("Travel " +amountTravel);
                } catch (Exception e) {
                    amountTravel = 0f;
                }
                try {
                    String qry2 = "select sum(Final_Amount) from  payment_info where Date like '" + yearUse + "-" + monthUse + "%' and  HallRID =0";
                    Statement st2 = con.createStatement();
                    ResultSet rs2 =st2.executeQuery(qry2);
                    rs2.next() ;
                    amountRoom = Float.parseFloat(rs2.getString("sum(Final_Amount)")) - amountTravel;
                    System.out.println("Room "+amountRoom);
                } catch (Exception e) {
                    amountRoom = 0f;
                }
                
                Document myDocument = new Document();
                PdfWriter mywriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();
                
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("header.jpeg");
                image.scalePercent(40);
                myDocument.add(image);
                
                myDocument.add(new Paragraph());
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------\n"));
                myDocument.add(new Paragraph("                     MONTHLY PAYMENT REPORT\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
              //  myDocument.add(new Paragraph("Hello",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD)));

                // myDocument.add(new Paragraph("=========================================================================="));
                myDocument.add(new Paragraph("YEAR: " + yearUse+ "      " + "MONTH : " + monthUse + "\n\n\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));

                PdfPTable table = new PdfPTable(2);

                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.getDefaultCell().setFixedHeight(25);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Income Category  ", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                PdfPCell cell2 = new PdfPCell(new Paragraph("Amount  ", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                total = amountHall+amountRoom+amountTravel; // final calculation
                PdfPCell cell3 = new PdfPCell(new Paragraph("Room Reservation ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                PdfPCell cell4 = new PdfPCell(new Paragraph("Rs. "+amountRoom+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                PdfPCell cell5 = new PdfPCell(new Paragraph("Hall Reservation ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                PdfPCell cell6 = new PdfPCell(new Paragraph("Rs. "+amountHall+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                PdfPCell cell7 = new PdfPCell(new Paragraph("Travel Packages ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                PdfPCell cell8 = new PdfPCell(new Paragraph("Rs. "+amountTravel+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                PdfPCell cell9 = new PdfPCell(new Paragraph("Total Income ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
                PdfPCell cel20 = new PdfPCell(new Paragraph("Rs. "+total+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD,BaseColor.RED)));
                
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel20.setHorizontalAlignment(Element.ALIGN_CENTER);

                cell1.setFixedHeight(30);
                cell3.setFixedHeight(25);
                cell5.setFixedHeight(25);
                cell7.setFixedHeight(25);
                cell9.setFixedHeight(25);
                
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
                table.addCell(cell9);
                table.addCell(cel20);
                

                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel20.setHorizontalAlignment(Element.ALIGN_CENTER);
                    
                myDocument.add(table);
                
                float max = 0;
                String type = null;
                if(amountRoom > amountHall && amountRoom > amountTravel){
                    max = amountRoom ;
                    type = "Room Reservations";                 
                }else if(amountHall > amountRoom && amountHall > amountTravel){
                    max = amountHall;
                    type = "Hall Reservations";
                }else if(amountHall == amountRoom){
                    max = amountHall;
                    type = "Hall resrvations and Room Reservations";
                }else if(amountRoom == amountTravel){
                    max = amountTravel;
                    type = "Room Reservations and Travel Packeages";
                }else if(amountTravel == amountHall){
                    max = amountHall;
                    type = "Travel packeages and Hall Reservations";
                }else{
                    max = amountTravel;
                    type = "Travel Packages";
                }
                myDocument.add(new Paragraph("\n\n\nMaximum income of this month come/s from " + type +"\n", FontFactory.getFont(FontFactory.HELVETICA, 12,BaseColor.RED)));
                myDocument.add(new Paragraph("Amount is Rs:" + max +"\n", FontFactory.getFont(FontFactory.HELVETICA, 12,BaseColor.RED)));
                myDocument.close();
                JOptionPane.showMessageDialog(null, "Report was successfully generated !!!");
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
             
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ddateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ddateKeyReleased
        
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDate.format(ddate.getDate());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(date.trim()));
        
    }//GEN-LAST:event_ddateKeyReleased

    private void ddateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ddateKeyPressed
        // TODO add your handling code here:
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDate.format(ddate.getDate());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(date.trim()));
    }//GEN-LAST:event_ddateKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        try {
            if(ddate.equals("")){
             // fillTable();
           // SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
          //  String date = sDate.format(ddate.getDate());
            String date = "2020";
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
            jTable1.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(date));
            
        }else{            
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDate.format(ddate.getDate());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(date.trim()));
        
        }
        } catch (Exception e) {
            fillTable();
        }
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthActionPerformed

    private void txt_hallIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hallIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hallIdKeyTyped

    private void txt_hallIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hallIdKeyReleased
        try{
            String id= "0";
            txt_roomId.setText(id);
            txt_roomId.setEditable(false);

            String  PATTERN = "^[0-9]{0,10}$";
            Pattern patt = Pattern.compile(PATTERN);
            Matcher match = patt.matcher(txt_hallId.getText());

            if(!match.matches()){
                j3.setText("Numeric Values Only");
            }
            else{
                j3.setText(null);
            }

            int hod = Integer.parseInt(txt_hallId.getText());
            Connection con = MySqlConnection();
            int amount = 0;

            try {
                try {
                    String qry = "select * from  reservation where reservation_ID like '"+hod+"'";
                    Statement st = con.createStatement();
                    ResultSet rs =st.executeQuery(qry);
                    rs.next() ;
                    amount = Integer.parseInt(rs.getString("total_amount"));
                } catch (MySQLIntegrityConstraintViolationException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid Hall Reservaion ID !!!");
                }

            } catch (Exception e) {
                amount = 0;
                JOptionPane.showMessageDialog(null, "Invalid Hall Reservaion ID !!!");
            }
            txt_final.setText(Integer.toString(amount));

        }
        catch(NullPointerException ex){

            JOptionPane.showMessageDialog(null, "Empty value !!!");
        }
    }//GEN-LAST:event_txt_hallIdKeyReleased

    private void txt_hallIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hallIdKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_hallIdKeyPressed

    private void txt_hallIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hallIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hallIdActionPerformed

    private void txt_roomIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_roomIdKeyTyped

    }//GEN-LAST:event_txt_roomIdKeyTyped

    private void txt_roomIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_roomIdKeyReleased
        // TODO add your handling code here:
        float amount, amount2, amount3;
        try{
            String  PATTERN = "^[0-9]{0,10}$";
            Pattern patt = Pattern.compile(PATTERN);
            Matcher match = patt.matcher(txt_roomId.getText());

            if(!match.matches()){
                j2.setText("Numeric Values Only");
            }
            else{
                j2.setText(null);
            }

            if(txt_roomId.getText()!=null){

                String id= "0";
                txt_hallId.setText(id);
                txt_hallId.setEditable(false);

                int rod = Integer.parseInt(txt_roomId.getText());
                Connection con = MySqlConnection();

                try {
                    String qry = "select sum(amount) from  trav_pack_handle where RRID like '"+rod+"'";
                    Statement st = con.createStatement();
                    ResultSet rs =st.executeQuery(qry);
                    rs.next() ;
                    amount = 0;
                    amount = Float.parseFloat(rs.getString("sum(amount)"));
                } catch (Exception e) {
                    amount = 0;
                }
                try {
                    String qry2 = "select Amount from  roomreservation where RRID like '"+rod+"'";
                    Statement st2 = con.createStatement();
                    ResultSet rs2 =st2.executeQuery(qry2);
                    rs2.next();
                    amount2 = Float.parseFloat(rs2.getString("Amount"));
                } catch (Exception e) {
                    amount2 = 0;
                }
                try {
                    String qry3 = "select sum(Price) from  roommenu rm,menus m, roomreservation r where rm.RRID  = r.RRID  and rm.MenuId =m.MenuID and r.RRID like '"+rod+"'" ;
                    Statement st3 = con.createStatement();
                    ResultSet rs3 =st3.executeQuery(qry3);
                    rs3.next();
                    amount3 = 0;
                    amount3 = Float.parseFloat(rs3.getString("sum(Price)"));
                } catch (Exception e) {
                    amount3 =0 ;
                }

                // System.out.println(amount3);

                float finalA = amount + amount2 + amount3;
                /* try {
                    if(finalA == 0){
                        JOptionPane.showMessageDialog(null,"Invalid Room Reservation ID !!!");
                    }
                } catch (Exception e) {
                }*/

                txt_final.setText(Float.toString(finalA));
                System.out.println(finalA);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_txt_roomIdKeyReleased

    private void txt_roomIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_roomIdKeyPressed

    }//GEN-LAST:event_txt_roomIdKeyPressed

    private void txt_roomIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_roomIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_roomIdActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
        paymentDelete pd = new paymentDelete();
        pd.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new payment().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_clock;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_update;
    private com.toedter.calendar.JDateChooser ddate;
    private javax.swing.JLabel j2;
    private javax.swing.JLabel j3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JRadioButton radioCard;
    private javax.swing.JRadioButton radioCash;
    private com.toedter.calendar.JDateChooser txt_date;
    private javax.swing.JTextField txt_final;
    private javax.swing.JTextField txt_hallId;
    private javax.swing.JTextField txt_payId;
    private javax.swing.ButtonGroup txt_paymentType;
    private javax.swing.JTextField txt_roomId;
    private javax.swing.JTextField txt_time;
    private com.toedter.calendar.JYearChooser year;
    // End of variables declaration//GEN-END:variables
}



