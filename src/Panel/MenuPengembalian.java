package Panel;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Config.Koneksi;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import main.FormLogin;

/**
 *
 * @author Rifal
 */
public class MenuPengembalian extends javax.swing.JPanel {

    private DefaultTableModel keranjangTableModel;
    private int halamanSaatIni = 1;
    private int dataPerHalaman = 20;
    private int totalHalaman;
    private int totalPages;
    private String idKategori;
    private String idPenerbit;
    private String loggedInUsername; 
private FormLogin formLogin;

    public MenuPengembalian() {
        initComponents();
         formLogin = new FormLogin();
       loggedInUsername = FormLogin.GlobalVariables.loggedInUsername;
        formLogin.setLoggedInUsername(loggedInUsername);
        txtIDPenjaga.setText(loggedInUsername);
  String namaPenjaga = formLogin.getNamaPenjaga();
        txtNamaJaga.setText(namaPenjaga);
  txtIDKembali.setText(setIDKembali());
        Color headerColor = new Color(127, 140, 117);
        txtIDPinjam.setEditable(false);
        txtTglDikembalikan.setEditable(false);
        txtIDPenjaga.setEditable(false);
        txtIDPinjam.setEditable(false);
        txtNISN.setEditable(false);
        txtNamaJaga.setEditable(false);
        txtNamaSantri.setEditable(false);
        txtTglKembali.setEditable(false);
        txtTglPinjam.setEditable(false);
        txtUnit.setEditable(false);
        txtDetail.setEditable(false);
        txtSubDenda.setEditable(false);
        txtIDKembali.setEditable(false);
        txtDetail.setVisible(false);
        jLabel30.setVisible(false);

        KeranjangTableModel();

        txtIDPinjam.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateKodeDetail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateKodeDetail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        txtUnit.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateKodeDetail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateKodeDetail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               
            }
        });

        DocumentListener dateDocumentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateFine();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateFine();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
             
            }
        };

        txtTglPinjam.getDocument().addDocumentListener(dateDocumentListener);
        txtTglKembali.getDocument().addDocumentListener(dateDocumentListener);
        txtTglDikembalikan.getDocument().addDocumentListener(dateDocumentListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        PanelMain = new javax.swing.JPanel();
        PanelAdd = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnMengembalikan = new rojerusan.RSMaterialButtonRectangle();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        txtIDPinjam = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTglPinjam = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTglKembali = new javax.swing.JTextField();
        btnPilihPinjam = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtTglDikembalikan = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtIDPenjaga = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtNISN = new javax.swing.JTextField();
        txtNamaJaga = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtNamaSantri = new javax.swing.JTextField();
        txtUnit = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKeranjang = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        btnOtherUnit = new javax.swing.JButton();
        lbDendaHari = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtSubDenda = new javax.swing.JTextField();
        lbTotalDenda = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtIDKembali = new javax.swing.JTextField();
        btnHilang = new javax.swing.JButton();
        btn_addKeranjang = new keeptoo.KButton();
        btn_hapusKeranjang = new keeptoo.KButton();
        txtDetail = new javax.swing.JTextField();

        dateChooser1.setTextRefernce(txtTglDikembalikan);

        setLayout(new java.awt.CardLayout());

        PanelMain.setBackground(new java.awt.Color(255, 255, 255));
        PanelMain.setPreferredSize(new java.awt.Dimension(950, 590));
        PanelMain.setLayout(new java.awt.CardLayout());

        PanelAdd.setBackground(new java.awt.Color(255, 255, 255));
        PanelAdd.setForeground(new java.awt.Color(80, 141, 105));
        PanelAdd.setPreferredSize(new java.awt.Dimension(950, 590));
        PanelAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel13.setText("Input Pengembalian");
        PanelAdd.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, -1, -1));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/pngwing.com (44)_1.png"))); // NOI18N
        PanelAdd.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 120, -1));

        btnMengembalikan.setBackground(new java.awt.Color(80, 141, 105));
        btnMengembalikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/13.png"))); // NOI18N
        btnMengembalikan.setText("Kembalikan");
        btnMengembalikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMengembalikanActionPerformed(evt);
            }
        });
        PanelAdd.add(btnMengembalikan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 130, 45));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pinjam.png"))); // NOI18N
        PanelAdd.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, -1, 24));

        kGradientPanel1.setkEndColor(new java.awt.Color(243, 238, 234));
        kGradientPanel1.setkStartColor(new java.awt.Color(235, 227, 213));

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel15.setText("ID Peminjaman");

        txtIDPinjam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtIDPinjam.setForeground(new java.awt.Color(102, 102, 102));
        txtIDPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDPinjamActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel17.setText("Tgl Pinjam");

        txtTglPinjam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTglPinjam.setForeground(new java.awt.Color(102, 102, 102));
        txtTglPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglPinjamActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel18.setText("Tgl Tenggat");

        txtTglKembali.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTglKembali.setForeground(new java.awt.Color(102, 102, 102));
        txtTglKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglKembaliActionPerformed(evt);
            }
        });

        btnPilihPinjam.setBackground(new java.awt.Color(172, 196, 140));
        btnPilihPinjam.setText("Pilih");
        btnPilihPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihPinjamActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel32.setText("Tgl Pengembalian");

        txtTglDikembalikan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTglDikembalikan.setForeground(new java.awt.Color(102, 102, 102));
        txtTglDikembalikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglDikembalikanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTglDikembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIDPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPilihPinjam)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtTglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtTglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPilihPinjam)
                    .addComponent(txtIDPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtTglDikembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addContainerGap())
        );

        PanelAdd.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 1260, 52));

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel19.setText("Tambah Pengembalian");
        PanelAdd.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 54, -1, -1));

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel21.setText("ID Penjaga");
        PanelAdd.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        txtIDPenjaga.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtIDPenjaga.setForeground(new java.awt.Color(102, 102, 102));
        txtIDPenjaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDPenjagaActionPerformed(evt);
            }
        });
        PanelAdd.add(txtIDPenjaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 230, -1));

        jLabel22.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel22.setText("NISN");
        PanelAdd.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        txtNISN.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNISN.setForeground(new java.awt.Color(102, 102, 102));
        txtNISN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNISNActionPerformed(evt);
            }
        });
        PanelAdd.add(txtNISN, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 230, -1));

        txtNamaJaga.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNamaJaga.setForeground(new java.awt.Color(102, 102, 102));
        txtNamaJaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaJagaActionPerformed(evt);
            }
        });
        PanelAdd.add(txtNamaJaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 230, -1));

        jLabel23.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel23.setText("Nama Penjaga");
        PanelAdd.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        jLabel24.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel24.setText("Nama Santri");
        PanelAdd.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        txtNamaSantri.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNamaSantri.setForeground(new java.awt.Color(102, 102, 102));
        txtNamaSantri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaSantriActionPerformed(evt);
            }
        });
        PanelAdd.add(txtNamaSantri, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 230, -1));

        txtUnit.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtUnit.setForeground(new java.awt.Color(102, 102, 102));
        txtUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitActionPerformed(evt);
            }
        });
        PanelAdd.add(txtUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 290, -1));

        jLabel29.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel29.setText("ID Buku");
        PanelAdd.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, -1, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Keranjang Pengembalian"));

        tblKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblKeranjang);

        PanelAdd.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 1250, 190));

        jLabel30.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel30.setText("ID Detail");
        PanelAdd.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, -1, -1));

        btnOtherUnit.setBackground(new java.awt.Color(172, 196, 140));
        btnOtherUnit.setText("...");
        btnOtherUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtherUnitActionPerformed(evt);
            }
        });
        PanelAdd.add(btnOtherUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 250, -1, -1));

        lbDendaHari.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lbDendaHari.setText("5000");
        PanelAdd.add(lbDendaHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 270, 70, -1));

        jLabel34.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel34.setText("Total Denda");
        PanelAdd.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 340, -1, -1));

        jLabel35.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel35.setText("SubTotal Denda");
        PanelAdd.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, -1, -1));

        txtSubDenda.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtSubDenda.setForeground(new java.awt.Color(102, 102, 102));
        txtSubDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubDendaActionPerformed(evt);
            }
        });
        PanelAdd.add(txtSubDenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, 250, -1));

        lbTotalDenda.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lbTotalDenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalDenda.setText("0");
        PanelAdd.add(lbTotalDenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(1104, 380, 120, -1));

        jLabel37.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel37.setText("Denda/Hari");
        PanelAdd.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 240, -1, -1));

        jLabel25.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel25.setText("ID Pengembalian");
        PanelAdd.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        txtIDKembali.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtIDKembali.setForeground(new java.awt.Color(102, 102, 102));
        txtIDKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDKembaliActionPerformed(evt);
            }
        });
        PanelAdd.add(txtIDKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 230, -1));

        btnHilang.setBackground(new java.awt.Color(255, 102, 102));
        btnHilang.setText("Hilang");
        btnHilang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHilangActionPerformed(evt);
            }
        });
        PanelAdd.add(btnHilang, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, 70, -1));

        btn_addKeranjang.setText("Add");
        btn_addKeranjang.setkBackGroundColor(new java.awt.Color(51, 0, 204));
        btn_addKeranjang.setkBorderRadius(20);
        btn_addKeranjang.setkEndColor(new java.awt.Color(102, 51, 255));
        btn_addKeranjang.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        btn_addKeranjang.setkHoverForeGround(new java.awt.Color(153, 153, 255));
        btn_addKeranjang.setkHoverStartColor(new java.awt.Color(102, 102, 255));
        btn_addKeranjang.setkSelectedColor(new java.awt.Color(255, 153, 255));
        btn_addKeranjang.setkStartColor(new java.awt.Color(0, 204, 204));
        btn_addKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addKeranjangActionPerformed(evt);
            }
        });
        PanelAdd.add(btn_addKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 100, 30));

        btn_hapusKeranjang.setText("Hapus");
        btn_hapusKeranjang.setkBackGroundColor(new java.awt.Color(51, 0, 204));
        btn_hapusKeranjang.setkBorderRadius(20);
        btn_hapusKeranjang.setkEndColor(new java.awt.Color(102, 51, 255));
        btn_hapusKeranjang.setkHoverEndColor(new java.awt.Color(153, 102, 255));
        btn_hapusKeranjang.setkHoverForeGround(new java.awt.Color(153, 153, 255));
        btn_hapusKeranjang.setkHoverStartColor(new java.awt.Color(102, 102, 255));
        btn_hapusKeranjang.setkSelectedColor(new java.awt.Color(255, 153, 255));
        btn_hapusKeranjang.setkStartColor(new java.awt.Color(255, 51, 51));
        btn_hapusKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusKeranjangActionPerformed(evt);
            }
        });
        PanelAdd.add(btn_hapusKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 100, 30));

        txtDetail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtDetail.setForeground(new java.awt.Color(102, 102, 102));
        txtDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDetailActionPerformed(evt);
            }
        });
        PanelAdd.add(txtDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 350, 330, -1));

        PanelMain.add(PanelAdd, "card2");

        add(PanelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDPinjamActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIDPinjamActionPerformed

    private void btnMengembalikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMengembalikanActionPerformed
        Connection conn = null;
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false);

            String idPengembalian = txtIDKembali.getText();
            String idPeminjaman = txtIDPinjam.getText();
            String tglDikembalikanStr = txtTglDikembalikan.getText();

        if (tglDikembalikanStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tanggal dikembalikan harus diisi!");
            return;
        }

       
        if (!isValidDateOrder(txtTglPinjam.getText(), tglDikembalikanStr)) {
            JOptionPane.showMessageDialog(this, "Tanggal dikembalikan tidak valid. Harus setelah tanggal pinjam.");
            return;
        }
            
            if (isDetailPeminjamanCountValid(conn, idPeminjaman)) {

                String pengembalianSql = "INSERT INTO pengembalian (ID_Pengembalian, ID_Peminjaman, ID_Penjaga, NISN, Tanggal_Dikembalikan, Total_Denda) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statementPengembalian = conn.prepareStatement(pengembalianSql)) {
                    statementPengembalian.setString(1, idPengembalian);
                    statementPengembalian.setString(2, idPeminjaman);
                    statementPengembalian.setString(3, txtIDPenjaga.getText());
                    statementPengembalian.setString(4, txtNISN.getText());
                    statementPengembalian.setString(5, txtTglDikembalikan.getText());
                    statementPengembalian.setDouble(6, Double.parseDouble(lbTotalDenda.getText()));
                    statementPengembalian.executeUpdate();
                }
                String updateStatusPeminjamanSql = "UPDATE peminjaman SET Status_Peminjaman = 'Selesai' WHERE ID_Peminjaman = ?";
                try (PreparedStatement statementUpdateStatusPeminjaman = conn.prepareStatement(updateStatusPeminjamanSql)) {
                    statementUpdateStatusPeminjaman.setString(1, idPeminjaman);
                    statementUpdateStatusPeminjaman.executeUpdate();
                }

                String insertDetailPengembalianSql = "INSERT INTO DETAIL_PENGEMBALIAN (ID_PENGEMBALIAN, ID_DETAIL_PENGEMBALIAN, ID_BUKU, SUBTOTAL_DENDA) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstDetailPengembalian = conn.prepareStatement(insertDetailPengembalianSql)) {
                    for (int row = 0; row < keranjangTableModel.getRowCount(); row++) {
                        pstDetailPengembalian.setString(1, idPengembalian);
                        pstDetailPengembalian.setString(2, (String) keranjangTableModel.getValueAt(row, 0));
                        pstDetailPengembalian.setString(3, (String) keranjangTableModel.getValueAt(row, 2));
                        pstDetailPengembalian.setDouble(4, Double.parseDouble((String) keranjangTableModel.getValueAt(row, 3)));

                        pstDetailPengembalian.addBatch();
                    }
                    pstDetailPengembalian.executeBatch();
                }

                String updateStatusPengembalianSql = "UPDATE detail_peminjaman SET STATUS_PENGEMBALIAN = ? WHERE ID_BUKU = ?";
                try (PreparedStatement statementUpdateStatusPengembalian = conn.prepareStatement(updateStatusPengembalianSql)) {
                    for (int row = 0; row < keranjangTableModel.getRowCount(); row++) {
                        String statusPengembalian = (String) keranjangTableModel.getValueAt(row, 4); 
                        String idBuku = (String) keranjangTableModel.getValueAt(row, 2);
                        statementUpdateStatusPengembalian.setString(1, statusPengembalian);
                        statementUpdateStatusPengembalian.setString(2, idBuku);
                        statementUpdateStatusPengembalian.addBatch();
                    }
                    statementUpdateStatusPengembalian.executeBatch();
                }


                String updateStatusKetersediaanSql = "UPDATE entry SET STATUS_KETERSEDIAAN = CASE WHEN ? = 'Dikembalikan' THEN 'Tersedia' WHEN ? = 'Hilang' THEN 'Hilang' ELSE STATUS_KETERSEDIAAN END WHERE ID_BUKU = ?";
                try (PreparedStatement statementUpdateStatusKetersediaan = conn.prepareStatement(updateStatusKetersediaanSql)) {
                    for (int row = 0; row < keranjangTableModel.getRowCount(); row++) {
                        String statusPengembalian = (String) keranjangTableModel.getValueAt(row, 4); 
                        String idBuku = (String) keranjangTableModel.getValueAt(row, 2);

                        statementUpdateStatusKetersediaan.setString(1, statusPengembalian);
                        statementUpdateStatusKetersediaan.setString(2, statusPengembalian);
                        statementUpdateStatusKetersediaan.setString(3, idBuku);
                        statementUpdateStatusKetersediaan.addBatch();
                    }
                    statementUpdateStatusKetersediaan.executeBatch();
                }

             
                conn.commit();
                JOptionPane.showMessageDialog(this, "Pengembalian berhasil!");
                clearForm();
                 updateTotalDenda();
                 txtIDKembali.setText(setIDKembali());
            } else {
                JOptionPane.showMessageDialog(this, "Jumlah buku yang dikembalikan tidak sesuai.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
             
                rollbackEx.printStackTrace();
            } finally {
                closeConnection(conn);
            }

            Logger.getLogger(MenuPeminjaman.class.getName()).log(Level.SEVERE, "Error during peminjaman process!", ex);
            JOptionPane.showMessageDialog(this, "Error during peminjaman process!");
        } finally {
            closeConnection(conn);
        }


    }//GEN-LAST:event_btnMengembalikanActionPerformed

    private void txtTglPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglPinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglPinjamActionPerformed

    private void txtTglKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglKembaliActionPerformed

    private void txtIDPenjagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDPenjagaActionPerformed

    }//GEN-LAST:event_txtIDPenjagaActionPerformed

    private void btnPilihPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihPinjamActionPerformed
        Dialog_DataPeminjaman dialogDataPeminjaman = new Dialog_DataPeminjaman(new javax.swing.JFrame(), true);
        dialogDataPeminjaman.setVisible(true);

        if (dialogDataPeminjaman.ispeminjamanSelected()) {

            String selectedidminjaman = dialogDataPeminjaman.getselectedidPeminjaman();
            String selectedNisn = dialogDataPeminjaman.getselectedNISNSantri();
            String selectedNamaSantri = dialogDataPeminjaman.getselectednamaSantri();
            String selectedtglPinjam = dialogDataPeminjaman.getselectedtglPeminjaman();
            String selectedtglKembali = dialogDataPeminjaman.getselectedtglPengembalian();
            txtIDPinjam.setText(selectedidminjaman);
            txtNISN.setText(selectedNisn);
            txtNamaSantri.setText(selectedNamaSantri);
            txtTglPinjam.setText(selectedtglPinjam);
            txtTglKembali.setText(selectedtglKembali);

        }
    }//GEN-LAST:event_btnPilihPinjamActionPerformed

    private void txtNISNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNISNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNISNActionPerformed

    private void txtNamaJagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaJagaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaJagaActionPerformed

    private void txtNamaSantriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaSantriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaSantriActionPerformed

    private void txtUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitActionPerformed

    private void txtTglDikembalikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglDikembalikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglDikembalikanActionPerformed

    private void btnOtherUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOtherUnitActionPerformed
        String IDPeminjaman = txtIDPinjam.getText();

        if (!IDPeminjaman.isEmpty()) {

            Dialog_DataUnit dialogDataUnit = new Dialog_DataUnit(new javax.swing.JFrame(), true);

            dialogDataUnit.loadUnitbyIDPeminjaman(IDPeminjaman);

            dialogDataUnit.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {

                    if (dialogDataUnit.isunitSelected()) {

                        String selectedIdBuku = dialogDataUnit.getselectedidBuku();

                        txtUnit.setText(selectedIdBuku);
                        ;
                    }
                }
            });
            dialogDataUnit.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Masukkan ISBN terlebih dahulu.");
        }
    }//GEN-LAST:event_btnOtherUnitActionPerformed

    private void txtSubDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubDendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubDendaActionPerformed

    private void txtIDKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKembaliActionPerformed

    private void btnHilangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHilangActionPerformed
        String idDetail = txtDetail.getText();
        String idPengembalian = txtIDKembali.getText();
        String idBuku = txtUnit.getText();
        String statusPengembalian = "Hilang";

        if (idDetail.isEmpty() || idPengembalian.isEmpty() || idBuku.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        if (!isIdBukuExists(idBuku)) {
           
            JTextField subDendaField = new JTextField();

          
            ((AbstractDocument) subDendaField.getDocument()).setDocumentFilter(new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                        throws BadLocationException {
                    if (text.matches("[0-9]*")) {
                        super.insertString(fb, offset, text, attr);
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    if (text.matches("[0-9]*")) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            });

           
            int option = JOptionPane.showConfirmDialog(this, subDendaField, "Masukkan Denda",
                    JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String subDendaInput = subDendaField.getText();

                if (subDendaInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Sub Denda tidak boleh kosong!");
                    return;
                }

                try {
                    Double.parseDouble(subDendaInput);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Sub Denda harus berupa angka!");
                    return;
                }

                keranjangTableModel.addRow(new Object[]{idDetail, idPengembalian, idBuku, subDendaInput, statusPengembalian});
                clearKeranjang();
                updateTotalDenda();
            }
        } else {
            JOptionPane.showMessageDialog(this, "ID Buku Sudah ada");
        }
    }//GEN-LAST:event_btnHilangActionPerformed

    private void btn_addKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addKeranjangActionPerformed
        String idDetail = txtDetail.getText();
        String idPengembalian = txtIDKembali.getText();
        String idBuku = txtUnit.getText();
        String subDenda = txtSubDenda.getText();
        String statusPengembalian = "Dikembalikan";

        if (idDetail.isEmpty() || idPengembalian.isEmpty() || idBuku.isEmpty() || subDenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        if (!isIdBukuExists(idBuku)) {
            keranjangTableModel.addRow(new Object[]{idDetail, idPengembalian, idBuku, subDenda, statusPengembalian});
            clearKeranjang();
            updateTotalDenda();
        } else {
            JOptionPane.showMessageDialog(this, "ID Buku Sudah ada");
        }
    }//GEN-LAST:event_btn_addKeranjangActionPerformed

    private void btn_hapusKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusKeranjangActionPerformed
        int selectedRow = tblKeranjang.getSelectedRow();

        if (selectedRow != -1) {
            keranjangTableModel.removeRow(selectedRow);

        } else {
            JOptionPane.showMessageDialog(this, "Pilih Baris");
        }
    }//GEN-LAST:event_btn_hapusKeranjangActionPerformed

    private void txtDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDetailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDetailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAdd;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JButton btnHilang;
    private rojerusan.RSMaterialButtonRectangle btnMengembalikan;
    private javax.swing.JButton btnOtherUnit;
    private javax.swing.JButton btnPilihPinjam;
    private keeptoo.KButton btn_addKeranjang;
    private keeptoo.KButton btn_hapusKeranjang;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lbDendaHari;
    private javax.swing.JLabel lbTotalDenda;
    private javax.swing.JTable tblKeranjang;
    private javax.swing.JTextField txtDetail;
    private javax.swing.JTextField txtIDKembali;
    private javax.swing.JTextField txtIDPenjaga;
    private javax.swing.JTextField txtIDPinjam;
    private javax.swing.JTextField txtNISN;
    private javax.swing.JTextField txtNamaJaga;
    private javax.swing.JTextField txtNamaSantri;
    private javax.swing.JTextField txtSubDenda;
    private javax.swing.JTextField txtTglDikembalikan;
    private javax.swing.JTextField txtTglKembali;
    private javax.swing.JTextField txtTglPinjam;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables

    private void clearAll() {
        txtIDPinjam.setText("");
        txtTglDikembalikan.setText("");
        txtIDPenjaga.setText("");
        txtNISN.setText("");
        txtNamaJaga.setText("");
        txtNamaSantri.setText("");
        txtTglPinjam.setText("");
        txtTglKembali.setText("");
        txtUnit.setText("");
        txtSubDenda.setText("");
        txtIDKembali.setText("");
        txtDetail.setText("");
        keranjangTableModel.setRowCount(0);
    }

    private boolean isDetailPeminjamanCountValid(Connection conn, String idPeminjaman) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM detail_peminjaman WHERE ID_Peminjaman = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, idPeminjaman);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int rowCount = resultSet.getInt("Total");
                        int expectedRowCount = keranjangTableModel.getRowCount();

                        return rowCount == expectedRowCount;
                    }
                }
            }
        } catch (SQLException e) {
        
            e.printStackTrace();
        }
        return false;
    }

    private double calculateTotalDenda() {
        double totalDenda = 0.0;

        for (int row = 0; row < keranjangTableModel.getRowCount(); row++) {
            double subTotalDenda = Double.parseDouble(keranjangTableModel.getValueAt(row, 3).toString());
            totalDenda += subTotalDenda;
        }

        return totalDenda;
    }

    private void updateTotalDenda() {
        double totalDenda = calculateTotalDenda();
        lbTotalDenda.setText(String.valueOf(totalDenda));
    }

    private void calculateFine() {
        try {
          
            if (!txtTglPinjam.getText().isEmpty() && !txtTglKembali.getText().isEmpty() && !txtTglDikembalikan.getText().isEmpty()) {
                Date tanggalPinjam = new SimpleDateFormat("yyyy-MM-dd").parse(txtTglPinjam.getText());
                Date tanggalKembali = new SimpleDateFormat("yyyy-MM-dd").parse(txtTglKembali.getText());
                Date tanggalDikembalikan = new SimpleDateFormat("yyyy-MM-dd").parse(txtTglDikembalikan.getText());

             
                if (tanggalDikembalikan.after(tanggalKembali)) {
                    double dendaPerHari = Double.parseDouble(lbDendaHari.getText());
                    double totalFine = 0;

                    long diffInMillies = Math.abs(tanggalDikembalikan.getTime() - tanggalKembali.getTime());
                    long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(tanggalKembali);

                    for (int i = 0; i < diffInDays; i++) {
                        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.FRIDAY) {
                            totalFine += dendaPerHari;
                        }

                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                    }

                   
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    totalFine = Double.parseDouble(decimalFormat.format(totalFine));

                   
                    txtSubDenda.setText(String.valueOf(totalFine));
                } else {
          
                    txtSubDenda.setText("0.00");
                    lbTotalDenda.setText("0.00");
                }
            } else {
            
                txtSubDenda.setText("0.00");
           
            }

        } catch (ParseException e) {
            e.printStackTrace();  
        }
    }

    private void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException closeEx) {
         
            closeEx.printStackTrace();
        }
    }

    private void clearForm() {
        txtIDPinjam.setText("");
        txtTglDikembalikan.setText("");
        txtIDPenjaga.setText("");
        txtNISN.setText("");
        txtNamaJaga.setText("");
        txtNamaSantri.setText("");
        txtTglPinjam.setText("");
        txtTglKembali.setText("");
        txtUnit.setText("");
        txtSubDenda.setText("");
        txtIDKembali.setText("");
        txtDetail.setText("");
        keranjangTableModel.setRowCount(0);

    }

    private void clearKeranjang() {
        txtUnit.setText("");
    }

    private boolean isIdBukuExists(String idBuku) {
        for (int row = 0; row < keranjangTableModel.getRowCount(); row++) {
            String existingIdBuku = (String) keranjangTableModel.getValueAt(row, 2);
            if (existingIdBuku.equals(idBuku)) {
                return true;
            }
        }
        return false;
    }

    private void KeranjangTableModel() {
        String[] keranjangColumns = {"ID Detail", "ID Pengembalian", "ID Buku", "SubTotal Denda", "Status Pengembalian"};
        Object[][] keranjangData = new Object[0][keranjangColumns.length];

        keranjangTableModel = new DefaultTableModelNonEditable(keranjangData, keranjangColumns);
        tblKeranjang.setModel(keranjangTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i = 0; i < keranjangColumns.length; i++) {
            tblKeranjang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = tblKeranjang.getTableHeader();
        header.setBackground(new Color(235, 227, 213));

    }

    class DefaultTableModelNonEditable extends DefaultTableModel {

        public DefaultTableModelNonEditable(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void updateKodeDetail() {
        String idPengembalin = txtIDKembali.getText();
        String idBuku = txtUnit.getText();

        if (!idPengembalin.isEmpty() && !idBuku.isEmpty()) {
            String kodeDetail = idPengembalin + "//" + idBuku;
            txtDetail.setText(kodeDetail);
        }
    }

    private String setIDKembali() {
        String urutan = null;
        Date now = new Date();
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");

        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT RIGHT(ID_PENGEMBALIAN, 4) AS Nomor "
                    + "FROM PENGEMBALIAN "
                    + "WHERE ID_PENGEMBALIAN LIKE 'KMBL" + noFormat.format(now) + "%' "
                    + "ORDER BY ID_PENGEMBALIAN DESC "
                    + "LIMIT 1";

            try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

                if (rs.next()) {
                    int nomor = Integer.parseInt(rs.getString("Nomor")) + 1;
                    urutan = "KMBL" + noFormat.format(now) + String.format("%04d", nomor);
                } else {
                    urutan = "KMBL" + noFormat.format(now) + "001";
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuEntry.class.getName()).log(Level.SEVERE, null, e);
        }
        return urutan;
    }
 private boolean isValidDateOrder(String startDate, String endDate) {
        if (startDate.isEmpty() || endDate.isEmpty()) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datePinjam = sdf.parse(startDate);
            Date dateKembali = sdf.parse(endDate);

            return dateKembali.after(datePinjam);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
