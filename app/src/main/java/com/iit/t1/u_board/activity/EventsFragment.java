package com.iit.t1.u_board.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.internal.widget.ListViewCompat;

import com.iit.t1.u_board.R;
import com.iit.t1.u_board.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by CREATIVITY on 11/4/2015.
 */
public class EventsFragment extends ListFragment {
    ArrayList<UboardNotices> returnValues = new ArrayList<UboardNotices>();
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> passlistItems=new ArrayList<String>();
    ListViewCompat listview;

    public EventsFragment() {
        // Required empty public constructor
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }
*/
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GetNoticeAsyncTask task = new GetNoticeAsyncTask();
        try {
            returnValues = task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(UboardNotices x: returnValues) {
            System.out.println(x.getCategory().toString());
            if (x.getCategory().toString().equalsIgnoreCase("Events")) {
                System.out.println("Inside If");
                listItems.add(x.getNoticeTitle());
            }else
            {
                continue;
            }
        }

        ListAdapter listNotices= new ListAdapter(getActivity(), R.layout.custom_adapter_view,listItems, UboardNotices.noticepics);
        setListAdapter(listNotices);
        registerForContextMenu(getListView());
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

