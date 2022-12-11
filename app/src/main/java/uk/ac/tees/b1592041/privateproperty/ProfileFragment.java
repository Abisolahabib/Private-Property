package uk.ac.tees.b1592041.privateproperty;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfileFragment extends Fragment {
    TextView name;
    TextInputLayout username, email, phonenumber, lastname, address, firstname;
    String TAG = ProfileFragment.class.getSimpleName();

    //    updates
    String newfirstName;
    String newUserName;
    String newlastName;
    String newEmail;
    String newPhoneNumber;
    String newAddress;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DocumentReference documentReference;
    private Button update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.full_profile_name);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        phonenumber = view.findViewById(R.id.phonenumber);
        firstname = view.findViewById(R.id.firstname);
        lastname = view.findViewById(R.id.lastname);
        address = view.findViewById(R.id.address);
        update = view.findViewById(R.id.update);

        Intent intent = getActivity().getIntent();
        String userEmail = intent.getStringExtra(LoginActivity.EXTRA_CONTENT);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    Log.d(TAG, "onSuccess : LIST EMPTY");
                    Toast.makeText(getActivity(), "List Empty", Toast.LENGTH_SHORT).show();
                } else {
                    UserModel user = documentSnapshot.toObject(UserModel.class);
                    name.setText(user.getFirstname() + " " + user.getLastname());
                    username.getEditText().setText(user.getUsername());
                    email.getEditText().setText(user.getEmail());
                    phonenumber.getEditText().setText(user.getPhonenumber());
                    address.getEditText().setText(user.getAddress());
                    firstname.getEditText().setText(user.getFirstname());
                    lastname.getEditText().setText(user.getLastname());


                }
            }
        });

        Places.initialize(getActivity().getApplicationContext(), " AIzaSyCtbpvaJqlTf29XfZh798ZGiC4TZ7HUfD8");
        address.getEditText().setFocusable(false);
        address.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(getActivity());
                startActivityForResult(intent, 1);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateFields(firstname) || !validateFields(lastname) || !validateFields(phonenumber) || !validateFields(username) || !validateFields(address)) {
                    return;
                }

                newfirstName = firstname.getEditText().getText().toString().trim();
                newlastName = lastname.getEditText().getText().toString().trim();
                newEmail = email.getEditText().getText().toString().trim();
                newPhoneNumber = phonenumber.getEditText().getText().toString().trim();
                newUserName = username.getEditText().getText().toString().trim();
                newAddress = address.getEditText().getText().toString().trim();

                Map<String, Object> userDetails = new HashMap<>();
                userDetails.put("Address", newAddress);
                userDetails.put("firstname", newfirstName);
                userDetails.put("lastname", newlastName);
                userDetails.put("phonenumber", newPhoneNumber);
                userDetails.put("username", newUserName);
                Log.i(TAG, "NEW USER NAME>>> " + newUserName);
                documentReference.update(userDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), " User update successful", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);
//            set address on text
            address.getEditText().setText(place.getAddress());


        }
    }

    //validate email
    private Boolean validateFields(TextInputLayout textInputLayout) {
        String val = textInputLayout.getEditText().getText().toString();

        if (val.isEmpty()) {
            textInputLayout.setError("Field cannot be empty");
            return false;
        } else {
            textInputLayout.setError(null);

            return true;
        }


    }
}