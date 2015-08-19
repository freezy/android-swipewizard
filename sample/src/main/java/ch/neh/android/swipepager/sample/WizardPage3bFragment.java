package ch.neh.android.swipepager.sample;

import ch.neh.android.swipepager.FragmentStateManager;
import ch.neh.android.swipepager.RelativePagerFragment;
import ch.neh.android.swipepager.WizardStepFragment;

public class WizardPage3bFragment extends WizardStepFragment {

	public WizardPage3bFragment() {
		super(R.layout.fragment_wizard_3b);
	}

	@Override
	protected int hasNextButton() {
		return STATUS_ENABLED;
	}

	@Override
	protected int hasPrevButton() {
		return STATUS_ENABLED;
	}

	@Override
	public RelativePagerFragment getNext(FragmentStateManager fsm) {
		return fsm.getFragment(WizardPage4Fragment.class);
	}

	@Override
	public RelativePagerFragment getPrev(FragmentStateManager fsm) {
		return fsm.getFragment(WizardPage2Fragment.class);
	}
}
