/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ListaTelefones.telas;

import br.com.ListaTelefones.conexaobd.ModuloConexao;
import br.com.ListaTelefones.dao.TelefonesDao;
import br.com.ListaTelefones.model.TelefoneCentral;
import br.com.ListaTelefones.model.Telefones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author teste
 */
public class CadTelefones extends javax.swing.JFrame {

    /**
     * Creates new form CadTelefones
     */
    public CadTelefones() {
        initComponents();
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/br/com/ListaTelefones/icones/phone 24x24.png"));
        setIconImage(icon.getImage());

    }
    //criando variavel um texto de acordo com o radio boton selecionado

    public String tipo;

    public void salvar(Telefones tel) {

        TelefonesDao tell = new TelefonesDao();

        tel.setLoja(txt_loja.getText());
        tel.setFone(txt_telefone.getText());
        tel.setCell(txt_celular.getText());

        if (txt_loja.getText().isEmpty() || txt_telefone.getText().isEmpty() || txt_celular.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        } else {
            tell.salvar(tel);

            JOptionPane.showMessageDialog(null, "Telefone salvo!");

        }
        txt_loja.setText("");
        txt_telefone.setText("");
        txt_celular.setText("");

    }

    public void salvar_central(TelefoneCentral telCentral) {

        TelefonesDao central = new TelefonesDao();
        telCentral.setNome(txt_nome.getText());
        telCentral.setFone(txt_telefone.getText());
        telCentral.setSetor(cbx_setor.getSelectedItem().toString());

        if (txt_nome.getText().isEmpty() || txt_telefone.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        } else {
            central.salvarCentral(telCentral);

            JOptionPane.showMessageDialog(null, "Contato salvo!");

        }

        txt_nome.setText("");
        txt_telefone.setText("");

        txt_nome.setEnabled(false);
        cbx_setor.setEnabled(true);// feito coreção do combobox q não habilitava depois de salvar contato

    }

    public void apagar(Telefones tele) {
        
        if (lbl_id.getText().isEmpty() || txt_loja.getText().isEmpty() || txt_telefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um contato!");
        } else {
            TelefonesDao del = new TelefonesDao();

        tele.setIduser(lbl_id.getText());

        del.delete(tele);
        }
        

    }

    public void apagarCentral(Telefones central) {
        if (lbl_id.getText().isEmpty() || txt_nome.getText().isEmpty() || txt_telefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um contato!");
        } else {
            TelefonesDao delete = new TelefonesDao();

        central.setIduser(lbl_id.getText());

        delete.deleteCentral(central);
        }

        
    }

