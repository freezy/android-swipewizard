package ch.neh.android.swipepager.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ch.neh.android.swipepager.FragmentStateManager;
import ch.neh.android.swipepager.RelativePagerAdapter;
import ch.neh.android.swipepager.RelativeViewPager;

public class MainActivity extends AppCompatActivity implements FragmentStateManager.FragmentStateManageable {

	private RelativePagerAdapter mAdapter;
	private RelativeViewPager mPager;
	private FragmentStateManager mFragmentStateManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupWizard();
	}

	public void setupWizard() {

		// retrieve the pager from the view
		mPager = (RelativeViewPager)findViewById(R.id.pager);

		// create an adapter
		mAdapter = new RelativePagerAdapter(getSupportFragmentManager(), getFragmentStateManager());

		// initialize the first page
		mAdapter.setInitialFragment(getFragmentStateManager().getFragment(WizardPage1Fragment.class));

		// link pager to adapter
		mPager.setAdapter(mAdapter);
	}

	@Override
	public FragmentStateManager getFragmentStateManager() {
		if (mFragmentStateManager == null) {
			mFragmentStateManager = new FragmentStateManager();
		}
		return mFragmentStateManager;
	}
}
