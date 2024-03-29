/* Form Edit Password
*/
package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class FormEPass extends javax.swing.JFrame {
    String user="root"; 
    String pwd=""; 
    String url="jdbc:mysql://localhost:3306/penggajiandb"; 
    private Dimension screensize;
    
    /**
     * Creates new form FormEPass
     */
    public FormEPass() {
        initComponents();
        data();
        screensize = Toolkit.getDefaultToolkit().getScreenSize(); 
        setLocation((screensize.width / 2) - (getSize().width / 2), (screensize.height / 2) - (getSize().height / 2));
    }
    
    //Method
    void bersih(){
        passlTF.setText("");
        passbTF.setText("");
        konfirmTF.setText("");
    }
    void data(){
        FormLoginT login = new FormLoginT();
        try {       
            Connection con = DriverManager.getConnection(url,user,pwd);  
            Statement stt = con.createStatement();     
            String sql = "SELECT nama, id_jabatan, penerbit, jml_diperpus, jml_dipinjam, statistik FROM karyawan WHERE id_user='"+login.getUserTF().getText()+"'";   
            ResultSet res = stt.executeQuery(sql);           
            while(res.next()){          
            Object[] ob = new Object[6];      
            ob[0]=  res.getString(1);          
            ob[1]= res.getString(2);       
            ob[2]= res.getString(3);        
            ob[3]= res.getString(4);         
            ob[4]= res.getString(5);        
            ob[5]= res.getString(6);    
        }         res.close(); stt.close();     
        } catch (Exception e) {         
            System.out.println(e.getMessage());    
        }
    }
    void simpan(){
        try{        
            FormMenuT menu = new FormMenuT();
            Connection conn=DriverManager.getConnection(url,user,pwd);    
            Statement st=conn.createStatement();
            String sql="update pengguna set kata_sandi='" +passbTF.getText()+ "' where id_user='" +menu.getId_user().getText()+"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"Kata Sandi Berhasil Diganti","Informasi",JOptionPane.INFORMATION_MESSAGE);
            bersih();
            System.out.println(sql);
        }catch(SQLException e){          
            bersih();
            System.out.println("Koneksi Gagal"+e.toString());   
        }
        formWindowActivated(null);
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
        title = new javax.swing.JLabel();
        passlama = new javax.swing.JLabel();
        passlTF = new javax.swing.JPasswordField();
        spasslBT = new javax.swing.JToggleButton();
        passbaru = new javax.swing.JLabel();
        passbTF = new javax.swing.JPasswordField();
        spassbBT = new javax.swing.JToggleButton();
        konfirm = new javax.swing.JLabel();
        konfirmTF = new javax.swing.JPasswordField();
        spasskBT = new javax.swing.JToggleButton();
        simpanBT = new javax.swing.JButton();
        batalBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Password");
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        bg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bg.setkEndColor(new java.awt.Color(0, 102, 0));
        bg.setkStartColor(new java.awt.Color(0, 0, 0));

        title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/epass.png"))); // NOI18N

        passlama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        passlama.setForeground(new java.awt.Color(255, 255, 255));
        passlama.setText("Password Lama");

        passlTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passlTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        passlTF.setSelectionColor(new java.awt.Color(0, 102, 0));

        spasslBT.setBackground(new java.awt.Color(255, 255, 255));
        spasslBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/spassB.png"))); // NOI18N
        spasslBT.setContentAreaFilled(false);
        spasslBT.setOpaque(true);
        spasslBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spasslBTActionPerformed(evt);
            }
        });

        passbaru.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        passbaru.setForeground(new java.awt.Color(255, 255, 255));
        passbaru.setText("Password Baru");

        passbTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passbTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        passbTF.setSelectionColor(new java.awt.Color(0, 102, 0));

        spassbBT.setBackground(new java.awt.Color(255, 255, 255));
        spassbBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/spassB.png"))); // NOI18N
        spassbBT.setContentAreaFilled(false);
        spassbBT.setOpaque(true);
        spassbBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spassbBTActionPerformed(evt);
            }
        });

        konfirm.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        konfirm.setForeground(new java.awt.Color(255, 255, 255));
        konfirm.setText("Konfirmasi");

        konfirmTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        konfirmTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        konfirmTF.setSelectionColor(new java.awt.Color(0, 102, 0));

        spasskBT.setBackground(new java.awt.Color(255, 255, 255));
        spasskBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/spassB.png"))); // NOI18N
        spasskBT.setContentAreaFilled(false);
        spasskBT.setOpaque(true);
        spasskBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spasskBTActionPerformed(evt);
            }
        });

        simpanBT.setBackground(new java.awt.Color(255, 255, 255));
        simpanBT.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        simpanBT.setForeground(new java.awt.Color(255, 255, 255));
        simpanBT.setText("SIMPAN");
        simpanBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        simpanBT.setContentAreaFilled(false);
        simpanBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        batalBT.setBackground(new java.awt.Color(255, 255, 255));
        batalBT.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        batalBT.setForeground(new java.awt.Color(255, 255, 255));
        batalBT.setText("BATAL");
        batalBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 2));
        batalBT.setContentAreaFilled(false);
        batalBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passlama)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passbTF)
                                    .addComponent(konfirmTF)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(konfirm)
                                            .addComponent(passbaru))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(0, 0, 0)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spassbBT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spasskBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(batalBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(simpanBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(passlTF)
                                .addGap(0, 0, 0)
                                .addComponent(spasslBT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(passlama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spasslBT, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(passlTF))
                .addGap(18, 18, 18)
                .addComponent(passbaru)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spassbBT, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(passbTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(konfirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spasskBT, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(konfirmTF))
                .addGap(20, 20, 20)
                .addComponent(simpanBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(batalBT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
        simpan();
    }//GEN-LAST:event_simpanBTActionPerformed

    private void simpanBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseEntered
        simpanBT.setOpaque(true);
        simpanBT.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_simpanBTMouseEntered

    private void simpanBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseExited
        simpanBT.setOpaque(false);
        simpanBT.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_simpanBTMouseExited

    private void batalBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalBTActionPerformed
        this.dispose();
    }//GEN-LAST:event_batalBTActionPerformed

    private void batalBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalBTMouseEntered
        batalBT.setOpaque(true);
        batalBT.setForeground(new Color(255,0,0));
    }//GEN-LAST:event_batalBTMouseEntered

    private void batalBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalBTMouseExited
        batalBT.setOpaque(false);
        batalBT.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_batalBTMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void spasslBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spasslBTActionPerformed
        if(spasslBT.isSelected()){
            passlTF.setEchoChar((char)0);
            spasslBT.setOpaque(true);
            spasslBT.setBackground(new Color(0,0,0));
            spasslBT.setIcon(new ImageIcon(getClass().getResource("/icon/spassW.png")));
        }else{
            passlTF.setEchoChar('*');
            spasslBT.setBackground(new Color(255,255,255));
            spasslBT.setIcon(new ImageIcon(getClass().getResource("/icon/spassB.png")));
        }
    }//GEN-LAST:event_spasslBTActionPerformed

    private void spassbBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spassbBTActionPerformed
        if(spassbBT.isSelected()){
            passbTF.setEchoChar((char)0);
            spassbBT.setOpaque(true);
            spassbBT.setBackground(new Color(0,0,0));
            spassbBT.setIcon(new ImageIcon(getClass().getResource("/icon/spassW.png")));
        }else{
            passbTF.setEchoChar('*');
            spassbBT.setBackground(new Color(255,255,255));
            spassbBT.setIcon(new ImageIcon(getClass().getResource("/icon/spassB.png")));
        }
    }//GEN-LAST:event_spassbBTActionPerformed

    private void spasskBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spasskBTActionPerformed
        if(spasskBT.isSelected()){
            konfirmTF.setEchoChar((char)0);
            spasskBT.setOpaque(true);
            spasskBT.setBackground(new Color(0,0,0));
            spasskBT.setIcon(new ImageIcon(getClass().getResource("/icon/spassW.png")));
        }else{
            konfirmTF.setEchoChar('*');
            spasskBT.setBackground(new Color(255,255,255));
            spasskBT.setIcon(new ImageIcon(getClass().getResource("/icon/spassB.png")));
        }
    }//GEN-LAST:event_spasskBTActionPerformed

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
            java.util.logging.Logger.getLogger(FormEPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormEPass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batalBT;
    private keeptoo.KGradientPanel bg;
    private javax.swing.JLabel konfirm;
    private javax.swing.JPasswordField konfirmTF;
    private javax.swing.JPasswordField passbTF;
    private javax.swing.JLabel passbaru;
    private javax.swing.JPasswordField passlTF;
    private javax.swing.JLabel passlama;
    private javax.swing.JButton simpanBT;
    private javax.swing.JToggleButton spassbBT;
    private javax.swing.JToggleButton spasskBT;
    private javax.swing.JToggleButton spasslBT;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
