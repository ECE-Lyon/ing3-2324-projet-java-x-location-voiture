package codes;

import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;
import codes.model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serial;

public class MainJFrame extends JFrame implements WindowListener, ComponentListener {

    @Serial
    private static final long serialVersionUID = 1L;

    private JFrame frame = new JFrame("Window");

    private InscrPage inscrPage;
    private InscrConnecPage inscrConnecPage;
    private ConnecPage connecPage;
    private ShopPage shopPage;




    Client client;
    UtilisateurDaoImpl utilisateurDao;



    Utilisateur utilisateur;



    private PrivateSpacePage privateSpacePage;



    private JPanel inscrConnecMainPanel = new JPanel();

    private JLabel legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");
    private JPanel legendaryMotorsportPanel = new JPanel();
    private JPanel connexionPanel = new JPanel();
    private JPanel inscriptionPanel = new JPanel();

    private int windowSizeWidth = GlobalVariable.getScreenWidth();
    private int windowSizeHeight = GlobalVariable.getScreenHeight();
    private Dimension dimensionLegendaryMotorsportPanel = new Dimension(windowSizeWidth/3, windowSizeHeight/10);
    private Dimension dimensionConnexionPanel = new Dimension(windowSizeWidth/8, windowSizeHeight/10);
    private Dimension dimensionInscriptionPanel = new Dimension(windowSizeWidth/8, windowSizeHeight/10);




    private String name;
    private String firstName;
    private String email;
    private String password;


    private boolean connectedState = false;


    public MainJFrame(){
        super("Client");
        frame.addWindowListener(this);
        frame.addComponentListener(this);
        frame.setSize(GlobalVariable.getScreenWidth(), GlobalVariable.getScreenHeight());



        this.shopPage = new ShopPage(this);
        this.inscrConnecPage = new InscrConnecPage(this, this.shopPage);
        this.inscrPage = new InscrPage(this, this.shopPage,this.inscrConnecPage);
        this.connecPage = new ConnecPage(this, this.shopPage,this.inscrConnecPage,this.inscrPage);
        this.privateSpacePage = new PrivateSpacePage(this, this.connecPage, this.inscrPage, this.inscrConnecPage, this.shopPage);


        //this.connectedState = false;
        this.shopPage.resetMainContent();



        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////// LIGNES FINALES  ///////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }



    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
        System.out.println("The window has been destroy successfully.");
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////AJOUTER LA MOFIFICATION DE LA TAILLE DE LA POLICE SI ON A LE TEMPS ET LA DETER//////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //this.legendaryMotorsportLabel = new JLabel("LEGENDARY MOTORSPORT");
        this.legendaryMotorsportPanel.add(this.legendaryMotorsportLabel);



        this.windowSizeHeight = this.frame.getSize().height;
        this.windowSizeWidth = this.frame.getSize().width;
        this.dimensionLegendaryMotorsportPanel = new Dimension(this.windowSizeWidth / 3, this.windowSizeHeight / 10);
        this.dimensionConnexionPanel = new Dimension(this.windowSizeWidth / 8, this.windowSizeHeight / 10);
        this.dimensionInscriptionPanel = new Dimension(this.windowSizeWidth / 8, this.windowSizeHeight / 10);

        this.legendaryMotorsportPanel.setPreferredSize(this.dimensionLegendaryMotorsportPanel);
        this.connexionPanel.setPreferredSize(this.dimensionConnexionPanel);
        this.inscriptionPanel.setPreferredSize(this.dimensionInscriptionPanel);
        this.inscrConnecMainPanel.revalidate();
        this.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    public JFrame getFrame() {
        return frame;
    }

    public PrivateSpacePage getPrivateSpacePage() {
        return privateSpacePage;
    }

    public InscrPage getInscrPage() {
        return inscrPage;
    }

    public InscrConnecPage getInscrConnecPage() {
        return inscrConnecPage;
    }

    public ConnecPage getConnecPage() {
        return connecPage;
    }

    public boolean isConnected() {
        return connectedState;
    }

    public void setConnected(boolean connected) {
        connectedState = connected;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public UtilisateurDaoImpl getUtilisateurDao() {
        return utilisateurDao;
    }

    public void setUtilisateurDao(UtilisateurDaoImpl utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }
}
