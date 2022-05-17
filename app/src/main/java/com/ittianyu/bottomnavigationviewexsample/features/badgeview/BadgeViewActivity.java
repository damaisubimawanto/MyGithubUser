package com.ittianyu.bottomnavigationviewexsample.features.badgeview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.ActivityBadgeViewBinding;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class BadgeViewActivity extends AppCompatActivity {
    private ActivityBadgeViewBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_badge_view);

        initView();
    }

    private void initView() {
        // disable all animations
        bind.bnve.enableAnimation(false);
        bind.bnve.enableShiftingMode(false);
        bind.bnve.enableItemShiftingMode(false);


        // add badge
        addBadgeAt(2, 1);
    }

    private Badge addBadgeAt(int position, int number) {
        // add badge
        View viewItem = bind.bnve.getBottomNavigationItemView(position);
        if (viewItem != null) {
            new QBadgeView(this)
                    .setBadgeNumber(number)
                    .setGravityOffset(12, 2, true)
                    .bindTarget(viewItem)
                    .setOnDragStateChangedListener((dragState, badge, targetView) -> {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
                    });
        }
        return null;
    }

}
