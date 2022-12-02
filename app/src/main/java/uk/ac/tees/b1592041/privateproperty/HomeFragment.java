package uk.ac.tees.b1592041.privateproperty;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;

    private FirestoreRecyclerAdapter firestoreAdapter;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragm
        View fview = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = fview.findViewById(R.id.recyclerview);
        Query query = firebaseFirestore.collection("property");

        PropertyModel x = new PropertyModel("", "", "");

        FirestoreRecyclerOptions<PropertyModel> options = new FirestoreRecyclerOptions.Builder<PropertyModel>()
                .setQuery(query, PropertyModel.class)
                .build();
        firestoreAdapter = new FirestoreRecyclerAdapter<PropertyModel, PropertyViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull PropertyViewHolder holder, int position, @NonNull PropertyModel model) {
                holder.amount.setText(model.getAmount());
                holder.location.setText(model.getLocation());
                String url = model.getImage();
                Log.i("TAG", "url>>>> " + url);
                Picasso.get().load(url).into(holder.displayImg);


            }

            @NonNull
            @Override
            public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.singleproperty, parent, false);
                return new PropertyViewHolder(view);

            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(firestoreAdapter);

        return fview;
    }

    @Override
    public void onStart() {
        super.onStart();
        firestoreAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firestoreAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(firestoreAdapter);
    }

    private class PropertyViewHolder extends RecyclerView.ViewHolder {
        TextView amount;
        TextView location;
        ImageView displayImg;

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            location = itemView.findViewById(R.id.location);
            displayImg = itemView.findViewById(R.id.displayImg);
        }


    }
}

