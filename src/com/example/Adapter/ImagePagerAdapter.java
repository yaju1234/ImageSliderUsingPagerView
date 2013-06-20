package com.example.Adapter;

import com.example.View.R;
import com.example.View.Slideshow;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ImagePagerAdapter extends PagerAdapter{


	private String[] images;
	private LayoutInflater inflater;
	private Activity mActivity;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;

	public ImagePagerAdapter(String[] images, Activity activity) {
		this.images = images;
		mActivity = activity;
		inflater = mActivity.getLayoutInflater();
		 imageLoader.init(ImageLoaderConfiguration.createDefault(mActivity.getApplicationContext()));
		 options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.stub)
			.cacheOnDisc()
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
			.build();
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public void finishUpdate(View container) {
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object instantiateItem(View view, int position) {
		final View imageLayout = inflater.inflate(R.layout.pager_row, null);
		final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.pagerimage);
		final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);

		imageLoader.displayImage(images[position], imageView, options, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted() {
				spinner.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(FailReason failReason) {
				String message = null;
				switch (failReason) {
					case IO_ERROR:
						message = "Input/Output error";
						break;
					case OUT_OF_MEMORY:
						message = "Out Of Memory error";
						break;
					case UNKNOWN:
						message = "Unknown error";
						break;
				}
				Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();

				spinner.setVisibility(View.GONE);
				imageView.setImageResource(android.R.drawable.ic_delete);
			}

			@Override
			public void onLoadingComplete(Bitmap loadedImage) {
				spinner.setVisibility(View.GONE);
				Animation anim = AnimationUtils.loadAnimation(mActivity, R.anim.fade_in);
				imageView.setAnimation(anim);
				anim.start();
			}

			@Override
			public void onLoadingCancelled() {
				// Do nothing
			}
		});

		((ViewPager) view).addView(imageLayout, 0);
		return imageLayout;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View container) {
	}


}
