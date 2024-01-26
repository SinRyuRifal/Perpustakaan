/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Panel;

import Config.Koneksi;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.renderer.category.BarRenderer;
import Grafik.GrafikAnggota;
import Grafik.GrafikDenda;
import Grafik.GrafikPeminjaman;
import Grafik.GrafikPengembalian;
import Grafik.GrafikPenjaga;
import Grafik.PieChartBuku;
import Grafik.PieChartSiswa;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Rifal
 */
public class Dashboard extends javax.swing.JPanel {

    private void updatejumlahAnggota() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT COUNT(*) AS jumlahanggota FROM Anggota";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int anggotapp = rs.getInt("jumlahanggota");
                lb_jumlahAnggota.setText(Integer.toString(anggotapp));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            closeResources(stmt, rs);
        }
    }

    private void updateTotalJudulBuku() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT COUNT(*) AS totaljudul FROM Buku";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int JudulBuku = rs.getInt("totaljudul");
                lb_judulBuku.setText(Integer.toString(JudulBuku));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, rs);
        }
    }

    private void updateJumlahKoleksi() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT COUNT(*) AS totalkoleksi FROM entry";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int totalKoleksi = rs.getInt("totalkoleksi");
                lb_totalpembelian.setText(Integer.toString(totalKoleksi));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, rs);
        }
    }

    private void updateJumlahPenjaga() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT COUNT(*) AS totalpenjaga FROM penjaga";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int totalPenjaga = rs.getInt("totalpenjaga");
                lb_jumlahPenjaga.setText(Integer.toString(totalPenjaga));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, rs);
        }
    }

    private void updateJumlahPeminjaman() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT COUNT(*) AS totalpinjam FROM peminjaman";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int totalPeminjaman = rs.getInt("totalpinjam");
                lb_peminjaman.setText(Integer.toString(totalPeminjaman));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, rs);
        }
    }

    private void updateJumlahPengembalian() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT COUNT(*) AS jumlahpengembalian FROM pengembalian";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int totalPengembalian = rs.getInt("jumlahpengembalian");
                lb_jumlahproduksi.setText(Integer.toString(totalPengembalian));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, rs);
        }
    }

    private void closeResources(PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates new form Form_Customer
     */
    private String getMonthName(int month) {
        String[] monthNames = {"", "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Ags", "Sep", "Okt", "Nov", "Des"};
        return monthNames[month];
    }

    public Dashboard() {
        initComponents();
        updatejumlahAnggota();
        updateTotalJudulBuku();
        updateJumlahKoleksi();
        updateJumlahPenjaga();
        updateJumlahPeminjaman();
        updateJumlahPengembalian();
updateTopBorrowers();
updateTopBorrowedBooks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        comboGrafik = new rojerusan.RSComboMetro();
        btnShow = new rojeru_san.RSButton();
        panelCustomTransparanGradien7 = new CustomSwing.PanelCustomTransparanGradien();
        lb_jumlahPenjaga = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelCustomTransparanGradien6 = new CustomSwing.PanelCustomTransparanGradien();
        lb_jumlahAnggota = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelCustomTransparanGradien5 = new CustomSwing.PanelCustomTransparanGradien();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lb_judulBuku = new javax.swing.JLabel();
        panelCustomTransparanGradien4 = new CustomSwing.PanelCustomTransparanGradien();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lb_jumlahproduksi = new javax.swing.JLabel();
        panelCustomTransparanGradien3 = new CustomSwing.PanelCustomTransparanGradien();
        lb_totalpembelian = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelCustomTransparanGradien1 = new CustomSwing.PanelCustomTransparanGradien();
        jLabel18 = new javax.swing.JLabel();
        lb_peminjaman = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panelCustom2 = new CustomSwing.PanelCustomTransparan();
        Buku1 = new javax.swing.JLabel();
        Buku2 = new javax.swing.JLabel();
        Buku3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        panelCustom1 = new CustomSwing.PanelCustomTransparan();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        top2 = new javax.swing.JLabel();
        top3 = new javax.swing.JLabel();
        top1 = new javax.swing.JLabel();
        jumlh1 = new javax.swing.JLabel();
        jumlh2 = new javax.swing.JLabel();
        jumlh3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.CardLayout());

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));
        Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboGrafik.setBackground(new java.awt.Color(0, 0, 0));
        comboGrafik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih", "Total Peminjaman", "Total Denda", "Total Pengembalian", "Total Anggota", "Tierlist Buku", "Tierlist Santri" }));
        comboGrafik.setColorArrow(new java.awt.Color(130, 65, 13));
        comboGrafik.setColorBorde(new java.awt.Color(130, 65, 13));
        comboGrafik.setColorFondo(new java.awt.Color(184, 132, 63));
        Dashboard.add(comboGrafik, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 300, 30));

        btnShow.setBackground(new java.awt.Color(184, 132, 63));
        btnShow.setText("Show Graphics");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });
        Dashboard.add(btnShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 680, 140, 30));

        panelCustomTransparanGradien7.setEndColor(new java.awt.Color(209, 253, 255));
        panelCustomTransparanGradien7.setRoundBottomLeft(20);
        panelCustomTransparanGradien7.setRoundBottomRight(20);
        panelCustomTransparanGradien7.setRoundTopLeft(20);
        panelCustomTransparanGradien7.setRoundTopRight(20);
        panelCustomTransparanGradien7.setStartColor(new java.awt.Color(253, 219, 146));

        lb_jumlahPenjaga.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_jumlahPenjaga.setText("0");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/icons8_user_male_30px.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("Jumlah Penjaga");

        javax.swing.GroupLayout panelCustomTransparanGradien7Layout = new javax.swing.GroupLayout(panelCustomTransparanGradien7);
        panelCustomTransparanGradien7.setLayout(panelCustomTransparanGradien7Layout);
        panelCustomTransparanGradien7Layout.setHorizontalGroup(
            panelCustomTransparanGradien7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien7Layout.createSequentialGroup()
                .addGroup(panelCustomTransparanGradien7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomTransparanGradien7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(panelCustomTransparanGradien7Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lb_jumlahPenjaga)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        panelCustomTransparanGradien7Layout.setVerticalGroup(
            panelCustomTransparanGradien7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelCustomTransparanGradien7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(38, 38, 38)
                .addComponent(lb_jumlahPenjaga)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        Dashboard.add(panelCustomTransparanGradien7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, -1, 170));

        panelCustomTransparanGradien6.setEndColor(new java.awt.Color(209, 253, 255));
        panelCustomTransparanGradien6.setRoundBottomLeft(20);
        panelCustomTransparanGradien6.setRoundBottomRight(20);
        panelCustomTransparanGradien6.setRoundTopLeft(20);
        panelCustomTransparanGradien6.setRoundTopRight(20);
        panelCustomTransparanGradien6.setStartColor(new java.awt.Color(253, 219, 146));

        lb_jumlahAnggota.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_jumlahAnggota.setText("0");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_people_30px.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Jumlah Anggota");

        javax.swing.GroupLayout panelCustomTransparanGradien6Layout = new javax.swing.GroupLayout(panelCustomTransparanGradien6);
        panelCustomTransparanGradien6.setLayout(panelCustomTransparanGradien6Layout);
        panelCustomTransparanGradien6Layout.setHorizontalGroup(
            panelCustomTransparanGradien6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien6Layout.createSequentialGroup()
                .addGroup(panelCustomTransparanGradien6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomTransparanGradien6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(panelCustomTransparanGradien6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lb_jumlahAnggota)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        panelCustomTransparanGradien6Layout.setVerticalGroup(
            panelCustomTransparanGradien6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomTransparanGradien6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelCustomTransparanGradien6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(lb_jumlahAnggota)
                .addGap(56, 56, 56))
        );

        Dashboard.add(panelCustomTransparanGradien6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        panelCustomTransparanGradien5.setEndColor(new java.awt.Color(209, 253, 255));
        panelCustomTransparanGradien5.setRoundBottomLeft(20);
        panelCustomTransparanGradien5.setRoundBottomRight(20);
        panelCustomTransparanGradien5.setRoundTopLeft(20);
        panelCustomTransparanGradien5.setRoundTopRight(20);
        panelCustomTransparanGradien5.setStartColor(new java.awt.Color(253, 219, 146));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Judul Buku");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/icons8_book_30px_1.png"))); // NOI18N

        lb_judulBuku.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_judulBuku.setText("0");

        javax.swing.GroupLayout panelCustomTransparanGradien5Layout = new javax.swing.GroupLayout(panelCustomTransparanGradien5);
        panelCustomTransparanGradien5.setLayout(panelCustomTransparanGradien5Layout);
        panelCustomTransparanGradien5Layout.setHorizontalGroup(
            panelCustomTransparanGradien5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien5Layout.createSequentialGroup()
                .addGroup(panelCustomTransparanGradien5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomTransparanGradien5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(panelCustomTransparanGradien5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lb_judulBuku)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        panelCustomTransparanGradien5Layout.setVerticalGroup(
            panelCustomTransparanGradien5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelCustomTransparanGradien5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(lb_judulBuku)
                .addGap(45, 45, 45))
        );

        Dashboard.add(panelCustomTransparanGradien5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));

        panelCustomTransparanGradien4.setEndColor(new java.awt.Color(209, 253, 255));
        panelCustomTransparanGradien4.setRoundBottomLeft(20);
        panelCustomTransparanGradien4.setRoundBottomRight(20);
        panelCustomTransparanGradien4.setRoundTopLeft(20);
        panelCustomTransparanGradien4.setRoundTopRight(20);
        panelCustomTransparanGradien4.setStartColor(new java.awt.Color(253, 219, 146));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Pengembalian");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/icons8_Return_Book_30px.png"))); // NOI18N

        lb_jumlahproduksi.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_jumlahproduksi.setText("0");

        javax.swing.GroupLayout panelCustomTransparanGradien4Layout = new javax.swing.GroupLayout(panelCustomTransparanGradien4);
        panelCustomTransparanGradien4.setLayout(panelCustomTransparanGradien4Layout);
        panelCustomTransparanGradien4Layout.setHorizontalGroup(
            panelCustomTransparanGradien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien4Layout.createSequentialGroup()
                .addGroup(panelCustomTransparanGradien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomTransparanGradien4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(panelCustomTransparanGradien4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lb_jumlahproduksi)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        panelCustomTransparanGradien4Layout.setVerticalGroup(
            panelCustomTransparanGradien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelCustomTransparanGradien4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(38, 38, 38)
                .addComponent(lb_jumlahproduksi)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        Dashboard.add(panelCustomTransparanGradien4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        panelCustomTransparanGradien3.setEndColor(new java.awt.Color(209, 253, 255));
        panelCustomTransparanGradien3.setRoundBottomLeft(20);
        panelCustomTransparanGradien3.setRoundBottomRight(20);
        panelCustomTransparanGradien3.setRoundTopLeft(20);
        panelCustomTransparanGradien3.setRoundTopRight(20);
        panelCustomTransparanGradien3.setStartColor(new java.awt.Color(253, 219, 146));

        lb_totalpembelian.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_totalpembelian.setText("0");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/icons8_book_30px.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("Total Koleksi");

        javax.swing.GroupLayout panelCustomTransparanGradien3Layout = new javax.swing.GroupLayout(panelCustomTransparanGradien3);
        panelCustomTransparanGradien3.setLayout(panelCustomTransparanGradien3Layout);
        panelCustomTransparanGradien3Layout.setHorizontalGroup(
            panelCustomTransparanGradien3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelCustomTransparanGradien3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomTransparanGradien3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCustomTransparanGradien3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lb_totalpembelian)))
                .addContainerGap())
        );
        panelCustomTransparanGradien3Layout.setVerticalGroup(
            panelCustomTransparanGradien3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelCustomTransparanGradien3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(37, 37, 37)
                .addComponent(lb_totalpembelian)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        Dashboard.add(panelCustomTransparanGradien3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 310, 170));

        panelCustomTransparanGradien1.setEndColor(new java.awt.Color(209, 253, 255));
        panelCustomTransparanGradien1.setRoundBottomLeft(20);
        panelCustomTransparanGradien1.setRoundBottomRight(20);
        panelCustomTransparanGradien1.setRoundTopLeft(20);
        panelCustomTransparanGradien1.setRoundTopRight(20);
        panelCustomTransparanGradien1.setStartColor(new java.awt.Color(253, 219, 146));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setText("Peminjaman");

        lb_peminjaman.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_peminjaman.setText("0");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/icons8_Borrow_Book_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelCustomTransparanGradien1Layout = new javax.swing.GroupLayout(panelCustomTransparanGradien1);
        panelCustomTransparanGradien1.setLayout(panelCustomTransparanGradien1Layout);
        panelCustomTransparanGradien1Layout.setHorizontalGroup(
            panelCustomTransparanGradien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomTransparanGradien1Layout.createSequentialGroup()
                .addGroup(panelCustomTransparanGradien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomTransparanGradien1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addGroup(panelCustomTransparanGradien1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lb_peminjaman)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        panelCustomTransparanGradien1Layout.setVerticalGroup(
            panelCustomTransparanGradien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomTransparanGradien1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(panelCustomTransparanGradien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addGap(36, 36, 36)
                .addComponent(lb_peminjaman)
                .addGap(47, 47, 47))
        );

        Dashboard.add(panelCustomTransparanGradien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 300, 170));

        panelCustom2.setBackground(new java.awt.Color(255, 149, 101));
        panelCustom2.setEndColor(new java.awt.Color(175, 241, 218));
        panelCustom2.setOpacity(200);
        panelCustom2.setRoundBottomLeft(20);
        panelCustom2.setRoundBottomRight(20);
        panelCustom2.setRoundTopLeft(20);
        panelCustom2.setRoundTopRight(20);
        panelCustom2.setStartColor(new java.awt.Color(249, 234, 143));

        Buku1.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 14)); // NOI18N
        Buku1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Buku1.setText("1");

        Buku2.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 14)); // NOI18N
        Buku2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Buku2.setText("2");

        Buku3.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 14)); // NOI18N
        Buku3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Buku3.setText("3");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setText("Leaderboard Buku");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/icons8_crown_30px.png"))); // NOI18N

        javax.swing.GroupLayout panelCustom2Layout = new javax.swing.GroupLayout(panelCustom2);
        panelCustom2.setLayout(panelCustom2Layout);
        panelCustom2Layout.setHorizontalGroup(
            panelCustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustom2Layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel21)
                .addGap(106, 106, 106))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustom2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Buku2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Buku3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Buku1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCustom2Layout.setVerticalGroup(
            panelCustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustom2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCustom2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel21)))
                .addGap(34, 34, 34)
                .addComponent(Buku1)
                .addGap(43, 43, 43)
                .addComponent(Buku2)
                .addGap(45, 45, 45)
                .addComponent(Buku3)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        Dashboard.add(panelCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, -1, 300));

        panelCustom1.setBackground(new java.awt.Color(0, 255, 51));
        panelCustom1.setEndColor(new java.awt.Color(175, 241, 218));
        panelCustom1.setOpacity(200);
        panelCustom1.setRoundBottomLeft(20);
        panelCustom1.setRoundBottomRight(20);
        panelCustom1.setRoundTopLeft(20);
        panelCustom1.setRoundTopRight(20);
        panelCustom1.setStartColor(new java.awt.Color(249, 234, 143));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setText("Leaderboard Santri");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/icons8_crown_30px.png"))); // NOI18N

        top2.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 18)); // NOI18N
        top2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top2.setText("Nama 2");

        top3.setBackground(new java.awt.Color(255, 255, 255));
        top3.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 18)); // NOI18N
        top3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top3.setText("Nama 3");

        top1.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 18)); // NOI18N
        top1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        top1.setText("Nama 1");

        jumlh1.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 18)); // NOI18N
        jumlh1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jumlh1.setText("Jumlah 1");

        jumlh2.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 18)); // NOI18N
        jumlh2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jumlh2.setText("Jumlah 2");

        jumlh3.setBackground(new java.awt.Color(255, 255, 255));
        jumlh3.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 18)); // NOI18N
        jumlh3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jumlh3.setText("Jumlah 3");

        javax.swing.GroupLayout panelCustom1Layout = new javax.swing.GroupLayout(panelCustom1);
        panelCustom1.setLayout(panelCustom1Layout);
        panelCustom1Layout.setHorizontalGroup(
            panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom1Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel19)
                .addGap(104, 104, 104))
            .addGroup(panelCustom1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(top1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(top2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(top3, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jumlh1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jumlh2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jumlh3, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(30, 30, 30))
        );
        panelCustom1Layout.setVerticalGroup(
            panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCustom1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19)))
                .addGroup(panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustom1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(top1)
                        .addGap(44, 44, 44)
                        .addComponent(top2)
                        .addGap(44, 44, 44)
                        .addComponent(top3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustom1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jumlh1)
                        .addGap(44, 44, 44)
                        .addComponent(jumlh2)
                        .addGap(44, 44, 44)
                        .addComponent(jumlh3)
                        .addGap(49, 49, 49))))
        );

        Dashboard.add(panelCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 470, 300));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/5031659.jpg"))); // NOI18N
        Dashboard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1373, 813));

        mainPanel.add(Dashboard, "card2");

        add(mainPanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        String selectedGrafik = (String) comboGrafik.getSelectedItem();

        if (selectedGrafik.equals("Pilih")) {
            JOptionPane.showMessageDialog(this, "Pilih grafik yang ingin ditampilkan.", "Pilihan Invalid", JOptionPane.WARNING_MESSAGE);
            return;
        }


        switch (selectedGrafik) {
            case "Total Peminjaman":
            showFrame(new GrafikPeminjaman());
            break;
            case "Total Denda":
            showFrame(new GrafikDenda());
            break;
            case "Total Pengembalian":
            showFrame(new GrafikPengembalian());
            break;
            case "Total Anggota":
            showFrame(new GrafikAnggota());
            break;
            case "Total Penjaga":
            showFrame(new GrafikPenjaga());
            break;
            case "Tierlist Buku":
            showFrame(new PieChartBuku());
            break;
            case "Tierlist Santri":
            showFrame(new PieChartSiswa());
            break;
            
            default:

            break;
        }
    }//GEN-LAST:event_btnShowActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Buku1;
    private javax.swing.JLabel Buku2;
    private javax.swing.JLabel Buku3;
    private javax.swing.JPanel Dashboard;
    private rojeru_san.RSButton btnShow;
    private rojerusan.RSComboMetro comboGrafik;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jumlh1;
    private javax.swing.JLabel jumlh2;
    private javax.swing.JLabel jumlh3;
    private javax.swing.JLabel lb_judulBuku;
    private javax.swing.JLabel lb_jumlahAnggota;
    private javax.swing.JLabel lb_jumlahPenjaga;
    private javax.swing.JLabel lb_jumlahproduksi;
    private javax.swing.JLabel lb_peminjaman;
    private javax.swing.JLabel lb_totalpembelian;
    private javax.swing.JPanel mainPanel;
    private CustomSwing.PanelCustomTransparan panelCustom1;
    private CustomSwing.PanelCustomTransparan panelCustom2;
    private CustomSwing.PanelCustomTransparanGradien panelCustomTransparanGradien1;
    private CustomSwing.PanelCustomTransparanGradien panelCustomTransparanGradien3;
    private CustomSwing.PanelCustomTransparanGradien panelCustomTransparanGradien4;
    private CustomSwing.PanelCustomTransparanGradien panelCustomTransparanGradien5;
    private CustomSwing.PanelCustomTransparanGradien panelCustomTransparanGradien6;
    private CustomSwing.PanelCustomTransparanGradien panelCustomTransparanGradien7;
    private javax.swing.JLabel top1;
    private javax.swing.JLabel top2;
    private javax.swing.JLabel top3;
    // End of variables declaration//GEN-END:variables
 
    private void showFrame(JFrame frame) {
    frame.setVisible(true);
}
    private void updateTopBorrowedBooks() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Koneksi.getConnection();
        String query = "SELECT ID_BUKU, COUNT(*) AS totalBorrowed "
                + "FROM detail_peminjaman "
                + "GROUP BY ID_BUKU "
                + "ORDER BY totalBorrowed DESC "
                + "LIMIT 3";

        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        int count = 1;
        while (rs.next()) {
            String bookId = rs.getString("ID_BUKU");
            String ISBN = getBookISBN(bookId);
            String bookTitle = getBookTitle(ISBN);

            if (count == 1) {
                Buku1.setText(bookTitle);
            } else if (count == 2) {
                Buku2.setText(bookTitle);
            } else if (count == 3) {
                Buku3.setText(bookTitle);
            }

            count++;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(stmt, rs);
    }
}

