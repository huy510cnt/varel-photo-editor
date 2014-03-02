package com.varel.photo_editor.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.varel.photo_editor.R;
import com.varel.photo_editor.abstract_libs.ImageFragment;
import com.varel.photo_editor.activities.FiltersEditorActivity;
import com.varel.photo_editor.activities.SquareActivity;

public class MainFragment extends ImageFragment {

   private Button ButtonCreateSquare;
   private Button ButtonCreateFilters;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.main_fragment, parent, false);

      ButtonCreateSquare = (Button) v.findViewById(R.id.button_create_square);
      ButtonCreateSquare.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Intent i = new Intent(getActivity(), SquareActivity.class);
            startActivity(i);
         }
      });

      ButtonCreateFilters = (Button) v.findViewById(R.id.button_create_filters);
      ButtonCreateFilters.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Intent i = new Intent(getActivity(), FiltersEditorActivity.class);
            startActivity(i);
         }
      });
      return v;
   }

   @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (resultCode != Activity.RESULT_OK) return;
      //if (requestCode == REQUEST_DATE) { }
   }

   public static MainFragment newInstance() {
      Bundle args = new Bundle();
      //args.putSerializable(EXTRA_CRIME_ID, crimeId);
      MainFragment fragment = new MainFragment();
      fragment.setArguments(args);
      return fragment;
   }
}
