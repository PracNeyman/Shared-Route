package com.example.administrator.sharedroute.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.administrator.sharedroute.R;
import com.example.administrator.sharedroute.entity.DialogMenuItem;
import com.example.administrator.sharedroute.widget.BlurBehind;
import com.example.administrator.sharedroute.widget.NormalListDialog;

import java.util.ArrayList;

public class BlurredActivity extends Activity {

	public static ArrayList<DialogMenuItem> testItems = new ArrayList<>();
	public static String fromActivity;

//	private BaseAnimatorSet base_in;
//	private BaseAnimatorSet base_out;
//	private Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blurred);
		BlurBehind.getInstance()
                .withAlpha(90)
                .withFilterColor(Color.parseColor("#7fffffff"))
                .setBackground(this);

		if(getIntent().getStringExtra("Activity").equals("Main")){
			fromActivity = "Main";
		}else{
			fromActivity = "SearchNeeds";
		}
		if(getIntent().getStringExtra("select").equals("release")) {
			testItems.add(new DialogMenuItem("快递名称:", R.mipmap.ic_express));
			testItems.add(new DialogMenuItem("发布时间:", R.mipmap.ic_get_time));
		}else{
			testItems.add(new DialogMenuItem("发布人:", R.mipmap.ic_user1));
		}
		testItems.add(new DialogMenuItem("类型:", R.mipmap.ic_type));
		testItems.add(new DialogMenuItem("取货码:", R.mipmap.ic_code));
		testItems.add(new DialogMenuItem("金额:", R.mipmap.ic_money));
		testItems.add(new DialogMenuItem("状态:", R.mipmap.ic_status));

//		try {
//			((BlurredActivity) context).setBaseIn((BaseAnimatorSet) ZoomInEnter.class.newInstance());
//			((BlurredActivity) context).setBaseOut((BaseAnimatorSet) ZoomOutExit.class.newInstance());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		final NormalListDialog dialog = new NormalListDialog(BlurredActivity.this, testItems);
		dialog.title(getIntent().getStringExtra("title_name"))//
				.titleTextSize_SP(22)//
				.titleBgColor(Color.parseColor("#409ED7"))//
				.itemPressColor(Color.parseColor("#85D3EF"))//
				.itemTextColor(Color.parseColor("#303030"))//
				.itemTextSize(20)//
				.cornerRadius(0)//
				.widthScale(0.8f)//
				.show(R.style.myDialogAnim);
		testItems.clear();
	}

//
//	public void setBaseIn(BaseAnimatorSet base_in) {
//		this.base_in = base_in;
//	}
//
//	public void setBaseOut(BaseAnimatorSet base_out) {
//		this.base_out = base_out;
//	}
}