package ch.neh.android.swipepager;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

public abstract class WizardStepFragment extends RelativePagerFragment {

	protected final static int STATUS_ENABLED = 0x01;
	protected final static int STATUS_DISABLED = 0x02;
	protected final static int STATUS_GONE = 0x03;

	protected WizardStepFragment(@LayoutRes int layout) {
		super(layout);
	}

	/**
	 * Indicates whether a page has a next page.
	 * @return one of: STATUS_ENABLED, STATUS_DISABLED or STATUS_GONE.
	 */
	protected abstract int hasNextButton();

	/**
	 * Indicates whether a page has a previous page.
	 * @return one of: STATUS_ENABLED, STATUS_DISABLED or STATUS_GONE.
	 */
	protected abstract int hasPrevButton();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public boolean hasNext() {
		return hasNextButton() == STATUS_ENABLED;
	}

	@Override
	public boolean hasPrev() {
		return hasPrevButton() == STATUS_ENABLED;
	}

	public boolean isLast() {
		return false;
	}

	public int getNextButtonLabel() {
		return R.string.next;
	}

	public int getPrevButtonLabel() {
		return R.string.previous;
	}
}
