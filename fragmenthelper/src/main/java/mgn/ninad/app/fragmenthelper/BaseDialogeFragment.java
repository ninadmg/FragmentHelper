package mgn.ninad.app.fragmenthelper;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by ninad on 30-03-2015.
 */
public class BaseDialogeFragment extends DialogFragment implements FragmentResult{

    protected int resultcode;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    @Override
    public void onStart() {
        super.onStart();
        resultcode = getArguments().getInt(RESULTCODE);
    }

    /**pop with result if the result set result was already called the the fragment
     *
     * @param bundle bundel to be passed to the previous Fragment
     * @param ResultStaus status of result.
     */
    public void popWithResult(Bundle bundle,int ResultStaus)
    {
        popWithResult(bundle, ResultStaus, getResultcode());
    }

    /**pop the current Fragment with result
     *
     * @param bundle bundle to be passed to previous fragment
     * @param ResultStaus status of result.
     * @param resultcode result code of this Fragment.
     */
    public void popWithResult(Bundle bundle,int ResultStaus,int resultcode) {
        if (getActivity() instanceof BaseActivity)
        {
            ((BaseActivity) getActivity()).popFragmentWithResult(resultcode, ResultStaus,bundle);
        }
        else
        {
            throw new ClassCastException("Activity should extend Base activity");
        }

    }


    public void onFragementResult(int requestcode,int responsecode,Bundle data)
    {

    }
}
