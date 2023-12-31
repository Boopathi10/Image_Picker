package com.code.imgpicker;


import androidx.fragment.app.FragmentActivity;

public class ImgBottomPicker extends ImgBottomSheetDialogFragment {

    public static Builder with(FragmentActivity fragmentActivity) {
        return new Builder(fragmentActivity);
    }

    public static class Builder extends BaseBuilder<Builder> {

        private Builder(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public void show(OnImageSelectedListener onImageSelectedListener) {
            this.onImageSelectedListener = onImageSelectedListener;
            create().show(fragmentActivity.getSupportFragmentManager());
        }

        public void showMultiImage(OnMultiImageSelectedListener onMultiImageSelectedListener) {
            this.onMultiImageSelectedListener = onMultiImageSelectedListener;
            create().show(fragmentActivity.getSupportFragmentManager());
        }
    }


}
