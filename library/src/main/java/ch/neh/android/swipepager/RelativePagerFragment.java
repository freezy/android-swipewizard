package ch.neh.android.swipepager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * An abstract Fragment that delivers the next or previous page when used in
 * a {@link RelativeViewPager}.
 *
 * @author freezy <freezy@kodi.tv>
 */
public abstract class RelativePagerFragment extends Fragment {

	protected final static String DATA_NEXT_STATUS = "com.kiwi.merchant.app.DATA_NEXT_STATUS";
	protected final static String DATA_HAS_NEXT = "com.kiwi.merchant.app.DATA_HAS_NEXT";

	private final @LayoutRes
	int mLayout;

	protected OnStatusChangeListener mStatusChangeListener;
	protected FragmentStateManager mFragmentStateManager;

	protected RelativePagerFragment(@LayoutRes int layout) {
		mLayout = layout;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(mLayout, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		mFragmentStateManager = FragmentStateManager.get(getActivity());
		mStatusChangeListener = mFragmentStateManager.getOnStatusChangeListener();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mFragmentStateManager.removeFragment(this.getClass());
	}

	/**
	 * Indicates whether a page has a next page.
	 */
	public abstract boolean hasNext();

	/**
	 * Indicates whether a page has a previous page.
	 */
	public abstract boolean hasPrev();

	/**
	 * Returns the next page.
	 * @return Next page
	 * @param fsm Use to instantiate Fragment
	 */
	public RelativePagerFragment getNext(FragmentStateManager fsm) {
		return null;
	}

	/**
	 * Returns the previous page.
	 * @return Previous page
	 * @param fsm Use to instantiate Fragment
	 */
	public RelativePagerFragment getPrev(FragmentStateManager fsm) {
		return null;
	}

	/**
	 * Executed when the fragment is settled.
	 */
	public void onPageActive() {
	}

	/**
	 * Executed on next button click.
	 * @return If true, nothing else will be executed, otherwise paging is applied.
	 */
	public boolean onNextClicked() {
		return false;
	}

	/**
	 * Executed on previous button click.
	 * @return If true, nothing else will be executed, otherwise paging is applied.
	 */
	public boolean onPrevClicked() {
		return false;
	}

	/**
	 * Shortcut to {@link #getActivity().getApplicationContext()}.
	 * @return Application Context
	 */
	protected Context getApplicationContext() {
		return getActivity().getApplicationContext();
	}

	/**
	 * Allows to manually enforce the next or previous page.
	 */
	public interface OnStatusChangeListener {
		/**
		 * The next page is displayed.
		 */
		void onNextPage();

		/**
		 * The previous page is displayed.
		 */
		void onPrevPage();

		/**
		 * The same page is displayed but must be refreshed.
		 */
		void onStatusUpdated();
	}
}