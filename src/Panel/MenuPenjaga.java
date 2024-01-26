package Panel;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Config.Koneksi;
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
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.sql.Blob;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.FormLogin;

/**
 *
 * @author Rifal
 */
public class MenuPenjaga extends javax.swing.JPanel {

    private FormLogin formLogin;
    private int halamanSaatIni = 1;
    private int dataPerHalaman = 20;
    private int totalHalaman;
    private int totalPages;

    public class ImageUtil {

        public static Image getImageFromBlob(Blob blob) throws SQLException, IOException, ClassNotFoundException {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Image) ois.readObject();
        }
    }

    private void setTableRenderer() {
        class CustomRender extends DefaultTableCellRenderer {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                if (value instanceof ImageIcon) {
                    ImageIcon icon = (ImageIcon) value;
                    Image originalImage = icon.getImage();
                    int desireWidth = 70;
                    int desireHeight = 90;

                    Image resizedImage = originalImage.getScaledInstance(desireWidth, desireHeight, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);

                    JLabel label = new JLabel(resizedIcon);
                    label.setHorizontalAlignment(JLabel.CENTER);

                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }

        }
        tblData.getColumnModel().getColumn(6).setCellRenderer(new CustomRender());
    }

    private void getImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Gambar", "jpg", "png", "jpeg"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            txtImage.setText(path);

            try {
                byte[] img = Files.readAllBytes(selectedFile.toPath());
                ImageIcon imageIcon = new ImageIcon(img);

                int labelWidth = lbGambar.getWidth();
                int labelHeight = lbGambar.getHeight();

                int imageWidth = imageIcon.getIconWidth();
                int imageHeight = imageIcon.getIconHeight();

                double scaleX = (double) labelWidth / (double) imageWidth;
                double scaleY = (double) labelHeight / (double) imageHeight;
                double scale = Math.min(scaleX, scaleY);

                Image scaleImage = imageIcon.getImage().getScaledInstance(
                        (int) (scale * imageWidth),
                        (int) (scale * imageHeight),
                        Image.SCALE_SMOOTH);
                lbGambar.setIcon(new ImageIcon(scaleImage));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class ImageRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                ImageIcon icon = (ImageIcon) value;
                JLabel label = new JLabel(icon);
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        }
    }

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

    private void setImage(JLabel lb_gambar, String id) {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT * FROM penjaga WHERE ID_Penjaga = '" + id + "'";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                byte[] img = rs.getBytes("Gambar");
                if (img != null) {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().
                            getScaledInstance(lb_gambar.getWidth(), lb_gambar.getHeight(), Image.SCALE_SMOOTH));
                    lb_gambar.setIcon(imageIcon);
                } else {
                    ImageIcon defaultIcon = new ImageIcon(getClass().getResource("Image/icons8_people_120px_2.png"));
                    lb_gambar.setIcon(defaultIcon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void paginationPenjaga() {
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
            String sql = "SELECT COUNT(*) AS total from Penjaga";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    totalData = rs.getInt("total");

                }

            }

        } catch (Exception e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }
        return totalData;
    }

    public MenuPenjaga() {
        initComponents();
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

            }
        });

        setTabelModel();
         loadData();
         paginationPenjaga();
 setTableRenderer();
        txtID.setEditable(false);
        Color headerColor = new Color(127, 140, 117);
        tblData.getTableHeader().setBackground(headerColor);
       
        

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
        jLabel15 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTelepon = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JTextField();
        btnSave = new rojerusan.RSMaterialButtonRectangle();
        btnCancel2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel3 = new javax.swing.JLabel();
        lbGambar = new javax.swing.JLabel();
        txtImage = new javax.swing.JTextField();
        btnBrowse = new keeptoo.KButton();

        dateChooser1.setTextRefernce(txtTanggal);

        setLayout(new java.awt.CardLayout());

        PanelMain.setBackground(new java.awt.Color(255, 255, 255));
        PanelMain.setPreferredSize(new java.awt.Dimension(950, 590));
        PanelMain.setLayout(new java.awt.CardLayout());

        PanelView.setBackground(new java.awt.Color(255, 255, 255));
        PanelView.setPreferredSize(new java.awt.Dimension(950, 590));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setText("Penjaga Perpustakaan");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setText("Daftar Penjaga");

        lb_iconAnggota.setBackground(new java.awt.Color(250, 250, 250));
        lb_iconAnggota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/pngwing.com (32).png"))); // NOI18N

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
        tblData.setRowHeight(150);
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/penjaga.png"))); // NOI18N

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
                                .addGap(0, 689, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
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
        jLabel13.setText("Input Data Penjaga");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setText("Tambah Data Penjaga");

        lb_iconAnggota2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/pngwing.com (33).png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel15.setText("ID");

        txtID.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(102, 102, 102));
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel16.setText("Email");

        txtNama.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNama.setForeground(new java.awt.Color(102, 102, 102));
        txtNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel17.setText("Nama Penjaga");

        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(102, 102, 102));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel18.setText("Telepon");

        txtTelepon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTelepon.setForeground(new java.awt.Color(102, 102, 102));
        txtTelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTeleponKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTeleponKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel19.setText("Jenis Kelamin");

        jenisKelamin.add(rbLaki);
        rbLaki.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        rbLaki.setForeground(new java.awt.Color(102, 102, 102));
        rbLaki.setText("Laki - Laki");

        jenisKelamin.add(rbPerempuan);
        rbPerempuan.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        rbPerempuan.setForeground(new java.awt.Color(102, 102, 102));
        rbPerempuan.setText("Perempuan");

        jLabel20.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel20.setText("Tanggal Bergabung");

        txtTanggal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTanggal.setForeground(new java.awt.Color(102, 102, 102));
        txtTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalActionPerformed(evt);
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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/penjaga.png"))); // NOI18N

        lbGambar.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbGambar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon30/icons8_people_120px_2.png"))); // NOI18N
        lbGambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtImage.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        txtImage.setForeground(new java.awt.Color(204, 204, 204));
        txtImage.setText("Image Path");
        txtImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImageActionPerformed(evt);
            }
        });

        btnBrowse.setText("...");
        btnBrowse.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        btnBrowse.setkBorderRadius(15);
        btnBrowse.setkEndColor(new java.awt.Color(236, 244, 214));
        btnBrowse.setkForeGround(new java.awt.Color(0, 0, 0));
        btnBrowse.setkHoverEndColor(new java.awt.Color(45, 149, 150));
        btnBrowse.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnBrowse.setkHoverStartColor(new java.awt.Color(38, 80, 115));
        btnBrowse.setkIndicatorColor(new java.awt.Color(204, 204, 204));
        btnBrowse.setkStartColor(new java.awt.Color(154, 208, 194));
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAddLayout = new javax.swing.GroupLayout(PanelAdd);
        PanelAdd.setLayout(PanelAddLayout);
        PanelAddLayout.setHorizontalGroup(
            PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAddLayout.createSequentialGroup()
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAddLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addComponent(lb_iconAnggota2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTanggal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                                    .addComponent(txtTelepon, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelAddLayout.createSequentialGroup()
                                        .addComponent(rbLaki)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbPerempuan))
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAddLayout.createSequentialGroup()
                        .addContainerGap(722, Short.MAX_VALUE)
                        .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelAddLayout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAddLayout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelAddLayout.createSequentialGroup()
                        .addComponent(lbGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImage)
                            .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan))
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
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

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalActionPerformed


    }//GEN-LAST:event_txtTanggalActionPerformed

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
        txtID.setText(setIDPenjaga());

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

    private void txtTeleponKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeleponKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeleponKeyPressed

    private void txtTeleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeleponKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c))) {

            evt.consume();
        }
    }//GEN-LAST:event_txtTeleponKeyTyped

    private void txtImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImageActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        getImage();
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void txtNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKeyTyped
         char c = evt.getKeyChar();
    
    
    if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_QUOTE) {
        evt.consume();
    }

    }//GEN-LAST:event_txtNamaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAdd;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelView;
    private rojerusan.RSMaterialButtonRectangle btnAdd;
    private keeptoo.KButton btnBrowse;
    private rojerusan.RSMaterialButtonRectangle btnCancel;
    private rojerusan.RSMaterialButtonRectangle btnCancel2;
    private rojerusan.RSMaterialButtonRectangle btnDelete;
    private rojerusan.RSMaterialButtonRectangle btnSave;
    private javax.swing.JButton btn_before;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JComboBox<String> cbx_data;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbGambar;
    private javax.swing.JLabel lb_halaman;
    private javax.swing.JLabel lb_iconAnggota;
    private javax.swing.JLabel lb_iconAnggota2;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtImage;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTelepon;
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
        PanelMain.add(new MenuPenjaga());
        PanelMain.repaint();
        PanelMain.revalidate();
    }

    private void resetForm() {
        txtID.setText("");
        txtNama.setText("");
        txtEmail.setText("");
        txtTelepon.setText("");
        rbLaki.setSelected(false);
        rbPerempuan.setSelected(false);
        txtTanggal.setText("");
    }

    private void setTabelModel() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Email");
        model.addColumn("Telepon");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Tanggal Bergabung");
        model.addColumn("Foto");

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
            String sql = "SELECT * FROM Penjaga LIMIT ?,?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, startIndex);
                st.setInt(2, entriesPage);

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    String idPenjaga = rs.getString("ID_Penjaga");
                    String namaPenjaga = rs.getString("Nama_Penjaga");
                    String emailPenjaga = rs.getString("Email_Penjaga");
                    String teleponPenjaga = rs.getString("Telepon_Penjaga");
                    String jenisKelamin = rs.getString("Jenis_Kelamin_Penjaga");
                    String tanggalBergabung = rs.getString("Tanggal_Bergabung_Penjaga");

                    byte[] imageData = rs.getBytes("Gambar");
                    ImageIcon imageIcon = new ImageIcon(imageData);

                    Object[] rowData = {idPenjaga, namaPenjaga, emailPenjaga, teleponPenjaga, jenisKelamin, tanggalBergabung, imageIcon};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private String setIDPenjaga() {
        String urutan = null;
        Date now = new Date();
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");

        String sql = "SELECT RIGHT(ID_Penjaga, 3) AS Nomor "
                + "FROM Penjaga "
                + "WHERE ID_Penjaga LIKE 'PJG" + noFormat.format(now) + "%' "
                + "ORDER BY ID_Penjaga DESC "
                + "LIMIT 1";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                int nomor = Integer.parseInt(rs.getString("Nomor")) + 1;
                urutan = "PJG" + noFormat.format(now) + String.format("%03d", nomor);
            } else {
                urutan = "PJG" + noFormat.format(now) + "001";
            }

        } catch (SQLException e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }

        return urutan;
    }

    private boolean isEmailPenjagaExists(String email) {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT COUNT(*) FROM Penjaga WHERE Email_Penjaga = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, email);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private void insertData() {
        String idPenjaga = txtID.getText();
        String namaPenjaga = txtNama.getText();
        String emailPenjaga = txtEmail.getText();
        String teleponPenjaga = txtTelepon.getText();
        String imagePath = txtImage.getText();

        String jenisKelamin;

        if (rbLaki.isSelected()) {
            jenisKelamin = rbLaki.getText();
        } else if (rbPerempuan.isSelected()) {
            jenisKelamin = rbPerempuan.getText();
        } else {
            jenisKelamin = "";
        }

        String tanggalBergabung = txtTanggal.getText();

        if (idPenjaga.isEmpty() || namaPenjaga.isEmpty() || emailPenjaga.isEmpty() || teleponPenjaga.isEmpty() || jenisKelamin.isEmpty() || tanggalBergabung == null || imagePath.equals("Image Path")) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(emailPenjaga)) {
            JOptionPane.showMessageDialog(this, "Format email tidak valid!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isNamaPenjagaExists(namaPenjaga) || isEmailPenjagaExists(emailPenjaga)) {
            JOptionPane.showMessageDialog(this, "Nama atau email penjaga sudah ada, silakan gunakan yang lain!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO Penjaga (ID_Penjaga, Nama_Penjaga, Email_Penjaga, Telepon_Penjaga, Jenis_Kelamin_Penjaga, Tanggal_Bergabung_Penjaga, Gambar) VALUES (?, ?,?,?,?,?,?)";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, idPenjaga);
                st.setString(2, namaPenjaga);
                st.setString(3, emailPenjaga);
                st.setString(4, teleponPenjaga);
                st.setString(5, jenisKelamin);
                st.setString(6, tanggalBergabung);

                File imageFile = new File(imagePath);
                FileInputStream fis = new FileInputStream(imageFile);
                st.setBinaryStream(7, fis, (int) imageFile.length());

                int rowInserted = st.executeUpdate();
                if (rowInserted > 0) {

                    String insertAkunSql = "INSERT INTO Akun (Username, Email, Password, Level) VALUES (?,?,?,?)";
                    try (PreparedStatement stAkun = conn.prepareStatement(insertAkunSql)) {
                        String usernameAkun = idPenjaga;
                        String usernamePassword = formLogin.getMd5java(idPenjaga);

                        stAkun.setString(1, usernameAkun);
                        stAkun.setString(2, emailPenjaga);
                        stAkun.setString(3, usernamePassword);
                        stAkun.setString(4, "Admin");

                        int rowInsertedAkun = stAkun.executeUpdate();

                        if (rowInsertedAkun > 0) {
                            conn.commit();
                            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
                            resetForm();
                            loadData();
                            showPanel();
                        } else {
                            conn.rollback();
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MenuBuku.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isNamaPenjagaExists(String namaPenjaga) {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT COUNT(*) FROM Penjaga WHERE Nama_Penjaga=?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, namaPenjaga);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private void updateData() {
        String idPenjaga = txtID.getText();
        String namaPenjaga = txtNama.getText();
        String emailPenjaga = txtEmail.getText();
        String teleponPenjaga = txtTelepon.getText();
        String jenisKelamin;
        String imagePath = txtImage.getText();

        if (rbLaki.isSelected()) {
            jenisKelamin = rbLaki.getText();
        } else if (rbPerempuan.isSelected()) {
            jenisKelamin = rbPerempuan.getText();
        } else {
            jenisKelamin = "";
        }

        String tanggalBergabung = txtTanggal.getText();

        if (idPenjaga.isEmpty() || namaPenjaga.isEmpty() || emailPenjaga.isEmpty() || teleponPenjaga.isEmpty() || jenisKelamin.isEmpty() || tanggalBergabung == null) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(emailPenjaga)) {
            JOptionPane.showMessageDialog(this, "Format email tidak valid!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isNamaPenjagaExistsOther(idPenjaga, namaPenjaga) || isEmailPenjagaExistsOther(idPenjaga, emailPenjaga)) {
            JOptionPane.showMessageDialog(this, "Nama atau email penjaga sudah ada, silakan gunakan yang lain!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = Koneksi.getConnection()) {

            String checkEmailQuery = "SELECT * FROM Penjaga WHERE Email_Penjaga = ? AND ID_Penjaga != ?";
            try (PreparedStatement checkEmailStatement = conn.prepareStatement(checkEmailQuery)) {
                checkEmailStatement.setString(1, emailPenjaga);
                checkEmailStatement.setString(2, idPenjaga);
                ResultSet emailResult = checkEmailStatement.executeQuery();

                if (emailResult.next()) {
                    JOptionPane.showMessageDialog(this, "Email sudah digunakan oleh penjaga lain!", "Validasi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String updateQuery = "UPDATE penjaga SET Nama_Penjaga=?, Email_Penjaga=?, Telepon_Penjaga=?, Jenis_Kelamin_Penjaga=?, Tanggal_Bergabung_Penjaga=?";
            if (!imagePath.equals("Image Path")) {
                updateQuery += ",Gambar = ?";
            }
            updateQuery += " WHERE ID_PENJAGA=?";

            try (PreparedStatement st = conn.prepareStatement(updateQuery)) {
                st.setString(1, namaPenjaga);
                st.setString(2, emailPenjaga);
                st.setString(3, teleponPenjaga);
                st.setString(4, jenisKelamin);
                st.setString(5, tanggalBergabung);

                if (!imagePath.equals("Image Path")) {
                    File imageFile = new File(imagePath);
                    FileInputStream fis = new FileInputStream(imageFile);
                    st.setBinaryStream(6, fis, (int) imageFile.length());
                    st.setString(7, idPenjaga);
                } else {
                    st.setString(6, idPenjaga);
                }
                int rowUpdated = st.executeUpdate();
                if (rowUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui");
                    resetForm();
                    loadData();
                    showPanel();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MenuBuku.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private boolean isNamaPenjagaExistsOther(String idPenjaga, String namaPenjaga) {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT COUNT(*) FROM Penjaga WHERE Nama_Penjaga = ? AND ID_Penjaga <> ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, namaPenjaga);
                st.setString(2, idPenjaga);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private boolean isEmailPenjagaExistsOther(String idPenjaga, String emailPenjaga) {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT COUNT(*) FROM Penjaga WHERE Email_Penjaga = ? AND ID_Penjaga <> ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, emailPenjaga);
                st.setString(2, idPenjaga);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private void dataTabel() {
        PanelView.setVisible(false);
        PanelAdd.setVisible(true);

        int row = tblData.getSelectedRow();
        String id = tblData.getModel().getValueAt(row, 0).toString();
        jLabel14.setText("Perbarui Data Penjaga");
        txtID.setEnabled(false);
        txtTanggal.setEditable(false);

        txtID.setText(tblData.getValueAt(row, 0).toString());
        txtNama.setText(tblData.getValueAt(row, 1).toString());
        txtEmail.setText(tblData.getValueAt(row, 2).toString());
        txtTelepon.setText(tblData.getValueAt(row, 3).toString());

        String jenisKelamin = tblData.getValueAt(row, 4).toString();
        if (jenisKelamin.equals("Laki - Laki")) {
            rbLaki.setSelected(true);
        } else if (jenisKelamin.equals("Perempuan")) {
            rbPerempuan.setSelected(true);
        }

        txtTanggal.setText(tblData.getValueAt(row, 5).toString());
        setImage(lbGambar, id);
    }

    private void deleteData() {
        int selectedRow = tblData.getSelectedRow();
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah anda yakin menghapus data ini ?",
                "Konfirmasi Hapus Data",
                JOptionPane.YES_NO_OPTION);

        Connection conn = null;

        if (confirm == JOptionPane.YES_OPTION) {
            String idPenjaga = tblData.getValueAt(selectedRow, 0).toString();

            try {
                conn = Koneksi.getConnection();
                conn.setAutoCommit(false);

                String deletePenjagaSql = "DELETE FROM Penjaga WHERE ID_Penjaga=?";
                try (PreparedStatement stPenjaga = conn.prepareStatement(deletePenjagaSql)) {
                    stPenjaga.setString(1, idPenjaga);
                    int rowDeletedPenjaga = stPenjaga.executeUpdate();

                    if (rowDeletedPenjaga > 0) {

                        String deleteAkunSql = "DELETE FROM Akun WHERE Username=?";
                        try (PreparedStatement stAkun = conn.prepareStatement(deleteAkunSql)) {
                            stAkun.setString(1, idPenjaga);

                            int rowDeletedAkun = stAkun.executeUpdate();

                            if (rowDeletedAkun > 0) {
                                conn.commit();
                                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                            } else {
                                conn.rollback();
                                JOptionPane.showMessageDialog(this, "Gagal menghapus data Akun");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Gagal menghapus data Penjaga");
                    }
                }
            } catch (Exception e) {
                try {
                    if (conn != null) {
                        conn.rollback();
                        JOptionPane.showMessageDialog(this, "Terjadi kesalahan. Rollback transaksi.");
                    }
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Gagal melakukan rollback transaksi.");
                }
                e.printStackTrace();
                Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
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
            String sql = "SELECT * FROM Penjaga WHERE Nama_Penjaga LIKE ? OR Email_Penjaga LIKE ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, "%" + kataKunci + "%");
                st.setString(2, "%" + kataKunci + "%");
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    String idPenjaga = rs.getString("ID_Penjaga");
                    String namaPenjaga = rs.getString("Nama_Penjaga");
                    String emailPenjaga = rs.getString("Email_Penjaga");
                    String teleponPenjaga = rs.getString("Telepon_Penjaga");
                    String jenisKelamin = rs.getString("Jenis_Kelamin_Penjaga");
                    String tanggalBergabung = rs.getString("Tanggal_Bergabung_penjaga");

                    byte[] imageData = rs.getBytes("Gambar");
                    ImageIcon imageIcon = new ImageIcon(imageData);

                    Object[] rowData = {idPenjaga, namaPenjaga, emailPenjaga, teleponPenjaga, jenisKelamin, tanggalBergabung, imageIcon};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MenuPenjaga.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
