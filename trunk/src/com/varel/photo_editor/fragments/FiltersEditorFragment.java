package com.varel.photo_editor.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.Toast;
import com.varel.photo_editor.R;
import com.varel.photo_editor.libs.ImageFilters;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FiltersEditorFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener{

   View.OnClickListener onClickListener;
   View.OnLongClickListener onLongClickListener;
   private Bitmap imageBitmap;
   private Bitmap imageBitmap_;
   private Bitmap imageBitmap_active;
   private ImageFilters imageFilters = new ImageFilters();
   List<Bitmap> imagesEffectSave;
   List<Integer> imagesEffectSaveIndex;
   private ImageView imageView;

   private int MAX_WIDTH_IMAGE = 1024;
   private int MAX_HEIGHT_IMAGE = 1024;
   private String TAG = "TAG_ID";
   private boolean isAddImage = false;
   private boolean isBorder = false;
   private boolean isGaussian = false;
   private boolean isLight = false;
   private float isRotate = 0;
   private String folderApplication = Environment.getExternalStorageDirectory() + "/PhotoEditor/";

   public static final int ID_MENU_GET_IMAGE = 100;
   public static final int ID_MENU_SAVE_IMAGE = 200;
   public static final int ID_MENU_ROTATE = 300;
   public static final int ID_MENU_BUTTON_CAMERA = 101;
   public static final int ID_MENU_BUTTON_GALLERY = 102;
   public static final int ID_MENU_BUTTON_ROTATE_NONE = 301;
   public static final int ID_MENU_BUTTON_ROTATE_90 = 302;
   public static final int ID_MENU_BUTTON_ROTATE_180 = 303;
   public static final int ID_MENU_BUTTON_ROTATE_270 = 304;

   public static final int ID_MENU_BUTTON_SAVE = 201;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      File wallpaperDirectory = new File(folderApplication);
      if(!wallpaperDirectory.exists()) { wallpaperDirectory.mkdirs(); }

      imagesEffectSave = new ArrayList<Bitmap>();
      imagesEffectSaveIndex = new ArrayList<Integer>();
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.filters_fragment, parent, false);
      setVariables(v);

      return v;
   }

   public void onClick(View v) {
      final int ID = v.getId();
      switch (ID) {
         case R.id.image_view:
            getActivity().openContextMenu(imageView);
            break;

         case R.id.button_effect_real:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageBitmap;
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_tint:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyTintEffect(imageBitmap, 100);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();

            break;

         case R.id.button_effect_sheding_yellow:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyShadingFilter(imageBitmap, Color.YELLOW);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_grayscale:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyGreyscaleEffect(imageBitmap);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_sheding_cyan:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyShadingFilter(imageBitmap, Color.CYAN);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_sepia_green:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applySepiaToningEffect(imageBitmap, 10, 0.88, 2.45, 1.43);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_sepia_blue:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applySepiaToningEffect(imageBitmap, 10, 1.2, 0.87, 2.1);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_sepia:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applySepiaToningEffect(imageBitmap, 10, 1.5, 0.6, 0.12);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_round_corner:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyRoundCornerEffect(imageBitmap, 20);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_invert:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyInvertEffect(imageBitmap);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_flea:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyFleaEffect(imageBitmap);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_engrave:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyEngraveEffect(imageBitmap);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_emboss:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyEmbossEffect(imageBitmap);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_contrast:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyContrastEffect(imageBitmap, 70);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_color_green:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyColorFilterEffect(imageBitmap, 0, 255, 0);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_brightness:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyBrightnessEffect(imageBitmap, 80);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();
            break;

         case R.id.button_effect_black:
            new AsyncTasks() {
               protected void doingInBackground() {
                  try {
                     imageBitmap_ = imagesEffectSave.get(imagesEffectSaveIndex.indexOf(ID));
                  } catch ( IndexOutOfBoundsException e ) {
                     imageBitmap_ = imageFilters.applyBlackFilter(imageBitmap);
                     imagesEffectSaveIndex.add(ID);
                     imagesEffectSave.add(imageBitmap_);
                  } finally {
                     updateImageToView(imageBitmap_);
                  }
               }
            }.execute();

            break;


         case R.id.button_top_effect_border:
            isBorder = !isBorder;
            updateActiveTopNav();
            new AsyncTasks() {
               protected void doingInBackground() {
                  updateImageToView(imageBitmap_);
               }
            }.execute();
            break;

         case R.id.button_top_effect_gaussian:
            isGaussian = !isGaussian;
            updateActiveTopNav();
            new AsyncTasks() {
               protected void doingInBackground() {
                  updateImageToView(imageBitmap_);
               }
            }.execute();
            break;

         case R.id.button_top_effect_light:
            isLight = !isLight;
            updateActiveTopNav();
            new AsyncTasks() {
               protected void doingInBackground() {
                  updateImageToView(imageBitmap_);
               }
            }.execute();
            break;

         case R.id.button_top_effect_rotate:
            getActivity().openContextMenu(getView().findViewById(R.id.layout_top_effect_rotate));
            break;

         case R.id.button_top_action_done:
            saveImage();
            break;

         case R.id.button_top_action_add_photo:
            getActivity().openContextMenu(imageView);
            break;
      }
   }

   public boolean onLongClick(View v) {
      switch (v.getId()) {

         case R.id.image_view:
            getActivity().openContextMenu(getView().findViewById(R.id.layout_buttons_effects));
            break;

      }
      return true;
   }

   public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
      super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
      imagesEffectSave = new ArrayList<Bitmap>();
      imagesEffectSaveIndex = new ArrayList<Integer>();
      switch(requestCode) {
         case 0:
            if (resultCode == Activity.RESULT_OK) {
               Bundle extras = imageReturnedIntent.getExtras();
               imageBitmap_ = imageBitmap = reSizeImage((Bitmap) extras.get("data"));
               updateImageToView(imageBitmap_);
               setImage();
            }

            break;

         case 1:
            if(resultCode == Activity.RESULT_OK){
               Uri selectedImage = imageReturnedIntent.getData();
               String[] filePathColumn = {MediaStore.Images.Media.DATA};
               Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
               cursor.moveToFirst();
               int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
               String filePath = cursor.getString(columnIndex);
               cursor.close();

               imageBitmap_ = imageBitmap = reSizeImage(BitmapFactory.decodeFile(filePath));
               updateImageToView(imageBitmap_);
               setImage();
            }
            break;
      }
   }

   @Override
   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
      switch (v.getId()) {
         case R.id.image_view :
            // .setIcon(R.drawable.menu_quit_icon)
            menu.add(ID_MENU_GET_IMAGE, ID_MENU_BUTTON_CAMERA, 1, "Сделать фото");
            menu.add(ID_MENU_GET_IMAGE, ID_MENU_BUTTON_GALLERY, 2, "Открыть с галерея");
            break;

         case R.id.layout_buttons_effects :
            menu.add(ID_MENU_SAVE_IMAGE, ID_MENU_BUTTON_SAVE, 1, "Сохранить");
            break;

         case R.id.layout_top_effect_rotate :
            menu.add(ID_MENU_ROTATE, ID_MENU_BUTTON_ROTATE_NONE, 1, "Не поворачивать").setIcon(R.drawable.ic_action_screen_rotation_white);
            menu.add(ID_MENU_ROTATE, ID_MENU_BUTTON_ROTATE_90, 2, "На право").setIcon(R.drawable.ic_action_rotate_right);
            menu.add(ID_MENU_ROTATE, ID_MENU_BUTTON_ROTATE_270, 3, "На лево").setIcon(R.drawable.ic_action_rotate_right);
            menu.add(ID_MENU_ROTATE, ID_MENU_BUTTON_ROTATE_180, 4, "Перевернуть").setIcon(R.drawable.ic_action_rotate_bottom);
            break;

      }
   }

   @Override
   public boolean onContextItemSelected(MenuItem item) {
      switch (item.getItemId()) {
         case ID_MENU_BUTTON_CAMERA:
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 0);
            break;

         case ID_MENU_BUTTON_GALLERY:
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);//one can be replced with any action code
            break;

         case ID_MENU_BUTTON_SAVE:
            saveImage();
            break;

         case ID_MENU_BUTTON_ROTATE_NONE:
            isRotate = 0;
            updateActiveTopNav();
            new AsyncTasks() {
               protected void doingInBackground() {
                  updateImageToView(imageBitmap_);
               }
            }.execute();
            break;

         case ID_MENU_BUTTON_ROTATE_90:
            isRotate = 90;
            updateActiveTopNav();
            new AsyncTasks() {
               protected void doingInBackground() {
                  updateImageToView(imageBitmap_);
               }
            }.execute();
            break;

         case ID_MENU_BUTTON_ROTATE_180:
            isRotate = 180;
            updateActiveTopNav();
            new AsyncTasks() {
               protected void doingInBackground() {
                  updateImageToView(imageBitmap_);
               }
            }.execute();
            break;

         case ID_MENU_BUTTON_ROTATE_270:
            isRotate = 270;
            updateActiveTopNav();
            new AsyncTasks() {
               protected void doingInBackground() {
                  updateImageToView(imageBitmap_);
               }
            }.execute();
            break;

         default:
            return super.onContextItemSelected(item);
      }

      return true;
   }

   @Override
   public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      super.onCreateOptionsMenu(menu, inflater);
   }

   private void updateActiveTopNav() {
      Resources r = getResources();

      getView().findViewById(R.id.layout_top_effect_border).setBackgroundColor(
              isBorder ?
                      r.getColor(R.color.background_top_button_active) :
                      r.getColor(R.color.background_top_button)
      );
      ((ImageView) getView().findViewById(R.id.button_top_effect_border)).setImageBitmap(
              isBorder ?
                      BitmapFactory.decodeResource(r, R.drawable.ic_action_border_active) :
                      BitmapFactory.decodeResource(r, R.drawable.ic_action_border)
      );

      getView().findViewById(R.id.layout_top_effect_gaussian).setBackgroundColor(
              isGaussian ?
                      r.getColor(R.color.background_top_button_active) :
                      r.getColor(R.color.background_top_button)
      );
      ((ImageView) getView().findViewById(R.id.button_top_effect_gaussian)).setImageBitmap(
              isGaussian ?
                      BitmapFactory.decodeResource(r, R.drawable.ic_action_gaussian_action) :
                      BitmapFactory.decodeResource(r, R.drawable.ic_action_gaussian)
      );

      getView().findViewById(R.id.layout_top_effect_light).setBackgroundColor(
              isLight ?
                      r.getColor(R.color.background_top_button_active) :
                      r.getColor(R.color.background_top_button)
      );
      ((ImageView) getView().findViewById(R.id.button_top_effect_light)).setImageBitmap(
              isLight ?
                      BitmapFactory.decodeResource(r, R.drawable.ic_action_brightness_high) :
                      BitmapFactory.decodeResource(r, R.drawable.ic_action_bightness_low)
      );

      getView().findViewById(R.id.layout_top_effect_rotate).setBackgroundColor(
              isRotate != 0 ?
                      r.getColor(R.color.background_top_button_active) :
                      r.getColor(R.color.background_top_button)
      );
   }

   public static FiltersEditorFragment newInstance() {
      Bundle args = new Bundle();
      //args.putSerializable(EXTRA_CRIME_ID, crimeId);
      FiltersEditorFragment fragment = new FiltersEditorFragment();
      fragment.setArguments(args);
      return fragment;
   }

   private void setVariables(View view) {

      imageView = (ImageView) view.findViewById(R.id.image_view);
      imageView.setOnClickListener(this);
      imageView.setOnLongClickListener(this);

      view.findViewById(R.id.button_effect_real).setOnClickListener(this);
      view.findViewById(R.id.button_effect_tint).setOnClickListener(this);
      view.findViewById(R.id.button_effect_sheding_yellow).setOnClickListener(this);
      view.findViewById(R.id.button_effect_grayscale).setOnClickListener(this);
      view.findViewById(R.id.button_effect_sheding_cyan).setOnClickListener(this);
      view.findViewById(R.id.button_effect_sepia_green).setOnClickListener(this);
      view.findViewById(R.id.button_effect_sepia_blue).setOnClickListener(this);
      view.findViewById(R.id.button_effect_sepia).setOnClickListener(this);
      view.findViewById(R.id.button_effect_round_corner).setOnClickListener(this);
      view.findViewById(R.id.button_effect_invert).setOnClickListener(this);
      view.findViewById(R.id.button_effect_flea).setOnClickListener(this);
      view.findViewById(R.id.button_effect_engrave).setOnClickListener(this);
      view.findViewById(R.id.button_effect_emboss).setOnClickListener(this);
      view.findViewById(R.id.button_effect_contrast).setOnClickListener(this);
      view.findViewById(R.id.button_effect_color_green).setOnClickListener(this);
      view.findViewById(R.id.button_effect_brightness).setOnClickListener(this);
      view.findViewById(R.id.button_effect_black).setOnClickListener(this);

      view.findViewById(R.id.button_top_effect_border).setOnClickListener(this);
      view.findViewById(R.id.button_top_effect_gaussian).setOnClickListener(this);
      view.findViewById(R.id.button_top_effect_light).setOnClickListener(this);
      view.findViewById(R.id.button_top_effect_rotate).setOnClickListener(this);
      view.findViewById(R.id.button_top_action_done).setOnClickListener(this);
      view.findViewById(R.id.button_top_action_add_photo).setOnClickListener(this);

      view.findViewById(R.id.image_view).setOnCreateContextMenuListener(this);
      view.findViewById(R.id.layout_buttons_effects).setOnCreateContextMenuListener(this);
      view.findViewById(R.id.layout_top_effect_rotate).setOnCreateContextMenuListener(this);
   }

   private void updateImageToView(Bitmap pImageBitmap) {
      if(pImageBitmap != null) {
         if(isGaussian) {
            double[][] GaussianBlurConfig = new double[][] {
                    { 2, 2, 2 },
                    { 2, 0, 2 },
                    { 2, 2, 2 }
            };
            pImageBitmap = imageFilters.applyGaussianBlurEffect(pImageBitmap, GaussianBlurConfig);
         }

         if(isLight) {
            pImageBitmap = imageFilters.applyBrightnessEffect(pImageBitmap, 70);
         }

         if(isRotate != 0) {
            pImageBitmap = imageFilters.applyRotateEffect(pImageBitmap, isRotate);
         }

         if(isBorder) {
            pImageBitmap = imageFilters.applySimpleBorder(pImageBitmap, getActivity(), 0.1f);
         }
      }
      imageBitmap_active = pImageBitmap;
   }

   private void setImage() {
      if(imageBitmap_active !=  null) {
         isAddImage = true;
         imageView.setImageBitmap(imageBitmap_active);
      }
   }

   private void saveImage() {
      if(isAddImage) {
         new AsyncTasks("Сохранение фотографии") {
            protected void doingInBackground() {
               try {
                  String name = "file_" + (new DateFormat().format("s_m_k_dd_MM_yyyy", new Date()).toString()) + ".png";
                  File filename = new File(folderApplication, name);
                  FileOutputStream out = new FileOutputStream(filename);
                  Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                  bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                  out.close();
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
            protected void doingAfter() {
               Toast.makeText(getActivity(), "Фотография сохранена.", Toast.LENGTH_SHORT).show();
            }
         }.execute();
      }
   }

   private Bitmap reSizeImage(Bitmap image) {
      int height = image.getHeight();
      int width = image.getWidth();
      if(height > MAX_HEIGHT_IMAGE || width > MAX_WIDTH_IMAGE) {
         float koef = (float) height / (float) width;
         int newWidth = 0;
         int newHeight = 0;
         if(height > width) {
            newHeight = MAX_HEIGHT_IMAGE;
            newWidth = (int) (newHeight / koef);
         } else {
            newWidth = MAX_WIDTH_IMAGE;
            newHeight = (int) (newWidth * koef);
         }
         Log.d(TAG, "height=" + height + "; width=" + width + "; koef=" + koef);
         Log.d(TAG, "newWidth="+newWidth+"; newHeight="+newHeight);
         return Bitmap.createScaledBitmap(image, newWidth, newHeight, true);
      } else {
         return image;
      }
   }

   private abstract class AsyncTasks extends AsyncTask<Void, Void, String> {
      private ProgressDialog dialog;
      protected abstract void doingInBackground();
      protected void doingAfter() {};
      private String textShow = "";

      public AsyncTasks() {
         textShow = getString(R.string.loader_text_wait);
      }

      public AsyncTasks(String pText) {
         textShow = pText;
      }

      @Override
      protected void onPreExecute() {
         dialog = ProgressDialog.show(getActivity(), "", textShow, true);
      }

      @Override
      protected String doInBackground(Void... params) {
         doingInBackground();
         return "";
      }

      @Override
      protected void onPostExecute(String result) {
         dialog.dismiss();
         setImage();
         doingAfter();
      }
   }
}
