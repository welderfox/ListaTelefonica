/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ListaTelefones.telas;

import br.com.ListaTelefones.conexaobd.ModuloConexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * Welder
 */
public class TelaInicial extends javax.swing.JFrame {

    Connection conexao = null;
    ResultSet rs = null;

    public TelaInicial() {

        //boolean cx = true;
        initComponents();
        Connection co = ModuloConexao.conector();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/br/com/ListaTelefones/icones/phone 24x24.png"));
        setIconImage(icon.getImage());

        if (co != null) {
            lbll_conecxao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/database24x24_conected.png")));
        } else {
            lbll_conecxao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/database24x24_lost.png")));
        }

        //centraliza tela
        setLocationRelativeTo(null);

        prencherTabela();
    }
    private String tipo;

    public void prencherTabela() {

        StringBuilder sql = new StringBuilder();

        ResultSet resultado;

        sql.append("select loja as 'NºLoja',fone as 'NºTelefone',"
                + "cell as 'NºCelular' from tb_contatoloja where loja like ?");

        try {
            Connection connection = ModuloConexao.conector();
            PreparedStatement comando = connection.prepareStatement(sql.toString());
            comando.setString(1, txt_pesquisa.getText() + "%");
            resultado = comando.executeQuery();
            tb_contatoloja.setModel(DbUtils.resultSetToTableModel(resultado));

        } catch (java.lang.NullPointerException e) {
            //Exibe mesagem caso banco esteja fora.
            JOptionPane.showMessageDialog(null, "Sem conexão com banco de dados");

        } catch (HeadlessException | SQLException f) {

            JOptionPane.showMessageDialog(null, f);
            //System.out.println("" + f);
        }
    }

