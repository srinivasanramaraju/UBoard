package com.iit.t1.u_board.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.iit.t1.u_board.R;

import java.net.UnknownHostException;

public class CreateNoticeFragment extends Fragment {
    EditText editText_NoticeTitle;
    EditText editText_Description;
    Spinner spinner_category;
    String sales="Sales";
    public CreateNoticeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public void SubmitNotice(View v) throws UnknownHostException {

        //Intent myIntent = new Intent(CreateNoticeFragment.this, SalesFragment.class);// have to change from one fragment to another
        //CreateNoticeFragment.this.startActivity(myIntent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_notice, container, false);

        editText_NoticeTitle = (EditText) rootView.findViewById(R.id.editTextNoticetitle);
        editText_Description = (EditText) rootView.findViewById(R.id.editTextDescription);
        spinner_category=(Spinner)rootView.findViewById(R.id.spinnercategory);
        // Inflate the layout for this fragment
        AppCompatButton submitButton= (AppCompatButton)rootView.findViewById(R.id.buttoncreatenotice);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UboardNotices uboardNotices = new UboardNotices();
                uboardNotices.noticeTitle = editText_NoticeTitle.getText().toString();
                uboardNotices.description = editText_Description.getText().toString();
                uboardNotices.category = spinner_category.getSelectedItem().toString();

                SubmitNoticeAsyncTask tsk = new SubmitNoticeAsyncTask();
                tsk.execute(uboardNotices);
                if (uboardNotices.category.equalsIgnoreCase("Sales")) {
                    Fragment fragment = new SalesFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    ((MainActivity) getActivity())
                            .setActionBar("Sales");
                    Toast.makeText(getActivity(), "Your post regarding sale posted ! ", Toast.LENGTH_SHORT).show();

                } else if (uboardNotices.category.equalsIgnoreCase("Housing")) {
                    Fragment fragment = new HousingFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    ((MainActivity) getActivity())
                            .setActionBar("Housing");
                    Toast.makeText(getActivity(), "Your post regarding Housing posted ! ", Toast.LENGTH_SHORT).show();

                } else if (uboardNotices.category.equalsIgnoreCase("Jobs and Internships")) {
                    Fragment fragment = new JobsAndOpportunitiesFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    ((MainActivity) getActivity())
                            .setActionBar("Jobs and Opportunities");
                    Toast.makeText(getActivity(), "Your post regarding Jobs posted ! ", Toast.LENGTH_SHORT).show();

                } else if (uboardNotices.category.equalsIgnoreCase("Events")) {
                    Fragment fragment = new EventsFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    ((MainActivity) getActivity())
                            .setActionBar("Events");
                    Toast.makeText(getActivity(), "Your post regarding upcoming Events posted ! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
