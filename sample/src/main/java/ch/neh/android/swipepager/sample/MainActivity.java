package ch.neh.android.swipepager.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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

		mPager = (RelativeViewPager)findViewById(R.id.pager);
		mAdapter = new RelativePagerAdapter(getSupportFragmentManager(), getFragmentStateManager());
		final Fragment firstPage = getFragmentStateManager().getFragment(WizardPage1Fragment.class);
		mAdapter.setInitialFragment((WizardPage1Fragment)firstPage);

		// init pager
		mPager.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public FragmentStateManager getFragmentStateManager() {
		if (mFragmentStateManager == null) {
			mFragmentStateManager = new FragmentStateManager();
		}
		return mFragmentStateManager;
	}
}
