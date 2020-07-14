package com.tuandm.codeme.common;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.tuandm.codeme.R;

public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_loading);

        Window window = this.getWindow();
        if (window != null) {
            window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}
