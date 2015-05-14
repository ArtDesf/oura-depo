package imgtobmpkeilconvertor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

/**
 * Cette classe permet de créer une fenetre qui permet à l'utilisateur de 
 * réaliser les actions : charger une image, voir l'image, lancer la conversion
 * et récupèrer le résultat de la conversion
 * @author mathieu guyot (ouranos588)
 * @version 1
 */
public class VConvFen extends JFrame  implements Observer{
    private MConvFen modele;
    private JButton butLoadImg;
    private JButton butLancerConv;
    private JButton butVoirImage;
    private JButton butOpenfenColorConv;
    private JButton butOpenfenInfos;
    private JTextArea taResConv;
    private JTextArea taCouleursRes;
    private JTextArea taBMPArrayCRes;
    private JTextArea taBMPArrayHRes;
    private File image;
    
    /**
     * Constructeur de la classe VConvFen
     * @param modele le modèle associé à la vue
     */
    public VConvFen(MConvFen modele) {
        this.modele = modele;
        modele.addObserver(this);
        butLoadImg = new JButton("Charger une image");
        butLoadImg.addActionListener(new LoadImgListener());
        butLancerConv = new JButton("Lancer conversion");
        butLancerConv.addActionListener(new LancerConvLIstener());
        butLancerConv.setEnabled(false);
        butVoirImage = new JButton("Voir l'image");
        butVoirImage.addActionListener(new ShowImgListener());
        butVoirImage.setEnabled(false);
        taResConv = new JTextArea(5,43);
        DefaultCaret caret = (DefaultCaret)taResConv.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        taResConv.setText("En attente du chargement d'une image...");
        taResConv.setFocusable(false);
        taCouleursRes = new JTextArea(5,43);
        taBMPArrayCRes = new JTextArea(5,43);
        taBMPArrayHRes  = new JTextArea(5,43);     
        butOpenfenColorConv = new JButton("Convertir une couleur");
        butOpenfenColorConv.addActionListener(new ConvCouleurLIstener());
        butOpenfenInfos = new JButton("à propos");
        butOpenfenInfos.addActionListener(new InfosLIstener());
        
        this.ajouterEtPlacerComp();
        
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setMinimumSize(new Dimension(500,625));
        super.setResizable(false);
        super.setTitle("Image vers keil bmp convertisseur");
        super.setVisible(true);
    }
    
    /**
     * Cette fonction permet de placer les différents composants dans la fenetre
     */
    private void ajouterEtPlacerComp() {
        JLabel lbInfoTAConsole = new JLabel("Avancée de la conversion");
        JLabel lbInfoTACouleurs = new JLabel("Couleurs de l'image (à placer dans GLDC.h)");
        JLabel lbInfoTAImageC = new JLabel("Résultat de la conversion (à placer dans un fichier.c)");
        JLabel lbInfoTAImageH = new JLabel("Résultat de la conversion (à placer dans un fichier.h)");
        
        JScrollPane scpanCons = new JScrollPane(this.taResConv);
        JScrollPane scpanCouleursRes = new JScrollPane(this.taCouleursRes);
        JScrollPane scpanResC = new JScrollPane(this.taBMPArrayCRes);
        JScrollPane scpanResH = new JScrollPane(this.taBMPArrayHRes);
        
        super.add(this.butLoadImg);
        super.add(this.butLancerConv);
        super.add(this.butVoirImage);
        super.add(scpanCons);
        super.add(scpanCouleursRes);
        super.add(scpanResC);
        super.add(scpanResH);
        super.add(lbInfoTAConsole);
        super.add(lbInfoTACouleurs);
        super.add(lbInfoTAImageC);
        super.add(lbInfoTAImageH);
        super.add(butOpenfenColorConv);
        super.add(butOpenfenInfos);
        
        //layout
        SpringLayout layout  = new SpringLayout();
        super.setLayout(layout);
        //placement des boutons
        layout.putConstraint(SpringLayout.WEST, this.butLoadImg, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, this.butLoadImg, 10, 
                             SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, this.butLancerConv, 10, 
                             SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, this.butVoirImage, 10, 
                             SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, this.butLancerConv, 10, 
                             SpringLayout.EAST, this.butLoadImg);
        layout.putConstraint(SpringLayout.WEST, this.butVoirImage, 10, 
                             SpringLayout.EAST, this.butLancerConv);
        
        //placement des lb
        layout.putConstraint(SpringLayout.WEST, lbInfoTAConsole, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lbInfoTACouleurs, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lbInfoTAImageC, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lbInfoTAImageH, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lbInfoTAConsole, 10, 
                             SpringLayout.SOUTH, this.butLoadImg);
        layout.putConstraint(SpringLayout.NORTH, lbInfoTACouleurs, 30, 
                             SpringLayout.SOUTH, scpanCons);
        layout.putConstraint(SpringLayout.NORTH, lbInfoTAImageC, 10, 
                             SpringLayout.SOUTH, scpanCouleursRes);
        layout.putConstraint(SpringLayout.NORTH, lbInfoTAImageH, 10, 
                             SpringLayout.SOUTH, scpanResC);
        
        //placement des ta
        layout.putConstraint(SpringLayout.WEST, scpanCons, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, scpanResC, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, scpanResH, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, scpanCouleursRes, 10, 
                             SpringLayout.WEST, this);        
        layout.putConstraint(SpringLayout.NORTH, scpanCons, 10, 
                             SpringLayout.SOUTH, lbInfoTAConsole);
        layout.putConstraint(SpringLayout.NORTH, scpanCouleursRes, 10, 
                             SpringLayout.SOUTH, lbInfoTACouleurs);
        layout.putConstraint(SpringLayout.NORTH, scpanResC, 10, 
                             SpringLayout.SOUTH, lbInfoTAImageC);
        layout.putConstraint(SpringLayout.NORTH, scpanResH, 10, 
                             SpringLayout.SOUTH, lbInfoTAImageH);
        layout.putConstraint(SpringLayout.NORTH, this.butOpenfenColorConv, 550, 
                             SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, this.butOpenfenColorConv, 10, 
                             SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, this.butOpenfenInfos, 550, 
                             SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, this.butOpenfenInfos, 400, 
                             SpringLayout.WEST, this);
    }
    
