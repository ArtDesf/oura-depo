package imgtobmpkeilconvertor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

/**
 * Cette fonction (au nom très mal choisi :)) permet de créer une fenetre 
 * fournissant à l'utilisateur de quoi obtenir le code décimal et héxadécimal
 * d'une couleur donnée (255,255,255).
 * @author mathieu guyot (ouranos588)
 */
public class V255ToCode extends JFrame{
    private JSpinner spnRed;
    private JSpinner spnGreen;
    private JSpinner spnBlue;
    private JTextField colorCodeHex;
    private JTextField colorCodeDec;
    private JButton butConv;

    /**
     * Constructeur de la classe V255ToCode
     */
    public V255ToCode(){
        SpinnerNumberModel modelRed = new SpinnerNumberModel(0, 0, 255, 1);
        SpinnerNumberModel modelGreen = new SpinnerNumberModel(0, 0, 255, 1);
        SpinnerNumberModel modelBleu = new SpinnerNumberModel(0, 0, 255, 1);
        spnRed = new JSpinner(modelRed);
        spnGreen = new JSpinner(modelGreen);
        spnBlue = new JSpinner(modelBleu);
        colorCodeHex = new JTextField(10);
        colorCodeDec = new JTextField(10);
        butConv = new JButton("convertir");
        butConv.addActionListener(new ConversionListener());
        
        this.ajouterEtPlacerComp();
        
        super.setTitle("Convertir couleur");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setMinimumSize(new Dimension(230,260));
        super.setResizable(false);
        super.setVisible(true);
    }
    
    /**
     * Cette fonction permet de placer les différents composants dans la fenetre
     */
    private void ajouterEtPlacerComp() {
        JLabel lbRed = new JLabel("rouge : ");
        JLabel lbGreen = new JLabel("vert : ");
        JLabel lbBlue = new JLabel("bleu : ");
        JLabel lbCodeDec = new JLabel("Décimal : ");
        JLabel lbCodeHex = new JLabel("Hexadécimal : ");
        
        //Ajout des composants
        super.add(this.butConv);
        super.add(this.spnRed);
        super.add(this.spnGreen);
        super.add(this.spnBlue);    
        super.add(this.colorCodeDec);
        super.add(this.colorCodeHex);
        super.add(lbRed);
        super.add(lbGreen);
        super.add(lbBlue);
        super.add(lbCodeDec);
        super.add(lbCodeHex);
        
        //layout
        SpringLayout layout  = new SpringLayout();
        super.setLayout(layout);
        
        //Placement des composants
        layout.putConstraint(SpringLayout.WEST, lbRed, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lbGreen, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lbBlue, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lbCodeDec, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lbCodeHex, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, this.butConv, 70, 
                             SpringLayout.WEST, this);
        
        
        layout.putConstraint(SpringLayout.NORTH, lbRed, 10, 
                             SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, lbGreen, 20, 
                             SpringLayout.SOUTH, lbRed);
        layout.putConstraint(SpringLayout.NORTH, lbBlue, 20, 
                             SpringLayout.SOUTH, lbGreen);
        layout.putConstraint(SpringLayout.NORTH, lbCodeDec, 20, 
                             SpringLayout.SOUTH, this.butConv);
        layout.putConstraint(SpringLayout.NORTH, lbCodeHex, 20, 
                             SpringLayout.SOUTH, lbCodeDec);
        layout.putConstraint(SpringLayout.NORTH, this.butConv, 20, 
                             SpringLayout.SOUTH, lbBlue);
        
        layout.putConstraint(SpringLayout.WEST, this.spnRed, 110, 
                             SpringLayout.EAST, lbRed);
        layout.putConstraint(SpringLayout.WEST, this.spnGreen, 121, 
                             SpringLayout.EAST, lbGreen);
        layout.putConstraint(SpringLayout.WEST, this.spnBlue, 120, 
                             SpringLayout.EAST, lbBlue);
        layout.putConstraint(SpringLayout.WEST, this.colorCodeDec, 37, 
                             SpringLayout.EAST, lbCodeDec);
        layout.putConstraint(SpringLayout.WEST, this.colorCodeHex, 10, 
                             SpringLayout.EAST, lbCodeHex);
        
        layout.putConstraint(SpringLayout.NORTH, this.spnRed, 10, 
                             SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, this.spnGreen, 20, 
                             SpringLayout.SOUTH, this.spnRed);
        layout.putConstraint(SpringLayout.NORTH, this.spnBlue, 20, 
                             SpringLayout.SOUTH, this.spnGreen);
        layout.putConstraint(SpringLayout.NORTH, this.colorCodeDec, 20, 
                             SpringLayout.SOUTH, this.butConv);
        layout.putConstraint(SpringLayout.NORTH, this.colorCodeHex, 20, 
                             SpringLayout.SOUTH, this.colorCodeDec);
        
        
        
    }
    
    public class ConversionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int code = this.trouverCode((int)V255ToCode.this.spnRed.getValue(), 
                                        (int)V255ToCode.this.spnGreen.getValue(),
                                        (int)V255ToCode.this.spnBlue.getValue());
            V255ToCode.this.colorCodeDec.setText(String.valueOf(code));
            V255ToCode.this.colorCodeHex.setText("0x" + Integer.toHexString(code));
            
        }
        
        /**
        * Permet de trouver le code décimal de la couleur
        * @param red
        * @param green
        * @param blue
        * @return le code décimal de la couleur
        */
       private int trouverCode(int red, int green, int blue) {
           red >>= 3;
           green >>=2;
           blue >>=3;
           return (red << 11) | (green << 5) | blue;
       }
    }
}
