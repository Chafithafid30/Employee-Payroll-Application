/* Form Edit Data Jabatan
*/
package ui;
import java.sql.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTable;


public class FormEJabatan extends javax.swing.JFrame {
    String user = "root";
    String pwd = "";
    String url = "jdbc:mysql://localhost:3306/penggajiandb";
    Boolean isi=true;
    private Dimension screensize;
    static boolean maximized = true;
    int xMouse;
    int yMouse;

    /**
     * Creates new form FormEKaryawan
     */
    public FormEJabatan() {
        initComponents();
        screensize = Toolkit.getDefaultToolkit().getScreenSize(); 
        setLocation((screensize.width / 2) - (getSize().width / 2), (screensize.height / 2) - (getSize().height / 2));
    }
    
    //Method
    void aktif(){  
        idTF.setEnabled(true);
        namaTF.setEnabled(true);
        gajiTF.setEnabled(true);
        descTF.setEnabled(true);
    } 
    void nonaktif(){   
        idTF.setEnabled(false);
        namaTF.setEnabled(false);
        gajiTF.setEnabled(false);
        descTF.setEnabled(false);   
    }
    void bersih(){        
        idTF.setText("");
        namaTF.setText("");
        gajiTF.setText("");
        descTF.setText("");    
    }
    //Auto Increment
    void otomatis(){   
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);      
            Statement st = (Statement) conn.createStatement();  
            String sql = "SELECT RIGHT(id_jabatan,3)+1 FROM jabatan";    
            ResultSet rs = st.executeQuery(sql);    
            if (rs.next()){             
                rs.last();             
                String kode = rs.getString(1);      
                while (kode.length()<3){              
                    kode="0"+kode;                 
                    idTF.setText("888"+kode);       
                }           
            }else{      
                idTF.setText("888001");         
            }       
        }catch (Exception e){      
        }   
    }
    //CRUD
    void simpan(){    
        try{            
            Connection conn=DriverManager.getConnection(url,user,pwd);            
            Statement st=conn.createStatement();        
            String sql="INSERT INTO jabatan VALUES('" +idTF.getText()+ "','" +namaTF.getText()+ "','" +gajiTF.getText()+ "','" +descTF.getText()+ "')";       
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
            String sql="UPDATE jabatan SET nama_jabatan='" +namaTF.getText()+ "',gaji_pokok='" +gajiTF.getText()+ "',deskripsi='" +descTF.getText()+ "' WHERE id_jabatan='" +idTF.getText()+"'";
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
            String sql="DELETE FROM jabatan WHERE id_jabatan='" +idTF.getText()+"'";        
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
            ResultSet rs=st.executeQuery("SELECT * FROM jabatan WHERE id_jabatan='" + cariTF.getText() +"'");    
            if(rs.next()){             
                idTF.setText(rs.getString("id_jabatan"));     
                namaTF.setText(rs.getString("nama_jabatan"));
                gajiTF.setText(rs.getString("gaji_pokok"));
                descTF.setText(rs.getString("deskripsi"));        
            }else{  
                JOptionPane.showMessageDialog(this,"Data Tidak Ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);      
                cariTF.requestFocus();      
            }   
        }catch(SQLException e){  
                System.out.println("Koneksi Gagal"+e.toString());   
        }       
        ubahBT.setEnabled(true); 
        hapusBT.setEnabled(true);  
        tambahBT.setEnabled(false); 
    }
    //Tabel Jabatan
    void tabel(){    
        String[] columnNames={"ID Jabatan","Nama Jabatan","Gaji Pokok","Deskripsi"};   
        JTable table=new JTable(getData(),columnNames);  
        table.setEnabled(false);       
        ScrollPane.setViewportView(table);   
    }
    private Object[][] getData(){   
        Object[][] data1=null;    
        try{           
            Connection conn=DriverManager.getConnection(url,user,pwd);          
            Statement st=conn.createStatement();    
            ResultSet rs=st.executeQuery("SELECT * FROM jabatan");  
            rs.last();        
            int rowCount=rs.getRow();      
            rs.beforeFirst();         
            data1=new Object[rowCount][4];       
            int no=-1;        
            while(rs.next()){         
                no=no+1;          
                data1[no][0]=rs.getString("id_jabatan");
                data1[no][1]=rs.getString("nama_jabatan");
                data1[no][2]="Rp. "+rs.getString("gaji_pokok");
                data1[no][3]=rs.getString("deskripsi");
            }          
        }catch(SQLException e){      
            System.out.println("Koneksi Gagal"+e.toString());     
        } 
        return data1;   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new keeptoo.KGradientPanel();
        titlePN = new javax.swing.JPanel();
        closeBT = new javax.swing.JButton();
        miniBT = new javax.swing.JButton();
        maxiBT = new javax.swing.JButton();
        icon = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        jabatanTB = new javax.swing.JTable();
        dataPN = new javax.swing.JPanel();
        cariTF = new javax.swing.JTextField();
        cariBT = new javax.swing.JButton();
        id = new javax.swing.JLabel();
        idTF = new javax.swing.JTextField();
        nama = new javax.swing.JLabel();
        namaTF = new javax.swing.JTextField();
        gaji = new javax.swing.JLabel();
        gajiTF = new javax.swing.JTextField();
        desc = new javax.swing.JLabel();
        descTF = new javax.swing.JTextField();
        tombolPN = new javax.swing.JPanel();
        tambahBT = new javax.swing.JButton();
        ubahBT = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        batalBT = new javax.swing.JButton();
        keluarBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Data Jabatan");
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        bg.setkEndColor(new java.awt.Color(204, 204, 0));
        bg.setkGradientFocus(1250);
        bg.setkStartColor(new java.awt.Color(0, 102, 0));
        bg.setMaximumSize(new java.awt.Dimension(1200, 600));
        bg.setMinimumSize(new java.awt.Dimension(1200, 600));
        bg.setPreferredSize(new java.awt.Dimension(1200, 600));

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

        maxiBT.setBackground(new java.awt.Color(255, 255, 255));
        maxiBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/maxiBT.png"))); // NOI18N
        maxiBT.setContentAreaFilled(false);
        maxiBT.setOpaque(true);
        maxiBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maxiBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maxiBTMouseExited(evt);
            }
        });
        maxiBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxiBTActionPerformed(evt);
            }
        });

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ico2_20.png"))); // NOI18N

        title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        title.setText("Aplikasi Penggajian | Edit Data Jabatan");

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
                .addComponent(maxiBT)
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
                    .addComponent(miniBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maxiBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        ScrollPane.setOpaque(false);

        jabatanTB.setAutoCreateColumnsFromModel(false);
        jabatanTB.setBackground(new java.awt.Color(204, 204, 204));
        jabatanTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Jabatan", "Nama Jabatan", "Gaji Pokok", "Deskripsi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jabatanTB.setEnabled(false);
        jabatanTB.setGridColor(new java.awt.Color(0, 0, 0));
        jabatanTB.getTableHeader().setReorderingAllowed(false);
        ScrollPane.setViewportView(jabatanTB);
        if (jabatanTB.getColumnModel().getColumnCount() > 0) {
            jabatanTB.getColumnModel().getColumn(0).setResizable(false);
            jabatanTB.getColumnModel().getColumn(1).setResizable(false);
            jabatanTB.getColumnModel().getColumn(2).setResizable(false);
            jabatanTB.getColumnModel().getColumn(3).setResizable(false);
        }

        dataPN.setBackground(new java.awt.Color(0, 0, 0));
        dataPN.setMaximumSize(new java.awt.Dimension(300, 51));
        dataPN.setMinimumSize(new java.awt.Dimension(300, 51));

        cariTF.setBackground(new java.awt.Color(0, 0, 0));
        cariTF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cariTF.setForeground(new java.awt.Color(255, 255, 255));
        cariTF.setText("Cari ID Jabatan");
        cariTF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        cariTF.setSelectionColor(new java.awt.Color(204, 204, 0));
        cariTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariTFKeyPressed(evt);
            }
        });

        cariBT.setBackground(new java.awt.Color(0, 0, 0));
        cariBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search15.png"))); // NOI18N
        cariBT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
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
        id.setText("ID Jabatan");

        idTF.setPreferredSize(new java.awt.Dimension(6, 25));
        idTF.setSelectionColor(new java.awt.Color(204, 204, 0));

        nama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        nama.setForeground(new java.awt.Color(255, 255, 255));
        nama.setText("Nama Jabatan");

        namaTF.setPreferredSize(new java.awt.Dimension(6, 25));
        namaTF.setSelectionColor(new java.awt.Color(204, 204, 0));

        gaji.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        gaji.setForeground(new java.awt.Color(255, 255, 255));
        gaji.setText("Gaji Pokok");

        gajiTF.setPreferredSize(new java.awt.Dimension(6, 25));
        gajiTF.setSelectionColor(new java.awt.Color(204, 204, 0));

        desc.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        desc.setForeground(new java.awt.Color(255, 255, 255));
        desc.setText("Deskripsi");

        descTF.setPreferredSize(new java.awt.Dimension(6, 29));
        descTF.setSelectionColor(new java.awt.Color(204, 204, 0));

        javax.swing.GroupLayout dataPNLayout = new javax.swing.GroupLayout(dataPN);
        dataPN.setLayout(dataPNLayout);
        dataPNLayout.setHorizontalGroup(
            dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataPNLayout.createSequentialGroup()
                        .addComponent(cariTF, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariBT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(id)
                    .addComponent(nama)
                    .addComponent(gaji)
                    .addComponent(desc)
                    .addGroup(dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(descTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                        .addComponent(gajiTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(namaTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dataPNLayout.setVerticalGroup(
            dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPNLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariBT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(id)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gaji)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gajiTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tombolPN.setBackground(new java.awt.Color(0, 0, 0));

        tambahBT.setBackground(new java.awt.Color(204, 204, 0));
        tambahBT.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        tambahBT.setForeground(new java.awt.Color(255, 255, 255));
        tambahBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tambah.png"))); // NOI18N
        tambahBT.setToolTipText("Tambah data");
        tambahBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        tambahBT.setContentAreaFilled(false);
        tambahBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambahBT.setOpaque(true);
        tambahBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tambahBTMouseExited(evt);
            }
        });
        tambahBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBTActionPerformed(evt);
            }
        });

        ubahBT.setBackground(new java.awt.Color(204, 204, 0));
        ubahBT.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        ubahBT.setForeground(new java.awt.Color(255, 255, 255));
        ubahBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ubah.png"))); // NOI18N
        ubahBT.setToolTipText("Ubah data");
        ubahBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ubahBT.setContentAreaFilled(false);
        ubahBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ubahBT.setOpaque(true);
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

        hapusBT.setBackground(new java.awt.Color(204, 204, 0));
        hapusBT.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        hapusBT.setForeground(new java.awt.Color(255, 255, 255));
        hapusBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hapus.png"))); // NOI18N
        hapusBT.setToolTipText("Hapus data");
        hapusBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        hapusBT.setContentAreaFilled(false);
        hapusBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapusBT.setOpaque(true);
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

        simpanBT.setBackground(new java.awt.Color(204, 204, 0));
        simpanBT.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        simpanBT.setForeground(new java.awt.Color(255, 255, 255));
        simpanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/simpan.png"))); // NOI18N
        simpanBT.setToolTipText("Simpan data");
        simpanBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        simpanBT.setContentAreaFilled(false);
        simpanBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpanBT.setOpaque(true);
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

        batalBT.setBackground(new java.awt.Color(204, 204, 0));
        batalBT.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        batalBT.setForeground(new java.awt.Color(255, 255, 255));
        batalBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/batal.png"))); // NOI18N
        batalBT.setToolTipText("Batal edit");
        batalBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        batalBT.setContentAreaFilled(false);
        batalBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        batalBT.setOpaque(true);
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

        keluarBT.setBackground(new java.awt.Color(255, 0, 0));
        keluarBT.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        keluarBT.setForeground(new java.awt.Color(255, 255, 255));
        keluarBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/keluar.png"))); // NOI18N
        keluarBT.setToolTipText("Keluar");
        keluarBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        keluarBT.setContentAreaFilled(false);
        keluarBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        keluarBT.setOpaque(true);
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

        javax.swing.GroupLayout tombolPNLayout = new javax.swing.GroupLayout(tombolPN);
        tombolPN.setLayout(tombolPNLayout);
        tombolPNLayout.setHorizontalGroup(
            tombolPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tombolPNLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tambahBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ubahBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(hapusBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(simpanBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(batalBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(keluarBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        tombolPNLayout.setVerticalGroup(
            tombolPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ubahBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(hapusBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(simpanBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tombolPNLayout.createSequentialGroup()
                .addComponent(tambahBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(keluarBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(batalBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane)
                    .addComponent(tombolPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dataPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(titlePN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(tombolPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        otomatis(); 
        aktif();     
        idTF.setEnabled(false);   
        namaTF.requestFocus();     
        simpanBT.setEnabled(true); 
        batalBT.setEnabled(true);    
        tambahBT.setEnabled(false);
    }//GEN-LAST:event_tambahBTActionPerformed

    private void maxiBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxiBTMouseEntered
        maxiBT.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_maxiBTMouseEntered

    private void maxiBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxiBTMouseExited
        maxiBT.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_maxiBTMouseExited

    private void maxiBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxiBTActionPerformed
        if(maximized){
            //handle fullscreen - taskbar
            FormEJabatan.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            FormEJabatan.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        }else{
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }//GEN-LAST:event_maxiBTActionPerformed

    private void tambahBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBTMouseEntered
        tambahBT.setBackground(new Color(255,255,255));
        tambahBT.setIcon(new ImageIcon(getClass().getResource("/icon/tambahJ.png")));
    }//GEN-LAST:event_tambahBTMouseEntered

    private void tambahBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBTMouseExited
        tambahBT.setBackground(new Color(204,204,0));
        tambahBT.setIcon(new ImageIcon(getClass().getResource("/icon/tambah.png")));
    }//GEN-LAST:event_tambahBTMouseExited

    private void ubahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahBTActionPerformed
        isi=false;    
        aktif();   
        ubahBT.setEnabled(false);      
        tambahBT.setEnabled(false);    
        simpanBT.setEnabled(true);   
        batalBT.setEnabled(true);
    }//GEN-LAST:event_ubahBTActionPerformed

    private void ubahBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahBTMouseEntered
        ubahBT.setBackground(new Color(255,255,255));
        ubahBT.setIcon(new ImageIcon(getClass().getResource("/icon/ubahJ.png")));
    }//GEN-LAST:event_ubahBTMouseEntered

    private void ubahBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahBTMouseExited
        ubahBT.setBackground(new Color(204,204,0));
        ubahBT.setIcon(new ImageIcon(getClass().getResource("/icon/ubah.png")));
    }//GEN-LAST:event_ubahBTMouseExited

    private void hapusBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBTActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Data akan dihapus secara permanen. Tetap Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            delete();
            formWindowActivated(null);
        }
    }//GEN-LAST:event_hapusBTActionPerformed

    private void hapusBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseEntered
        hapusBT.setBackground(new Color(255,255,255));
        hapusBT.setIcon(new ImageIcon(getClass().getResource("/icon/hapusJ.png")));
    }//GEN-LAST:event_hapusBTMouseEntered

    private void hapusBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseExited
        hapusBT.setBackground(new Color(204,204,0));
        hapusBT.setIcon(new ImageIcon(getClass().getResource("/icon/hapus.png")));
    }//GEN-LAST:event_hapusBTMouseExited

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
        if(isi==true){  
            simpan();    
        }else{          
            update();     
            isi=true;     
        }
    }//GEN-LAST:event_simpanBTActionPerformed

    private void simpanBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseEntered
        simpanBT.setBackground(new Color(255,255,255));
        simpanBT.setIcon(new ImageIcon(getClass().getResource("/icon/simpanJ.png")));
    }//GEN-LAST:event_simpanBTMouseEntered

    private void simpanBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseExited
        simpanBT.setBackground(new Color(204,204,0));
        simpanBT.setIcon(new ImageIcon(getClass().getResource("/icon/simpan.png")));
    }//GEN-LAST:event_simpanBTMouseExited

    private void batalBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalBTActionPerformed
        formWindowActivated(null);
    }//GEN-LAST:event_batalBTActionPerformed

    private void batalBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalBTMouseEntered
        batalBT.setBackground(new Color(255,255,255));
        batalBT.setIcon(new ImageIcon(getClass().getResource("/icon/batalJ.png")));
    }//GEN-LAST:event_batalBTMouseEntered

    private void batalBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalBTMouseExited
        batalBT.setBackground(new Color(204,204,0));
        batalBT.setIcon(new ImageIcon(getClass().getResource("/icon/batal.png")));
    }//GEN-LAST:event_batalBTMouseExited

    private void keluarBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarBTActionPerformed
        dispose();
    }//GEN-LAST:event_keluarBTActionPerformed

    private void keluarBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarBTMouseEntered
        keluarBT.setBackground(new Color(255,255,255));
        keluarBT.setIcon(new ImageIcon(getClass().getResource("/icon/keluarE.png")));
    }//GEN-LAST:event_keluarBTMouseEntered

    private void keluarBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarBTMouseExited
        keluarBT.setBackground(new Color(255,0,0));
        keluarBT.setIcon(new ImageIcon(getClass().getResource("/icon/keluar.png")));
    }//GEN-LAST:event_keluarBTMouseExited

    private void cariBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBTActionPerformed
        cari();
    }//GEN-LAST:event_cariBTActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        nonaktif();     
        bersih();   
        tabel();     
        tambahBT.setEnabled(true);  
        simpanBT.setEnabled(false);     
        ubahBT.setEnabled(false);     
        hapusBT.setEnabled(false);  
        batalBT.setEnabled(false);   
        keluarBT.setEnabled(true);
    }//GEN-LAST:event_formWindowActivated

    private void cariBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBTMouseEntered
        cariBT.setBackground(new Color(255,255,255));
        cariBT.setIcon(new ImageIcon(getClass().getResource("/icon/search15B.png")));
    }//GEN-LAST:event_cariBTMouseEntered

    private void cariBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBTMouseExited
        cariBT.setBackground(new Color(0,0,0));
        cariBT.setIcon(new ImageIcon(getClass().getResource("/icon/search15.png")));
    }//GEN-LAST:event_cariBTMouseExited

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
            java.util.logging.Logger.getLogger(FormEJabatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEJabatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEJabatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEJabatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormEJabatan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton batalBT;
    private keeptoo.KGradientPanel bg;
    private javax.swing.JButton cariBT;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton closeBT;
    private javax.swing.JPanel dataPN;
    private javax.swing.JLabel desc;
    private javax.swing.JTextField descTF;
    private javax.swing.JLabel gaji;
    private javax.swing.JTextField gajiTF;
    private javax.swing.JButton hapusBT;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel id;
    private javax.swing.JTextField idTF;
    private javax.swing.JTable jabatanTB;
    private javax.swing.JButton keluarBT;
    private javax.swing.JButton maxiBT;
    private javax.swing.JButton miniBT;
    private javax.swing.JLabel nama;
    private javax.swing.JTextField namaTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JButton tambahBT;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titlePN;
    private javax.swing.JPanel tombolPN;
    private javax.swing.JButton ubahBT;
    // End of variables declaration//GEN-END:variables
}
