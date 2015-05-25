import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class FrmExibir extends JFrame{
    private JButton btnFiltrar, btnExcluir;
    private JLabel lblFiltro, lblLinhas;
    private JTextField txtFiltro;
    private JTable tbl;
    private DefaultTableModel modelo;
    private JScrollPane scroll;
    private JComboBox cbxFiltro;
    private JRadioButton rdbNome, rdbIdade;
    private ButtonGroup btgFiltro;
    private JPanel pnl;
    private FrmPrincipal fp;
    
    private void zerarTabela(DefaultTableModel modelo){
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
    }
    
    private void atualizarTabela(DefaultTableModel modelo, int tipo, int campo, String valor){
        switch(tipo){
            case 0:
                if(!PessoaBD.isVazia(campo, valor)){
                    zerarTabela(modelo);
                    
                    for(String c:PessoaBD.getCampos()){
                        modelo.addColumn(c);
                    }
                    
                    for(Pessoa p:PessoaBD.buscar(campo, valor)){
                        modelo.addRow(p.getPessoa().toArray());
                    }
                }else{
                    JOptionPane.showMessageDialog(FrmExibir.this, "Não há pessoas cadastradas que atenda aos critérios da consulta!");
                }
            break;
            
            case 1:
                if(!ProfessorBD.isVazia(campo, valor)){
                    zerarTabela(modelo);
                    
                    for(String c:ProfessorBD.getCampos()){
                        modelo.addColumn(c);
                    }
                    
                    for(Professor p:ProfessorBD.buscar(campo, valor)){
                        modelo.addRow(p.getProfessor().toArray());
                    }
                }else{
                    JOptionPane.showMessageDialog(FrmExibir.this, "Não há pessoas cadastradas que atenda aos critérios da consulta!");
                }
            break;
                
            case 2:
                if(!AlunoBD.isVazia(campo, valor)){
                    zerarTabela(modelo);
                    
                    for(String c:AlunoBD.getCampos()){
                        modelo.addColumn(c);
                    }
                    
                    for(Aluno p:AlunoBD.buscar(campo, valor)){
                        modelo.addRow(p.getAluno().toArray());
                    }
                }else{
                    JOptionPane.showMessageDialog(FrmExibir.this, "Não há pessoas cadastradas que atenda aos critérios da consulta!");
                }
            break;
        }
    }
    
    private void initComponents(){
        setTitle("Exibir Pessoas");
        setSize(500,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        lblFiltro = new JLabel("Filtro:");
        lblLinhas = new JLabel();
        txtFiltro = new JTextField();
        cbxFiltro = new JComboBox(new String[]{"Pessoa","Professor","Aluno"});
        btnFiltrar = new JButton("Procurar");
        btnExcluir = new JButton("Excluir");
        rdbNome = new JRadioButton("Por nome");
        rdbIdade = new JRadioButton("Por idade");
        btgFiltro = new ButtonGroup();
        btgFiltro.add(rdbNome);
        btgFiltro.add(rdbIdade);
        pnl = new JPanel(null);
        
        lblFiltro.setFont(new Font("Arial", Font.PLAIN,23));
        
        lblFiltro.setBounds(10, 10, 70, 25);
        txtFiltro.setBounds(80, 10, 200, 25);
        btnFiltrar.setBounds(290, 10, 200, 25);
        cbxFiltro.setBounds(10, 45, 150, 25);
        rdbNome.setBounds(0, 0, 120, 25);
        rdbIdade.setBounds(130, 0, 120, 25);
        pnl.setBounds(170, 45, 260, 25);
        btnExcluir.setBounds(10, 485, 125, 25);
        lblLinhas.setBounds(165, 485, 200, 25);
        
        rdbNome.setSelected(true);
        
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    PessoaBD.deletarPessoa(Integer.parseInt(tbl.getValueAt
                        (tbl.getSelectedRow(), 0).toString()));
                    atualizarTabela(modelo, cbxFiltro.getSelectedIndex(), (rdbNome.isSelected()?1:2), 
                                (txtFiltro.getText().trim().length() > 0 ? txtFiltro.getText():""));
                    lblLinhas.setText("Linhas: "+tbl.getRowCount());
                }catch(ArrayIndexOutOfBoundsException excep){
                    JOptionPane.showMessageDialog(FrmExibir.this, "Selecione o aluno que deseja excluir.");
                }
            }
        });
        
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                atualizarTabela(modelo, cbxFiltro.getSelectedIndex(), (rdbNome.isSelected()?1:2), 
                                (txtFiltro.getText()));
                lblLinhas.setText("Linhas: "+tbl.getRowCount());
            }
        });
        
        cbxFiltro.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                atualizarTabela(modelo, cbxFiltro.getSelectedIndex(),
                        (txtFiltro.getText().trim().length() == 0 ? 0 : (rdbNome.isSelected()? 1 : 2)),
                        (txtFiltro.getText().trim().length() > 0 ? txtFiltro.getText() : ""));
                
                lblLinhas.setText("Linhas: "+tbl.getRowCount());
            }
        });
        
        modelo = new DefaultTableModel();
        
        atualizarTabela(modelo, 0, 0, "");
        
        tbl = new JTable(modelo);
        
        scroll = new JScrollPane(tbl);
        
        lblLinhas.setText("Linhas: "+tbl.getRowCount());
        
        scroll.setBounds(10, 85, 480, 365);
        
        pnl.add(rdbNome);
        pnl.add(rdbIdade);
        
        add(lblFiltro);
        add(txtFiltro);
        add(btnFiltrar);
        add(scroll);
        add(cbxFiltro);
        add(btnExcluir);
        add(pnl);
        add(lblLinhas);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                fp.setVisible(true);
            }
        });
        
        setVisible(true);
    }
    
    public FrmExibir(FrmPrincipal fp){
        this.fp = fp;
        initComponents();
    }
}
