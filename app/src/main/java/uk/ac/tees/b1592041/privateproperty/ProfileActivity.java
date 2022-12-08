////package uk.ac.tees.b1592041.privateproperty;
////
////import androidx.activity.result.ActivityResultLauncher;
////import androidx.activity.result.contract.ActivityResultContracts;
////import androidx.appcompat.app.AppCompatActivity;
////
////import android.os.Bundle;
////
////import android.graphics.Bitmap;
////import android.graphics.BitmapFactory;
////import android.net.Uri;
////import android.provider.MediaStore;
////import android.util.Log;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.ImageView;
////import androidx.fragment.app.Fragment;
////
////import java.io.FileNotFoundException;
////import java.io.InputStream;
////import com.google.firebase.firestore.FirebaseFirestore;
////import android.content.Intent;
////
////public class ProfileActivity extends AppCompatActivity {
////
////
////        ImageView bannerImage;
////
////        private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
////                new ActivityResultContracts.StartActivityForResult(),
////                result -> {
////                    if (result.getResultCode() == getActivity().RESULT_OK) {
////                        if (result.getData() != null) {
////                            Uri imageUri = result.getData().getData();
////
////                            InputStream inputStream = null;
////                            try {
////                                inputStream = getContext().getContentResolver().openInputStream(imageUri);
////                            } catch (FileNotFoundException e) {
////                                e.printStackTrace();
////                            }
////                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
////                            bannerImage.setImageURI(result.getData().getData());
////                            bannerImage.setImageBitmap(bitmap);
////                            encodeImage = encodeImage(bitmap);
////
////
////                        }
////                    }
////                }
////        );
//
//
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//
//
//
//
//            bannerImage = layout.findViewById(R.id.image_banner);
//
//
//
//
//            }
//
//
//
//
//            imageLayout.setOnClickListener(v -> {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                startActivityForResult(intent, 101);
//            });
//
//
//
//                createdEvent.put("Image", encodeImage);
//
//
//
//                {
//                    Uri imageUri = data.getData();
//                    InputStream inputStream = null;
//                    try {
//                        inputStream = getActivity().getApplicationContext().getContentResolver().openInputStream(imageUri);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    bannerImage.setImageURI(data.getData());
//                    bannerImage.setImageBitmap(bitmap);
//
//                    encodeImage = encodeImage(bitmap);
//                    Log.d("IMAGE", "onActivityResult: " + encodeImage);
//                }
//            }
//        }
//
//        private String encodeImage(Bitmap bitmap){
//
//        private String encodeImage(Bitmap bitmap) {
//            int previewWidth = 150;
//            int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
//            Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
//            byte[] bytes = byteArrayOutputStream.toByteArray();
//            return Base64.encodeToString(bytes, Base64.DEFAULT);
//
//
//
//    }
//
//}