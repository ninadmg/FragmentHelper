package mgn.ninad.app.fragmenthelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


/**
 * Created by ninad on 27-03-2015.
 */
public abstract class BaseActivity extends ActionBarActivity {

    ArrayList<ArrayList<Fragment>> tabsectionList = new ArrayList<ArrayList<Fragment>>();
    int container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addTabSection(getTabCount());
        container = getContainer();
    }



    public abstract int getTabCount();

    public abstract Toolbar getToolbar();
    /*creating more than one container so that the back press dose not move between Fragments*/
    private void addTabSection(int count)
    {
        for(int i=0;i<count;i++)
        {
            tabsectionList.add(new ArrayList<Fragment>());
        }
    }

    int currentsection=1;

    private ArrayList<Fragment> getcurrentfragmelist()
    {
        return tabsectionList.get(currentsection);
    }

    public void addFragment(Fragment fragment)
    {
        getcurrentfragmelist().add(fragment);
        loadFragment(fragment);

    }

    public void addDialogFragment(BaseDialogeFragment dialogeFragment)
    {
        getcurrentfragmelist().add(dialogeFragment);
        dialogeFragment.show(getSupportFragmentManager(), ((Object)dialogeFragment).getClass().getCanonicalName());
    }
    public void popCurrentFrgment()
    {
        ArrayList<Fragment> section = getcurrentfragmelist();
        section.remove(section.size() - 1);
        loadFragment(section.get(section.size() - 1));
    }

    public void popFragmentWithResult(int resultcode,int resultstatus,Bundle result)
    {
        ArrayList<Fragment> section = getcurrentfragmelist();
        section.remove(section.size() - 1);
        Fragment baseFragment =section.get(section.size()-1);
        loadFragment(baseFragment);
        if(baseFragment instanceof FragmentResult) {
            ((FragmentResult)baseFragment).onFragementResult(resultcode, resultstatus, result);
        }
    }


    private void loadFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(container, fragment)
                .commit();
    }

    protected void onTabChanged(int pos)
    {
        currentsection = pos;
    }

    @Override
    public void onBackPressed() {
        if(getcurrentfragmelist().size()!=1)
        {
            popCurrentFrgment();
        }
        else
        {
            super.onBackPressed();
        }
    }


    public abstract int getContainer();
}
