package Panel;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Config.Koneksi;
import com.raven.datechooser.SelectedDate;
import java.awt.Color;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.FormLogin;

/**
 *
 * @author Rifal
 */
public class MenuAkun extends javax.swing.JPanel {

    private int halamanSaatIni = 1;
    private int dataPerHalaman = 20;
    private int totalHalaman;
    private int totalPages;
    
private final Connection conn;
private FormLogin formLogin;

    private void autoResizeAllColumns() {
        int columns = tblData.getColumnCount();
        for (int i = 0; i < columns; i++) {
            TableColumn column = tblData.getColumnModel().getColumn(i);
            int width = (int) tblData.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(tblData, column.getHeaderValue(), false, false, -1, i)
                    .getPreferredSize().getWidth();
            for (int row = 0; row < tblData.getRowCount(); row++) {
                int preferedWidth = (int) tblData.getCellRenderer(row, i)
                        .getTableCellRendererComponent(tblData, tblData.getValueAt(row, i), false, false, row, i)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            column.setPreferredWidth(width);
        }
    }

    private void paginationAkun() {
        btn_first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                halamanSaatIni = 1;
                loadData();

            }

        });
        btn_before.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (halamanSaatIni > 1) {
                    halamanSaatIni--;
                    loadData();
                }
            }
        });
        cbx_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPerHalaman = Integer.parseInt(cbx_data.getSelectedItem().toString());
                halamanSaatIni = 1;
                loadData();
            }
        });
        btn_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (halamanSaatIni < totalPages) {
                    halamanSaatIni++;
                    loadData();
                }
            }
        });
        btn_last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                halamanSaatIni = totalPages;
                loadData();
            }
        });
    }

    private void calculateTotalPages() {
        int totalData = getTotalData();
        totalPages = (int) Math.ceil((double) totalData / dataPerHalaman);
    }

    private int getTotalData() {
        int totalData = 0;
       try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT COUNT(*) AS total from Akun";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    totalData = rs.getInt("total");

                }

            }

        } catch (Exception e) {
            Logger.getLogger(MenuAkun.class.getName()).log(Level.SEVERE, null, e);
        }
        return totalData;
    }

    

    public MenuAkun() {
        initComponents();

        setTabelModel();
        this.conn = Koneksi.getConnection();
        Color headerColor = new Color(127, 140, 117);
        tblData.getTableHeader().setBackground(headerColor);
        loadData();
        paginationAkun();
        formLogin = new FormLogin();
txt_search.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        searchData();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        searchData();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // Plain text components do not fire these events
    }
});

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ButtonGroup jenisKelamin = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        PanelMain = new javax.swing.JPanel();
        PanelView = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lb_iconAnggota = new javax.swing.JLabel();
        lb_halaman = new javax.swing.JLabel();
        btn_first = new javax.swing.JButton();
        btn_before = new javax.swing.JButton();
        cbx_data = new javax.swing.JComboBox<>();
        btn_next = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnAdd = new rojerusan.RSMaterialButtonRectangle();
        btnDelete = new rojerusan.RSMaterialButtonRectangle();
        btnCancel = new rojerusan.RSMaterialButtonRectangle();
        jLabel2 = new javax.swing.JLabel();
        PanelAdd = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lb_iconAnggota2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbPW = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnSave = new rojerusan.RSMaterialButtonRectangle();
        btnCancel2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        cbLevel = new javax.swing.JComboBox<>();

        setLayout(new java.awt.CardLayout());

        PanelMain.setBackground(new java.awt.Color(255, 255, 255));
        PanelMain.setPreferredSize(new java.awt.Dimension(950, 590));
        PanelMain.setLayout(new java.awt.CardLayout());

        PanelView.setBackground(new java.awt.Color(255, 255, 255));
        PanelView.setPreferredSize(new java.awt.Dimension(950, 590));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setText("Akun");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setText("Daftar Akun");

        lb_iconAnggota.setBackground(new java.awt.Color(250, 250, 250));
        lb_iconAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pngwing.com (34).png"))); // NOI18N

        lb_halaman.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_halaman.setText("Halaman of Total Halaman");

        btn_first.setText("First Page");

        btn_before.setText("<");

        cbx_data.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "20", "40", "80" }));

        btn_next.setText(">");

        btn_last.setText("Last Page");

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchKeyTyped(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_database_30px.png"))); // NOI18N

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblData.setShowGrid(true);
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblData);

        btnAdd.setBackground(new java.awt.Color(80, 141, 105));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/13.png"))); // NOI18N
        btnAdd.setText("Tambah");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(190, 49, 68));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/13.png"))); // NOI18N
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(153, 153, 153));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/13.png"))); // NOI18N
        btnCancel.setText("Batal");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/akun.png"))); // NOI18N

        javax.swing.GroupLayout PanelViewLayout = new javax.swing.GroupLayout(PanelView);
        PanelView.setLayout(PanelViewLayout);
        PanelViewLayout.setHorizontalGroup(
            PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addComponent(lb_iconAnggota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelViewLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelViewLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelViewLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(PanelViewLayout.createSequentialGroup()
                                .addComponent(btn_first)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_before)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_next)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_last))
                            .addComponent(lb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        PanelViewLayout.setVerticalGroup(
            PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_iconAnggota)
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(44, 44, 44)))
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelViewLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_data, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        PanelMain.add(PanelView, "card2");

        PanelAdd.setBackground(new java.awt.Color(255, 255, 255));
        PanelAdd.setPreferredSize(new java.awt.Dimension(950, 590));

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel13.setText("Input Data Akun");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setText("Tambah Data Akun");

        lb_iconAnggota2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pngwing.com (35).png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel16.setText("Username");

        txtUser.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtUser.setForeground(new java.awt.Color(102, 102, 102));
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel18.setText("Email");

        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(102, 102, 102));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        lbPW.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbPW.setText("Password");

        jLabel20.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel20.setText("Level");

        btnSave.setBackground(new java.awt.Color(80, 141, 105));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/13.png"))); // NOI18N
        btnSave.setText("Simpan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel2.setBackground(new java.awt.Color(153, 153, 153));
        btnCancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/13.png"))); // NOI18N
        btnCancel2.setText("Batal");
        btnCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel2ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/akun.png"))); // NOI18N

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbLevel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--", "Admin", "User" }));

        javax.swing.GroupLayout PanelAddLayout = new javax.swing.GroupLayout(PanelAdd);
        PanelAdd.setLayout(PanelAddLayout);
        PanelAddLayout.setHorizontalGroup(
            PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAddLayout.createSequentialGroup()
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelAddLayout.createSequentialGroup()
                        .addContainerGap(722, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelAddLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addComponent(lb_iconAnggota2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))
                            .addComponent(txtUser)
                            .addComponent(txtEmail)
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18)
                                    .addComponent(lbPW)
                                    .addComponent(jLabel20)
                                    .addComponent(cbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        PanelAddLayout.setVerticalGroup(
            PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel13)
                        .addComponent(jLabel3))
                    .addGroup(PanelAddLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel14))
                    .addComponent(lb_iconAnggota2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelMain.add(PanelAdd, "card2");

        add(PanelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txt_searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyTyped
        // TODO add your handling code here:
        searchData();
    }//GEN-LAST:event_txt_searchKeyTyped

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked

        if (btnAdd.getText().equals("Tambah")) {
            btnAdd.setText("Ubah");
            btnDelete.setVisible(true);
            btnCancel.setVisible(true);
        }

    }//GEN-LAST:event_tblDataMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        PanelMain.removeAll();
        PanelMain.add(PanelAdd);
        PanelMain.repaint();
        PanelMain.revalidate();
       

        if (btnAdd.getText().equals("Ubah")) {
        dataTabel();
        btnSave.setText("Perbarui");

        
        txtUser.setEditable(false);
    } else {
       
        txtUser.setEditable(true);
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        showPanel();
        loadData();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (btnSave.getText().equals("Tambah")) {
            btnSave.setText("Simpan");
        } else if (btnSave.getText().equals("Simpan")) {
            insertData();
        } else if (btnSave.getText().equals("Perbarui")) {
            updateData();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed
        // TODO add your handling code here:
        showPanel();
        loadData();
    }//GEN-LAST:event_btnCancel2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAdd;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelView;
    private rojerusan.RSMaterialButtonRectangle btnAdd;
    private rojerusan.RSMaterialButtonRectangle btnCancel;
    private rojerusan.RSMaterialButtonRectangle btnCancel2;
    private rojerusan.RSMaterialButtonRectangle btnDelete;
    private rojerusan.RSMaterialButtonRectangle btnSave;
    private javax.swing.JButton btn_before;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JComboBox<String> cbLevel;
    private javax.swing.JComboBox<String> cbx_data;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbPW;
    private javax.swing.JLabel lb_halaman;
    private javax.swing.JLabel lb_iconAnggota;
    private javax.swing.JLabel lb_iconAnggota2;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        calculateTotalPages();
        int totalData = getTotalData();
        lb_halaman.setText(String.valueOf("Halaman " + halamanSaatIni + " dari Total Data " + totalData));

        int startIndex = (halamanSaatIni - 1) * dataPerHalaman;
        getData(startIndex, dataPerHalaman, (DefaultTableModel) tblData.getModel());

        btnDelete.setVisible(false);
        btnCancel.setVisible(false);
    }

    private void showPanel() {
        PanelMain.removeAll();
        PanelMain.add(new MenuAkun());
        PanelMain.repaint();
        PanelMain.revalidate();
    }

    private void resetForm() {
         txtUser.setText("");
         txtEmail.setText("");
         txtPassword.setText("");
        cbLevel.setSelectedItem("--Pilih--");
    }

    private void setTabelModel() {
      DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
             return false;
        }
    };
        model.addColumn("Username");
        model.addColumn("Email");
        model.addColumn("Level");

        tblData.setModel(model);
        autoResizeAllColumns();
        tblData.setAutoCreateRowSorter(true);
        tblData.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < tblData.getColumnCount(); i++) {
            tblData.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            
            tblData.setDefaultEditor(Object.class, null);
        }
    }

    private void getData(int startIndex, int entriesPage, DefaultTableModel model) {
        model.setRowCount(0);

        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT * FROM Akun LIMIT ?,?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, startIndex);
                st.setInt(2, entriesPage);

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    String Username = rs.getString("Username");
                    String emailAkun = rs.getString("Email");
                    String levelAkun = rs.getString("Level");

                    Object[] rowData = {Username, emailAkun, levelAkun};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuAkun.class.getName()).log(Level.SEVERE, null, e);
        }
    }


   private boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    return email.matches(emailRegex);
}

