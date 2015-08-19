package ch.neh.android.swipepager.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.neh.android.swipepager.FragmentStateManager;
import ch.neh.android.swipepager.RelativePagerFragment;
import ch.neh.android.swipepager.WizardStepFragment;

public class WizardPage4Fragment extends WizardStepFragment {

	public WizardPage4Fragment() {
		super(R.layout.fragment_wizard_4);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = super.onCreateView(inflater, container, savedInstanceState);

		if (view != null) {
			view.findViewById(R.id.btn_start_over).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getStatusChangeListener().onPrevPage();
				}
			});
		}
		return view;
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
