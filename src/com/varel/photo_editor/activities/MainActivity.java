package com.varel.photo_editor.activities;

import android.app.Fragment;
import com.varel.photo_editor.fragments.MainFragment;
import com.varel.photo_editor.abstract_libs.FrameWorkActivity;

public class MainActivity extends FrameWorkActivity {
   @Override
   protected Fragment createFragment() {
      return new MainFragment();
   }
}
