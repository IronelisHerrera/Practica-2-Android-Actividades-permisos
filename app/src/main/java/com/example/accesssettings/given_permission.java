package com.example.accesssettings;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class given_permission extends AppCompatActivity {


    Button storage;
    Button location;
    Button call_phone;
    Button check_contact;
    ImageView android_image;
    ImageView storage_image;

    final static int LOCATION_CODE = 201;
    final static int CAMARA_CODE = 202;
    private static final int PHONE_CALL_NUMBER = 203;
    final static int CONTACTS_CODE = 204;
    final static int STORAGE_CODE = 205;
    final static int PHONE_CALL_CODE = 206;
    final static String GOOGLE_MAPS_URL = "https://www.google.com/maps/place/Pontificia+Universidad+Cat%C3%B3lica+Madre+y+Maestra/@19.4436055,-70.6865672,17z/data=!3m1!4b1!4m5!3m4!1s0x8eb1cf64929050fb:0x4f33915a6b4a939a!8m2!3d19.4436005!4d-70.6843785";

    //private static final String NUMBER = "tel:8400000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_given_permission);

        Intent get_continue_btn_intent = getIntent();
        storage = findViewById(R.id.storage_btn_id);
        location = findViewById(R.id.location_btn_id);
        storage_image = findViewById(R.id.imageView_storage);
        android_image = findViewById(R.id.imageView1);
        call_phone = findViewById(R.id.phone_btn_id);
        check_contact = findViewById(R.id.contacts_btn_id);




    }
    public void check_storage_listener(View storage_btn){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

            AlertDialog.Builder window_storage_dialog = new AlertDialog.Builder(this);
            window_storage_dialog.setMessage("Quiere acceder al almacenamiento?");
            window_storage_dialog.setTitle("CUADRO INFORMATIVO");
            window_storage_dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    access_to_my_storage();
                }
            });
            window_storage_dialog.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            window_storage_dialog.create().show();
        }
        else{
            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(storage, "Es necesario que solicite permiso para acceder", Snackbar.LENGTH_SHORT).show();
            }
        }
        requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 200);

    }

    private void access_to_my_storage() {
        Intent storage_intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        storage_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        storage_intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.parse("/storage/"));
        startActivityForResult(storage_intent, STORAGE_CODE);
    }


    public void check_location_listener(View location_btn){
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            AlertDialog.Builder window_location_dialog = new AlertDialog.Builder(this);
            window_location_dialog.setMessage("¿Quiere acceder al mapa?");
            window_location_dialog.setTitle("CUADRO INFORMATIVO");
            window_location_dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    access_to_my_location();
                }
            });
            window_location_dialog.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            window_location_dialog.create().show();
        }
        else{
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                Snackbar.make(location, "Es necesario que solicite permiso para acceder", Snackbar.LENGTH_SHORT).show();
            }
        }
        requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 200);

    }

    private void access_to_my_location() {
        Uri location_uri = Uri.parse(GOOGLE_MAPS_URL);
        Intent location_intent= new Intent(Intent.ACTION_VIEW, location_uri);
        location_intent.setPackage("com.google.android.apps.maps");
        startActivityForResult(location_intent, LOCATION_CODE);
    }


    public void check_camara_listener(View camara_btn){
        if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            AlertDialog.Builder window_camara_dialog = new AlertDialog.Builder(this);
            window_camara_dialog.setMessage("Quiere acceder a la cámara?");
            window_camara_dialog.setTitle("CUADRO INFORMATIVO");
            window_camara_dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    access_to_my_camara();
                }
            });
            window_camara_dialog.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            window_camara_dialog.create().show();

        }
        else{
            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                Snackbar.make(android_image, "Es necesario que solicite permiso para acceder", Snackbar.LENGTH_SHORT).show();
            }
        }
        requestPermissions(new String[] {Manifest.permission.CAMERA}, 200);

    }

    private void access_to_my_camara() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         startActivityForResult(cameraIntent, CAMARA_CODE);
    }
    //phone
    public void call_phone_listener(View location_btn){
//        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED){
//
//            AlertDialog.Builder window_phone_dialog = new AlertDialog.Builder(this);
//            window_phone_dialog.setTitle("CUADRO INFORMATIVO");
//            window_phone_dialog.setMessage("Para realizar esta acción debe otorgar permiso para acceder a los contactos");
//            window_phone_dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//
//                }
//            });
//            window_phone_dialog.create().show();
//
//        }
        //else{

            if(checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                AlertDialog.Builder window_phone_dialog = new AlertDialog.Builder(this);
                window_phone_dialog.setTitle("CUADRO INFORMATIVO");
                window_phone_dialog.setMessage("¿Quiere hacer una llamada?");
                window_phone_dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        call_contact_number();
                    }
                });
                window_phone_dialog.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                window_phone_dialog.create().show();

            }
            else{
                if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                    Snackbar.make(call_phone, "Es necesario que solicite permiso para acceder", Snackbar.LENGTH_SHORT).show();
                }
            }
            requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, 200);
        //}

    }
    public void check_contacts_listener(View contacts_btn){
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            AlertDialog.Builder window_contacts_dialog = new AlertDialog.Builder(this);
            window_contacts_dialog.setTitle("CUADRO INFORMATIVO");
            window_contacts_dialog.setMessage("Si acepta esta opción, estará otorgando permiso para que la aplicación pueda la información de contactos");
            window_contacts_dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    check_contacts();
                }
            });
            window_contacts_dialog.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            window_contacts_dialog.create().show();

        }
        else{
            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)){
                Snackbar.make(check_contact, "Es necesario que solicite permiso para acceder", Snackbar.LENGTH_SHORT).show();
            }
        }
        requestPermissions(new String[] {Manifest.permission.CAMERA}, 200);

    }

    private void check_contacts() {

        Intent check_contacts = new Intent(Intent.ACTION_PICK);
        check_contacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        if (check_contacts.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(check_contacts, CONTACTS_CODE);

        }else{
            Toast.makeText(this, "Ocurrió un error al entrar a la agenda", Toast.LENGTH_SHORT).show();
        }
    }

    private void call_contact_number() {

        // Start an activity for the user to pick a phone number from contacts
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, PHONE_CALL_CODE);
            }
            else {
                Toast.makeText(this, "No se pudo llamar!!!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Para realizar esta acción debe otorgar permiso para acceder a los contactos", Toast.LENGTH_SHORT).show();
        }



