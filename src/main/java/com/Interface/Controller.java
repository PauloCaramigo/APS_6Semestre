package com.Interface;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.System.*;

import java.awt.image.BufferedImage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable{
    // Declare variables
    ObservableList<Database> data = null;
    RunQuery runQuery = new RunQuery();
    CompareImages getImages = new CompareImages();
    Stage primaryStage = new Stage();
    FileChooser fileChooser = new FileChooser();
    BufferedImage selected, registered;
    ImageIO image;
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Fotos", "*.jpg", "*.jpeg","*.png");
    String limitedUser = null, limitedEmail = null, limitedPassword = null, limitedPasswordCheck = null, getPath = null, getUser = null;
    String permission = runQuery.getInfo(getUser, "permission", "users");

    //Login page elements
    @FXML private Label lblImage; 
    @FXML private Label lblLoginFail;
    @FXML private Pane paneLogin;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtLogin;

    // Register page elements
    @FXML private Label lblFailedInfo; 
    @FXML private Label lblImageReg;    
    @FXML private Pane paneRegister; 
    @FXML private Pane panePrompt;
    @FXML private PasswordField txtPasswordCheck; 
    @FXML private PasswordField txtPasswordReg;
    @FXML private TextField txtEmail; 
    @FXML private TextField txtUser;  
    @FXML private ComboBox<String> cbbState;  

    // User home page elements
    @FXML private Button btnPermission;
    @FXML private Hyperlink lnkExit;
    @FXML private Label lblHome;
    @FXML private Pane paneHome;
    @FXML private ScrollPane paneSystem;
    
    // User account page elements
    @FXML private Button btnCancel;
    @FXML private Button btnEdit;
    @FXML private Button btnSave;
    @FXML private Label lblAccountInfo;
    @FXML private Pane paneAccount;
    @FXML private TextField txtUserBiometry;
    @FXML private TextField txtUserEmail;
    @FXML private TextField txtUserLogin;
    @FXML private TextField txtUserPassword;
    @FXML private TextField txtUserPermission;
    @FXML private TextField txtUserState;

    // Permission management page elements
    @FXML private Button btnSavePermission;
    @FXML private Label lblManagement;
    @FXML private Label lblSelectFail;
    @FXML private Label lblUserPermission;
    @FXML ListView<String> list = new ListView<String>();
    @FXML private Pane paneManagement;
    @FXML private Pane panePermission;
    @FXML private RadioButton rbtAdmin;
    @FXML private RadioButton rbtBasic;
    @FXML private RadioButton rbtDirector;

    // Confirmation prompt elements
    @FXML private Button btnCancelAction;
    @FXML private Label lblAction;
    @FXML private Label lblFailedConf;
    @FXML private Label lblImageConf;
    @FXML private Pane paneConfirm;

    // Database page elements
    @FXML private Pane paneDatabase;
    public TableView<Database> database;
    public TableColumn<Database, String> stateCol;
    public TableColumn<Database, String> agro1Col;
    public TableColumn<Database, String> agro2Col;
    public TableColumn<Database, String> agro3Col;
    public TableColumn<Database, String> agro4Col;
    public TableColumn<Database, String> othersCol;
    public TableColumn<Database, String> totalCol;

    public boolean verifyNull(String field){
        if (field == "" || field == null || field == " ") return true;
        else return false;
    }
    
    @FXML        
    private void browseImage(MouseEvent event) throws IOException {
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null){
            selected = ImageIO.read(file.getAbsoluteFile());
            getPath = file.getAbsoluteFile().toString().replace("\\", "/");
            
            if (file != null && paneLogin.isVisible()) lblImage.setText(file.getName());
            else if (file != null && paneRegister.isVisible()) lblImageReg.setText(file.getName());
            else if (file != null && paneConfirm.isVisible()) lblImageConf.setText(file.getName());
        }
    }
        
    @FXML
    private void back(MouseEvent event) throws IOException{
        if (paneConfirm.isVisible() && paneSystem.isVisible()) managePermissions(event);
        else if (paneSystem.isVisible()) {paneSystem.setVisible(false); paneAccount.setVisible(false); paneLogin.setVisible(true);}
        else {paneLogin.setVisible(true); panePrompt.setVisible(false); paneRegister.setVisible(false);}
        txtLogin.setText(null); txtPassword.setText(null);        
        lblImage.setText(null); lblLoginFail.setVisible(false);
        getPath = null;
    }

    @FXML
    private void registerUser(MouseEvent event) {
        paneLogin.setVisible(false); panePrompt.setVisible(false); paneRegister.setVisible(true); paneRegister.setDisable(false);
        txtEmail.setText(null); txtPasswordCheck.setText(null); txtPasswordReg.setText(null); txtUser.setText(null);
        lblImageReg.setText(null);
        getPath = null;
        ObservableList<String> items =FXCollections.observableArrayList ("Bahia", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", 
        "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraná", "Pernambuco", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "São Paulo", "Santa Catarina");
        cbbState.setItems(items);
    }

    @FXML
    private void limitText(KeyEvent event){
        int limitEmail = 100, limitUser = 16;
        int lenUser = txtUser.getLength(), lenEmail = txtEmail.getLength(), lenPassword = txtPasswordReg.getLength(), lenPasswordCheck = txtPasswordCheck.getLength();

        if (txtUser.isFocused() == true){
            if (lenUser <= limitUser) limitedUser = txtUser.getText();
            else txtUser.setText(limitedUser);
        }

        else if (txtEmail.isFocused() == true){
            if (lenEmail <= limitEmail) limitedEmail = txtEmail.getText();
            else txtEmail.setText(limitedEmail);
        }

        else if (txtPasswordReg.isFocused() == true){
            if (lenPassword <= limitUser) limitedPassword = txtPasswordReg.getText();
            else txtPasswordReg.setText(limitedPassword);
        }

        else if (txtPasswordCheck.isFocused() == true){
            if (lenPasswordCheck <= limitUser) limitedPasswordCheck = txtPasswordCheck.getText();
            else txtPasswordCheck.setText(limitedPasswordCheck);
        }
    }

    @FXML
    private void login(MouseEvent event){
        lblLoginFail.setVisible(false);
        
        try{
            Boolean credentials = false, biometry = false;
            String userName = txtLogin.getText();
            String password = txtPassword.getText();
            String userBiometry = null;
            if (!verifyNull(userName) && !verifyNull(password) && !verifyNull(getPath)) {
                userBiometry = runQuery.getBiometry(userName);
                registered = ImageIO.read(new File (userBiometry));
                credentials = runQuery.ConfirmLogin(userName, password);
                if (registered != null && selected != null) biometry = getImages.compareImage(selected, registered);
                System.out.println(biometry);
                if (credentials && biometry) {
                    paneAccount.setVisible(false); paneHome.setVisible(true); paneLogin.setVisible(false); paneSystem.setVisible(true); paneManagement.setVisible(false); panePermission.setVisible(false); paneDatabase.setVisible(false);
                    getUser = userName;
                    lblHome.setText("Olá, " + userName + "!");
                    permission = runQuery.getInfo(getUser, "permission", "users");
                    if (!permission.equals("3")) btnPermission.setVisible(false);
                    else btnPermission.setVisible(true);
                }
                else lblLoginFail.setVisible(true);
            }
            else lblLoginFail.setVisible(true);
        }
        catch (Exception ex){
            System.out.println(ex);
            lblLoginFail.setVisible(true);
        } 
    }

    @FXML
    private void register(MouseEvent event){
        lblFailedInfo.setVisible(false);
        String userName = txtUser.getText();
        String email = txtEmail.getText();
        String password = txtPasswordReg.getText();
        String passwordCheck = txtPasswordCheck.getText();
        String state = cbbState.getSelectionModel().getSelectedItem();
        Boolean userCheck = null, emailCheck = null;

        if (verifyNull(userName) || verifyNull(email) || verifyNull(password) || verifyNull(passwordCheck) || verifyNull(getPath) || verifyNull(state)){
            lblFailedInfo.setVisible(true);
            lblFailedInfo.setText("Favor, preencha todos os campos.");
        }

        else{
            userCheck = runQuery.verifyInfo(userName, "userName");
            emailCheck = runQuery.verifyInfo(email, "email");

            if (!userCheck && !emailCheck){
                if (!password.equals(passwordCheck)) {
                    lblFailedInfo.setVisible(true);
                    lblFailedInfo.setText("As senhas não conferem.");
                }
                else {
                    lblFailedInfo.setVisible(false);
                    runQuery.insertNewUser(userName, password, email, getPath, state);
                    paneRegister.setDisable(true); panePrompt.setVisible(true);
                }
            }
            else{
                lblFailedInfo.setVisible(true);
                lblFailedInfo.setText("Usuário já cadastrado.");
            }
        }
    }
    @FXML
    private void home(MouseEvent event){
        paneHome.setVisible(true); paneAccount.setVisible(false); paneManagement.setVisible(false); panePermission.setVisible(false); paneDatabase.setVisible(false); paneConfirm.setVisible(false);
        permission = runQuery.getInfo(getUser, "permission", "users");
        lnkExit.setDisable(false);
        if (!permission.equals("3")) btnPermission.setVisible(false);
        else btnPermission.setVisible(true);
    }

    @FXML
    private void userProfile(MouseEvent event){
        String email = runQuery.getInfo(getUser, "email", "users");
        String biometry = runQuery.getInfo(getUser, "biometry", "users");
        String password = runQuery.getInfo(getUser, "password", "users");
        String state = runQuery.getInfo(getUser, "state", "users");
        String[] aux = null;
        permission = runQuery.getInfo(getUser, "permission", "users");
        txtUserLogin.setEditable(false); txtUserEmail.setEditable(false); txtUserPassword.setEditable(false);
        btnCancel.setVisible(false); btnEdit.setDisable(false); btnSave.setVisible(false);
        lnkExit.setDisable(false);

        if (biometry.contains("/")) aux = biometry.split("/");
        else if (biometry.contains("\\")) aux = biometry.split("\\");
        biometry = aux[aux.length-1];

        if (permission.equals("1")) permission = permission + " - Básica";
        else if (permission.equals("2")) permission = permission + " - Diretor";
        else if (permission.equals("3")) permission = permission + " - Administrador";
        
        paneHome.setVisible(false); paneAccount.setVisible(true); paneManagement.setVisible(false); panePermission.setVisible(false); paneDatabase.setVisible(false); paneConfirm.setVisible(false);
        txtUserLogin.setText(getUser); txtUserEmail.setText(email); txtUserBiometry.setText(biometry); txtUserPermission.setText(permission); txtUserPassword.setText(password); txtUserState.setText(state);
    }

    @FXML
    private void enableEdit(MouseEvent event){
        txtUserLogin.setEditable(true); txtUserEmail.setEditable(true); txtUserPassword.setEditable(true); 
        btnCancel.setVisible(true); btnEdit.setDisable(true); btnSave.setVisible(true);
        lblAccountInfo.setVisible(false);       
    }

    @FXML
    private void cancel(MouseEvent event){
        if (paneAccount.isVisible()) userProfile(event);
    }

    @FXML
    private void save(MouseEvent event){
        if (paneAccount.isVisible()){
            String email = runQuery.getInfo(getUser, "email", "users");
            String password = runQuery.getInfo(getUser, "password", "users");
            String newLogin = txtUserLogin.getText();
            String newEmail = txtUserEmail.getText();
            String newPassword = txtUserPassword.getText();
            lblAccountInfo.setVisible(false);
            if (!verifyNull(newLogin) && !verifyNull(newEmail) && !verifyNull(newPassword)){
                if (!newLogin.equals(getUser) || !newEmail.equals(email) || !newPassword.equals(password)){
                    if (!newPassword.equals(password)) runQuery.updateInfo(getUser, "password", newPassword);
                    if (!newEmail.equals(email)) runQuery.updateInfo(getUser, "email", newEmail);
                    if (!newLogin.equals(getUser)) {
                        Boolean verifyLogin = runQuery.verifyInfo(getUser, "userName");
                        if (!verifyLogin) runQuery.updateInfo(getUser, "userName", newLogin);
                        else {lblAccountInfo.setVisible(true); lblAccountInfo.setText("Login já cadastrado.");}
                    }
                    if (!lblAccountInfo.isVisible()){
                        txtUserLogin.setEditable(false); txtUserEmail.setEditable(false); txtUserPassword.setEditable(false);
                        btnCancel.setVisible(false); btnEdit.setDisable(false); btnSave.setVisible(false);
                    }
                }
                else {
                    txtUserLogin.setEditable(false); txtUserEmail.setEditable(false); txtUserPassword.setEditable(false);
                    btnCancel.setVisible(false); btnEdit.setDisable(false); btnSave.setVisible(false);
                }
            }
            else {lblAccountInfo.setVisible(true); lblAccountInfo.setText("Campos em branco, favor revisar.");}
        }
    }

    @FXML 
    private void okPrompt(MouseEvent event) throws IOException{
        System.out.println(lblAction.getText());
        if (lblAction.getText().equals("Para confirmar a ação, insira sua biometria.")) {changePermissions(event);}
        else {showDatabase(event);}
    }

    @FXML
    private void showDatabase(MouseEvent event) throws IOException{
        paneHome.setVisible(false); paneAccount.setVisible(false); panePermission.setVisible(false); paneManagement.setVisible(false);
        if (!paneConfirm.isVisible() || !lblAction.getText().equals("Para acessar as informações, insira sua biometria.")){
            paneConfirm.setVisible(true); paneDatabase.setVisible(false);
            selected = null;
            btnCancelAction.setVisible(false);
            lblFailedConf.setVisible(false); lblImageConf.setText(null); lblAction.setText("Para acessar as informações, insira sua biometria.");
            lnkExit.setDisable(true);
        }
        else{
            if (selected != null){
                String userBiometry = runQuery.getBiometry(getUser);
                registered = ImageIO.read(new File (userBiometry));
                Boolean biometry = getImages.compareImage(selected, registered);
                lblFailedConf.setVisible(false);
                if (biometry) {
                    lnkExit.setDisable(false);
                    paneConfirm.setVisible(false); paneDatabase.setVisible(true);
                    String state = runQuery.getInfo(getUser, "state", "users");
                    permission = runQuery.getInfo(getUser, "permission", "users");
                    if (permission.equals("1")) {
                        agro1Col.setVisible(false); agro2Col.setVisible(false); agro3Col.setVisible(false); agro4Col.setVisible(false); othersCol.setVisible(false);
                        String total = runQuery.getInfo(state, "total", "agrotoxicos");
                        data = FXCollections.observableArrayList(new Database(state, "", "", "", "", "", total));
                        stateCol.setCellValueFactory(new PropertyValueFactory<>("State"));
                        totalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));
                    }

                    else{
                        String list = runQuery.getAll("*", "agrotoxicos");
                        String[] rows = list.split("--");

                        String[] row1 = rows[0].split(";");
                        String[] row2 = rows[1].split(";");
                        String[] row3 = rows[2].split(";");
                        String[] row4 = rows[3].split(";");
                        String[] row5 = rows[4].split(";");
                        String[] row6 = rows[5].split(";");
                        String[] row7 = rows[6].split(";");
                        String[] row8 = rows[7].split(";");
                        String[] row9 = rows[8].split(";");
                        String[] row10 = rows[9].split(";");
                        String[] row11 = rows[10].split(";");
                        String[] row12 = rows[11].split(";");
                        String[] row13 = rows[12].split(";");
                        String[] row14 = rows[13].split(";");
                        String[] row15 = rows[14].split(";");
                        String[] row16 = rows[15].split(";");
                        String[] row17 = rows[16].split(";");

                        data = FXCollections.observableArrayList(
                            new Database (row1[0], row1[1], row1[2], row1[3], row1[4], row1[5], row1[6]),
                            new Database (row2[0], row2[1], row2[2], row2[3], row2[4], row2[5], row2[6]),
                            new Database (row3[0], row3[1], row3[2], row3[3], row3[4], row3[5], row3[6]),
                            new Database (row4[0], row4[1], row4[2], row4[3], row4[4], row4[5], row4[6]),
                            new Database (row5[0], row5[1], row5[2], row5[3], row5[4], row5[5], row5[6]),
                            new Database (row6[0], row6[1], row6[2], row6[3], row6[4], row6[5], row6[6]),
                            new Database (row7[0], row7[1], row7[2], row7[3], row7[4], row7[5], row7[6]),
                            new Database (row8[0], row8[1], row8[2], row8[3], row8[4], row8[5], row8[6]),
                            new Database (row9[0], row9[1], row9[2], row9[3], row9[4], row9[5], row9[6]),
                            new Database (row10[0], row10[1], row10[2], row10[3], row10[4], row10[5], row10[6]),
                            new Database (row11[0], row11[1], row11[2], row11[3], row11[4], row11[5], row11[6]),
                            new Database (row12[0], row12[1], row12[2], row12[3], row12[4], row12[5], row12[6]),
                            new Database (row13[0], row13[1], row13[2], row13[3], row13[4], row13[5], row13[6]),
                            new Database (row14[0], row14[1], row14[2], row14[3], row14[4], row14[5], row14[6]),
                            new Database (row15[0], row15[1], row15[2], row15[3], row15[4], row15[5], row15[6]),
                            new Database (row16[0], row16[1], row16[2], row16[3], row16[4], row16[5], row16[6]),
                            new Database (row17[0], row17[1], row17[2], row17[3], row17[4], row17[5], row17[6]));

                        stateCol.setCellValueFactory(new PropertyValueFactory<>("State"));
                        agro1Col.setCellValueFactory(new PropertyValueFactory<>("Agro1"));
                        agro2Col.setCellValueFactory(new PropertyValueFactory<>("Agro2"));
                        agro3Col.setCellValueFactory(new PropertyValueFactory<>("Agro3"));
                        agro4Col.setCellValueFactory(new PropertyValueFactory<>("Agro4"));
                        othersCol.setCellValueFactory(new PropertyValueFactory<>("Others"));
                        totalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));

                        if (permission.equals("2")) {agro1Col.setVisible(false); agro2Col.setVisible(false); agro3Col.setVisible(false); agro4Col.setVisible(false); othersCol.setVisible(false);}
                        else {agro1Col.setVisible(true); agro2Col.setVisible(true); agro3Col.setVisible(true); agro4Col.setVisible(true); othersCol.setVisible(true);}
                    }
                    database.setItems(data);
                }
                else lblFailedConf.setVisible(true);
            }
            else lblFailedConf.setVisible(true);
        }
    }

    @FXML
    private void managePermissions(MouseEvent event){
        rbtBasic.setSelected(false); rbtDirector.setSelected(false); rbtAdmin.setSelected(false);
        rbtBasic.setDisable(false); rbtDirector.setDisable(false); rbtAdmin.setDisable(false);
        btnSavePermission.setDisable(false);
        lnkExit.setDisable(false);
        permission = runQuery.getInfo(getUser, "permission", "users");
        if (!paneManagement.isVisible()){
            paneHome.setVisible(false); panePermission.setVisible(false); paneAccount.setVisible(false); paneManagement.setVisible(true); paneDatabase.setVisible(false); paneConfirm.setVisible(false);
            lblManagement.setText("Gerenciamento de permissões");
            String users = runQuery.getAll("userName", "users");
            ObservableList<String> items =FXCollections.observableArrayList (users.split(";"));
            list.setItems(items);
        }
        else{
            panePermission.setVisible(true);
            btnCancelAction.setVisible(true);
            String userName = list.getSelectionModel().getSelectedItem();
            String permission = runQuery.getInfo(userName, "permission", "users");
            lblUserPermission.setText("A permissão do usuário " + userName + " é " + permission);
            lblSelectFail.setVisible(false);
            if (permission.equals("1")) rbtBasic.setDisable(true);
            else if (permission.equals("2")) rbtDirector.setDisable(true);
            else if (permission.equals("3")) {
                btnSavePermission.setDisable(true);
                lblSelectFail.setText("Não é possível alterar permissão de outro Administrador"); lblSelectFail.setVisible(true);
                rbtAdmin.setDisable(true); rbtDirector.setDisable(true); rbtBasic.setDisable(true);
            }
        }
    }

    @FXML private void changePermissions(MouseEvent event) throws IOException{
        int permission = 0;
        String userName = list.getSelectionModel().getSelectedItem();
        btnSavePermission.setDisable(false);
        lblSelectFail.setVisible(false);
        if (rbtBasic.isSelected()) permission = 1;
        else if (rbtDirector.isSelected()) permission = 2;
        else if (rbtAdmin.isSelected()) permission = 3;
        else {lblSelectFail.setText("Selecione uma opção"); lblSelectFail.setVisible(true);}
        if (!paneConfirm.isVisible()){
            if (permission != 0) {
                paneConfirm.setVisible(true); paneManagement.setVisible(false);
                selected = null;
                lblFailedConf.setVisible(false); lblImageConf.setText(null); lblAction.setText("Para confirmar a ação, insira sua biometria.");
                lnkExit.setDisable(true);
            }
        }
        else {
            if (selected != null){
                String userBiometry = runQuery.getBiometry(getUser);
                registered = ImageIO.read(new File (userBiometry));
                Boolean biometry = getImages.compareImage(selected, registered);
                lblFailedConf.setVisible(false);
                if (biometry) {
                    runQuery.updatePermission(userName, permission);
                    lnkExit.setDisable(false);
                    paneConfirm.setVisible(false); paneManagement.setVisible(true);
                    managePermissions(event);
                }
                else lblFailedConf.setVisible(true);
            }
            else lblFailedConf.setVisible(true);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        paneRegister.setVisible(false);
        paneDatabase.setVisible(false);
        paneSystem.setVisible(false);
        paneLogin.setVisible(true);
        lblLoginFail.setVisible(false);
    }
}