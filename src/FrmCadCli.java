import javax.swing.*;
import java.awt.event.*;

public class FrmCadCli extends JFrame{
    private JButton btnCad;
    private JLabel lblNome, lblSexo, lblRenda, lblData, lblLimite;
    private JTextField txtNome, txtSexo, txtRenda, txtData, txtLimite;
    private FrmPrincipal fp;
    
    private void initComponents(){
        setTitle("Cadastro de Clientes");
        setSize(300, 165);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        //private String dataCadastro;
    //private double renda, limite;
        
        
        lblNome = new JLabel("Nome:");
        lblSexo = new JLabel("Sexo:");
        lblRenda = new JLabel("Renda:");
        
        txtNome = new JTextField();
        txtRenda = new JTextField();
        txtSexo = new JTextField();
        
        btnCad = new JButton("Cadastrar");
        
        lblNome.setBounds(10, 10, 60, 20);
        lblSexo.setBounds(10, 40, 60, 20);
        lblRenda.setBounds(10, 70, 60, 20);
        
        txtNome.setBounds(80,10,200,20);
        txtSexo.setBounds(80,40,40,20);
        txtRenda.setBounds(80,70,100,20);
        
        btnCad.setBounds(10, 100, 120, 25);
        
        add(lblNome);
        add(lblSexo);
        add(lblRenda);
        add(txtNome);
        add(txtSexo);
        add(txtRenda);
        add(btnCad);
        //add(txtData);
        //add(lblData);
        //add(txtLimite);
        //add(lblLimite)
        
        btnCad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    Cliente c = new Cliente(0,Integer.parseInt(txtSexo.getText()),
                        txtNome.getText(), txtData.getText(), txtRM.getText()));
                    
                    if(txtNome.getText().length() > 0 && txtRM.getText().length() > 0){
                        if(AlunoBD.cadastrar(a)){
                            JOptionPane.showMessageDialog(FrmCadAluno.this, "Aluno "+a.getNome()+" cadastrado com sucesso!");
                        }else{
                            JOptionPane.showMessageDialog(FrmCadAluno.this, "Erro ao cadastrar o aluno!","ERRO",JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(FrmCadAluno.this, "A idade deve ser apenas numérica!","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
                    }
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(FrmCadAluno.this, "A idade deve ser apenas numérica!","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                fp.setVisible(true);
            }
        });
        
        setVisible(true);
    }
    
    public FrmCadAluno(FrmPrincipal fp){
        this.fp = fp;
        initComponents();
    }
}
