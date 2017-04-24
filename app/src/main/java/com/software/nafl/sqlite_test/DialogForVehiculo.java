package com.software.nafl.sqlite_test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.software.nafl.sqlite_test.Modelos.Vehiculo;

/**
 * Created by Neury on 3/30/2017.
 */

public class DialogForVehiculo extends DialogFragment{

    private static final String TAG = DialogForVehiculo.class.getName();

    EditText compania,ficha,fecha,latitud,longitud,creadoPor;

    int a =1;
    private Vehiculo vehiculoM;

    public interface DialogAddVehiculoListener {
        //Método que se dispara cuando se intenta agregar uan nueva nota
        void onDialogAddVehiculo(Vehiculo newVehiculo);
        //Método que se dispara cuando se cancela el dialogo de agregar nueva nota
        void onDialogCancel();
        //Método que se dispara cuando se desea actualizar una nota
        void onDialogUpdNote(Vehiculo newVehiculo);
    }

    // Declaramos una instancia de la interfaz antes descrita para usarla para enviar
    // la info al Activity Principal

    DialogAddVehiculoListener mListener;

    // Sobre escribimos el metodo Fragment.onAttach() para instanciar method al DialogAddNoteListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verificamos se la MainActivity implementa la interfaz callback DialogAddNoteListener
        try {
            // Instanciamos el listener DialogAddNoteListener para que pueda comunucarse luego
            // con la MainActivity
            mListener = (DialogAddVehiculoListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Obtenemos una referencia al layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Inflamos la vista del Dialogo
        View rootView = inflater.inflate(R.layout.dialog_addvehiculo, null);
        compania = (EditText) rootView.findViewById(R.id.compania);
        ficha = (EditText) rootView.findViewById(R.id.ficha);
        fecha = (EditText) rootView.findViewById(R.id.fecha);
        longitud = (EditText)rootView.findViewById(R.id.longitud);
        latitud = (EditText) rootView.findViewById(R.id.latitud);
        creadoPor = (EditText) rootView.findViewById(R.id.creadopor);
        //Seteamos los valores de la nota que se desea actualizar (si aplica)
        setDataToUpdate();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(rootView)
                // Add action buttons
                .setPositiveButton(R.string.agregar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (mListener != null){
                            if (vehiculoM != null) {
                                //Actualizamos las propiedades de la nota
                                vehiculoM.setCompania_2403(compania.getText().toString());
                                vehiculoM.setFicha_2403(ficha.getText().toString());
                                vehiculoM.setFecha_2403(Integer.parseInt(fecha.getText().toString()));
                                vehiculoM.setLatitud_2403(latitud.getText().toString());
                                vehiculoM.setLongitud_2403(longitud.getText().toString());
                                vehiculoM.setCreado_por_2403(fecha.getText().toString());


                                //Ejecutamos el método onDialogUpdNote que esta implementado en el MainActivity
                                mListener.onDialogUpdNote(vehiculoM);
                            } else {
                                //Instanciamos la nueva nota
                                Vehiculo vehiculo = new Vehiculo(compania.getText().toString(),ficha.getText().toString(),Integer.parseInt(fecha.getText().toString()), latitud.getText().toString(),longitud.getText().toString(),creadoPor.getText().toString());
                                //Ejecutamos el método onDialogAddNote que esta implementado en el MainActivity
                                mListener.onDialogAddVehiculo(vehiculo);
                            }
                        }
                        //Cancelamos el Dialogo
                        DialogForVehiculo.this.getDialog().cancel();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (mListener != null){
                            //Ejecutamos el método onDialogCancel que esta implementado en el MainActivity
                            mListener.onDialogCancel();
                        }
                        //Cancelamos el Dialogo
                        DialogForVehiculo.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    public Vehiculo getmVehiculoForUpd() {
        return vehiculoM;
    }

    public void setmVehiculoForUpd(Vehiculo vehiculoM) {
        this.vehiculoM = vehiculoM;
    }

    private void setDataToUpdate(){
        if (vehiculoM != null){
            compania.setText(vehiculoM.getCompania_2403());
            ficha.setText(vehiculoM.getFicha_2403());
            fecha.setText(String.valueOf(vehiculoM.getFecha_2403()));
            latitud.setText(vehiculoM.getLatitud_2403());
            longitud.setText(vehiculoM.getLongitud_2403());
            creadoPor.setText(vehiculoM.getCreado_por_2403());
        }
    }

}
