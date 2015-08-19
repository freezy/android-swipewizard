Swipe Wizard for Android
========================

A dynamic next/prev pager where each step defines the flow.


Idea
----

A view pager for fragments. Every step corresponds to a fragment and defines on
its own the next and previous step. This makes dynamic chaining up of wizard
steps easy.


Download
--------

Add this line to your Gradle dependencies:

    compile 'ch.neh.android:swipewizard:0.1.0@aar'


Usage
-----

In order to use the pager, add this widget to your layout:

```xml
<ch.neh.android.swipepager.RelativeViewPager
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_alignParentTop="true"/>
```

Then, in your Activity, initialize it:

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // retrieve the pager from the view
    mPager = (RelativeViewPager)findViewById(R.id.pager);

    // create an adapter
    mAdapter = new RelativePagerAdapter(getSupportFragmentManager(), getFragmentStateManager());

    // initialize the first page
    mAdapter.setInitialFragment(getFragmentStateManager().getFragment(WizardPage1Fragment.class));

    // link pager to adapter
    mPager.setAdapter(mAdapter);
}
```

Finally, add a Fragment for each step of the wizard by extending `WizardStepFragment`:

```java
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
```

License
-------

```
Copyright 2015 freezy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```