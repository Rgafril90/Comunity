package com.rbt.comunity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.rbt.comunity.Activities.DasbordActivity;
import com.rbt.comunity.R;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;

    private CardView ivProf;

    private ImageView ivMenu, ivCreate, btnClose, btnCheck, ivBackground, btnBackTautan;
    private BottomSheetDialog sheetDialog;
    private LinearLayout logout, llEdit, llCheck, llTautan, llTautanAdd;
    private ConstraintLayout clTautan;
    private Button editProfil;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        viewPager2 = view.findViewById(R.id.vp_user);
        tabLayout = view.findViewById(R.id.tb_user);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        ivCreate = view.findViewById(R.id.iv_create);
        ivMenu = view.findViewById(R.id.iv_menu);
        editProfil = view.findViewById(R.id.btn_edit);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialog = new BottomSheetDialog(getContext(), R.style.AppBottomSheetDialogTheme);
                sheetDialog.setContentView(R.layout.fragment_bottom_sheet_menu);
                sheetDialog.show();

                logout = sheetDialog.findViewById(R.id.ll_logout);
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DasbordActivity.class);
                        Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
            }
        });
        ivCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialog = new BottomSheetDialog(getContext(), R.style.AppBottomSheetDialogTheme);
                sheetDialog.setContentView(R.layout.fragment_bottom_sheet_create);
                sheetDialog.show();
            }
        });

        editProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialog = new BottomSheetDialog(getContext(), R.style.AppBottomSheetDialogTheme);
                sheetDialog.setContentView(R.layout.sheet_edit );
                sheetDialog.show();

                btnClose = sheetDialog.findViewById(R.id.btn_close);
                btnCheck = sheetDialog.findViewById(R.id.btn_check);
                ivBackground = sheetDialog.findViewById(R.id.iv_background_edit);
                ivProf = sheetDialog.findViewById(R.id.cv_prof);
                llCheck = sheetDialog.findViewById(R.id.ll_edit_check);
                llEdit = sheetDialog.findViewById(R.id.ll_edit_prof);
                llTautanAdd = sheetDialog.findViewById(R.id.ll_tautan_add);
                llTautan = sheetDialog.findViewById(R.id.ll_tautan);
                clTautan = sheetDialog.findViewById(R.id.cl_tautan);
                btnBackTautan = sheetDialog.findViewById(R.id.btn_back_tautan);

                clTautan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llCheck.setVisibility(View.GONE);
                        llEdit.setVisibility(View.GONE);
                        llTautanAdd.setVisibility(View.VISIBLE);
                        llTautan.setVisibility(View.VISIBLE);
                    }
                });

                btnBackTautan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llCheck.setVisibility(View.VISIBLE);
                        llEdit.setVisibility(View.VISIBLE);
                        llTautanAdd.setVisibility(View.GONE);
                        llTautan.setVisibility(View.GONE);
                    }
                });

                ivBackground.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Background", Toast.LENGTH_SHORT).show();
                    }
                });
                ivProf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Profil", Toast.LENGTH_SHORT).show();
                    }
                });

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetDialog.dismiss();
                    }
                });
                btnCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetDialog.dismiss();
                    }
                });
            }
        });



        initView();
        return view;
    }

    private void initView() {
        FragmentManager fm = getChildFragmentManager();
        adapter = new ViewPagerAdapter(fm, getLifecycle());

        adapter.addFragment(new PostFragment(), "Post");
        adapter.addFragment(new ComunityFragment(), "Comunity");

        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(1);

        new TabLayoutMediator(tabLayout,viewPager2,
                (tab,position) -> {
                    tab.setText(adapter.fragmentTitleList.get(position));
                }).attach();

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView tv = (TextView) LayoutInflater.from(getContext())
                    .inflate(R.layout.custom_tabs, null);
            tabLayout.getTabAt(i).setCustomView(tv);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    class ViewPagerAdapter extends FragmentStateAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }
        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
             fragmentTitleList.add(title);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}