//        Intent call_phone_number = new Intent(Intent.ACTION_CALL);
//        call_phone_number.setData(Uri.parse(NUMBER));
//        if (call_phone_number.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(call_phone_number, PHONE_CALL_CODE);
//
//        }else{
//            Toast.makeText(this, "No se pudo llamar al número"+" "+NUMBER, Toast.LENGTH_SHORT).show();
//        }

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == CAMARA_CODE){
                Bitmap camera_image = (Bitmap) data.getExtras().get("data");
                android_image.setImageBitmap(camera_image);
            }
        }
        if(resultCode == RESULT_OK){
            if(requestCode == STORAGE_CODE){
                Toast.makeText(this, "Ok!", Toast.LENGTH_SHORT).show();
            }
        }
        if(resultCode==RESULT_OK){
            if(requestCode == LOCATION_CODE){
                Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
            }
        }

        if ( resultCode == RESULT_OK) {
            if(requestCode == PHONE_CALL_CODE){
                // Get the URI and query the content provider for the phone number
                Intent c = new Intent(Intent.ACTION_CALL);
                Uri contactUri = data.getData();
                String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor = getContentResolver().query(contactUri, projection,
                        null, null, null);
                // If the cursor returned is valid, get the phone number
                if (cursor != null && cursor.moveToFirst()) {
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(numberIndex);
                    c.setData(Uri.parse("tel:"+number));
                    startActivityForResult(c, PHONE_CALL_CODE);
                }
            }

        }
        if(resultCode ==RESULT_OK){
            if(requestCode ==CONTACTS_CODE){
                Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
            }
        }


//        if(resultCode == RESULT_OK) {
//            if (requestCode == PHONE_CALL_CODE) {
//                //
//                // Get the URI and query the content provider for the phone number
//                Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
//            }
//
//        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {


        if(requestCode == 200 && grantResults.length >0 ){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Snackbar.make(storage, "Permiso otorgado", Snackbar.LENGTH_SHORT).show();

            } else {
                Snackbar.make(storage, "El permiso no fue otorgado", Snackbar.LENGTH_SHORT).show();
            }


        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


}