/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Panel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import Config.Koneksi;
import java.awt.Color;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Dialog_DataUnit extends javax.swing.JDialog {
 
    protected void loadUnitbyIDPeminjaman(String kodeJenis) {
    DefaultTableModel Model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };

   Model.addColumn("ID Buku");
    Model.addColumn("ISBN");
    Model.addColumn("Judul Buku");

    tblDialog.setAutoCreateRowSorter(true); 

    try {
        
        String sql = "SELECT dp.ID_BUKU, b.ISBN, b.JUDUL_BUKU\n" +
"FROM detail_peminjaman dp\n" +
"JOIN entry e ON dp.ID_BUKU = e.ID_BUKU\n" +
"JOIN buku b ON e.ISBN = b.ISBN\n" +
"JOIN peminjaman p ON dp.ID_PEMINJAMAN = p.ID_PEMINJAMAN\n" +
"WHERE p.ID_PEMINJAMAN =  ?";
        Connection postgresqlConfig = Koneksi.getConnection();
        PreparedStatement pst = postgresqlConfig.prepareStatement(sql);
        pst.setString(1, kodeJenis);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Model.addRow(new Object[]{
                     rs.getString("ID_BUKU"),
                rs.getString("ISBN"),
                rs.getString("JUDUL_BUKU")
              
            });
        }

        tblDialog.setModel(Model);
        autoResizeAllColumns();

        
        for (int i = 0; i < Model.getColumnCount(); i++) {
            tblDialog.getColumnModel().getColumn(i).setCellEditor(null);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < Model.getColumnCount(); i++) {
            tblDialog.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    } catch (Exception e) {
        e.printStackTrace(); 
    }
}
    
    
    protected void loadUnitbyISBN(String kodeJenis) {
    DefaultTableModel Model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };

   Model.addColumn("ID Buku");
    Model.addColumn("ISBN");
    Model.addColumn("Bentuk FIsik");
        Model.addColumn("Status Ketersediaan");

    tblDialog.setAutoCreateRowSorter(true); 

    try {
        
        String sql = "SELECT * FROM ENTRY WHERE ISBN = ? AND status_ketersediaan = 'Tersedia' ";
        Connection postgresqlConfig = Koneksi.getConnection();
        PreparedStatement pst = postgresqlConfig.prepareStatement(sql);
        pst.setString(1, kodeJenis);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Model.addRow(new Object[]{
                     rs.getString("ID_BUKU"),
                rs.getString("ISBN"),
                rs.getString("BENTUK_FISIK"),
                 rs.getString("STATUS_KETERSEDIAAN")
            });
        }

        tblDialog.setModel(Model);
        autoResizeAllColumns();

        
        for (int i = 0; i < Model.getColumnCount(); i++) {
            tblDialog.getColumnModel().getColumn(i).setCellEditor(null);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < Model.getColumnCount(); i++) {
            tblDialog.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    } catch (Exception e) {
        e.printStackTrace(); 
    }
}
private boolean unitSelected = false;
    private String selectedidBuku = null;
    
    
    public boolean isunitSelected() {
        return unitSelected;
    }

    
    public String getselectedidBuku() {
        return selectedidBuku;
    }

    
 

    private void autoResizeAllColumns() {
        int columns = tblDialog.getColumnCount();
        for (int i = 0; i < columns; i++) {
            TableColumn column = tblDialog.getColumnModel().getColumn(i);
            int width = (int) tblDialog.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(tblDialog, column.getHeaderValue(), false, false, -1, i)
                    .getPreferredSize().getWidth();
            for (int row = 0; row < tblDialog.getRowCount(); row++) {
                int preferedWidth = (int) tblDialog.getCellRenderer(row, i)
                        .getTableCellRendererComponent(tblDialog, tblDialog.getValueAt(row, i), false, false, row, i)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            column.setPreferredWidth(width);
        }
    }

   private void loadData() {
    DefaultTableModel Model2 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    Model2.addColumn("ID Buku");
    Model2.addColumn("ISBN");
    Model2.addColumn("Bentuk FIsik");
        Model2.addColumn("Status Ketersediaan");


    tblDialog.setAutoCreateRowSorter(true);
    autoResizeAllColumns(); 

    try {
        String sql = "SELECT * FROM ENTRY WHERE STATUS_KETERSEDIAAN = 'Tersedia'";
        Connection postgresqlConfig = Koneksi.getConnection();
        PreparedStatement pst = postgresqlConfig.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Model2.addRow(new Object[]{
                rs.getString("ID_BUKU"),
                rs.getString("ISBN"),
                rs.getString("BENTUK_FISIK"),
                 rs.getString("STATUS_KETERSEDIAAN")
                
            });
        }
        tblDialog.setModel(Model2);

     
        for (int i = 0; i < Model2.getColumnCount(); i++) {
            tblDialog.getColumnModel().getColumn(i).setCellEditor(null);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < Model2.getColumnCount(); i++) {
            tblDialog.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private void filterData(String keyword, String selectedField) {
    DefaultTableModel mesinModel = (DefaultTableModel) tblDialog.getModel();
    mesinModel.setRowCount(0);

    String sql = "SELECT * FROM ENTRY WHERE ";

    switch (selectedField) {
        case "ISBN":
            sql += "ISBN LIKE ?";
            break;
        case "ID Buku":
            sql += "ID_BUKU LIKE ?";
            break;
      
      
        default:
            return;
    }

    try (Connection conn = Koneksi.getConnection();
         PreparedStatement stm = conn.prepareStatement(sql)) {

        stm.setString(1, "%" + keyword + "%");

        try (ResultSet res = stm.executeQuery()) {
            int no = 1;
            while (res.next()) {
                mesinModel.addRow(new Object[]{
          
                        res.getString("ID_BUKU"),
                res.getString("ISBN"),
                res.getString("BENTUK_FISIK"),
                 res.getString("STATUS_KETERSEDIAAN")
                });
            }
            autoResizeAllColumns(); 
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    /**
     * Creates new form Dialog_DataMesin
     */
    public Dialog_DataUnit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         tblDialog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    doubleClickAction();
                }
            }
        });

        Color headerColor = new Color(181,199,151);
        tblDialog.getTableHeader().setBackground(headerColor);
        loadData();
        getRootPane().setDefaultButton(btn_pilih);
              txt_cari.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        String selectedField = (String) cb_cari.getSelectedItem();
        filterData(txt_cari.getText(), selectedField);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String selectedField = (String) cb_cari.getSelectedItem();
        filterData( txt_cari.getText(), selectedField);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDialog = new javax.swing.JTable();
        btn_pilih = new javax.swing.JButton();
        cb_cari = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(181, 199, 151));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Data Unit Buku");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tblDialog.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDialogMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDialog);

        btn_pilih.setText("Pilih");
        btn_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihActionPerformed(evt);
            }
        });

        cb_cari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ISBN", "ID Buku" }));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_database_30px.png"))); // NOI18N

        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pilih, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btn_pilih)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDialogMouseClicked
        
    }//GEN-LAST:event_tblDialogMouseClicked

    private void btn_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihActionPerformed
        
        int selectedRow = tblDialog.getSelectedRow();
        if (selectedRow != -1) {
           
            selectedidBuku = tblDialog.getValueAt(selectedRow, 0).toString(); 
           
           
            unitSelected = true;

           
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_btn_pilihActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
       
    }//GEN-LAST:event_txt_cariActionPerformed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("TextComponent.arc", 10);
            UIManager.put("Button.arc", 20);
            UIManager.put("Component.arc", 20);
            UIManager.put("ProgressBar.arc", 20);
            UIManager.put("Component.arrowType", "chevron");
            UIManager.put("Component.innerFocusWidth", 0);
            UIManager.put("Button.innerFocusWidth", 0);
            UIManager.put("ScrollBar.trackArc", 999);
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
            UIManager.put("ScrollBar.track", new Color(0xe0e0e0));

        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialog_DataUnit dialog = new Dialog_DataUnit(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pilih;
    private javax.swing.JComboBox<String> cb_cari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDialog;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
    private void doubleClickAction() {
        int selectedRow = tblDialog.getSelectedRow();
        if (selectedRow != -1) {
            selectedidBuku = tblDialog.getValueAt(selectedRow, 0).toString();
            unitSelected = true;
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
        }
    }
}
