package com.erised.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.erised.R;

/**
 * Created by Rahul on 26/9/15.
 */

public class LoadingDialog {

	private static LoadingDialog instance;
	private static Dialog dialog;
	
	public static LoadingDialog getInstance() {
		
		if (instance == null) {
			
			instance =  new LoadingDialog();
		}
		
		return instance;
	}
	
	public void showDialog(Context context) {

		dialog = new Dialog(context);
		
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_loading);
		dialog.setCancelable(false);
		
		dialog.show();
	}
	
	public void setDialogCanceleable(boolean flag){
		dialog.setCancelable(flag);
	}
	public void dismissDialog() {
		
		dialog.dismiss();
	}
	
	public boolean isShowing() {
		
		if (dialog != null) {
			
			return dialog.isShowing();
		}
		return false;
	}
}