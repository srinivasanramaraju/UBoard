package com.iit.t1.u_board.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.internal.widget.ListViewCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.iit.t1.u_board.R;
import com.iit.t1.u_board.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by CREATIVITY on 11/4/2015.
 */
public class EventsFragment extends ListFragment {
    private static final int DELETE_ID = Menu.FIRST + 1;
    private static final int SHARE_ID = Menu.FIRST + 2;
    private static final int UPDATE_ID = Menu.FIRST + 3;
    public  static String[] arrayOfEvents;
    public static  String[] arrayOfEventsTitle;
    ArrayList<UboardNotices> returnValues = new ArrayList<UboardNotices>();
    ArrayList<String> dummylist=new ArrayList<String>();
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
        registerForContextMenu(getListView());
        setHasOptionsMenu(true);
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
                dummylist.add(x.getDescription());
            }else
            {
                continue;
            }
        }
        arrayOfEvents = dummylist.toArray(new String[dummylist.size()]);
        arrayOfEventsTitle=listItems.toArray(new String[listItems.size()]);
        ListAdapter listNotices= new ListAdapter(getActivity(), R.layout.custom_adapter_view,listItems, UboardNotices.noticepics);
        setListAdapter(listNotices);
        registerForContextMenu(getListView());
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getListView().setItemChecked(position, true);
                Fragment fragment = new ViewNoticeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragment = ViewNoticeFragment.newInstance(position);
                ViewNoticeFragment.findCategory(3);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,UPDATE_ID, 0, R.string.menu_update);
        menu.add(0,DELETE_ID,0,R.string.menu_delete);
        menu.add(0,SHARE_ID,0,R.string.menu_share);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                return true;
            case UPDATE_ID:
                return true;
            case SHARE_ID:
                return true;
        }
        return super.onContextItemSelected(item);
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

