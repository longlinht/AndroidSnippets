package io.github.longlinht.library.permission;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.longlinht.library.R;

public class PermissionDialog extends Dialog {

    private boolean cancelable;
    private View view;

    private PermissionDialog(Builder builder) {
        super(builder.context);
        cancelable = builder.cancelable;
        view = builder.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);

        setCancelable(cancelable);


        Window window = getWindow();
        WindowManager.LayoutParams lp;
        if (window != null) {
            lp = window.getAttributes();
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }
    }

    @Override
    public void show() {
        setCancelable(cancelable);
        super.show();
    }

    public static final class Builder {

        private boolean cancelable;
        private Context context;
        private View view;
        private HashMap<Integer, View.OnClickListener> eventViews = new HashMap<>();
        private HashMap<Integer, String> textViews = new HashMap<>();
        private ArrayList<Integer> cancelViews = new ArrayList<>();

        public Builder(Context context) {
            this.context = context;
            this.eventViews.clear();
            this.cancelViews.clear();
        }

        public Builder view(@LayoutRes int res) {
            this.view = LayoutInflater.from(context).inflate(res, null);
            return this;
        }

        public Builder setCancelable(boolean cancel) {
            this.cancelable = cancel;
            return this;
        }

        Builder addDialogEventView(@IdRes int id, View.OnClickListener listener) {
            this.eventViews.put(id, listener);
            return this;
        }

        Builder findTextViewAndSetText(@IdRes int id, String text) {
            this.textViews.put(id, text);
            return this;
        }

        Builder findViewAndSetVisibility(@IdRes int id, int visibility) {
            this.view.findViewById(id).setVisibility(visibility);
            return this;
        }

        public Builder setRecycler(RecyclerView.Adapter adapter) {
            RecyclerView recyclerView = this.view.findViewById(R.id.recycler);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
            return this;
        }

        public PermissionDialog build() {
            PermissionDialog dialog = new PermissionDialog(this);
            if (view != null) {
                //cancel event
                addCancelEvent(dialog);
                //click event
                addClickEvent(dialog);
                //set text
                setTextViews();
            }
            return dialog;
        }

        public void show() {
            final PermissionDialog dialog = build();
            dialog.show();
        }

        private void setTextViews() {
            if (!textViews.isEmpty() && view != null) {
                for (Map.Entry<Integer, String> entry : textViews.entrySet()) {
                    @IdRes int viewId = entry.getKey();
                    View eventView = view.findViewById(viewId);
                    final String text = entry.getValue();
                    if (eventView instanceof TextView) {
                        eventView.setVisibility(View.VISIBLE);
                        ((TextView) eventView).setText(text);
                    }
                }
            }
        }

        private void addClickEvent(final PermissionDialog dialog) {
            if (!eventViews.isEmpty() && view != null) {
                for (Map.Entry<Integer, View.OnClickListener> entry : eventViews.entrySet()) {
                    @IdRes int viewId = entry.getKey();
                    View eventView = view.findViewById(viewId);
                    final View.OnClickListener listener = entry.getValue();
                    if (eventView != null && listener != null) {
                        eventView.setVisibility(View.VISIBLE);
                        eventView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onClick(v);
                                if (dialog != null && dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                }
            }
        }

        private void addCancelEvent(final PermissionDialog dialog) {
            View.OnClickListener cancelListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            };

            if (!cancelViews.isEmpty() && view != null) {
                for (@IdRes int viewId : cancelViews) {
                    View cancelView = view.findViewById(viewId);
                    if (cancelView != null) {
                        cancelView.setOnClickListener(cancelListener);
                    }
                }
            }
        }
    }
}