package Grafik;

import Config.Koneksi;
import java.awt.Color;
import Grafik.ModelPieChart;
import Grafik.PieChart;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.UIManager;

/**
 *
 * @author raven
 */
public class PieChartSiswa extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public PieChartSiswa() {
        initComponents();
        
        getContentPane().setBackground(new Color(255, 255, 255));
        pieChart1.setChartType(PieChart.PeiChartType.DONUT_CHART);
          setSize(800, 600);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pieChart1 = new Grafik.PieChart();
        jLabel1 = new javax.swing.JLabel();
        comboYear = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboMonth = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pie Chart");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Tahun");

        comboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboYearActionPerformed(evt);
            }
        });

        jLabel2.setText("Bulan");

        comboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            Koneksi.getConnection();
            showYear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void comboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboYearActionPerformed
        if (comboYear.getSelectedIndex()>=0){
            int year = Integer.valueOf(comboYear.getSelectedItem(). toString());
            try{
                comboMonth.removeAllItems();
                showMonth(year);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_comboYearActionPerformed

    private void comboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonthActionPerformed
      if(comboMonth.getSelectedIndex()>=0){
          int year = Integer.valueOf(comboYear.getSelectedItem().toString());
          Model_Month month = (Model_Month) comboMonth.getSelectedItem();
          showData(year, month.getMonth());
      }
    }//GEN-LAST:event_comboMonthActionPerformed
    private void showYear() throws SQLException {
        String sql = "SELECT DISTINCT YEAR(TANGGAL_PEMINJAMAN) AS yearNumber\n" +
"FROM peminjaman;";
        Connection mysqlconfig = Koneksi.getConnection();
        PreparedStatement pst = mysqlconfig.prepareStatement(sql);
        ResultSet r = pst.executeQuery();

        while (r.next()) {
            int year = r.getInt("yearNumber");
            comboYear.addItem(year + "");
        }
        r.close();
        pst.close();    
    }

    private void showMonth(int year) throws SQLException{
     String sql = "SELECT\n" +
"    MONTHNAME(TANGGAL_PEMINJAMAN) AS monthText,\n" +
"    MONTH(TANGGAL_PEMINJAMAN) AS monthNumber\n" +
"FROM\n" +
"    peminjaman\n" +
"WHERE\n" +
"    YEAR(TANGGAL_PEMINJAMAN) = ?\n" +
"GROUP BY\n" +
"    monthNumber, monthText";
     
        Connection mysqlconfig = Koneksi.getConnection();     
        PreparedStatement pst = mysqlconfig.prepareStatement(sql);
           pst.setInt(1, year);
        ResultSet r = pst.executeQuery();

        while (r.next()) {
          String monthText = r.getString("MonthText");
          int month = r.getInt("monthNumber");
          comboMonth.addItem(new Model_Month(month, monthText));
        }
        r.close();
        pst.close();    
}
    
    private void showData(int year, int month){
       try{
           pieChart1.clearData();
           String sql = "SELECT\n" +
"    ANGGOTA.NAMA_ANGGOTA AS namaAnggota,\n" +
"    COUNT(PEMINJAMAN.ID_PEMINJAMAN) AS TotalPeminjaman\n" +
"FROM\n" +
"    PEMINJAMAN\n" +
"    JOIN ANGGOTA ON PEMINJAMAN.NISN = ANGGOTA.NISN\n" +
"WHERE\n" +
"    YEAR(PEMINJAMAN.TANGGAL_PEMINJAMAN) = ?\n" +
"    AND MONTH(PEMINJAMAN.TANGGAL_PEMINJAMAN) = ?\n" +
"GROUP BY\n" +
"    namaAnggota\n" +
"ORDER BY\n" +
"    namaAnggota;";
        Connection mysqlconfig = Koneksi.getConnection();     
        PreparedStatement pst = mysqlconfig.prepareStatement(sql);
           pst.setInt(1, year);
            pst.setInt(2, month);
        ResultSet r = pst.executeQuery();
        int index = 0;
           while (r.next()) {
               String namaProduk = r.getString(1);
               double values = r.getDouble(2);
               pieChart1.addData(new ModelPieChart(namaProduk, values, getColor(index++)));
        }
        r.close();
        pst.close();  
    }catch (Exception e){
    e.printStackTrace();
}
}
    private Color getColor (int index){
            Color[] color = new Color[]{new Color(139, 0, 0),new Color(0, 100, 0),new Color(0, 0, 139),new Color(128, 128, 0),
                new Color(75, 0, 130),new Color(255, 140, 0),new Color(0, 0, 128),new Color(101, 67, 33),
                new Color(105, 105, 105),new Color(0, 139, 139),new Color(139, 0, 139),new Color(0, 139, 139)};
        return color[index];
    }
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
                new PieChartSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> comboMonth;
    private javax.swing.JComboBox<String> comboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private Grafik.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
}
