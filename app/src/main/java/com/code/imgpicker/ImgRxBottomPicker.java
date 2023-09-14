package com.code.imgpicker;

import android.app.Activity;
import android.net.Uri;

import java.util.List;

import io.reactivex.Single;

public class ImgRxBottomPicker extends ImgBottomSheetDialogFragment {

    public static Builder with(Activity fragmentActivity) {
        return new Builder(fragmentActivity);
    }


    public static class Builder extends BaseBuilder<Builder> {

        private Builder(Activity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public Builder setOnImageSelectedListener(OnImageSelectedListener onImageSelectedListener) {
            throw new RuntimeException("You have to use show() method. Or read usage document");
        }

        @Override
        public Builder setOnMultiImageSelectedListener(OnMultiImageSelectedListener onMultiImageSelectedListener) {
            throw new RuntimeException("You have to use showMultiImage() method. Or read usage document");
        }

        public Single<Uri> show() {
            return Single.create(emitter -> {
                onImageSelectedListener = emitter::onSuccess;
                onErrorListener = message -> emitter.onError(new Exception(message));
                create().show(fragmentActivity.getSupportFragmentManager());
            });
        }

        public Single<List<Uri>> showMultiImage() {
            return Single.create(emitter -> {
                onMultiImageSelectedListener = emitter::onSuccess;
                onErrorListener = message -> emitter.onError(new Exception(message));
                create().show(fragmentActivity.getSupportFragmentManager());
            });
        }
    }

}
