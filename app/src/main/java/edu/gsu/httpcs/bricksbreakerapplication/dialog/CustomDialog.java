package edu.gsu.httpcs.bricksbreakerapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gsu.httpcs.bricksbreakerapplication.R;

/**
 * Created by thaku on 7/6/2017.
 */

public class CustomDialog extends Dialog {
        private String linkMessage=" ";
        private int radioId;
          ColorDrawable[] BackGroundColor = {
                  new ColorDrawable(Color.parseColor("#fa0707")),
                  new ColorDrawable(Color.parseColor("#3507fa")),
                  new ColorDrawable(Color.parseColor("#07faec")),
                  new ColorDrawable(Color.parseColor("#faec07")),
                  new ColorDrawable(Color.parseColor("#fa0707")),
                  new ColorDrawable(Color.parseColor("#4507fa")),
                  new ColorDrawable(Color.parseColor("#07faec")),
                  new ColorDrawable(Color.parseColor("#faec07")),
                  new ColorDrawable(Color.parseColor("#fa0707")),
                  new ColorDrawable(Color.parseColor("#3507fa")),
                  new ColorDrawable(Color.parseColor("#07faec")),
                  new ColorDrawable(Color.parseColor("#6aec07"))
    };
        TransitionDrawable transitiondrawable;
        private final ICustomDialogListener listener;
        public interface ICustomDialogListener{
            public void displayResult(String msg);
        }
        @BindView(R.id.quiz_bt_layoutut)
        RadioGroup rg;
       @BindView(R.id.relative_dialog)
        RelativeLayout rl;
        @OnClick(R.id.quiz_dialog_costumn_ok)
        public void checkedVal()
        {

            switch (radioId){
                case(R.id.quiz_radio1):
                   linkMessage="http://other.web.rh01.sycdn.kuwo.cn/resource/n3/77/1/1061700123.mp3";
                    break;
                case(R.id.quiz_radio2):
                    linkMessage="http://other.web.rh01.sycdn.kuwo.cn/resource/n3/77/1/1061700123.mp3";
                    break;
                case(R.id.quiz_radio3):
                    linkMessage="http://other.web.rh01.sycdn.kuwo.cn/resource/n3/77/1/1061700123.mp3";
                    break;
                case(R.id.quiz_radio4):
                    linkMessage="http://other.web.rh01.sycdn.kuwo.cn/resource/n3/77/1/1061700123.mp3";
                    break;
            }
            listener.displayResult(linkMessage);
            this.cancel();
        }
    public CustomDialog(@NonNull Context context, final ICustomDialogListener listener) {
        super(context, R.style.Dialog);

        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);
        transitiondrawable = new TransitionDrawable(BackGroundColor);
        rl.setBackground(transitiondrawable);
        transitiondrawable.startTransition(10000);
        transitiondrawable.reverseTransition(10000);
        this.listener=listener;
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                radioId=checkedId;
            }
        });
    }
}
