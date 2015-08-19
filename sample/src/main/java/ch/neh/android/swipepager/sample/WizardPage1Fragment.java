package ch.neh.android.swipepager.sample;

import ch.neh.android.swipepager.FragmentStateManager;
import ch.neh.android.swipepager.RelativePagerFragment;
import ch.neh.android.swipepager.WizardStepFragment;

public class WizardPage1Fragment extends WizardStepFragment {

	public WizardPage1Fragment() {
		super(R.layout.fragment_wizard_1);
	}

	@Override
	protected int hasNextButton() {
		return STATUS_ENABLED;
	}

	@Override
	protected int hasPrevButton() {
		return STATUS_GONE;
	}

	@Override
	public RelativePagerFragment getNext(FragmentStateManager fsm) {
		return fsm.getFragment(WizardPage2Fragment.class);
	}

	@Override
	public RelativePagerFragment getPrev(FragmentStateManager fsm) {
		return null; // first step has no previous page.
	}
}
