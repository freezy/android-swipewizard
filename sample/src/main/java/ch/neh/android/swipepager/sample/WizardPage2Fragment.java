package ch.neh.android.swipepager.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.neh.android.swipepager.FragmentStateManager;
import ch.neh.android.swipepager.RelativePagerFragment;
import ch.neh.android.swipepager.WizardStepFragment;

public class WizardPage2Fragment extends WizardStepFragment {

	private boolean mContinueWithA = true;

	public WizardPage2Fragment() {
		super(R.layout.fragment_wizard_2);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = super.onCreateView(inflater, container, savedInstanceState);

		if (view != null) {
			view.findViewById(R.id.goto_a).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mContinueWithA = true;
					mStatusChangeListener.onStatusUpdated();
				}
			});
			view.findViewById(R.id.goto_b).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mContinueWithA = false;
					mStatusChangeListener.onStatusUpdated();
				}
			});
		}
		return view;
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
		return fsm.getFragment(mContinueWithA ? WizardPage3aFragment.class : WizardPage3bFragment.class);
	}

	@Override
	public RelativePagerFragment getPrev(FragmentStateManager fsm) {
		return fsm.getFragment(WizardPage1Fragment.class);
	}
}
