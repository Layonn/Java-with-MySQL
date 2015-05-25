import javax.swing.*;
import java.awt.event.*;

public class FrmPrincipal extends JFrame{
    private JButton btnCadProf, btnCadAlu, btnExibir;
    
    private void initComponents(){
        setTitle("PRINCIPAL");
        setSize(180,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        btnCadProf = new JButton("Cad. Vendedor");
        btnCadAlu = new JButton("Cad. Cliente");
        btnExibir = new JButton("Exibir Pessoas");
        
        btnCadProf.setBounds(10, 10, 150, 25);
        btnCadAlu.setBounds(10, 45, 150, 25);
        btnExibir.setBounds(10, 80, 150, 25);
        
        btnCadProf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new FrmCadVend(FrmPrincipal.this);
                setVisible(false);
            }
        });
        
        btnCadAlu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //new FrmCadCli(FrmPrincipal.this);
                setVisible(false);
            }
        });
        
        btnExibir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new FrmExibir(FrmPrincipal.this);
                setVisible(false);
            }
        });
        
        add(btnCadProf);
        add(btnCadAlu);
        add(btnExibir);
        
        setVisible(true);
    }
    
    public FrmPrincipal(){
        initComponents();
    }
}