    public void preenchertabelaCentral() {

        StringBuilder sql = new StringBuilder();

        ResultSet resultado;

        sql.append("select nome as Nome,fone as 'Nº Telefone',"
                + "dep as Setor from tb_contatocentral where nome like ?");

        try {
            Connection connection = ModuloConexao.conector();
            PreparedStatement comando = connection.prepareStatement(sql.toString());
            comando.setString(1, txt_pesquisa.getText() + "%");
            resultado = comando.executeQuery();
            tb_contatoloja.setModel(DbUtils.resultSetToTableModel(resultado));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    private void imprime (){
//        Connection co = ModuloConexao.conector();
//        
//        int confirma = JOptionPane.showConfirmDialog(null, "Confirma impressão da Lista", "Atenção", JOptionPane.YES_NO_OPTION);
//        if (confirma == JOptionPane.YES_OPTION) {
//            //imprime relatorio
//            try {
//                //Usando a clase jasperprint para preparar impressão de um relatorio
//                JasperPrint print = JasperFillManager.fillReport("C:/Telefones/Lista/ListaTelefones.jasper",null,co);
//                //exibi o relatorio atraves da classe JasperViwer
//                JasperViewer.viewReport(print,false);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        }
//    
//    }
//    private void imprimeCentral (){
//        Connection co = ModuloConexao.conector();
//        
//        int confirma = JOptionPane.showConfirmDialog(null, "Confirma impressão da Lista", "Atenção", JOptionPane.YES_NO_OPTION);
//        if (confirma == JOptionPane.YES_OPTION) {
//            //imprime relatorio
//            try {
//                //Usando a clase jasperprint para preparar impressão de um relatorio
//                JasperPrint print = JasperFillManager.fillReport("C:/Telefones/Lista/ListaTelefones.jasper",null,co);
//                //exibi o relatorio atraves da classe JasperViwer
//                JasperViewer.viewReport(print,false);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        }
//    
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_contatoloja = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_pesquisa = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        lbll_conecxao = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        RB_lojas = new javax.swing.JRadioButton();
        RB_escritorio = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LISTA TELEFONICA");
        setBackground(new java.awt.Color(35, 73, 102));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_contatoloja.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tb_contatoloja.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tb_contatoloja.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
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
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_contatoloja.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tb_contatoloja.setCellSelectionEnabled(true);
        tb_contatoloja.setEnabled(false);
        jScrollPane1.setViewportView(tb_contatoloja);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 117, 653, 384));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PESQUISAR:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 79, -1, -1));

        txt_pesquisa.setToolTipText("Digite Nº da Loja");
        txt_pesquisa.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(241, 143, 103)));
        txt_pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisaActionPerformed(evt);
            }
        });
        txt_pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(txt_pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 75, 206, 24));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 566, 673, 10));

        jLabel2.setText("Conexão DB");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 592, -1, -1));

        lbll_conecxao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbll_conecxao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/database24x24_conected.png"))); // NOI18N
        lbll_conecxao.setToolTipText("Mysql");
        getContentPane().add(lbll_conecxao, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 582, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/logo-bh-white64x64.png"))); // NOI18N
        jLabel4.setToolTipText("SUPERMERCADOS BH");
        jLabel4.setPreferredSize(new java.awt.Dimension(64, 64));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 11, 113, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/phone 24x24.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 75, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(RB_lojas);
        RB_lojas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        RB_lojas.setText("Lojas");
        RB_lojas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RB_lojas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB_lojasActionPerformed(evt);
            }
        });

        buttonGroup1.add(RB_escritorio);
        RB_escritorio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        RB_escritorio.setText("Escritorio");
        RB_escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RB_escritorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB_escritorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RB_lojas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(RB_escritorio)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_lojas)
                    .addComponent(RB_escritorio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 23, -1, -1));

        jMenuBar1.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                jMenuBar1AncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });

        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Cadastro Lojas/Central");
        jMenuItem1.setAutoscrolls(true);
        jMenuItem1.setBorderPainted(true);
        jMenuItem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuItem1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem3.setText("Imprimir Lista de Telefones");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opções");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // sair do sistema
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txt_pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisaActionPerformed
        // Configurando campo de pesquisa:
        if (tipo.equals("loja")) {
            prencherTabela();
        } else {
            preenchertabelaCentral();
        }
    }//GEN-LAST:event_txt_pesquisaActionPerformed

    private void txt_pesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pesquisaKeyReleased
        // TODO add your handling code here:
        if (tipo.equals("loja")) {
            prencherTabela();
        } else {
            preenchertabelaCentral();
        }
    }//GEN-LAST:event_txt_pesquisaKeyReleased

    private void RB_escritorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB_escritorioActionPerformed
        // TODO add your handling code here:
        RB_escritorio.setSelected(true);
        tipo = "central";
        preenchertabelaCentral();
        //System.out.println(""+tipo);
    }//GEN-LAST:event_RB_escritorioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        CadTelefones tela = new CadTelefones();
        tela.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Configurando Radion buton
        RB_lojas.setSelected(true);
        tipo = "loja";

        Connection co = ModuloConexao.conector();
        if (co != null) {
            lbll_conecxao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/database24x24_conected.png")));
        } else {
            lbll_conecxao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/database24x24_lost.png")));
            JOptionPane.showMessageDialog(null, "Sem conexão com banco de dados");
            //Fecha o programa
            int sair = JOptionPane.showConfirmDialog(null, "Deseja fechar aplicação ?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (sair == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void RB_lojasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB_lojasActionPerformed
        // Configurando Radion buton
        RB_lojas.setSelected(true);
        tipo = "loja";
        prencherTabela();
        //System.out.println(""+tipo);
    }//GEN-LAST:event_RB_lojasActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //imprime
        TelaImprime imp = new TelaImprime();
        imp.setVisible(true);
        //imprime();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuBar1AncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jMenuBar1AncestorMoved
        // mover programa 

    }//GEN-LAST:event_jMenuBar1AncestorMoved

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
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RB_escritorio;
    private javax.swing.JRadioButton RB_lojas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbll_conecxao;
    private javax.swing.JTable tb_contatoloja;
    private javax.swing.JTextField txt_pesquisa;
    // End of variables declaration//GEN-END:variables
}
