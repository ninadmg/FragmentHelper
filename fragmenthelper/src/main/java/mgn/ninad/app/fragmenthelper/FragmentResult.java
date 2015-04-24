package mgn.ninad.app.fragmenthelper;

import android.os.Bundle;

/**
 * Created by ninad on 30-03-2015.
 */
public interface FragmentResult {

     String RESULTCODE = "mgn.ninad.app.fragmenthelper.resultcode";
     String RESULTSTATUS = "mgn.ninad.app.fragmenthelper.resultstatus";
     int OK =1;
     int CANCEL = 0;
     void onFragementResult(int requestcode,int responsecode,Bundle data);
}
