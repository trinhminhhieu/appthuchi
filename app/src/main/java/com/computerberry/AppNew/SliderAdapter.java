package com.computerberry.AppNew;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slideImages = {
//            R.mipmap.ic_whatshot_black_24dp,
            R.mipmap.ic_check_circle_black_24dp,
            R.mipmap.ic_money_off_black_24dp
    };
    public int[] slideBackgrounds = {
//            R.drawable.circlebackgroundgreen,
            R.drawable.circlebackgroundyellow,
            R.drawable.circlebackgroundpurple,
    };
    public String[] slideHeadings = {
//            "Tiết kệm",
            "Ngày dưới ngân sách",
            "Tổng số tiền đã tiết kiệm"
    };
    public String[] slideDescs = {
//            String.format("Bạn hiện có %d ngày liên tục!\n Hãy tiếp tục! ", 0),
            String.format("Bạn đã thiếu ngân sách tổng cộng là %d/%d ngày!\n\nTốt thôi! ", HomeActivity.daysUnderBudget, HomeActivity.totalDays),
            String.format("Bạn hiện đã tiết kiệm được tổng cộng $%s bằng DigiMoney!\nNói về tiết kiệm bạn đã làm rất tốt!", String.format("%.2f",HomeActivity.totalAmountSaved))
    };

    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideImageView);
        TextView headingTextView = (TextView) view.findViewById(R.id.headingTextView);
        TextView bodyTextView = (TextView) view.findViewById(R.id.bodyTextView);

        slideImageView.setImageResource(slideImages[position]);
        slideImageView.setBackgroundResource(slideBackgrounds[position]);
        headingTextView.setText(slideHeadings[position]);
        bodyTextView.setText(slideDescs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
