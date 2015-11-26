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

public class SalesFragment extends ListFragment {
    private static final int DELETE_ID = Menu.FIRST + 1;
    private static final int SHARE_ID = Menu.FIRST + 2;
    private static final int UPDATE_ID = Menu.FIRST + 3;
    public  static String[] arrayOfSales;
    public static  String[] arrayOfSalesTitle;
    ArrayList<String> dummylist=new ArrayList<String>();
    ArrayList<UboardNotices> returnValues = new ArrayList<UboardNotices>();
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> passlistItems=new ArrayList<String>();
    ListViewCompat listview;

    public SalesFragment() {
        // Required empty public constructor
    }

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
            if (x.getCategory().toString().equalsIgnoreCase("sales")) {
                System.out.println("Inside If");
                listItems.add(x.getNoticeTitle());
                dummylist.add(x.getDescription());
            }else
            {
                continue;
            }
        }
        arrayOfSales = dummylist.toArray(new String[dummylist.size()]);
        arrayOfSalesTitle=listItems.toArray(new String[listItems.size()]);
        ListAdapter listNotices= new ListAdapter(getActivity(),R.layout.custom_adapter_view,listItems, UboardNotices.noticepics);
        setListAdapter(listNotices);
        registerForContextMenu(getListView());
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getListView().setItemChecked(position, true);
                Fragment fragment = new ViewNoticeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragment = ViewNoticeFragment.newInstance(position);
                ViewNoticeFragment.findCategory(1);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
            }
        });
    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sales, container, false);
        listview=rootView.findViewById(R.id.saleslistView);

        listview.setAdapter(listNotices);
        listview.setTextFilterEnabled(true);
        registerForContextMenu(listview);
        // Inflate the layout for this fragment
        return rootView;
    }*/
   @Override
   public void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);

   }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,UPDATE_ID, 0, R.string.menu_update);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
        menu.add(0, SHARE_ID, 0, R.string.menu_share);
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
