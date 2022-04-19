package br.com.ListaTelefones.dao;

import br.com.ListaTelefones.conexaobd.ModuloConexao;
import br.com.ListaTelefones.model.TelefoneCentral;
import br.com.ListaTelefones.model.Telefones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Welder
 */
public class TelefonesDao {

    public void salvar(Telefones tel) {
        StringBuilder sql = new StringBuilder();

        sql.append("insert into tb_contatoloja");
        sql.append("(loja,Fone,cell)");
        sql.append("values(?,?,?)");

        Connection co = ModuloConexao.conector();

        try {
            PreparedStatement pst = co.prepareStatement(sql.toString());

            pst.setString(1, tel.getLoja());
            pst.setString(2, tel.getFone());
            pst.setString(3, tel.getCell());
                pst.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para salvar telefones internos Central
    public void salvarCentral(TelefoneCentral salvarCentral) {

        StringBuilder sql = new StringBuilder();

        sql.append("insert into tb_contatocentral");
        sql.append("(nome,fone,dep)");
        sql.append("values(?,?,?)");

        Connection co = ModuloConexao.conector();

        try {
            PreparedStatement pst = co.prepareCall(sql.toString());

            pst.setString(1, salvarCentral.getNome());
            pst.setString(2, salvarCentral.getFone());
            pst.setString(3, salvarCentral.getSetor());
            pst.executeUpdate();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void delete(Telefones tel) {

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja remover este Contato ?", "Tem certeza!", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM tb_contatoloja WHERE iduser =?;");

            Connection co = ModuloConexao.conector();

            try {
                PreparedStatement pst = co.prepareCall(sql.toString());

                pst.setString(1, tel.getIduser());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Contato removido com sucesso!");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            }
        } else {

        }
    }

    public void deleteCentral(Telefones deletaCentral) {

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja remover este Contato ?", "Tem certeza!", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM tb_contatocentral WHERE iduser =?;");

            Connection co = ModuloConexao.conector();

            try {
                PreparedStatement pst = co.prepareCall(sql.toString());

                pst.setString(1, deletaCentral.getIduser());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Contato removido com sucesso!");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            }
        } else {

        }
    }

    public void update(Telefones up) {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja alterar este Contato ?", "Tem certeza!", JOptionPane.YES_NO_OPTION);

        StringBuilder sql = new StringBuilder();

        sql.append("update tb_contatoloja set loja=?,Fone=?,cell=? where iduser=?");

        Connection co = ModuloConexao.conector();

        if (confirma == JOptionPane.YES_OPTION) {

            try {
                PreparedStatement pst = co.prepareStatement(sql.toString());

                pst.setString(1, up.getLoja());
                pst.setString(2, up.getFone());
                pst.setString(3, up.getCell());
                pst.setString(4, up.getIduser());
                
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Contato atualizado!!");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            }
        } else {
        }
    }

    public void updateCentral(Telefones upc) {

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja alterar este Contato ?", "Tem certeza!", JOptionPane.YES_NO_OPTION);

        StringBuilder sql = new StringBuilder();

        sql.append("update tb_contatocentral set nome=?,fone=?,dep=? where iduser=?");

        Connection co = ModuloConexao.conector();

        if (confirma == JOptionPane.YES_OPTION) {

            try {
                PreparedStatement pst = co.prepareStatement(sql.toString());

                pst.setString(1, upc.getNome());
                pst.setString(2, upc.getFone());
                pst.setString(3, upc.getSetor());
                pst.setString(4, upc.getIduser());
                
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Contato atualizado!!");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            }
        } else {
        }

    }

}
