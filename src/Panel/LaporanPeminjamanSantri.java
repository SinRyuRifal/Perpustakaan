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
import java.util.HashMap;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import main.FormLogin.GlobalVariables;

/**
 *
 * @author Rifal
 */
public class LaporanPeminjamanSantri extends javax.swing.JPanel {
private String loggedInUsername; 
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

    private final Connection conn;

    public LaporanPeminjamanSantri() {
        initComponents();
       loggedInUsername = GlobalVariables.loggedInUsername;
        
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

            }
        });

        loadDetailPeminjaman("");

        this.conn = Koneksi.getConnection();
       
        btnCancel.setVisible(false);

        Color headerColor = new Color(127, 140, 117);
        tblData.getTableHeader().setBackground(headerColor);
        tblDetail.getTableHeader().setBackground(headerColor);
        tblData.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblData.getSelectedRow();
                    if (selectedRow != -1) {
                        String selectedIdPeminjaman = tblData.getValueAt(selectedRow, 0).toString();
                        loadDetailPeminjaman(selectedIdPeminjaman);
                    }
                }
            }
        });
        loadPeminjaman();
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
        btnCancel = new rojerusan.RSMaterialButtonRectangle();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetail = new javax.swing.JTable();

        setLayout(new java.awt.CardLayout());

        PanelMain.setBackground(new java.awt.Color(255, 255, 255));
        PanelMain.setPreferredSize(new java.awt.Dimension(950, 590));
        PanelMain.setLayout(new java.awt.CardLayout());

        PanelView.setBackground(new java.awt.Color(255, 255, 255));
        PanelView.setPreferredSize(new java.awt.Dimension(950, 590));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setText("Laporan Peminjaman");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setText("Laporan Peminjaman");

        lb_iconAnggota.setBackground(new java.awt.Color(250, 250, 250));
        lb_iconAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/pngwing.com (43).png"))); // NOI18N

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

        btnCancel.setBackground(new java.awt.Color(153, 153, 153));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/13.png"))); // NOI18N
        btnCancel.setText("Batal");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pinjam.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setText("Detail Peminjaman");

        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Judul", "Judul", "Judul", "Judul"
            }
        ));
        tblDetail.setShowGrid(true);
        tblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetailMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDetail);

        javax.swing.GroupLayout PanelViewLayout = new javax.swing.GroupLayout(PanelView);
        PanelView.setLayout(PanelViewLayout);
        PanelViewLayout.setHorizontalGroup(
            PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelViewLayout.createSequentialGroup()
                .addGap(6, 6, 6)
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
                                .addComponent(jLabel9)))
                        .addGap(14, 14, 14))
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelViewLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(PanelViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelViewLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                            .addComponent(lb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        PanelViewLayout.setVerticalGroup(
            PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(50, 50, 50))
                    .addComponent(lb_iconAnggota))
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelViewLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelViewLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_data, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelMain.add(PanelView, "card2");

        add(PanelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyTyped
        // TODO add your handling code here:
        searchData();
    }//GEN-LAST:event_txt_searchKeyTyped

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        int baris = tblData.getSelectedRow();
        if (baris != -1) {

            String idpeminjaman = tblData.getValueAt(baris, 0).toString();

            loadDetailPeminjaman(idpeminjaman);

        }
     
        btnCancel.setVisible(true);
    }//GEN-LAST:event_tblDataMouseClicked

    private void tblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseClicked

    }//GEN-LAST:event_tblDetailMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        loadPeminjaman();
        showPanel();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelView;
    private rojerusan.RSMaterialButtonRectangle btnCancel;
    private javax.swing.JButton btn_before;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JComboBox<String> cbx_data;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_halaman;
    private javax.swing.JLabel lb_iconAnggota;
    private javax.swing.JTable tblData;
    private javax.swing.JTable tblDetail;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
 private void loadDetailPeminjaman(String selectedIdPeminjaman) {
        DefaultTableModel Model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Model.addColumn("ID Detail");
        Model.addColumn("ID Peminjaman");
        Model.addColumn("ID Buku");
        Model.addColumn("Status Pengembalian");

        tblDetail.setAutoCreateRowSorter(true);
        tblData.getTableHeader().setReorderingAllowed(false);
        try {
            int no = 1;
            String sql = "SELECT * FROM Detail_Peminjaman WHERE ID_Peminjaman = '" + selectedIdPeminjaman + "'";
            java.sql.Connection mysqlconfig = (Connection) Koneksi.getConnection();
            java.sql.Statement stm = mysqlconfig.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                Model.addRow(new Object[]{
                    res.getString("ID_DETAIL_PEMINJAMAN"),
                    res.getString("ID_PEMINJAMAN"),
                    res.getString("ID_BUKU"),
                    res.getString("STATUS_PENGEMBALIAN")});
            }
            tblDetail.setModel(Model);
            autoResizeAllColumns();

            for (int i = 0; i < Model.getColumnCount(); i++) {
                tblDetail.getColumnModel().getColumn(i).setCellEditor(null);
            }

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            for (int i = 0; i < Model.getColumnCount(); i++) {
                tblDetail.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        } catch (Exception e) {

        }
    }

    private void loadPeminjaman() {
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    model.addColumn("ID Peminjaman");
    model.addColumn("ID Penjaga");
    model.addColumn("NISN");
    model.addColumn("Tangal Peminjaman");
    model.addColumn("Tanggal Pengembalian");
    model.addColumn("Status Peminjaman");
    model.addColumn("Jumlah Pinjaman");

    tblData.setAutoCreateRowSorter(true);
    tblData.getTableHeader().setReorderingAllowed(false);
    try {
        int no = 1;
       
        String sql = "SELECT * FROM peminjaman WHERE NISN = '" + loggedInUsername + "'";
        java.sql.Connection mysqlconfig = (Connection) Koneksi.getConnection();
        java.sql.Statement stm = mysqlconfig.createStatement();
        java.sql.ResultSet res = stm.executeQuery(sql);
        while (res.next()) {
            model.addRow(new Object[]{
                res.getString("ID_Peminjaman"),
                res.getString("ID_Penjaga"),
                res.getString("NISN"),
                res.getString("Tanggal_Peminjaman"),
                res.getString("Tanggal_Pengembalian"),
                res.getString("Status_Peminjaman"),
                res.getString("Jumlah_Pinjaman")});
        }
        tblData.setModel(model);
        autoResizeAllColumns();

        for (int i = 0; i < model.getColumnCount(); i++) {
            tblData.getColumnModel().getColumn(i).setCellEditor(null);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            tblData.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    } catch (Exception e) {
        
    }
}


    private void showPanel() {
        PanelMain.removeAll();
        PanelMain.add(new LaporanPeminjamanSantri());
        PanelMain.repaint();
        PanelMain.revalidate();
    }

   private void searchData() {
    String kataKunci = txt_search.getText();
    DefaultTableModel model = (DefaultTableModel) tblData.getModel();
    model.setRowCount(0);

    try {
        
        String sql = "SELECT * FROM peminjaman WHERE NISN = ? AND (ID_PEMINJAMAN LIKE ? OR NISN LIKE ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, loggedInUsername);
            st.setString(2, "%" + kataKunci + "%");
            st.setString(3, "%" + kataKunci + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String IDPinjam = rs.getString("ID_Peminjaman");
                String IDJaga = rs.getString("ID_Penjaga");
                String Nisn = rs.getString("NISN");
                String tanggalPinjam = rs.getString("Tanggal_Peminjaman");
                String tanggalKembali = rs.getString("Tanggal_Pengembalian");
                String statusPinjam = rs.getString("Status_Peminjaman");
                String jumlahPinjam = rs.getString("Jumlah_Pinjaman");

                Object[] rowData = {IDPinjam, IDJaga, Nisn, tanggalPinjam, tanggalKembali, statusPinjam, jumlahPinjam};
                model.addRow(rowData);
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(LaporanPeminjamanSantri.class.getName()).log(Level.SEVERE, null, e);
    }
}
    private void deleteSelectedPeminjaman() {
        int selectedRow = tblData.getSelectedRow();

        if (selectedRow != -1) {
            Connection conn = null;

            try {
                int confirmation = JOptionPane.showConfirmDialog(this, "Yakin menghapus baris ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    String idPeminjaman = tblData.getValueAt(selectedRow, 0).toString();

                    conn = Koneksi.getConnection();
                    conn.setAutoCommit(false);

                    String deleteDetailPengembalianSql = "DELETE FROM DETAIL_PENGEMBALIAN WHERE ID_PENGEMBALIAN IN (SELECT ID_PENGEMBALIAN FROM PENGEMBALIAN WHERE ID_PEMINJAMAN = ?)";
                    try (PreparedStatement pstDeleteDetailPengembalian = conn.prepareStatement(deleteDetailPengembalianSql)) {
                        pstDeleteDetailPengembalian.setString(1, idPeminjaman);
                        pstDeleteDetailPengembalian.executeUpdate();
                    }
                   
                    String deletePengembalianSql = "DELETE FROM PENGEMBALIAN WHERE ID_PEMINJAMAN = ?";
                    try (PreparedStatement pstDeletePengembalian = conn.prepareStatement(deletePengembalianSql)) {
                        pstDeletePengembalian.setString(1, idPeminjaman);
                        pstDeletePengembalian.executeUpdate();
                    }

                  
                    String deleteDetailPeminjamanSql = "DELETE FROM DETAIL_PEMINJAMAN WHERE ID_PEMINJAMAN = ?";
                    try (PreparedStatement pstDeleteDetailPeminjaman = conn.prepareStatement(deleteDetailPeminjamanSql)) {
                        pstDeleteDetailPeminjaman.setString(1, idPeminjaman);
                        pstDeleteDetailPeminjaman.executeUpdate();
                    }

                  
                    String getStatusSql = "SELECT STATUS_PEMINJAMAN FROM PEMINJAMAN WHERE ID_PEMINJAMAN = ?";
                    try (PreparedStatement pstGetStatus = conn.prepareStatement(getStatusSql)) {
                        pstGetStatus.setString(1, idPeminjaman);
                        try (ResultSet rs = pstGetStatus.executeQuery()) {
                            if (rs.next()) {
                                String statusPeminjaman = rs.getString("STATUS_PEMINJAMAN");

                                if ("Sedang Berlangsung".equals(statusPeminjaman)) {
                                 
                                    String updateEntriBukuSql = "UPDATE ENTRY SET STATUS_KETERSEDIAAN = 'Tersedia' WHERE ID_BUKU IN (SELECT ID_BUKU FROM DETAIL_PEMINJAMAN WHERE ID_PEMINJAMAN = ?)";
                                    try (PreparedStatement pstUpdateEntriBuku = conn.prepareStatement(updateEntriBukuSql)) {
                                        pstUpdateEntriBuku.setString(1, idPeminjaman);
                                        pstUpdateEntriBuku.executeUpdate();
                                    }
                                }
                            }
                        }
                    }

                    // Delete PEMINJAMAN
                    String deletePeminjamanSql = "DELETE FROM PEMINJAMAN WHERE ID_PEMINJAMAN = ?";
                    try (PreparedStatement pstDeletePeminjaman = conn.prepareStatement(deletePeminjamanSql)) {
                        pstDeletePeminjaman.setString(1, idPeminjaman);
                        pstDeletePeminjaman.executeUpdate();
                    }

                    conn.commit();

                    JOptionPane.showMessageDialog(this, "Berhasil hapus data");

                    loadPeminjaman();
                    loadDetailPeminjaman(idPeminjaman);
                }
            } catch (SQLException ex) {
                try {
                    if (conn != null) {
                        conn.rollback();
                    }
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(LaporanPeminjamanSantri.class.getName()).log(Level.SEVERE, null, rollbackEx);
                }
                Logger.getLogger(LaporanPeminjamanSantri.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error saat penghapusan!");
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        Logger.getLogger(LaporanPeminjamanSantri.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih Baris!");
        }
    }

}
