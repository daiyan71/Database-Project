/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectMSSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daiya
 */
public class SPOrders extends javax.swing.JFrame {

    /**
     * Creates new form SPOrders
     */
    public SPOrders() {
        initComponents();
    }
    
    public SPOrders(String ID) {
        initComponents();
        
        spId=ID;
        System.out.println("in show");
        ArrayList<List2> listt=SPlist2();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        Object[] row = new Object[8];
        System.out.println("list er size"+listt.size()+"  "+spId);
        if(listt.size()!=0)
        {
         for(int i=listt.size()-1;i!=0;i--)
        {
            row[0]=i+1;
            row[1]=listt.get(i).getsName();
            row[3]=listt.get(i).getSpName();
            row[2]=listt.get(i).getsRate();
            row[4]=listt.get(i).getEmail();
            row[5]=listt.get(i).getPhone();
            row[6]=listt.get(i).getAddrs();
            row[7]=listt.get(i).getDateTime();
            
            System.out.println("\nIn for");
            model.addRow(row);
        }
        }
         
         
         
         
         
        ArrayList<List3> listt1=SPlist3();
        DefaultTableModel model1=(DefaultTableModel)jTable2.getModel();
        Object[] row1 = new Object[8];
        System.out.println("list er size"+listt1.size()+"  "+spId);
        if(listt1.size()!=0)
        {
         for(int i=listt1.size()-1;i!=0;i--)
        {
            row1[0]=i+1;
            row1[1]=listt1.get(i).getCarBrand();
            row1[2]=listt1.get(i).getCarModel();
            row1[3]=listt1.get(i).getRentPrice();
            row1[4]=listt1.get(i).getSpName();
            row1[5]=listt1.get(i).getPhone();
            row1[6]=listt1.get(i).getAddrs();
            row1[7]=listt1.get(i).getDatenTime();
            System.out.println("\nIn for");
            model1.addRow(row1);
        }
        }
    }
    
    
    
    public ArrayList<List2> SPlist2()
     {
           ArrayList<List2> splist2=new ArrayList<>();
           
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
            Connection con = DriverManager.getConnection(url);
            String sql = "Select ServiceName,ServiceRate,UserTable.FirstName+' '+UserTable.LastName as spName,UserTable.Phone,UserTable.UserAddress,UserTable.Email,OrderTable.DateAndTime from UserTable inner join OrderTable on UserTable.UserId=OrderTable.UserId inner join \n" +
"CarService on OrderTable.CarServiceId=CarService.CarServiceId inner join ServiceTable on  ServiceTable.CarServiceId=CarService.CarServiceId where ServiceProviderId=? order by OrderId";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, spId);
            ResultSet rs = pst.executeQuery();
            List2 list2;
            while(rs.next()){
                list2=new List2(rs.getString("ServiceName"),rs.getString("ServiceRate"),rs.getString("spName"),rs.getString("DateAndTime"),rs.getString("UserAddress"),rs.getString("Email"),rs.getString("Phone"));
                splist2.add(list2);
            }
                
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
           
           
           return splist2;
     }
    
    
    
    
    
    public ArrayList<List3> SPlist3()
     {
           ArrayList<List3> splist3=new ArrayList<>();
           
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
            Connection con = DriverManager.getConnection(url);
            String sql = "Select CarBrand,CarModel,RentPrice,UserTable.FirstName+' '+UserTable.LastName as spName,UserTable.Phone,UserTable.UserAddress,DateAndTime from UserTable\n" +
                         "inner join OrderTable on UserTable.UserId=OrderTable.UserId\n" +
                         "inner join CarRent on OrderTable.CarRentId=CarRent.CarRentId\n" +
                         "inner join Car on Car.CarRentId=CarRent.CarRentId where ServiceProviderId=? order by OrderId";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, spId);
            ResultSet rs = pst.executeQuery();
            List3 list3;
            while(rs.next()){
                list3=new List3(rs.getString("CarBrand"),rs.getString("CarModel"),rs.getString("RentPrice"),rs.getString("spName"),rs.getString("DateAndTime"),rs.getString("UserAddress"),rs.getString("Phone"));
                splist3.add(list3);
            }
                
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
           
           
           return splist3;
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1262, 800));

        jPanel1.setBackground(new java.awt.Color(224, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 1100));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Car Services");

        jTable1.setBackground(new java.awt.Color(204, 255, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No.", "Service Name", "Amount", "Customer Name", "Customer Email", "Customer Phone", "Customer Address", "Date and time"
            }
        ));
        jTable1.setRowHeight(30);
        jTable1.setRowMargin(8);
        jScrollPane2.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Car Rent");

        jTable2.setBackground(new java.awt.Color(204, 255, 204));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No.", "Car Brand", "Car Model", "Rent Price", "Customer Name", "Phone", "Address", "Date and Time"
            }
        ));
        jTable2.setRowHeight(30);
        jTable2.setRowMargin(8);
        jScrollPane3.setViewportView(jTable2);

        jButton1.setBackground(new java.awt.Color(102, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BACK");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(505, 505, 505))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(496, 496, 496))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        ServiceProviderHome s = new ServiceProviderHome(spId);
        s.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    String spId;
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
            java.util.logging.Logger.getLogger(SPOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SPOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SPOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SPOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SPOrders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
