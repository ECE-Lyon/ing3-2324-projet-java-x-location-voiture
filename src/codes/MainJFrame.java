package codes;

import codes.dao.UtilisateurDaoImpl;
import codes.model.Client;
import codes.model.Employe;
import codes.model.Entreprise;
import codes.model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serial;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainJFrame extends JFrame implements WindowListener, ComponentListener {

    @Serial
    private static final long serialVersionUID = 1L;

    private JFrame frame = new JFrame("Window");

    private InscrPage inscrPage;
    private InscrConnecPage inscrConnecPage;
    private ConnecPage connecPage;
    private ShopPage shopPage;
    private PrivateSpacePage privateSpacePage;
    private BasketPage basketPage;
    private DisplayCars displayCars;
    private ModifyModelPage modifyModelPage;
    private EmployeeMainPage employeeMainPage;
    private PopularityPage popularityPage;
    private ClientInfosPage clientInfosPage;



    private UneVoiture uneVoiture;

    public PaymentPage getPaymentPage() {
        return paymentPage;
    }

    public void setPaymentPage(PaymentPage paymentPage) {
        this.paymentPage = paymentPage;
    }

    private PaymentPage paymentPage;







    Client client;
    UtilisateurDaoImpl utilisateurDao;
    Entreprise entreprise;
    Employe employe;
    Utilisateur utilisateur;






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
    private long Siret;
    private String companyName;


    private int idUtilisateur = 0;




    private String filter = "No filter";



    private ArrayList<Integer> idVoitureAchetees = new ArrayList<>();


    private boolean connectedState = false;


    public MainJFrame() throws SQLException {
        super("Client");

        frame.addWindowListener(this);
        frame.addComponentListener(this);
        frame.setSize(GlobalVariable.getScreenWidth(), GlobalVariable.getScreenHeight());


        System.out.println("Updating images");

        this.displayCars = new DisplayCars(this);
        this.shopPage = new ShopPage(this);
        this.inscrConnecPage = new InscrConnecPage(this, this.shopPage);
        this.inscrPage = new InscrPage(this, this.shopPage,this.inscrConnecPage);
        this.connecPage = new ConnecPage(this, this.shopPage,this.inscrConnecPage,this.inscrPage);
        this.privateSpacePage = new PrivateSpacePage(this, this.connecPage, this.inscrPage, this.inscrConnecPage, this.shopPage);
        this.basketPage = new BasketPage(this, this.connecPage, this.inscrPage, this.inscrConnecPage, this.shopPage, this.privateSpacePage);
        this.modifyModelPage = new ModifyModelPage(this);
        this.employeeMainPage = new EmployeeMainPage(this);
        this.popularityPage = new PopularityPage(this);
        this.clientInfosPage = new ClientInfosPage(this);
        this.paymentPage = new PaymentPage(this);
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

    public BasketPage getBasketPage() {
        return basketPage;
    }

    public void setBasketPage(BasketPage basketPage) {
        this.basketPage = basketPage;
    }

    public DisplayCars getDisplayCars() {
        return displayCars;
    }

    public void setDisplayCars(DisplayCars displayCars) {
        this.displayCars = displayCars;
    }

    public ModifyModelPage getModifyModelPage() {
        return modifyModelPage;
    }

    public void setModifyModelPage(ModifyModelPage modifyModelPage) {
        this.modifyModelPage = modifyModelPage;
    }

    public PopularityPage getPopularityPage() {
        return popularityPage;
    }

    public void setPopularityPage(PopularityPage popularityPage) {
        this.popularityPage = popularityPage;
    }

    public ShopPage getShopPage() {
        return shopPage;
    }

    public void setShopPage(ShopPage shopPage) {
        this.shopPage = shopPage;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }



    // UTILISATEUR

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

    public UtilisateurDaoImpl getUtilisateurDao() {
        return utilisateurDao;
    }

    public void setUtilisateurDao(UtilisateurDaoImpl utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }



    // CLIENT

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientInfosPage getClientInfosPage() {
        return clientInfosPage;
    }

    public void setClientInfosPage(ClientInfosPage clientInfosPage) {
        this.clientInfosPage = clientInfosPage;
    }

    public void initializeClient(String name, String firstName, String email, String password) {
        this.client = new Client(-1, password, email, name, firstName, null);
    }

    public void updateClientInformation(String name, String firstName, String email, String password) {
        if (this.client != null) {
            this.client.setNom_client(name);
            this.client.setPrenom_client(firstName);
            this.client.setEmail(email);
            this.client.setMdp(password);
        }
    }



    // EMPLOYE

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public EmployeeMainPage getEmployeeMainPage() {
        return employeeMainPage;
    }

    public void setEmployeeMainPage(EmployeeMainPage employeeMainPage) {
        this.employeeMainPage = employeeMainPage;
    }



    // ENTREPRISE

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getSiret() {
        return Siret;
    }

    public void setSiret(long siret) {
        Siret = siret;
    }

    public void initializeEntreprise(String name, long siret, String email, String password) {
        this.entreprise = new Entreprise(-1, password, email, name, siret);
    }

    public void updateEntrepriseInformation(String name, long siret, String email, String password) {
        if (this.entreprise != null) {

            this.entreprise.setNom_entreprise(name);
            this.entreprise.setSiret(siret);
            this.entreprise.setEmail(email);
            this.entreprise.setMdp(password);

        }
    }



    // VOITURE

    public UneVoiture getUneVoiture() {
        return uneVoiture;
    }

    public void setUneVoiture(UneVoiture uneVoiture) {
        this.uneVoiture = uneVoiture;
    }

    public ArrayList<Integer> getIdVoitureAchetees() {
        return idVoitureAchetees;
    }

    public void setIdVoitureAchetees(ArrayList<Integer> idVoitureAchetees) {
        this.idVoitureAchetees = idVoitureAchetees;
    }

    public void addToIdVoitureAchetees(int i){
        this.idVoitureAchetees.add(i);
    }

}