private String getBookISBN(String bookId) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String ISBN = "";

    try {
        conn = Koneksi.getConnection();
        String query = "SELECT ISBN FROM entry WHERE ID_BUKU = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, bookId);

        rs = stmt.executeQuery();

        if (rs.next()) {
            ISBN = rs.getString("ISBN");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(stmt, rs);
    }

    return ISBN;
}

private String getBookTitle(String ISBN) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String bookTitle = "";

    try {
        conn = Koneksi.getConnection();
        String query = "SELECT JUDUL_BUKU FROM buku WHERE ISBN = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, ISBN);

        rs = stmt.executeQuery();

        if (rs.next()) {
            bookTitle = rs.getString("JUDUL_BUKU");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(stmt, rs);
    }

    return bookTitle;
}
  private void updateTopBorrowers() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Koneksi.getConnection();
        String query = "SELECT NISN, COUNT(*) AS totalBorrowed "
                + "FROM peminjaman "
                + "GROUP BY NISN "
                + "ORDER BY totalBorrowed DESC "
                + "LIMIT 3";

        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        int count = 1;
        while (rs.next()) {
            String NISN = rs.getString("NISN");
            String studentName = getStudentName(NISN);
            int totalBorrowed = rs.getInt("totalBorrowed");

          
            if (count == 1) {
                top1.setText(studentName);
                jumlh1.setText(Integer.toString(totalBorrowed));
            } else if (count == 2) {
                top2.setText(studentName);
                jumlh2.setText(Integer.toString(totalBorrowed));
            } else if (count == 3) {
                top3.setText(studentName);
                jumlh3.setText(Integer.toString(totalBorrowed));
            }

            count++;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(stmt, rs);
    }
}


    private String getStudentName(String NISN) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String studentName = "";

        try {
            conn = Koneksi.getConnection();
            String query = "SELECT NAMA_ANGGOTA FROM anggota WHERE NISN = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, NISN);

            rs = stmt.executeQuery();

            if (rs.next()) {
                studentName = rs.getString("NAMA_ANGGOTA");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, rs);
        }

        return studentName;
    }
}
