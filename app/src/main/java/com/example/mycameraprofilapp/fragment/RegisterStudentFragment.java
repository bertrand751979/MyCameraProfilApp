package com.example.mycameraprofilapp.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.mycameraprofilapp.R;
import com.example.mycameraprofilapp.model.Student;
import com.example.mycameraprofilapp.viewModel.RegisterStudentFragmentViewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterStudentFragment extends Fragment {
    private EditText editName;
    private EditText editClassroom;
    private EditText editSport;
    private TextView photoPath;
    private AppCompatImageView photoImageView;
    private Button btnTakePicture;
    private Button btnAddCountry;
    private RegisterStudentFragmentViewModel registerStudentFragmentViewModel;
    private ActivityResultLauncher<Intent> startCamera  = null;
    private ActivityResultLauncher<String> cameraPermission;
    private ActivityResultLauncher<Intent> photoChooseFromGallery;
    private Bitmap imageBitmap;
    private Button btnDisplayPhotoPath;
    private AppCompatImageView photoPathImageDisplay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Saisie");
        registerStudentFragmentViewModel = new ViewModelProvider(this).get(RegisterStudentFragmentViewModel.class);
        cameraPermission  = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result){
                    takePhotoWithDeviceCamera();
                }else{
                    Toast.makeText(RegisterStudentFragment.this.getContext(), "Accès  Caméra Refusé", Toast.LENGTH_SHORT).show();
                }
            }
        });

        startCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            setImageCapture(result);
                        }
                    }
                });

        photoChooseFromGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            setImageChooseIntoTheGallery(result);
                        }
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDisplayPhotoPath = view.findViewById(R.id.btn_display_image_from_photoPath);
        photoPathImageDisplay = view.findViewById(R.id.edit_photo_path_image);
        editName = view.findViewById(R.id.edit_student_name);
        editClassroom = view.findViewById(R.id.edit_student_classroom);
        editSport = view.findViewById(R.id.edit_student_sport);
        photoImageView = view.findViewById(R.id.edit_camera_picture);
        btnTakePicture = view.findViewById(R.id.btn_take_picture);
        photoPath = view.findViewById(R.id.edit_photo_path);
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialogChooseAction();
            }
        });
        btnAddCountry = view.findViewById(R.id.btn_save_student);
        btnAddCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setName(editName.getText().toString());
                student.setClassroom(editClassroom.getText().toString());
                student.setSport(editSport.getText().toString());
                registerStudentFragmentViewModel.addCountry(student,getContext());
                Toast.makeText(RegisterStudentFragment.this.getContext(), "Ajouté", Toast.LENGTH_SHORT).show();
            }
        });
        btnDisplayPhotoPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoPathImageDisplay.setImageBitmap(BitmapFactory.decodeFile(photoPath.getText().toString()));
            }
        });
    }

    private void openAlertDialogChooseAction() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterStudentFragment.this.getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    cameraPermission.launch(Manifest.permission.CAMERA);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    openDeviceGallery();
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void takePhotoWithDeviceCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startCamera.launch(takePictureIntent);
    }


    private void openDeviceGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        photoChooseFromGallery.launch(intent);
    }


    public static String saveToCacheMemory(Activity activity, Bitmap bitmapImage){
        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        ContextWrapper cw = new ContextWrapper(activity);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,mDateFormat.format(new Date())  + ".jpeg");
        Log.d(TAG, "directory: " + directory);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.d(TAG, "bit exception: Success" );
        } catch (Exception e) {
            Log.d(TAG, "bit exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "io exce: " + e.getMessage());
            }
        }
        Log.d(TAG, "absolute path " + directory.getAbsolutePath());
        return mypath.getAbsolutePath();
    }


    private void setImageCapture(ActivityResult result){
        if(result.getData()!=null){
            Bundle extras = result.getData().getExtras();
            /*Bitmap*/ imageBitmap = (Bitmap) extras.get("data");
            photoImageView.setImageBitmap(imageBitmap);
            saveToCacheMemory(getActivity(),imageBitmap);
            photoPath.setText(saveToCacheMemory(getActivity(),imageBitmap));
        }
    }

    private void setImageChooseIntoTheGallery(ActivityResult result){
        if(result.getData()!=null){
            Intent data = result.getData();
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                photoImageView.setImageBitmap(selectedImage);
                saveToCacheMemory(getActivity(),selectedImage);
                photoPath.setText(saveToCacheMemory(getActivity(),selectedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(RegisterStudentFragment.this.getContext(), "Erreur", Toast.LENGTH_LONG).show();
            }
        }
    }




}
