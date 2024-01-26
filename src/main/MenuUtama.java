/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;
import Config.Koneksi;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Panel.Dashboard;
import Panel.LaporanPeminjaman;
import Panel.LaporanPengembalian;
import Panel.MenuKategori;
import Panel.MenuAnggota;
import Panel.MenuBuku;
import Panel.MenuPenerbit;
import Panel.MenuAkun;
import Panel.MenuEntry;
import Panel.MenuInventaris;
import Panel.MenuPeminjaman;
import Panel.MenuPengembalian;
import Panel.MenuPenjaga;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.sql.Blob;
import java.sql.SQLException;
/**
 *
 * @author Rifal
 */
//Muhammad Rifqi Naufal Irsyad (09010622010)
//MOCHAMMAD NASRUDDIN ZUHRI (09040622064)
//REYHAN DANY  (09040622076)
//RICO RAHMAT H(09020622041)
public class MenuUtama extends javax.swing.JFrame {
private Timer timer;
private String loggedInUsername; 
private FormLogin formLogin;

private void showDayDateTime(){
    Calendar calendar = Calendar.getInstance();
    Date now = new Date();
    SimpleDateFormat formatHari = new SimpleDateFormat("EEEE",new Locale("in","ID"));
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
    String hari = formatHari.format(calendar.getTime());
    String dateTime = dateFormat.format(now);
    lb_tanggal.setText(hari +", "+dateTime);
}    


private ImageIcon getResizedImageIcon(String loggedInUsername, int targetWidth, int targetHeight) {
    ImageIcon resizedImageIcon = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
     
        connection = Koneksi.getConnection();

        
        String sql = "SELECT Gambar FROM Penjaga WHERE ID_Penjaga = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, loggedInUsername);

        
        resultSet = preparedStatement.executeQuery();

       
        if (resultSet.next()) {
         
            Blob blob = resultSet.getBlob("Gambar");

            if (blob != null) {
               
                byte[] imageData = blob.getBytes(1, (int) blob.length());
                ImageIcon originalImageIcon = new ImageIcon(imageData);

               
                Image originalImage = originalImageIcon.getImage();
                Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                resizedImageIcon = new ImageIcon(resizedImage);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    return resizedImageIcon;
}

private void setResizedGambarIcon(int targetWidth, int targetHeight) {
    ImageIcon resizedImageIcon = getResizedImageIcon(loggedInUsername, targetWidth, targetHeight);

    if (resizedImageIcon != null) {
        lbGambar.setIcon(resizedImageIcon);
        lbGambar.setHorizontalAlignment(SwingConstants.CENTER);
        lbGambar.setVerticalAlignment(SwingConstants.CENTER);
        lbGambar.setHorizontalTextPosition(SwingConstants.CENTER);
        lbGambar.setVerticalTextPosition(SwingConstants.CENTER);
    }
}
    public MenuUtama() {
        initComponents();
         
          formLogin = new FormLogin();
       loggedInUsername = FormLogin.GlobalVariables.loggedInUsername;
        formLogin.setLoggedInUsername(loggedInUsername);
     String namaPenjaga = formLogin.getNamaPenjaga();
        jLabel3.setText(loggedInUsername + " - " + namaPenjaga);
        this.setExtendedState(MAXIMIZED_BOTH);
 setResizedGambarIcon(60, 70);
        execute();
        timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 showDayDateTime();
            }
        });
               timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupAkses = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        gradient1 = new main.Gradient();
        lb_tanggal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbGambar = new javax.swing.JLabel();
        pn_sidebar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pn_menu = new javax.swing.JPanel();
        pn_content = new javax.swing.JPanel();
        pn_utama = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        gradient1.setBackground(new java.awt.Color(250, 231, 201));
        gradient1.setColorEnd(new java.awt.Color(250, 231, 201));
        gradient1.setColorStart(new java.awt.Color(123, 172, 110));

        lb_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_tanggal.setText("Hari, tanggal, dan waktu");

        jLabel1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/422-SMA_KHADIJAH (1)_1.png"))); // NOI18N
        jLabel1.setText("SMA Khadijah");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ID Penjaga");

