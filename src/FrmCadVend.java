import javax.swing.*;
import java.awt.event.*;

public class FrmCadVend extends JFrame{
    private JButton btnCad;
    private JLabel lblNome, lblSexo, lblNum, lblSerie, lblData, lblSalario;
    private JTextField txtNome, txtSexo, txtNum, txtSerie, txtData, txtSalario;
    private FrmPrincipal fp;
    
    private void initComponents(){
        setTitle("Cadastro de vendedores");
        setSize(500, 365);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        lblNome = new JLabel("Nome:");
        lblSexo = new JLabel("Sexo (M/F):");
        lblNum = new JLabel("Número da CNT:");
        lblSerie = new JLabel("Nº de série da CNT:");
        lblData = new JLabel("Admissão(números):");
        lblSalario = new JLabel("Salário:");
        
        txtNome = new JTextField();
        txtSexo = new JTextField();
        txtNum = new JTextField();
        txtSerie = new JTextField();
        txtData = new JTextField();
        txtSalario = new JTextField();
        
        btnCad = new JButton("Cadastrar");
        
        lblNome.setBounds(10, 10, 60, 20);
        lblSexo.setBounds(10, 40, 80, 20);
        lblNum.setBounds(10, 70, 150, 20);
        lblSerie.setBounds(10, 100, 150, 20);
        lblData.setBounds(10, 130, 160, 20);
        lblSalario.setBounds(10, 160, 60, 20);
        
        txtNome.setBounds(170,10,200,20);
        txtSexo.setBounds(170,40,200,20);
        txtNum.setBounds(170,70,200,20);
        txtSerie.setBounds(170,100,200,20);
        txtData.setBounds(170,130,200,20);
        txtSalario.setBounds(170,160,200,20);
        
        btnCad.setBounds(10, 190, 120, 25);
        
        add(lblNome);
        add(lblSexo);
        add(lblNum);
        add(lblSerie);
        add(lblData);
        add(lblSalario);
        
        add(txtNome);
        add(txtSexo);
        add(txtNum);
        add(txtSerie);
        add(txtData);
        add(txtSalario);
        
        add(btnCad);
        
        btnCad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    
                    Vendedor v = null; 
                    v = new Vendedor(0, 
                            txtSexo.getText(),
                            txtNome.getText(),
                            txtNum.getText(), 
                            txtSerie.getText(),
                            txtData.getText(), 
                            Double.parseDouble(txtSalario.getText()),
                            //v.calcularComissao(Double.parseDouble(txtSalario.getText()))
                            Double.parseDouble(txtSalario.getText())
                            );
                    
                   /*if(txtSexo.getText().equalsIgnoreCase("m") && txtSexo.getText().equalsIgnoreCase("f") ){
                        if(txtData.getText().length() > 0){
                            if(txtNome.getText()!= null){
                                if(Integer.parseInt(txtSalario.getText()) > 0){*/
                                    if(VendedorBD.cadastrar(v)){
                                        JOptionPane.showMessageDialog(FrmCadVend.this, "Vendedor "+v.getNome()+" cadastrado com sucesso!");
                                    }else{
                                        JOptionPane.showMessageDialog(FrmCadVend.this, "Erro ao cadastrar o vendedor!","ERRO",JOptionPane.ERROR_MESSAGE);
                                    }
                                    /*
                                }else{
                                    JOptionPane.showMessageDialog(FrmCadVend.this, "O campo \"salário\" não pode ficar em branco", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                                }
                                
                            }else{
                                JOptionPane.showMessageDialog(FrmCadVend.this, "O campo \"nome\" não pode ficar em branco", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(FrmCadVend.this, "A data de admissão deve conter apenas números", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                        }
                       
                    }else{
                        JOptionPane.showMessageDialog(FrmCadVend.this, "O campo \"sexo\" deve ser preenchido apenas com M ou F!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    }*/
                    
                    /*
                    if(txtNome.getText().length() > 0 && txtSalario.getText().length() > 0){
                        if(VendedorBD.cadastrar(v)){
                            JOptionPane.showMessageDialog(FrmCadVend.this, "Vendedor "+v.getNome()+" cadastrado com sucesso!");
                        }else{
                            JOptionPane.showMessageDialog(FrmCadVend.this, "Erro ao cadastrar o vendedor!","ERRO",JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(FrmCadVend.this, "O salário deve ser apenas numérico!","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
                    }*/
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(FrmCadVend.this, "O salário deve ser apenas numérico!","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
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
    
    public FrmCadVend(FrmPrincipal fp){
        this.fp = fp;
        initComponents();
    }
}
