package com.varel.photo_editor.activities;

import android.app.Fragment;
import com.varel.photo_editor.fragments.FiltersEditorFragment;
import com.varel.photo_editor.libs.FrameWorkActivity;

public class FiltersEditorActivity extends FrameWorkActivity {
   @Override
   protected Fragment createFragment() {
      return new FiltersEditorFragment();
   }
}
