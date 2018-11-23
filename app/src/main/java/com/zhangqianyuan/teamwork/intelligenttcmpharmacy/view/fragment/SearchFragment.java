package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.IBaseView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 搜索fragment
 * Created at: 2018/10/21 11:20
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/10/21 搜索功能，和服务器结合
public class SearchFragment extends Fragment implements IBaseView {


    //药物简介
    @BindView(R.id.introduction_content)
    TextView introduction;

    //生长环境
    @BindView(R.id.growth_habit_content)
    TextView  growth;

    //药用价值
    @BindView(R.id.medicinal_value_content)
    TextView  way;

    //特点
    @BindView(R.id.character_content)
    TextView  character;

    @BindView(R.id.et_searchMedical)
    EditText  search;

    @BindView(R.id.btn_search)
    Button  ok;

    private View view;
    private Context context;

    public static Fragment newInstance(){
        SearchFragment fragment = new SearchFragment();
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, null);
        context = getContext();
        initView(view);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                introduction.setText("当归，（学名：Angelica sinensis，）别名干归、秦哪、西当归、岷当归、金当归、当归身、涵归尾、当归曲、土当归，多年生草本，高0.4-1米。花期6-7月，果期7-9月。\n" +
                        "中国1957年从欧洲引种欧当归。主产甘肃东南部，以岷县产量多，质量好，其次为云南、四川、陕西、湖北等省，均为栽培。国内有些省区也已引种栽培。");
                growth.setText("为低温长日照作物，宜高寒凉爽气候，在海拔1500-3000m左右均可栽培\n" +
                        "。在低海的地区栽培抽苔率高，不易越夏。幼苗期喜阴，透光度为10%，忌烈日直晒；成株能耐强光。\n" +
                        "宜土层深厚、疏松、排水良好、肥沃富含腐殖质的砂质壤土栽培，不宜在低洼积水或者易板结的粘土和贫瘠的砂质土栽种，忌连作。 [3] ");
                way.setText("基原：\n" +
                        "　　为伞形科多年生草本植物当归的根。\n" +
                        "　　该品为伞形科植物当归Angelica sinensis （Oliv、） Diels 的干燥根。秋末采挖，除去须根及泥沙，待水分稍蒸发后，捆成小把，上棚，用烟火慢慢熏干。全当归根略呈圆柱形，根上端称“归头”，\n" +
                        "主根称“归身”或“寸身”。支根称“归尾”或“归腿”，全体称“全归”。全当归既能补血，又可活血，统称和血；当归身补血，当归尾破血。　乾归（见《神农本草经》）山薪（见《尔雅》）白薪（见《尔雅》），文无（见《本草纲目》）\n" +
                        "　　性味：\n" +
                        "　　甘、辛、温 [5]  \n" +
                        "　　①《本经》：味甘，温。\n" +
                        "　　②《吴普本草》：神农、黄帝、桐君、扁鹊：甘，无毒。岐伯、雷公：辛、无毒。李氏：小温。\n" +
                        "　　③《别录》：辛，大温，无毒。\n" +
                        "　　④《本草述》：味苦，温，无毒。\n" +
                        "　　归经：\n" +
                        "　　归肝、心、脾经。 [5]  \n" +
                        "　　①《汤液本草》：入手少阴、足太阴、厥阴经。\n" +
                        "　　②《雷公炮制药性解》：入心、肝、肺三经。\n" +
                        "　　采集加工：\n" +
                        "　　秋末采挖，除去须根，待水分稍蒸发后，捆成小把，上棚，用烟火慢慢熏干。\n" +
                        "　　炮制\n" +
                        "当归：拣去杂质，洗净，闷润，稍晾至内外湿度适宜时，切片晒干。\n" +
                        "酒当归：取当归片，用黄酒喷淋均匀，稍闷，置锅内用微火炒，取出，放凉（每当归片100斤，用黄酒10斤）。\n" +
                        "《雷公炮炙论》：凡使当归，先去尘并头尖硬处一分已来，酒浸一宿。 [3] ");
                character.setText("形态特点：多年生草本，高0.4-1米。\n根圆柱状，分枝，有多数肉质须根，黄棕色，有浓郁香气。茎直立，绿白色或带紫色，有纵深沟纹，光滑无毛。");
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(View view){
        ButterKnife.bind(this,view);
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }


    @Override
    public void showInfo(Object object, Boolean issucced) {

    }
}