    /**
     * Fonction de mise à jour de la vue
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        String consText = this.taResConv.getText();
        consText += "\nRécupération des couleurs de l'image...";
        this.taResConv.setText(consText);
        consText += "\nRéussite";
        consText += "\nRécupération du code de l'image...";
        consText += "\nCe traitemenent peut être long.";
        this.taResConv.setText(consText);
        this.taCouleursRes.setText(modele.getCouleurs());
        this.taBMPArrayCRes.setText(modele.trouverCodeCImage());
        this.taBMPArrayHRes.setText(modele.trouverCodeHImage());
        consText += "\nRéussite, image convertie";
        this.taResConv.setText(consText);
    }
    
    /**
     * Classe listener pour le bouton de chargement d'image.
     * Créer un JFileChooser qui permet la récupération d'une image
     */
    public class LoadImgListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
            final JFileChooser fc = new JFileChooser();
            fc.addChoosableFileFilter(imageFilter);
            fc.setAcceptAllFileFilterUsed(false);
            int returnVal = fc.showOpenDialog(VConvFen.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                image = fc.getSelectedFile();
                if(image != null) {
                    modele.setImage(image);
                    if(modele.imageIsValid()) {
                        VConvFen.this.butLancerConv.setEnabled(true);
                        VConvFen.this.butVoirImage.setEnabled(true);
                        String consText = VConvFen.this.taResConv.getText();
                        consText += "\nL'image " + image.getPath() + " a correctement été chargée.";
                        consText += "\nAttente du lancement de la conversion...";
                        VConvFen.this.taResConv.setText(consText);
                    }
                    else {
                        String consText = VConvFen.this.taResConv.getText();
                        consText += "\nErreur lors du chargement de l'image, elle dépasse 320*240 pixels";
                        VConvFen.this.taResConv.setText(consText);
                    }
                }
            }
        }
    }
    
    /**
     * Classe listener pour le bouton de visionner une image.
     * Créer un VImageShow qui permet de visionner une image
     */
    public class ShowImgListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            VImageShow show = new VImageShow(image);
            String consText = VConvFen.this.taResConv.getText();
            consText += "\nVisualisation de l'image " + image.getPath();
            VConvFen.this.taResConv.setText(consText);
        }
    }
    
    /**
     *  Classe listener pour le bouton de lancement de conversion
     */
    public class LancerConvLIstener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            VConvFen.this.modele.lancerConversion();
        }
    }
    
    /**
     *  Classe listener pour le bouton de lancement de conversion d'une couleur
     */
    public class ConvCouleurLIstener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            V255ToCode fen = new V255ToCode();
        }
    }
    
    /**
     *  Classe listener pour le bouton de lancement de la fen d'infos
     */
    public class InfosLIstener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            VILogicielInfos inf = new VILogicielInfos();
        }
    }
}
