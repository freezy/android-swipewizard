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

	protected final static String DATA_NEXT_STATUS = "ch.neh.android.DATA_NEXT_STATUS";
	protected final static String DATA_HAS_NEXT = "ch.neh.android.app.DATA_HAS_NEXT";

	private final @LayoutRes int mLayout;

	private OnStatusChangeListener mStatusChangeListener;
	private FragmentStateManager mFragmentStateManager;

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
	 *
	 * @param fsm Use to instantiate Fragment
	 * @return Next page or {@code null} if next is disabled (or gone).
	 */
	public abstract RelativePagerFragment getNext(FragmentStateManager fsm);

	/**
	 * Returns the previous page.
	 *
	 * @param fsm Use to instantiate Fragment
	 * @return Previous page {@code null} if next is disabled (or gone).
	 */
	public abstract RelativePagerFragment getPrev(FragmentStateManager fsm);

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
	 * Gives access to the status change listener, which allows
	 * to paginate programmatically through the steps.
	 *
	 * @return Status change listener
	 */
	protected OnStatusChangeListener getStatusChangeListener() {
		return mStatusChangeListener;
	}

	/**
	 * Gives access to the frame state manager.
	 *
	 * @return FragmentStateManager
	 */
	protected FragmentStateManager getFragmentStateManager() {
		return mFragmentStateManager;
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