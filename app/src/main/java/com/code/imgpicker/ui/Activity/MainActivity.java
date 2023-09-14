package com.code.imgpicker.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.code.imgpicker.ImgRxBottomPicker;
import com.code.imgpicker.R;
import com.code.imgpicker.databinding.ActivityMainBinding;
import com.code.imgpicker.ui.Adapter.AddPhotosAdapter;
import com.code.imgpicker.ui.Model.AddPhotosModel;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private List<Uri> selectedUriList = new ArrayList<>();
    private Disposable multiImageDisposable;

    AddPhotosAdapter driDocAdapter;
    ArrayList<AddPhotosModel> docModel = new ArrayList<>();

    ArrayList<String> Arraylist_image_encode = new ArrayList<>();
    String driver_doc = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setOnClick();
    }

    private void setOnClick() {

        binding.btnMulAdd.setOnClickListener(view -> {

            selectedUriList.clear();
            docModel.clear();
            Arraylist_image_encode.clear();

            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    showPickerBottomSheetMul();
                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                }
            };

            checkPermission(permissionlistener);


        });
    }

    private void showPickerBottomSheetMul() {

        multiImageDisposable = ImgRxBottomPicker.with(MainActivity.this)
                .setPeekHeight(800)
                .showTitle(false)
                .setPreviewMaxCount(Integer.MAX_VALUE)
                .setSelectedForeground(R.drawable.ic_select_pic)
                .setCompleteButtonText("Done")
                .setEmptySelectionText("No Select")
                .setSpacing(5)
                .showTitle(true)
                .setTitle("Maximum 4 images")
                .setSpacingResId(R.dimen.img_picker)
                .setSelectMaxCountErrorText("Maximum 4 images")
                .setSelectedUriList(selectedUriList)
                .setCameraTileBackgroundResId(R.color.black)
                .setGalleryTileBackgroundResId(R.color.black)
                .setSelectMaxCount(4)
                .showMultiImage()
                .subscribe(uris -> {
                    selectedUriList = uris;
                    showUriListMul(uris);
                }, Throwable::printStackTrace);
    }

    private void checkPermission(PermissionListener permissionListener) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            TedPermission.with(MainActivity.this)
                    .setPermissionListener(permissionListener)
                    .setDeniedMessage("""
                            If you reject permission,you can not use this service

                            Please turn on permissions at [Setting] > [Permission]""")
                    .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES)
                    .check();
        } else {
            TedPermission.with(MainActivity.this)
                    .setPermissionListener(permissionListener)
                    .setDeniedMessage("""
                            If you reject permission,you can not use this service

                            Please turn on permissions at [Setting] > [Permission]""")
                    .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .check();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showUriListMul(List<Uri> uriList) {

        for (Uri uri : uriList) {

            String encodedstring = toBase64Mul(uri, MainActivity.this);

            binding.rvDriverDoc.setVisibility(View.VISIBLE);
            docModel.add(new AddPhotosModel(uri, encodedstring));

        }

        driDocAdapter.notifyDataSetChanged();

    }

    //GET base64 String here to call this method
    private void Encode_ImageMul() {
        Arraylist_image_encode.clear();
        for (AddPhotosModel s : docModel) {
            Arraylist_image_encode.add(s.getEncode_string());
        }
        driver_doc = Encode_Image(Arraylist_image_encode);
    }


    public String Encode_Image(ArrayList<String> list) {

        AtomicReference<String> listString = new AtomicReference<>("");

        Thread th = new Thread(() -> {

            StringBuilder sb = new StringBuilder();

            for (String s : list) {
                sb.append(listString.get()).append(s).append("IMAGE:");
            }

            listString.set(sb.toString());

        });

        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return listString.get();
    }

    public static String toBase64Mul(Uri uri, Context context) {
        AtomicReference<String> b64 = new AtomicReference<>("");
        Thread th = new Thread(() -> {
            InputStream imageStream = null;

            try {
                imageStream = context.getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 60, baos);
            byte[] bArr = baos.toByteArray();
            String result = Base64.encodeToString(bArr, Base64.DEFAULT);
            b64.set(result);

        });
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return b64.get();
    }
}