package ch.neh.android.swipepager.sample;

import ch.neh.android.swipepager.FragmentStateManager;
import ch.neh.android.swipepager.RelativePagerFragment;
import ch.neh.android.swipepager.WizardStepFragment;

public class WizardPage2Fragment extends WizardStepFragment {

	public WizardPage2Fragment() {
		super(R.layout.fragment_wizard_2);
	}

	@Override
	protected int hasNextButton() {
		return STATUS_DISABLED;
	}

	@Override
	protected int hasPrevButton() {
		return STATUS_ENABLED;
	}

	@Override
	public RelativePagerFragment getNext(FragmentStateManager fsm) {
		return null;
	}

	@Override
	public RelativePagerFragment getPrev(FragmentStateManager fsm) {
		return fsm.getFragment(WizardPage1Fragment.class);
	}
}
