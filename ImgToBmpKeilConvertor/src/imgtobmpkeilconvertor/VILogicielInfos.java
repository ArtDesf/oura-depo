package imgtobmpkeilconvertor;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Cette classe permet d'afficher les infos du logiciel dans une fenetre
 * @author mathieu guyot (ouranos588)
 */
public class VILogicielInfos extends JFrame{

    public VILogicielInfos() {
        String infos = "";
        infos += "Ce logiciel permet de porter une image sur microcontrôleur\n";
        infos += "Il gère la création d'un code de portage d'une image en language c\n\n";
        infos += "Note : si l'image dépasse 200*200 pixels, la durée de convertion peut dépasser 1 minute !\n\n";
        infos += "Attention : \nAvec la license éducative UVision, la taille du code est limitée.\n";
        infos += "Ainsi, éviter de convertir des images comportant trop de couleurs (>50) et de trop grande taille(50*50)\n\n";
        infos += "logiciel développé par mathieu guyot\n";
        infos += "Etudiant à l'IUT informatique de la Rochelle\n"; 
        infos += "Code source : https://github.com/ouranos588/oura-depo\n";
        infos += "commentaires, bugs : mathieuguyot40@yahoo.fr\n";
        
        JTextArea taInfos = new JTextArea(100,100);
        taInfos.setText(infos);
        taInfos.setFocusable(false);
        super.setContentPane(taInfos);
        super.setMinimumSize(new Dimension(570,250));
        super.setResizable(false);
        super.setTitle("Informations sur ce logiciel");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }
   
}
