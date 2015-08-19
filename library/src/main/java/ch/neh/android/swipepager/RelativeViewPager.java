package ch.neh.android.swipepager;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * A view pager that relies on the currently displayed fragment to deliver the
 * next or previous page, as opposed to a global list of fragments.
 *
 * A bit inspired from <a href="http://stackoverflow.com/questions/7951730">Stackoverflow.</a>
 *
 * @author freezy <freezy@kodi.tv>
 */
public class RelativeViewPager extends ViewPager {

	private RelativePagerAdapter mAdapter;

	private boolean mEnabledPrev = true;
	private boolean mEnabledNext = true;
	private float mXPos = -1;

	private int mCurrPosition = 0;
	private OnRelativePageChangeListener mPageChangeListener;

	public RelativeViewPager(Context context) {
		super(context);
	}

	public RelativeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void initRelativeViewPager() {

		addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				mCurrPosition = position;
			}

			@Override
			public void onPageScrollStateChanged(final int state) {

				if (state == ViewPager.SCROLL_STATE_IDLE) {
					if (mAdapter == null) {
						return;
					}
					if (mCurrPosition == RelativePagerAdapter.PAGE_POSITION_LEFT) {
						mAdapter.move(1);
					} else if (mCurrPosition == RelativePagerAdapter.PAGE_POSITION_RIGHT) {
						mAdapter.move(-1);
					}
					onPageActive();
				}
			}
		});
		onPageActive();
	}

	private void onPageActive() {
		setCurrentItem(RelativePagerAdapter.PAGE_POSITION_CENTER, false);

		setPagingNextEnabled(mAdapter.getCurrentFragment().hasNext());
		setPagingPrevEnabled(mAdapter.getCurrentFragment().hasPrev());

		mAdapter.getCurrentFragment().onPageActive();
		if (mPageChangeListener != null) {
			mPageChangeListener.onPageSelected(mAdapter.getCurrentFragment());
		}
	}

	@Override
	public final void setCurrentItem(final int item) {
		if (item != RelativePagerAdapter.PAGE_POSITION_CENTER) {
			throw new RuntimeException("Cannot change page index unless its 1.");
		}
		super.setCurrentItem(item);
	}

	@Override
	public void setAdapter(final PagerAdapter adapter) {
		if (adapter instanceof RelativePagerAdapter) {
			super.setAdapter(adapter);

			mAdapter = (RelativePagerAdapter)adapter;
			mAdapter.setPager(this);

			initRelativeViewPager();

		} else {
			throw new IllegalArgumentException("Adapter must be an instance of RelativePagerAdapter.");
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!mEnabledPrev && !mEnabledNext) {
			return true;
		}
		if (mEnabledPrev && mEnabledNext) {
			return super.onTouchEvent(event);
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			mXPos = event.getX();
		}
		if (mXPos < 0) {
			return super.onTouchEvent(event);
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (!mEnabledPrev && mXPos < event.getX()) {
				return true;
			}
			if (!mEnabledNext && mXPos > event.getX()) {
				return true;
			}
		}
		//Log.d("pager", "Event: " + event.getAction() + " (" + event.getX() + ")");
		return super.onTouchEvent(event);
	}

	/**
	 * Enables or disables paging back to the previous page.
	 * @param enabled If true, enable, otherwise disable.
	 */
	public void setPagingPrevEnabled(boolean enabled) {
		mEnabledPrev = enabled;
	}

	/**
	 * Enables or disables paging forth to the next page.
	 * @param enabled If true, enable, otherwise disable.
	 */
	public void setPagingNextEnabled(boolean enabled) {
		mEnabledNext = enabled;
	}

	public void setOnRelativePageChangeListener(OnRelativePageChangeListener listener) {
		mPageChangeListener = listener;
	}

	public void onStatusUpdate() {
		if (mPageChangeListener != null) {
			mPageChangeListener.onPageUpdated(mAdapter.getCurrentFragment());
		}
	}

	public interface OnRelativePageChangeListener {
		void onPageSelected(RelativePagerFragment fragment);
		void onPageUpdated(RelativePagerFragment fragment);
	}
}