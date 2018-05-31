package app.template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import app.template.common.EnvInfoAbout;
import app.template.s42.templateandroid.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView txtAbout = findViewById(R.id.txtAbout);

        EnvInfoAbout environmentInfo = new EnvInfoAbout(AboutActivity.this);

        if(environmentInfo.getIsLoaded()) {
            txtAbout.setText(Html.fromHtml(aboutStr(environmentInfo.getVersion(), environmentInfo.getBuild(), environmentInfo.getDate(), environmentInfo.getCommit(), environmentInfo.getCommitFull(), environmentInfo.getGithub())));
            txtAbout.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            txtAbout.setVisibility(View.GONE);
        }
    }

    private String aboutStr(String version, String build, String date, String commit, String commitFull, String github) {
        String str = getResources().getString(R.string.app_name) + ", version "+ version +", build "+ build +".<br />";
        str += "Built on "+ date +". Last commit <a href='"+github+"/commit/"+commitFull+"'>"+ commit +"</a>.<br />";
        str += "(C) <a href='http://teclib-edition.com/'>Teclib'</a> 2017. Licensed under <a href='https://www.gnu.org/licenses/gpl-3.0.en.html'>GPLv3</a>. <a href='https://flyve-mdm.com/'>Flyve MDM</a> (R)";

        return str;
    }
}
