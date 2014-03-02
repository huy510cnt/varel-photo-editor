package com.varel.photo_editor.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import com.varel.photo_editor.R;
import com.varel.photo_editor.fragments.MainFragment;
import com.varel.photo_editor.libs.FrameWorkActivity;

public class MainActivity extends FrameWorkActivity {
   @Override
   protected Fragment createFragment() {
      return new MainFragment();
   }
}
