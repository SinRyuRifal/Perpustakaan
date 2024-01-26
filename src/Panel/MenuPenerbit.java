package Panel;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Config.Koneksi;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author Rifal
 */
public class MenuPenerbit extends javax.swing.JPanel {

    
  

    private int halamanSaatIni = 1;
    private int dataPerHalaman = 20;
    private int totalHalaman;
    private int totalPages;


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

    private void pagination() {
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
            String sql = "SELECT COUNT(*) AS total from penerbit";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    totalData = rs.getInt("total");

                }

            }

        } catch (Exception e) {
            Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
        }
        return totalData;
    }

   

    public MenuPenerbit() {
        initComponents();
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

        setTabelModel();
        
        Color headerColor = new Color(127, 140, 117);
        tblData.getTableHeader().setBackground(headerColor);
        loadData();
        pagination();
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
        btn_Import = new keeptoo.KButton();
        btn_Ecport = new keeptoo.KButton();
        PanelAdd = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lb_iconAnggota2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtKota = new javax.swing.JTextField();
        btnSave = new rojerusan.RSMaterialButtonRectangle();
        btnCancel2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        PanelMain.setBackground(new java.awt.Color(255, 255, 255));
        PanelMain.setPreferredSize(new java.awt.Dimension(950, 590));
        PanelMain.setLayout(new java.awt.CardLayout());

        PanelView.setBackground(new java.awt.Color(255, 255, 255));
        PanelView.setPreferredSize(new java.awt.Dimension(950, 590));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setText("Penerbit");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setText("Daftar Penerbit");

        lb_iconAnggota.setBackground(new java.awt.Color(250, 250, 250));
        lb_iconAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pngwing.com (14).png"))); // NOI18N

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/penerbit.png"))); // NOI18N

        btn_Import.setText("Import");
        btn_Import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImportActionPerformed(evt);
            }
        });

        btn_Ecport.setText("Export");
        btn_Ecport.setkEndColor(new java.awt.Color(204, 255, 255));
        btn_Ecport.setkStartColor(new java.awt.Color(51, 0, 255));
        btn_Ecport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EcportActionPerformed(evt);
            }
        });

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
                                .addGap(0, 670, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelViewLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelViewLayout.createSequentialGroup()
                        .addComponent(btn_Ecport, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Import, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addComponent(lb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_data, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Import, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Ecport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        PanelMain.add(PanelView, "card2");

        PanelAdd.setBackground(new java.awt.Color(255, 255, 255));
        PanelAdd.setPreferredSize(new java.awt.Dimension(950, 590));

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel13.setText("Input Penerbit");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setText("Tambah Penerbit");

        lb_iconAnggota2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pngwing.com (15).png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel15.setText("ID Penerbit");

        txtID.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(102, 102, 102));
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel16.setText("Kota Terbit");

        txtNama.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNama.setForeground(new java.awt.Color(102, 102, 102));

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel17.setText("Nama Penerbit");

        txtKota.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtKota.setForeground(new java.awt.Color(102, 102, 102));
        txtKota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKotaActionPerformed(evt);
            }
        });

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kategori.png"))); // NOI18N

        javax.swing.GroupLayout PanelAddLayout = new javax.swing.GroupLayout(PanelAdd);
        PanelAdd.setLayout(PanelAddLayout);
        PanelAddLayout.setHorizontalGroup(
            PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAddLayout.createSequentialGroup()
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAddLayout.createSequentialGroup()
                        .addContainerGap(738, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelAddLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addComponent(lb_iconAnggota2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))
                            .addComponent(txtID)
                            .addComponent(txtNama)
                            .addComponent(txtKota)
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        PanelAddLayout.setVerticalGroup(
            PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAddLayout.createSequentialGroup()
                        .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel3))
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAddLayout.createSequentialGroup()
                        .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel14))
                            .addComponent(lb_iconAnggota2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelMain.add(PanelAdd, "card2");

        add(PanelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIDActionPerformed

    private void txtKotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKotaActionPerformed

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
        txtID.setText(setIDPenerbit());
        if (btnAdd.getText().equals("Ubah")) {
            dataTabel();
            btnSave.setText("Perbarui");
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

    private void btn_ImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImportActionPerformed
      // TODO add your handling code here:
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
    fileChooser.setFileFilter(filter);

    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        importExcelToDatabase(selectedFile);
        loadData(); 
    }
    }//GEN-LAST:event_btn_ImportActionPerformed

    private void btn_EcportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EcportActionPerformed
     try {
        
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Penerbit Data");

       
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < columnCount; col++) {
            Cell headerCell = headerRow.createCell(col);
            headerCell.setCellValue(model.getColumnName(col));
        }

       
        for (int row = 0; row < rowCount; row++) {
            Row dataRow = sheet.createRow(row + 1);
            for (int col = 0; col < columnCount; col++) {
                Cell dataCell = dataRow.createCell(col);
                dataCell.setCellValue(String.valueOf(model.getValueAt(row, col)));
            }
        }

       
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
                JOptionPane.showMessageDialog(this, "Data exported successfully to:\n" + filePath, "Export Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error exporting data: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        workbook.close();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error exporting data: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btn_EcportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAdd;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelView;
    private rojerusan.RSMaterialButtonRectangle btnAdd;
    private rojerusan.RSMaterialButtonRectangle btnCancel;
    private rojerusan.RSMaterialButtonRectangle btnCancel2;
    private rojerusan.RSMaterialButtonRectangle btnDelete;
    private rojerusan.RSMaterialButtonRectangle btnSave;
    private keeptoo.KButton btn_Ecport;
    private keeptoo.KButton btn_Import;
    private javax.swing.JButton btn_before;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JComboBox<String> cbx_data;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_halaman;
    private javax.swing.JLabel lb_iconAnggota;
    private javax.swing.JLabel lb_iconAnggota2;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtKota;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
private void importExcelToDatabase(File file) {
    try {
        Connection conn = Koneksi.getConnection();

       
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

      
        boolean isHeaderRow = true;

       
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

           
            if (isHeaderRow) {
                isHeaderRow = false;
                continue;
            }

            Iterator<Cell> cellIterator = row.cellIterator();

          
            String idPenerbit = getCellValueAsString(cellIterator.next());
            String namaPenerbit = getCellValueAsString(cellIterator.next());
            String kotaTerbit = getCellValueAsString(cellIterator.next());

         
            if (isIdPenerbitExists(conn, idPenerbit)) {
                System.out.println("Skipping row with idPenerbit " + idPenerbit + " as it already exists.");
                continue;  
            }

           
            insertDataToDatabase(conn, idPenerbit, namaPenerbit, kotaTerbit);
        }

        workbook.close();
        fis.close();
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading Excel file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error inserting data to database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private boolean isIdPenerbitExists(Connection conn, String idPenerbit) throws SQLException {
    String query = "SELECT COUNT(*) AS count FROM penerbit WHERE ID_Penerbit=?";
    try (PreparedStatement st = conn.prepareStatement(query)) {
        st.setString(1, idPenerbit);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0;
        }
    }
    return false;
}

private String getCellValueAsString(Cell cell) {
    switch (cell.getCellType()) {
        case STRING:
            return cell.getStringCellValue();
        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
               
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.format(cell.getDateCellValue());
            } else {
             
                return String.valueOf(cell.getNumericCellValue());
            }
        case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        case FORMULA:
        
            return cell.getCellFormula();
        case BLANK:
          
            return "";
        default:
            return "";
    }
}


private void insertDataToDatabase(Connection conn, String idPenerbit, String namaPenerbit, String kotaTerbit)
        throws SQLException {
    String query = "INSERT INTO penerbit (id_penerbit, nama_penerbit, kota_terbit) VALUES (?, ?, ?)";
    try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
        preparedStatement.setString(1, idPenerbit);
        preparedStatement.setString(2, namaPenerbit);
        preparedStatement.setString(3, kotaTerbit);

        preparedStatement.executeUpdate();
    }
}
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
        PanelMain.add(new MenuPenerbit());
        PanelMain.repaint();
        PanelMain.revalidate();
    }

    private void resetForm() {
        txtID.setText("");
        txtNama.setText("");
        txtKota.setText("");

    }

    private void setTabelModel() {
         DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
             return false;
        }
    };
        model.addColumn("ID Penerbit");
        model.addColumn("Nama Penerbit");
        model.addColumn("Kota Terbit");
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
            String sql = "SELECT * FROM penerbit LIMIT ?,?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, startIndex);
                st.setInt(2, entriesPage);

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    String idPenerbit = rs.getString("ID_Penerbit");
                    String namaPenerbit = rs.getString("Nama_Penerbit");
                    String tempatTerbit = rs.getString("Kota_Terbit");

                    Object[] rowData = {idPenerbit, namaPenerbit, tempatTerbit};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
        }
    }

   
    public boolean validasiNama() {
        boolean valid = false;
        String idPenerbit = txtID.getText();
        String namaPenerbit = txtNama.getText();
Connection conn = Koneksi.getConnection();
        String sql = "SELECT Nama_Penerbit FROM Penerbit WHERE ID_Penerbit!='" + idPenerbit + "' AND Nama_Penerbit LIKE BINARY'" + namaPenerbit + "'";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Penerbit sudah ada\nSilahkan input Nama Penerbit yang berbeda",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                valid = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
        }

        return valid;
    }
 private String setIDPenerbit() {
    String urutan = null;
    Date now = new Date();
    SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");
    txtID.setEnabled(false);
    
    String sql = "SELECT RIGHT(ID_Penerbit, 3) AS Nomor "
            + "FROM Penerbit "
            + "WHERE ID_Penerbit LIKE 'TBT" + noFormat.format(now) + "%' "
            + "ORDER BY ID_Penerbit DESC "
            + "LIMIT 1";

    try (Connection conn = Koneksi.getConnection();
         PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {

        if (rs.next()) {
            int nomor = Integer.parseInt(rs.getString("Nomor")) + 1;
            urutan = "TBT" + noFormat.format(now) + String.format("%03d", nomor);
        } else {
            urutan = "TBT" + noFormat.format(now) + "001";
        }

    } catch (SQLException e) {
        Logger.getLogger(MenuKategori.class.getName()).log(Level.SEVERE, null, e);
    }

    return urutan;
}

    private void insertData() {
    String idPenerbit = txtID.getText();
    String namaPenerbit = txtNama.getText();
    String tempatTerbit = txtKota.getText();

    if (idPenerbit.isEmpty() || namaPenerbit.isEmpty() || tempatTerbit.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validasi nama penerbit unik
    if (!validasiNamaPenerbit(namaPenerbit)) {
        return;
    }

    try (Connection conn = Koneksi.getConnection()) {
        String sql = "INSERT INTO penerbit (ID_Penerbit, Nama_Penerbit, Kota_Terbit) VALUES (?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, idPenerbit);
            st.setString(2, namaPenerbit);
            st.setString(3, tempatTerbit);

            int rowInserted = st.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
                resetForm();
                loadData();
                showPanel();
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
    }
}


private boolean validasiNamaPenerbit(String namaPenerbit) {
    try (Connection conn = Koneksi.getConnection()) {
        String query = "SELECT * FROM penerbit WHERE Nama_Penerbit=?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, namaPenerbit);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Nama Penerbit sudah ada!", "Validasi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
    }
    return true;
}

   private void updateData() {
    String idPenerbit = txtID.getText();
    String namaPenerbit = txtNama.getText();
    String tempatTerbit = txtKota.getText();

    if (idPenerbit.isEmpty() || namaPenerbit.isEmpty() || tempatTerbit.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validasi nama penerbit unik saat pembaruan
    if (!validasiNamaPenerbit(idPenerbit, namaPenerbit)) {
        return;
    }

    try (Connection conn = Koneksi.getConnection()) {
        String sql = "UPDATE penerbit SET Nama_Penerbit=?, Kota_Terbit=? WHERE ID_Penerbit=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, namaPenerbit);
            st.setString(2, tempatTerbit);
            st.setString(3, idPenerbit);

            int rowUpdated = st.executeUpdate();
            if (rowUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui");
                resetForm();
                loadData();
                showPanel();
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
    }
}


private boolean validasiNamaPenerbit(String idPenerbit, String namaPenerbit) {
    try (Connection conn = Koneksi.getConnection()) {
        String query = "SELECT * FROM penerbit WHERE Nama_Penerbit=? AND ID_Penerbit != ?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, namaPenerbit);
            st.setString(2, idPenerbit);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Nama Penerbit sudah ada!", "Validasi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
    }
    return true;
}

    private void dataTabel() {
        PanelView.setVisible(false);
        PanelAdd.setVisible(true);

        int row = tblData.getSelectedRow();
        jLabel14.setText("Perbarui Data Penerbit");
       

        txtID.setText(tblData.getValueAt(row, 0).toString());
        txtNama.setText(tblData.getValueAt(row, 1).toString());
        txtKota.setText(tblData.getValueAt(row, 2).toString());

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
                String sql = "DELETE FROM penerbit WHERE ID_Penerbit=?";
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
                Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
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
            String sql = "SELECT * FROM penerbit WHERE Nama_Penerbit LIKE ? OR Kota_Terbit LIKE ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, "%" + kataKunci + "%");
                st.setString(2, "%" + kataKunci + "%");
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    String idPenerbit = rs.getString("ID_Penerbit");
                    String namaPenerbit = rs.getString("Nama_Penerbit");
                    String tempatTerbit = rs.getString("Kota_Terbit");

                    Object[] rowData = {idPenerbit, namaPenerbit, tempatTerbit};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenerbit.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
