/* Form Edit Pengguna
*/
package ui;
import java.sql.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class FormEPengguna extends javax.swing.JFrame {
    String user = "root";
    String pwd = "";
    String url = "jdbc:mysql://localhost:3306/penggajiandb";
    Boolean isi=true;
    private Dimension screensize;
    static boolean maximized = true;
    int xMouse;
    int yMouse;
    
    /**
     * Creates new form FormEPengguna
     */
    public FormEPengguna() {
        initComponents();
        screensize = Toolkit.getDefaultToolkit().getScreenSize(); 
        setLocation((screensize.width / 2) - (getSize().width / 2), (screensize.height / 2) - (getSize().height / 2));
    }
    
    //Method
    void aktif(){  
        idTF.setEnabled(true);
        namaTF.setEnabled(true);
        passTF.setEnabled(true);
    } 
    void nonaktif(){   
        idTF.setEnabled(false);
        namaTF.setEnabled(false);
        passTF.setEnabled(false);
    }
    void bersih(){        
        idTF.setText("");
        namaTF.setText("");
        passTF.setText("");
    }
    //CRUD
    void simpan(){    
        try{            
            Connection conn=DriverManager.getConnection(url,user,pwd);            
            Statement st=conn.createStatement();        
            String sql="INSERT INTO pengguna VALUES('" +idTF.getText()+ "','" +passTF.getText()+ "','" +namaTF.getText()+"')";       
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"Data Berhasil Disimpan.","Informasi",JOptionPane.INFORMATION_MESSAGE);   
        }catch(SQLException e){ 
            System.out.println("Koneksi Gagal"+e.toString());     
        }
        formWindowActivated(null);
    }
    void update(){  
        try{        
            Connection conn=DriverManager.getConnection(url,user,pwd);    
            Statement st=conn.createStatement();       
            String sql="UPDATE pengguna SET kata_sandi='" +passTF.getText()+ "',nama='" +namaTF.getText()+ "' WHERE id_user='" +idTF.getText()+"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"Data Berhasil Diperbarui","Informasi",JOptionPane.INFORMATION_MESSAGE);    
        }catch(SQLException e){          
            System.out.println("Koneksi Gagal"+e.toString());   
        }      
        formWindowActivated(null);  
    }
    void delete(){    
        try{        
            Connection conn=DriverManager.getConnection(url,user,pwd);        
            Statement st=conn.createStatement();          
            String sql="DELETE FROM pengguna WHERE id_user='" +idTF.getText()+"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"Data Berhasil Dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);   
        }catch(SQLException e){     
            System.out.println("Koneksi Gagal"+e.toString());      
        }    
        formWindowActivated(null); 
    }
    void cari(){    
        try{         
            Connection conn=DriverManager.getConnection(url,user,pwd);     
            Statement st=conn.createStatement();        
            ResultSet rs=st.executeQuery("SELECT * FROM pengguna WHERE id_user='" + cariTF.getText() +"'");    
            if(rs.next()){             
                idTF.setText(rs.getString("id_user"));     
                passTF.setText(rs.getString("kata_sandi"));
                namaTF.setText(rs.getString("nama"));
            }else{  
                JOptionPane.showMessageDialog(this,"Data Tidak Ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);      
                cariTF.requestFocus();      
            }   
        }catch(SQLException e){  
                System.out.println("Koneksi Gagal"+e.toString());   
        }       
        ubahBT.setEnabled(true); 
        hapusBT.setEnabled(true);
    }
    //Tabel Pengguna
    void tabel(){    
        String[] columnNames={"ID User","Password","Nama"};   
        JTable table=new JTable(getData(),columnNames);  
        table.setEnabled(false);
        ScrollPane.setViewportView(table);   
    }
    private Object[][] getData(){   
        Object[][] data=null;    
        try{           
            Connection conn=DriverManager.getConnection(url,user,pwd);          
            Statement st=conn.createStatement();    
            ResultSet rs=st.executeQuery("SELECT * FROM pengguna");
            rs.last();        
            int rowCount=rs.getRow();
            rs.beforeFirst();
            data=new Object[rowCount][3];       
            int no=-1;        
            while(rs.next()){         
                no=no+1;          
                data[no][0]=rs.getString("id_user");
                data[no][1]=rs.getString("kata_sandi");
                data[no][2]=rs.getString("nama");
            }          
        }catch(SQLException e){      
            System.out.println("Koneksi Gagal"+e.toString());     
        } 
        return data;   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bg = new keeptoo.KGradientPanel();
        titlePN = new javax.swing.JPanel();
        closeBT = new javax.swing.JButton();
        miniBT = new javax.swing.JButton();
        icon = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        cariTF = new javax.swing.JTextField();
        cariBT = new javax.swing.JButton();
        id = new javax.swing.JLabel();
        idTF = new javax.swing.JTextField();
        pass = new javax.swing.JLabel();
        passTF = new javax.swing.JPasswordField();
        spassBT = new javax.swing.JToggleButton();
        nama = new javax.swing.JLabel();
        namaTF = new javax.swing.JTextField();
        ubahBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();
        batalBT = new javax.swing.JButton();
        keluarBT = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Data Pengguna");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        bg.setkEndColor(new java.awt.Color(0, 102, 0));
        bg.setkGradientFocus(1000);
        bg.setkStartColor(new java.awt.Color(0, 0, 0));

        titlePN.setBackground(new java.awt.Color(255, 255, 255));
        titlePN.setMaximumSize(new java.awt.Dimension(800, 25));
        titlePN.setMinimumSize(new java.awt.Dimension(800, 25));
        titlePN.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titlePNMouseDragged(evt);
            }
        });
        titlePN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titlePNMousePressed(evt);
            }
        });

        closeBT.setBackground(new java.awt.Color(255, 0, 0));
        closeBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/closeBT.png"))); // NOI18N
        closeBT.setContentAreaFilled(false);
        closeBT.setOpaque(true);
        closeBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeBTMouseExited(evt);
            }
        });
        closeBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBTActionPerformed(evt);
            }
        });

        miniBT.setBackground(new java.awt.Color(255, 255, 255));
        miniBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/miniBT.png"))); // NOI18N
        miniBT.setContentAreaFilled(false);
        miniBT.setOpaque(true);
        miniBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                miniBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                miniBTMouseExited(evt);
            }
        });
        miniBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miniBTActionPerformed(evt);
            }
        });

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ico2_20.png"))); // NOI18N

        title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        title.setText("Aplikasi Penggajian | Edit Data Pengguna");

        javax.swing.GroupLayout titlePNLayout = new javax.swing.GroupLayout(titlePN);
        titlePN.setLayout(titlePNLayout);
        titlePNLayout.setHorizontalGroup(
            titlePNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePNLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(miniBT)
                .addGap(0, 0, 0)
                .addComponent(closeBT))
        );
        titlePNLayout.setVerticalGroup(
            titlePNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePNLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(titlePNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(closeBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(miniBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID User", "Password", "Nama"
            }
        ));
        ScrollPane.setViewportView(Table);

        cariTF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cariTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cariTF.setText("Cari ID User");
        cariTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cariTF.setSelectionColor(new java.awt.Color(0, 102, 0));
        cariTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariTFKeyPressed(evt);
            }
        });

        cariBT.setBackground(new java.awt.Color(255, 255, 255));
        cariBT.setForeground(new java.awt.Color(255, 255, 255));
        cariBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search15B.png"))); // NOI18N
        cariBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cariBT.setContentAreaFilled(false);
        cariBT.setOpaque(true);
        cariBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cariBTMouseExited(evt);
            }
        });
        cariBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBTActionPerformed(evt);
            }
        });

        id.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        id.setForeground(new java.awt.Color(255, 255, 255));
        id.setText("ID User");

        idTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        idTF.setPreferredSize(new java.awt.Dimension(59, 30));

        pass.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        pass.setForeground(new java.awt.Color(255, 255, 255));
        pass.setText("Password");

        passTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        spassBT.setBackground(new java.awt.Color(255, 255, 255));
        spassBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/spassB.png"))); // NOI18N
        spassBT.setContentAreaFilled(false);
        spassBT.setOpaque(true);
        spassBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                spassBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                spassBTMouseExited(evt);
            }
        });
        spassBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spassBTActionPerformed(evt);
            }
        });

        nama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        nama.setForeground(new java.awt.Color(255, 255, 255));
        nama.setText("Nama");

        namaTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        namaTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        ubahBT.setBackground(new java.awt.Color(255, 255, 255));
        ubahBT.setForeground(new java.awt.Color(255, 255, 255));
        ubahBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ubah.png"))); // NOI18N
        ubahBT.setText("UBAH");
        ubahBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        ubahBT.setContentAreaFilled(false);
        ubahBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ubahBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ubahBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ubahBTMouseExited(evt);
            }
        });
        ubahBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahBTActionPerformed(evt);
            }
        });

        simpanBT.setBackground(new java.awt.Color(255, 255, 255));
        simpanBT.setForeground(new java.awt.Color(255, 255, 255));
        simpanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/simpan.png"))); // NOI18N
        simpanBT.setText("SIMPAN");
        simpanBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        simpanBT.setContentAreaFilled(false);
        simpanBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpanBT.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        simpanBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                simpanBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                simpanBTMouseExited(evt);
            }
        });
        simpanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanBTActionPerformed(evt);
            }
        });

        hapusBT.setBackground(new java.awt.Color(255, 255, 255));
        hapusBT.setForeground(new java.awt.Color(255, 255, 255));
        hapusBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hapus.png"))); // NOI18N
        hapusBT.setText("HAPUS");
        hapusBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        hapusBT.setContentAreaFilled(false);
        hapusBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapusBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapusBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hapusBTMouseExited(evt);
            }
        });
        hapusBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBTActionPerformed(evt);
            }
        });

        batalBT.setBackground(new java.awt.Color(255, 255, 255));
        batalBT.setForeground(new java.awt.Color(255, 255, 255));
        batalBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/batal.png"))); // NOI18N
        batalBT.setText("BATAL");
        batalBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        batalBT.setContentAreaFilled(false);
        batalBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        batalBT.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        batalBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                batalBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                batalBTMouseExited(evt);
            }
        });
        batalBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalBTActionPerformed(evt);
            }
        });

        keluarBT.setBackground(new java.awt.Color(255, 255, 255));
        keluarBT.setForeground(new java.awt.Color(255, 255, 255));
        keluarBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/keluar.png"))); // NOI18N
        keluarBT.setText("KELUAR");
        keluarBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 3));
        keluarBT.setContentAreaFilled(false);
        keluarBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        keluarBT.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        keluarBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                keluarBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                keluarBTMouseExited(evt);
            }
        });
        keluarBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(cariTF)
                        .addGap(0, 0, 0)
                        .addComponent(cariBT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(namaTF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id)
                            .addComponent(nama))
                        .addGap(20, 20, 20)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(pass)
                                .addGap(107, 145, Short.MAX_VALUE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(passTF)
                                .addGap(0, 0, 0)
                                .addComponent(spassBT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ubahBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hapusBT, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(simpanBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(batalBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(keluarBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(titlePN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cariTF, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(cariBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id)
                            .addComponent(pass))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passTF)
                            .addComponent(idTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spassBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(nama)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ubahBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(simpanBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(batalBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hapusBT, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(keluarBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBTMouseEntered
        closeBT.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_closeBTMouseEntered

    private void closeBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBTMouseExited
        closeBT.setBackground(new Color(255,0,0));
    }//GEN-LAST:event_closeBTMouseExited

    private void closeBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBTActionPerformed
        dispose();
    }//GEN-LAST:event_closeBTActionPerformed

    private void miniBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniBTMouseEntered
        miniBT.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_miniBTMouseEntered

    private void miniBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniBTMouseExited
        miniBT.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_miniBTMouseExited

    private void miniBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miniBTActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_miniBTActionPerformed

    private void titlePNMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlePNMouseDragged
        if(maximized){
            int x = evt.getXOnScreen();
            int y = evt.getYOnScreen();
            this.setLocation(x - xMouse, y - yMouse);
        }
    }//GEN-LAST:event_titlePNMouseDragged

    private void titlePNMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlePNMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_titlePNMousePressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        nonaktif();     
        bersih();   
        tabel();     
        simpanBT.setEnabled(false);     
        ubahBT.setEnabled(false);     
        hapusBT.setEnabled(false);  
        batalBT.setEnabled(false);   
        keluarBT.setEnabled(true);
    }//GEN-LAST:event_formWindowActivated

    private void spassBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spassBTActionPerformed
        if(spassBT.isSelected()){
            passTF.setEchoChar((char)0);
            spassBT.setOpaque(true);
            spassBT.setBackground(new Color(0,0,0));
            spassBT.setIcon(new ImageIcon(getClass().getResource("/icon/spassW.png")));
        }else{
            passTF.setEchoChar('*');
            spassBT.setBackground(new Color(255,255,255));
            spassBT.setIcon(new ImageIcon(getClass().getResource("/icon/spassB.png")));
        }
    }//GEN-LAST:event_spassBTActionPerformed

    private void spassBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spassBTMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_spassBTMouseEntered

    private void spassBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spassBTMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_spassBTMouseExited

    private void cariBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBTActionPerformed
        cari();
    }//GEN-LAST:event_cariBTActionPerformed

    private void cariBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBTMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBTMouseEntered

    private void cariBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBTMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBTMouseExited

    private void ubahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahBTActionPerformed
        isi=false;    
        aktif();   
        ubahBT.setEnabled(false);      
        simpanBT.setEnabled(true);   
        batalBT.setEnabled(true);
    }//GEN-LAST:event_ubahBTActionPerformed

    private void ubahBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahBTMouseEntered
        ubahBT.setIcon(new ImageIcon(getClass().getResource("/icon/ubahU.png")));
        ubahBT.setForeground(new Color(0,0,0));
        ubahBT.setOpaque(true);
    }//GEN-LAST:event_ubahBTMouseEntered

    private void ubahBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahBTMouseExited
        ubahBT.setIcon(new ImageIcon(getClass().getResource("/icon/ubah.png")));
        ubahBT.setForeground(new Color(255,255,255));
        ubahBT.setOpaque(false);
    }//GEN-LAST:event_ubahBTMouseExited

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
        if(isi==true){  
            simpan();    
        }else{          
            update();     
            isi=true;     
        }
    }//GEN-LAST:event_simpanBTActionPerformed

    private void simpanBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseEntered
        simpanBT.setIcon(new ImageIcon(getClass().getResource("/icon/simpanU.png")));
        simpanBT.setForeground(new Color(0,0,0));
        simpanBT.setOpaque(true);
    }//GEN-LAST:event_simpanBTMouseEntered

    private void simpanBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseExited
        simpanBT.setIcon(new ImageIcon(getClass().getResource("/icon/simpan.png")));
        simpanBT.setForeground(new Color(255,255,255));
        simpanBT.setOpaque(false);
    }//GEN-LAST:event_simpanBTMouseExited

    private void hapusBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBTActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Data akan dihapus secara permanen. Tetap Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            delete();
            formWindowActivated(null);
        }
    }//GEN-LAST:event_hapusBTActionPerformed

    private void hapusBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseEntered
        hapusBT.setIcon(new ImageIcon(getClass().getResource("/icon/hapusU.png")));
        hapusBT.setForeground(new Color(0,0,0));
        hapusBT.setOpaque(true);
    }//GEN-LAST:event_hapusBTMouseEntered

    private void hapusBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseExited
        hapusBT.setIcon(new ImageIcon(getClass().getResource("/icon/hapus.png")));
        hapusBT.setForeground(new Color(255,255,255));
        hapusBT.setOpaque(false);
    }//GEN-LAST:event_hapusBTMouseExited

    private void batalBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalBTActionPerformed
        formWindowActivated(null);
    }//GEN-LAST:event_batalBTActionPerformed

    private void batalBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalBTMouseEntered
        batalBT.setIcon(new ImageIcon(getClass().getResource("/icon/batalU.png")));
        batalBT.setForeground(new Color(0,0,0));
        batalBT.setOpaque(true);
    }//GEN-LAST:event_batalBTMouseEntered

    private void batalBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalBTMouseExited
        batalBT.setIcon(new ImageIcon(getClass().getResource("/icon/batal.png")));
        batalBT.setForeground(new Color(255,255,255));
        batalBT.setOpaque(false);
    }//GEN-LAST:event_batalBTMouseExited

    private void keluarBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarBTActionPerformed
        dispose();
    }//GEN-LAST:event_keluarBTActionPerformed

    private void keluarBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarBTMouseEntered
        keluarBT.setIcon(new ImageIcon(getClass().getResource("/icon/keluarE.png")));
        keluarBT.setForeground(new Color(255,0,0));
        keluarBT.setOpaque(true);
    }//GEN-LAST:event_keluarBTMouseEntered

    private void keluarBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarBTMouseExited
        keluarBT.setIcon(new ImageIcon(getClass().getResource("/icon/keluar.png")));
        keluarBT.setForeground(new Color(255,255,255));
        keluarBT.setOpaque(false);
    }//GEN-LAST:event_keluarBTMouseExited

    private void cariTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            cari();
        }
    }//GEN-LAST:event_cariTFKeyPressed

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
            java.util.logging.Logger.getLogger(FormEPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormEPengguna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JTable Table;
    private javax.swing.JButton batalBT;
    private keeptoo.KGradientPanel bg;
    private javax.swing.JButton cariBT;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton closeBT;
    private javax.swing.JButton hapusBT;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel id;
    private javax.swing.JTextField idTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton keluarBT;
    private javax.swing.JButton miniBT;
    private javax.swing.JLabel nama;
    private javax.swing.JTextField namaTF;
    private javax.swing.JLabel pass;
    private javax.swing.JPasswordField passTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JToggleButton spassBT;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titlePN;
    private javax.swing.JButton ubahBT;
    // End of variables declaration//GEN-END:variables
}