        lbGambar.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbGambar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/akun.png"))); // NOI18N
        lbGambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout gradient1Layout = new javax.swing.GroupLayout(gradient1);
        gradient1.setLayout(gradient1Layout);
        gradient1Layout.setHorizontalGroup(
            gradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradient1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
                .addGroup(gradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        gradient1Layout.setVerticalGroup(
            gradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradient1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(gradient1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_tanggal))
                    .addComponent(lbGambar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pn_sidebar.setBackground(new java.awt.Color(255, 255, 255));
        pn_sidebar.setPreferredSize(new java.awt.Dimension(250, 495));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        pn_menu.setBackground(new java.awt.Color(255, 255, 255));
        pn_menu.setLayout(new javax.swing.BoxLayout(pn_menu, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(pn_menu);

        javax.swing.GroupLayout pn_sidebarLayout = new javax.swing.GroupLayout(pn_sidebar);
        pn_sidebar.setLayout(pn_sidebarLayout);
        pn_sidebarLayout.setHorizontalGroup(
            pn_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        pn_sidebarLayout.setVerticalGroup(
            pn_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        getContentPane().add(pn_sidebar, java.awt.BorderLayout.LINE_START);

        pn_content.setBackground(new java.awt.Color(123, 172, 110));

        pn_utama.setBackground(new java.awt.Color(255, 255, 255));
        pn_utama.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pn_contentLayout = new javax.swing.GroupLayout(pn_content);
        pn_content.setLayout(pn_contentLayout);
        pn_contentLayout.setHorizontalGroup(
            pn_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_contentLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
        );
        pn_contentLayout.setVerticalGroup(
            pn_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_utama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        getContentPane().add(pn_content, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(846, 613));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      
        pn_utama.add(new content_bg());
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_formWindowOpened

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("TextComponent.arc", 10);
        UIManager.put("Component.arc", 10);
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
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.Gradient gradient1;
    private javax.swing.ButtonGroup grupAkses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbGambar;
    private javax.swing.JLabel lb_tanggal;
    private javax.swing.JPanel pn_content;
    private javax.swing.JPanel pn_menu;
    private javax.swing.JPanel pn_sidebar;
    private javax.swing.JPanel pn_utama;
    // End of variables declaration//GEN-END:variables

    private void execute() {
        ImageIcon iconMaster = new ImageIcon(getClass().getResource("/img/mas_Master.png"));
         ImageIcon iconkeuangan = new ImageIcon(getClass().getResource("/img/keuangan.png"));
        ImageIcon iconKaryawan = new ImageIcon(getClass().getResource("/img/mas_Karyawan.png"));
        ImageIcon iconGaji = new ImageIcon(getClass().getResource("/img/mas_Gaji.png"));
        ImageIcon iconTransaction = new ImageIcon(getClass().getResource("/img/mas_TransGaji.png"));
        ImageIcon iconKepegawaian = new ImageIcon(getClass().getResource("/img/mas_Kepegawaian.png"));
        ImageIcon iconOperasional = new ImageIcon(getClass().getResource("/img/operational.png"));
        ImageIcon iconMesin = new ImageIcon(getClass().getResource("/img/mesin.png"));
        ImageIcon iconProduksi = new ImageIcon(getClass().getResource("/img/production.png"));
        ImageIcon iconDetailMesin = new ImageIcon(getClass().getResource("/img/detail.png"));
        ImageIcon iconBarang = new ImageIcon(getClass().getResource("/img/mas_Barang.png"));
        ImageIcon iconProduk = new ImageIcon(getClass().getResource("/img/produk.png"));
        ImageIcon iconBahanBaku = new ImageIcon(getClass().getResource("/img/bahan.png"));
        ImageIcon iconStock = new ImageIcon(getClass().getResource("/img/stock.png"));
        ImageIcon iconKeuangan = new ImageIcon(getClass().getResource("/img/keuangan.png"));
        ImageIcon iconReport = new ImageIcon(getClass().getResource("/img/report.png"));
        ImageIcon iconHome = new ImageIcon(getClass().getResource("/icon/homepp.png"));
        ImageIcon iconLogout = new ImageIcon(getClass().getResource("/img/logout.png"));
        ImageIcon iconSupplier = new ImageIcon(getClass().getResource("/img/supplier.png"));
        ImageIcon iconPlusOrder = new ImageIcon(getClass().getResource("/img/tambahOrder.png"));
        ImageIcon iconCustomer = new ImageIcon(getClass().getResource("/img/customer.png"));
        ImageIcon iconOrder = new ImageIcon(getClass().getResource("/img/order.png"));
        ImageIcon iconDetailOrder = new ImageIcon(getClass().getResource("/img/detail2.png"));
        ImageIcon iconJenises = new ImageIcon(getClass().getResource("/img/jenis.png"));
          ImageIcon iconKulakan = new ImageIcon(getClass().getResource("/img/Kulakan.png"));
         ImageIcon iconDetKulak = new ImageIcon(getClass().getResource("/img/DetKulak.png"));
        ImageIcon iconberkulak = new ImageIcon(getClass().getResource("/img/berkulak.png"));
        ImageIcon iconDashboard = new ImageIcon(getClass().getResource("/icon/dashboard.png"));
       ImageIcon iconBuku = new ImageIcon(getClass().getResource("/icon/bookpp.png"));
       ImageIcon iconList = new ImageIcon(getClass().getResource("/icon/listpp.png"));
       ImageIcon iconSantri = new ImageIcon(getClass().getResource("/icon/reading.png"));
      ImageIcon iconPenerbit = new ImageIcon(getClass().getResource("/icon/penerbit.png"));
      ImageIcon iconKategori = new ImageIcon(getClass().getResource("/icon/kategori.png"));
       ImageIcon iconTransaksi = new ImageIcon(getClass().getResource("/icon/Transaksi.png"));
       ImageIcon iconLaporan = new ImageIcon(getClass().getResource("/icon/laporan.png"));
       ImageIcon iconPinjam = new ImageIcon(getClass().getResource("/icon/pinjam.png"));
       ImageIcon iconRetrun = new ImageIcon(getClass().getResource("/icon/mengembalikan.png"));      
       ImageIcon iconMember = new ImageIcon(getClass().getResource("/icon/member.png"));      
       ImageIcon iconPerpus = new ImageIcon(getClass().getResource("/icon/perpus.png"));      
        ImageIcon iconPenjaga = new ImageIcon(getClass().getResource("/icon/penjaga.png"));    
        ImageIcon iconAkun = new ImageIcon(getClass().getResource("/icon/akun.png"));      
      ImageIcon iconEntry = new ImageIcon(getClass().getResource("/icon30/Entry.png"));      
      ImageIcon iconInvent = new ImageIcon(getClass().getResource("/icon30/invent.png"));  
      ImageIcon iconBuku2 = new ImageIcon(getClass().getResource("/icon30/BukuBuku.png"));  
       ImageIcon logout = new ImageIcon(getClass().getResource("/icon30/LogOutya.png"));  
     
        MenuItem EntryBuku = new MenuItem(null, true, iconEntry, "Entry Buku", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuEntry());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
          MenuItem EntryInventaris = new MenuItem(null, true, iconInvent,"Inventaris", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuInventaris());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
       MenuItem PengembalianR = new MenuItem(null, true, iconRetrun, "Pengembalian", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new LaporanPengembalian());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
       MenuItem PeminjamanR = new MenuItem(null, true, iconPinjam, "Peminjaman", new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new LaporanPeminjaman());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
       MenuItem PengembalianT = new MenuItem(null, true, iconRetrun, "Pengembalian", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuPengembalian());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
       
           MenuItem PeminjamanT = new MenuItem(null, true, iconPinjam, "Peminjaman", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuPeminjaman());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
     
           MenuItem menuAkun = new MenuItem(iconAkun, false, null, "Akun", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuAkun());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
     
       

        MenuItem menuHome = new MenuItem(iconHome, false, null, "Home", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new content_bg());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem menuDashboard = new MenuItem(iconDashboard, false, null, "Dashboard", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new Dashboard());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem menuLogout = new MenuItem(iconLogout, false, null, "Logout", new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
          
            FormLogin loginForm = new FormLogin();
            loginForm.setVisible(true);
            
            
            dispose();
        }
    }
});
          MenuItem Santri = new MenuItem(null, true, iconSantri, "Santri", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuAnggota());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
           MenuItem Penjaga = new MenuItem(null, true, iconPenjaga, "Penjaga Perpus", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuPenjaga());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
         MenuItem List = new MenuItem(null, true, iconList, "List", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuBuku());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
         MenuItem Kategori = new MenuItem(null, true, iconKategori, "Kategori", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuKategori());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
         MenuItem Penerbit = new MenuItem(null, true, iconPenerbit, "Penerbit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new MenuPenerbit());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
 MenuItem menuBuku2 = new MenuItem(iconBuku2, false, null, "Master Buku", null, EntryBuku, EntryInventaris);
      MenuItem menuBuku = new MenuItem(iconBuku, false, null, "Buku", null, List, Kategori, Penerbit);
      
      MenuItem menuAnggota = new MenuItem(iconPerpus, false, null, "Anggota", null, Santri, Penjaga);
       
      MenuItem menuTransaksi = new MenuItem(iconTransaksi, false, null, "Transaksi", null, PeminjamanT, PengembalianT);
        MenuItem menuLaporan = new MenuItem(iconLaporan, false, null, "Laporan", null, PeminjamanR, PengembalianR);
      
 addMenu(menuHome, menuDashboard, menuAkun, menuAnggota, menuBuku2,menuBuku, menuTransaksi, menuLaporan, menuLogout);
    }
    
    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            pn_menu.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        pn_menu.revalidate();
    }
      
}
