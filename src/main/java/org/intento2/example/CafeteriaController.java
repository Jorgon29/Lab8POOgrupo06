package org.intento2.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;

public class CafeteriaController {

    @FXML
    private Spinner<Integer> spnVegetales;
    @FXML
    private Spinner<Integer> spnCrepeado;
    @FXML
    private Spinner<Integer> spnCarne;
    @FXML
    private Spinner<Integer> spnAlas;
    @FXML
    private Label txtCantCrepeadoInt;
    @FXML
    private Label txtCantCarneInt;
    @FXML
    private Label txtCantAlasInt;
    @FXML
    private Label txtTotalInt;
    @FXML
    private Label txtCantVegetalesInt;
    @FXML
    private RadioButton rbtnTarjeta;
    @FXML
    private RadioButton rbtnEfectivo;
    @FXML
    private RadioButton rbtnEmpleado;
    @FXML
    private RadioButton rbtnEstudiante;
    @FXML
    private TextArea inpNombre;
    private boolean descuentoActivo;

    private float descuento = 0.0f;

    @FXML
    protected void actualizarValores(){
        try{

            double total = 0.0f;

            double totalAlas = spnAlas.getValue()*1.75;
            double totalCrepeado = spnCrepeado.getValue()*1.25;
            double totalCarne = spnCarne.getValue()*2.25;
            double totalVegetales = spnVegetales.getValue()*0.75;

            total += totalAlas;
            total += totalCrepeado;
            total += totalCarne;
            total += totalVegetales;

            txtCantAlasInt.setText("$" + totalAlas);;
            txtCantCarneInt.setText("$" + totalCarne);
            txtCantCrepeadoInt.setText("$" + totalCrepeado);
            txtCantVegetalesInt.setText("$" + totalVegetales);

            txtTotalInt.setText("$" + Math.round((total - total*descuento)*100.0) / 100.0);
        } catch (Exception e){
            System.out.println("Exception en actualizar valores");
            System.out.println(e.getMessage());
        }

    }

    @FXML
    protected void onClickRbtnEmpleado(){

        rbtnEstudiante.setSelected(false);
        if (descuentoActivo){
            //Por si se desactiva el boton sin presionar el boton de estudiante
            descuento = 0.0f;
            descuentoActivo = false;
        } else {
            descuento = 0.1f;
            descuentoActivo = true;
        }
        actualizarValores();


    }

    @FXML
    protected void onClickRbtnEstudiante(){
        descuento = 0.0f;
        rbtnEmpleado.setSelected(false);
        actualizarValores();
        descuentoActivo = false;
    }

    @FXML
    protected void onClickRbtnTarjeta(){
        rbtnEfectivo.setSelected(false);
    }
    @FXML
    protected void onClickRbtnEfectivo(){
        rbtnTarjeta.setSelected(false);
    }

    @FXML
    protected void limpiar(){
        rbtnTarjeta.setSelected(false);
        rbtnEfectivo.setSelected(false);
        rbtnEmpleado.setSelected(false);
        rbtnEstudiante.setSelected(false);
        txtCantCarneInt.setText("$0.0");
        txtCantVegetalesInt.setText("$0.0");
        txtCantCrepeadoInt.setText("$0.0");
        txtCantAlasInt.setText("$0.0");
        txtTotalInt.setText("$0.0");
        inpNombre.setText("");
        spnCrepeado.getValueFactory().setValue(0);
        spnAlas.getValueFactory().setValue(0);
        spnCarne.getValueFactory().setValue(0);
        spnVegetales.getValueFactory().setValue(0);
    }

    @FXML
    protected void comprar(){
        if (inpNombre.getText().length() < 6){

            Alert nombreCorto = new Alert(Alert.AlertType.WARNING);
            nombreCorto.setTitle("Cafeteria UCA");
            nombreCorto.setContentText("Digite un nombre valido! \n(Mayor a 6 caracteres)");
            nombreCorto.showAndWait();
            return;
            }

        if (txtTotalInt.getText().equals("$0.0")){
            Alert totalCero = new Alert(Alert.AlertType.ERROR);
            totalCero.setTitle("Cafeteria UCA");
            totalCero.setContentText("Debe haber seleccionado algo");
            totalCero.showAndWait();
            return;
        }
        try{
            String formaPago;
            if (rbtnEfectivo.isSelected()){
                formaPago = "efectivo";
            } else if(rbtnTarjeta.isSelected()){
                formaPago = "tarjeta";
            } else {
                formaPago = "no seleccionada";
            }

            Alert compra = new Alert(Alert.AlertType.CONFIRMATION);
            float total = Float.parseFloat(txtTotalInt.getText().substring(1));
            compra.setTitle("Cafeteria UCA");
            compra.setContentText(
                    "Bienvenido " + inpNombre.getText() + "\n"
                            +"Subtotal: $" + (total + total*descuento) + "\n"
                            +"Descuento: $" + total*descuento + "\n"
                            +"Total: $" + total + "\n"
                            +"Forma de pago: " + formaPago + "\n"
                            +"Gracias por su compra :D"
            );
            compra.showAndWait();
        } catch (Exception e){
            System.out.println("Exception en compra");
            System.out.println(e.getMessage());
        }



    }

    public CafeteriaController(){}


}