private void insertData() {
    String username = txtUser.getText();
    String emailAkun = txtEmail.getText();
    String password = formLogin.getMd5java(txtPassword.getText());
    String levelAkun = cbLevel.getSelectedItem().toString();

    if (username.isEmpty() || emailAkun.isEmpty() || password.isEmpty() || cbLevel.getSelectedItem().toString().equals("--Pilih--")) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!isValidEmail(emailAkun)) {
        JOptionPane.showMessageDialog(this, "Format email tidak valid!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

   try (Connection conn = Koneksi.getConnection()) {
   
    String checkExistenceQuery = "SELECT COUNT(*) FROM Akun WHERE Username=? OR Email=?";
    try (PreparedStatement checkExistenceStmt = conn.prepareStatement(checkExistenceQuery)) {
        checkExistenceStmt.setString(1, username);
        checkExistenceStmt.setString(2, emailAkun);
        try (ResultSet resultSet = checkExistenceStmt.executeQuery()) {
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Username atau email sudah ada!", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

   
    String insertQuery = "INSERT INTO Akun (Username, Email, Password, Level) VALUES (?,?,?,?)";
    try (PreparedStatement st = conn.prepareStatement(insertQuery)) {
        st.setString(1, username);
        st.setString(2, emailAkun);
        st.setString(3, password);
        st.setString(4, levelAkun);

        int rowInserted = st.executeUpdate();
        if (rowInserted > 0) {
            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
            resetForm();
            loadData();
            showPanel();
        }
    }
} catch (SQLException e) {
    Logger.getLogger(MenuAkun.class.getName()).log(Level.SEVERE, null, e);
}
}

    private void updateData() {
    String username = txtUser.getText();
    String emailAkun = txtEmail.getText();
    String levelAkun = cbLevel.getSelectedItem().toString();

    if (username.isEmpty() || emailAkun.isEmpty() || cbLevel.getSelectedItem().toString().equals("--Pilih--")) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!isValidEmail(emailAkun)) {
        JOptionPane.showMessageDialog(this, "Format email tidak valid!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

   try (Connection conn = Koneksi.getConnection()) {
    
    String checkExistenceQuery = "SELECT COUNT(*) FROM Akun WHERE (Username=? OR Email=?) AND Username<>?";
    try (PreparedStatement checkExistenceStmt = conn.prepareStatement(checkExistenceQuery)) {
        checkExistenceStmt.setString(1, username);
        checkExistenceStmt.setString(2, emailAkun);
        checkExistenceStmt.setString(3, username);
        try (ResultSet resultSet = checkExistenceStmt.executeQuery()) {
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Username atau email sudah ada!", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

   
    String updateQuery = "UPDATE Akun SET Email=?, Level=? WHERE Username=?";
    try (PreparedStatement st = conn.prepareStatement(updateQuery)) {
        st.setString(1, emailAkun);
        st.setString(2, levelAkun);
        st.setString(3, username);

        int rowUpdated = st.executeUpdate();
        if (rowUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui");
            resetForm();
            loadData();
            showPanel();
        }
    }
} catch (SQLException e) {
    Logger.getLogger(MenuAkun.class.getName()).log(Level.SEVERE, null, e);
}
}


    private void dataTabel() {
        PanelView.setVisible(false);
        PanelAdd.setVisible(true);

        int row = tblData.getSelectedRow();
        jLabel14.setText("Perbarui Data Akun");
        
        
        lbPW.setVisible(false);
        txtPassword.setVisible(false);

      
        txtUser.setText(tblData.getValueAt(row, 0).toString());
        txtEmail.setText(tblData.getValueAt(row, 1).toString());
        cbLevel.setSelectedItem(tblData.getValueAt(row, 2).toString());
    }

    private void deleteData() {
        int selectedRow = tblData.getSelectedRow();
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah anda yakin menghapus data ini ?",
                "Konfirmasi Hapus Data",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String id = tblData.getValueAt(selectedRow, 0).toString();
            try (Connection conn = Koneksi.getConnection()) {
                String sql = "DELETE FROM Akun WHERE Username=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, id);
                    int rowDeleted = st.executeUpdate();

                    if (rowDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                    } else {
                        JOptionPane.showMessageDialog(this, "Data Gagal Dihapus");
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(MenuAkun.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        resetForm();
        loadData();
        showPanel();
    }

    private void searchData() {
        String kataKunci = txt_search.getText();
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        model.setRowCount(0);

       try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT * FROM Akun WHERE Username LIKE ? OR Email LIKE ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, "%" + kataKunci + "%");
                st.setString(2, "%" + kataKunci + "%");
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
              
                    String Username = rs.getString("Username");
                    String emailAkun = rs.getString("Email");
                    String levelAkun = rs.getString("Level");

                    Object[] rowData = { Username, emailAkun, levelAkun};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuAkun.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
