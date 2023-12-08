/* Form Edit Data Karyawan
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


public final class FormEKaryawan extends javax.swing.JFrame {
    String user = "root";
    String pwd = "";
    String url = "jdbc:mysql://localhost:3306/penggajiandb";
    Boolean isi=true;
    private final Dimension screensize;
    static boolean maximized = true;
    int xMouse;
    int yMouse;

    /**
     * Creates new form FormEKaryawan
     */
    public FormEKaryawan() {
        initComponents();
        combo();
        screensize = Toolkit.getDefaultToolkit().getScreenSize(); 
        setLocation((screensize.width / 2) - (getSize().width / 2), (screensize.height / 2) - (getSize().height / 2));
    }
    
    //Method
    void aktif(){  
        iduserTF.setEnabled(true);
        namaTF.setEnabled(true);
        jenisCB.setEnabled(true);
        tempatTF.setEnabled(true);
        tanggalDC.setEnabled(true);
        agamaTF.setEnabled(true);
        idjabatanCB.setEnabled(true);
        statusCB.setEnabled(true);     
        kontakTF.setEnabled(true);
        alamatTF.setEnabled(true);
    } 
    void nonaktif(){   
        iduserTF.setEnabled(false);
        namaTF.setEnabled(false);
        jenisCB.setEnabled(false);
        tempatTF.setEnabled(false);
        tanggalDC.setEnabled(false);
        agamaTF.setEnabled(false);
        idjabatanCB.setEnabled(false);
        statusCB.setEnabled(false);     
        kontakTF.setEnabled(false);
        alamatTF.setEnabled(false);    
    }
    void bersih(){        
        iduserTF.setText("");
        namaTF.setText("");
        tempatTF.setText("");
        tanggalDC.setText("");
        agamaTF.setText("");
        kontakTF.setText("");
        alamatTF.setText("");    
    }
    //Auto Increment
    void otomatis(){   
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);      
            Statement st = (Statement) conn.createStatement();  
            String sql = "SELECT RIGHT(id_user,2)+1 FROM karyawan";    
            ResultSet rs = st.executeQuery(sql);    
            if (rs.next()){             
                rs.last();             
                String kode = rs.getString(1);      
                while (kode.length()<3){              
                    kode="0"+kode;                 
                    iduserTF.setText("52"+kode);       
                }           
            }else{      
                iduserTF.setText("52001");         
            }       
        }catch (SQLException e){      
        }   
    }
    void otomatispass(){   
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);      
            Statement st = (Statement) conn.createStatement();  
            String sql = "SELECT RIGHT(id_user,2)+1 FROM pengguna";    
            ResultSet rs = st.executeQuery(sql);    
            if (rs.next()){             
                rs.last();             
                String kode = rs.getString(1);      
                while (kode.length()<3){              
                    kode="0"+kode;                 
                    iduserTF.setText("52"+kode);       
                }           
            }else{      
                iduserTF.setText("52001");         
            }       
        }catch (SQLException e){      
        }   
    }
    //CRUD
    void simpan(){    
        try{            
            Connection conn=DriverManager.getConnection(url,user,pwd);            
            Statement st=conn.createStatement();        
            String sql="INSERT INTO karyawan VALUES('"+iduserTF.getText()+"','"+namaTF.getText()+"','"+jenisCB.getSelectedItem()+"','"+tempatTF.getText()+"','"+tanggalDC.getText()+"','"+agamaTF.getText()+"','"+idj.getText()+"','"+statusCB.getSelectedItem()+"','"+kontakTF.getText()+"','"+alamatTF.getText()+"')";       
            String sql2="INSERT INTO pengguna (nama) VALUES('"+namaTF.getText()+"')";
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
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
            String sql="UPDATE karyawan SET nama='"+namaTF.getText()+"', jenis_kelamin='"+jenisCB.getSelectedItem()+"', tempat_lahir='"+tempatTF.getText()+"', tanggal_lahir='"+tanggalDC.getText()+"', agama='"+agamaTF.getText()+"', id_jabatan='"+idj.getText()+"', status='"+statusCB.getSelectedItem()+"', kontak='"+kontakTF.getText()+"', alamat='"+alamatTF.getText()+"' WHERE id_user='"+iduserTF.getText()+"'";
            String sql2="UPDATE pengguna SET nama='"+namaTF.getText()+"' WHERE id_user='"+iduserTF.getText()+"'";
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
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
            String sql="DELETE FROM karyawan WHERE id_user='"+iduserTF.getText()+"'";
            String sql2="DELETE FROM pengguna WHERE id_user='"+iduserTF.getText()+"'";
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
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
            ResultSet rs=st.executeQuery("SELECT * FROM karyawan WHERE id_user='"+cariTF.getText()+"'");    
            if(rs.next()){             
                iduserTF.setText(rs.getString("id_user"));     
                namaTF.setText(rs.getString("nama"));
                tempatTF.setText(rs.getString("tempat_lahir"));
                tanggalDC.setText(rs.getString("tanggal_lahir"));
                agamaTF.setText(rs.getString("agama"));
                kontakTF.setText(rs.getString("kontak"));
                alamatTF.setText(rs.getString("alamat"));        
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
    //Combo Box Jabatan
    void combo(){   
        try {       
            Connection conn = DriverManager.getConnection(url,user,pwd);          
            Statement st = conn.createStatement();         
            String sql = "SELECT id_jabatan, nama_jabatan FROM jabatan";    
            ResultSet rs = st.executeQuery(sql);         
            while (rs.next()){         
                String data = rs.getString("nama_jabatan");           
                idjabatanCB.addItem(data);            
            }                  
        }catch (SQLException e) {    
            System.out.println(e.getMessage());    
        }               
    }
    public void combo_tampil()     {  
        try {       
            Connection con = DriverManager.getConnection(url,user,pwd);  
            Statement stt = con.createStatement();     
            String sql = "SELECT id_jabatan FROM jabatan WHERE nama_jabatan='"+idjabatanCB.getSelectedItem()+"'";   
            ResultSet res = stt.executeQuery(sql);           
            while(res.next()){          
            Object[] ob = new Object[1];      
            ob[0]= res.getString(1);    
            idj.setText((String) ob[0]);    
        }res.close(); stt.close();     
        } catch (SQLException e) {         
            System.out.println(e.getMessage());    
        }                 
    }
    //Tabel Karyawan
    void tabel(){    
        String[] columnNames={"ID User","ID Jabatan","Nama","Tempat Lahir","Tanggal Lahir","Jenis Kelamin","Agama","Status","Kontak","Alamat"};   
        JTable table=new JTable(getData(),columnNames);  
        table.setEnabled(false);
        ScrollPane.setViewportView(table);   
    }
    private Object[][] getData(){   
        Object[][] data=null;    
        try{           
            Connection conn=DriverManager.getConnection(url,user,pwd);          
            Statement st=conn.createStatement();    
            ResultSet rs=st.executeQuery("SELECT * FROM karyawan");
            rs.last();        
            int rowCount=rs.getRow();
            rs.beforeFirst();
            data=new Object[rowCount][10];       
            int no=-1;        
            while(rs.next()){         
                no=no+1;          
                data[no][0]=rs.getString("id_user");
                data[no][1]=rs.getString("id_jabatan");
                data[no][2]=rs.getString("nama");
                data[no][3]=rs.getString("tempat_lahir");     
                data[no][4]=rs.getString("tanggal_lahir");
                data[no][5]=rs.getString("jenis_kelamin");
                data[no][6]=rs.getString("agama");
                data[no][7]=rs.getString("status");     
                data[no][8]=rs.getString("kontak");
                data[no][9]=rs.getString("alamat");
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

        bg = new keeptoo.KGradientPanel();
        titlePN = new javax.swing.JPanel();
        closeBT = new javax.swing.JButton();
        miniBT = new javax.swing.JButton();
        maxiBT = new javax.swing.JButton();
        icon = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        karyawanTB = new javax.swing.JTable();
        dataPN = new javax.swing.JPanel();
        cariTF = new javax.swing.JTextField();
        cariBT = new javax.swing.JButton();
        ScrollPane2 = new javax.swing.JScrollPane();
        scrollPN = new javax.swing.JPanel();
        iduser = new javax.swing.JLabel();
        iduserTF = new javax.swing.JTextField();
        nama = new javax.swing.JLabel();
        namaTF = new javax.swing.JTextField();
        jenis = new javax.swing.JLabel();
        jenisCB = new javax.swing.JComboBox<>();
        tempat = new javax.swing.JLabel();
        tempatTF = new javax.swing.JTextField();
        tanggal = new javax.swing.JLabel();
        agama = new javax.swing.JLabel();
        agamaTF = new javax.swing.JTextField();
        idjabatan = new javax.swing.JLabel();
        idj = new javax.swing.JLabel();
        idjabatanCB = new javax.swing.JComboBox<>();
        status = new javax.swing.JLabel();
        statusCB = new javax.swing.JComboBox<>();
        kontak = new javax.swing.JLabel();
        kontakTF = new javax.swing.JTextField();
        alamat = new javax.swing.JLabel();
        alamatTF = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        tombolPN = new javax.swing.JPanel();
        tambahBT = new javax.swing.JButton();
        ubahBT = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        batalBT = new javax.swing.JButton();
        keluarBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Data Karyawan");
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        bg.setkEndColor(new java.awt.Color(255, 0, 0));
        bg.setkGradientFocus(2000);
        bg.setkStartColor(new java.awt.Color(0, 51, 153));
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
        title.setText("Aplikasi Penggajian | Edit Data Karyawan");

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

        karyawanTB.setBackground(new java.awt.Color(204, 204, 204));
        karyawanTB.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        karyawanTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID User", "Nama", "Jenis Kelamin", "Tempat Lahir", "Tanggal Lahir", "Agama", "ID Jabatan", "Status", "Kontak", "Alamat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        karyawanTB.setEnabled(false);
        karyawanTB.setGridColor(new java.awt.Color(0, 0, 0));
        karyawanTB.getTableHeader().setReorderingAllowed(false);
        ScrollPane.setViewportView(karyawanTB);
        if (karyawanTB.getColumnModel().getColumnCount() > 0) {
            karyawanTB.getColumnModel().getColumn(0).setResizable(false);
            karyawanTB.getColumnModel().getColumn(1).setResizable(false);
            karyawanTB.getColumnModel().getColumn(2).setResizable(false);
            karyawanTB.getColumnModel().getColumn(3).setResizable(false);
            karyawanTB.getColumnModel().getColumn(4).setResizable(false);
            karyawanTB.getColumnModel().getColumn(5).setResizable(false);
            karyawanTB.getColumnModel().getColumn(6).setResizable(false);
            karyawanTB.getColumnModel().getColumn(7).setResizable(false);
            karyawanTB.getColumnModel().getColumn(8).setResizable(false);
            karyawanTB.getColumnModel().getColumn(9).setResizable(false);
        }

        dataPN.setBackground(new java.awt.Color(0, 0, 0));
        dataPN.setMaximumSize(new java.awt.Dimension(300, 51));
        dataPN.setMinimumSize(new java.awt.Dimension(300, 51));

        cariTF.setBackground(new java.awt.Color(0, 0, 0));
        cariTF.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cariTF.setForeground(new java.awt.Color(255, 255, 255));
        cariTF.setText("Cari ID User");
        cariTF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        cariTF.setSelectionColor(new java.awt.Color(102, 0, 102));
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

        ScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        ScrollPane2.setBorder(null);
        ScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPN.setBackground(new java.awt.Color(0, 0, 0));

        iduser.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        iduser.setForeground(new java.awt.Color(255, 255, 255));
        iduser.setText("ID User");

        iduserTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        iduserTF.setSelectionColor(new java.awt.Color(102, 0, 102));

        nama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        nama.setForeground(new java.awt.Color(255, 255, 255));
        nama.setText("Nama");

        namaTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        namaTF.setSelectionColor(new java.awt.Color(102, 0, 102));

        jenis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jenis.setForeground(new java.awt.Color(255, 255, 255));
        jenis.setText("Jenis Kelamin");

        jenisCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jenis Kelamin", "Laki-Laki", "Perempuan" }));

        tempat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tempat.setForeground(new java.awt.Color(255, 255, 255));
        tempat.setText("Tempat Lahir");

        tempatTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tempatTF.setSelectionColor(new java.awt.Color(102, 0, 102));

        tanggal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tanggal.setForeground(new java.awt.Color(255, 255, 255));
        tanggal.setText("Tanggal Lahir");

        agama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        agama.setForeground(new java.awt.Color(255, 255, 255));
        agama.setText("Agama");

        agamaTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        agamaTF.setSelectionColor(new java.awt.Color(102, 0, 102));

        idjabatan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        idjabatan.setForeground(new java.awt.Color(255, 255, 255));
        idjabatan.setText("ID Jabatan :");

        idj.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        idj.setForeground(new java.awt.Color(255, 255, 255));
        idj.setText("ID");

        idjabatanCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jabatan" }));
        idjabatanCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idjabatanCBActionPerformed(evt);
            }
        });

        status.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        status.setForeground(new java.awt.Color(255, 255, 255));
        status.setText("Status");

        statusCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Status Akses", "Admin", "User" }));

        kontak.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        kontak.setForeground(new java.awt.Color(255, 255, 255));
        kontak.setText("Kontak");

        kontakTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kontakTF.setSelectionColor(new java.awt.Color(102, 0, 102));

        alamat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        alamat.setForeground(new java.awt.Color(255, 255, 255));
        alamat.setText("Alamat");

        alamatTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        alamatTF.setPreferredSize(new java.awt.Dimension(6, 25));
        alamatTF.setSelectionColor(new java.awt.Color(102, 0, 102));

        javax.swing.GroupLayout scrollPNLayout = new javax.swing.GroupLayout(scrollPN);
        scrollPN.setLayout(scrollPNLayout);
        scrollPNLayout.setHorizontalGroup(
            scrollPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollPNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scrollPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iduser)
                    .addComponent(tempat)
                    .addComponent(tanggal)
                    .addComponent(agama)
                    .addComponent(status)
                    .addComponent(kontak)
                    .addComponent(alamat)
                    .addGroup(scrollPNLayout.createSequentialGroup()
                        .addComponent(idjabatan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idj))
                    .addComponent(nama)
                    .addComponent(namaTF, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(tempatTF)
                    .addComponent(agamaTF)
                    .addComponent(kontakTF)
                    .addComponent(alamatTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iduserTF)
                    .addComponent(idjabatanCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, scrollPNLayout.createSequentialGroup()
                        .addGroup(scrollPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jenisCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jenis)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        scrollPNLayout.setVerticalGroup(
            scrollPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollPNLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(iduser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iduserTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(scrollPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idjabatan)
                    .addComponent(idj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idjabatanCB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempatTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tanggal)
                .addGroup(scrollPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scrollPNLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jenis))
                    .addGroup(scrollPNLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jenisCB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(agama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agamaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusCB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kontak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kontakTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alamat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alamatTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ScrollPane2.setViewportView(scrollPN);

        javax.swing.GroupLayout dataPNLayout = new javax.swing.GroupLayout(dataPN);
        dataPN.setLayout(dataPNLayout);
        dataPNLayout.setHorizontalGroup(
            dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPNLayout.createSequentialGroup()
                .addGroup(dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataPNLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariBT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataPNLayout.setVerticalGroup(
            dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPNLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(dataPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariBT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(ScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        tombolPN.setBackground(new java.awt.Color(0, 0, 0));

        tambahBT.setBackground(new java.awt.Color(102, 0, 102));
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

        ubahBT.setBackground(new java.awt.Color(102, 0, 102));
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

        hapusBT.setBackground(new java.awt.Color(102, 0, 102));
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

        simpanBT.setBackground(new java.awt.Color(102, 0, 102));
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

        batalBT.setBackground(new java.awt.Color(102, 0, 102));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
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
                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
                    .addComponent(tombolPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        otomatispass();
        aktif();     
        iduserTF.setEnabled(false);   
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
            FormEKaryawan.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            FormEKaryawan.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        }else{
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }//GEN-LAST:event_maxiBTActionPerformed

    private void tambahBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBTMouseEntered
        tambahBT.setBackground(new Color(255,255,255));
        tambahBT.setIcon(new ImageIcon(getClass().getResource("/icon/tambahK.png")));
    }//GEN-LAST:event_tambahBTMouseEntered

    private void tambahBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBTMouseExited
        tambahBT.setBackground(new Color(102,0,102));
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
        ubahBT.setIcon(new ImageIcon(getClass().getResource("/icon/ubahK.png")));
    }//GEN-LAST:event_ubahBTMouseEntered

    private void ubahBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahBTMouseExited
        ubahBT.setBackground(new Color(102,0,102));
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
        hapusBT.setIcon(new ImageIcon(getClass().getResource("/icon/hapusK.png")));
    }//GEN-LAST:event_hapusBTMouseEntered

    private void hapusBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseExited
        hapusBT.setBackground(new Color(102,0,102));
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
        simpanBT.setIcon(new ImageIcon(getClass().getResource("/icon/simpanK.png")));
    }//GEN-LAST:event_simpanBTMouseEntered

    private void simpanBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseExited
        simpanBT.setBackground(new Color(102,0,102));
        simpanBT.setIcon(new ImageIcon(getClass().getResource("/icon/simpan.png")));
    }//GEN-LAST:event_simpanBTMouseExited

    private void batalBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalBTActionPerformed
        formWindowActivated(null);
    }//GEN-LAST:event_batalBTActionPerformed

    private void batalBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalBTMouseEntered
        batalBT.setBackground(new Color(255,255,255));
        batalBT.setIcon(new ImageIcon(getClass().getResource("/icon/batalK.png")));
    }//GEN-LAST:event_batalBTMouseEntered

    private void batalBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalBTMouseExited
        batalBT.setBackground(new Color(102,0,102));
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

    private void idjabatanCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idjabatanCBActionPerformed
        combo_tampil();
    }//GEN-LAST:event_idjabatanCBActionPerformed

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
            java.util.logging.Logger.getLogger(FormEKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormEKaryawan().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JScrollPane ScrollPane2;
    private javax.swing.JLabel agama;
    private javax.swing.JTextField agamaTF;
    private javax.swing.JLabel alamat;
    private javax.swing.JTextField alamatTF;
    private javax.swing.JButton batalBT;
    private keeptoo.KGradientPanel bg;
    private javax.swing.JButton cariBT;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton closeBT;
    private javax.swing.JPanel dataPN;
    private javax.swing.JButton hapusBT;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel idj;
    private javax.swing.JLabel idjabatan;
    private javax.swing.JComboBox<String> idjabatanCB;
    private javax.swing.JLabel iduser;
    private javax.swing.JTextField iduserTF;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jenis;
    private javax.swing.JComboBox<String> jenisCB;
    private javax.swing.JTable karyawanTB;
    private javax.swing.JButton keluarBT;
    private javax.swing.JLabel kontak;
    private javax.swing.JTextField kontakTF;
    private javax.swing.JButton maxiBT;
    private javax.swing.JButton miniBT;
    private javax.swing.JLabel nama;
    private javax.swing.JTextField namaTF;
    private javax.swing.JPanel scrollPN;
    private javax.swing.JButton simpanBT;
    private javax.swing.JLabel status;
    private javax.swing.JComboBox<String> statusCB;
    private javax.swing.JButton tambahBT;
    private javax.swing.JLabel tanggal;
    private javax.swing.JLabel tempat;
    private javax.swing.JTextField tempatTF;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titlePN;
    private javax.swing.JPanel tombolPN;
    private javax.swing.JButton ubahBT;
    // End of variables declaration//GEN-END:variables


        private static String getText() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void setText(String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    private static class tanggalDC {

        private static void setText(String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static String getText() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void setEnabled(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public tanggalDC() {
        }
    }

}