    public void prencherTabela() {

        StringBuilder sql = new StringBuilder();

        ResultSet resultado;

        sql.append("select iduser as ID, loja as 'NºLoja',fone as 'NºTelefone',"
                + "cell as 'NºCelular' from tb_contatoloja where loja like ?");

        try {
            Connection connection = ModuloConexao.conector();
            PreparedStatement comando = connection.prepareStatement(sql.toString());
            comando.setString(1, txt_consulta.getText() + "%");
            resultado = comando.executeQuery();
            tbl_consulta.setModel(DbUtils.resultSetToTableModel(resultado));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void preenchertabelaCentral() {

        StringBuilder sql = new StringBuilder();

        ResultSet resultado;

        sql.append("select iduser as ID, nome as Nome,fone as 'NºTelefone',"
                + "dep as Setor from tb_contatocentral where nome like ?");

        try {
            Connection connection = ModuloConexao.conector();
            PreparedStatement comando = connection.prepareStatement(sql.toString());
            comando.setString(1, txt_consulta.getText() + "%");
            resultado = comando.executeQuery();
            tbl_consulta.setModel(DbUtils.resultSetToTableModel(resultado));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setar_campos() {

        int setar = tbl_consulta.getSelectedRow();

        lbl_id.setText(tbl_consulta.getModel().getValueAt(setar, 0).toString());
        txt_loja.setText(tbl_consulta.getModel().getValueAt(setar, 1).toString());
        txt_telefone.setText(tbl_consulta.getModel().getValueAt(setar, 2).toString());
        txt_celular.setText(tbl_consulta.getModel().getValueAt(setar, 3).toString());

    }

    public void setarCentral() {
        int setar = tbl_consulta.getSelectedRow();

        lbl_id.setText(tbl_consulta.getModel().getValueAt(setar, 0).toString());
        txt_nome.setText(tbl_consulta.getModel().getValueAt(setar, 1).toString());
        txt_telefone.setText(tbl_consulta.getModel().getValueAt(setar, 2).toString());
        cbx_setor.setSelectedItem(tbl_consulta.getModel().getValueAt(setar, 3).toString());

    }

    public void limpar() {

        lbl_id.setText("");
        txt_loja.setText("");
        txt_telefone.setText("");
        txt_celular.setText("");
    }

    public void limparCentral() {

        txt_nome.setText("");
        txt_telefone.setText("");
    }

    public void atualizar(Telefones contato) {
        if (lbl_id.getText().isEmpty() || txt_loja.getText().isEmpty() || txt_telefone.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Selecione um contato!");

        } else {
            TelefonesDao atu = new TelefonesDao();

            contato.setLoja(txt_loja.getText());
            contato.setFone(txt_telefone.getText());
            contato.setCell(txt_celular.getText());
            contato.setIduser(lbl_id.getText());

            atu.update(contato);
        }

    }

    public void atualizarCentral(Telefones contatoCentral) {
        if (lbl_id.getText().isEmpty() || txt_nome.getText().isEmpty() || txt_telefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um contato!");
        } else {
            TelefonesDao atualiza = new TelefonesDao();

            contatoCentral.setNome(txt_nome.getText());
            contatoCentral.setFone(txt_telefone.getText());
            contatoCentral.setSetor(cbx_setor.getSelectedItem().toString());
            contatoCentral.setIduser(lbl_id.getText());

            atualiza.updateCentral(contatoCentral);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupCadastro = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_loja = new javax.swing.JTextField();
        txt_telefone = new javax.swing.JFormattedTextField();
        txt_celular = new javax.swing.JFormattedTextField();
        btn_salvar = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        cbx_setor = new javax.swing.JComboBox<>();
        lbl_id = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_consulta = new javax.swing.JTable();
        txt_consulta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 64), new java.awt.Dimension(0, 64), new java.awt.Dimension(32767, 64));
        jPanel1 = new javax.swing.JPanel();
        rbtn_loja = new javax.swing.JRadioButton();
        rbtn_central = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(172, 0, 19));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CADASTRO DE TELEFONES");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/Phone32x32.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LOJA:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TELEFONE:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CELULAR:");

        txt_loja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lojaActionPerformed(evt);
            }
        });

        try {
            txt_telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txt_celular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)-#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/Salvar_disk24x24.png"))); // NOI18N
        btn_salvar.setToolTipText("SALVAR");
        btn_salvar.setBorderPainted(false);
        btn_salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salvar.setInheritsPopupMenu(true);
        btn_salvar.setPreferredSize(new java.awt.Dimension(24, 24));
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/delete24x24.png"))); // NOI18N
        jToggleButton2.setToolTipText("DELETAR");
        jToggleButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton2.setPreferredSize(new java.awt.Dimension(24, 24));
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/refresh24x24.png"))); // NOI18N
        jToggleButton4.setToolTipText("ATUALIZA");
        jToggleButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton4.setPreferredSize(new java.awt.Dimension(24, 24));
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/logo-bh-white64x64.png"))); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(64, 64));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("NOME:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("SETOR:");

        txt_nome.setEnabled(false);

        cbx_setor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AUDITORIA", "RECEPCAO", "DIRETORIA", "FINANCEIRO", "FECHAMENTO CX", "CONTAS A PAGAR", "CONTAS A RECEBER", "CONTROLADORIA", "COMERCIAL", "JURUDICO", "FISCAL", "EXPEDICAO", "CPD CENTRAL", "CONTABILIDADE", "MARKETING", "FOLHA DE PAGAMENTO", "SUPERVISAO LOJAS", "SEGURANCA DO TRABALHO", "CD NACIONAL", "CD ATALAIA", "CD IMPERIAL", "LOGISTICA", "SETOR MEDICO RH", "ARTES", "SECRETARIAS", "TRANSPORTES", "INDICADORES", "MANUTENCAO" }));
        cbx_setor.setEnabled(false);

        lbl_id.setForeground(new java.awt.Color(204, 0, 18));

        tbl_consulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_consultaMouseClicked(evt);
            }
        });
        tbl_consulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_consultaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_consulta);

        txt_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_consultaMouseClicked(evt);
            }
        });
        txt_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_consultaActionPerformed(evt);
            }
        });
        txt_consulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_consultaKeyReleased(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ListaTelefones/icones/pesquisa_48x48.png"))); // NOI18N
        jLabel10.setToolTipText("Pesquisar");

        filler1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.SystemColor.activeCaptionBorder, null, null));
        filler1.setForeground(new java.awt.Color(0, 2, 246));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btnGroupCadastro.add(rbtn_loja);
        rbtn_loja.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtn_loja.setText("Loja");
        rbtn_loja.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rbtn_loja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbtn_loja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_lojaActionPerformed(evt);
            }
        });

        btnGroupCadastro.add(rbtn_central);
        rbtn_central.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtn_central.setText("Central");
        rbtn_central.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbtn_central.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_centralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtn_loja)
                .addGap(37, 37, 37)
                .addComponent(rbtn_central)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtn_loja)
                    .addComponent(rbtn_central))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbx_setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(46, 46, 46))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_loja))
                                .addGap(29, 29, 29)))))
                .addGap(28, 28, 28)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(42, 42, 42)
                                .addComponent(txt_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_loja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbx_setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txt_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
            .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        //salvar

        if (tipo.equals("loja")) {

            Telefones tel = new Telefones();
            salvar(tel);
            prencherTabela();

        } else {

            TelefoneCentral central = new TelefoneCentral();
            salvar_central(central);
            preenchertabelaCentral();
            txt_nome.setEnabled(true);
        }

    }//GEN-LAST:event_btn_salvarActionPerformed

    private void txt_lojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lojaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lojaActionPerformed

    private void txt_consultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_consultaActionPerformed
        // prencher tabela:
        if (tipo.equals("loja")) {
            limpar();
            prencherTabela();
        } else {
            preenchertabelaCentral();
        }

    }//GEN-LAST:event_txt_consultaActionPerformed

    private void txt_consultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_consultaKeyReleased
        // prencher tabela:
        if (tipo.equals("loja")) {
            limpar();
            prencherTabela();
        } else {
            preenchertabelaCentral();
        }

    }//GEN-LAST:event_txt_consultaKeyReleased

    private void tbl_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_consultaMouseClicked
        // setar Campos
        if (tipo.equals("loja")) {
            limpar();
            setar_campos();
            btn_salvar.setEnabled(false);
        } else {

            txt_loja.setEnabled(false);
            txt_celular.setEnabled(false);
            setarCentral();
            btn_salvar.setEnabled(false);
            txt_nome.setEnabled(true);
            txt_telefone.setEnabled(true);
            cbx_setor.setEnabled(true);
        }

    }//GEN-LAST:event_tbl_consultaMouseClicked

    private void tbl_consultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_consultaKeyReleased
        // setar Campos
        if (tipo.equals("loja")) {
            limpar();
            setar_campos();
            btn_salvar.setEnabled(false);
        } else {
            txt_loja.setEnabled(false);
            txt_celular.setEnabled(false);
            setarCentral();
            btn_salvar.setEnabled(false);
        }

    }//GEN-LAST:event_tbl_consultaKeyReleased

    private void txt_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_consultaMouseClicked
        // prencher tabela:
        if (tipo.equals("loja")) {
            limpar();
            prencherTabela();
        } else {
            preenchertabelaCentral();
        }

    }//GEN-LAST:event_txt_consultaMouseClicked

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // apagar contato selecionado
        if (tipo.equals("loja")) {
            Telefones tel = new Telefones();
            apagar(tel);
            limpar();
            prencherTabela();
            btn_salvar.setEnabled(true);
        } else {
            Telefones tel = new Telefones();

            apagarCentral(tel);
            limparCentral();
            preenchertabelaCentral();
            btn_salvar.setEnabled(true);
            txt_nome.setEnabled(true);
            txt_telefone.setEnabled(true);
        }

    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // Atualizar telefones
        if (tipo.equals("loja")) {
            Telefones tel = new Telefones();
            atualizar(tel);
            limpar();
            prencherTabela();
            btn_salvar.setEnabled(true);
        } else {

            Telefones tell = new Telefones();
            atualizarCentral(tell);
            limparCentral();
            preenchertabelaCentral();
            btn_salvar.setEnabled(true);
            txt_nome.setEnabled(true);
            txt_telefone.setEnabled(true);
        }

    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // setando radiobuton
        //rbtn_loja.setSelected(true);
        //tipo = "loja";
        //txt_loja.setEnabled(true);
        //txt_celular.setEnabled(true);
        //System.out.println(""+tipo);

    }//GEN-LAST:event_formWindowActivated

    private void rbtn_centralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_centralActionPerformed
        // Configuranto tipo Central:
        rbtn_central.setSelected(true);
        tipo = "central";
        txt_loja.setEnabled(false);
        txt_celular.setEnabled(false);
        txt_nome.setEnabled(true);
        cbx_setor.setEnabled(true);
        btn_salvar.setEnabled(true);
        preenchertabelaCentral();
        limparCentral();
        limpar();
        //System.out.println(""+tipo);
    }//GEN-LAST:event_rbtn_centralActionPerformed

    private void rbtn_lojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_lojaActionPerformed
        // Configuranto tipo loja:
        rbtn_loja.setSelected(true);
        tipo = "loja";
        txt_loja.setEnabled(true);
        txt_celular.setEnabled(true);
        txt_nome.setEnabled(false);
        cbx_setor.setEnabled(false);
        btn_salvar.setEnabled(true);
        prencherTabela();
        limparCentral();
        limpar();

        //System.out.println(""+tipo);
    }//GEN-LAST:event_rbtn_lojaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // setando radiobuton
        rbtn_loja.setSelected(true);
        tipo = "loja";
        txt_loja.setEnabled(true);
        txt_celular.setEnabled(true);
        //System.out.println(""+tipo);

    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(CadTelefones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadTelefones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadTelefones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadTelefones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadTelefones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupCadastro;
    private javax.swing.JToggleButton btn_salvar;
    private javax.swing.JComboBox<String> cbx_setor;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JRadioButton rbtn_central;
    private javax.swing.JRadioButton rbtn_loja;
    private javax.swing.JTable tbl_consulta;
    private javax.swing.JFormattedTextField txt_celular;
    private javax.swing.JTextField txt_consulta;
    private javax.swing.JTextField txt_loja;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JFormattedTextField txt_telefone;
    // End of variables declaration//GEN-END:variables
}
